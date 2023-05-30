package ArknightsDoctorMod.characters;


import ArknightsDoctorMod.cards.attack.Strike;
import ArknightsDoctorMod.cards.operators.RhodesIslandOperators.Amiya;
import ArknightsDoctorMod.cards.skill.Defend;
import ArknightsDoctorMod.helper.DoctorHelper;


import ArknightsDoctorMod.modcore.ArknightsDoctorMod;
import ArknightsDoctorMod.relics.Laevatain;
import ArknightsDoctorMod.relics.OperatorRecords;
import ArknightsDoctorMod.relics.PRTS;
import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;

import java.util.ArrayList;


public class Doctor extends CustomPlayer {

    // 火堆的人物立绘（行动前） √
    private static final String MY_CHARACTER_SHOULDER_1 = DoctorHelper.getResourcePath()+"/img/char/shoulder.png";
    // 火堆的人物立绘（行动后） √
    private static final String MY_CHARACTER_SHOULDER_2 = DoctorHelper.getResourcePath()+"/img/char/shoulder.png";
    // 人物死亡图像 √
    private static final String CORPSE_IMAGE = DoctorHelper.getResourcePath()+"/img/char/corpse.png";
    // 战斗界面左下角能量图标的每个图层 √
    private static final String[] ORB_TEXTURES = new String[]{
            DoctorHelper.getResourcePath()+"img/UI/orb/layer5.png",
            DoctorHelper.getResourcePath()+"img/UI/orb/layer4.png",
            DoctorHelper.getResourcePath()+"img/UI/orb/layer3.png",
            DoctorHelper.getResourcePath()+"img/UI/orb/layer2.png",
            DoctorHelper.getResourcePath()+"img/UI/orb/layer1.png",
            DoctorHelper.getResourcePath()+"img/UI/orb/layer0.png",
            DoctorHelper.getResourcePath()+"img/UI/orb/layer5d.png",
            DoctorHelper.getResourcePath()+"img/UI/orb/layer4d.png",
            DoctorHelper.getResourcePath()+"img/UI/orb/layer3d.png",
            DoctorHelper.getResourcePath()+"img/UI/orb/layer2d.png",
            DoctorHelper.getResourcePath()+"img/UI/orb/layer1d.png"
    };
    private static final float[] LAYER_SPEED = new float[]{-40.0F, -32.0F, 20.0F, -20.0F, 0.0F, -10.0F, -8.0F, 5.0F, -5.0F, 0.0F};
    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString("ArknightsDoctorMod:Doctor");


    public CardGroup OperatorsWaiting=new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);;

    //Doctor机制：信赖度
    public int trust=0;
    public int increaseTrust(int amount){
        if (amount < 0 ){
            amount=0;
        }
        trust += amount;

        if (trust >200){
            trust = 200;
        }
        updateTrustText();
        return trust;
    }

    public int reduceTrust(int amount){
        if (amount < 0){
            amount = 0;
        }
        trust-=amount;
        if (trust < 0 ){
            trust=0;
        }
        updateTrustText();
        return trust;
    }

    public void updateTrustText(){
        if (this.hasRelic(OperatorRecords.ID)){
            OperatorRecords r= (OperatorRecords) this.getRelic(OperatorRecords.ID);
            r.setDescriptionAfterLoading(this.trust);
            if (!r.istrust200 && this.trust == 200){
                r.istrust200=true;
            }
        }
    }



    public Doctor(String name){
        super(name,Enums.DOCTOR_PLAYER ,ORB_TEXTURES,DoctorHelper.getResourcePath()+"img/UI/orb/vfx.png", LAYER_SPEED, null, null);
        // 人物对话气泡的大小，如果游戏中尺寸不对在这里修改（libgdx的坐标轴左下为原点）
        this.dialogX = (this.drawX + 0.0F * Settings.scale);
        this.dialogY = (this.drawY + 150.0F * Settings.scale);


        // 初始化你的人物，如果你的人物只有一张图，那么第一个参数填写你人物图片的路径。
        this.initializeClass(
                DoctorHelper.RESOURCEPATH+"img/char/doctor.png", // 人物图片
                MY_CHARACTER_SHOULDER_2, MY_CHARACTER_SHOULDER_1,
                CORPSE_IMAGE, // 人物死亡图像
                this.getLoadout(),
                0.0F, 0.0F,
                200.0F, 220.0F, // 人物碰撞箱大小，越大的人物模型这个越大
                new EnergyManager(2) // 初始每回合的能量
        );

    }


    //初始卡，4打击4防御，1阿米娅1凯尔希
    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        for(int x = 0; x<4; x++) {
            retVal.add(Strike.ID);
        }
        for(int x = 0; x<4; x++) {
            retVal.add(Defend.ID);
        }
        retVal.add(Amiya.ID);
        return retVal;
    }

    //初始遗物，莱瓦汀、PRTS
    @Override
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(PRTS.ID);
        retVal.add(Laevatain.ID);
        retVal.add(OperatorRecords.ID);
        return retVal;
    }

    //
    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(characterStrings.NAMES[0], characterStrings.TEXT[0], 65, 65, 0, 99, 5, this, this.getStartingRelics(), this.getStartingDeck(), false);
    }

    @Override
    public String getTitle(PlayerClass playerClass) {
        return characterStrings.NAMES[0];
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return Enums.DOCTOR_CARD;
    }

    //
    @Override
    public Color getCardRenderColor() {
        return ArknightsDoctorMod.GetCharColor();
    }


    //没牌
    @Override
    public AbstractCard getStartCardForEvent() {
        return new Strike();
    }

    @Override
    public Color getCardTrailColor() {
        return ArknightsDoctorMod.GetCharColor();
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 5;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontBlue;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, false);

    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_HEAVY";
    }

    @Override
    public String getLocalizedCharacterName() {
        return characterStrings.NAMES[0];
    }

    @Override
    public AbstractPlayer newInstance() {
        return new Doctor(this.name);
    }

    @Override
    public String getSpireHeartText() {
        return characterStrings.TEXT[1];
    }

    @Override
    public Color getSlashAttackColor() {
        return ArknightsDoctorMod.GetCharColor();
    }

    //打击心脏时的特效
    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL, AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL};
    }

    @Override
    public String getVampireText() {
        return Vampires.DESCRIPTIONS[0];
    }

    public static class Enums {

        @SpireEnum(name = "ArknightsDoctorMod:OPERATORS")
        public static AbstractCard.CardTags OPERATORS;

        @SpireEnum(name = "ArknightsDoctorMod:OPERATORSKILL")
        public static AbstractCard.CardTags OPERATORSKILL;

        @SpireEnum
        public static PlayerClass DOCTOR_PLAYER;

        @SpireEnum(name = "ArknightsDoctor_COLOR")
        public static AbstractCard.CardColor DOCTOR_CARD;

        @SpireEnum(name = "ArknightsDoctor_COLOR")
        public static CardLibrary.LibraryType DOCTOR_LIBRARY;
    }
}
