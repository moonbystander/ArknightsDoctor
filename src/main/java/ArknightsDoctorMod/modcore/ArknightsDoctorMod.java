package ArknightsDoctorMod.modcore;

import ArknightsDoctorMod.cards.AbstractOperatorsCard;
import ArknightsDoctorMod.characters.Doctor;
import ArknightsDoctorMod.helper.DoctorHelper;
import ArknightsDoctorMod.relics.Laevatain;
import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.abstracts.CustomRelic;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import java.nio.charset.StandardCharsets;

@SpireInitializer
public class ArknightsDoctorMod implements EditCardsSubscriber, EditStringsSubscriber, EditCharactersSubscriber, EditRelicsSubscriber, EditKeywordsSubscriber {
//    private static final String MY_CHARACTER_BUTTON = DoctorHelper.RESOURCEPATH +"img/char/img.png";//√
    private static final String MY_CHARACTER_BUTTON = DoctorHelper.RESOURCEPATH +"img/char/button.png";//√
    private static final String MY_CHARACTER_PORTRAIT = DoctorHelper.RESOURCEPATH+"img/char/Doctor_Portrait.png";//√

    //目前使用莉莉丝素材
    private static final String BG_ATTACK_512 = DoctorHelper.RESOURCEPATH+"img/512/bg_attack_512.png";//√
    private static final String BG_POWER_512 = DoctorHelper.RESOURCEPATH+"img/512/bg_power_512.png";//√
    private static final String BG_SKILL_512 = DoctorHelper.RESOURCEPATH+"img/512/bg_skill_512.png";//√
    private static final String energy_orb = DoctorHelper.RESOURCEPATH+"img/512/SELESOrb.png";//√
    private static final String big_orb = DoctorHelper.RESOURCEPATH+"img/1024/SELESOrb.png";//√
    private static final String BG_ATTACK_1024 = DoctorHelper.RESOURCEPATH+"img/1024/bg_attack_1024.png";//√
    private static final String BG_POWER_1024 = DoctorHelper.RESOURCEPATH+"img/1024/bg_power_1024.png";//√
    private static final String BG_SKILL_1024 = DoctorHelper.RESOURCEPATH+"img/1024/bg_skill_1024.png";//√
    private static final String small_orb = DoctorHelper.RESOURCEPATH+"img/UI/energyOrb.png";//√

    private static final Color MY_COLOR = new Color(0.0F, 1.0F, 1.0F, 1.0F);

    public ArknightsDoctorMod(){
        BaseMod.subscribe(this);
        BaseMod.addColor(Doctor.Enums.DOCTOR_CARD, MY_COLOR, MY_COLOR, MY_COLOR, MY_COLOR, MY_COLOR, MY_COLOR, MY_COLOR, BG_ATTACK_512, BG_SKILL_512, BG_POWER_512, energy_orb, BG_ATTACK_1024, BG_SKILL_1024, BG_POWER_1024, big_orb, small_orb);
    }

    public static void initialize() {
        new ArknightsDoctorMod();
    }



    @Override
    public void receiveEditCards() {
        new AutoAdd("ArknightsDoctorMod")
                .packageFilter(AbstractOperatorsCard.class)
                .setDefaultSeen(true)
                .cards();
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new Doctor(CardCrawlGame.playerName), MY_CHARACTER_BUTTON, MY_CHARACTER_PORTRAIT, Doctor.Enums.DOCTOR_PLAYER);
    }

    //注册自定义关键字
    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String lang ;
        if (Settings.language == Settings.GameLanguage.ZHS) {
            lang = "ZHS";
        }else {
            lang = "ZHS";
        }

        String json = Gdx.files.internal(DoctorHelper.RESOURCEPATH+"localization/"+ lang+"/keywords.json")
                .readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = gson.fromJson(json, Keyword[].class);
        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword("ArknightsDoctorMod", keyword.NAMES[0], keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

    //注册遗物
    @Override
    public void receiveEditRelics() {
        //将MyRelic改为自己的遗物
        new AutoAdd("ArknightsDoctorMod")
                .packageFilter(Laevatain.class)
                .any(CustomRelic.class, (info, relic) -> {
                    BaseMod.addRelicToCustomPool(relic, Doctor.Enums.DOCTOR_CARD);
                    UnlockTracker.markRelicAsSeen(relic.relicId);
                });

    }

    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(CardStrings.class, DoctorHelper.RESOURCEPATH+"localization/ZHS" + "/cards.json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class, DoctorHelper.RESOURCEPATH+"localization/ZHS" + "/characters.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, DoctorHelper.RESOURCEPATH+"localization/ZHS" + "/relics.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, DoctorHelper.RESOURCEPATH+"localization/ZHS" + "/powers.json");
    }

    public static Color GetCharColor(){
        return MY_COLOR;
    }
}
