package co.uk.RandomPanda30.CasinoM.Files;

public enum Config {

	COMMAND_WAND (true), COMMAND_CREATE (true);

	public Object value;

	Config (Object value) {
		this.value = value;
	}
}