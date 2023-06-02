package ArknightsDoctorMod.cards.contractCards;


import ArknightsDoctorMod.actions.ContractCardUpgradeAction;
import ArknightsDoctorMod.cards.AbstractContractCard;
import ArknightsDoctorMod.contracts.AbstractContract;
import ArknightsDoctorMod.contracts.AbstractContractsChain;
import ArknightsDoctorMod.contracts.ContractGroup;
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
    public static final String NAME=cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static int COST = 1;
    private static int hp=5;
    private static int count=0;

    public InfusedOriginiumSlug(){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),COST,DESCRIPTION,CardType.SKILL,CardRarity.COMMON,CardTarget.SELF);
        this.baseDamage=5;
        this.baseBlock=5;
        this.contractGroup.setPublicContracts();
    }

    public InfusedOriginiumSlug(ContractGroup group){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),COST,DESCRIPTION,CardType.SKILL,CardRarity.COMMON,CardTarget.SELF);
        this.baseDamage=5;
        this.baseBlock=5;
        this.contractGroup=group;
    }

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


//        this.rawDescription = "修改后的文本";
//        this.initializeDescription();
        String EString = "";
        if (this.contractGroup.attack!=1f){
            EString+=contractGroup.getAttackString();
        }
        if (this.contractGroup.baseDefence!=0){
            EString+=contractGroup.getBaseDefenceString();
        }
        if (this.contractGroup.defence!=1f){
            EString+=contractGroup.getDefenceString();
        }
        if (this.contractGroup.hp!=1f){
            EString+=contractGroup.getHpString();
        }
        if (this.contractGroup.draw!=0){
            EString+=contractGroup.getDrawString();
        }
        this.rawDescription=DESCRIPTION+EString;
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
                    new InfusedOriginiumSlugPower(abstractPlayer,damage)));
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
