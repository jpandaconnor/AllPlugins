package co.uk.randompanda30.fileguilds.events;

import co.uk.randompanda30.fileguilds.Guilds;
import co.uk.randompanda30.fileguilds.config.Guild;
import co.uk.randompanda30.fileguilds.data.PlayerData;
import co.uk.randompanda30.fileguilds.object.GuildOB;
import co.uk.randompanda30.fileguilds.object.Rank;
import co.uk.randompanda30.fileguilds.string.Dispatch;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;

import static co.uk.randompanda30.fileguilds.TEMP.guildsc;

public class OnPlayerJoinEvent implements Listener {

    public OnPlayerJoinEvent() {
        Guilds.getPlugin().getServer().getPluginManager().registerEvents(this, Guilds.getPlugin());
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (PlayerData.isInGuild(player)) {
            GuildOB guildOB = new GuildOB(PlayerData.getGuild(player));
            Dispatch.sendMessage(guildOB.getMOTD(), player);

            // Member list fix here
            Rank rank = guildOB.getRank(PlayerData.getRank(player));

            if (rank.getName().equals("Leader")) {
                if (!guildOB.getMembers().contains(player.getUniqueId().toString())) {
                    ArrayList<String> members = new ArrayList<>(guildOB.getMembers());
                    members.add(player.getUniqueId().toString());
                    guildsc.set(guildOB.getName() + ".members", members);
                    Guild.save();
                }
            }
        }
    }
}