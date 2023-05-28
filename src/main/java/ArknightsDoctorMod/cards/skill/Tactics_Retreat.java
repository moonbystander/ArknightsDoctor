package ArknightsDoctorMod.cards.skill;

import ArknightsDoctorMod.cards.AbstractMemoryCard;
import ArknightsDoctorMod.cards.AbstractOperatorsCard;
import ArknightsDoctorMod.helper.DoctorHelper;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

//战术撤退，效果：获得5信赖，并选择手牌中的一张干员牌退场
public class Tactics_Retreat extends AbstractMemoryCard {

    public static final String ID = DoctorHelper.MakePath("Tactics_Retreat");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;

    public Tactics_Retreat(){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),COST,DESCRIPTION,CardType.SKILL,CardRarity.COMMON,CardTarget.SELF,5);
        this.exhaust=true;

    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        super.use(abstractPlayer, abstractMonster);
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
        }
    }
}
