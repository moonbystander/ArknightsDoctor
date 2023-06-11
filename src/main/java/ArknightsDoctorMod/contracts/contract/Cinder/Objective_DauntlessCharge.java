package ArknightsDoctorMod.contracts.contract.Cinder;

import ArknightsDoctorMod.contracts.AbstractContract;

public class Objective_DauntlessCharge extends AbstractContract {

    public int k;
    public float a;
    public Objective_DauntlessCharge(String name,float hp,int k,float a){
        this.name=name;
        this.hp=hp;
        this.k=k;
        this.a=a;
    }

    @Override
    public float calculateAttack(float attackAmount) {
        return super.calculateAttack(attackAmount)/6*k;
    }


}
