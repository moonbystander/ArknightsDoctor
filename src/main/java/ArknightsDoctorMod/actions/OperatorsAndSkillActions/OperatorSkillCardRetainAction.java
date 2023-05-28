package ArknightsDoctorMod.actions.OperatorsAndSkillActions;

import ArknightsDoctorMod.cards.AbstractOperatorsExclusiveCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;


//如果此专属技能卡在手上，则费用减一
public class OperatorSkillCardRetainAction extends AbstractGameAction {

    private AbstractCard targetCard;
    private CardGroup group;
    private float startingDuration;

    public OperatorSkillCardRetainAction(AbstractOperatorsExclusiveCard card, CardGroup group){
        this.targetCard=card;
        this.group=group;
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startingDuration;
    }


    @Override
    public void update() {
        if (this.duration == this.startingDuration && this.group.contains(this.targetCard)) {
            //修改卡牌能量消耗
            targetCard.modifyCostForCombat(-1);
        }
        this.tickDuration();
    }
}
