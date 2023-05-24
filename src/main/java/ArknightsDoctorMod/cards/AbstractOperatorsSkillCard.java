package ArknightsDoctorMod.cards;

import ArknightsDoctorMod.actions.OperatorsAndSkillActions.FindOperatorsCardToDoAction;
import ArknightsDoctorMod.actions.OperatorsAndSkillActions.OperatorSkillCardRetainAction;
import ArknightsDoctorMod.characters.Doctor;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public abstract class AbstractOperatorsSkillCard extends CustomCard {

    //所属干员牌的ID
    public String OperatorsCardId;


    //自带效果：消耗、保留，每保留一回合能耗减一
    public AbstractOperatorsSkillCard(String id, String name, String img, int cost, String rawDescription,CardType type ,CardTarget target,String OperatorsCardId) {
        super(id, name, img, cost, rawDescription, type, Doctor.Enums.DOCTOR_CARD, CardRarity.SPECIAL, target);
        this.tags.add(Doctor.Enums.OPERATORSKILL);
        this.OperatorsCardId=OperatorsCardId;
        this.selfRetain = true;
    }




    //保留时费用减一
    @Override
    public void onRetained(){
        this.addToBot(new OperatorSkillCardRetainAction(this, AbstractDungeon.player.hand));
    }


    public void makeOperatorRetreat(AbstractPlayer abstractPlayer){
        this.addToBot(new FindOperatorsCardToDoAction(abstractPlayer,this.OperatorsCardId, AbstractOperatorsCard::OnRetreat));
    }


    //预期效果：本场战斗内，此卡从对应干员牌的选项中不再出现，主要针对专属能力牌,需要在能力牌的use中调用 未测试
    //需要测试：是否能在本场战斗中移除、是否会影响下次战斗
    public void cardRemoveToOptions(AbstractPlayer abstractPlayer){
        this.addToBot(new FindOperatorsCardToDoAction(abstractPlayer,this.OperatorsCardId,card -> card.removeCardToOptions(this)));
    }






}
