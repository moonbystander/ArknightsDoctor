package ArknightsDoctorMod.cards.skill;

import ArknightsDoctorMod.actions.ContractCardUpgradeAction;
import ArknightsDoctorMod.cards.AbstractContractCard;
import ArknightsDoctorMod.cards.AbstractMemoryCard;
import ArknightsDoctorMod.helper.DoctorHelper;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SoEasy extends AbstractMemoryCard {

    public static final String ID = DoctorHelper.MakePath("SoEasy");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;

    public SoEasy(){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),COST,DESCRIPTION,CardType.SKILL,CardRarity.RARE,CardTarget.NONE,
                -50);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        super.use(abstractPlayer, abstractMonster);
        //选择手中的合约牌，进行一次词条强化
        this.addToBot(new SelectCardsInHandAction(1,"选择一张合约卡进行词条强化",card -> card instanceof AbstractContractCard,
                list->{
                    list.forEach(card -> this.addToBot(new ContractCardUpgradeAction(
                            (AbstractContractCard) card,
                            ((AbstractContractCard)card).contractGroup)
                            )
                    );
                }));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeName();
            this.upgradeBaseCost(0);
            this.selfRetain=true;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}
