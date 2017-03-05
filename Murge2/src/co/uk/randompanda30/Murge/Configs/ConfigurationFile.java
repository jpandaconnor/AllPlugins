package co.uk.RandomPanda30.Murge.Configs;

public class ConfigurationFile {

	public enum FileType {
		CONFIG, MESSAGES, ALL;
	}

	public ConfigurationFile (FileType type) {
		if (type != FileType.ALL) {
			init(type);
		} else {
			init();
		}
	}

	public void init() {
		initConfig();
		initMessages();
	}

	public void init(FileType type) {
		switch (type) {
		case CONFIG:
			initConfig();
			break;
		case MESSAGES:
			initMessages();
			break;
		default:
			break;
		}
	}

	private void initConfig() {
		
	}

	private void initMessages() {

	}

}