package ArknightsDoctorMod.modifiers;

import ArknightsDoctorMod.helper.DoctorHelper;
import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;

public class LaevatainDamage extends AbstractDamageModifier {

    public static final String ID = DoctorHelper.MakePath("LeechDamage");

    public LaevatainDamage(){
    }



    //对所有敌人造成原始伤害330%的伤害
    //问题：群体伤害动作是通过生成多次单体伤害实现的，直接在此函数中生成群体伤害，会导致每个原始单体伤害都会触发一次此动作
    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        //目标为player则不生效
        if (target.isPlayer){
            return;
        }
        int d= (int) (info.base*3.3);
        this.addToBot(new DamageAllEnemiesAction((AbstractPlayer) info.owner,d, info.type, AbstractGameAction.AttackEffect.NONE));
    }

    //攻击时，改变原始伤害信息，返回值为原始伤害信息最终造成的伤害
    //使原伤害无效
    //目标被人物的伤害不修改
    public int onAttackToChangeDamage(DamageInfo info, int damageAmount, AbstractCreature target) {
        return target.isPlayer?damageAmount:0;
    }

    @Override
    public AbstractDamageModifier makeCopy() {
        return new LaevatainDamage();
    }

    public boolean isInherent() {
        return true;
    }
}
