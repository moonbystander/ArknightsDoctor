package ArknightsDoctorMod.powers;

import ArknightsDoctorMod.actions.LossTrustAction;
import ArknightsDoctorMod.cards.AbstractOperatorsExclusiveCard;
import ArknightsDoctorMod.characters.Doctor;
import ArknightsDoctorMod.helper.DoctorHelper;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ForVictory extends AbstractPower {

    public static String POWER_ID = DoctorHelper.MakePath("ForVictory");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public int magic;
    public ForVictory(AbstractCreature owner,int magic){
        this.owner=owner;
        this.magic=magic;
        this.amount=-1;
        this.ID=POWER_ID;
        this.name=NAME;
        this.type=PowerType.BUFF;

        String path128 = DoctorHelper.getResourcePath()+"img/powers/test84.png";
        String path48 = DoctorHelper.getResourcePath()+"img/powers/test32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description=DESCRIPTIONS[0]+this.magic+DESCRIPTIONS[1]+this.magic+DESCRIPTIONS[2]+DESCRIPTIONS[3];
    }

    @Override
    public void atStartOfTurn() {
        if (((Doctor)this.owner).trust > 50){
            this.addToBot(new LossTrustAction(this.owner,20));
            this.addToBot(new DrawCardAction(this.owner,2));
            for (AbstractCard card:((AbstractPlayer)this.owner).hand.group) {
                if (card instanceof AbstractOperatorsExclusiveCard){
                    ((AbstractOperatorsExclusiveCard)card).onRetained();
                    ((AbstractOperatorsExclusiveCard)card).onRetained();
                }
            }
        }else {
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        }

    }
}
