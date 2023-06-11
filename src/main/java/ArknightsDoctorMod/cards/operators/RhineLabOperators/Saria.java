package ArknightsDoctorMod.cards.operators.RhineLabOperators;

import ArknightsDoctorMod.actions.GetTrustAction;
import ArknightsDoctorMod.cards.operatorCards.DirectorOfDefense;
import ArknightsDoctorMod.cards.operatorCards.FirstAid;
import ArknightsDoctorMod.cards.operatorCards.RhineChargedSuit;
import ArknightsDoctorMod.cards.operators.RhineLabOperator;
import ArknightsDoctorMod.helper.DoctorHelper;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.MachineLearning;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BarricadePower;

public class Saria extends RhineLabOperator {

    public static final String ID = DoctorHelper.MakePath("Saria");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    public Saria(){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),DESCRIPTION,CardRarity.UNCOMMON,DoctorHelper.MakePath("SariaPower"));
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        super.use(abstractPlayer, abstractMonster);
        for (AbstractCard card:abstractPlayer.hand.group) {
            if (card instanceof Silence){
                this.addToBot(new GainBlockAction(abstractPlayer,abstractPlayer,4));
                this.addToBot(new GetTrustAction(abstractPlayer,1));
                continue;
            }
            if (card instanceof Ifrit){
                this.addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new BarricadePower(abstractPlayer)));
            }
        }
    }

    @Override
    public void setStartOptions() {
        this.addCardToOptions(new FirstAid());
        this.addCardToOptions(new MachineLearning());
        this.addCardToOptions(new RhineChargedSuit());
        this.addCardToOptions(new DirectorOfDefense());
    }
}
