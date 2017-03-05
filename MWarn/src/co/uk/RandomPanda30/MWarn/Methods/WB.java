package co.uk.RandomPanda30.MWarn.Methods;

import co.uk.RandomPanda30.MWarn.B;
import co.uk.RandomPanda30.MWarn.S;

public class WB {

	public static S getState(String state) {
		String rawState = B.dataC.getString(state);
		S s = null;
		switch (rawState) {
		case "ACTIVE":
			s = S.ACTIVE;
			break;
		case "EXPIRED":
			s = S.EXPIRED;
			break;
		case "REMOVED":
			s = S.REMOVED;
			break;
		}
		return s;
	}

	public static int getWarningExpirery() {
		return (Integer) B.configC.get("WARNINGS.BANLENGTH");
	}

	public static int getBanLength() {
		return (Integer) B.configC.get("WARNINGS.BANLENGTH");
	}

	public static int getWarningThreshold() {
		return (Integer) B.configC.get("WARNINGS.THRESHOLD");
	}
}