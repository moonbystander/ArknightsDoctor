package ArknightsDoctorMod.cards.operatorCards;

import ArknightsDoctorMod.cards.AbstractOperatorsExclusiveCard;
import ArknightsDoctorMod.cards.operators.RhineLabOperators.Saria;
import ArknightsDoctorMod.helper.DoctorHelper;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FirstAid extends AbstractOperatorsExclusiveCard {

    public static final String ID = DoctorHelper.MakePath("FirstAid");
    private static final CardStrings cardStrings= CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME=cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static int COST = 8;

    public FirstAid(){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),COST,DESCRIPTION,CardType.SKILL,CardTarget.SELF, Saria.ID);
        this.baseDamage=6;
    }

    @Override
    public boolean cardPlayable(AbstractMonster m) {
        return super.cardPlayable(m) && AbstractDungeon.player.currentHealth < (AbstractDungeon.player.maxHealth/2);
    }



    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeName();
            this.upgradeDamage(4);
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBot(new HealAction(abstractPlayer,abstractPlayer,this.damage));
    }
}
