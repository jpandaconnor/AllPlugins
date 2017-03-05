package co.uk.randompanda30.party.events;

import co.uk.randompanda30.party.Party;
import co.uk.randompanda30.party.TEMP;
import co.uk.randompanda30.party.data.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by panda on 15/04/16.
 */
public class OnEntityDeathEvent implements Listener {

    public OnEntityDeathEvent() {
        Party.getPlugin().getServer().getPluginManager().registerEvents(this, Party.getPlugin());
    }

    @EventHandler
    public void onEntityDeathEvent(EntityDeathEvent event) {
        Entity entity = event.getEntity();

        if(!(entity instanceof Player)) {
            if(event.getEntity().getKiller() != null) {
                Player player = event.getEntity().getKiller();
                ArrayList<UUID> normies = null;

                if (TEMP.normiesHit.get(entity) != null) {
                    normies = TEMP.normiesHit.get(entity);
                }

                int xp = event.getDroppedExp();
                event.setDroppedExp(0);

                if (TEMP.parties != null) {
                    if (!PlayerData.inParty(player.getUniqueId())) {
                        if (normies != null) {
                            int people = normies.size();

                            int newXP = ((int) xp / people);

                            normies.stream().filter(uuid -> Bukkit.getServer().getPlayer(uuid) != null && Bukkit.getServer()
                                    .getPlayer(uuid).isOnline()).forEach(uuid -> Bukkit.getServer().getPlayer(uuid).giveExp(newXP));
                        }
                    } else {
                        TEMP.parties.stream().filter(ob -> ob.getHitEntities() != null).filter(ob ->
                                ob.getHitEntities().containsKey(entity)).forEach(ob -> {
                            ArrayList<UUID> hits = ob.getHitEntities().get(entity);

                            int newXP = ((int) xp / hits.size());

                            hits.stream().filter(hit -> Bukkit.getServer().getPlayer(hit) != null && Bukkit.getServer()
                                    .getPlayer(hit).isOnline()).forEach(hit -> Bukkit.getServer().getPlayer(hit).giveExp(newXP));
                        });
                    }
                }
            }
        }
    }
}