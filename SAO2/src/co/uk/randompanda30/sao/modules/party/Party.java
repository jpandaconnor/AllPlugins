package co.uk.randompanda30.sao.modules.party;

/*
   Created by Connor Brady on 12/10/2016.

   Copyright 2016 JPanda (Connor Brady)

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

import co.uk.randompanda30.sao.TEMP;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class Party {

    UUID leader;
    ArrayList<UUID> players = new ArrayList<>();

    public Party(Player leader) {
        new Party(leader.getUniqueId());
    }

    public Party(UUID leader) {
        this.leader = leader;
        players.add(leader);
        TEMP.parties.add(this);
    }

    public void add(UUID uuid) {
        players.add(uuid);
    }

    public void add(Player player) {
        add(player.getUniqueId());
    }

    public void remove(UUID uuid) {
        players.remove(uuid);
    }

    public void remove(Player player) {
        remove(player.getUniqueId());
    }

    public boolean isInParty(UUID uuid) {
        return players.contains(uuid);
    }

    public boolean isInParty(Player player) {
        return isInParty(player.getUniqueId());
    }

    public boolean isLeader(UUID uuid) {
        return leader.equals(uuid);
    }

    public boolean isLeader(Player player) {
        return isLeader(player.getUniqueId());
    }

    public ArrayList<UUID> getOccupants() { return players; }
}