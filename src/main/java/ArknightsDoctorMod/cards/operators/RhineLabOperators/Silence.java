package ArknightsDoctorMod.cards.operators.RhineLabOperators;

import ArknightsDoctorMod.actions.GetTrustAction;
import ArknightsDoctorMod.cards.operatorCards.MedicalDrone;
import ArknightsDoctorMod.cards.operatorCards.RhineTech;
import ArknightsDoctorMod.cards.operators.RhineLabOperator;
import ArknightsDoctorMod.helper.DoctorHelper;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BarricadePower;

public class Silence extends RhineLabOperator {
    public static final String ID = DoctorHelper.MakePath("Silence");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    public Silence(){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),DESCRIPTION,CardRarity.UNCOMMON,DoctorHelper.MakePath("SilencePower"));
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        super.use(abstractPlayer, abstractMonster);
        for (AbstractCard card:abstractPlayer.hand.group) {
            if (card instanceof Saria){
                this.addToBot(new HealAction(abstractPlayer,abstractPlayer,3));
                this.addToBot(new GetTrustAction(abstractPlayer,1));
                continue;
            }
            if (card instanceof Ifrit){
                this.addToBot(new GainBlockAction(abstractPlayer,abstractPlayer,4));
            }
        }
    }

    @Override
    public void setStartOptions() {
        this.addCardToOptions(new RhineTech(ID));
        this.addCardToOptions(new MedicalDrone());
    }
}
