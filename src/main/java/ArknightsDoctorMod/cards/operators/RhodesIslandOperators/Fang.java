package ArknightsDoctorMod.cards.operators.RhodesIslandOperators;

import ArknightsDoctorMod.cards.operatorCards.Chargeα;
import ArknightsDoctorMod.cards.operators.RhodesIslandOperator;
import ArknightsDoctorMod.helper.DoctorHelper;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;

public class Fang extends RhodesIslandOperator {

    public static final String ID = DoctorHelper.MakePath("Fang");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(DoctorHelper.MakePath(ID));
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    public Fang(){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),DESCRIPTION,CardRarity.UNCOMMON,DoctorHelper.MakePath("FangPower"));
    }


    @Override
    public void setStartOptions() {
        this.addCardToOptions(new Chargeα(ID));
    }
}
