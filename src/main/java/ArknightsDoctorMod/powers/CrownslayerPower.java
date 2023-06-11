package ArknightsDoctorMod.powers;

import ArknightsDoctorMod.helper.DoctorHelper;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.IntangiblePower;

public class CrownslayerPower extends AbstractPower {

    public static String POWER_ID = DoctorHelper.MakePath("CrownslayerPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    //每getPowerTurn回合数获得无实体
    public int getPowerTurn;
    //每energTurn回合数获得energyAmount点能量
    public int energTurn;
    public int energyAmount;
    //当前回合数
    public int powerCount;
    public int energyCount;
    public CrownslayerPower(AbstractCreature owner,int getPowerTurn,int energTurn,int energyAmount){
        this.owner=owner;
        this.amount=-1;
        this.getPowerTurn=getPowerTurn;
        this.energTurn=energTurn;
        this.energyAmount=energyAmount;
        this.powerCount=0;
        this.energyCount=0;

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
        this.description=
                DESCRIPTIONS[0]+(getPowerTurn-powerCount)+DESCRIPTIONS[1]+(energTurn-energyCount)+DESCRIPTIONS[2]+energyAmount+DESCRIPTIONS[3];
    }

    @Override
    public void atStartOfTurnPostDraw() {
        this.powerCount++;
        this.energyCount++;
        if (this.powerCount == this.getPowerTurn){
            this.powerCount=0;
            this.addToBot(new ApplyPowerAction(owner,owner,new IntangiblePower(owner,1)));
        }
        if (this.energyCount == this.energTurn){
            this.energyCount=0;
            this.addToBot(new GainEnergyAction(this.energyAmount));
        }
    }
}
