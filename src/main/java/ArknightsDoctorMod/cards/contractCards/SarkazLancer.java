package ArknightsDoctorMod.cards.contractCards;

import ArknightsDoctorMod.actions.ContractCardUpgradeAction;
import ArknightsDoctorMod.cards.AbstractContractCard;
import ArknightsDoctorMod.contracts.AbstractContract;
import ArknightsDoctorMod.contracts.AbstractContractsChain;
import ArknightsDoctorMod.contracts.ContractGroup;
import ArknightsDoctorMod.contracts.chain.Cinder.Objective_DauntlessChargeChain;
import ArknightsDoctorMod.contracts.chain.both.*;
import ArknightsDoctorMod.contracts.contract.Cinder.Objective_DauntlessCharge;
import ArknightsDoctorMod.helper.DoctorHelper;
import ArknightsDoctorMod.powers.InfusedOriginiumSlugPower;
import ArknightsDoctorMod.powers.MudrockZealotPower;
import ArknightsDoctorMod.powers.SarkazLancerPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import java.util.ArrayList;

public class SarkazLancer extends AbstractContractCard {

    public static final String ID = DoctorHelper.MakePath("SarkazLancer");
    private static final CardStrings cardStrings= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final CardStrings contractStrings= CardCrawlGame.languagePack.getCardStrings(DoctorHelper.MakePath("Contract"));
    public static final String NAME=cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static int COST = -1;
    private static int hp=10;
    private static int a=3;

    public SarkazLancer(){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),COST,
                DESCRIPTION+String.format(cardStrings.EXTENDED_DESCRIPTION[0],3,6),CardType.SKILL,
                CardRarity.UNCOMMON,CardTarget.ENEMY);
        this.baseDamage=6;
        this.baseMagicNumber=a;
        this.count=0;
        this.contractGroup.addChain(new Anti_ArmorChain());
        this.contractGroup.addChain(new Anti_ClusteringChain());
        this.contractGroup.addChain(new OriginiumZone_ActivationChain());
        this.contractGroup.addChain(new OriginiumZone_StimulusChain());
        this.contractGroup.addChain(new Objective_DauntlessChargeChain());

    }

    public SarkazLancer(ContractGroup group){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),COST,
                DESCRIPTION+String.format(cardStrings.EXTENDED_DESCRIPTION[0],3,6),CardType.SKILL,CardRarity.COMMON,
                CardTarget.SELF);
        this.baseDamage=6;
        this.baseMagicNumber=a;
        //将group中的AllContracts里的元素拷贝到constractGroup中
        for (AbstractContractsChain copychain:group.AllContracts) {
            this.contractGroup.addChain(copychain.makeThisCopy());
        }
        this.contractGroup.calculateContract();
    }


    @Override
    public void upgrade() {
        //将升级次数设置为1，使进入战斗时复制卡牌后，将原始卡牌调用一次upgrade函数
        this.timesUpgraded=1;
        ArrayList<AbstractContract> applyContracts=contractGroup.calculateContract();
        for (AbstractContract contract:applyContracts) {
            //重设每点能量转换的加速回合数
            if (contract instanceof Objective_DauntlessCharge){
                this.baseMagicNumber= (int) (a/((Objective_DauntlessCharge)contract).a);
                break;
            }
        }
        //更新描述
        //更好的方法应该是将词条以关键字的形式添加到描述信息里
        int d= (int) (this.baseDamage*contractGroup.attack);
        int losshp= (int) (hp/contractGroup.hp);
        int draw= contractGroup.draw;

        String Cname="";
        for (AbstractContract c:applyContracts) {
            Cname+=" "+c.name;
        }
        if (!Cname.equals("")){
            this.rawDescription=
                    Cname+"，"+String.format(cardStrings.EXTENDED_DESCRIPTION[0],this.baseMagicNumber,d);
        }
        initializeDescription();
    }

    @Override
    public void applyPowers() {
        int d=(int) (this.baseDamage*contractGroup.attack);
        super.applyPowers();
        ArrayList<AbstractContract> applyContracts=contractGroup.applyContracts;
        String Cname="";
        for (AbstractContract c:applyContracts) {
            Cname+=" "+c.name;
        }

        if (!Cname.equals("")){
            this.rawDescription=
                    Cname+"，"+String.format(cardStrings.EXTENDED_DESCRIPTION[0],this.baseMagicNumber,d);
        }
        initializeDescription();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        contractGroup.calculateContract();
        //当 无畏冲锋 将damage进行修改，该如何计算？

        int d= (int) (this.baseDamage*contractGroup.attack);
        int b= (int) ((this.baseBlock+contractGroup.baseDefence)*contractGroup.defence);
        int losshp= (int) (hp/contractGroup.hp);
        int contractdraw= contractGroup.draw;

        if (b>0){
            this.addToBot(new GainBlockAction(abstractPlayer,abstractPlayer,b));
        }
        if ( losshp > 0){
            this.addToBot(new DamageAction(abstractPlayer,new DamageInfo(abstractPlayer,losshp,
                    DamageInfo.DamageType.HP_LOSS)));
        }
        if (contractdraw > 0){
            this.addToBot(new DrawCardAction(abstractPlayer,contractdraw));
        }

        //为敌人增加穿刺power:amount回合数等于3x，伤害等于damage*3x

        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1) {
            effect = this.energyOnUse;
        }

        this.addToBot(new ApplyPowerAction(abstractMonster,abstractPlayer,new SarkazLancerPower(abstractMonster,
                effect*this.baseMagicNumber,
                d*effect*this.baseMagicNumber)));
        abstractPlayer.energy.use(EnergyPanel.totalCount);

        //将此卡的词条减一
        count++;
        if (count==5){
            count=0;
            this.addToBot(new ContractCardUpgradeAction(this,contractGroup));
        }
    }
}
