package co.uk.randompanda30.fileguilds.events;

import co.uk.randompanda30.fileguilds.Guilds;
import co.uk.randompanda30.fileguilds.data.PlayerData;
import co.uk.randompanda30.fileguilds.object.GuildOB;
import co.uk.randompanda30.fileguilds.string.Format;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class OnAsyncPlayerChatEvent implements Listener {

    public OnAsyncPlayerChatEvent() {
        Guilds.getPlugin().getServer().getPluginManager().registerEvents(this, Guilds.getPlugin());
    }

    @EventHandler
    public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if (PlayerData.isInGuild(player)) {
            GuildOB guild = new GuildOB(PlayerData.getGuild(player));
            event.setMessage(Format.format("&f[&a" + guild.getTag() + "&f] ") + event.getMessage());
        }
    }
}