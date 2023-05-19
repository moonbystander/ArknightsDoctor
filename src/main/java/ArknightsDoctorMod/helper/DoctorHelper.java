package ArknightsDoctorMod.helper;

import com.megacrit.cardcrawl.cards.AbstractCard.CardType;

public class DoctorHelper {
    public DoctorHelper() {
    }


    public static String RESOURCEPATH="ArknightsDoctorModResource/";
    public static String MODID="ArknightsDoctorMod";
    public static String MakePath(String id) {
        return MODID+":" + id;
    }

    public static String MakeAssetPath(String id) {
        return RESOURCEPATH + id;
    }

    public static String getResourcePath(){
        return RESOURCEPATH;
    }


    public static String GetTestImgPath(){
        return RESOURCEPATH+"img/cards/test.png";
    }

    public static String GetImgPath(String id, CardType TYPE) {
        if (TYPE == CardType.ATTACK) {
            return "img/cards/attack/" + id + ".png";
        } else if (TYPE == CardType.SKILL) {
            return "img/cards/skill/" + id + ".png";
        } else {
            return TYPE == CardType.POWER ? RESOURCEPATH+"img/cards/power/" + id + ".png" : RESOURCEPATH+"img/cards/" + id + ".png";
        }
    }

    public static String GetPowerString(String id) {
        return RESOURCEPATH+"img/powers/" + id + ".png";
    }

    public static String GetVfxString(String Type, String id) {
        return RESOURCEPATH+"img/vfx/" + Type + "/" + id + "/";
    }
}
