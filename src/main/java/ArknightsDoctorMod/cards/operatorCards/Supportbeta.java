package ArknightsDoctorMod.cards.operatorCards;

import ArknightsDoctorMod.cards.AbstractOperatorsExclusiveCard;
import ArknightsDoctorMod.cards.operators.RhodesIslandOperators.Myrtle;
import ArknightsDoctorMod.helper.DoctorHelper;
import ArknightsDoctorMod.powers.SupportbetaPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Supportbeta extends AbstractOperatorsExclusiveCard {
    public static final String ID = DoctorHelper.MakePath("Supportbeta");
    private static final CardStrings cardStrings= CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME=cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static int COST = 6;

    public Supportbeta(){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),COST,DESCRIPTION,CardType.SKILL,CardTarget.SELF, Myrtle.ID);
        this.baseMagicNumber=this.magicNumber=2;
    }
    public Supportbeta(String operatorID){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),COST,DESCRIPTION,CardType.SKILL,CardTarget.SELF, operatorID);
        this.baseMagicNumber=this.magicNumber=2;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeName();
            this.upgradeMagicNumber(2);
            this.upgradeBaseCost(4);
        }
    }

    //接下来M回合，每回合获得2能量
    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new SupportbetaPower(abstractPlayer,this.baseMagicNumber)));
    }
}
