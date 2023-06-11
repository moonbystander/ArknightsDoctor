package ArknightsDoctorMod.powers;

import ArknightsDoctorMod.helper.DoctorHelper;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ActiveOriginiumPower extends AbstractPower {

    public static String POWER_ID = DoctorHelper.MakePath("ActiveOriginiumPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    int draw;

    public ActiveOriginiumPower(AbstractCreature owner){
        this.owner=owner;
        this.amount=-1;
        this.ID=POWER_ID;
        this.name=NAME;
        this.type=PowerType.BUFF;
        this.draw=1;

        String path128 = DoctorHelper.getResourcePath()+"img/powers/test84.png";
        String path48 = DoctorHelper.getResourcePath()+"img/powers/test32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0]+this.draw+DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurn() {
        this.addToBot(new DamageAction(owner,new DamageInfo(owner,5,
                DamageInfo.DamageType.HP_LOSS)));
        this.addToBot(new DrawCardAction(owner,this.draw));
    }
}
