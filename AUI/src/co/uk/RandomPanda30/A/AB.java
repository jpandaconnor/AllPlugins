package co.uk.RandomPanda30.A;

import org.bukkit.plugin.PluginDescriptionFile;

public class AB {

	private static PluginDescriptionFile pdfFile;

	public static PluginDescriptionFile getDataFile() {
		return pdfFile;
	}

	public static void setDataFile() {
		pdfFile = A.getInstance().getDescription();
	}

}
