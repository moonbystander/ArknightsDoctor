package ArknightsDoctorMod.cards.operatorCards;

import ArknightsDoctorMod.cards.AbstractOperatorsSkillCard;
import ArknightsDoctorMod.cards.operators.Red;
import ArknightsDoctorMod.helper.DoctorHelper;
import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

//狼群，击晕所有怪物
public class WolfPack extends AbstractOperatorsSkillCard {

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(DoctorHelper.MakePath("WolfPack"));
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 2;
    public static final String ID = DoctorHelper.MakePath("WolfPack");



    public WolfPack(){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),COST,DESCRIPTION,CardType.ATTACK,CardTarget.ALL_ENEMY,Red.ID);
        this.baseDamage=4;
        this.isMultiDamage=true;
    }


    //增伤减费
    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeName();
            this.upgradeDamage(4);
            this.updateCost(1);
        }
    }


    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        //群体伤害
        this.addToBot(new DamageAllEnemiesAction(abstractPlayer,this.multiDamage,this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

        //击晕所有怪物
        for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters){
            this.addToBot(new StunMonsterAction(mo, abstractPlayer, 1));
        }
        //使用后退场
        super.makeOperatorRetreat(abstractPlayer);
    }
}
