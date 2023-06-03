package ArknightsDoctorMod.cards.contractCards;


import ArknightsDoctorMod.actions.ContractCardUpgradeAction;
import ArknightsDoctorMod.cards.AbstractContractCard;
import ArknightsDoctorMod.contracts.AbstractContract;
import ArknightsDoctorMod.contracts.AbstractContractsChain;
import ArknightsDoctorMod.contracts.ContractGroup;
import ArknightsDoctorMod.contracts.chain.Barrenland.OriginiumZone_SlugCardiacFusionChain;
import ArknightsDoctorMod.helper.DoctorHelper;
import ArknightsDoctorMod.powers.InfusedOriginiumSlugPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class InfusedOriginiumSlug extends AbstractContractCard {
//    public static ContractGroup contractGroup=new ContractGroup();
//    //设置词条
//    static {
//        //添加公共词条
//        contractGroup.setPublicContracts();
//    }

    public static final String ID = DoctorHelper.MakePath("InfusedOriginiumSlug");
    private static final CardStrings cardStrings= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final CardStrings contractStrings= CardCrawlGame.languagePack.getCardStrings(DoctorHelper.MakePath("Contract"));
    public static final String NAME=cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static int COST = 1;
    private static int hp=5;
    private static int count=0;

    public InfusedOriginiumSlug(){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),COST,DESCRIPTION+String.format(cardStrings.EXTENDED_DESCRIPTION[0],
                5,5),CardType.SKILL,
                CardRarity.COMMON,
                CardTarget.SELF);
        this.baseDamage=5;
        this.baseBlock=5;
        this.contractGroup.setPublicContracts();
        this.contractGroup.addChain(new OriginiumZone_SlugCardiacFusionChain());
    }

    public InfusedOriginiumSlug(ContractGroup group){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),COST,
                DESCRIPTION+String.format(cardStrings.EXTENDED_DESCRIPTION[0],5,5),CardType.SKILL,CardRarity.COMMON,
                CardTarget.SELF);
        this.baseDamage=5;
        this.baseBlock=5;
        //将group中的AllContracts里的元素拷贝到constractGroup中
        for (AbstractContractsChain copychain:group.AllContracts) {
            this.contractGroup.addChain(copychain.makeThisCopy());
        }
        this.contractGroup.calculateContract();

    }

    //妈的合着奖励卡也是用的这个方法,改！
    //目标：将contractGroup进行深拷贝，将AllContracts中的chain全部new一份出来，别共用
    @Override
    public AbstractCard makeCopy() {
        return new InfusedOriginiumSlug(this.contractGroup);
    }

    //进行词条强化
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
        int d= (int) (this.baseDamage*contractGroup.attack);
        int b= (int) ((this.baseBlock+contractGroup.baseDefence)*contractGroup.defence);
        int losshp= (int) (hp/contractGroup.hp);
        int draw= contractGroup.draw;

        String Cname="";
        for (AbstractContract c:applyContracts) {
            Cname+=" "+c.name;
        }
        if (!Cname.equals("")){
            this.rawDescription=
                    Cname+"，"+String.format(cardStrings.EXTENDED_DESCRIPTION[0],b,d);
        }
        initializeDescription();

    }

    @Override
    public void applyPowers() {
        int d=(int) (this.baseDamage*contractGroup.attack);
        int b=(int) ((this.baseBlock+contractGroup.baseDefence)*contractGroup.defence);
        super.applyPowers();
        ArrayList<AbstractContract> applyContracts=contractGroup.applyContracts;
        String Cname="";
        for (AbstractContract c:applyContracts) {
            Cname+=" "+c.name;
        }

        if (!Cname.equals("")){
            this.rawDescription=
                    Cname+"，"+String.format(cardStrings.EXTENDED_DESCRIPTION[0],b,d);
        }
        initializeDescription();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        contractGroup.calculateContract();
        int d= (int) (this.baseDamage*contractGroup.attack);
        int b= (int) ((this.baseBlock+contractGroup.baseDefence)*contractGroup.defence);
        int losshp= (int) (hp/contractGroup.hp);
        int draw= contractGroup.draw;

        if ( d >0 ){
            this.addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,
                    new InfusedOriginiumSlugPower(abstractPlayer,d)));
        }
        this.addToBot(new GainBlockAction(abstractPlayer,abstractPlayer,b));
        if ( losshp > 0){
            this.addToBot(new DamageAction(abstractPlayer,new DamageInfo(abstractPlayer,losshp,
                    DamageInfo.DamageType.HP_LOSS)));
        }
        if (draw > 0){
            this.addToBot(new DrawCardAction(abstractPlayer,draw));
        }

        //将此卡的词条减一
        count++;
        if (count==5){
            count=0;
            this.addToBot(new ContractCardUpgradeAction(this,contractGroup));
        }
    }


}
