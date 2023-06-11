package ArknightsDoctorMod.actions;

import ArknightsDoctorMod.cards.AbstractOperatorsCard;
import ArknightsDoctorMod.powers.AbstractOperatorRedeploymentPower;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

public class SacrificeStrategyAction extends AbstractGameAction {

    public SacrificeStrategyAction(int amount){
        this.amount=amount;
    }

    @Override
    public void update() {
        this.isDone=true;
        ArrayList<AbstractCard> handOperators=new ArrayList<>();
        for (AbstractCard card:AbstractDungeon.player.hand.group){
            if (card instanceof AbstractOperatorsCard){
                handOperators.add(card);
            }
        }
        if (handOperators.size()!=0){
            this.addToBot(new SelectCardsAction(handOperators,1,"选择一张干员牌",false,card -> true,
                    (list) ->
                    {
                        list.forEach(card -> ((AbstractOperatorsCard)card).OnRetreat());
                        list.clear();
                    })
            );
            for (AbstractPower power:AbstractDungeon.player.powers) {
                if (power instanceof AbstractOperatorRedeploymentPower ){
                    this.addToBot(new ReducePowerAction(AbstractDungeon.player,AbstractDungeon.player,power,this.amount));
                }
            }
        }

    }
}
