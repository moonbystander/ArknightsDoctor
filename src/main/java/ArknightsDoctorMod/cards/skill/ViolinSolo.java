package ArknightsDoctorMod.cards.skill;

import ArknightsDoctorMod.cards.AbstractMemoryCard;
import ArknightsDoctorMod.helper.DoctorHelper;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ViolinSolo extends AbstractMemoryCard {
    public static final String ID = DoctorHelper.MakePath("ViolinSolo");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;


    public ViolinSolo(){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),COST,DESCRIPTION,CardType.SKILL,CardRarity.UNCOMMON,CardTarget.SELF);
        this.baseBlock=6;
        this.baseMagicNumber=this.magicNumber=2;
    }


    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeName();
            this.upgradeBlock(3);
            this.upgradeMagicNumber(2);
            this.trustamount+=1;
        }
    }

    //获得信赖、格挡、生命
    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        super.use(abstractPlayer, abstractMonster);
        this.addToBot(new GainBlockAction(abstractPlayer,abstractPlayer,this.block));
        this.addToBot(new HealAction(abstractPlayer,abstractPlayer,this.magicNumber));
    }
}
