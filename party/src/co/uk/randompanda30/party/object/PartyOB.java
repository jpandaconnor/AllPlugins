package co.uk.randompanda30.party.object;

import co.uk.randompanda30.party.Party;
import co.uk.randompanda30.party.TEMP;
import co.uk.randompanda30.party.config.Messages;
import co.uk.randompanda30.party.string.Dispatch;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PartyOB {

    public UUID leader;
    public ArrayList<UUID> players;

    public HashMap<Entity, ArrayList<UUID>> hitEntities = new HashMap<>();

    public PartyOB(UUID leader) {
        players = new ArrayList<>();
        players.add(leader);

        this.leader = leader;

        TEMP.parties.add(this);
    }

    public UUID getLeader() {
        return leader;
    }

    public ArrayList<UUID> getPlayers() {
        return players;
    }

    public void invitePlayer(Player target, Player sender) {
        new Invite(target, sender, this, Party.getPlugin());
    }

    public UUID getPlayer(UUID uuid) {
        for(UUID uuids: getPlayers()) {
            if(uuids.equals(uuid)) {
                return uuids;
            }
        }
        return null;
    }

    public void addPlayer(UUID uuid) {
        players.add(uuid);
    }

    public void addPlayer(Player player) {
        addPlayer(player.getUniqueId());
    }

    public List<Player> getPlayersOB() {
        ArrayList<Player> p = getPlayers().stream().filter(uuid -> Bukkit.getPlayer(uuid) != null &&
                Bukkit.getPlayer(uuid).isOnline()).map(Bukkit::getPlayer).collect(Collectors.toCollection(ArrayList::new));
        return p;
    }

    public void bruteKickPlayer(Player player) {
        Dispatch.sendMessage((String) Messages.MessagesValues.PARTY_KICK_KICKED.value, player);
        getPlayersOB().stream().filter(p -> !p.equals(player)).forEach(p -> {
            Dispatch.sendMessage(Messages.MessagesValues.PARTY_KICK_HASKICKED.value.toString().
                    replace("%player", player.getName()), p);
        });

        if(players.contains(player.getUniqueId())) {
            players.remove(player.getUniqueId());
        }
    }

    public void kickPlayer(Player player) {
        if(leader.equals(player.getUniqueId())) {
            disbandParty();
            return;
        }

        Dispatch.sendMessage((String) Messages.MessagesValues.PARTY_LEAVE_YOULEFT.value, player);
        getPlayersOB().stream().filter(p -> !p.equals(player)).forEach(p -> {
            Dispatch.sendMessage(Messages.MessagesValues.PARTY_LEAVE_HASLEFT.value.toString().
                    replace("%player", player.getName()), p);
        });

        if(players.contains(player.getUniqueId())) {
            players.remove(player.getUniqueId());
        }
    }

    public void kickPlayer(UUID player) {
        if(leader.equals(player)) {
            disbandParty();
            return;
        }

        getPlayersOB().stream().filter(p -> !p.getUniqueId().equals(player)).forEach(p ->
                Dispatch.sendMessage((String) Messages.MessagesValues.PARTY_LEAVE_HASLEFT.value, p));

        if(players.contains(player)) {
            players.remove(player);
        }
    }

    public void disbandParty() {
        sendGroupMessage((String) Messages.MessagesValues.PARTY_LEAVE_DISBANDED.value);
        if(TEMP.parties.contains(this)) {
            TEMP.parties.remove(this);
        }
    }

    public void sendGroupMessage(String message) {
        for(Player p : getPlayersOB()) {
            Dispatch.sendMessage(message, p);
        }
    }

    public HashMap<Entity, ArrayList<UUID>> getHitEntities() {
        return hitEntities;
    }

    public void addHitEntity(Entity entity, ArrayList<UUID> uuid) {
        hitEntities.put(entity, uuid);
    }


}