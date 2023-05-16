package ArknightsDoctorMod.cards;

import ArknightsDoctorMod.actions.CardMoveAction;
import ArknightsDoctorMod.actions.ChooseCardsToHandAction;
import ArknightsDoctorMod.actions.GetTrustAction;
import ArknightsDoctorMod.characters.Doctor;
import ArknightsDoctorMod.helper.DoctorHelper;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Iterator;


//思考：若在抽象干员牌中添加成员：抽象再部署能力，子类负责决定抽象再部署能力是什么，然后在onMoveToDiscard方法中将
public abstract class AbstractOperatorsCard extends CustomCard {

    public static String TESTIMG= DoctorHelper.RESOURCEPATH+"img/cards/test.png";
    public ArrayList<AbstractCard> options=new ArrayList<>();
    public int redeployment=4;

    //默认再部署回合4
    public AbstractOperatorsCard(String id, String name, String img, String rawDescription, CardRarity rarity) {
        super(id, name, img, -2, rawDescription, CardType.SKILL , Doctor.Enums.DOCTOR_CARD, rarity, CardTarget.SELF);
        this.tags.add(Doctor.Enums.OPERATORS);
    }

    public AbstractOperatorsCard(String id, String name, String img, String rawDescription, CardRarity rarity,int redeployment) {
        super(id, name, img, -2, rawDescription, CardType.SKILL , Doctor.Enums.DOCTOR_CARD, rarity, CardTarget.SELF);
        this.tags.add(Doctor.Enums.OPERATORS);
        this.redeployment=redeployment;
    }

    //每次打出干员牌获得1信赖
    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBot(new GetTrustAction(abstractPlayer,1));
        //如果绑定的技能牌存在，选择一张加入手牌
        if (options.size() > 0 ){
            this.addToBot(new ChooseCardsToHandAction(options,"选择一张技能牌加入手牌",true));
        }
        //调用子类实现的方法，添加再部署power
        addRedeploymentPowerAction(abstractPlayer);

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

    //进入弃牌堆后立刻到再部署区
    //添加一个层数等于再部署时间的power，当层数为零时，将此卡重新加入抽牌堆
    @Override
    public void onMoveToDiscard() {
        Doctor doctor= (Doctor) AbstractDungeon.player;
        this.addToBot(new CardMoveAction(this,doctor.discardPile,doctor.OperatorsWaiting));
    }

    //抽象方法，添加再部署power，需要子类进行实现
    public abstract void addRedeploymentPowerAction(AbstractCreature owner);

    //向列表中添加一张牌
    public void addCardToOptions(AbstractCard c){
        this.options.add(c);
    }

    //从列表中移除一张牌
    public void removeCardToOptions(AbstractCard c){
        this.options.remove(c);
    }


    //获得与干员绑定的技能牌
    public ArrayList<AbstractCard> getOptions(){
        return options;
    };


}
