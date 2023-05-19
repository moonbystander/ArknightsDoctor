package ArknightsDoctorMod.actions;

import ArknightsDoctorMod.cards.AbstractOperatorsCard;
import ArknightsDoctorMod.characters.Doctor;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;

import java.util.Iterator;


//找到退场干员牌，然后调用它的OnRetreat函数
public class FindRetreatedCardAction extends AbstractGameAction {

    public String OperatorId;

    public FindRetreatedCardAction(AbstractCreature target,String OperatorId){
        this.target=target;
        this.OperatorId=OperatorId;
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
                ((AbstractOperatorsCard)card).OnRetreat();
            }
        }

        it=((AbstractPlayer)target).discardPile.group.iterator();
        while (it.hasNext()){
            card= (AbstractCard) it.next();
            if (card.cardID.equals(this.OperatorId)){
                ((AbstractOperatorsCard)card).OnRetreat();
            }
        }

        it=((AbstractPlayer)target).hand.group.iterator();
        while (it.hasNext()){
            card= (AbstractCard) it.next();
            if (card.cardID.equals(this.OperatorId)){
                ((AbstractOperatorsCard)card).OnRetreat();
            }
        }

        it=((Doctor)target).OperatorsWaiting.group.iterator();
        while (it.hasNext()){
            card= (AbstractCard) it.next();
            if (card.cardID.equals(this.OperatorId)){
                ((AbstractOperatorsCard)card).OnRetreat();
            }
        }

    }
}
