package ArknightsDoctorMod.cards.skill;

import ArknightsDoctorMod.actions.RemoveCardFromDeckAction;
import ArknightsDoctorMod.cards.AbstractMemoryCard;
import ArknightsDoctorMod.cards.states.BountyHunter;
import ArknightsDoctorMod.helper.DoctorHelper;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class KnightTreasure extends AbstractMemoryCard {


    public static final String ID = DoctorHelper.MakePath("KnightTreasure");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 0;

    public KnightTreasure(){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),COST,DESCRIPTION,CardType.SKILL,CardRarity.RARE,CardTarget.NONE);
        this.exhaust=true;

    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        super.use(abstractPlayer, abstractMonster);
        this.addToBot(new MakeTempCardInDiscardAction(new Treasure(),1));
        this.addToBot(new MakeTempCardInDrawPileAction(new TreasureKey(),1,true,false,false));
        this.addToBot(new RemoveCardFromDeckAction(this));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeName();
            this.trustamount=5;
        }
    }
}
