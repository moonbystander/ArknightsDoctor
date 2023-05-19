package ArknightsDoctorMod.cards.operatorsSkill;

import ArknightsDoctorMod.cards.AbstractOperatorsSkillCard;
import ArknightsDoctorMod.cards.operators.Amiya;
import ArknightsDoctorMod.helper.DoctorHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SpiritBurst extends AbstractOperatorsSkillCard {

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(DoctorHelper.MakePath("SpiritBurst"));
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    public static final String ID = DoctorHelper.MakePath("SpiritBurst");

    public SpiritBurst(){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),COST,DESCRIPTION,CardTarget.ENEMY, Amiya.ID);
        this.baseDamage=2;
        this.baseMagicNumber=this.magicNumber=6;
    }


    //
    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeName();
            this.upgradeDamage(1);
            this.upgradeMagicNumber(2);
        }
    }

    //造成伤害并添加晕眩到弃牌堆
    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        for (int i=0;i<this.magicNumber;i++){
            this.addToBot(new DamageAction(abstractMonster,new DamageInfo(abstractPlayer,this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }
        this.addToBot(new MakeTempCardInDiscardAction(new Dazed(),5));
    }
}
