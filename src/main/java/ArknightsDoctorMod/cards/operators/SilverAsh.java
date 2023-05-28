package ArknightsDoctorMod.cards.operators;

import ArknightsDoctorMod.cards.AbstractOperatorsCard;
import ArknightsDoctorMod.cards.operatorCards.Leader;
import ArknightsDoctorMod.cards.operatorCards.RulesofSurvival;
import ArknightsDoctorMod.cards.operatorCards.WolfPack;
import ArknightsDoctorMod.helper.DoctorHelper;
import ArknightsDoctorMod.powers.OperatorsPower.AmiyaPower;
import ArknightsDoctorMod.powers.OperatorsPower.OperatorRedeploymentPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;

public class SilverAsh extends AbstractOperatorsCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(DoctorHelper.MakePath("SilverAsh"));
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String ID = DoctorHelper.MakePath("SilverAsh");

    public SilverAsh(){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),DESCRIPTION,CardRarity.UNCOMMON,DoctorHelper.MakePath(
                "SilverAshPower"));
    }

    @Override
    public void addRedeploymentPowerAction(AbstractCreature owner) {
        this.addToBot(new ApplyPowerAction(owner,owner,new OperatorRedeploymentPower(owner,this.redeployment,this),this.redeployment));
    }

    @Override
    public void setStartOptions() {
        this.addCardToOptions(new Leader());
        this.addCardToOptions(new RulesofSurvival());
    }
}
