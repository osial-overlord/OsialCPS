package org.osial.osialcps.hooks;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.osial.osialcps.cps.CPSManager;

public class CPSPlaceholderAPI extends PlaceholderExpansion {

    private CPSManager cpsManager;

    public CPSPlaceholderAPI(CPSManager cpsManager) {
        this.cpsManager = cpsManager;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "osialcps";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Osial";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {
        if (params.equalsIgnoreCase("cps")) {
            return String.valueOf(cpsManager.getCPS(player.getUniqueId()));
        }

        return super.onPlaceholderRequest(player, params);
    }
}
