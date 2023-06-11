package ArknightsDoctorMod.cards.operatorCards;

import ArknightsDoctorMod.cards.AbstractOperatorsExclusiveCard;
import ArknightsDoctorMod.cards.operators.RhineLabOperators.Saria;
import ArknightsDoctorMod.helper.DoctorHelper;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MedicineDispensing extends AbstractOperatorsExclusiveCard {

    public static final String ID = DoctorHelper.MakePath("MedicineDispensing");
    private static final CardStrings cardStrings= CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME=cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static int COST = 5;

    public MedicineDispensing(){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),COST,DESCRIPTION,CardType.SKILL,CardTarget.SELF, Saria.ID);
        this.baseDamage=3;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeName();
            this.upgradeDamage(2);
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBot(new HealAction(abstractPlayer,abstractPlayer,this.damage));
    }
}
