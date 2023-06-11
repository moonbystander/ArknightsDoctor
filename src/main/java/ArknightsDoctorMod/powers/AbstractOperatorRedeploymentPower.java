package ArknightsDoctorMod.powers;

import ArknightsDoctorMod.actions.CardMoveAction;
import ArknightsDoctorMod.cards.AbstractOperatorsCard;
import ArknightsDoctorMod.characters.Doctor;
import ArknightsDoctorMod.helper.DoctorHelper;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;


//思考：不同干员的再部署能力之间，区别只有ID和card，能否复用power？
public abstract class AbstractOperatorRedeploymentPower extends AbstractPower {
    public AbstractOperatorsCard card;

    public AbstractOperatorRedeploymentPower(AbstractCreature owner , int amount , AbstractOperatorsCard card){
        this.owner=owner;
        this.amount=amount;
        this.card=card;
        this.type = PowerType.BUFF;
    }



    //回合结束时，减少一层
    @Override
    public void atEndOfTurn(boolean isPlayer) {
        this.addToBot(new ReducePowerAction(owner,owner,this,1));
    }

    //当power被移除时，添加一个行动用以将与此power绑定的card置入抽卡堆
    @Override
    public void onRemove() {
        Doctor doctor= (Doctor) AbstractDungeon.player;
        this.addToBot(new CardMoveAction(card,doctor.OperatorsWaiting,doctor.drawPile,true));
    }
}
