package ArknightsDoctorMod.powers;

import ArknightsDoctorMod.helper.DoctorHelper;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;

import java.util.ArrayList;

public class RhineLabPower extends AbstractPower {

    public static String POWER_ID = DoctorHelper.MakePath("RhineLabPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    public RhineLabPower(AbstractCreature owner,int amount){
        this.owner=owner;
        this.amount=amount;
        this.ID=POWER_ID;
        this.name=NAME;
        this.type=PowerType.BUFF;

        String path128 = DoctorHelper.getResourcePath()+"img/powers/test84.png";
        String path48 = DoctorHelper.getResourcePath()+"img/powers/test32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description=DESCRIPTIONS[0];
    }

    @Override
    public void onVictory() {
        if (this.amount < 10 ){
            return;
        }
        //升级一张卡组中的卡
        randomDeckUpgrade();
        //再升级一张卡组中的卡，获得5点生命上限
        if (this.amount >= 25){
            randomDeckUpgrade();
            this.owner.increaseMaxHp(5,true);
        }
        //获得一件随机遗物
        if (this.amount >= 50){
            AbstractDungeon.getCurrRoom().addRelicToRewards(GetRandomTier());
        }
    }

    private AbstractRelic.RelicTier GetRandomTier() {
        float randNum = (new Random()).random(0.0F, 1.0F);
        if (randNum <= 0.125F)
            return AbstractRelic.RelicTier.RARE;
        if (randNum <= 0.5F)
            return AbstractRelic.RelicTier.UNCOMMON;
        return AbstractRelic.RelicTier.COMMON;
    }

    //来自紫音mod
    public void randomDeckUpgrade() {
        ArrayList<AbstractCard> possibleCards = new ArrayList<>();
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.canUpgrade())
                possibleCards.add(c);
        }
        AbstractCard theCard = null;
        if (!possibleCards.isEmpty()) {
            theCard = possibleCards.get(AbstractDungeon.miscRng.random(0, possibleCards.size() - 1));
            theCard.upgrade();
            AbstractDungeon.player.bottledCardUpgradeCheck(theCard);
        }
        if (theCard != null) {
            AbstractDungeon.effectsQueue.add(new UpgradeShineEffect(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
            AbstractDungeon.topLevelEffectsQueue.add(new ShowCardBrieflyEffect(theCard.makeStatEquivalentCopy()));
            addToTop(new WaitAction(Settings.ACTION_DUR_MED));
        }
    }
}
