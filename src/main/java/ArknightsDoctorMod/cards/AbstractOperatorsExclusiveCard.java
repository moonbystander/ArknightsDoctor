package ArknightsDoctorMod.cards;

import ArknightsDoctorMod.actions.OperatorsAndSkillActions.FindOperatorsCardToDoAction;
import ArknightsDoctorMod.actions.OperatorsAndSkillActions.OperatorSkillCardRetainAction;
import ArknightsDoctorMod.characters.Doctor;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public abstract class AbstractOperatorsExclusiveCard extends CustomCard {

    //所属干员牌的ID
    public String OperatorsCardId;


    //自带效果：消耗、保留，每保留一回合能耗减一
    public AbstractOperatorsExclusiveCard(String id, String name, String img, int cost, String rawDescription, CardType type , CardTarget target, String OperatorsCardId) {
        super(id, name, img, cost, rawDescription, type, Doctor.Enums.DOCTOR_CARD, CardRarity.SPECIAL, target);
        this.tags.add(Doctor.Enums.OPERATORSKILL);
        this.OperatorsCardId=OperatorsCardId;
        this.selfRetain = true;
        this.exhaust=true;
    }




    //保留时费用减一
    @Override
    public void onRetained(){
        this.addToBot(new OperatorSkillCardRetainAction(this, AbstractDungeon.player.hand));
    }



    public void makeOperatorRetreat(AbstractPlayer abstractPlayer){
        this.addToBot(new FindOperatorsCardToDoAction(abstractPlayer,this.OperatorsCardId, AbstractOperatorsCard::OnRetreat));
    }


    //每场战斗只能打出一次的卡牌需要调用此函数
    //预期效果：本场战斗内，此卡从对应干员牌的选项中不再出现，主要针对专属能力牌,需要在能力牌的use中调用 测试结果：无法在战斗中移除选项
    //需要测试：是否能在本场战斗中移除、是否会影响下次战斗(由于每次拷贝干员牌，都会执行setStartOptions重置专属牌选项，因此不会影响)
    public void cardRemoveToOptions(AbstractPlayer abstractPlayer){
        this.addToBot(new FindOperatorsCardToDoAction(abstractPlayer,this.OperatorsCardId,card -> card.removeCardToOptions(this)));
    }


    @Override
    public AbstractCard makeStatEquivalentCopy() {
        AbstractOperatorsExclusiveCard card= (AbstractOperatorsExclusiveCard) super.makeStatEquivalentCopy();
        card.OperatorsCardId=this.OperatorsCardId;
        return card;
    }
}
