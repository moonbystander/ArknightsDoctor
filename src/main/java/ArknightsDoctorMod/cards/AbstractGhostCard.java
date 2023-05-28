package ArknightsDoctorMod.cards;

import ArknightsDoctorMod.characters.Doctor;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public abstract class AbstractGhostCard extends CustomCard {

    public int useTrust=10;

    public AbstractGhostCard(String id, String name, String img, String rawDescription, CardType type,
                             CardRarity rarity, CardTarget target) {
        super(id, name, img, -2, rawDescription, type, Doctor.Enums.DOCTOR_CARD, rarity, target);
    }

    public AbstractGhostCard(String id, String name, String img, String rawDescription, CardType type,
                             CardRarity rarity, CardTarget target,int useTrust){
        this(id, name, img, rawDescription, type, rarity, target);
        this.useTrust=useTrust;
    }

    @Override
    public boolean cardPlayable(AbstractMonster m) {
        return super.cardPlayable(m) && ((Doctor)AbstractDungeon.player).trust>=useTrust;
    }
}
