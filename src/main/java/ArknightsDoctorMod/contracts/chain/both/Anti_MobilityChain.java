package ArknightsDoctorMod.contracts.chain.both;

import ArknightsDoctorMod.contracts.AbstractContractsChain;
import ArknightsDoctorMod.contracts.contract.Anti_Mobility;

public class Anti_MobilityChain extends AbstractContractsChain {
    public static String ID="反机动";

    public Anti_MobilityChain(){
        super(ID);
        this.setStartContracts();
    }

    @Override
    public void setStartContracts() {
        this.addContract(new Anti_Mobility(ID));
    }

}
