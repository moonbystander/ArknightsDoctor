package ArknightsDoctorMod.contracts;

import ArknightsDoctorMod.cards.AbstractContractCard;
import com.megacrit.cardcrawl.cards.AbstractCard;

public abstract class AbstractContract {

    public String name;
    //攻击
    public float attack=1;
    //生命
    public float hp=1;
    //防御
    public float defence=1;
    //基础防御
    public int baseDefence=0;
    //移速
    public int cost=0;
    //攻速
    public int drawNum=0;


    public AbstractContract(){
    }


    public AbstractContract(String name, float attack, float hp, float defence, int baseDefence, int cost,
                            int drawNum) {
        this.name = name;
        this.attack = attack;
        this.hp = hp;
        this.defence = defence;
        this.baseDefence = baseDefence;
        this.cost = cost;
        this.drawNum = drawNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAttack() {
        return attack;
    }

    public void setAttack(float attack) {
        this.attack = attack;
    }

    public float getHp() {
        return hp;
    }

    public void setHp(float hp) {
        this.hp = hp;
    }

    public float getDefence() {
        return defence;
    }

    public void setDefence(float defence) {
        this.defence = defence;
    }

    public int getBaseDefence() {
        return baseDefence;
    }

    public void setBaseDefence(int baseDefence) {
        this.baseDefence = baseDefence;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getDrawNum() {
        return drawNum;
    }

    public void setDrawNum(int drawNum) {
        this.drawNum = drawNum;
    }

    public float calculateAttack(float attackAmount){
        return attackAmount*attack;
    }
    public float calculateHp(float hpAmount){
        return hpAmount*hp;
    }
    public float calculateDefence(float defenceAmount){
        return defenceAmount*defence;
    }
    public int calculatebaseDefence(int baseBlock){
        return this.baseDefence+baseBlock;
    }
    public int calculateDraw(int draw){
        return draw+this.drawNum;
    }

    //带有此词条的卡打出时需要应用的power
    //需要传入最终伤害、最终防御等
    public void getPowerOnUse(AbstractContractCard card,int damage,int block){};
    public void addSpecialAction(AbstractContractCard card){}

    //其他花里胡哨的东西，留个空接口给子类自己折腾去
    public void changeThisCard(AbstractCard card){}
}
