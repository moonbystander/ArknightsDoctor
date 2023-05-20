package ArknightsDoctorMod.cards;

import ArknightsDoctorMod.actions.CardMoveAction;
import ArknightsDoctorMod.actions.CopyCardsToHandAction;
import ArknightsDoctorMod.actions.GetTrustAction;
import ArknightsDoctorMod.actions.OperatorsAndSkillActions.RetreatAction;
import ArknightsDoctorMod.characters.Doctor;
import ArknightsDoctorMod.helper.DoctorHelper;
import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.SpawnModificationCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Iterator;


//思考：若在抽象干员牌中添加成员：抽象再部署能力，子类负责决定抽象再部署能力是什么，然后在onMoveToDiscard方法中将
public abstract class AbstractOperatorsCard extends CustomCard implements SpawnModificationCard {

    public static String TESTIMG= DoctorHelper.RESOURCEPATH+"img/cards/test.png";
    public ArrayList<AbstractCard> options=new ArrayList<>();
    //再部署能力的ID
    public String powerId;
    public int redeployment=4;

    //默认再部署回合4
    public AbstractOperatorsCard(String id, String name, String img, String rawDescription, CardRarity rarity,String powerId) {
        super(id, name, img, -2, rawDescription, CardType.SKILL , Doctor.Enums.DOCTOR_CARD, rarity, CardTarget.SELF);
        this.setStartOptions();
        this.powerId=powerId;
        this.exhaust = true;
        this.tags.add(Doctor.Enums.OPERATORS);
    }

    public AbstractOperatorsCard(String id, String name, String img, String rawDescription, CardRarity rarity,String powerId,int redeployment) {
        super(id, name, img, -2, rawDescription, CardType.SKILL , Doctor.Enums.DOCTOR_CARD, rarity, CardTarget.SELF);
        this.setStartOptions();
        this.powerId=powerId;
        this.exhaust=true;
        this.tags.add(Doctor.Enums.OPERATORS);
        this.redeployment=redeployment;
    }

    //每次打出干员牌获得1信赖
    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.UseOperatorsCard(abstractPlayer,abstractMonster);
        //如果绑定的技能牌存在，选择一张加入手牌
        if (options.size() > 0 ){
            this.addToBot(new CopyCardsToHandAction(options,"选择一张技能牌加入手牌",true));
        }


    }

    //将对应技能牌全部升级
    @Override
    public void upgrade() {
        if(!this.upgraded){
            this.upgradeName();
            Iterator it =options.iterator();
            while (it.hasNext()){
                AbstractCard c= (AbstractCard) it.next();
                c.upgrade();
            }
        }
    }

    //消耗后立刻进入再部署区
    @Override
    public void triggerOnExhaust() {
        Doctor doctor= (Doctor) AbstractDungeon.player;
        this.addToBot(new CardMoveAction(this,doctor.exhaustPile,doctor.OperatorsWaiting));
    }

    //抽象方法，添加再部署power，需要子类进行实现,在父类中调用
    public abstract void addRedeploymentPowerAction(AbstractCreature owner);

    //向列表中添加一张牌
    public void addCardToOptions(AbstractCard c){
        this.options.add(c);
    }

    public abstract void setStartOptions();

    //从列表中移除一张牌
    public void removeCardToOptions(AbstractCard c){
        this.options.remove(c);
    }

    //获得与干员绑定的技能牌
    public ArrayList<AbstractCard> getOptions(){
        return options;
    };

    //退场时需要调用此函数，用于将战斗中所有干员牌相关移除本场战斗
    public void OnRetreat(){
        this.addToBot(new RetreatAction(AbstractDungeon.player,this,powerId));
    }

    //每次使用干员牌，增加1信赖
    public void UseOperatorsCard(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster){
        this.addToBot(new GetTrustAction(abstractPlayer,1));
        this.addRedeploymentPowerAction(abstractPlayer);
    }


    //如果卡组中已经存在，则剔除


    @Override
    public boolean canSpawn(ArrayList<AbstractCard> currentRewardCards) {
        //Player can't already have the card.
        //检查卡组中是否有此卡
        for(AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.cardID.equals(this.cardID)) {
                return false;
            }
        }
        //Card will spawn.
        return true;
    }
}
