package ArknightsDoctorMod.powers;

import ArknightsDoctorMod.helper.DoctorHelper;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class SarkazLancerPower extends AbstractPower {

    public static String POWER_ID = DoctorHelper.MakePath("SarkazLancerPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public int damage;

    public SarkazLancerPower(AbstractCreature owner,int amount ,int damage){
        this.owner=owner;
        this.amount=amount;
        this.damage=damage;
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
        this.description=DESCRIPTIONS[0]+this.amount+DESCRIPTIONS[1]+this.damage+DESCRIPTIONS[2];
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        this.addToBot(new ReducePowerAction(this.owner,this.owner,this,1));
    }

    //移除时造成伤害
    @Override
    public void onRemove() {
        this.addToBot(new DamageAction(this.owner,
                new DamageInfo(AbstractDungeon.player,
                        this.damage, DamageInfo.DamageType.NORMAL),
                AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }
}
