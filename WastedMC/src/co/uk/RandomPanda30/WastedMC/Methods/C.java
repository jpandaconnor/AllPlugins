package co.uk.RandomPanda30.WastedMC.Methods;

public class C {

	public static boolean c(Class<?>[] c, Class<?>[] cc) {
		boolean equal = true;
		if (c.length != cc.length) {
			return false;
		}
		for (int i = 0; i < c.length; i++) {
			if (c[i] != cc[i]) {
				equal = false;
				break;
			}
		}
		return equal;
	}
}