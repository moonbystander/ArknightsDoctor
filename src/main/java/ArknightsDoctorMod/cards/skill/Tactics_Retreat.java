package ArknightsDoctorMod.cards.skill;

import ArknightsDoctorMod.actions.GetTrustAction;
import ArknightsDoctorMod.actions.Tactics_RetreatAction;
import ArknightsDoctorMod.cards.AbstractMemoryCard;
import ArknightsDoctorMod.cards.AbstractOperatorsCard;
import ArknightsDoctorMod.helper.DoctorHelper;

import ArknightsDoctorMod.powers.RepeatedlyPlayedPower;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

//战术撤退，效果：获得5信赖，并选择手牌中的一张干员牌退场
public class Tactics_Retreat extends AbstractMemoryCard {

    public static final String ID = DoctorHelper.MakePath("Tactics_Retreat");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;

    public Tactics_Retreat(){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),COST,DESCRIPTION,CardType.SKILL,CardRarity.COMMON,CardTarget.SELF
                ,0);
        this.exhaust=true;
        this.baseMagicNumber=this.magicNumber=2;

    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

        this.addToBot(new Tactics_RetreatAction(abstractPlayer));
        //调用clear函数避免被重新加回hand
        this.addToBot(new SelectCardsInHandAction(1,"选择手中的一张干员牌使其退场",card -> card instanceof AbstractOperatorsCard,
                (list)->{
                    list.forEach(card -> ((AbstractOperatorsCard)card).OnRetreat());
                    list.clear();
                }
        ));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeName();
            this.selfRetain=true;
            this.upgradeMagicNumber(1);
        }
    }
}
