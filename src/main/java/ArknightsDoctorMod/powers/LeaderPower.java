package ArknightsDoctorMod.powers;

import ArknightsDoctorMod.helper.DoctorHelper;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.Iterator;

public class LeaderPower extends AbstractPower {

    public static String POWER_ID = DoctorHelper.MakePath("LeaderPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public int reduce;

    public LeaderPower(AbstractCreature owner,int reduce){
        this.amount=-1;
        this.reduce=reduce;
        this.ID=POWER_ID;
        this.name=NAME;
        this.owner = owner;
        this.type = PowerType.BUFF;
        String path128 = DoctorHelper.getResourcePath()+"img/powers/test84.png";
        String path48 = DoctorHelper.getResourcePath()+"img/powers/test32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0]+this.reduce+DESCRIPTIONS[1];
    }

    //回合开始时将再部署时长不小于reduce的减少1
    @Override
    public void atStartOfTurn() {
        Iterator it=owner.powers.iterator();
        while (it.hasNext()){
            AbstractPower p = (AbstractPower) it.next();
            if (p instanceof AbstractOperatorRedeploymentPower && p.amount >= reduce ){
                p.atEndOfTurn(true);
            }
        }
    }
}
