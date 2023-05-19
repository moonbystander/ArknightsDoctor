package ArknightsDoctorMod.cards.skill;

import ArknightsDoctorMod.characters.Doctor;
import ArknightsDoctorMod.helper.DoctorHelper;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Defend extends CustomCard {

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(DoctorHelper.MakePath("Defend"));
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int BLOCK_AMT = 6;
    private static final int UPGRADE_PLUS_DMG = 3;
    public static final String ID = DoctorHelper.MakePath("Defend");

    public Defend(){
        super(ID, NAME, DoctorHelper.GetTestImgPath(), COST, DESCRIPTION, CardType.ATTACK, Doctor.Enums.DOCTOR_CARD , CardRarity.BASIC, CardTarget.SELF);
        this.tags.add(CardTags.STARTER_DEFEND);
        this.baseBlock = BLOCK_AMT;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_DMG);
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBot(new GainBlockAction(abstractPlayer, abstractPlayer, this.block));
    }
}
