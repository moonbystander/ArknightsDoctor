package ArknightsDoctorMod.actions;

import ArknightsDoctorMod.cards.AbstractOperatorsCard;
import ArknightsDoctorMod.characters.Doctor;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

public class SacrificeForTimeAction extends AbstractGameAction {

    public SacrificeForTimeAction(AbstractCreature target,int amount){
        this.target=target;
        this.amount=amount;
    }


    @Override
    public void update() {
        this.isDone=true;
        ArrayList<AbstractCard> op=new ArrayList<>();
        for (AbstractCard card: ((AbstractPlayer)target).drawPile.group) {
            if (card instanceof AbstractOperatorsCard){
                op.add(card);
            }
        }
        if (op.size() > 0){
            op.forEach(card -> ((AbstractPlayer)target).drawPile.moveToExhaustPile(card));
            op.clear();
        }

        for (AbstractCard card: ((AbstractPlayer)target).hand.group) {
            if (card instanceof AbstractOperatorsCard){
                op.add(card);
            }
        }
        if (op.size() > 0){
            op.forEach(card -> ((AbstractPlayer)target).hand.moveToExhaustPile(card));
            op.clear();
        }

        for (AbstractCard card: ((AbstractPlayer)target).discardPile.group) {
            if (card instanceof AbstractOperatorsCard){
                op.add(card);
            }
        }
        if (op.size() > 0){
            op.forEach(card -> ((AbstractPlayer)target).discardPile.moveToExhaustPile(card));
            op.clear();
        }

        this.addToBot(new SelectCardsAction(((Doctor) AbstractDungeon.player).OperatorsWaiting.group,1,"选择一名干员加入手牌",
                false,card -> true,
                (list)->{
                    list.forEach(card ->
                            {
                                String powerId=((AbstractOperatorsCard)card).powerId;
                                if (AbstractDungeon.player.hasPower(powerId)){
                                    AbstractPower p=AbstractDungeon.player.getPower(powerId);
                                    p.amount=0;
                                    this.target.powers.remove(p);
                                }
                                card.unfadeOut();
                                AbstractDungeon.player.hand.addToTop(card);
                                ((Doctor)AbstractDungeon.player).OperatorsWaiting.removeCard(card);
                            }
                    );
                    list.clear();
                })
        );

    }
}
