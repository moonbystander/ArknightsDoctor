package ArknightsDoctorMod.actions;

import ArknightsDoctorMod.powers.RepeatedlyPlayedPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class Tactics_RetreatAction extends AbstractGameAction {

    public Tactics_RetreatAction(AbstractCreature target){
        this.target=target;
    }

    @Override
    public void update() {
        this.isDone=true;
        if (target.hasPower(RepeatedlyPlayedPower.POWER_ID)){
            AbstractPower p=target.getPower(RepeatedlyPlayedPower.POWER_ID);
            this.addToBot(new GetTrustAction(target,p.amount));

        }
    }
}
