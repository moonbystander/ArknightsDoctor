package ArknightsDoctorMod.cards.power;

import ArknightsDoctorMod.cards.AbstractMemoryCard;
import ArknightsDoctorMod.helper.DoctorHelper;
import ArknightsDoctorMod.powers.ActiveOriginiumPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class ActiveOriginium extends AbstractMemoryCard {

    public static final String ID = DoctorHelper.MakePath("ActiveOriginium");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;

    public ActiveOriginium(){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),COST,DESCRIPTION,CardType.POWER,CardRarity.UNCOMMON,
                CardTarget.SELF,0);
        this.baseMagicNumber=this.magicNumber=3;
    }


    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeName();
            this.upgradeMagicNumber(2);
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new StrengthPower(abstractPlayer,
                this.baseMagicNumber)));
        this.addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new ActiveOriginiumPower(abstractPlayer)));
    }
}
