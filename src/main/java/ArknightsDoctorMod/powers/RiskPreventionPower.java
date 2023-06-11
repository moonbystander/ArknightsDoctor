package ArknightsDoctorMod.powers;

import ArknightsDoctorMod.cards.AbstractOperatorsExclusiveCard;
import ArknightsDoctorMod.helper.DoctorHelper;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class RiskPreventionPower extends AbstractPower {

    public static String POWER_ID = DoctorHelper.MakePath("RiskPreventionPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public int block;
    public RiskPreventionPower(AbstractCreature owner,int block){
        this.owner=owner;
        this.amount=-1;
        this.block=block;
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
    public void updateDescription() {
        this.description=DESCRIPTIONS[0]+this.block+DESCRIPTIONS[1];
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        int num=0;
        for (AbstractCard card: AbstractDungeon.player.hand.group) {
            if (card instanceof AbstractOperatorsExclusiveCard){
                num++;
            }
        }
        this.addToBot(new GainBlockAction(owner,num*this.block));
    }
}
