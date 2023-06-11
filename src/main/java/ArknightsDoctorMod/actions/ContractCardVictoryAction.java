package ArknightsDoctorMod.actions;

import ArknightsDoctorMod.cards.AbstractContractCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class ContractCardVictoryAction extends AbstractGameAction {

    ArrayList<AbstractContractCard> contractCardList;

    //当战斗结束时，将战斗中的合约卡的count复制到
    public ContractCardVictoryAction(ArrayList<AbstractContractCard> contractCardList){
        this.contractCardList=contractCardList;
    }

    @Override
    public void update() {
        for (AbstractContractCard cardA:contractCardList) {
            boolean ok=false;
            for (AbstractCard cardB: AbstractDungeon.player.drawPile.group) {
                if (cardB.uuid.equals(cardA.uuid)){
                    cardA.count=((AbstractContractCard)cardB).count;
                    ok=true;
                    break;
                }
            }
            if (ok){
                continue;
            }
            for (AbstractCard cardB: AbstractDungeon.player.hand.group) {
                if (cardB.uuid.equals(cardA.uuid)){
                    cardA.count=((AbstractContractCard)cardB).count;
                    ok=true;
                    break;
                }
            }
            if (ok){
                continue;
            }
            for (AbstractCard cardB: AbstractDungeon.player.discardPile.group) {
                if (cardB.uuid.equals(cardA.uuid)){
                    cardA.count=((AbstractContractCard)cardB).count;
                    ok=true;
                    break;
                }
            }
            if (ok){
                continue;
            }
            for (AbstractCard cardB: AbstractDungeon.player.exhaustPile.group) {
                if (cardB.uuid.equals(cardA.uuid)){
                    cardA.count=((AbstractContractCard)cardB).count;
                    ok=true;
                    break;
                }
            }

        }
    }
}
