package ArknightsDoctorMod.cards.operatorCards;

import ArknightsDoctorMod.cards.AbstractOperatorsExclusiveCard;
import ArknightsDoctorMod.cards.operators.SilverAsh;
import ArknightsDoctorMod.helper.DoctorHelper;
import ArknightsDoctorMod.powers.RulesofSurvivalPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class RulesofSurvival extends AbstractOperatorsExclusiveCard {

    private static final CardStrings cardStrings= CardCrawlGame.languagePack.getCardStrings(DoctorHelper.MakePath("RulesofSurvival"));
    public static final String NAME=cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static int COST = 3;
    public static final String ID = DoctorHelper.MakePath("RulesofSurvival");



    public RulesofSurvival(){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),COST,DESCRIPTION,CardType.POWER,CardTarget.SELF, SilverAsh.ID);
        this.baseBlock=4;
        this.baseMagicNumber=3;
    }


    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeName();
            this.upgradeBlock(3);
            this.upgradeMagicNumber(2);
            this.upgradeBaseCost(2);
        }
    }

    //每回合获得格挡，并恢复生命
    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new RulesofSurvivalPower(abstractPlayer,this.baseBlock,this.baseMagicNumber)));
        super.cardRemoveToOptions(abstractPlayer);
    }
}
