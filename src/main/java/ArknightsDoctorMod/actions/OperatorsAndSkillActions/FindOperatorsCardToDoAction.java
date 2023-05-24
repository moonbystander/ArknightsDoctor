package ArknightsDoctorMod.actions.OperatorsAndSkillActions;

import ArknightsDoctorMod.cards.AbstractOperatorsCard;
import ArknightsDoctorMod.characters.Doctor;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;

import java.util.Iterator;
import java.util.function.Consumer;


//找到符合ID的干员牌，根据Consumer进行对干员牌进行操作
public class FindOperatorsCardToDoAction extends AbstractGameAction {

    public Consumer<AbstractOperatorsCard> consumer;

    public String OperatorId;

    public FindOperatorsCardToDoAction(AbstractCreature target, String OperatorId, Consumer<AbstractOperatorsCard> consumer){
        this.target=target;
        this.OperatorId=OperatorId;
        this.consumer=consumer;
    }


    //遍历三堆，找到目标牌
    @Override
    public void update() {
        this.isDone=true;
        AbstractCard card;
        Iterator it=((AbstractPlayer)target).drawPile.group.iterator();
        while (it.hasNext()){
            card= (AbstractCard) it.next();
            if (card.cardID.equals(this.OperatorId)){
                consumer.accept((AbstractOperatorsCard) card);
            }
        }

        it=((AbstractPlayer)target).discardPile.group.iterator();
        while (it.hasNext()){
            card= (AbstractCard) it.next();
            if (card.cardID.equals(this.OperatorId)){
                consumer.accept((AbstractOperatorsCard) card);
            }
        }

        it=((AbstractPlayer)target).hand.group.iterator();
        while (it.hasNext()){
            card= (AbstractCard) it.next();
            if (card.cardID.equals(this.OperatorId)){
                consumer.accept((AbstractOperatorsCard) card);
            }
        }

        it=((Doctor)target).OperatorsWaiting.group.iterator();
        while (it.hasNext()){
            card= (AbstractCard) it.next();
            if (card.cardID.equals(this.OperatorId)){
                consumer.accept((AbstractOperatorsCard) card);
            }
        }

    }
}
