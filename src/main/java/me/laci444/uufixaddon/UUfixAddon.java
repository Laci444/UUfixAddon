package me.laci444.uufixaddon;

import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.VanillaItem;
import me.laci444.uufixaddon.UUrecipes.UUrecipes;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.cscorelib2.config.Config;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;

import java.io.File;

public class UUfixAddon extends JavaPlugin implements SlimefunAddon {

    @Override
    public void onEnable() {
        // Read something from your config.yml
        Config cfg = new Config(this);

        /*if (cfg.getBoolean("options.auto-update")) {
            // You could start an Auto-Updater for example
        }*/

        // Create main category to contain recipes
        ItemStack categoryItem = new CustomItem(Material.MAGENTA_DYE, "&5UU-matter recipes", "", "This recipes can be autocrafter!");
        NamespacedKey categoryID = new NamespacedKey(this, "main_category");

        Category main_category = new Category(categoryID, categoryItem);
        main_category.register(this);

        // Define recipe
        ItemStack[] testRecipe = { new ItemStack(Material.STICK), null, new ItemStack(Material.STICK), null, new ItemStack(Material.STICK), null, new ItemStack(Material.STICK), null, new ItemStack(Material.STICK) };
        // Create test item
        VanillaItem testItem = new VanillaItem(main_category, new ItemStack(Material.DIAMOND, 2), "DIAMOND", RecipeType.ENHANCED_CRAFTING_TABLE, testRecipe);
        testItem.register(this);

        File RecipesFile = new File(this.getDataFolder(), "recipes.yml");
        Config RecipesConfig = new Config(RecipesFile);

        UUrecipes recipes = new UUrecipes(RecipesConfig);

    }

    @Override
    public void onDisable() {
        // Logic for disabling the plugin...
    }

    @Override
    public String getBugTrackerURL() {
        // You can return a link to your Bug Tracker instead of null here
        return null;
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        /*
         * You will need to return a reference to your Plugin here.
         * If you are using your main class for this, simply return "this".
         */
        return this;
    }

}
