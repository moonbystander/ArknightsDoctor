package ArknightsDoctorMod.cards.power;

import ArknightsDoctorMod.cards.AbstractMemoryCard;
import ArknightsDoctorMod.helper.DoctorHelper;
import ArknightsDoctorMod.powers.ZhiPaoSekipoPower;
import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePower;

public class ZhiPaoSekipo extends AbstractMemoryCard {

    public static final String ID = DoctorHelper.MakePath("ZhiPaoSekipo");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;

    public ZhiPaoSekipo(){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),COST,DESCRIPTION,CardType.POWER,CardRarity.UNCOMMON,
                CardTarget.SELF,2);
        this.baseMagicNumber=this.magicNumber=5;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        super.use(abstractPlayer, abstractMonster);
        for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters){
            this.addToBot(new ApplyPowerAction(mo,mo,new IntangiblePower(mo,3)));
        }
        this.addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new ZhiPaoSekipoPower(abstractPlayer,
                this.baseMagicNumber)));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeName();
            this.upgradeBaseCost(0);
            this.upgradeMagicNumber(-1);
        }
    }
}
