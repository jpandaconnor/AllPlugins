package co.uk.randompanda30.party.events;

import co.uk.randompanda30.party.Party;
import co.uk.randompanda30.party.TEMP;
import co.uk.randompanda30.party.data.PlayerData;
import co.uk.randompanda30.party.object.PartyOB;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by panda on 16/04/16.
 */
public class OnEntityDamageByEntityEvent implements Listener {

    public OnEntityDamageByEntityEvent() {
        Party.getPlugin().getServer().getPluginManager().registerEvents(this, Party.getPlugin());
    }

    @EventHandler
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();

        if(!(entity instanceof Player)) {
            if(event.getDamager() instanceof Player) {


                Player player = (Player) event.getDamager();
                UUID uuid = player.getUniqueId();

                if(PlayerData.inParty(uuid)) {
                    PartyOB partyOB = PlayerData.getParty(uuid);

                    ArrayList<UUID> players;

                    if(partyOB.getHitEntities().get(entity) != null ){
                        players = partyOB.getHitEntities().get(entity);
                    } else {
                        players = new ArrayList<>();
                    }

                    if(!(players.contains(uuid))) {
                        players.add(uuid);
                        partyOB.addHitEntity(entity, players);
                    }
                } else {
                    ArrayList<UUID> players;

                    if(TEMP.normiesHit.get(entity) != null) {
                        players = TEMP.normiesHit.get(entity);
                    } else {
                        players = new ArrayList<>();
                    }

                    if(!players.contains(uuid)) {
                        players.add(uuid);
                    }

                    TEMP.normiesHit.put(entity, players);
                }
            }
        } else {
            if(event.getDamager() instanceof Player) {
                Player newEntity = (Player) entity;
                Player d = (Player) event.getDamager();

                if(PlayerData.inParty(newEntity.getUniqueId())
                        && PlayerData.inParty(d.getUniqueId())) {
                    if(PlayerData.getParty(newEntity.getUniqueId()).getPlayers().contains(d.getUniqueId())) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }
}