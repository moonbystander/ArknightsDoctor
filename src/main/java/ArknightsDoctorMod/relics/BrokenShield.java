package ArknightsDoctorMod.relics;

import ArknightsDoctorMod.helper.DoctorHelper;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;

public class BrokenShield extends CustomRelic {
    public static final String ID = DoctorHelper.MakePath("BrokenShield");
    private static final String IMG = DoctorHelper.MakeAssetPath("img/relics/test.png");
    // 遗物类型
    private static final RelicTier RELIC_TIER = RelicTier.SPECIAL;
    // 点击音效
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public int amount;

    public BrokenShield() {
        super(ID, ImageMaster.loadImage(IMG), RELIC_TIER, LANDING_SOUND);
        this.amount=1;
    }

    public int getAmount(){
        return this.amount;
    }

    public void setAmount(int amount){
        this.amount=amount;
    }

    @Override
    public void onEnergyRecharge(){
        this.flash();
        AbstractDungeon.player.gainEnergy(this.amount);
    }
}
