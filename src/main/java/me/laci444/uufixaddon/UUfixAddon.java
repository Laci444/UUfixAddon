package me.laci444.uufixaddon;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import me.laci444.uufixaddon.UUrecipes.RecipeReader;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class UUfixAddon extends JavaPlugin implements SlimefunAddon {

    @Override
    public void onEnable() {
        if (!new File(getDataFolder(), "config.yml").exists()) {
            saveDefaultConfig();
        }


        File recipesFile = new File(this.getDataFolder(), "recipes.yml");
        if (!recipesFile.exists()){
            saveResource("recipes.yml", false);
        }

        Config RecipesConfig = new Config(recipesFile);

        new RecipeReader(this, RecipesConfig);

    }

    @Override
    public void onDisable() {
        // Logic for disabling the plugin...
    }

    @Override
    public String getBugTrackerURL() {
        // You can return a link to your Bug Tracker instead of null here
        return "https://github.com/Laci444/UUfixAddon/issues";
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
