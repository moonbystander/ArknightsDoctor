package ArknightsDoctorMod.cards.skill;

import ArknightsDoctorMod.actions.SacrificeStrategyAction;
import ArknightsDoctorMod.cards.AbstractMemoryCard;
import ArknightsDoctorMod.helper.DoctorHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SacrificeStrategy extends AbstractMemoryCard {

    public static final String ID = DoctorHelper.MakePath("SacrificeStrategy");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;

    public SacrificeStrategy(){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),COST,DESCRIPTION,CardType.SKILL,CardRarity.UNCOMMON,
                CardTarget.SELF,-5);
        this.baseMagicNumber=1;
        this.exhaust=true;
    }


    //选择一张手中的干员牌退场，然后减少所有再部署
    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        super.use(abstractPlayer, abstractMonster);
        this.addToBot(new SacrificeStrategyAction(this.magicNumber));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeName();
            this.upgradeBaseCost(0);
            this.selfRetain=true;
            this.rawDescription=cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}
