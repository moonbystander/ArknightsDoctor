package ArknightsDoctorMod.relics;

import ArknightsDoctorMod.characters.Doctor;
import ArknightsDoctorMod.helper.DoctorHelper;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;

public class OperatorRecords extends CustomRelic {
    public static final String ID = DoctorHelper.MakePath("OperatorRecords");
    private static final String IMG = DoctorHelper.MakeAssetPath("img/relics/test.png");
    // 遗物类型
    private static final RelicTier RELIC_TIER = RelicTier.STARTER;
    // 点击音效
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public OperatorRecords() {
        super(ID, ImageMaster.loadImage(IMG), RELIC_TIER, LANDING_SOUND);
        setDescriptionAfterLoading();
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void setDescriptionAfterLoading() {
        this.description = getUpdatedDescription();
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.tips.add(new PowerTip(this.DESCRIPTIONS[1], ""+((Doctor)AbstractDungeon.player).trust));
        initializeTips();
    }




}
