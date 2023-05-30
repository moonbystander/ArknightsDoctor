package ArknightsDoctorMod.cards.skill;

import ArknightsDoctorMod.actions.RemoveCardFromDeckAction;
import ArknightsDoctorMod.cards.AbstractMemoryCard;
import ArknightsDoctorMod.helper.DoctorHelper;
import ArknightsDoctorMod.relics.BrokenShield;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BarricadePower;

//使用后移除，获得遗物：，遗物效果：每回合获得1能量（博士2能量开局）
public class Ace extends AbstractMemoryCard {

    public static final String ID = DoctorHelper.MakePath("Ace");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 0;

    public Ace(){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),COST,DESCRIPTION,CardType.SKILL,CardRarity.BASIC,CardTarget.SELF,2);
        this.baseBlock=50;
        this.exhaust=true;
    }

    //移除此卡
    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        super.use(abstractPlayer, abstractMonster);
        this.addToBot(new GainBlockAction(abstractPlayer,abstractPlayer,50));
        this.addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new BarricadePower(abstractPlayer)));
        this.addToBot(new RemoveCardFromDeckAction(this));
    }

    //获得遗物：一面损坏严重的大盾
    @Override
    public void onRemoveFromMasterDeck() {
        new BrokenShield().instantObtain();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeName();
            this.selfRetain=true;
        }
    }
}
