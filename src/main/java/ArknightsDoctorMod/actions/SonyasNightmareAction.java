package ArknightsDoctorMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SonyasNightmareAction extends AbstractGameAction {

    public AbstractCard card;

    public SonyasNightmareAction(AbstractCard card,int amount){
        this.card=card;
        this.amount=amount;
    }

    @Override
    public void update() {
        this.isDone=true;
        if (AbstractDungeon.player.hand.contains(card)){
            this.addToBot(new DamageAction(AbstractDungeon.player, new DamageInfo(AbstractDungeon.player,
                    this.amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
        }
    }
}
