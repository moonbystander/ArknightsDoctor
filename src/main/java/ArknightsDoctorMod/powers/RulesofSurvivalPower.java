package ArknightsDoctorMod.powers;

import ArknightsDoctorMod.helper.DoctorHelper;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class RulesofSurvivalPower extends AbstractPower {

    public static String POWER_ID = DoctorHelper.MakePath("RulesofSurvivalPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public int block;
    public int heal;

    public RulesofSurvivalPower(AbstractCreature owner,int block,int heal){
        this.amount=-1;
        this.ID=POWER_ID;
        this.name=NAME;
        this.owner=owner;
        this.block=block;
        this.heal=heal;
        this.type=PowerType.BUFF;
    }


    @Override
    public void updateDescription(){
        this.description=DESCRIPTIONS[0];
    }


    @Override
    public void atStartOfTurn() {
        this.addToBot(new GainBlockAction(owner,owner,block));
        this.addToBot(new HealAction(owner,owner,heal));
    }
}
