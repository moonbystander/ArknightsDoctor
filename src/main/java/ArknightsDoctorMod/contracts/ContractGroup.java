package ArknightsDoctorMod.contracts;

import ArknightsDoctorMod.cards.AbstractContractCard;
import ArknightsDoctorMod.contracts.chain.both.*;

import java.util.ArrayList;
import java.util.Iterator;

public class ContractGroup {
    public ArrayList<AbstractContractsChain> AllContracts=new ArrayList<>();
    public ArrayList<AbstractContract> applyContracts=new ArrayList<>();
    public float attack=1.0f;
    public float defence=1.0f;
    public float hp=1.0f;
    public int baseDefence=0;
    public int cost=0;
    public int draw=0;

    public ContractGroup(){}



    public ArrayList<AbstractContract> calculateContract(){
        Iterator<AbstractContractsChain> it= AllContracts.iterator();
        applyContracts.clear();
        attack=1.0f;
        defence=1.0f;
        hp=1.0f;
        baseDefence=0;
        cost=0;
        draw=0;
//        ArrayList<AbstractContract> applyContracts=new ArrayList<>();
        while (it.hasNext()){
            AbstractContractsChain chain=it.next();
            if (chain.isapply){
                AbstractContract contract= chain.getContract();
                applyContracts.add(contract);
                //计算最终加成
                attack=contract.calculateAttack(attack);
                defence=contract.calculateDefence(defence);
                hp=contract.calculateHp(hp);
                baseDefence=contract.calculatebaseDefence(baseDefence);
                draw=contract.calculateDraw(draw);
                cost=cost+contract.getCost();
            }
        }
        return applyContracts;
    }

    public void addChain(AbstractContractsChain chain){
        this.AllContracts.add(chain);
    }

    public void setPublicContracts(){
        this.addChain(new Anti_ArmorChain());
        this.addChain(new Anti_ClusteringChain());
        this.addChain(new Anti_MobilityChain());
        this.addChain(new OriginiumZone_ActivationChain());
        this.addChain(new OriginiumZone_StimulusChain());
    }

    //在AllContracts中根据ID寻找chain，并进行升级
    public void upgradeChain(AbstractContractsChain chain){
        for (AbstractContractsChain findchain:AllContracts) {
            if (findchain.id.equals(chain.id)){
                findchain.upgrade();
                this.calculateContract();
                return;
            }
        }
    }

    public String getAttackString(){
        return "攻击力提升至"+this.attack*100+"%";
    }
    public String getDefenceString(){
        return "防御力提升至"+this.defence*100+"%";
    }
    public String getBaseDefenceString(){
        return "防御增加"+this.baseDefence+"点";
    }
    public String getHpString(){
        return "生命提升至"+this.hp*100+"%";
    }
    public String getDrawString(){
        return "额外抽"+this.draw+"张牌";
    }



}
