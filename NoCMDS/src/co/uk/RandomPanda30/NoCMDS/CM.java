package co.uk.RandomPanda30.NoCMDS;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;

public class CM {

	public static void initConfig() {
		try {
			NoCMDS.config = new File("plugins/" + NoCMDS.pdfFile.getName(),
					"config.yml");
			if (!NoCMDS.config.exists()) {
				NoCMDS.config.getParentFile().mkdirs();
				NoCMDS.config.createNewFile();
			}

			NoCMDS.configC = new YamlConfiguration();
			NoCMDS.configCS = NoCMDS.configC.getConfigurationSection("");
			NoCMDS.configC.load(NoCMDS.config);

			for (Config value : Config.values()) {
				if (NoCMDS.configCS
						.get(value.name().replaceAll("_", ".")) == null) {
					NoCMDS.configCS.set(value.name().replaceAll("_", "."),
							value.value);
					NoCMDS.configC.save(NoCMDS.config);
				}

				value.value = NoCMDS.configCS
						.get(value.name().replaceAll("_", "."));
			}

			NoCMDS.configC.load(NoCMDS.config);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}