package ArknightsDoctorMod.powers;

import ArknightsDoctorMod.cards.AbstractOperatorsCard;
import ArknightsDoctorMod.cards.AbstractOperatorsExclusiveCard;
import ArknightsDoctorMod.helper.DoctorHelper;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class PreparedPower extends AbstractPower {

    public static String POWER_ID = DoctorHelper.MakePath("PreparedPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public int count;
    public int need;

    public PreparedPower(AbstractCreature owner,int need){
        this.owner=owner;
        this.amount=-1;
        this.count=0;
        this.need=need;
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
        this.description = DESCRIPTIONS[0]+(this.need-this.count)+DESCRIPTIONS[1];
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (count < need &&card instanceof AbstractOperatorsCard){
            count++;
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (count == need){
            for (AbstractCard card: AbstractDungeon.player.hand.group) {
                if (card instanceof AbstractOperatorsExclusiveCard){
                    ((AbstractOperatorsExclusiveCard)card).onRetained();
                }
            }
        }
        count=0;
    }
}
