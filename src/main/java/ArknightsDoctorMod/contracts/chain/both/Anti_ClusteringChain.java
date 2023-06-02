package ArknightsDoctorMod.contracts.chain.both;

import ArknightsDoctorMod.contracts.AbstractContractsChain;
import ArknightsDoctorMod.contracts.contract.Anti_Clustering;

public class Anti_ClusteringChain extends AbstractContractsChain {

    public static String ID="反集群";

    public Anti_ClusteringChain(){
        super(ID);
        this.setStartContracts();
    }

    @Override
    public void setStartContracts() {
        this.addContract(new Anti_Clustering(ID));
    }
}
