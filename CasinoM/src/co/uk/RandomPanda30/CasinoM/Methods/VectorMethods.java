package co.uk.RandomPanda30.CasinoM.Methods;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.util.Vector;

public class VectorMethods {

	private Vector p1;
	private Vector p2;

	public VectorMethods (Vector p1, Vector p2) {
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

	public ArrayList<Entity> getItemFrames(World world) {
		int topBlockX = (p1.getBlockX() < p2.getBlockX() ? p2.getBlockX() : p1
				.getBlockX());
		int bottomBlockX = (p1.getBlockX() > p2.getBlockX() ? p2.getBlockX()
				: p1.getBlockX());

		int topBlockY = (p1.getBlockY() < p2.getBlockY() ? p2.getBlockY() : p1
				.getBlockY());
		int bottomBlockY = (p1.getBlockY() > p2.getBlockY() ? p2.getBlockY()
				: p1.getBlockY());

		int topBlockZ = (p1.getBlockZ() < p2.getBlockZ() ? p2.getBlockZ() : p1
				.getBlockZ());
		int bottomBlockZ = (p1.getBlockZ() > p2.getBlockZ() ? p2.getBlockZ()
				: p1.getBlockZ());
		ArrayList<Entity> entities = new ArrayList<Entity>();
		for (int x = bottomBlockX; x <= topBlockX; x++) {
			for (int z = bottomBlockZ; z <= topBlockZ; z++) {
				for (int y = bottomBlockY; y <= topBlockY; y++) {
					Location el = new Location(world, x, y, z);
					for (Entity entity : el.getChunk().getEntities()) {
						if (entity instanceof ItemFrame) {
							entities.add(entity);
						}
					}
				}
			}
		}
		return entities;
	}
}