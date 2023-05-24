package ArknightsDoctorMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

import java.util.ArrayList;
import java.util.function.Consumer;


//选从给定的卡组中选择一张加入手牌
public class CopyCardsToHandAction extends AbstractGameAction {

    private final String msg;
    private final boolean skippable;
    private final ArrayList<AbstractCard> options;
    private boolean retrieveCard = false;

    public CopyCardsToHandAction(ArrayList<AbstractCard> options, String msg, boolean skippable) {//朴实无华的赋值
        this.msg = msg;//选择窗口的文本提示
        this.skippable = skippable;//有无跳过选项
        this.options = options;//要进行选择的所有卡牌
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if(options.size()!=0)
            {
                AbstractDungeon.cardRewardScreen.customCombatOpen(options, msg, skippable);
            }
            this.tickDuration();// 进行一帧 duration 计算 并结束这次 update（据Rita所说）
        } else
        {
            if (!this.retrieveCard)//如果还没用选择的卡牌
            {
                if (AbstractDungeon.cardRewardScreen.discoveryCard != null) {//如果选择的卡牌不为空（也就是说没跳过）
                    AbstractCard disCard = AbstractDungeon.cardRewardScreen.discoveryCard.makeSameInstanceOf();//获得你选择的卡牌
                    disCard.current_x = -1000.0F * Settings.xScale;
                    //加入手牌
                    AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(disCard, (float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                    AbstractDungeon.cardRewardScreen.discoveryCard = null;
                }
                this.retrieveCard = true;//现在用了
            }
            this.tickDuration();
        }
    }
}
