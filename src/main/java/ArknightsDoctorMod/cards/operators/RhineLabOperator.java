package ArknightsDoctorMod.cards.operators;

import ArknightsDoctorMod.cards.AbstractOperatorsCard;
import ArknightsDoctorMod.powers.RepeatedlyPlayedPower;
import ArknightsDoctorMod.powers.RhineLabPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public abstract class RhineLabOperator extends AbstractOperatorsCard {

    public RhineLabOperator(String id, String name, String img, String rawDescription, CardRarity rarity,
                                String powerId){
        super(id, name, img, rawDescription, rarity, powerId);
    }

    public RhineLabOperator(String id, String name, String img, String rawDescription, CardRarity rarity,
                                String powerId, int redeployment) {
        super(id, name, img, rawDescription, rarity, powerId, redeployment);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        super.use(abstractPlayer, abstractMonster);
        this.addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new RhineLabPower(abstractPlayer,1)));
    }

}
