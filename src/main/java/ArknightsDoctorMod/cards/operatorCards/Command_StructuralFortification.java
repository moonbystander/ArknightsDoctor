package ArknightsDoctorMod.cards.operatorCards;

import ArknightsDoctorMod.cards.AbstractOperatorsExclusiveCard;
import ArknightsDoctorMod.cards.operators.RhodesIslandOperators.Kaltsit;
import ArknightsDoctorMod.helper.DoctorHelper;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class Command_StructuralFortification extends AbstractOperatorsExclusiveCard {

    public static final String ID = DoctorHelper.MakePath("Command_StructuralFortification");
    private static final CardStrings cardStrings= CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME=cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static int COST = -1;

    public Command_StructuralFortification(){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),COST,DESCRIPTION,CardType.SKILL,CardTarget.SELF, Kaltsit.ID);
        this.baseBlock=4;
        this.baseMagicNumber=this.magicNumber=1;
        AbstractCardModifier
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            super.upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
            this.upgradeBlock(2);
        }
    }

    //效果：获得格挡x次，并获得1点金属化
    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1) {
            effect = this.energyOnUse;
        }

        //化合物X
        if (abstractPlayer.hasRelic("Chemical X")) {
            effect += 2;
            abstractPlayer.getRelic("Chemical X").flash();
        }

        if (this.upgraded) {
            effect ++;
        }

        for (int i = 0; i <effect ; i++) {
            this.addToBot(new GainBlockAction(abstractPlayer,abstractPlayer,this.baseBlock));
        }
        this.addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new MetallicizePower(abstractPlayer,1)));
        //消耗能量
        abstractPlayer.energy.use(EnergyPanel.totalCount);
    }
}
