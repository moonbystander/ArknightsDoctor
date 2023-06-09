package ArknightsDoctorMod.relics;

import ArknightsDoctorMod.actions.ChooseCardsToHandAction;
import ArknightsDoctorMod.actions.ContractCardVictoryAction;
import ArknightsDoctorMod.actions.CopyCardsToHandAction;
import ArknightsDoctorMod.cards.AbstractContractCard;
import ArknightsDoctorMod.cards.AbstractOperatorsCard;
import ArknightsDoctorMod.helper.DoctorHelper;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;

import java.util.ArrayList;
import java.util.Iterator;

public class PRTS extends CustomRelic {
    public static final String ID = DoctorHelper.MakePath("PRTS");
    private static final String IMG = DoctorHelper.MakeAssetPath("img/relics/test.png");
    // 遗物类型
    private static final RelicTier RELIC_TIER = RelicTier.STARTER;
    // 点击音效
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public int count=0;

    public PRTS() {
        super(ID, ImageMaster.loadImage(IMG), RELIC_TIER, LANDING_SOUND);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void atPreBattle() {
        count=0;
    }

    //前三回合生效，第一回合从抽牌堆挑选一张干员牌加入手牌，第二回合获得10点格挡，第三回合获得一点能量
    public void atTurnStart() {
        if (count < 3){
            switch (count){
                case 0:
                    this.addToBot(new ChooseCardsToHandAction(card -> card instanceof AbstractOperatorsCard,AbstractDungeon.player.drawPile,"选择一张干员牌加入手牌",1,true));
                    break;
                case 1:
                    this.addToBot(new GainBlockAction(AbstractDungeon.player,10));
                    break;
                case 2:
                    this.addToBot(new GainEnergyAction(1));
                    break;
                default:
                    System.out.println("why you can see this?");
            }
            count++;
        }
    }

    //将所有合约卡的count复制到卡组中
    @Override
    public void onVictory() {
        ArrayList<AbstractContractCard> contractCardList=new ArrayList<>();
        for (AbstractCard c:AbstractDungeon.player.masterDeck.group) {
            if (c instanceof AbstractContractCard){
                contractCardList.add((AbstractContractCard) c);
            }
        }
        this.addToBot(new ContractCardVictoryAction(contractCardList));
    }
}
