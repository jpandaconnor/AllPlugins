package co.uk.RandomPanda30.SAdminO.Util.Basic;

import co.uk.RandomPanda30.SAdminO.Files.Messages.MessagesValues;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MUtil {

    public static void sendMessage(String message, Player player) {
        if (player == null) {
            Bukkit.getConsoleSender().sendMessage(format(message));
        } else {
            player.sendMessage(format(message));
        }
    }

    public static void broadcastMessage(String message) {
        Bukkit.broadcastMessage(format(message));
    }

    public static String format(String string) {
        return string.replaceAll("%TAG", (String) MessagesValues.TAG.value)
                .replaceAll("%N", (String) MessagesValues.NORMAL.value)
                .replaceAll("%W", (String) MessagesValues.WARNING.value)
                .replaceAll("%E", (String) MessagesValues.ERROR.value)
                .replaceAll("%A", (String) MessagesValues.ARG.value)
                .replaceAll("%H", (String) MessagesValues.HEADER.value)
                .replaceAll("%G", (String) MessagesValues.GOOD.value)
                .replaceAll("%B", (String) MessagesValues.BAD.value).replaceAll("(&([a-fk-or0-9]))", "\u00A7$2")
                .replaceAll("&u", "\n");
    }

    public static String getArgs(String[] args, int num) {
        StringBuilder sb = new StringBuilder();
        String prefix = "";
        for (int i = num; i < args.length; i++) {
            sb.append(prefix);
            prefix = " ";
            sb.append(args[i]);
        }
        return sb.toString();
    }
}