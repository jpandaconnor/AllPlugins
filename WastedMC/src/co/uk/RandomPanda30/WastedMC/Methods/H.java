package co.uk.RandomPanda30.WastedMC.Methods;

import java.lang.reflect.InvocationTargetException;

public class H {

	public static Object h(Object object) {
		try {
			return M.m(object.getClass(), "getHandle", new Class[0]).invoke(object,
					new Object[0]);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
}