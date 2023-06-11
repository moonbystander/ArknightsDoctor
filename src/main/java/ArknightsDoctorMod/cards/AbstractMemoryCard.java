package ArknightsDoctorMod.cards;

import ArknightsDoctorMod.actions.GetTrustAction;
import ArknightsDoctorMod.actions.LossTrustAction;
import ArknightsDoctorMod.characters.Doctor;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public abstract class AbstractMemoryCard extends CustomCard {


    public int trustamount=1;

    public AbstractMemoryCard(String id, String name, String img, int cost, String rawDescription,CardType type,CardRarity rarity ,CardTarget target){
        super(id,name,img,cost,rawDescription,type, Doctor.Enums.DOCTOR_CARD,rarity,target);
    }

    public AbstractMemoryCard(String id, String name, String img, int cost, String rawDescription,CardType type,CardRarity rarity ,CardTarget target,int trustamount){
        super(id,name,img,cost,rawDescription,type, Doctor.Enums.DOCTOR_CARD,rarity,target);
        this.trustamount=trustamount;
    }



    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (trustamount>0){
            this.addToBot(new GetTrustAction(abstractPlayer,this.trustamount));
        }else if (trustamount<0){
            this.addToBot(new LossTrustAction(abstractPlayer,this.trustamount));
        }
    }
}
