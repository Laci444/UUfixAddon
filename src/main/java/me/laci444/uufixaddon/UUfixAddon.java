package me.laci444.uufixaddon;

import io.github.thebusybiscuit.slimefun4.implementation.items.VanillaItem;
import me.laci444.uufixaddon.UUrecipes.RecipeReader;
import me.laci444.uufixaddon.UUrecipes.RecipeRegister;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.cscorelib2.config.Config;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;

public class UUfixAddon extends JavaPlugin implements SlimefunAddon {

    @Override
    public void onEnable() {

        //File recipesFile = new File(this.getDataFolder(), "recipes.yml");
        Config RecipesConfig = new Config(this.getDataFolder()+"/recipes.yml");

        new RecipeReader(this, RecipesConfig);

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
