package co.uk.RandomPanda30.Murge.Collection.Bases;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.entity.Player;

public interface EffectsBase {

	public boolean isEffect();

	public void playEffect(Player player) throws NoSuchMethodException,
			SecurityException, ClassNotFoundException, InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchFieldException;

	public void setEffect(boolean value);
}