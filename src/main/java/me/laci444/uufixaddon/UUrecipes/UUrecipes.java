package me.laci444.uufixaddon.UUrecipes;

import me.laci444.uufixaddon.UUfixAddon;
import me.mrCookieSlime.Slimefun.cscorelib2.config.Config;

public class UUrecipes {
    UUfixAddon plugin;
    Config cfg;
    public UUrecipes(UUfixAddon plugin, Config cfg) {
        this.plugin = plugin;
        this.cfg = cfg;
    }

    public void reload(Config cfg){
        this.cfg = cfg;
    }


}
