package egg.eggmod;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;

public class EggOre  extends Block {

	private Random random = new Random();

	public EggOre() {
		super(Material.rock);
		this.setCreativeTab(egg.tabEgg);
		this.setHardness(4.0F);
		this.setResistance(5.0F);
		this.setStepSound(Block.soundTypeStone);
		this.setHarvestLevel("pickaxe", 2);
	}

	@Override
	public Item getItemDropped(int meta, Random random, int fortune) {
		return egg.egg_ingot;
	}

	@Override
	public int quantityDroppedWithBonus(int fortune, Random random) {
		if (fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped(0, random, fortune)) {
			int i = random.nextInt(fortune + 2) - 1;
			if (i < 0) {
				i = 0;
			}
			return this.quantityDropped(random) * (i + 1);
		} else {
			return this.quantityDropped(random);
		}
	}

	@Override
	public int getExpDrop(IBlockAccess iBlockAccess, int meta, int fortune) {
		return MathHelper.getRandomIntegerInRange(random, 3, 7);
	}
}