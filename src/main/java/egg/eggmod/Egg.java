package egg.eggmod;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.stats.Achievement;
import net.minecraft.world.World;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "EggMod", name = "Egg Mod", version = "1.0.0")
public class Egg {

	public static Item superegg;
	public static Item cookedegg;
	public static Item egg_sword;
	public static Item egg_pickaxe;
	public static Item egg_ingot;
	public static Block egg_ore;
	public static ToolMaterial EGG_SUPER;
	public static ToolMaterial EGG_SWORD;
	public static ToolMaterial EGG_PICKAXE;
	public static CreativeTabs tabEgg = new EggTab("EggTab");
	public static Achievement starteggmod;
	public static Achievement getcookedegg;
	public static Achievement make_egg_sword;
	public static Achievement make_egg_pickaxe;
	public static Achievement get_egg_ore;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		EGG_SUPER = EnumHelper.addToolMaterial("SuperEgg", 3, 130, 8.0F, 15.0F, 10)
				.setRepairItem(new ItemStack(Items.egg));
		EGG_SWORD = EnumHelper.addToolMaterial("egg_sword", 3, 63, 8.0F, 6.0F, 30)
				.setRepairItem(new ItemStack(Items.egg));
		EGG_PICKAXE = EnumHelper.addToolMaterial("egg_pickaxe", 3, 78, 16.0F, 4.0F, 30)
				.setRepairItem(new ItemStack(Items.egg));
		superegg = new ItemSword(EGG_SUPER)
		.setCreativeTab(egg.tabEgg)
		.setUnlocalizedName("eggs_super")
		.setTextureName("eggmod:superegg");
		GameRegistry.registerItem(superegg, "eggs_super");

		egg_sword = new ItemSword(EGG_SWORD){
			public void onCreated(ItemStack stack, World world, EntityPlayer player) {
				player.triggerAchievement(egg.make_egg_sword);
				super.onCreated(stack, world, player);
			}
		}
		.setCreativeTab(egg.tabEgg)
		.setUnlocalizedName("egg_sword")
		.setTextureName("eggmod:egg_sword");
		GameRegistry.registerItem(egg_sword, "egg_sword");

		egg_pickaxe = new ItemPickaxe(EGG_PICKAXE){
			public void onCreated(ItemStack stack, World world, EntityPlayer player) {
				player.triggerAchievement(egg.make_egg_pickaxe);
				super.onCreated(stack, world, player);
			}
		}
		.setCreativeTab(egg.tabEgg)
		.setUnlocalizedName("egg_pickaxe")
		.setTextureName("eggmod:egg_pickaxe");
		GameRegistry.registerItem(egg_pickaxe, "egg_pickaxe");

		cookedegg = new ItemFood(6, 3.0F, false){
			public void onCreated(ItemStack stack, World world, EntityPlayer player) {
				player.triggerAchievement(egg.getcookedegg);
				super.onCreated(stack, world, player);
			}
		}
		.setPotionEffect(9, 15, 0, 0.1F)
		.setCreativeTab(egg.tabEgg)
		.setUnlocalizedName("cookedegg")
		.setTextureName("eggmod:cookedegg");
		GameRegistry.registerItem(cookedegg, "cookedegg");

		egg_ore = new EggOre()
		.setBlockName("egg_ore")
		.setBlockTextureName("eggmod:egg_ore");
		GameRegistry.registerBlock(egg_ore, "egg_ore");

		egg_ingot = new Item()
		.setCreativeTab(egg.tabEgg)
		.setUnlocalizedName("egg_ingot")
		.setTextureName("eggmod:egg_ingot");
		GameRegistry.registerItem(egg_ingot, "egg_ingot");

		this.starteggmod = new Achievement("starteggmod", "starteggmod", 0, 0,
			new ItemStack(Items.egg, 0), null);
		this.getcookedegg = new Achievement("getcookedegg", "getcookedegg", -1, -1,
			new ItemStack(egg.cookedegg, 0), this.starteggmod);
		this.make_egg_sword = new Achievement("make_egg_sword", "make_egg_sword", 1, 1,
			new ItemStack(egg.egg_sword, 0), this.starteggmod);
		this.make_egg_pickaxe = new Achievement("make_egg_pickaxe", "make_egg_pickaxe", 1, -1,
			new ItemStack(egg.egg_pickaxe, 0), this.starteggmod);

		AchievementPage.registerAchievementPage(new AchievementPage("EggMod", new Achievement[]{this.starteggmod, this.getcookedegg, this.make_egg_sword, this.make_egg_pickaxe}));
		MinecraftForge.EVENT_BUS.register(new EggEvent());
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		GameRegistry.addRecipe(new ItemStack(egg.superegg),
				"XZX", "ZYZ", "XZX", "X", Items.gold_ingot, "Y", Items.egg, "Z", Items.diamond);
		GameRegistry.addRecipe(new ItemStack(egg.egg_sword),
				"  Y", " Y ", "X  ", "X", Items.stick, "Y", Items.egg);
		GameRegistry.addRecipe(new ItemStack(egg.egg_pickaxe),
				"YYY", " X ", " X ", "X", Items.stick, "Y", Items.egg);
		GameRegistry.addSmelting(Items.egg, new ItemStack(egg.cookedegg), 0.3F);
		GameRegistry.registerWorldGenerator(new EggOreGenerator(), 0);
	}
}