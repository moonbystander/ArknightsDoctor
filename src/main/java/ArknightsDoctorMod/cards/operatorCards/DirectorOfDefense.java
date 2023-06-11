package ArknightsDoctorMod.cards.operatorCards;

import ArknightsDoctorMod.cards.AbstractOperatorsExclusiveCard;
import ArknightsDoctorMod.cards.operators.RhineLabOperators.Saria;
import ArknightsDoctorMod.helper.DoctorHelper;
import ArknightsDoctorMod.powers.RhineLabPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DirectorOfDefense extends AbstractOperatorsExclusiveCard {

    public static final String ID = DoctorHelper.MakePath("MedicineDispensing");
    private static final CardStrings cardStrings= CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME=cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static int COST = 4;

    public DirectorOfDefense(){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),COST,DESCRIPTION,CardType.SKILL,CardTarget.ENEMY, Saria.ID);
        this.baseDamage=6;
        this.baseBlock=8;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeName();
            this.upgradeDamage(2);
            this.upgradeBlock(4);
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new RhineLabPower(abstractPlayer,1)));
        this.addToBot(new GainBlockAction(abstractPlayer,abstractPlayer,this.block));
        this.addToBot(new DamageAction(abstractMonster,
                new DamageInfo(abstractPlayer,
                        this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

    }
}
