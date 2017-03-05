package co.uk.randompanda30.houseshop.string;

import org.bukkit.Bukkit;
import org.bukkit.Location;

/**
 * Created by panda on 16/05/16.
 */
public class LocationS {

    /*
    worldname;x;y;z
    0         1 2 3
     */

    public static String decompileLocation(Location location) {
        String s = location.getWorld().getName() + ";" + Integer.toString((int) location.getX()) + ";" +
                Integer.toString((int) location.getY()) + ";" + Integer.toString((int) location.getZ());
        return s;
    }

    public static Location compileLocation(String s) {
        String[] ss = s.split(";");
        return new Location(Bukkit.getWorld(ss[0]),
                Integer.parseInt(ss[1]), Integer.parseInt(ss[2]), Integer.parseInt(ss[3]));
    }
}