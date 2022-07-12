package org.osial.osialcps.cps;

import lombok.Getter;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.*;

public class CPSManager implements Listener {

    private List<UUID> recordingPlayers = new ArrayList<>();
    private Map<UUID, Long> lastClickTime = new HashMap<>();
    private Map<UUID, Integer> currentCPS = new HashMap<>();
    public static final int RECORDING_TIME = 20 * 60; // 1 Minute in Ticks

    public void startRecording(UUID player) {
        recordingPlayers.add(player);
    }

    public void stopRecording(UUID player) {
        recordingPlayers.remove(player);
    }

    public double getCPS(UUID uuid) {
        Integer cps = currentCPS.get(uuid);
        if (cps == null) {
            return 0;
        }

        //CPS is stored in minutes.
        return (cps.doubleValue() / 60);
    }

    @EventHandler
    public void onPlayerClick(PlayerInteractEvent event) {
        if (recordingPlayers.contains(event.getPlayer().getUniqueId())) {
            if (event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_AIR) {
                lastClickTime.put(event.getPlayer().getUniqueId(), System.currentTimeMillis());
                Integer currentCPS = this.currentCPS.get(event.getPlayer().getUniqueId());
                if (currentCPS == null) {
                    currentCPS = 1;
                }
                else {
                    currentCPS++;
                }
                this.currentCPS.put(event.getPlayer().getUniqueId(), currentCPS);
            }
        }
    }

}
