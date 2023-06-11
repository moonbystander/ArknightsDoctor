package ArknightsDoctorMod.actions;

import ArknightsDoctorMod.cards.AbstractOperatorsCard;
import ArknightsDoctorMod.characters.Doctor;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AngerPower;

public class AngerSpreadingAction extends AbstractGameAction {

    public AngerSpreadingAction(AbstractCreature target,int amount){
        this.target=target;
        this.amount=amount;
    }

    @Override
    public void update() {
        this.isDone=true;
        AbstractDungeon.player.gainEnergy(amount);
        this.addToBot(new ApplyPowerAction(target,AbstractDungeon.player,new AngerPower(target,2)));
        this.addToBot(new SelectCardsAction(((Doctor)AbstractDungeon.player).OperatorsWaiting.group,1,"选择一名干员加入手牌",
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
