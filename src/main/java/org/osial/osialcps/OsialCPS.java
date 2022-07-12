package org.osial.osialcps;

import org.bukkit.plugin.java.JavaPlugin;
import org.osial.osialcps.cps.CPSCommand;
import org.osial.osialcps.cps.CPSManager;
import org.osial.osialcps.hooks.CPSPlaceholderAPI;

public final class OsialCPS extends JavaPlugin {

    private CPSManager cpsManager;
    private CPSCommand cpsCommand;
    private CPSPlaceholderAPI cpsPlaceholderAPI;

    @Override
    public void onEnable() {
        // Plugin startup logic
        cpsManager = new CPSManager();
        getServer().getPluginManager().registerEvents(cpsManager, this);

        cpsCommand = new CPSCommand(cpsManager, this);
        getCommand("cps").setExecutor(cpsCommand);
        getCommand("cps").setTabCompleter(cpsCommand);

        //Check for PAPI presence & make an instance and register the expansion.
        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            cpsPlaceholderAPI = new CPSPlaceholderAPI(cpsManager);
            cpsPlaceholderAPI.register();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
