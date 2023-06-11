package ArknightsDoctorMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Iterator;

//从卡组中删除指定的卡(根据uuid判定)
public class RemoveCardFromDeckAction extends AbstractGameAction {

    public AbstractCard removeCard;

    public RemoveCardFromDeckAction(AbstractCard removeCard){
        this.removeCard=removeCard;
    }


    @Override
    public void update() {
        this.isDone=true;
        AbstractCard c;
        Iterator<AbstractCard> it= AbstractDungeon.player.masterDeck.group.iterator();
        while(it.hasNext()) {
            c = it.next();
            if (c.uuid.equals(removeCard.uuid)) {
                AbstractDungeon.player.masterDeck.removeCard(c);
                break;
            }
        }
    }
}
