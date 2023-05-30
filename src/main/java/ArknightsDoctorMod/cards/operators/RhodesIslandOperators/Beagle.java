package ArknightsDoctorMod.cards.operators.RhodesIslandOperators;

import ArknightsDoctorMod.cards.operatorCards.DEFUp;
import ArknightsDoctorMod.cards.operatorCards.DEFUpα;
import ArknightsDoctorMod.cards.operators.RhodesIslandOperator;
import ArknightsDoctorMod.helper.DoctorHelper;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;

public class Beagle extends RhodesIslandOperator {

    public static final String ID = DoctorHelper.MakePath("Beagle");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(DoctorHelper.MakePath(ID));
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    public Beagle(){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),DESCRIPTION,CardRarity.COMMON,DoctorHelper.MakePath(
                "KaltsitPower"));
    }

    @Override
    public void setStartOptions() {
        this.addCardToOptions(new DEFUpα(ID));
        this.addCardToOptions(new DEFUp(ID));
    }
}
