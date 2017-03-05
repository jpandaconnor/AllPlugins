package co.uk.RandomPanda30.Murge.Util;

public class KeyUtil {

	public enum Mode {
		ALPHA, ALPHANUMERIC, NUMERIC;
	}

	private String key;
	private Mode mode;
	private int length;

	public KeyUtil () {
		this.mode = Mode.NUMERIC;
		this.length = 10;
	}

	public KeyUtil (int length, Mode mode) {
		this.length = length;
		this.mode = mode;
	}

	public void generate() {
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
		this.key = sb.toString();
	}

	public String getKey() {
		return key;
	}
}