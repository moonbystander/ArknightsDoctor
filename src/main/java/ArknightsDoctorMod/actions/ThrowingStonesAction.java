package ArknightsDoctorMod.actions;

import ArknightsDoctorMod.cards.operators.RhodesIslandOperators.Amiya;
import ArknightsDoctorMod.characters.Doctor;
import ArknightsDoctorMod.relics.OperatorRecords;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.UUID;

public class ThrowingStonesAction extends AbstractGameAction {

    public ThrowingStonesAction(AbstractCreature target){
        this.target=target;
    }

    @Override
    public void update() {
        this.isDone=true;
        UUID uuid=null;
        //获取阿米娅uuid
        for (AbstractCard c:((AbstractPlayer)target).masterDeck.group) {
            if (c.cardID.equals(Amiya.ID)){
                uuid=c.uuid;
                break;
            }
        }
        if (uuid==null){
            return;
        }
        //遍历所有堆
        for (AbstractCard c:((AbstractPlayer)target).hand.group) {
            if (c.uuid.equals(uuid)){
                //在手牌里就不继续找了
                return;
            }
        }

        //查找待部署区
        for (AbstractCard c:((Doctor)target).OperatorsWaiting.group) {
            if (c.uuid.equals(uuid)){
                //移动到手牌
                c.unfadeOut();
                ((Doctor)target).OperatorsWaiting.removeCard(c);
                ((AbstractPlayer)target).hand.addToTop(c);
                if (this.target.hasPower(((Amiya)c).powerId)){
                    AbstractPower p= AbstractDungeon.player.getPower(((Amiya)c).powerId);
                    p.amount=0;
                    this.target.powers.remove(p);
                }
                return;
            }
        }



        for (AbstractCard c:((AbstractPlayer)target).drawPile.group) {
            if (c.uuid.equals(uuid)){
                //移动到手牌
                c.unfadeOut();
                ((AbstractPlayer)target).drawPile.removeCard(c);
                ((AbstractPlayer)target).hand.addToTop(c);
                return;
            }
        }

        for (AbstractCard c:((AbstractPlayer)target).discardPile.group) {
            if (c.uuid.equals(uuid)){
                //移动到手牌
                c.unfadeOut();
                ((AbstractPlayer)target).discardPile.removeCard(c);
                ((AbstractPlayer)target).hand.addToTop(c);
                return;
            }
        }

        for (AbstractCard c:((AbstractPlayer)target).exhaustPile.group) {
            if (c.uuid.equals(uuid)){
                //移动到手牌
                c.unfadeOut();
                ((AbstractPlayer)target).exhaustPile.removeCard(c);
                ((AbstractPlayer)target).hand.addToTop(c);
                return;
            }
        }


    }
}
