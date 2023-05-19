package ArknightsDoctorMod.actions;

import ArknightsDoctorMod.cards.AbstractOperatorsCard;
import ArknightsDoctorMod.cards.AbstractOperatorsSkillCard;
import ArknightsDoctorMod.characters.Doctor;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.unique.ExhumeAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;
import java.util.Iterator;

//干员退场动作，将干员牌和再部署能力从本场战斗中移除
public class RetreatAction extends AbstractGameAction {

    public AbstractOperatorsCard card;
    public String powerId;

    //传入干员牌
    public RetreatAction(AbstractCreature target, AbstractOperatorsCard card,String powerId){
        this.target=target;
        this.card=card;
        this.powerId=powerId;
    }

    @Override
    public void update() {
        this.isDone=true;
        //移动到消耗堆 √
        this.addToBot(new CardMoveAction(card, ((Doctor)this.target).OperatorsWaiting,(AbstractDungeon.player).exhaustPile));
        this.addToBot(new CardMoveAction(card, ((Doctor)this.target).discardPile,(AbstractDungeon.player).exhaustPile));
        this.addToBot(new CardMoveAction(card, ((Doctor)this.target).drawPile,(AbstractDungeon.player).exhaustPile));
        this.addToBot(new CardMoveAction(card, ((Doctor)this.target).hand,(AbstractDungeon.player).exhaustPile));
        //移除干员再部署power √
        if (this.target.hasPower(powerId)){
            AbstractPower p=AbstractDungeon.player.getPower(powerId);
            p.amount=0;
            this.target.powers.remove(p);
        }
        //移除所有专属牌
        Iterator it=((AbstractPlayer)this.target).drawPile.group.iterator();
        AbstractCard c;
        ArrayList<AbstractCard> deletelist=new ArrayList<>();

        while (it.hasNext()){
            c= (AbstractCard) it.next();
            //如果类型是专属牌
            if (c instanceof AbstractOperatorsSkillCard ){
                //若专属牌的干员名是此干员牌，则丢入消耗堆
                if (((AbstractOperatorsSkillCard)c).OperatorsCardId.equals(card.cardID)){
                    if (((Doctor) this.target).cardInUse != this.card ){
                        deletelist.add(c);
                    }
                }
            }
        }
        it= deletelist.iterator();
        while (it.hasNext()){
            c= (AbstractCard) it.next();
            ((AbstractPlayer)this.target).drawPile.moveToExhaustPile(c);
            it.remove();
        }

        it=((AbstractPlayer)this.target).hand.group.iterator();
        while (it.hasNext()){
            c= (AbstractCard) it.next();
            //如果类型是专属牌
            if (c instanceof AbstractOperatorsSkillCard ){
                //若专属牌的干员名是此干员牌，则丢入消耗堆
                if (((AbstractOperatorsSkillCard)c).OperatorsCardId.equals(card.cardID)){
                    if (((Doctor) this.target).cardInUse != c){
                        deletelist.add(c);
                    }
                }
            }
        }
        it= deletelist.iterator();
        while (it.hasNext()){
            c= (AbstractCard) it.next();
            ((AbstractPlayer)this.target).hand.moveToExhaustPile(c);
            it.remove();
        }

        it=((AbstractPlayer)this.target).discardPile.group.iterator();
        while (it.hasNext()){
            c= (AbstractCard) it.next();
            //如果类型是专属牌
            if (c instanceof AbstractOperatorsSkillCard ){
                //若专属牌的干员名是此干员牌，则丢入消耗堆
                if (((AbstractOperatorsSkillCard)c).OperatorsCardId.equals(card.cardID)){
                    if (((Doctor) this.target).cardInUse != c){
                        deletelist.add(c);
                    }
                }
            }
        }
        it=deletelist.iterator();
        while (it.hasNext()){
            c= (AbstractCard) it.next();
            ((AbstractPlayer)this.target).discardPile.moveToExhaustPile(c);
            it.remove();
        }



    }
}
