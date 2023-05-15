package ArknightsDoctorMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;

//将卡牌从原卡堆移动到新卡堆
public class CardMoveAction extends AbstractGameAction {

    public AbstractCard card;
    public CardGroup groupA;
    public CardGroup groupB;


    public CardMoveAction(AbstractCard card,CardGroup groupA,CardGroup groupB){
        this.card=card;
        this.groupA=groupA;
        this.groupB=groupB;
    }

    //从A卡堆移动到B卡堆
    @Override
    public void update() {
        this.isDone=true;
        if (groupA.contains(card)){
            groupB.addToTop(card);
            groupA.removeCard(card);
        }
    }
}
