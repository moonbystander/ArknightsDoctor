package ArknightsDoctorMod.cards.operators;

import ArknightsDoctorMod.actions.AbyssalHuntersAction;
import ArknightsDoctorMod.cards.AbstractOperatorsCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public abstract class AbyssalHuntersOperator extends AbstractOperatorsCard {

    public AbyssalHuntersOperator(String id, String name, String img, String rawDescription, CardRarity rarity,
                            String powerId){
        super(id, name, img, rawDescription, rarity, powerId);
    }

    public AbyssalHuntersOperator(String id, String name, String img, String rawDescription, CardRarity rarity,
                            String powerId, int redeployment) {
        super(id, name, img, rawDescription, rarity, powerId, redeployment);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        super.use(abstractPlayer, abstractMonster);
        this.addToBot(new AbyssalHuntersAction());
    }

}
