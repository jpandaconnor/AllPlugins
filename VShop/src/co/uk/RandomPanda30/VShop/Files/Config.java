package co.uk.RandomPanda30.VShop.Files;

public enum Config {

	PLUGIN_ENABLED (true),

	CMD_WAND (true),
	CMD_CREATE(true);

	public Object value;

	Config (Object value) {
		this.value = value;
	}

}
