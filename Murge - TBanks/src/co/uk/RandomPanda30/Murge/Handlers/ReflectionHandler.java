package co.uk.RandomPanda30.Murge.Handlers;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ReflectionHandler {

	private static ReflectionHandler instance = new ReflectionHandler();

	public static ReflectionHandler getHandler() {
		return instance;
	}

	public Class<?> getNMSClass(String nmsClassString)
			throws ClassNotFoundException {
		String version = Bukkit.getServer().getClass().getPackage().getName()
				.replace(".", ",").split(",")[3]
				+ ".";
		String name = "net.minecraft.server." + version + nmsClassString;
		Class<?> nmsClass = Class.forName(name);
		return nmsClass;
	}

	public Object getConnection(Player player) throws SecurityException,
			NoSuchMethodException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		Method getHandle = player.getClass().getMethod("getHandle");
		Object nmsPlayer = getHandle.invoke(player);
		Field conField = nmsPlayer.getClass().getField("playerConnection");
		Object con = conField.get(nmsPlayer);
		return con;
	}
}