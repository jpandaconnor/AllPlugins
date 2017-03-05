package co.uk.RandomPanda30.Murge.Collection.Effects;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.Collection.Bases.EffectsBase;
import co.uk.RandomPanda30.Murge.Handlers.ReflectionHandler;
import co.uk.RandomPanda30.Murge.Values.Enums.ConfigValues;

public class BleedCollection implements EffectsBase {
	
	private static BleedCollection	instance	= new BleedCollection();
	
	public static BleedCollection getCollection() {
		return instance;
	}
	
	@Override
	public boolean isEffect() {
		return (Boolean) Murge.cMap.getStat(ConfigValues.EFFECT_BLEED);
	}
	
	@Override
	public void playEffect(Player player) throws NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException,
											IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		Location location = player.getLocation();
		
		Class<?> packetClass = ReflectionHandler.getHandler().getNMSClass("PacketPlayOutWorldParticles");
		Constructor<?> packetConstructor =
											packetClass.getConstructor(	net.minecraft.server.v1_8_R3.EnumParticle.class, boolean.class, float.class,
																		float.class, float.class, float.class, float.class, float.class, float.class,
																		int.class, int[].class);
		float x = (float) location.getX();
		float y = (float) location.getY();
		float z = (float) location.getZ();
		for (Entity entity : location.getWorld().getEntities()) {
			if (entity instanceof Player) {
				if (location.distance(entity.getLocation()) < 333) {
					Player near = (Player) entity;
					Object packet =
									packetConstructor.newInstance(	net.minecraft.server.v1_8_R3.EnumParticle.DRIP_LAVA, true, x, y, z, 0.5f, 1.0f,
																	0.5f, 100, 100, null);
					Method sendPacket =
										ReflectionHandler.getHandler().getNMSClass("PlayerConnection")
															.getMethod("sendPacket", ReflectionHandler.getHandler().getNMSClass("Packet"));
					sendPacket.invoke(ReflectionHandler.getHandler().getConnection(near), packet);
					
					player.playEffect(EntityEffect.HURT);
				}
			}
		}
	}
	
	@Override
	public void setEffect(boolean value) {
		Murge.cMap.setStat(ConfigValues.EFFECT_BLEED, value);
	}
}
