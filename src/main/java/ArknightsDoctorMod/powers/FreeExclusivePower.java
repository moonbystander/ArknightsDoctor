package ArknightsDoctorMod.powers;

import ArknightsDoctorMod.cards.AbstractOperatorsExclusiveCard;
import ArknightsDoctorMod.helper.DoctorHelper;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class FreeExclusivePower extends AbstractPower {

    // 能力的ID
    public static String POWER_ID = DoctorHelper.MakePath("FreeExclusivePower");
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public FreeExclusivePower(AbstractCreature owner,int amount){
        this.amount=amount;
        this.name = NAME;
        this.ID = POWER_ID;
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
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card instanceof AbstractOperatorsExclusiveCard && !card.purgeOnUse && this.amount > 0) {
            flash();
            card.setCostForTurn(0);
            this.amount--;
            if (this.amount == 0) {
                this.addToTop(
                        new RemoveSpecificPowerAction(
                                this.owner, this.owner,
                                this.ID)
                );
            }
        }
    }

    //回合结束时移除此效果
    @Override
    public void atEndOfTurn(boolean isPlayer) {
        this.addToTop(
                new RemoveSpecificPowerAction(
                        this.owner, this.owner,
                        this.ID)
        );
    }



}
