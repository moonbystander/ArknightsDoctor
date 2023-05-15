package ArknightsDoctorMod.cards;

import ArknightsDoctorMod.actions.CardMoveAction;
import ArknightsDoctorMod.actions.OperatorSkillCardRetainAction;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public abstract class AbstractOperatorsSkillCard extends CustomCard {


    //自带效果：消耗、保留，每保留一回合能耗减一
    public AbstractOperatorsSkillCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
        this.exhaust=true;
        this.retain=true;
    }


    //玩家回合结束时触发，添加新行动：专属技能牌保留行动，将手中的专属技能牌消耗减一
    @Override
    public void triggerOnEndOfPlayerTurn() {
        if (this.isEthereal) {
            this.addToTop(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
        }else {
            this.addToBot(new OperatorSkillCardRetainAction(this, AbstractDungeon.player.hand));
        }
    }


    //卡牌被丢入弃牌堆触发，当此卡进入弃牌堆时，将此卡消耗
    @Override
    public void onMoveToDiscard(){
        this.addToBot(new CardMoveAction(this,AbstractDungeon.player.discardPile,AbstractDungeon.player.exhaustPile));
    }



}
