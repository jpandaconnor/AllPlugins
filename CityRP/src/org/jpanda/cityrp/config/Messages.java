package org.jpanda.cityrp.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jpanda.cityrp.CityRP;

public class Messages {

	public enum MessagesValues {
		ARG ("&4"),
		ERROR ("&c"),
		HEADER ("&6"),
		NORMAL ("&f"),
		TEXT ("&7"),
		TAG ("%A[%NCityRP%A]%N"),
		WARNING ("&4"),
		GOOD ("&a"),
		BAD ("&c"),

		SETUP_DATABASE_STEP1("%TAG %NPlease enter the IP of the MySQL server"),
		SETUP_DATABASE_STEP2(
				"%TAG %NNow please enter the database name"),
		SETUP_DATABASE_STEP3("%TAG %NNow enter the database user"),
		SETUP_DATABASE_STEP4(
				"%TAG %NEnter the database password %A(This will be encrypted!)"),
		SETUP_DATABASE_STEP5(
				"%TAG %NEnter the port of the MySQL database"),
		SETUP_DATABASE_STEP6(
				"%TAG %NDid you enter everything correctly? Type Y if you did or N if you didn't"),

		SETUP_DATABASE_DETAILS(new ArrayList<String>() {
			private static final long serialVersionUID = 8788996163191664318L;

			{
				add("%H=========************************************************************=========");
				add("%AGuilds %Ndatabase setup");
				add(" ");
				add("%NYou will now be asked to type numerous details into the console in");
				add("%Norder to setup MySQL");
				add("  ");
				add("%NIf you make a mistake, carry on until the end of the setup where");
				add("%Nyou can choose to re-do the setup");
				add("   ");
				add("%NAll passwords are encrypted using specially designed methods");
				add("    ");
				add("%NThings will error! Do not worry about any errors that have occured");
				add("%Nalready with MySQL. They will be fixed once MySQL has been configured");
				add("     ");
				add("%NThe plugin will stop logging any data you type");
				add("      ");
				add("%NSetup will now commence under this message");
				add("%H=====************************************************************=====");
				add("       ");
			}
		});
		
		public Object value;
		
		MessagesValues (Object value) {
			this.value = value;
		}
	}
	
	private CityRP plugin;
	
	static File messages;
	static FileConfiguration messagesc;
	static ConfigurationSection messagescs;
	
	public Messages(CityRP plugin) {
		this.plugin = plugin;
		init();
	}
	
	private void init() {
		try {
			messages = new File("plugins/" + plugin.getName() + "/messages.yml");
			
			if(!messages.exists()) {
				messages.getParentFile().mkdirs();
				messages.createNewFile();
			}
			
			messagesc = new YamlConfiguration();
			messagescs = messagesc.getConfigurationSection("");
			messagesc.load(messages);
		
			for(MessagesValues value : MessagesValues.values()) {
				if(messagescs.get(value.name().replaceAll("_", ".")) == null) {
					messagescs.set(value.name().replaceAll("_", "."), value.value);
					save();
				}
				value.value = messagescs.get(value.name().replaceAll("_", "."));
			}
			
			messagesc.load(messages);
		} catch(IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	public static void save() {
		try {
			messagesc.save(messages);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static File getFile() {
		return messages;
	}
	
	public static FileConfiguration getConfiguration() {
		return messagesc;
	}
}