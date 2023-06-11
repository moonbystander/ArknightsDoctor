package ArknightsDoctorMod.cards.skill;

import ArknightsDoctorMod.actions.FindTreasureAction;
import ArknightsDoctorMod.cards.AbstractMemoryCard;
import ArknightsDoctorMod.helper.DoctorHelper;
import ArknightsDoctorMod.powers.KnightTreasurePower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class TreasureKey extends AbstractMemoryCard {

    public static final String ID = DoctorHelper.MakePath("TreasureKey");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 2;

    public TreasureKey(){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),COST,DESCRIPTION,CardType.SKILL,CardRarity.SPECIAL,CardTarget.NONE);
        this.exhaust=true;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBot(new FindTreasureAction(abstractPlayer,this));
    }

    @Override
    public void upgrade() {

    }
}
