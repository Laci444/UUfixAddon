package me.laci444.uufixaddon;

import me.laci444.uufixaddon.UUrecipes.RecipeReader;
import me.mrCookieSlime.Slimefun.cscorelib2.updater.GitHubBuildsUpdater;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import me.mrCookieSlime.Slimefun.cscorelib2.config.Config;

import java.io.File;

public class UUfixAddon extends JavaPlugin implements SlimefunAddon {

    @Override
    public void onEnable() {
        if (!new File(getDataFolder(), "config.yml").exists()) {
            saveDefaultConfig();
        }

        Config cfg = new Config(this);
        if (cfg.getBoolean("options.auto-update")){
            new GitHubBuildsUpdater(this, getFile(), "Laci444/UUfixAddon/master").start();
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
