package ArknightsDoctorMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class WindmillAction extends AbstractGameAction {

    public WindmillAction(){

    }

    @Override
    public void update() {
        this.isDone=true;

        for (AbstractCard card: AbstractDungeon.player.hand.group) {
            if (card.cost >= 0) {
                int newCost = AbstractDungeon.cardRandomRng.random(8);
                if (card.cost != newCost) {
                    card.setCostForTurn(newCost);
                }
            }
        }
    }
}
