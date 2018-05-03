package egg.eggmod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class EggTab extends CreativeTabs {

	public EggTab(String label) {
		super(label);
	}

	@Override
	public Item getTabIconItem() {
		return Items.egg;
	}
}
