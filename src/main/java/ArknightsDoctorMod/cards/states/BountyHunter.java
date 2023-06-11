package ArknightsDoctorMod.cards.states;

import ArknightsDoctorMod.cards.AbstractMemoryCard;
import ArknightsDoctorMod.helper.DoctorHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BountyHunter extends AbstractMemoryCard {

    public static final String ID = DoctorHelper.MakePath("BountyHunter");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = -2;

    public BountyHunter(){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),COST,DESCRIPTION,CardType.STATUS,CardRarity.COMMON,
                CardTarget.NONE,0);
        this.isEthereal=true;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster){
    }

    @Override
    public void triggerOnExhaust(){
        this.addToBot(new DamageAction(AbstractDungeon.player, new DamageInfo(AbstractDungeon.player,
                3, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
    }

    @Override
    public void upgrade() {

    }
}
