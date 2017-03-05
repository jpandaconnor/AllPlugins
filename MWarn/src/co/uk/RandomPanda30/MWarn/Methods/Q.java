package co.uk.RandomPanda30.MWarn.Methods;

import co.uk.RandomPanda30.MWarn.M;

public class Q {

	public static String generateRandomString(int length, M mode)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		String characters = "";
		switch (mode) {
		case ALPHA:
			characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
			break;
		case ALPHANUMERIC:
			characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
			break;
		case NUMERIC:
			characters = "1234567890";
			break;
		}

		int charactersLength = characters.length();
		for (int i = 0; i < length; i++) {
			double index = Math.random() * charactersLength;
			sb.append(characters.charAt((int) index));
		}
		return sb.toString();
	}
}