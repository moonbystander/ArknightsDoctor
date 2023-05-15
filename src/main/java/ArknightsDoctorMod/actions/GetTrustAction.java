package ArknightsDoctorMod.actions;

import ArknightsDoctorMod.characters.Doctor;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class GetTrustAction extends AbstractGameAction {

    public GetTrustAction(AbstractCreature target,int amount){
        this.target=target;
        this.amount = amount;
    }

    @Override
    public void update() {
        this.isDone=true;
        Doctor doctor=(Doctor)target;
        doctor.increaseTrust(amount);
    }
}
