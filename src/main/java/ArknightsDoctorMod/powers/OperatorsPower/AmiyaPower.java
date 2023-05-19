package ArknightsDoctorMod.powers.OperatorsPower;

import ArknightsDoctorMod.cards.AbstractOperatorsCard;
import ArknightsDoctorMod.helper.DoctorHelper;
import ArknightsDoctorMod.powers.AbstractOperatorRedeploymentPower;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class AmiyaPower extends AbstractOperatorRedeploymentPower {
    // 能力的ID
    public static String POWER_ID = DoctorHelper.MakePath("AmyPower");
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public AmiyaPower(AbstractCreature owner, int amount, AbstractOperatorsCard card) {
        super(owner, amount, card);
        this.ID = POWER_ID;
        this.name = NAME;
        this.type = PowerType.BUFF;
        String path128 = DoctorHelper.getResourcePath()+"img/powers/test84.png";
        String path48 = DoctorHelper.getResourcePath()+"img/powers/test32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
    }


    @Override
    public void updateDescription() {
        this.description = this.name+DESCRIPTIONS[0]+this.amount+DESCRIPTIONS[1];
    }


}
