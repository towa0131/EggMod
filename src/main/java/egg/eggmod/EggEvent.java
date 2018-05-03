package egg.eggmod;

import net.minecraft.init.Items;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EggEvent {
	@SubscribeEvent
	public void EntityItemPickUpEvent(EntityItemPickupEvent e) {
		if (e.item.getEntityItem().getItem() == Items.egg) {
			e.entityPlayer.triggerAchievement(egg.starteggmod);
		}
	}
}
