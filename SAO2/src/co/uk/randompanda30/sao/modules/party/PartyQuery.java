package co.uk.randompanda30.sao.modules.party;/*
   Created by Connor Brady on 13/10/2016.

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

public class PartyQuery {

    public PartyQuery() {}

    public static boolean isPlayerInParty(Player player) {
        return isPlayerInParty(player.getUniqueId());
    }

    private static boolean isPlayerInParty(UUID uuid) {
        for(Party p : TEMP.parties) {
            if(p.isInParty(uuid)) {
                return true;
            }
        }
        return false;
    }

    public static Party getPlayerParty(Player player) {
        return getPlayerParty(player.getUniqueId());
    }

    private static Party getPlayerParty(UUID uuid) {
        for(Party p : TEMP.parties) {
            if(p.isInParty(uuid)) {
                return p;
            }
        }
        return null;
    }

    public static int getSizeWOLeader(Party party, Player player) {
        return getSizeWOLeader(party, player.getUniqueId());
    }

    private static int getSizeWOLeader(Party party, UUID uuid) {
        ArrayList<UUID> players = party.getOccupants();
        players.remove(uuid);

        return players.size();
    }
}