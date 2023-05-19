package ArknightsDoctorMod.cards.operatorsSkill;

import ArknightsDoctorMod.cards.AbstractOperatorsSkillCard;
import ArknightsDoctorMod.cards.operators.Amiya;
import ArknightsDoctorMod.helper.DoctorHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Chimera extends AbstractOperatorsSkillCard {



    private static final CardStrings cardStrings=CardCrawlGame.languagePack.getCardStrings(DoctorHelper.MakePath("Chimera"));
    public static final String NAME=cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static int COST = 2;
    public static final String ID = DoctorHelper.MakePath("Chimera");

    public Chimera(){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),COST,DESCRIPTION,CardTarget.ALL_ENEMY, Amiya.ID);
        this.baseDamage=20;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeName();
            this.updateCost(1);
            this.upgradeDamage(10);
        }
    }

    //使用后使干员退场
    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBot(new DamageAllEnemiesAction(abstractPlayer,this.baseDamage, DamageInfo.DamageType.HP_LOSS, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        super.makeOperatorRetreat(abstractPlayer);
    }
}
