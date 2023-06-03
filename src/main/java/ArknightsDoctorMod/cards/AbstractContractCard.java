package ArknightsDoctorMod.cards;

import ArknightsDoctorMod.characters.Doctor;
import ArknightsDoctorMod.contracts.AbstractContract;
import ArknightsDoctorMod.contracts.AbstractContractsChain;
import ArknightsDoctorMod.contracts.ContractGroup;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class AbstractContractCard extends CustomCard {

    public ContractGroup contractGroup=new ContractGroup();

    public AbstractContractCard(String id, String name, String img, int cost, String rawDescription, CardType type,
                                 CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, Doctor.Enums.DOCTOR_CARD, rarity, target);
    }


    @Override
    public boolean canUpgrade() {
        return false;
    }

    //默认调用有参构造方法进行复制
    //合约卡需要实现有参构造方法，将contract复制一份，具体参照高爆源石虫
    @Override
    public AbstractCard makeCopy() {
        try {
            return (AbstractCard) this.getClass().getConstructor(ContractGroup.class).newInstance(this.contractGroup);
        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException("BaseMod failed to auto-generate makeCopy for card: " + this.cardID);
        }

    }
}
