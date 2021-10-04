package me.laci444.uufixaddon.UUrecipes;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.laci444.uufixaddon.UUfixAddon;
import me.laci444.uufixaddon.UUrecipes.RecipeReader.Recipe;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class RecipeRegister {
    UUfixAddon plugin;
    ArrayList<Recipe> recipes;
    ItemGroup main_category;

    public RecipeRegister(UUfixAddon plugin, ArrayList<Recipe> recipes){
        this.plugin = plugin;
        this.recipes = recipes;

        ItemStack categoryItem = new CustomItemStack(Material.MAGENTA_DYE, "&5UU-matter recipes", "", "This recipes can be autocrafter!");
        NamespacedKey categoryID = new NamespacedKey(plugin, "main_category");

        this.main_category = new ItemGroup(categoryID, categoryItem);
        main_category.register(plugin);

        registerRecipes();
    }

    public void registerRecipes(){
        for (Recipe recipe: recipes){
            SlimefunItem temp = new SlimefunItem(main_category, recipe.item, RecipeType.ENHANCED_CRAFTING_TABLE, recipe.recipe);
            temp.setRecipeOutput(new SlimefunItemStack(recipe.item, recipe.amount));
            temp.setUseableInWorkbench(true);
            temp.register(plugin);
        }
        plugin.getLogger().info("Registered " + recipes.size() + " recipes!");
    }
}
