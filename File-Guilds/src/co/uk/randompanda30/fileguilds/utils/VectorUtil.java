package co.uk.randompanda30.fileguilds.utils;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public class VectorUtil {

    private Vector p1;
    private Vector p2;

    public VectorUtil(Vector p1, Vector p2) {
        int x1 = Math.min(p1.getBlockX(), p2.getBlockX());
        int y1 = Math.min(p1.getBlockY(), p2.getBlockY());
        int z1 = Math.min(p1.getBlockZ(), p2.getBlockZ());

        int x2 = Math.max(p1.getBlockX(), p2.getBlockX());
        int y2 = Math.max(p1.getBlockY(), p2.getBlockY());
        int z2 = Math.max(p1.getBlockZ(), p2.getBlockZ());
        this.p1 = new Vector(x1, y1, z1);
        this.p2 = new Vector(x2, y2, z2);
    }

    public boolean contains(Location loc) {
        if (loc == null) {
            return false;
        }
        return loc.getBlockX() >= p1.getBlockX()
                && loc.getBlockX() <= p2.getBlockX()
                && loc.getBlockY() >= p1.getBlockY()
                && loc.getBlockY() <= p2.getBlockY()
                && loc.getBlockZ() >= p1.getBlockZ()
                && loc.getBlockZ() <= p2.getBlockZ();
    }
}