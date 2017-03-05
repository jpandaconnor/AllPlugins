package co.uk.randompanda30.houseshop.query;

import co.uk.randompanda30.houseshop.HouseShop;
import co.uk.randompanda30.houseshop.HouseState;
import co.uk.randompanda30.houseshop.TEMP;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Created by panda on 02/05/16.
 */
public class SelectionQ {

    public static boolean isHouse(String name) {
        return TEMP.datac.contains(name);
    }

    public static HouseState getHouseState(String name) {
        HouseState state = null;
        String stateString = (String) TEMP.datac.get(name + ".state");
        switch (stateString) {
            case "FORSALE":
                state = HouseState.FORSALE;
                break;
            case "OWNED":
                state = HouseState.OWNED;
                break;
        }
        return state;
    }

    public static boolean isInHouse(Player player) {
        Location location = player.getLocation();

        ApplicableRegionSet rgs = HouseShop.getWorldGuard().getRegionManager(location.getWorld()).getApplicableRegions(
                new Vector(location.getX(), location.getY(), location.getZ()));

        if(!rgs.getRegions().isEmpty()) {
            for(ProtectedRegion rr : rgs.getRegions()) {
                if(rr.getId().contains("hs_")) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String getHouse(Player player) {
        Location location = player.getLocation();

        ApplicableRegionSet rgs = HouseShop.getWorldGuard().getRegionManager(location.getWorld()).getApplicableRegions(
                new Vector(location.getX(), location.getY(), location.getZ()));

        if(!rgs.getRegions().isEmpty()) {
            for(ProtectedRegion rr : rgs.getRegions()) {
                if(rr.getId().contains("hs_")) {
                    return rr.getId();
                }
            }
        }
        return null;
    }
}