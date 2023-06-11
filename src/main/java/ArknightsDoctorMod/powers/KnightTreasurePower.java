package ArknightsDoctorMod.powers;

import ArknightsDoctorMod.helper.DoctorHelper;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class KnightTreasurePower extends AbstractPower {

    public static String POWER_ID = DoctorHelper.MakePath("KnightTreasurePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public boolean hasPosition=false;
    public boolean hasKey=false;

    public KnightTreasurePower(AbstractCreature owner){
        this.owner=owner;
        this.amount=-1;
        this.ID=POWER_ID;
        this.name=NAME;
        this.type=PowerType.DEBUFF;

        String path128 = DoctorHelper.getResourcePath()+"img/powers/test84.png";
        String path48 = DoctorHelper.getResourcePath()+"img/powers/test32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
    }

    @Override
    public void updateDescription(){
        if (hasPosition && hasKey){
            this.description=DESCRIPTIONS[3];
        } else {
            this.description=DESCRIPTIONS[0];
            if (!hasPosition){
                this.description+=DESCRIPTIONS[1];
            }
            if (!hasKey){
                this.description+=DESCRIPTIONS[2];
            }
        }
    }



    //添加随机遗物到奖励列表
    @Override
    public void onVictory() {
        if ( hasKey && hasPosition){
            AbstractDungeon.getCurrRoom().addGoldToRewards(150);
            AbstractDungeon.getCurrRoom().addRelicToRewards(GetRandomTier());
        }
    }

    private AbstractRelic.RelicTier GetRandomTier() {
        float randNum = (new Random()).random(0.0F, 1.0F);
        if (randNum <= 0.125F)
            return AbstractRelic.RelicTier.RARE;
        if (randNum <= 0.5F)
            return AbstractRelic.RelicTier.UNCOMMON;
        return AbstractRelic.RelicTier.COMMON;
    }
}
