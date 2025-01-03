package lotr.common.entity.npc;

import lotr.common.LOTRAchievement;
import lotr.common.LOTRCapes;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.world.spawning.LOTRInvasions;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTREntityHarnedorWarlord extends LOTREntityHarnedorWarrior implements LOTRUnitTradeable {
	public LOTREntityHarnedorWarlord(World world) {
		super(world);
		addTargetTasks(false);
		npcCape = LOTRCapes.NEAR_HARAD;
	}

	@Override
	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(25.0);
	}

	@Override
	public boolean canTradeWith(EntityPlayer entityplayer) {
		return LOTRLevelData.getData(entityplayer).getAlignment(getFaction()) >= 150.0f && isFriendly(entityplayer);
	}

	@Override
	public float getAlignmentBonus() {
		return 5.0f;
	}

	@Override
	public String getSpeechBank(EntityPlayer entityplayer) {
		if (isFriendly(entityplayer)) {
			if (canTradeWith(entityplayer)) {
				return "nearHarad/harnennor/warlord/friendly";
			}
			return "nearHarad/harnennor/warlord/neutral";
		}
		return "nearHarad/harnennor/warrior/hostile";
	}

	@Override
	public LOTRUnitTradeEntries getUnits() {
		return LOTRUnitTradeEntries.HARNEDOR_WARLORD;
	}

	@Override
	public LOTRInvasions getWarhorn() {
		return LOTRInvasions.NEAR_HARAD_HARNEDOR;
	}

	@Override
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
		data = super.onSpawnWithEgg(data);
		npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.pikeHarad));
		npcItemsInv.setIdleItem(npcItemsInv.getMeleeWeapon());
		setCurrentItemOrArmor(4, null);
		return data;
	}

	@Override
	public void onUnitTrade(EntityPlayer entityplayer) {
		LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.tradeHarnedorWarlord);
	}

	@Override
	public boolean shouldTraderRespawn() {
		return true;
	}
}
