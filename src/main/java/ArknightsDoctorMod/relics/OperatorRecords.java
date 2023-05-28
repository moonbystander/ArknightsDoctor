package ArknightsDoctorMod.relics;

import ArknightsDoctorMod.actions.AddMaxHpAction;
import ArknightsDoctorMod.actions.GetTrustAction;
import ArknightsDoctorMod.actions.OperatorsAndSkillActions.RetreatAction;
import ArknightsDoctorMod.actions.RemoveCardFromDeckAction;
import ArknightsDoctorMod.cards.AbstractOperatorsCard;
import ArknightsDoctorMod.cards.operators.Amiya;
import ArknightsDoctorMod.characters.Doctor;
import ArknightsDoctorMod.helper.DoctorHelper;
import ArknightsDoctorMod.powers.LaevatainPower;
import basemod.abstracts.CustomRelic;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.evacipated.cardcrawl.mod.stslib.relics.OnPlayerDeathRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.unique.RegenAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;

import java.util.ArrayList;
import java.util.Iterator;

public class OperatorRecords extends CustomRelic implements OnPlayerDeathRelic {
    public static final String ID = DoctorHelper.MakePath("OperatorRecords");
    private static final String IMG = DoctorHelper.MakeAssetPath("img/relics/test.png");
    // 遗物类型
    private static final RelicTier RELIC_TIER = RelicTier.STARTER;
    // 点击音效
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;
    //信赖度是否到达过200
    public static boolean istrust200=false;
    public static boolean hasAmiya=true;

    public OperatorRecords() {
        super(ID, ImageMaster.loadImage(IMG), RELIC_TIER, LANDING_SOUND);
        setDescriptionAfterLoading(0);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void setDescriptionAfterLoading(int trust) {
        this.description = getUpdatedDescription();
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.tips.add(new PowerTip(this.DESCRIPTIONS[1], ""+trust));
        initializeTips();
    }


    //结算顺序，200->干员->兔兔
    //不到200->兔兔
    @Override
    public boolean onPlayerDeath(AbstractPlayer abstractPlayer, DamageInfo damageInfo) {
        boolean canDead=true;

        //不足200.兔兔挡刀
        if (!istrust200){
            if (hasAmiya){
                boolean findAmiya=false;
                Amiya amiya = null;
                AbstractCard c;
                Iterator<AbstractCard> it=AbstractDungeon.player.masterDeck.group.iterator();
                while (it.hasNext()){
                    c=it.next();
                    if (c instanceof Amiya){
                        amiya= (Amiya) c;
                        findAmiya=true;
                    }
                }
                if (findAmiya){
                    //移除
                    this.addToBot(new RemoveCardFromDeckAction(amiya));
                    //退场
                    amiya.OnRetreat();
                    //恢复所有血量
                    this.addToBot(new HealAction(AbstractDungeon.player,AbstractDungeon.player,AbstractDungeon.player.maxHealth));
                    //清除debuff
                    this.addToBot(new RemoveDebuffsAction(AbstractDungeon.player));
                    canDead=false;
                }else canDead=true;

            }else canDead=true;
        }else {
            Iterator<AbstractCard> it=AbstractDungeon.player.masterDeck.group.iterator();
            ArrayList<AbstractOperatorsCard> operatorsCards=new ArrayList<>();
            AbstractCard c;
            Amiya amiya = null;
            boolean findAmiya=false;
            while (it.hasNext()){
                c=it.next();
                if (c instanceof AbstractOperatorsCard){
                    if (hasAmiya && !findAmiya && c instanceof Amiya){
                        findAmiya=true;
                        amiya= (Amiya) c;
                    }else {
                        operatorsCards.add((AbstractOperatorsCard) c);
                    }
                }
            }
            //若有其他干员牌，随机移除一张非兔兔的干员牌，return false中断死亡结算
            if (operatorsCards.size() > 0){
                //随机挑选
                AbstractOperatorsCard removeC;
                removeC=operatorsCards.get(AbstractDungeon.cardRandomRng.random(operatorsCards.size() - 1));
                //移除
                this.addToBot(new RemoveCardFromDeckAction(removeC));
                //退场
                removeC.OnRetreat();
                canDead=false;
            }
            //如果只有兔兔，移除兔兔并退场，恢复所有血量且清除所有debuff，return false中断死亡结算
            if (canDead && hasAmiya && findAmiya){
                //移除
                this.addToBot(new RemoveCardFromDeckAction(amiya));
                //退场
                amiya.OnRetreat();
                //恢复所有血量
                this.addToBot(new HealAction(AbstractDungeon.player,AbstractDungeon.player,AbstractDungeon.player.maxHealth));
                //清除debuff
                this.addToBot(new RemoveDebuffsAction(AbstractDungeon.player));
                canDead=false;
            }
        }
        return canDead;
    }


    //攻击时根据信赖增加伤害200 +10
    public int onAttackToChangeDamage(DamageInfo info, int damageAmount, AbstractCreature target) {
        int d=damageAmount;
        if (info.owner.isPlayer && !target.isPlayer && ((Doctor)info.owner).trust > 100){
            int result=(int) Math.floor(((((Doctor)info.owner).trust-100)*(((Doctor)info.owner).trust-100))/1000);
            d=d+result;
        }
        return d;
    }

    //被攻击时根据信赖减少伤害200 -10
    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        int d=damageAmount;
        if (!info.owner.isPlayer && ((Doctor)info.owner).trust > 100){
            int result=(int) Math.floor(((((Doctor)info.owner).trust-100)*(((Doctor)info.owner).trust-100))/1000);
            d=d-result;
        }
        return d;
    }

    //胜利时获得5信赖
    @Override
    public void onVictory() {
        this.flash();
        this.addToBot(new GetTrustAction(AbstractDungeon.player,5));
    }
}
