package ArknightsDoctorMod.contracts.chain.both;

import ArknightsDoctorMod.contracts.AbstractContractsChain;
import ArknightsDoctorMod.contracts.contract.OriginiumZone_Stimulus;

public class OriginiumZone_StimulusChain extends AbstractContractsChain {

    public static String ID="源石环境:刺激";

    public OriginiumZone_StimulusChain(){
        super(ID);
        this.setStartContracts();
    }

    @Override
    public void setStartContracts() {
        this.addContract(new OriginiumZone_Stimulus(ID+"I",1.3f));
        this.addContract(new OriginiumZone_Stimulus(ID+"II",1.5f));
        this.addContract(new OriginiumZone_Stimulus(ID+"III",1.8f));
    }
}
