package me.laci444.uufixaddon;

import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
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

        // Define item params
        SlimefunItemStack testSfItem = new SlimefunItemStack("testSfItem", Material.DIAMOND, "&bTeszt gyémánt", "", "&aHali, itt teszt gyémánt jelentkezik!");
        ItemStack[] testRecipe = { new ItemStack(Material.STICK), null, new ItemStack(Material.STICK), null, new ItemStack(Material.STICK), null, new ItemStack(Material.STICK), null, new ItemStack(Material.STICK) };
        // Create test item
        SlimefunItem testItem = new SlimefunItem(main_category, testSfItem, RecipeType.ENHANCED_CRAFTING_TABLE, testRecipe, new SlimefunItemStack(testSfItem, 2));
        testItem.setUseableInWorkbench(true).register(this);

        UUrecipes recipes = new UUrecipes(this, cfg);

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
