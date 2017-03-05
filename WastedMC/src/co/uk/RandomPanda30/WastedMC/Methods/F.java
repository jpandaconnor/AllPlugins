package co.uk.RandomPanda30.WastedMC.Methods;

import java.lang.reflect.Field;

public class F {

	public static Field getField(Class<?> c, String name) {
		try {
			Field field = c.getDeclaredField(name);
			field.setAccessible(true);
			return field;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
