package co.uk.randompanda30.publicchat.events;

import co.uk.randompanda30.publicchat.PublicChat;
import co.uk.randompanda30.publicchat.config.Config;
import co.uk.randompanda30.publicchat.string.Format;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.io.File;
import java.io.IOException;

public class OnAsyncPlayerChatEvent implements Listener {

    public OnAsyncPlayerChatEvent() {
        PublicChat.getPlugin().getServer().getPluginManager().registerEvents(this, PublicChat.getPlugin());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        int d = (Integer) Config.ConfigValues.LOCAL_DISTANCE.value;

        String finalMessage;

        File guilds = new File("plugins/Guilds/guilds.yml");
        File data = new File("plugins/Guilds/data.yml");

        FileConfiguration guildsConfiguration = new YamlConfiguration();
        FileConfiguration dataConfiguration = new YamlConfiguration();

        try {
            guildsConfiguration.load(guilds);
            dataConfiguration.load(data);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

        if (dataConfiguration.contains(player.getUniqueId().toString())) {
            String pguild = (String) dataConfiguration.get(player.getUniqueId().toString() + ".guild");
            String tag = (String) guildsConfiguration.get(pguild + ".tag");

            if (event.getMessage().contains("[") && event.getMessage().contains("]")) {
                finalMessage = event.getMessage().replace(Format.format("&f[&a" + tag + "&f]") + " ", "");
            } else {
                finalMessage = event.getMessage();
            }
        } else {
            finalMessage = event.getMessage();
        }


        if (!finalMessage.startsWith("!")) {
            event.getRecipients().clear();
            event.setFormat(Format.format("&f(&cLocal&f) " + event.getFormat()));
            Bukkit.getOnlinePlayers().stream().filter(p -> p.getWorld().equals(player.getWorld())).filter(p ->
                    p.getLocation().distance(player.getLocation()) <= d).forEach(p -> event.getRecipients().add(p));
        } else {
            event.setFormat(Format.format("&f(&6Global&f) " + event.getFormat()));
            if(finalMessage.startsWith("!")) {
                event.setMessage(event.getMessage().replaceFirst("!", ""));
            }
        }
    }
}