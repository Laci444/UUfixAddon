package me.laci444.uufixaddon.UUrecipes;

import me.laci444.uufixaddon.UUfixAddon;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.cscorelib2.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class RecipeReader {
    static class Recipe{
        public SlimefunItemStack item;
        public int amount;
        public ItemStack[] recipe;
        public boolean isFailed = false;
        public Recipe(String item, String name, int amount, ItemStack[] recipe){
            try {
                this.item = new SlimefunItemStack(item, Objects.requireNonNull(Material.getMaterial(item)), name);
            } catch (NullPointerException e){
                try {
                    this.item = new SlimefunItemStack(item, Objects.requireNonNull(SlimefunItem.getByID(item)).getItem(), name);
                } catch (NullPointerException ex){
                    Bukkit.getLogger().warning("Can't find " + item);
                    this.isFailed = true;
                }
            }
            this.amount = amount; this.recipe = recipe;
        }
    }

    Config recipesConfig;
    ArrayList<Recipe> recipes = new ArrayList<>();
    ItemStack UU_MATTER;

    public RecipeReader(UUfixAddon plugin, Config recipesConfig) {
        this.recipesConfig = recipesConfig;

        try{
            this.UU_MATTER = SlimefunItem.getByID("UU_MATTER").getItem();
        } catch (NullPointerException e){
            Bukkit.getLogger().warning("Can't fing UU-matter! Check the log for more details on LiteXpansion or contact with its authors.");
            return;
        }

        readRecipes();
        new RecipeRegister(plugin, this.recipes);
    }

    public void readRecipes(){
        if (recipesConfig.getKeys().isEmpty()){
            Bukkit.getLogger().severe("UUfixAddon can't find any recipes in recipes.yml! There will be no item in UU-matter recipes category :(");
            return;
        }

        for (String recipe : recipesConfig.getKeys()) {
            ArrayList<String> data = new ArrayList<>(recipesConfig.getKeys(recipe));
            Recipe temp = convert(recipe, recipesConfig.getString(recipe + "." + data.get(0)), recipesConfig.getInt(recipe + "." + data.get(1)), recipesConfig.getStringList(recipe + "." + data.get(2)));
            if (temp == null){ continue; }
            recipes.add(temp);
        }
    }

    public Recipe convert(String item, String name, int amount, List<String> items){
        ItemStack[] recipe = new ItemStack[9];
        int iter = 0;
        for (String row : items){
            for (String chr : row.replaceAll("\\s", "").split(",")) {
                if (chr.equals("x")) {
                    recipe[iter] = UU_MATTER;
                } else if (chr.equals("o")) {
                    recipe[iter] = null;
                }
                iter++;
            }
        }
        Recipe temp = new Recipe(item, name, amount, recipe);
        if (temp.isFailed) {return null;}
        return temp;
    }

}
