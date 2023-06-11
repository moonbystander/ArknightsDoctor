package ArknightsDoctorMod.powers;

import ArknightsDoctorMod.helper.DoctorHelper;
import basemod.BaseMod;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class CapacityExpansionPower extends AbstractPower {

    public static String POWER_ID = DoctorHelper.MakePath("CapacityExpansionPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public CapacityExpansionPower(AbstractCreature owner,int amount){
        this.owner=owner;
        this.amount=amount;
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
        this.description = DESCRIPTIONS[0]+this.amount+DESCRIPTIONS[1];
    }

    public void onInitialApplication() {
        super.onInitialApplication();
        BaseMod.MAX_HAND_SIZE += this.amount;
    }

    public void onRemove() {
        super.onRemove();
        BaseMod.MAX_HAND_SIZE -= this.amount;
    }

    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        BaseMod.MAX_HAND_SIZE += stackAmount;
    }

    public void reducePower(int reduceAmount) {
        super.reducePower(reduceAmount);
        BaseMod.MAX_HAND_SIZE -= reduceAmount;
    }

    public void onVictory() {
        super.onVictory();
        BaseMod.MAX_HAND_SIZE -= this.amount;
    }



}
