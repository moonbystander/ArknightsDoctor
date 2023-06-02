package ArknightsDoctorMod.contracts.chain.both;

import ArknightsDoctorMod.contracts.AbstractContractsChain;
import ArknightsDoctorMod.contracts.contract.Anti_Armor;

public class Anti_ArmorChain extends AbstractContractsChain {
    public static String ID="反装甲";

    public Anti_ArmorChain(){
        super(ID);
        this.setStartContracts();
    }

    @Override
    public void setStartContracts() {
        this.addContract(new Anti_Armor(ID+"I",4));
        this.addContract(new Anti_Armor(ID+"II",8));
    }
}
