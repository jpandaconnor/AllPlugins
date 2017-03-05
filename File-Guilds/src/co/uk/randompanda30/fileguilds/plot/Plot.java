package co.uk.randompanda30.fileguilds.plot;

import org.bukkit.Location;

public class Plot {

    public String world;

    public Location p1;
    public Location p2;
    public Location spawn;

    public Plot(String world, Location p1, Location p2, Location spawn) {
        this.world = world;
        this.p1 = p1;
        this.p2 = p2;
        this.spawn = spawn;
    }

    public Location getPoint1() {
        return p1;
    }

    public Location getPoint2() {
        return p2;
    }

    public Location getSpawn() {
        return spawn;
    }
}