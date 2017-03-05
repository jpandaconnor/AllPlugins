package co.uk.RandomPanda30.ExpIntercept.Methods;

import java.io.IOException;

import co.uk.RandomPanda30.ExpIntercept.ExpInterceptData;

public class IO {

	public static void setAmount(int amount) {
		ExpInterceptData.configC.set("DIVDEDAMOUNT", amount);
		try {
			ExpInterceptData.configC.save(ExpInterceptData.config);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static int getAmount() {
		return (Integer) ExpInterceptData.configC.get("DIVDEDAMOUNT");
	}

	public static boolean isNegative(double amount) {
		return amount < 0;
	}
}