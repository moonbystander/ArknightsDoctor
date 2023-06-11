package ArknightsDoctorMod.cards.contractCards;

import ArknightsDoctorMod.actions.ContractCardUpgradeAction;
import ArknightsDoctorMod.cards.AbstractContractCard;
import ArknightsDoctorMod.contracts.AbstractContract;
import ArknightsDoctorMod.contracts.AbstractContractsChain;
import ArknightsDoctorMod.contracts.ContractGroup;
import ArknightsDoctorMod.contracts.chain.both.Anti_ArmorChain;
import ArknightsDoctorMod.contracts.chain.both.Anti_ClusteringChain;
import ArknightsDoctorMod.contracts.chain.both.Anti_MobilityChain;
import ArknightsDoctorMod.contracts.chain.both.OriginiumZone_ActivationChain;
import ArknightsDoctorMod.helper.DoctorHelper;
import ArknightsDoctorMod.powers.InfusedOriginiumSlugPower;
import ArknightsDoctorMod.powers.MudrockZealotPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class MudrockZealot extends AbstractContractCard {

    public static final String ID = DoctorHelper.MakePath("MudrockZealot");
    private static final CardStrings cardStrings= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final CardStrings contractStrings= CardCrawlGame.languagePack.getCardStrings(DoctorHelper.MakePath("Contract"));
    public static final String NAME=cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static int COST = 2;
    private static int hp=10;

    public MudrockZealot(){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),COST,
                DESCRIPTION+String.format(cardStrings.EXTENDED_DESCRIPTION[0],10,1),CardType.SKILL,
                CardRarity.COMMON,CardTarget.SELF);
        this.baseBlock=10;
        this.baseDraw=1;
        this.count=0;
        this.contractGroup.addChain(new Anti_ArmorChain());
        this.contractGroup.addChain(new Anti_ClusteringChain());
        this.contractGroup.addChain(new Anti_MobilityChain());
        this.contractGroup.addChain(new OriginiumZone_ActivationChain());

    }


    public MudrockZealot(ContractGroup group){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),COST,
                DESCRIPTION+String.format(cardStrings.EXTENDED_DESCRIPTION[0],10,1),CardType.SKILL,CardRarity.COMMON,
                CardTarget.SELF);
        this.baseBlock=10;
        this.baseDraw=1;
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
        if (this.cost > 0){
            int newCost=COST-contractGroup.cost;
            this.upgradeBaseCost(Math.max(newCost, 0));
        }
        //更新描述
        //更好的方法应该是将词条以关键字的形式添加到描述信息里
        int b= (int) ((this.baseBlock+contractGroup.baseDefence)*contractGroup.defence);
        int losshp= (int) (hp/contractGroup.hp);

        String Cname="";
        for (AbstractContract c:applyContracts) {
            Cname+=" "+c.name;
        }
        if (!Cname.equals("")){
            this.rawDescription=
                    Cname+"，"+String.format(cardStrings.EXTENDED_DESCRIPTION[0],b,this.baseDraw);
        }
        initializeDescription();
    }

    @Override
    public void applyPowers() {
        int b=(int) ((this.baseBlock+contractGroup.baseDefence)*contractGroup.defence);
        super.applyPowers();
        ArrayList<AbstractContract> applyContracts=contractGroup.applyContracts;
        String Cname="";
        for (AbstractContract c:applyContracts) {
            Cname+=" "+c.name;
        }

        if (!Cname.equals("")){
            this.rawDescription=
                    Cname+"，"+String.format(cardStrings.EXTENDED_DESCRIPTION[0],b,this.baseDraw);
        }
        initializeDescription();
    }


    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        contractGroup.calculateContract();
        int b= (int) ((this.baseBlock+contractGroup.baseDefence)*contractGroup.defence);
        int losshp= (int) (hp/contractGroup.hp);
        int contractdraw= contractGroup.draw;

        this.addToBot(new GainBlockAction(abstractPlayer,abstractPlayer,b));
        if ( losshp > 0){
            this.addToBot(new DamageAction(abstractPlayer,new DamageInfo(abstractPlayer,losshp,
                    DamageInfo.DamageType.HP_LOSS)));
        }
        if (contractdraw > 0){
            this.addToBot(new DrawCardAction(abstractPlayer,contractdraw));
        }

        //为自己增加power
        this.addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new MudrockZealotPower(abstractPlayer,baseDraw)));

        //将此卡的词条减一
        count++;
        if (count==5){
            count=0;
            this.addToBot(new ContractCardUpgradeAction(this,contractGroup));
        }
    }
}
