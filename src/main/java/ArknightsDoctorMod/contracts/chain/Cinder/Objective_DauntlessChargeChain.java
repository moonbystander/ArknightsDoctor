package ArknightsDoctorMod.contracts.chain.Cinder;

import ArknightsDoctorMod.contracts.AbstractContractsChain;
import ArknightsDoctorMod.contracts.contract.Cinder.Objective_DauntlessCharge;

public class Objective_DauntlessChargeChain extends AbstractContractsChain {
    public static String ID="目标:无畏冲锋";

    public Objective_DauntlessChargeChain(){
        super(ID);
        this.setStartContracts();
    }

    @Override
    public void setStartContracts() {
        this.addContract(new Objective_DauntlessCharge(ID+"I",1.5f,6,1));
        this.addContract(new Objective_DauntlessCharge(ID+"II",1.8f,10,1.5f));
    }
}
