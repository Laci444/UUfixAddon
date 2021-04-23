package me.laci444.uufixaddon.UUrecipes;

import com.google.common.annotations.VisibleForTesting;
import javafx.util.Pair;
import me.laci444.uufixaddon.UUfixAddon;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.cscorelib2.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

public class RecipeReader {
    Config recipesConfig;
    ArrayList<Pair<SlimefunItemStack, ItemStack[]>> recipes = new ArrayList<>();
    SlimefunItem UU_Matter = SlimefunItem.getByID("UU_MATTER");

    public RecipeReader(UUfixAddon plugin, Config recipesConfig) {
        this.recipesConfig = recipesConfig;
        readRecipes();
        new RecipeRegister(plugin, this.recipes);
    }

    public void readRecipes(){
        if (recipesConfig.getKeys().isEmpty()){
            Bukkit.getLogger().severe("UUfixAddon can't find any recipes in recipes.yml! There will be no item in UU-matter recipes category :(");
            return;
        }

        for (String recipe : recipesConfig.getKeys()) {
            ArrayList<String> temp = new ArrayList<>(recipesConfig.getKeys(recipe));
            recipes.add(convert(recipe, recipesConfig.getValue(recipe + "." + temp.get(0)).toString(), (int)recipesConfig.getValue(recipe + "." + temp.get(1)), recipesConfig.getValue(recipe + "." + temp.get(2)).toString()));
        }
    }

    public Pair<SlimefunItemStack, ItemStack[]> convert(String item, String name, int amount, String items){
        ItemStack[] temp = new ItemStack[9];
        char[] array = items.replaceAll("\\s", "").replaceAll(",", "").toCharArray();
        for (int i = 0; i < 9; i++){
            if (array[i] == 'x'){
                temp[i] = SlimefunItem.getByID("UU_MATTER").getItem();
            }
            else if (array[i] == 'o'){
                temp[i] = null;
            }
        }
        return new Pair<SlimefunItemStack, ItemStack[]> (new SlimefunItemStack(new SlimefunItemStack(item, Material.getMaterial(item), name), amount), temp);
    }

}
