package com.corn.callofthecorn.data;

import com.corn.callofthecorn.Main;
import com.corn.callofthecorn.init.CornBlocks;
import com.corn.callofthecorn.init.CornItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.concurrent.CompletableFuture;


public class CornRecipeProvider extends RecipeProvider {

    public CornRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    protected void buildRecipes() {
        ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.TOOLS, CornItems.CORNMETALAXE)
                .pattern("ii")
                .pattern("ic")
                .pattern(" c")
                .define('c', CornItems.CORN)
                .define('i', CornItems.CORNMETAL_INGOT)
                .unlockedBy("has_item", this.has(CornItems.CORNMETAL_INGOT)).save(output);
        ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.TOOLS, CornItems.CORNMETALPICK)
                .pattern("iii")
                .pattern(" c ")
                .pattern(" c ")
                .define('c', CornItems.CORN)
                .define('i', CornItems.CORNMETAL_INGOT)
                .unlockedBy("has_item", this.has(CornItems.CORNMETAL_INGOT)).save(output);
        ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.TOOLS, CornItems.CORNMETALSHOVEL)
                .pattern("i")
                .pattern("c")
                .pattern("c")
                .define('c', CornItems.CORN)
                .define('i', CornItems.CORNMETAL_INGOT)
                .unlockedBy("has_item", this.has(CornItems.CORNMETAL_INGOT)).save(output);
        ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.TOOLS, CornItems.CORNMETALHOE)
                .pattern("ii")
                .pattern(" c")
                .pattern(" c")
                .define('c', CornItems.CORN)
                .define('i', CornItems.CORNMETAL_INGOT)
                .unlockedBy("has_item", this.has(CornItems.CORNMETAL_INGOT)).save(output);
        ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.COMBAT, CornItems.CORNMETALSWORD)
                .pattern("i")
                .pattern("i")
                .pattern("c")
                .define('c', CornItems.CORN)
                .define('i', CornItems.CORNMETAL_INGOT)
                .unlockedBy("has_item", this.has(CornItems.CORNMETAL_INGOT)).save(output);
        ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.COMBAT, CornItems.CORNMETALBOW)
                .pattern("si ")
                .pattern("s i")
                .pattern("si ")
                .define('s', Items.STRING)
                .define('i', CornItems.CORNMETAL_INGOT)
                .unlockedBy("has_item", this.has(CornItems.CORNMETAL_INGOT)).save(output);

        ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.TOOLS, CornItems.MAIZERITEAXE)
                .pattern("ii")
                .pattern("ic")
                .pattern(" c")
                .define('c', CornItems.CORN)
                .define('i', CornItems.CORNMETAL_INGOT)
                .unlockedBy("has_item", this.has(CornItems.CORNMETAL_INGOT)).save(output);
        ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.TOOLS, CornItems.MAIZERITEPICK)
                .pattern("iii")
                .pattern(" c ")
                .pattern(" c ")
                .define('c', CornItems.CORNMETAL_INGOT)
                .define('i', CornItems.MAIZERITE)
                .unlockedBy("has_item", this.has(CornItems.MAIZERITE)).save(output);
        ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.TOOLS, CornItems.MAIZERITESHOVEL)
                .pattern("i")
                .pattern("c")
                .pattern("c")
                .define('c', CornItems.CORNMETAL_INGOT)
                .define('i', CornItems.MAIZERITE)
                .unlockedBy("has_item", this.has(CornItems.MAIZERITE)).save(output);
        ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.TOOLS, CornItems.MAIZERITEHOE)
                .pattern("ii")
                .pattern(" c")
                .pattern(" c")
                .define('c', CornItems.CORNMETAL_INGOT)
                .define('i', CornItems.MAIZERITE)
                .unlockedBy("has_item", this.has(CornItems.MAIZERITE)).save(output);
        ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.COMBAT, CornItems.MAIZERITESWORD)
                .pattern("i")
                .pattern("i")
                .pattern("c")
                .define('c', CornItems.CORNMETAL_INGOT)
                .define('i', CornItems.MAIZERITE)
                .unlockedBy("has_item", this.has(CornItems.MAIZERITE)).save(output);
        ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.COMBAT, CornItems.MAIZERITEBOW)
                .pattern("si ")
                .pattern("s i")
                .pattern("si ")
                .define('s', Items.STRING)
                .define('i', CornItems.MAIZERITE)
                .unlockedBy("has_item", this.has(CornItems.MAIZERITE)).save(output);

        armour(output, CornItems.CORNMETAL_INGOT, CornItems.CORNMETALHEAD, CornItems.CORNMETALCHEST, CornItems.CORNMETALPANTS, CornItems.CORNMETALFEET, CornItems.CORNMETAL_INGOT);
        armour(output, CornItems.MAIZERITE, CornItems.MAIZERITEHEAD, CornItems.MAIZERITECHEST, CornItems.MAIZERITEPANTS, CornItems.MAIZERITEFEET, CornItems.MAIZERITE);

        ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.COMBAT, CornItems.CORNSTAFF)
                .pattern(" rs")
                .pattern(" ir")
                .pattern("i  ")
                .define('s', CornItems.LESSER_SOUL)
                .define('r', Items.REDSTONE)
                .define('i', CornItems.CORN)
                .unlockedBy("has_item", this.has(CornItems.CORN)).save(output);
        ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.COMBAT, CornItems.CORNMETALSTAFF)
                .pattern(" es")
                .pattern(" il")
                .pattern("i  ")
                .define('s', CornItems.MILD_SOUL)
                .define('l', Items.LAPIS_LAZULI)
                .define('e', Items.ENDER_PEARL)
                .define('i', CornItems.CORNMETAL_INGOT)
                .unlockedBy("has_item", this.has(CornItems.CORNMETAL_INGOT)).save(output);
        ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.COMBAT, CornItems.MAIZERITESTAFF)
                .pattern(" es")
                .pattern(" id")
                .pattern("i  ")
                .define('s', CornItems.GREATER_SOUL)
                .define('d', Items.DIAMOND)
                .define('e', Items.ENDER_EYE)
                .define('i', CornItems.MAIZERITE)
                .unlockedBy("has_item", this.has(CornItems.MAIZERITE)).save(output);

        ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.MISC, CornItems.CORNMETAL_INGOT, 8)
                .pattern("ccc")
                .pattern("csc")
                .pattern("ccc")
                .define('c', CornItems.CORN)
                .define('s', CornItems.MILD_SOUL)
                .unlockedBy("has_item", this.has(CornItems.MILD_SOUL)).save(output);
        ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.MISC, CornItems.MAIZERITE, 8)
                .pattern("ccc")
                .pattern("csc")
                .pattern("ccc")
                .define('c', CornItems.CORNMETAL_INGOT)
                .define('s', CornItems.GREATER_SOUL)
                .unlockedBy("has_item", this.has(CornItems.GREATER_SOUL)).save(output);
        ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.MISC, CornItems.CORNSOUL, 8)
                .pattern("eci")
                .pattern("cgc")
                .pattern("rcl")
                .define('c', CornItems.CORN)
                .define('g', Items.GOLD_NUGGET)
                .define('i', Items.IRON_INGOT)
                .define('e', Items.EMERALD)
                .define('r', Items.REDSTONE_BLOCK)
                .define('l', Items.LAPIS_BLOCK)
                .unlockedBy("has_item", this.has(CornItems.CORN)).save(output);
        ShapelessRecipeBuilder.shapeless(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.MISC, CornItems.LESSER_SOUL, 5)
                .requires(CornItems.LESSER_SOUL, 1)
                .requires(CornItems.CORNSOUL, 1)
                .unlockedBy("has_item", this.has(CornItems.LESSER_SOUL)).save(output);
        ShapelessRecipeBuilder.shapeless(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.MISC, CornItems.MILD_SOUL, 3)
                .requires(CornItems.MILD_SOUL, 1)
                .requires(CornItems.CORNSOUL, 1)
                .unlockedBy("has_item", this.has(CornItems.MILD_SOUL)).save(output);
        ShapelessRecipeBuilder.shapeless(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.MISC, CornItems.GREATER_SOUL, 2)
                .requires(CornItems.GREATER_SOUL, 1)
                .requires(CornItems.CORNSOUL, 1)
                .unlockedBy("has_item", this.has(CornItems.GREATER_SOUL)).save(output);

        ShapelessRecipeBuilder.shapeless(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.MISC, CornItems.FARMHANDEGG)
                .requires(CornItems.LESSER_SOUL, 9)
                .unlockedBy("has_item", this.has(CornItems.LESSER_SOUL)).save(output);
        ShapelessRecipeBuilder.shapeless(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.MISC, CornItems.HEWHOHARVESTSEGG)
                .requires(CornItems.MILD_SOUL, 9)
                .unlockedBy("has_item", this.has(CornItems.MILD_SOUL)).save(output);
        ShapelessRecipeBuilder.shapeless(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.MISC, CornItems.HARVESTEGG)
                .requires(CornItems.GREATER_SOUL, 9)
                .unlockedBy("has_item", this.has(CornItems.GREATER_SOUL)).save(output);
        ShapelessRecipeBuilder.shapeless(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.MISC, CornBlocks.CORN_SEED.get().asItem())
                .requires(CornItems.CORN)
                .unlockedBy("has_item", this.has(CornItems.CORN)).save(output);

        ShapelessRecipeBuilder.shapeless(registries.lookupOrThrow(Registries.ITEM), RecipeCategory.MISC, CornItems.CREAMEDCORN)
                .requires(Items.MILK_BUCKET)
                .requires(CornItems.CORN_BUCKET)
                .unlockedBy("has_item", this.has(CornItems.CORN)).save(output);
        ShapelessRecipeBuilder.shapeless(registries.lookupOrThrow(Registries.ITEM), RecipeCategory.MISC, CornItems.CORN_BUCKET)
                .requires(Items.BUCKET)
                .requires(CornItems.CORN, 4)
                .unlockedBy("has_item", this.has(CornItems.CORN)).save(output);

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(CornItems.CORN), RecipeCategory.FOOD,
                        CornItems.POPCORN, 0.1f, 50)
                .unlockedBy("has_item", this.has(CornItems.CORN)).save(output);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(CornItems.CORN_BUCKET), RecipeCategory.FOOD,
                        CornItems.POPCORN_BUCKET, 0.4f, 200)
                .unlockedBy("has_item", this.has(CornItems.CORN_BUCKET)).save(output);

    }

    private void armour(RecipeOutput out, ItemLike material, ItemLike head, ItemLike chest, ItemLike legs, ItemLike boots, ItemLike unlock) {
        ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.COMBAT, head)
                .pattern("mmm")
                .pattern("m m")
                .define('m', material)
                .unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(unlock))
                .save(out);
        ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.COMBAT, chest)
                .pattern("m m")
                .pattern("mmm")
                .pattern("mmm")
                .define('m', material)
                .unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(unlock))
                .save(out);
        ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.COMBAT, legs)
                .pattern("mmm")
                .pattern("m m")
                .pattern("m m")
                .define('m', material)
                .unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(unlock))
                .save(out);
        ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.COMBAT, boots)
                .pattern("m m")
                .pattern("m m")
                .define('m', material)
                .unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(unlock))
                .save(out);

    }
    
    

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(output, lookupProvider);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput output) {
            return new CornRecipeProvider(provider, output);
        }

        @Override
        public String getName() {
            return Main.MOD_ID;
        }
    }


}
