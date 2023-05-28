package ArknightsDoctorMod.cards.operatorCards;

import ArknightsDoctorMod.cards.AbstractOperatorsExclusiveCard;
import ArknightsDoctorMod.cards.operators.Kaltsit;
import ArknightsDoctorMod.helper.DoctorHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Command_TacticalCoordination extends AbstractOperatorsExclusiveCard {

    public static final String ID = DoctorHelper.MakePath("Command_TacticalCoordination");
    private static final CardStrings cardStrings= CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME=cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static int COST = 3;

    public Command_TacticalCoordination(){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),COST,DESCRIPTION,CardType.ATTACK,CardTarget.ALL_ENEMY, Kaltsit.ID);
        this.baseDamage=6;
        this.isMultiDamage=true;
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
        this.addToBot(new DamageAllEnemiesAction(abstractPlayer,this.multiDamage, DamageInfo.DamageType.NORMAL,
                AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }
}
