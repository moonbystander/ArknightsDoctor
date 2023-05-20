package ArknightsDoctorMod.relics;

import ArknightsDoctorMod.actions.AddMaxHpAction;
import ArknightsDoctorMod.helper.DoctorHelper;
import ArknightsDoctorMod.powers.LaevatainPower;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.RegenAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Laevatain extends CustomRelic implements ClickableRelic {
    public static final String ID = DoctorHelper.MakePath("Laevatain");
    private static final String IMG = DoctorHelper.MakeAssetPath("img/relics/test.png");
    // 遗物类型
    private static final RelicTier RELIC_TIER = RelicTier.STARTER;
    // 点击音效
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    //TODO：重写abstructRelic的playLandingSFX播放音频，为SoundMasterd的构造函数写一个patch加载声音

    public Laevatain() {
        super(ID, ImageMaster.loadImage(IMG), RELIC_TIER, LANDING_SOUND);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }


    //首次右击时生效，生命上限增加500,恢复所有生命，添加能力 “莱万汀”
    @Override
    public void onRightClick() {
        if (!AbstractDungeon.player.hasPower(LaevatainPower.POWER_ID)){
            this.addToBot(new AddMaxHpAction(AbstractDungeon.player,500));
            //偷懒直接用了再生的action，如果使用莱万汀时身上有再生，会导致再生层数-1
            this.addToBot(new RegenAction(AbstractDungeon.player,AbstractDungeon.player.maxHealth));
            //添加能力：莱万汀
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new LaevatainPower(AbstractDungeon.player)));
        }
    }
}
