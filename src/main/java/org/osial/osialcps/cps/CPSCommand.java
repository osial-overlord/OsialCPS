package org.osial.osialcps.cps;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.osial.osialcps.OsialCPS;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CPSCommand implements CommandExecutor, TabCompleter {

    private CPSManager cpsManager;
    private OsialCPS plugin;

    public CPSCommand(CPSManager cpsManager, OsialCPS plugin) {
        this.cpsManager = cpsManager;
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 2) {
            String cmd = args[0].toLowerCase(Locale.ROOT);
            String player = args[1].toLowerCase(Locale.ROOT);
            Player p = sender.getServer().getPlayer(player);
            if (p == null) {
                sender.sendMessage("Player not found.");
                return false;
            }

            if (cmd.equals("start")) {
                cpsManager.startRecording(p.getUniqueId());
                Bukkit.getScheduler().runTaskLater(plugin, () -> {
                    cpsManager.stopRecording(p.getUniqueId());
                }, cpsManager.RECORDING_TIME);
                sender.sendMessage("Started recording for " + p.getName());
                return false;
            }
            else if (cmd.equals("stop")) {
                cpsManager.stopRecording(p.getUniqueId());
                sender.sendMessage("Stopped recording for " + p.getName());
                return false;
            }
            else {
                sender.sendMessage("Unknown command.");
            }
        }

        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            List<String> completions = new ArrayList<>();
            completions.add("start");
            completions.add("stop");
            return completions;
        }

        return null;
    }
}
