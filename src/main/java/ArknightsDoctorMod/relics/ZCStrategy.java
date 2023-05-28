package ArknightsDoctorMod.relics;

import ArknightsDoctorMod.helper.DoctorHelper;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;

public class ZCStrategy extends CustomRelic {

    public static final String ID = DoctorHelper.MakePath("ZCStrategy");
    private static final String IMG = DoctorHelper.MakeAssetPath("img/relics/test.png");
    // 遗物类型
    private static final RelicTier RELIC_TIER = RelicTier.RARE;
    // 点击音效
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    private int count=0;

    public ZCStrategy() {
        super(ID, ImageMaster.loadImage(IMG), RELIC_TIER, LANDING_SOUND);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    //使用卡牌触发，抽一回一
    public void onUseCard(final AbstractCard targetCard, final UseCardAction useCardAction) {
        if (count <2 ){
            this.flash();
            count++;
            this.addToBot(new DrawCardAction(AbstractDungeon.player, 1));
            this.addToBot(new GainEnergyAction(1));
        }
    }

}
