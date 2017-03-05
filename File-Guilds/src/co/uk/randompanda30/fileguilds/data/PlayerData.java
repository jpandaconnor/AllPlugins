package co.uk.randompanda30.fileguilds.data;

import org.bukkit.entity.Player;

import java.util.UUID;

import static co.uk.randompanda30.fileguilds.TEMP.pdatac;

public class PlayerData {

    public static boolean isInGuild(Player player) {
        return pdatac.contains(player.getUniqueId().toString());
    }

    public static boolean isInGuild(String name) {
        boolean inGuild = false;
        for (String s : pdatac.getConfigurationSection("").getKeys(false)) {
            if (pdatac.get(s + ".name").equals(name)) {
                inGuild = true;
                break;
            }
        }
        return inGuild;
    }

    public static boolean isInGuild(UUID uuid) {
        return pdatac.contains(uuid.toString());
    }

    public static String getGuild(Player player) {
        if (!isInGuild(player)) {
            return null;
        }
        return (String) pdatac.get(player.getUniqueId().toString() + ".guild");
    }

    public static String getRank(Player player) {
        if (!isInGuild(player)) {
            return null;
        }
        return (String) pdatac.get(player.getUniqueId().toString() + ".rank");
    }
}