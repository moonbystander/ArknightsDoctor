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
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public abstract class AbstractOperatorsCard extends CustomCard {

    public static String TESTIMG= DoctorHelper.RESOURCEPATH+"img/cards/test.png";
    public ArrayList<AbstractCard> options=new ArrayList<>();
    public int redeployment=4;

    //默认再部署回合4
    public AbstractOperatorsCard(String id, String name, String img, int cost, String rawDescription, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, CardType.SKILL , color, rarity, target);
        this.tags.add(Doctor.Enums.OPERATORS);
    }

    public AbstractOperatorsCard(String id, String name, String img, int cost, String rawDescription, CardColor color, CardRarity rarity, CardTarget target,int redeployment) {
        super(id, name, img, cost, rawDescription, CardType.SKILL , color, rarity, target);
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

    }


    //进入弃牌堆后立刻到再部署区
    //TODO：添加一个层数等于再部署时间的power，当层数为零时，将此卡重新加入抽牌堆
    @Override
    public void onMoveToDiscard() {
        Doctor doctor= (Doctor) AbstractDungeon.player;
        this.addToBot(new CardMoveAction(this,doctor.discardPile,doctor.OperatorsWaiting));
        addRedeploymentPowerAction();
    }

    public abstract void addRedeploymentPowerAction();

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
