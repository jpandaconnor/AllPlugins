package co.uk.RandomPanda30.ItemHandlerPlus.Methods;

import java.io.IOException;

import co.uk.RandomPanda30.ItemHandlerPlus.B;

public class V {

	public static boolean isEnabled(int no) {
		return (Boolean) B.configC.get("SLOT." + Integer.toString(no));
	}

	public static void toggle(int no) {
		if (!isEnabled(no)) {
			B.configC.set("SLOT." + Integer.toString(no), true);
		} else {
			B.configC.set("SLOT." + Integer.toString(no), false);
		}
		try {
			B.configC.save(B.config);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}