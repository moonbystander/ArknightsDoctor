package ArknightsDoctorMod.cards.operatorCards;

import ArknightsDoctorMod.cards.AbstractOperatorsSkillCard;
import ArknightsDoctorMod.cards.operators.SilverAsh;
import ArknightsDoctorMod.helper.DoctorHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class RulesofSurvival extends AbstractOperatorsSkillCard {

    private static final CardStrings cardStrings= CardCrawlGame.languagePack.getCardStrings(DoctorHelper.MakePath("RulesofSurvival"));
    public static final String NAME=cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static int COST = 2;
    public static final String ID = DoctorHelper.MakePath("RulesofSurvival");



    public RulesofSurvival(){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),COST,DESCRIPTION,CardType.POWER,CardTarget.SELF, SilverAsh.ID);
        this.baseBlock=2;
        this.baseMagicNumber=2;
    }


    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeName();
            this.upgradeBlock(3);
            this.upgradeMagicNumber(3);
            this.updateCost(1);
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

        super.cardRemoveToOptions(abstractPlayer);
    }
}
