package com.example.examplemod;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraftforge.data.event.GatherDataEvent;

import java.util.function.Consumer;

public class MyRecipes extends RecipeProvider {
    public MyRecipes(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput());
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.FOOD, ExampleMod.EXAMPLE_ITEM.get(), 4) // crafts 4
                .define('C', Items.COOKIE)
                .define('G', Items.GRASS_BLOCK)
                .pattern(" G ")
                .pattern("GCG")
                .pattern(" G ")
                .unlockedBy("has_cookie", RecipeProvider.has(Items.COOKIE))
                .unlockedBy("has_grass", RecipeProvider.has(Items.GRASS_BLOCK))
                .save(consumer);
    }

}
