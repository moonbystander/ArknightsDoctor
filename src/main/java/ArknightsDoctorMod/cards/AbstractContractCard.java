package ArknightsDoctorMod.cards;

import ArknightsDoctorMod.characters.Doctor;
import ArknightsDoctorMod.contracts.AbstractContract;
import ArknightsDoctorMod.contracts.AbstractContractsChain;
import ArknightsDoctorMod.contracts.ContractGroup;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class AbstractContractCard extends CustomCard {

    //所有合约卡都要重写makecopy
    public ContractGroup contractGroup=new ContractGroup();
//    public int damage;
//    public int block;
//    public int baseHp;
//    public int draw;

    public AbstractContractCard(String id, String name, String img, int cost, String rawDescription, CardType type,
                                 CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, Doctor.Enums.DOCTOR_CARD, rarity, target);
    }




    @Override
    public boolean canUpgrade() {
        return false;
    }

}
