package ArknightsDoctorMod.cards.operators.RhodesIslandOperators;

import ArknightsDoctorMod.cards.operatorCards.Double_Tap_Auto;
import ArknightsDoctorMod.cards.operatorCards.Targeting_Primary;
import ArknightsDoctorMod.cards.operators.RhodesIslandOperator;
import ArknightsDoctorMod.helper.DoctorHelper;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;

public class Kroos extends RhodesIslandOperator {
    public static final String ID = DoctorHelper.MakePath("Kroos");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(DoctorHelper.MakePath(ID));
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;


    public Kroos(){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),DESCRIPTION,CardRarity.UNCOMMON,DoctorHelper.MakePath("Kroos"));
    }


    @Override
    public void setStartOptions() {
        this.addCardToOptions(new Double_Tap_Auto(ID));
        this.addCardToOptions(new Targeting_Primary(ID));
    }
}
