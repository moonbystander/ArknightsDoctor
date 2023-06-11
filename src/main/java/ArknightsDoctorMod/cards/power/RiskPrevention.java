package ArknightsDoctorMod.cards.power;

import ArknightsDoctorMod.cards.AbstractMemoryCard;
import ArknightsDoctorMod.helper.DoctorHelper;
import ArknightsDoctorMod.powers.RiskPreventionPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class RiskPrevention extends AbstractMemoryCard {

    public static final String ID = DoctorHelper.MakePath("RiskPrevention");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;

    public RiskPrevention(){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),COST,DESCRIPTION,CardType.POWER,CardRarity.UNCOMMON,CardTarget.SELF);
        this.baseBlock=3;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        super.use(abstractPlayer, abstractMonster);
        this.addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new RiskPreventionPower(abstractPlayer,
                this.baseBlock)));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeName();
            this.upgradeBlock(2);
        }
    }
}
