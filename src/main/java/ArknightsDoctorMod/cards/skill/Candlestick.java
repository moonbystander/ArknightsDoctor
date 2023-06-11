package ArknightsDoctorMod.cards.skill;

import ArknightsDoctorMod.cards.AbstractMemoryCard;
import ArknightsDoctorMod.helper.DoctorHelper;
import ArknightsDoctorMod.powers.SecondFireDisasterPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Candlestick extends AbstractMemoryCard {

    public static final String ID = DoctorHelper.MakePath("Candlestick");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 2;

    public Candlestick(){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),COST,DESCRIPTION,CardType.SKILL,CardRarity.UNCOMMON,
                CardTarget.NONE,0);
        this.baseBlock=10;
        this.baseMagicNumber=this.magicNumber=3;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBot(new GainBlockAction(abstractPlayer,abstractPlayer,this.baseBlock));
        this.addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new SecondFireDisasterPower(abstractPlayer,
                this.baseMagicNumber)));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeName();
            this.upgradeBlock(4);
            this.upgradeBaseCost(1);
        }
    }
}
