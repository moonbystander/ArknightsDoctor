package ArknightsDoctorMod.cards.operatorCards;

import ArknightsDoctorMod.cards.AbstractOperatorsExclusiveCard;
import ArknightsDoctorMod.cards.operators.RhineLabOperators.Saria;
import ArknightsDoctorMod.helper.DoctorHelper;
import ArknightsDoctorMod.powers.RhineChargedSuitPower;
import ArknightsDoctorMod.powers.RhineLabPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class RhineChargedSuit extends AbstractOperatorsExclusiveCard {

    public static final String ID = DoctorHelper.MakePath("MedicineDispensing");
    private static final CardStrings cardStrings= CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME=cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static int COST = 3;


    public RhineChargedSuit(){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),COST,DESCRIPTION,CardType.SKILL,CardTarget.SELF, Saria.ID);
    }


    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeName();
            this.upgradeBaseCost(2);
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new RhineChargedSuitPower(abstractPlayer)));
        super.cardRemoveToOptions(abstractPlayer);
    }
}
