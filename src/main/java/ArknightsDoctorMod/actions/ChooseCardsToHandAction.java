package ArknightsDoctorMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;


//根据传入的筛选条件condition，从指定的卡堆中选择num张卡加入手牌
public class ChooseCardsToHandAction extends AbstractGameAction {

    public Predicate<AbstractCard> condition;
    public final String msg;
    public final int num;
    public boolean skippable=true;
    public CardGroup group;
    private boolean retrieveCard = false;

    public ChooseCardsToHandAction(Predicate<AbstractCard> condition, CardGroup group,String msg,int num,boolean skippable){
        this.condition=condition;
        this.msg=msg;
        this.num=num;
        this.skippable=skippable;
        this.group=group;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            ArrayList<AbstractCard> options=group.group.stream().filter(condition).collect(Collectors.toCollection(ArrayList::new));
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
                    AbstractCard disCard = AbstractDungeon.cardRewardScreen.discoveryCard;//获得你选择的卡牌
                    disCard.current_x = -1000.0F * Settings.xScale;
                    //加入手牌
                    AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(disCard, (float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                    AbstractDungeon.cardRewardScreen.discoveryCard = null;
                    //从原卡堆中删除被加入手牌的卡
                    group.removeCard(disCard);
                }
                this.retrieveCard = true;//现在用了
            }
            this.tickDuration();
        }
    }
}
