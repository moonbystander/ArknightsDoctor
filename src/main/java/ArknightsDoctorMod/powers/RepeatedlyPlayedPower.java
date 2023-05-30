package ArknightsDoctorMod.powers;

import ArknightsDoctorMod.actions.GetTrustAction;
import ArknightsDoctorMod.cards.AbstractOperatorsExclusiveCard;
import ArknightsDoctorMod.cards.operators.RhodesIslandOperator;
import ArknightsDoctorMod.helper.DoctorHelper;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class RepeatedlyPlayedPower extends AbstractPower {

    public static String POWER_ID = DoctorHelper.MakePath("RepeatedlyPlayedPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public RepeatedlyPlayedPower(AbstractCreature owner){
        this.name = NAME;
        this.ID = POWER_ID;
        this.type=PowerType.BUFF;
        this.owner=owner;
        this.amount=1;

        String path128 = DoctorHelper.getResourcePath()+"img/powers/test84.png";
        String path48 = DoctorHelper.getResourcePath()+"img/powers/test32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
    }


    @Override
    public void updateDescription(){
        this.description=DESCRIPTIONS[0];
    }


    //直接使用数据类型进行判定，是罗德岛干员牌增加信赖，是专属牌不打断，非罗德岛干员和专属牌，移除该Power
    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card instanceof RhodesIslandOperator){
            this.addToBot(new GetTrustAction(owner,this.amount-1));
        }else if (!(card instanceof AbstractOperatorsExclusiveCard)){
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this));
    }
}
