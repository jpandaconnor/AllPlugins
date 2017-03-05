package co.uk.RandomPanda30.WastedMC.Methods;

import java.lang.reflect.Method;

public class M {

	public static Method m(Class<?> c, String name, Class<?>... args) {
		for (Method m : c.getMethods()) {
			if ((m.getName().equals(name))
					&& ((args.length == 0) || (C.c(args, m.getParameterTypes())))) {
				m.setAccessible(true);
				return m;
			}
		}
		return null;
	}
}