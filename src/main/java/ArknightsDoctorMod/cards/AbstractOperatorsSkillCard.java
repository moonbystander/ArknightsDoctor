package ArknightsDoctorMod.cards;

import ArknightsDoctorMod.actions.OperatorSkillCardRetainAction;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public abstract class AbstractOperatorsSkillCard extends CustomCard {


    //自带效果：消耗、保留，每保留一回合能耗减一
    public AbstractOperatorsSkillCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
        this.exhaust=true;
        this.retain=true;
    }

    @Override
    public void triggerOnEndOfPlayerTurn() {
        this.addToBot(new OperatorSkillCardRetainAction(this, AbstractDungeon.player.hand));
    }



}
