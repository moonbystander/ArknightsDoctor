package ArknightsDoctorMod.cards.states;

import ArknightsDoctorMod.actions.SonyasNightmareAction;
import ArknightsDoctorMod.cards.AbstractMemoryCard;
import ArknightsDoctorMod.helper.DoctorHelper;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SonyasNightmare extends AbstractMemoryCard {

    public static final String ID = DoctorHelper.MakePath("Sonya'sNightmare");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = -2;

    public SonyasNightmare(){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),COST,DESCRIPTION,CardType.STATUS,CardRarity.COMMON,
                CardTarget.NONE,0);
        this.baseMagicNumber=this.magicNumber=4;
        this.selfRetain=true;
    }

    @Override
    public void upgrade() {
    }

    @Override
    public void onRetained() {
        this.addToBot(new SonyasNightmareAction(this,this.magicNumber));
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
    }
}
