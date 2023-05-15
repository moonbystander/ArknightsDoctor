package ArknightsDoctorMod.actions;

import ArknightsDoctorMod.characters.Doctor;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

//信赖减少
public class LossTrustAction extends AbstractGameAction {

    public LossTrustAction(AbstractCreature target, int amount){
        this.target=target;
        this.amount = amount;
    }

    @Override
    public void update() {
        this.isDone=true;
        Doctor doctor=(Doctor) target;
        doctor.reduceTrust(amount);
    }
}
