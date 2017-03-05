package co.uk.randompanda30.houseshop.object;

import org.bukkit.Bukkit;
import org.bukkit.Location;

/**
 * Created by panda on 16/06/16.
 */
public class HS {

    public Location location;

    public HS(String location) {
        // args[0] = world
        // args[1] = x
        // args[2] = y
        // args[3] = z

        String[] l = location.split(";");
        this.location = new Location(Bukkit.getWorld(l[0]),
                Integer.parseInt(l[1]),
                Integer.parseInt(l[2]),
                Integer.parseInt(l[3]));
    }

    public HS(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
}