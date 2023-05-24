package ArknightsDoctorMod.cards.operators;

import ArknightsDoctorMod.cards.AbstractOperatorsCard;
import ArknightsDoctorMod.cards.operatorCards.WolfPack;
import ArknightsDoctorMod.helper.DoctorHelper;
import ArknightsDoctorMod.powers.OperatorsPower.RedPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;

public class Red extends AbstractOperatorsCard {

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(DoctorHelper.MakePath("Red"));
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String ID = DoctorHelper.MakePath("Red");


    public Red(){
        super(ID,NAME,AbstractOperatorsCard.TESTIMG,DESCRIPTION,CardRarity.RARE, RedPower.POWER_ID,3);
    }

    @Override
    public void addRedeploymentPowerAction(AbstractCreature owner) {
        this.addToBot(new ApplyPowerAction(owner,owner,new RedPower(owner,this.redeployment,this),this.redeployment));
    }

    @Override
    public void setStartOptions() {
        this.addCardToOptions(new WolfPack());
    }
}
