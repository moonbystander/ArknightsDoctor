package ArknightsDoctorMod.contracts;

import com.megacrit.cardcrawl.cards.AbstractCard;

public abstract class AbstractContract {
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

    public float calculateAttack(float attackAmount){
        return attackAmount*attack;
    }
    public float calculateHp(float hpAmount){
        return hpAmount*attack;
    }
    public float calculateDefence(float defenceAmount){
        return defenceAmount*defence;
    }
    public int calculatebaseDefence(int baseBlock){
        return this.baseDefence+baseBlock;
    }
    public int calculateCost(int baseCost){
        if (baseCost<0){
            return baseCost;
        }
        return Math.max(baseCost - this.cost, 0);
    }
    public int calculateDraw(int draw){
        return draw+this.drawNum;
    }

    //带有此词条的卡打出时需要应用的power
    public void getPowerOnUse(){};

    //其他花里胡哨的东西，留个空接口给子类自己折腾去
    public void addToCard(AbstractCard card){}
    public void removeFromCard(AbstractCard card){}
}
