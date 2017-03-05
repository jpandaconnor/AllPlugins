package co.uk.randompanda30.houseshop.string;

import co.uk.randompanda30.houseshop.object.Sender;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Created by panda on 02/05/16.
 */
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

    public static void sendMessageList(ArrayList<String> messages, Player player) {
        if (messages == null) return;

        if (player == null) {
            for (String s : messages) {
                sendMessage(s, null);
            }
        } else {
            for (String s : messages) {
                sendMessage(s, player);
            }
        }
    }

    public static void senderMessage(String message, Sender sender) {
        if (message == null)
            return;

        if (!sender.isPlayer) {
            Bukkit.getConsoleSender().sendMessage(Format.format(message));
        } else {
            sender.getPlayer().getPlayer().sendMessage(Format.format(message));
        }
    }

    public static void broadcast(String message, boolean useBukkit) {
        if (useBukkit) {
            Bukkit.broadcastMessage(Format.format(message));
        } else {
            for (Player player : Bukkit.getOnlinePlayers()) {
                sendMessage(message, player);
            }
        }
    }

    public static class Start {
        public Start() {
            Dispatch.sendMessage("%TAG %GLoading...", null);
        }
    }

    public static class Stop {
        public Stop() {
            Dispatch.sendMessage("%TAG %BStopping", null);
        }
    }
}