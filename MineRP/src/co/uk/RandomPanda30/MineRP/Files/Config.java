package co.uk.RandomPanda30.MineRP.Files;

public enum Config {

	RP_WORLD ("downtown"),

	PAY_NEWPLAYER (1000),

	PAY_INTERVAL (10),

	TEST ("TEST");

	public Object value;

	Config (Object value) {
		this.value = value;
	}

}
