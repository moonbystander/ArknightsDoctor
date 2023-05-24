package ArknightsDoctorMod.cards.operatorCards;

import ArknightsDoctorMod.cards.AbstractOperatorsSkillCard;
import ArknightsDoctorMod.cards.operators.SilverAsh;
import ArknightsDoctorMod.helper.DoctorHelper;
import ArknightsDoctorMod.powers.LeaderPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Leader extends AbstractOperatorsSkillCard {

    private static final CardStrings cardStrings= CardCrawlGame.languagePack.getCardStrings(DoctorHelper.MakePath("Leader"));
    public static final String NAME=cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static int COST = 1;
    public static final String ID = DoctorHelper.MakePath("Leader");

    public Leader(){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),COST,DESCRIPTION,CardType.POWER,CardTarget.SELF, SilverAsh.ID);
        this.baseMagicNumber=this.magicNumber=6;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeName();
            this.upgradeMagicNumber(-2);
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new LeaderPower(abstractPlayer,this.magicNumber)));
        super.cardRemoveToOptions(abstractPlayer);
    }
}
