package ArknightsDoctorMod.cards.operators;

import ArknightsDoctorMod.cards.AbstractOperatorsCard;
import ArknightsDoctorMod.cards.operatorCards.Chimera;
import ArknightsDoctorMod.cards.operatorCards.SpiritBurst;
import ArknightsDoctorMod.helper.DoctorHelper;
import ArknightsDoctorMod.powers.OperatorsPower.AmiyaPower;
import ArknightsDoctorMod.relics.OperatorRecords;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;


//绑定专属技能牌
public class Amiya extends AbstractOperatorsCard {

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(DoctorHelper.MakePath("Amiya"));
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String ID = DoctorHelper.MakePath("Amiya");


    //初始化
    public Amiya(){
        super(ID,NAME,AbstractOperatorsCard.TESTIMG,DESCRIPTION,CardRarity.BASIC,AmiyaPower.POWER_ID);
    }

    @Override
    public void addRedeploymentPowerAction(AbstractCreature owner) {
        this.addToBot(new ApplyPowerAction(owner,owner,new AmiyaPower(owner,this.redeployment,this),this.redeployment));
    }

    @Override
    public void setStartOptions() {
        this.addCardToOptions(new SpiritBurst());
        this.addCardToOptions(new Chimera());
    }

    //当此卡从卡组中移除时，将信赖遗物中的hasAmiya置为false
    @Override
    public void onRemoveFromMasterDeck() {
        OperatorRecords.hasAmiya=false;
    }

}
