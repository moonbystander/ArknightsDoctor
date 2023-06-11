package ArknightsDoctorMod.cards.operators.RhineLabOperators;

import ArknightsDoctorMod.actions.GetTrustAction;
import ArknightsDoctorMod.cards.operators.RhineLabOperator;
import ArknightsDoctorMod.helper.DoctorHelper;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Ifrit extends RhineLabOperator {

    public static final String ID = DoctorHelper.MakePath("Ifrit");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    public Ifrit(){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),DESCRIPTION,CardRarity.RARE,DoctorHelper.MakePath("IfritPower"));
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        super.use(abstractPlayer, abstractMonster);
        for (AbstractCard card:abstractPlayer.hand.group) {
            if (card instanceof Saria){
                this.addToBot(new DrawCardAction(abstractPlayer,2));
                continue;
            }
            if (card instanceof Silence){
                this.addToBot(new GainEnergyAction(1));
            }
        }
    }

    @Override
    public void setStartOptions() {

    }
}
