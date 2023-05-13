package ArknightsDoctorMod.characters;


import ArknightsDoctorMod.helper.DoctorHelper;


import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;

import java.util.ArrayList;


public class Doctor extends CustomPlayer {

    // 火堆的人物立绘（行动前）
    private static final String MY_CHARACTER_SHOULDER_1 = DoctorHelper.getResourcePath()+"/img/char/shoulder.png";
    // 火堆的人物立绘（行动后）
    private static final String MY_CHARACTER_SHOULDER_2 = DoctorHelper.getResourcePath()+"/img/char/shoulder.png";
    // 人物死亡图像
    private static final String CORPSE_IMAGE = DoctorHelper.getResourcePath()+"/img/char/corpse.png";
    // 战斗界面左下角能量图标的每个图层
    private static final String[] ORB_TEXTURES = new String[]{
            DoctorHelper.getResourcePath()+"img/UI/orb/layer5.png",
            DoctorHelper.getResourcePath()+"img/UI/orb/layer4.png",
            DoctorHelper.getResourcePath()+"img/UI/orb/layer3.png",
            DoctorHelper.getResourcePath()+"img/UI/orb/layer2.png",
            DoctorHelper.getResourcePath()+"img/UI/orb/layer1.png",
            DoctorHelper.getResourcePath()+"img/UI/orb/layer6.png",
            DoctorHelper.getResourcePath()+"img/UI/orb/layer5d.png",
            DoctorHelper.getResourcePath()+"img/UI/orb/layer4d.png",
            DoctorHelper.getResourcePath()+"img/UI/orb/layer3d.png",
            DoctorHelper.getResourcePath()+"img/UI/orb/layer2d.png",
            DoctorHelper.getResourcePath()+"img/UI/orb/layer1d.png"
    };

    private static final float[] LAYER_SPEED = new float[]{-40.0F, -32.0F, 20.0F, -20.0F, 0.0F, -10.0F, -8.0F, 5.0F, -5.0F, 0.0F};

    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString("ArknightsDoctorMod:Doctor");


    public Doctor(String name){
        super(name,Enums.DOCTOR_PLAYER ,ORB_TEXTURES,DoctorHelper.getResourcePath()+"img/UI/orb/vfx.png", LAYER_SPEED, null, null);
        // 人物对话气泡的大小，如果游戏中尺寸不对在这里修改（libgdx的坐标轴左下为原点）
        this.dialogX = (this.drawX + 0.0F * Settings.scale);
        this.dialogY = (this.drawY + 150.0F * Settings.scale);


        // 初始化你的人物，如果你的人物只有一张图，那么第一个参数填写你人物图片的路径。
        this.initializeClass(
                DoctorHelper.RESOURCEPATH+"img/char/character.png", // 人物图片
                MY_CHARACTER_SHOULDER_2, MY_CHARACTER_SHOULDER_1,
                CORPSE_IMAGE, // 人物死亡图像
                this.getLoadout(),
                0.0F, 0.0F,
                200.0F, 220.0F, // 人物碰撞箱大小，越大的人物模型这个越大
                new EnergyManager(2) // 初始每回合的能量
        );

    }


    @Override
    public ArrayList<String> getStartingDeck() {
        return null;
    }

    @Override
    public ArrayList<String> getStartingRelics() {
        return null;
    }

    @Override
    public CharSelectInfo getLoadout() {
        return null;
    }

    @Override
    public String getTitle(PlayerClass playerClass) {
        return null;
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return null;
    }

    @Override
    public Color getCardRenderColor() {
        return null;
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return null;
    }

    @Override
    public Color getCardTrailColor() {
        return null;
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 0;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return null;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {

    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return null;
    }

    @Override
    public String getLocalizedCharacterName() {
        return null;
    }

    @Override
    public AbstractPlayer newInstance() {
        return null;
    }

    @Override
    public String getSpireHeartText() {
        return null;
    }

    @Override
    public Color getSlashAttackColor() {
        return null;
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[0];
    }

    @Override
    public String getVampireText() {
        return null;
    }

    public static class Enums {
        @SpireEnum
        public static PlayerClass DOCTOR_PLAYER;

        @SpireEnum(name = "ArknightsDoctor_COLOR")
        public static AbstractCard.CardColor EXAMPLE_CARD;

        @SpireEnum(name = "ArknightsDoctor_COLOR")
        public static CardLibrary.LibraryType EXAMPLE_LIBRARY;
    }
}
