package ArknightsDoctorMod.cards.attack;

import ArknightsDoctorMod.actions.OperatorsAndSkillActions.FindOperatorsCardToDoAction;
import ArknightsDoctorMod.actions.ThrowingStonesAction;
import ArknightsDoctorMod.cards.AbstractMemoryCard;
import ArknightsDoctorMod.cards.operators.RhodesIslandOperators.Amiya;
import ArknightsDoctorMod.helper.DoctorHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ThrowingStones extends AbstractMemoryCard {

    public static final String ID = DoctorHelper.MakePath("ThrowingStones");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;

    public ThrowingStones(){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),COST,DESCRIPTION,CardType.ATTACK,CardRarity.UNCOMMON,
                CardTarget.ENEMY,3);
        this.baseDamage=1;
        this.exhaust=true;
    }


    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        super.use(abstractPlayer, abstractMonster);
        this.addToBot(new DamageAction(abstractMonster,
                new DamageInfo(abstractPlayer,
                        this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        this.addToBot(new ThrowingStonesAction(abstractPlayer));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeName();
            this.upgradeBaseCost(0);
        }
    }
}
