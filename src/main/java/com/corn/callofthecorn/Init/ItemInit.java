package com.corn.callofthecorn.Init;
import com.corn.callofthecorn.Curios.CrowsFoot;
import com.corn.callofthecorn.Main;
import com.corn.callofthecorn.enums.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ItemInit {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MOD_ID);

	public static final RegistryObject<Item> CROWSFOOT = ITEMS.register("crowsfoot",() -> new CrowsFoot(defaultitemproperties().fireResistant()));




	public static final RegistryObject<Item> KERNAL = ITEMS.register("kernal",() -> new Kernal(CustomTier.KERNALRITE,3,-2F,defaultitemproperties().fireResistant()));
	public static final RegistryObject<Item> CROWSTAFF = ITEMS.register("crowstaff", () -> new CustomStaffs(defaultitemproperties().fireResistant(),4,false));
	public static final RegistryObject<Item> HARVESTSTAFF = ITEMS.register("harveststaff", () -> new CustomStaffs(defaultitemproperties().fireResistant(),0,true));
	public static final RegistryObject<Item> PUMPKINGSCROWN = ITEMS.register("pumpkingscrown",() -> new PumpCrown(CornArmourMaterials.PUMPKIN, ArmorItem.Type.HELMET, defaultitemproperties()));
	public static final RegistryObject<Item> HARVESTERSCYTHE = ITEMS.register("harvesterscythe",() -> new HarvesterScythe(CustomTier.KERNALRITE,3,-2.4F,defaultitemproperties()));
	//public static final RegistryObject<Item> PITCHFORK = ITEMS.register("pitchfork",() -> new Pitchfork(defaultitemproperties()));


	public static final RegistryObject<Item> CORN = ITEMS.register("corn", () -> new Item(defaultitemproperties().food((new FoodProperties.Builder()).nutrition(1).saturationMod(0.5f).build())));
	public static final RegistryObject<Item> POPCORN = ITEMS.register("popcorn", () -> new Item(defaultitemproperties().food((new FoodProperties.Builder()).nutrition(5).saturationMod(0.8f).build())));
	public static final RegistryObject<Item> CREAMEDCORN = ITEMS.register("creamedcorn", () -> new CreamedCorn(defaultitemproperties()));
	public static final RegistryObject<Item> FARMHANDEGG = ITEMS.register("farmhandegg",() ->  new Egg(EntityType.MAGMA_CUBE, 9804698, 1973273, (defaultitemproperties()),2));
	public static final RegistryObject<Item> HEWHOHARVESTSEGG = ITEMS.register("hewhoharvestsegg",() ->new Egg(EntityType.MAGMA_CUBE, 9804697, 1973272, (defaultitemproperties()),3));
	public static final RegistryObject<Item> HARVESTEGG = ITEMS.register("harvestegg",() ->new Egg(EntityType.MAGMA_CUBE, 9804696, 1973271, (defaultitemproperties()),4));

	//public static final RegistryObject<Item> SCARECROWEGG = ITEMS.register("scarecrowegg",() ->new Egg(EntityType.MAGMA_CUBE, 9804695, 1973270, (defaultitemproperties()),1));
	//public static final RegistryObject<Item> CROPEGG = ITEMS.register("cropegg",() ->new Egg(EntityType.MAGMA_CUBE, 9804695, 1973270, (defaultitemproperties()),5));

	public static final RegistryObject<Item> LESSERSOUL = ITEMS.register("lessersoul",() -> new Item(defaultitemproperties().fireResistant()));
	public static final RegistryObject<Item> MILDSOUL = ITEMS.register("mildsoul",() -> new Item(defaultitemproperties().fireResistant()));
	public static final RegistryObject<Item> GREATERSOUL = ITEMS.register("greatersoul",() -> new Item(defaultitemproperties().fireResistant()));
	public static final RegistryObject<Item> CORNSOUL = ITEMS.register("cornsoul", () ->  new Item(defaultitemproperties().fireResistant()));


	public static final RegistryObject<Item> CORNSTAFF = ITEMS.register("cornstaff",() -> new CustomStaffs(defaultitemproperties(),1,false));
	public static final RegistryObject<Item> CORNMETALBAR = ITEMS.register("cornmetalbar",() -> new Item(defaultitemproperties()));
	public static final RegistryObject<Item> CORNMETALPANTS = ITEMS.register("cornmetalpants",() -> new Cornmetal(CornArmourMaterials.CORNMETAL, ArmorItem.Type.LEGGINGS, defaultitemproperties()));
	public static final RegistryObject<Item> CORNMETALHEAD = ITEMS.register("cornmetalhead",() -> new Cornmetal(CornArmourMaterials.CORNMETAL, ArmorItem.Type.HELMET, defaultitemproperties()));
	public static final RegistryObject<Item> CORNMETALFEET = ITEMS.register("cornmetalfeet",() -> new Cornmetal(CornArmourMaterials.CORNMETAL, ArmorItem.Type.BOOTS, defaultitemproperties()));
	public static final RegistryObject<Item> CORNMETALCHEST = ITEMS.register("cornmetalchest",() -> new Cornmetal(CornArmourMaterials.CORNMETAL, ArmorItem.Type.CHESTPLATE,defaultitemproperties()));
	public static final RegistryObject<Item> CORNMETALAXE = ITEMS.register("cornmetalaxe",() -> new CustomAxe(CustomTier.CORNMETAL,5F,-2F,defaultitemproperties()));
	public static final RegistryObject<Item> CORNMETALPICK = ITEMS.register("cornmetalpick",() -> new CustomPick(CustomTier.CORNMETAL,1,-2F,defaultitemproperties()));
	public static final RegistryObject<Item> CORNMETALHOE = ITEMS.register("cornmetalhoe",() -> new CustomHoe(CustomTier.CORNMETAL,-3,0F,defaultitemproperties()));
	public static final RegistryObject<Item> CORNMETALSWORD = ITEMS.register("cornmetalsword",() -> new CustomSword(CustomTier.CORNMETAL,3,-1F,defaultitemproperties()));
	public static final RegistryObject<Item> CORNMETALSHOVEL = ITEMS.register("cornmetalshovel",() -> new CustomShovel(CustomTier.CORNMETAL,1.5F,-2F,defaultitemproperties()));
	public static final RegistryObject<Item> CORNMETALBOW = ITEMS.register("cornmetalbow",() -> new CustomBows(defaultitemproperties(),35,4,3,false));
	public static final RegistryObject<Item> CORNMETALSTAFF = ITEMS.register("cornmetalstaff",() -> new CustomStaffs(defaultitemproperties(),2,false));


	public static final RegistryObject<Item> MAIZERITE = ITEMS.register("maizerite",() -> new Item(defaultitemproperties()));
	public static final RegistryObject<Item> MAIZERITEPANTS = ITEMS.register("maizeritepants",() -> new Mazierite(CornArmourMaterials.MAIZERITE, ArmorItem.Type.LEGGINGS, defaultitemproperties()));
	public static final RegistryObject<Item> MAIZERITEHEAD = ITEMS.register("maizeritehead",() -> new Mazierite(CornArmourMaterials.MAIZERITE, ArmorItem.Type.HELMET, defaultitemproperties()));
	public static final RegistryObject<Item> MAIZERITEFEET = ITEMS.register("maizeritefeet",() -> new Mazierite(CornArmourMaterials.MAIZERITE, ArmorItem.Type.BOOTS, defaultitemproperties()));
	public static final RegistryObject<Item> MAIZERITECHEST = ITEMS.register("maizeritechest",() -> new Mazierite(CornArmourMaterials.MAIZERITE, ArmorItem.Type.CHESTPLATE,defaultitemproperties()));
	public static final RegistryObject<Item> MAIZERITEAXE = ITEMS.register("maizeriteaxe",() -> new CustomAxe(CustomTier.MAIZERITE,5.0F,-3F,defaultitemproperties()));
	public static final RegistryObject<Item> MAIZERITEPICK = ITEMS.register("maizeritepick",() -> new CustomPick(CustomTier.MAIZERITE,3,-2.8F,defaultitemproperties()));
	public static final RegistryObject<Item> MAIZERITEHOE = ITEMS.register("maizeritehoe",() -> new CustomHoe(CustomTier.MAIZERITE,-2,0F,defaultitemproperties()));
	public static final RegistryObject<Item> MAIZERITESWORD = ITEMS.register("maizeritesword",() -> new CustomSword(CustomTier.MAIZERITE,3,-2.4F,defaultitemproperties()));
	public static final RegistryObject<Item> MAIZERITESHOVEL = ITEMS.register("maizeriteshovel",() -> new CustomShovel(CustomTier.MAIZERITE,2F,-3F,defaultitemproperties()));
	public static final RegistryObject<Item> MAIZERITEBOW = ITEMS.register("maizeritebow",() -> new CustomBows(defaultitemproperties(),10,5,7,true));
	public static final RegistryObject<Item> MAIZERITESTAFF = ITEMS.register("maizeritestaff",() -> new CustomStaffs(defaultitemproperties(),3,false));


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