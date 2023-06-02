package ArknightsDoctorMod.contracts.chain.both;

import ArknightsDoctorMod.contracts.AbstractContractsChain;
import ArknightsDoctorMod.contracts.contract.OriginiumZone_Activation;
import ArknightsDoctorMod.contracts.contract.OriginiumZone_Stimulus;

public class OriginiumZone_ActivationChain extends AbstractContractsChain {

    public static String ID="源石环境:活性";

    public OriginiumZone_ActivationChain(){
        super(ID);
        this.setStartContracts();
    }

    @Override
    public void setStartContracts() {
        this.addContract(new OriginiumZone_Activation(ID+"I",1.4f));
        this.addContract(new OriginiumZone_Activation(ID+"II",1.8f));
        this.addContract(new OriginiumZone_Activation(ID+"III",2.2f));
    }
}
