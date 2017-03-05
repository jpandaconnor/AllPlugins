package co.uk.randompanda30.fileguilds.events;

import co.uk.randompanda30.fileguilds.Guilds;
import co.uk.randompanda30.fileguilds.data.PlayerData;
import co.uk.randompanda30.fileguilds.object.GuildOB;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class OnEntityDamageByEntityEvent implements Listener {

    public OnEntityDamageByEntityEvent() {
        Guilds.getPlugin().getServer().getPluginManager().registerEvents(this, Guilds.getPlugin());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
        if (event.getEntity() == null) {
            return;
        }

        if ((event.getEntity() instanceof Player) && (event.getDamager() instanceof Player)) {
            Player player = (Player) event.getDamager();
            Player dam = (Player) event.getEntity();

            if (PlayerData.isInGuild(player) && PlayerData.isInGuild(dam)) {
                if (!new GuildOB(PlayerData.getGuild(player)).getName().
                        equals(new GuildOB(PlayerData.getGuild(dam)).getName())) {
                    if (new GuildOB(PlayerData.getGuild(player)).getEnemys().
                            contains(new GuildOB(PlayerData.getGuild(dam)).getName())) {
                        event.setCancelled(false);
                    }
                } else {
                    GuildOB guild = new GuildOB(PlayerData.getGuild(player));
                    if (guild.getPVP()) {
                        event.setCancelled(false);
                    }
                }
            }
        }
    }
}