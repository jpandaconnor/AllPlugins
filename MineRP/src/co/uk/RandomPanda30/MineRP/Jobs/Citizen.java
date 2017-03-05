package co.uk.RandomPanda30.MineRP.Jobs;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;

import co.uk.RandomPanda30.MineRP.MineRPData;
import co.uk.RandomPanda30.MineRP.Files.JobEnums.CitizenE;

public class Citizen {

	public static int getSalary() {
		return (Integer) MineRPData.citizenC.get("SALARY");
	}

	public static String getColour() {
		return (String) MineRPData.citizenC.get("COLOUR");
	}

	public static boolean getVote() {
		return (Boolean) MineRPData.citizenC.get("VOTE");
	}

	public static void setSalary(int amount) {
		MineRPData.citizenC.set("SALARY", amount);
	}

	public static void setColour(String colour) {
		MineRPData.citizenC.set("COLOUR", colour);
	}

	public static void setVote(boolean arg) {
		MineRPData.citizenC.set("VOTE", arg);
	}

	public static void initCitizenFile() {
		try {
			MineRPData.citizen = new File("plugins/"
					+ MineRPData.getDataFile().getName() + "/jobs",
					"citizen.yml");
			if (!MineRPData.citizen.exists()) {
				MineRPData.citizen.getParentFile().mkdirs();
				MineRPData.citizen.createNewFile();
			}

			MineRPData.citizenC = new YamlConfiguration();
			MineRPData.citizenCS = MineRPData.citizenC
					.getConfigurationSection("");
			MineRPData.citizenC.load(MineRPData.citizen);

			for (CitizenE value : CitizenE.values()) {
				if (MineRPData.citizenCS.get(value.name().replaceAll("_", ".")) == null) {
					MineRPData.citizenCS.set(value.name().replaceAll("_", "."),
							value.value);
					MineRPData.citizenC.save(MineRPData.citizen);
				}

				value.value = MineRPData.citizenCS.get(value.name().replaceAll(
						"_", "."));
			}
			MineRPData.citizenC.load(MineRPData.citizen);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}