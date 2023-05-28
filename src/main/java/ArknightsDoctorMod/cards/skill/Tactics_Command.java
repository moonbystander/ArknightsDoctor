package ArknightsDoctorMod.cards.skill;

import ArknightsDoctorMod.cards.AbstractMemoryCard;
import ArknightsDoctorMod.helper.DoctorHelper;
import ArknightsDoctorMod.powers.FreeExclusivePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Tactics_Command extends AbstractMemoryCard {

    public static final String ID = DoctorHelper.MakePath("Tactics_Command");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 3;

    //获得5信赖，本回合打出的下一张干员专属牌耗能为0
    public Tactics_Command(){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),COST,DESCRIPTION,CardType.SKILL,CardRarity.RARE,CardTarget.SELF,5);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        super.use(abstractPlayer, abstractMonster);
        this.addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new FreeExclusivePower(abstractPlayer,1),1));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeName();
            this.upgradeBaseCost(2);
        }
    }
}
