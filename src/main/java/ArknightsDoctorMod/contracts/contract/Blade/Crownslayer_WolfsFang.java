package ArknightsDoctorMod.contracts.contract.Blade;

import ArknightsDoctorMod.contracts.AbstractContract;

public class Crownslayer_WolfsFang extends AbstractContract {

    public int magicNumber;
    public int powerTurn;
    public int energyTurn;
    public int energyCount;

    public Crownslayer_WolfsFang(String name,float hp,int magicNumber,int powerTurn,int energyTurn, int energyCount){
        super();
        this.setName(name);
        this.setHp(hp);
        this.magicNumber=magicNumber;
        this.powerTurn=powerTurn;
        this.energyTurn=energyTurn;
        this.energyCount=energyCount;
    }
}
