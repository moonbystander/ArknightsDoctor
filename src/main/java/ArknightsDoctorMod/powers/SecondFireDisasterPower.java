package ArknightsDoctorMod.powers;

import ArknightsDoctorMod.actions.CardMoveAction;
import ArknightsDoctorMod.cards.states.SonyasNightmare;
import ArknightsDoctorMod.characters.Doctor;
import ArknightsDoctorMod.helper.DoctorHelper;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class SecondFireDisasterPower extends AbstractPower {
    public static String POWER_ID = DoctorHelper.MakePath("SecondFireDisasterPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public int count=2;

    public SecondFireDisasterPower(AbstractCreature owner,int amount){
        this.owner=owner;
        this.amount=amount;
        this.ID=POWER_ID;
        this.name=NAME;
        this.type=PowerType.DEBUFF;

        String path128 = DoctorHelper.getResourcePath()+"img/powers/test84.png";
        String path48 = DoctorHelper.getResourcePath()+"img/powers/test32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();

    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0]+this.count+DESCRIPTIONS[1];
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        this.addToBot(new DamageAllEnemiesAction((AbstractPlayer) this.owner, this.count, DamageInfo.DamageType.THORNS,
                AbstractGameAction.AttackEffect.NONE));
        this.count*=4;
        this.addToBot(new ReducePowerAction(owner,owner,this,1));
    }

    //大火结束时，为玩家增加3层饥饿并向弃牌堆增加三张索尼娅的噩梦
    @Override
    public void onRemove() {
        this.addToBot(new MakeTempCardInDiscardAction(new SonyasNightmare(),3));
        this.addToBot(new ApplyPowerAction(owner,owner,new IntolerableHungerPower(owner,3)));
    }
}
