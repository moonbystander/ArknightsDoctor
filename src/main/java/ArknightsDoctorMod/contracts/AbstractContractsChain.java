package ArknightsDoctorMod.contracts;

import ArknightsDoctorMod.cards.AbstractContractCard;

import java.util.ArrayList;

public abstract class AbstractContractsChain {
    public boolean isapply=false;
    public int times=0;
    //对不同的合约卡，contracts这个数组完全是可以共用的，可以节约内存开销
    public ArrayList<AbstractContract> contracts=new ArrayList<>();
    public String id;

    public AbstractContractsChain(){

    }

    public AbstractContractsChain(String id) {
        this.id = id;
    }

    public AbstractContractsChain(String id, int times, ArrayList<AbstractContract> contracts){
        this.id=id;
        this.times=times;
        this.contracts.addAll(contracts);
        if (this.contracts.size()!=0){
            if (this.times>this.contracts.size()){
                this.times=0;
            }
        }
    }

    public abstract void setStartContracts();
    public void addContract(AbstractContract contract){
        this.contracts.add(contract);
    }

    //返回可否升级，一般只要词条没满级就返回true
    public boolean canUpgrade(AbstractContractCard card){
        return this.times < this.contracts.size();
    }

    //更新词条等级并将应用状态设置为已应用
    public void upgrade(){
        this.times++;
        if (!this.isapply){
            this.isapply=true;
        }
    }

    //返回是否应用词条
    public boolean isIsapply(){
        return this.isapply;
    }

    public void setIsapply(boolean isapply){
        this.isapply=isapply;
    }

    //返回当前词条
    public AbstractContract getContract(){

        return this.contracts.get(this.times-1);
    }


    public AbstractContractsChain makeThisCopy(){
        AbstractContractsChain chain=null;
        try {
            chain=this.getClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        if (chain!=null){
            chain.isapply=this.isapply;
            chain.times=this.times;
        }
        return chain;

    }



}
