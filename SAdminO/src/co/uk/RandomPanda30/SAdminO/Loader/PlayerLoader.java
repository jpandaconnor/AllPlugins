package co.uk.RandomPanda30.SAdminO.Loader;

import co.uk.RandomPanda30.SAdminO.Util.ReflectionUtil;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.UUID;

@SuppressWarnings({"deprecation", "rawtypes", "unchecked"})
public class PlayerLoader {

    public static Player loadPlayer(String name) {
        return loadPlayer(Bukkit.getOfflinePlayer(name));
    }

    public static Player loadPlayer(UUID id) {
        return loadPlayer(Bukkit.getOfflinePlayer(id));
    }

    public static Player loadPlayer(OfflinePlayer player) {
        if (player == null)
            return null;
        if (player instanceof Player) {
            return (Player) player;
        }
        return loadPlayer(player.getUniqueId(), player.getName());
    }

    private static Player loadPlayer(UUID id, String name) {
        Object server = getMinecraftServer();
        Object interactManager = newPlayerInteractManager();
        Object worldServer = getWorldServer();
        Object profile = newGameProfile(id, name);
        Class<?> entityPlayerClass = ReflectionUtil.getNmsClass("EntityPlayer");
        Constructor entityPlayerConstructor = ReflectionUtil.makeConstructor(entityPlayerClass,
                ReflectionUtil.getNmsClass("MinecraftServer"), ReflectionUtil.getNmsClass("WorldServer"),
                ReflectionUtil.getUtilClass("com.mojang.authlib.GameProfile"),
                ReflectionUtil.getNmsClass("PlayerInteractManager"));
        Object entityPlayer = ReflectionUtil.callConstructor(entityPlayerConstructor, server, worldServer, profile,
                interactManager);
        Player player = (Player) getBukkitEntity(entityPlayer);
        return player;
    }

    private static Object newGameProfile(UUID id, String name) {
        Class<?> gameProfileClass = ReflectionUtil.getUtilClass("com.mojang.authlib.GameProfile");
        if (gameProfileClass == null) {
            return name;
        }
        Constructor gameProfileConstructor = null;
        gameProfileConstructor = ReflectionUtil.makeConstructor(gameProfileClass, UUID.class, String.class);
        if (gameProfileConstructor == null) {
            gameProfileConstructor = ReflectionUtil.makeConstructor(gameProfileClass, String.class, String.class);
            return ReflectionUtil.callConstructor(gameProfileConstructor, id.toString(), name);
        } else {
            return ReflectionUtil.callConstructor(gameProfileConstructor, id, name);
        }
    }

    private static Object newPlayerInteractManager() {
        Object worldServer = getWorldServer();
        Class<?> playerInteractClass = ReflectionUtil.getNmsClass("PlayerInteractManager");
        Class<?> worldClass = ReflectionUtil.getNmsClass("World");
        Constructor c = ReflectionUtil.makeConstructor(playerInteractClass, worldClass);
        return ReflectionUtil.callConstructor(c, worldServer);
    }

    private static Object getWorldServer() {
        Object server = getMinecraftServer();
        Class<?> minecraftServerClass = ReflectionUtil.getNmsClass("MinecraftServer");
        Method getWorldServer = ReflectionUtil.makeMethod(minecraftServerClass, "getWorldServer", int.class);
        return ReflectionUtil.callMethod(getWorldServer, server, 0);
    }

    private static Object getMinecraftServer() {
        return ReflectionUtil.callMethod(
                ReflectionUtil.makeMethod(ReflectionUtil.getCbClass("CraftServer"), "getServer"), Bukkit.getServer());
    }

    private static Entity getBukkitEntity(Object o) {
        Method getBukkitEntity = ReflectionUtil.makeMethod(o.getClass(), "getBukkitEntity");
        return ReflectionUtil.callMethod(getBukkitEntity, o);
    }
}