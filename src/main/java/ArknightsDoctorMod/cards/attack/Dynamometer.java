package ArknightsDoctorMod.cards.attack;

import ArknightsDoctorMod.cards.AbstractMemoryCard;
import ArknightsDoctorMod.helper.DoctorHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class Dynamometer extends AbstractMemoryCard {

    public static final String ID = DoctorHelper.MakePath("Dynamometer");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;

    public Dynamometer(){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),COST,DESCRIPTION,CardType.ATTACK,CardRarity.UNCOMMON,CardTarget.ENEMY);
        this.baseDamage=8;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        super.use(abstractPlayer, abstractMonster);
        this.addToBot(new DamageAction(abstractMonster,
                new DamageInfo(abstractPlayer,
                        this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        this.addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new StrengthPower(abstractPlayer,1)));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeName();
            this.trustamount+=2;
            this.upgradeDamage(4);
        }
    }
}
