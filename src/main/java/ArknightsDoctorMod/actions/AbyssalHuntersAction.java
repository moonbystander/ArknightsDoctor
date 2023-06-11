package ArknightsDoctorMod.actions;

import ArknightsDoctorMod.cards.operators.AbyssalHuntersOperator;
import ArknightsDoctorMod.powers.AbstractOperatorRedeploymentPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class AbyssalHuntersAction extends AbstractGameAction {

    public AbyssalHuntersAction(){

    }

    @Override
    public void update() {
        this.isDone=true;
        for (AbstractPower p:AbstractDungeon.player.powers) {
            if (p instanceof AbstractOperatorRedeploymentPower){
                if(((AbstractOperatorRedeploymentPower)p).card instanceof AbyssalHuntersOperator){
                    this.addToBot(new ReducePowerAction(AbstractDungeon.player,AbstractDungeon.player,p,1));
                }
            }
        }
    }
}
