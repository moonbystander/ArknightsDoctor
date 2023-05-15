package ArknightsDoctorMod.patches;



//修改CardGroup类中的moveToDiscardPile方法，使卡牌类型为Doctor.Enums.OPERATORS的卡牌，在使用后进入等待区
//

import ArknightsDoctorMod.characters.Doctor;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


//所有干员牌使用后不进入弃牌堆，而是进入再部署牌堆
public class CardGroupMoveToDiscardPilePatch {

/*    @SpirePatch(
            clz = CardGroup.class,
            method = "moveToDiscardPile"
    )
    public static class useOperatorsCardPatch{
        @SpirePrefixPatch
        public static SpireReturn<Void> AddToWaiting(AbstractPlayer _inst, AbstractCard c){
            //如果卡牌类型为OPERATORS，进入"OperatorsWaiting"区域
            if (c.type == Doctor.Enums.OPERATORS ){
                //进入"OperatorsWaiting"牌堆
                Doctor d= (Doctor) AbstractDungeon.player;
                d.OperatorsWaiting.addToTop(c);
                return SpireReturn.Return();
            }else {
                return SpireReturn.Continue();
            }
        }
    }*/
}
