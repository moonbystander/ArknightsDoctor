package ArknightsDoctorMod.contracts.chain.Blade;

import ArknightsDoctorMod.contracts.AbstractContractsChain;
import ArknightsDoctorMod.contracts.contract.Blade.Crownslayer_WolfsFang;

public class Crownslayer_WolfsFangChain extends AbstractContractsChain {

    public static String ID="弑君者：狼牙";
    public Crownslayer_WolfsFangChain(){
        super(ID);
        this.setStartContracts();
    }

    @Override
    public void setStartContracts() {
        this.addContract(new Crownslayer_WolfsFang(ID+"II",1.7f,2,5,2,2));
        this.addContract(new Crownslayer_WolfsFang(ID+"III",2.3f,3,2,1,2));
    }
}
