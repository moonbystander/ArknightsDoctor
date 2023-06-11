package ArknightsDoctorMod.cards.attack;

import ArknightsDoctorMod.actions.WindmillAction;
import ArknightsDoctorMod.cards.AbstractMemoryCard;
import ArknightsDoctorMod.helper.DoctorHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Windmill extends AbstractMemoryCard {

    public static final String ID = DoctorHelper.MakePath("Windmill");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 2;

    public Windmill(){
        super(ID,NAME,DoctorHelper.GetTestImgPath(),COST,DESCRIPTION,CardType.ATTACK,CardRarity.UNCOMMON,
                CardTarget.ALL_ENEMY,3);
        this.baseDamage=5;
        this.baseMagicNumber=this.magicNumber=3;
    }


    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        super.use(abstractPlayer, abstractMonster);
        for (int i = 0; i < this.baseMagicNumber; i++) {
            AbstractMonster randomMonster = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
            if (randomMonster!=null){
                this.addToBot(new DamageAction(randomMonster,
                        new DamageInfo(abstractPlayer,
                                this.damage, this.damageTypeForTurn),
                        AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            }
        }
        this.addToBot(new WindmillAction());


    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
    }
}
