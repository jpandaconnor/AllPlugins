package co.uk.randompanda30.party.data;

import co.uk.randompanda30.party.TEMP;
import co.uk.randompanda30.party.object.PartyOB;

import java.util.UUID;

/**
 * Created by panda on 15/04/16.
 */
public class PlayerData {

    public static boolean inParty(UUID uuid) {
        for(PartyOB p : TEMP.parties) {
            if(p.getPlayers().contains(uuid)) {
                return true;
            }
        }
        return false;
    }

    public static PartyOB getParty(UUID uuid) {
        for(PartyOB p: TEMP.parties) {
            if(p.getPlayers().contains(uuid)) {
                return p;
            }
        }
        return null;
    }
}