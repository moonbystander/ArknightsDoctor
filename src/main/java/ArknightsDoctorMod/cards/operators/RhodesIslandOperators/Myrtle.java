package ArknightsDoctorMod.cards.operators.RhodesIslandOperators;

import ArknightsDoctorMod.cards.operatorCards.HealingWings;
import ArknightsDoctorMod.cards.operatorCards.Supportbeta;
import ArknightsDoctorMod.cards.operators.RhodesIslandOperator;
import ArknightsDoctorMod.helper.DoctorHelper;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;

public class Myrtle extends RhodesIslandOperator {
    public static final String ID = DoctorHelper.MakePath("Myrtle");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    public Myrtle(){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),DESCRIPTION,CardRarity.UNCOMMON,DoctorHelper.MakePath(
                "MyrtlePower"));
    }


    @Override
    public void setStartOptions() {
        this.addCardToOptions(new Supportbeta(ID));
        this.addCardToOptions(new HealingWings(ID));
    }
}
