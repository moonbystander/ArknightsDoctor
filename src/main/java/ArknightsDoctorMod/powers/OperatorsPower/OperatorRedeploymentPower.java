package ArknightsDoctorMod.powers.OperatorsPower;

import ArknightsDoctorMod.cards.AbstractOperatorsCard;
import ArknightsDoctorMod.helper.DoctorHelper;
import ArknightsDoctorMod.powers.AbstractOperatorRedeploymentPower;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class OperatorRedeploymentPower extends AbstractOperatorRedeploymentPower {

    public static String POWER_ID = DoctorHelper.MakePath("OperatorPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    //传进来的card.powerID记得要带“ArknightsDoctorMod:”前缀
    public OperatorRedeploymentPower(AbstractCreature owner, int amount, AbstractOperatorsCard card) {
        super(owner, amount, card);
        this.ID = card.powerId;
        this.name = card.name;
        this.type = PowerType.BUFF;
        String path128 = DoctorHelper.getResourcePath()+"img/powers/test84.png";
        String path48 = DoctorHelper.getResourcePath()+"img/powers/test32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
    }

    public OperatorRedeploymentPower(AbstractCreature owner, int amount, AbstractOperatorsCard card,String path128,String path48){
        super(owner, amount, card);
        this.ID = card.powerId;
        this.name = card.name;
        this.type = PowerType.BUFF;
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = this.name+DESCRIPTIONS[0]+this.amount+DESCRIPTIONS[1];
    }

}
