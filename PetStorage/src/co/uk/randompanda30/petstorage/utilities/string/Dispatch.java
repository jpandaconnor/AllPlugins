package co.uk.randompanda30.petstorage.utilities.string;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Dispatch {

    public static void sendMessage(String message, Player player) {
        if (message == null)
            return;

        if (player == null) {
            Bukkit.getConsoleSender().sendMessage(Format.format(message));
        } else {
            player.getPlayer().sendMessage(Format.format(message));
        }
    }

    public static void sendMessage(String message, boolean useBukkit) {
        if (useBukkit) {
            Bukkit.broadcastMessage(Format.format(message));
        } else {
            for (Player player : Bukkit.getOnlinePlayers()) {
                sendMessage(message, player);
            }
        }
    }
}