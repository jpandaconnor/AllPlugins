package co.uk.randompanda30.petstorage.objects;

import co.uk.randompanda30.petstorage.PetStorage;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.util.UnsafeList;
import org.bukkit.entity.Cow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;

import java.lang.reflect.Field;
import java.util.List;

public class CustomCow implements Listener {

    public PetStorage plugin;
    public Player player;

    public CustomCow(PetStorage plugin, Player player) {
        this.player = player;
        this.plugin = plugin;

        Entity e = (Entity) player.getWorld().spawnEntity(player.getLocation(), EntityType.COW);
        Cow cow = (Cow) e;

        cow.setRemoveWhenFarAway(false);
        cow.setTarget(player);
    }

}