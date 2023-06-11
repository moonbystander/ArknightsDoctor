package ArknightsDoctorMod.cards.contractCards;

import ArknightsDoctorMod.actions.ContractCardUpgradeAction;
import ArknightsDoctorMod.cards.AbstractContractCard;
import ArknightsDoctorMod.contracts.AbstractContract;
import ArknightsDoctorMod.contracts.AbstractContractsChain;
import ArknightsDoctorMod.contracts.ContractGroup;
import ArknightsDoctorMod.contracts.chain.Blade.Crownslayer_WolfsFangChain;
import ArknightsDoctorMod.contracts.chain.both.Anti_ArmorChain;
import ArknightsDoctorMod.contracts.chain.both.Anti_ClusteringChain;
import ArknightsDoctorMod.contracts.chain.both.Anti_MobilityChain;
import ArknightsDoctorMod.contracts.chain.both.OriginiumZone_ActivationChain;
import ArknightsDoctorMod.contracts.contract.Blade.Crownslayer_WolfsFang;
import ArknightsDoctorMod.helper.DoctorHelper;
import ArknightsDoctorMod.powers.CrownslayerPower;
import ArknightsDoctorMod.powers.InfusedOriginiumSlugPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePower;

import java.util.ArrayList;

public class Crownslayer extends AbstractContractCard {

    public static final String ID = DoctorHelper.MakePath("SarkazLancer");
    private static final CardStrings cardStrings= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final CardStrings contractStrings= CardCrawlGame.languagePack.getCardStrings(DoctorHelper.MakePath("Contract"));
    public static final String NAME=cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static int COST = 2;
    private static int hp=10;
    private static int POWERTURN=10;
    private static int ENERGYTURN=3;
    private static int ENERGYCOUNT=1;

    private int powerTurn=POWERTURN;
    private int energyTurn=ENERGYTURN;
    private int energyCount=ENERGYCOUNT;


    public Crownslayer(){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),COST,
                DESCRIPTION+String.format(cardStrings.EXTENDED_DESCRIPTION[0],3,POWERTURN,ENERGYTURN,ENERGYCOUNT),
                CardType.POWER,CardRarity.RARE,CardTarget.SELF);
        this.baseMagicNumber=this.magicNumber=1;


        this.contractGroup.addChain(new Anti_ArmorChain());
        this.contractGroup.addChain(new Anti_ClusteringChain());
        this.contractGroup.addChain(new Anti_MobilityChain());
        this.contractGroup.addChain(new OriginiumZone_ActivationChain());
        this.contractGroup.addChain(new Crownslayer_WolfsFangChain());

    }

    public Crownslayer(ContractGroup group){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),COST,
                DESCRIPTION+String.format(cardStrings.EXTENDED_DESCRIPTION[0],1,POWERTURN,ENERGYTURN,ENERGYCOUNT),
                CardType.POWER,CardRarity.RARE,CardTarget.SELF);
        this.baseMagicNumber=this.magicNumber=1;
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
        for (AbstractContract contract:applyContracts) {
            if (contract instanceof Crownslayer_WolfsFang){
                //获取词条中的相应数据，并将此卡进行重设
                this.baseMagicNumber=this.magicNumber=((Crownslayer_WolfsFang)contract).magicNumber;
                this.powerTurn=((Crownslayer_WolfsFang)contract).powerTurn;
                this.energyTurn=((Crownslayer_WolfsFang)contract).energyTurn;
                this.energyCount=((Crownslayer_WolfsFang)contract).energyCount;
            }
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
                    Cname+"，"+String.format(cardStrings.EXTENDED_DESCRIPTION[0],this.baseMagicNumber,this.powerTurn,
                            this.energyTurn,this.energyCount);
        }
        initializeDescription();
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        ArrayList<AbstractContract> applyContracts=contractGroup.applyContracts;
        String Cname="";
        for (AbstractContract c:applyContracts) {
            Cname+=" "+c.name;
        }

        if (!Cname.equals("")){
            this.rawDescription=
                    Cname+"，"+String.format(cardStrings.EXTENDED_DESCRIPTION[0],this.baseMagicNumber,this.powerTurn,
                            this.energyTurn,this.energyCount);
        }
        initializeDescription();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        ArrayList<AbstractContract> applyContracts=contractGroup.calculateContract();
        int b= (int) ((this.baseBlock+contractGroup.baseDefence)*contractGroup.defence);
        int losshp= (int) (hp/contractGroup.hp);
        int draw= contractGroup.draw;
        for (AbstractContract contract:applyContracts) {
            if (contract instanceof Crownslayer_WolfsFang){
                //获取词条中的相应数据，并将此卡进行重设
                this.baseMagicNumber=this.magicNumber=((Crownslayer_WolfsFang)contract).magicNumber;
                this.powerTurn=((Crownslayer_WolfsFang)contract).powerTurn;
                this.energyTurn=((Crownslayer_WolfsFang)contract).energyTurn;
                this.energyCount=((Crownslayer_WolfsFang)contract).energyCount;
            }
        }

        if (b>0){
            this.addToBot(new GainBlockAction(abstractPlayer,abstractPlayer,b));
        }
        if ( losshp > 0){
            this.addToBot(new DamageAction(abstractPlayer,new DamageInfo(abstractPlayer,losshp,
                    DamageInfo.DamageType.HP_LOSS)));
        }
        if (draw > 0){
            this.addToBot(new DrawCardAction(abstractPlayer,draw));
        }

        this.addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new IntangiblePower(abstractPlayer,this.baseMagicNumber)));
        this.addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new CrownslayerPower(abstractPlayer,
                powerTurn,energyTurn,energyCount)));

        //将此卡的词条减一
        count++;
        if (count==5){
            count=0;
            this.addToBot(new ContractCardUpgradeAction(this,contractGroup));
        }
    }
}
