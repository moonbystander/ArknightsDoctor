package ArknightsDoctorMod.actions;

import ArknightsDoctorMod.cards.skill.Treasure;
import ArknightsDoctorMod.cards.skill.TreasureKey;
import ArknightsDoctorMod.cards.states.BountyHunter;
import ArknightsDoctorMod.cards.states.SonyasNightmare;
import ArknightsDoctorMod.powers.KnightTreasurePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class FindTreasureAction extends AbstractGameAction {

    public AbstractCard card;
    public FindTreasureAction(AbstractCreature target,AbstractCard card){
        this.target=target;
        this.card=card;
    }


    @Override
    public void update() {
        this.isDone=true;
        if (target.hasPower(KnightTreasurePower.POWER_ID)){
            KnightTreasurePower p= (KnightTreasurePower) target.getPower(KnightTreasurePower.POWER_ID);
            if (card instanceof TreasureKey){
                p.hasKey=true;
                p.updateDescription();
                this.addToBot(new MakeTempCardInDiscardAction(new BountyHunter(),3));
            }else if (card instanceof Treasure){
                p.hasPosition=true;
                p.updateDescription();
            }
        }
    }
}
