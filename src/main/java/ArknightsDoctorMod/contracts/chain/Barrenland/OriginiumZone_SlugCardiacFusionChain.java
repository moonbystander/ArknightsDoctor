package ArknightsDoctorMod.contracts.chain.Barrenland;

import ArknightsDoctorMod.contracts.AbstractContractsChain;
import ArknightsDoctorMod.contracts.contract.Barrenland.OriginiumZone_SlugCardiacFusion;

public class OriginiumZone_SlugCardiacFusionChain extends AbstractContractsChain {
    public static String ID="源石环境:虫心聚变";

    public OriginiumZone_SlugCardiacFusionChain(){
        super(ID);
        this.setStartContracts();
    }

    @Override
    public void setStartContracts() {
        this.addContract(new OriginiumZone_SlugCardiacFusion(ID+"I",1f,1.4f));
        this.addContract(new OriginiumZone_SlugCardiacFusion(ID+"II",1.35f,1.8f));
        this.addContract(new OriginiumZone_SlugCardiacFusion(ID+"III",1.8f,2.5f));
    }
}
