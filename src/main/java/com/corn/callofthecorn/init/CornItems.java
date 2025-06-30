package com.corn.callofthecorn.init;

import com.corn.callofthecorn.Main;
import com.corn.callofthecorn.items.*;
import com.corn.callofthecorn.items.armour.CornArmourMaterials;
import com.corn.callofthecorn.items.armour.PumpCrown;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.equipment.ArmorType;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.Set;

public class CornItems {

	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Main.MOD_ID);

	public static final Consumable CREAMED_CORN_CONSUME = Consumables.defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(List.of(
				new MobEffectInstance(MobEffects.REGENERATION, 1000, 0),
				new MobEffectInstance(MobEffects.HEALTH_BOOST, 1000, 0)
			))).build();


	public static final DeferredItem<Item> CROWSFOOT = ITEMS.registerSimpleItem("crowsfoot", unstackableProperties().fireResistant());

	public static final DeferredItem<Item> CORN = ITEMS.registerSimpleItem("corn", defaultitemproperties()
			.food((new FoodProperties.Builder()).nutrition(1).saturationModifier(0.5f).build()));
	public static final DeferredItem<Item> POPCORN = ITEMS.registerSimpleItem("popcorn", defaultitemproperties().food(
			(new FoodProperties.Builder()).nutrition(3).saturationModifier(0.75f).build()));
	public static final DeferredItem<Item> CREAMEDCORN = ITEMS.registerSimpleItem("creamedcorn", defaultitemproperties()
			.craftRemainder(Items.BUCKET).component(DataComponents.CONSUMABLE, CREAMED_CORN_CONSUME).usingConvertsTo(Items.BUCKET).stacksTo(1));
	public static final DeferredItem<Item> CORN_BUCKET = ITEMS.registerSimpleItem("corn_bucket", defaultitemproperties()
			.food((new FoodProperties.Builder()).nutrition(4).saturationModifier(2f).build()).usingConvertsTo(Items.BUCKET).stacksTo(16));
	public static final DeferredItem<Item> POPCORN_BUCKET = ITEMS.registerSimpleItem("popcorn_bucket", defaultitemproperties()
			.food((new FoodProperties.Builder()).nutrition(12).saturationModifier(3f).build()).usingConvertsTo(Items.BUCKET).stacksTo(16));

	public static final DeferredItem<Item> FARMHANDEGG = ITEMS.register("farmhandegg",
			l -> new BossSummonItem(CornMobs.FARMHAND::get, 9, 3.0, 8, 4, defaultitemproperties(l)));
	public static final DeferredItem<Item> HEWHOHARVESTSEGG = ITEMS.register("hewhoharvestsegg",
			l -> new BossSummonItem(CornMobs.HARVESTER::get, 16,4.0, 10, 5, defaultitemproperties(l)));
	public static final DeferredItem<Item> HARVESTEGG = ITEMS.register("harvestegg",
			l -> new AerialBossSummonItem(CornMobs.HARVEST::get, defaultitemproperties(l)));

	public static final DeferredItem<Item> LESSER_SOUL = ITEMS.register("lessersoul", l -> new Item(defaultitemproperties(l).fireResistant()));
	public static final DeferredItem<Item> MILD_SOUL = ITEMS.register("mildsoul", l -> new Item(defaultitemproperties(l).fireResistant()));
	public static final DeferredItem<Item> GREATER_SOUL = ITEMS.register("greatersoul", l -> new Item(defaultitemproperties(l).fireResistant()));
	public static final DeferredItem<Item> CORNSOUL = ITEMS.register("cornsoul", l ->  new Item(defaultitemproperties(l).fireResistant()));

	public static final DeferredItem<Item> KERNAL = ITEMS.register("kernal",
			l -> new KernelItem(CornToolTiers.KERNALRITE, 3.0F, -2.4F, defaultitemproperties(l).fireResistant()));
	public static final DeferredItem<Item> CROWSTAFF = ITEMS.register("crowstaff",
			l -> new CustomStaffItem(unstackableProperties(l).fireResistant(),4,false, CornItems.MILD_SOUL));
	public static final DeferredItem<Item> HARVESTSTAFF = ITEMS.register("harveststaff",
			l -> new CustomStaffItem(unstackableProperties(l).fireResistant(),0,true, CornItems.MILD_SOUL));
	public static final DeferredItem<Item> PUMPKINGSCROWN = ITEMS.register("pumpkingscrown",
			l -> new PumpCrown(unstackableProperties(l).humanoidArmor(CornArmourMaterials.PUMPKIN, ArmorType.HELMET)));
	public static final DeferredItem<Item> HARVESTERSCYTHE = ITEMS.register("harvesterscythe",
			l -> new HarvesterScytheItem(CornToolTiers.KERNALRITE, 3.0F, -2.0F, defaultitemproperties(l)));
	//public static final DeferredItem<Item> PITCHFORK = ITEMS.registerItem("pitchfork",l -> new Pitchfork(defaultitemproperties(l)));

	public static final DeferredItem<Item> CORNSTAFF = ITEMS.register("cornstaff",
			l -> new CustomStaffItem(unstackableProperties(l),1,false, CornItems.LESSER_SOUL));
	public static final DeferredItem<Item> CORNMETAL_INGOT = ITEMS.registerSimpleItem("cornmetalbar");
	public static final DeferredItem<Item> CORNMETALPANTS = ITEMS.register("cornmetalpants",
			l -> new Item(unstackableProperties(l).humanoidArmor(CornArmourMaterials.CORNMETAL, ArmorType.LEGGINGS)));
	public static final DeferredItem<Item> CORNMETALHEAD = ITEMS.register("cornmetalhead",
			l -> new Item(unstackableProperties(l).humanoidArmor(CornArmourMaterials.CORNMETAL, ArmorType.HELMET)));
	public static final DeferredItem<Item> CORNMETALFEET = ITEMS.register("cornmetalfeet",
			l -> new Item(unstackableProperties(l).humanoidArmor(CornArmourMaterials.CORNMETAL, ArmorType.BOOTS)));
	public static final DeferredItem<Item> CORNMETALCHEST = ITEMS.register("cornmetalchest",
			l -> new Item(unstackableProperties(l).humanoidArmor(CornArmourMaterials.CORNMETAL, ArmorType.CHESTPLATE)));
	public static final DeferredItem<Item> CORNMETALAXE = ITEMS.register("cornmetalaxe",
			l -> new AxeItem(CornToolTiers.CORNMETAL,5F,-2F,defaultitemproperties(l)));
	public static final DeferredItem<Item> CORNMETALPICK = ITEMS.register("cornmetalpick",
			l -> new Item(defaultitemproperties(l).pickaxe(CornToolTiers.CORNMETAL, 1,-2F)));
	public static final DeferredItem<Item> CORNMETALHOE = ITEMS.register("cornmetalhoe",
			l -> new HoeItem(CornToolTiers.CORNMETAL,-3,0F,defaultitemproperties(l)));
	public static final DeferredItem<Item> CORNMETALSWORD = ITEMS.register("cornmetalsword",
			l -> new Item(defaultitemproperties(l).sword(CornToolTiers.CORNMETAL,3,-1F)));
	public static final DeferredItem<Item> CORNMETALSHOVEL = ITEMS.register("cornmetalshovel",
			l -> new ShovelItem(CornToolTiers.CORNMETAL, 1.5F, -2F, defaultitemproperties(l)));
	public static final DeferredItem<Item> CORNMETALBOW = ITEMS.register("cornmetalbow",
			l -> new CustomBowItem(unstackableProperties(l),4,24, false));
	public static final DeferredItem<Item> CORNMETALSTAFF = ITEMS.register("cornmetalstaff",
			l -> new CustomStaffItem(unstackableProperties(l),2,false, CornItems.LESSER_SOUL));


	public static final DeferredItem<Item> MAIZERITE = ITEMS.registerSimpleItem("maizerite");
	public static final DeferredItem<Item> MAIZERITEPANTS = ITEMS.register("maizeritepants",
			l -> new Item(unstackableProperties(l).humanoidArmor(CornArmourMaterials.MAIZERITE, ArmorType.LEGGINGS)));
	public static final DeferredItem<Item> MAIZERITEHEAD = ITEMS.register("maizeritehead",
			l -> new Item(unstackableProperties(l).humanoidArmor(CornArmourMaterials.MAIZERITE, ArmorType.HELMET)));
	public static final DeferredItem<Item> MAIZERITEFEET = ITEMS.register("maizeritefeet",
			l -> new Item(unstackableProperties(l).humanoidArmor(CornArmourMaterials.MAIZERITE, ArmorType.BOOTS)));
	public static final DeferredItem<Item> MAIZERITECHEST = ITEMS.register("maizeritechest",
			l -> new Item(unstackableProperties(l).humanoidArmor(CornArmourMaterials.MAIZERITE, ArmorType.CHESTPLATE)));
	public static final DeferredItem<Item> MAIZERITEAXE = ITEMS.register("maizeriteaxe",
			l -> new AxeItem(CornToolTiers.MAIZERITE, 5.0F, -3.0F, defaultitemproperties(l)));
	public static final DeferredItem<Item> MAIZERITEPICK = ITEMS.register("maizeritepick",
			l -> new Item(defaultitemproperties(l).pickaxe(CornToolTiers.MAIZERITE, 3.0F, -2.8F)));
	public static final DeferredItem<Item> MAIZERITEHOE = ITEMS.register("maizeritehoe",
			l -> new HoeItem(CornToolTiers.MAIZERITE, -2.0F, 0.0F, defaultitemproperties(l)));
	public static final DeferredItem<Item> MAIZERITESWORD = ITEMS.register("maizeritesword",
			l -> new Item(defaultitemproperties(l).sword(CornToolTiers.MAIZERITE, 3.0F, -2.4F)));
	public static final DeferredItem<Item> MAIZERITESHOVEL = ITEMS.register("maizeriteshovel",
			l -> new ShovelItem(CornToolTiers.MAIZERITE, 2.0F, -3.0F, defaultitemproperties(l)));
	public static final DeferredItem<Item> MAIZERITEBOW = ITEMS.register("maizeritebow",l -> new CustomBowItem(unstackableProperties(l),5,16,true));
	public static final DeferredItem<Item> MAIZERITESTAFF = ITEMS.register("maizeritestaff",l -> new CustomStaffItem(unstackableProperties(l),3,false, CornItems.LESSER_SOUL));




	private static Item.Properties defaultitemproperties(ResourceLocation l){
			return new Item.Properties().setId(ResourceKey.create(Registries.ITEM, l));
	}

	private static Item.Properties defaultitemproperties(){
		return new Item.Properties();
	}

	private static Item.Properties unstackableProperties(ResourceLocation l){
		return new Item.Properties().setId(ResourceKey.create(Registries.ITEM, l)).stacksTo(1);
	}

	private static Item.Properties unstackableProperties(){
		return new Item.Properties().stacksTo(1);
	}

	// All items that should get weapon- or tool-style models in the hand 
	public static final Set<DeferredItem<Item>> HANDHELD_ITEMS = Set.of(
			CORNMETALAXE, CORNMETALHOE, CORNMETALSHOVEL, CORNMETALSWORD, CORNMETALPICK, CORNMETALSTAFF,
			MAIZERITEAXE, MAIZERITEHOE, MAIZERITESHOVEL, MAIZERITESWORD, MAIZERITEPICK, MAIZERITESTAFF,
			CORNSTAFF, KERNAL, CROWSTAFF, HARVESTSTAFF, HARVESTERSCYTHE
	);
	
	// All items that should get bow-style models in the hand
	public static final Set<DeferredItem<Item>> BOW_MODEL_ITEMS = Set.of(
			CORNMETALBOW, MAIZERITEBOW
	);

}