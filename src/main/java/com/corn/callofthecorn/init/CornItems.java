package com.corn.callofthecorn.init;
import com.corn.callofthecorn.items.armour.CornArmourMaterials;
import com.corn.callofthecorn.items.CornToolTiers;
import com.corn.callofthecorn.items.armour.CornMetalArmourItem;
import com.corn.callofthecorn.items.armour.MazieriteArmourItem;
import com.corn.callofthecorn.items.armour.PumpCrown;
import com.corn.callofthecorn.items.curios.CrowsFoot;
import com.corn.callofthecorn.Main;
import com.corn.callofthecorn.items.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class CornItems {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MOD_ID);

	public static final RegistryObject<Item> CROWSFOOT = ITEMS.register("crowsfoot",() -> new CrowsFoot(defaultitemproperties().fireResistant()));

	public static final RegistryObject<Item> KERNAL = ITEMS.register("kernal",() -> new KernelItem(CornToolTiers.KERNALRITE,3,-2F,defaultitemproperties().fireResistant()));
	public static final RegistryObject<Item> CROWSTAFF = ITEMS.register("crowstaff", () -> new CustomStaffItem(defaultitemproperties().fireResistant(),4,false));
	public static final RegistryObject<Item> HARVESTSTAFF = ITEMS.register("harveststaff", () -> new CustomStaffItem(defaultitemproperties().fireResistant(),0,true));
	public static final RegistryObject<Item> PUMPKINGSCROWN = ITEMS.register("pumpkingscrown",() -> new PumpCrown(CornArmourMaterials.PUMPKIN, ArmorItem.Type.HELMET, defaultitemproperties()));
	public static final RegistryObject<Item> HARVESTERSCYTHE = ITEMS.register("harvesterscythe",() -> new HarvesterScytheItem(CornToolTiers.KERNALRITE,3,-2.4F,defaultitemproperties()));
	//public static final RegistryObject<Item> PITCHFORK = ITEMS.register("pitchfork",() -> new Pitchfork(defaultitemproperties()));


	public static final RegistryObject<Item> CORN = ITEMS.register("corn", () -> new Item(defaultitemproperties().food((new FoodProperties.Builder()).nutrition(1).saturationMod(0.5f).build())));
	public static final RegistryObject<Item> POPCORN = ITEMS.register("popcorn", () -> new Item(defaultitemproperties().food((new FoodProperties.Builder()).nutrition(5).saturationMod(0.8f).build())));
	public static final RegistryObject<Item> CREAMEDCORN = ITEMS.register("creamedcorn", () -> new CreamedCornItem(defaultitemproperties()));
	public static final RegistryObject<Item> FARMHANDEGG = ITEMS.register("farmhandegg",() ->  new BossSummonItem(CornMobs.FARMHAND::get, 9, 3.0, 8, 4, defaultitemproperties()));
	public static final RegistryObject<Item> HEWHOHARVESTSEGG = ITEMS.register("hewhoharvestsegg",() ->new BossSummonItem(CornMobs.HARVESTER::get, 16,4.0, 10, 5, defaultitemproperties()));
	public static final RegistryObject<Item> HARVESTEGG = ITEMS.register("harvestegg",() ->new AerialBossSummonItem(CornMobs.HARVEST::get, defaultitemproperties()));

	public static final RegistryObject<Item> LESSERSOUL = ITEMS.register("lessersoul",() -> new Item(defaultitemproperties().fireResistant()));
	public static final RegistryObject<Item> MILDSOUL = ITEMS.register("mildsoul",() -> new Item(defaultitemproperties().fireResistant()));
	public static final RegistryObject<Item> GREATERSOUL = ITEMS.register("greatersoul",() -> new Item(defaultitemproperties().fireResistant()));
	public static final RegistryObject<Item> CORNSOUL = ITEMS.register("cornsoul", () ->  new Item(defaultitemproperties().fireResistant()));


	public static final RegistryObject<Item> CORNSTAFF = ITEMS.register("cornstaff",() -> new CustomStaffItem(defaultitemproperties(),1,false));
	public static final RegistryObject<Item> CORNMETALBAR = ITEMS.register("cornmetalbar",() -> new Item(defaultitemproperties()));
	public static final RegistryObject<Item> CORNMETALPANTS = ITEMS.register("cornmetalpants",() -> new CornMetalArmourItem(CornArmourMaterials.CORNMETAL, ArmorItem.Type.LEGGINGS, defaultitemproperties()));
	public static final RegistryObject<Item> CORNMETALHEAD = ITEMS.register("cornmetalhead",() -> new CornMetalArmourItem(CornArmourMaterials.CORNMETAL, ArmorItem.Type.HELMET, defaultitemproperties()));
	public static final RegistryObject<Item> CORNMETALFEET = ITEMS.register("cornmetalfeet",() -> new CornMetalArmourItem(CornArmourMaterials.CORNMETAL, ArmorItem.Type.BOOTS, defaultitemproperties()));
	public static final RegistryObject<Item> CORNMETALCHEST = ITEMS.register("cornmetalchest",() -> new CornMetalArmourItem(CornArmourMaterials.CORNMETAL, ArmorItem.Type.CHESTPLATE,defaultitemproperties()));
	public static final RegistryObject<Item> CORNMETALAXE = ITEMS.register("cornmetalaxe",() -> new CustomAxeItem(CornToolTiers.CORNMETAL,5F,-2F,defaultitemproperties()));
	public static final RegistryObject<Item> CORNMETALPICK = ITEMS.register("cornmetalpick",() -> new CustomPickaxeItem(CornToolTiers.CORNMETAL,1,-2F,defaultitemproperties()));
	public static final RegistryObject<Item> CORNMETALHOE = ITEMS.register("cornmetalhoe",() -> new CustomHoeItem(CornToolTiers.CORNMETAL,-3,0F,defaultitemproperties()));
	public static final RegistryObject<Item> CORNMETALSWORD = ITEMS.register("cornmetalsword",() -> new CustomSwordItem(CornToolTiers.CORNMETAL,3,-1F,defaultitemproperties()));
	public static final RegistryObject<Item> CORNMETALSHOVEL = ITEMS.register("cornmetalshovel",() -> new CustomShovelItem(CornToolTiers.CORNMETAL,1.5F,-2F,defaultitemproperties()));
	public static final RegistryObject<Item> CORNMETALBOW = ITEMS.register("cornmetalbow",() -> new CustomBowItem(defaultitemproperties(),35,4,3,false));
	public static final RegistryObject<Item> CORNMETALSTAFF = ITEMS.register("cornmetalstaff",() -> new CustomStaffItem(defaultitemproperties(),2,false));


	public static final RegistryObject<Item> MAIZERITE = ITEMS.register("maizerite",() -> new Item(defaultitemproperties()));
	public static final RegistryObject<Item> MAIZERITEPANTS = ITEMS.register("maizeritepants",() -> new MazieriteArmourItem(CornArmourMaterials.MAIZERITE, ArmorItem.Type.LEGGINGS, defaultitemproperties()));
	public static final RegistryObject<Item> MAIZERITEHEAD = ITEMS.register("maizeritehead",() -> new MazieriteArmourItem(CornArmourMaterials.MAIZERITE, ArmorItem.Type.HELMET, defaultitemproperties()));
	public static final RegistryObject<Item> MAIZERITEFEET = ITEMS.register("maizeritefeet",() -> new MazieriteArmourItem(CornArmourMaterials.MAIZERITE, ArmorItem.Type.BOOTS, defaultitemproperties()));
	public static final RegistryObject<Item> MAIZERITECHEST = ITEMS.register("maizeritechest",() -> new MazieriteArmourItem(CornArmourMaterials.MAIZERITE, ArmorItem.Type.CHESTPLATE,defaultitemproperties()));
	public static final RegistryObject<Item> MAIZERITEAXE = ITEMS.register("maizeriteaxe",() -> new CustomAxeItem(CornToolTiers.MAIZERITE,5.0F,-3F,defaultitemproperties()));
	public static final RegistryObject<Item> MAIZERITEPICK = ITEMS.register("maizeritepick",() -> new CustomPickaxeItem(CornToolTiers.MAIZERITE,3,-2.8F,defaultitemproperties()));
	public static final RegistryObject<Item> MAIZERITEHOE = ITEMS.register("maizeritehoe",() -> new CustomHoeItem(CornToolTiers.MAIZERITE,-2,0F,defaultitemproperties()));
	public static final RegistryObject<Item> MAIZERITESWORD = ITEMS.register("maizeritesword",() -> new CustomSwordItem(CornToolTiers.MAIZERITE,3,-2.4F,defaultitemproperties()));
	public static final RegistryObject<Item> MAIZERITESHOVEL = ITEMS.register("maizeriteshovel",() -> new CustomShovelItem(CornToolTiers.MAIZERITE,2F,-3F,defaultitemproperties()));
	public static final RegistryObject<Item> MAIZERITEBOW = ITEMS.register("maizeritebow",() -> new CustomBowItem(defaultitemproperties(),10,5,7,true));
	public static final RegistryObject<Item> MAIZERITESTAFF = ITEMS.register("maizeritestaff",() -> new CustomStaffItem(defaultitemproperties(),3,false));


	private static Item.Properties defaultitemproperties(){
		return new Item.Properties();
	}

	// All items that should get weapon- or tool-style models in the hand 
	public static final List<RegistryObject<Item>> HANDHELD_ITEMS = List.of(
			CORNMETALAXE, CORNMETALHOE, CORNMETALSHOVEL, CORNMETALSWORD, CORNMETALPICK, CORNMETALSTAFF,
			MAIZERITEAXE, MAIZERITEHOE, MAIZERITESHOVEL, MAIZERITESWORD, MAIZERITEPICK, MAIZERITESTAFF,
			CORNSTAFF, KERNAL, CROWSTAFF, HARVESTSTAFF, HARVESTERSCYTHE
	);
	
	// All items that should get bow-style models in the hand
	public static final List<RegistryObject<Item>> BOW_ITEMS = List.of(
			CORNMETALBOW, MAIZERITEBOW
	);

}