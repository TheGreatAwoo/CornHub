package com.corn.callofthecorn.data;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.concurrent.CompletableFuture;

public class CornRecipeProvider extends RecipeProvider {

    public CornRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        // Example shaped recipe
//        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SILVER_INGOT.get())
//                .pattern("nnn")
//                .pattern("nnn")
//                .pattern("nnn")
//                .define('n', ModItems.SILVER_NUGGET.get())
//                .unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SILVER_NUGGET.get()))
//                .save(recipeOutput, new ResourceLocation(Main.MOD_ID, "silver_ingot"));

        // Example shapeless recipe
//        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SILVER_NUGGET.get(), 9)
//                .requires(ModItems.SILVER_INGOT.get())
//                .unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SILVER_INGOT.get()))
//                .save(recipeOutput, new ResourceLocation(Main.MOD_ID, "silver_nugget"));

        // Example smelting recipe
//        standardSmeltable(recipeOutput, ModBlocks.SILVER_ORE.get(), ModItems.SILVER_INGOT.get(), 0.7f);
    }

    private void standardSmeltableOnly(RecipeOutput output, Item in, Item out, float xp) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(in), RecipeCategory.MISC,
                        out, xp, 200)
                .unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(in))
                .save(output, ForgeRegistries.ITEMS.getKey(in));
    }

    private void standardSmeltable(RecipeOutput output, Item in, Item out, float xp) {
        standardSmeltableOnly(output, in, out, xp);
        standardBlastable(output, in, out, xp);
    }

    private void standardSmeltable(RecipeOutput output, Block in, Item out, float xp) {
        standardSmeltable(output, in.asItem(), out, xp);
    }

    private void standardBlastable(RecipeOutput output, Item in, Item out, float xp) {
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(in), RecipeCategory.MISC,
                        out, xp, 100)
                .unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(in))
                .save(output, ForgeRegistries.ITEMS.getKey(in) + "_blast");
    }

    private void standardBlastable(RecipeOutput output, Block in, Item out, float xp) {
        standardBlastable(output, in.asItem(), out, xp);
    }
}
