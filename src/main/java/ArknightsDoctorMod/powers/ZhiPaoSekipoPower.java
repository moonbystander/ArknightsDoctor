package ArknightsDoctorMod.powers;

import ArknightsDoctorMod.actions.GetTrustAction;
import ArknightsDoctorMod.cards.AbstractOperatorsCard;
import ArknightsDoctorMod.cards.AbstractOperatorsExclusiveCard;
import ArknightsDoctorMod.cards.operators.RhodesIslandOperator;
import ArknightsDoctorMod.helper.DoctorHelper;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ZhiPaoSekipoPower extends AbstractPower {
    public static String POWER_ID = DoctorHelper.MakePath("ZhiPaoSekipoPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public int count;
    public int resetCount;

    public ZhiPaoSekipoPower(AbstractCreature owner,int resetCount){
        this.owner=owner;
        this.amount=-1;
        this.resetCount=resetCount;
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
        this.description = DESCRIPTIONS[0]+(this.resetCount-this.count)+DESCRIPTIONS[1];
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card instanceof AbstractOperatorsCard){
            this.count++;
            if (this.count==this.resetCount){
                this.count=0;
                for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters){
                    this.addToBot(new StunMonsterAction(mo, owner, 1));
                }
            }
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        count=0;
    }
}
