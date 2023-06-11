package ArknightsDoctorMod.cards.skill;

import ArknightsDoctorMod.actions.RemoveCardFromDeckAction;
import ArknightsDoctorMod.cards.AbstractMemoryCard;
import ArknightsDoctorMod.helper.DoctorHelper;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.watcher.SkipEnemiesTurnAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Pignus extends AbstractMemoryCard {

    public static final String ID = DoctorHelper.MakePath("Pignus");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 2;

    public Pignus(){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),COST,DESCRIPTION,CardType.SKILL,CardRarity.UNCOMMON,
                CardTarget.SELF,0);

    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeName();
            this.upgradeBaseCost(1);
            this.selfRetain=true;
            this.rawDescription=cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        super.use(abstractPlayer, abstractMonster);
        this.addToBot(new SkipEnemiesTurnAction());
        this.addToBot(new DamageAction(abstractPlayer,new DamageInfo(abstractPlayer,abstractPlayer.currentHealth-1,
                DamageInfo.DamageType.HP_LOSS)));
        this.addToBot(new RemoveCardFromDeckAction(this));
    }
}
