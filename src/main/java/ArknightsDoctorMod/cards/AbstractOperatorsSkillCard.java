package ArknightsDoctorMod.cards;

import ArknightsDoctorMod.actions.OperatorsAndSkillActions.FindRetreatedCardAction;
import ArknightsDoctorMod.actions.OperatorsAndSkillActions.OperatorSkillCardRetainAction;
import ArknightsDoctorMod.characters.Doctor;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public abstract class AbstractOperatorsSkillCard extends CustomCard {

    //所属干员牌的ID
    public String OperatorsCardId;


    //自带效果：消耗、保留，每保留一回合能耗减一
    public AbstractOperatorsSkillCard(String id, String name, String img, int cost, String rawDescription, CardTarget target,String OperatorsCardId) {
        super(id, name, img, cost, rawDescription, CardType.SKILL, Doctor.Enums.DOCTOR_CARD, CardRarity.SPECIAL, target);
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
        this.addToBot(new FindRetreatedCardAction(abstractPlayer,this.OperatorsCardId));
    }






}
