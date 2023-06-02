package ArknightsDoctorMod.actions;

import ArknightsDoctorMod.cards.AbstractContractCard;
import ArknightsDoctorMod.contracts.AbstractContract;
import ArknightsDoctorMod.contracts.AbstractContractsChain;
import ArknightsDoctorMod.contracts.ContractGroup;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.Iterator;

public class ContractCardUpgradeAction extends AbstractGameAction {

    public AbstractContractCard card;
    public ContractGroup group;

    public ContractCardUpgradeAction(AbstractContractCard card, ContractGroup group){
        this.card=card;
        this.group=group;
    }


    //在group中，找到未满级的合约放到新列表，随机升级，选择后将被选择的词条链进行upgrade，并调用card的upgrade
    //需要调用upgrade的card除了此卡，还有卡组中的card，以uuid为唯一标识符
    @Override
    public void update() {
        this.isDone=true;
        ArrayList<AbstractContractsChain> canUpgradeChain=new ArrayList<>();
        for (AbstractContractsChain chain : group.AllContracts) {
            if (chain.canUpgrade(card)) {
                canUpgradeChain.add(chain);
            }
        }
        AbstractContractsChain upgradeChain=
                canUpgradeChain.get(AbstractDungeon.cardRandomRng.random(canUpgradeChain.size() - 1));
        upgradeChain.upgrade();

        for (AbstractCard c:AbstractDungeon.player.masterDeck.group) {
            if (c.uuid.equals(card.uuid)){
                c.upgrade();
                break;
            }
        }

    }
}
