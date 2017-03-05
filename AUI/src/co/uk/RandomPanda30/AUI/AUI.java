package co.uk.RandomPanda30.AUI;

import org.bukkit.plugin.java.JavaPlugin;

import co.uk.RandomPanda30.A.A;
import co.uk.RandomPanda30.A.AB;

public class AUI extends JavaPlugin {

	@Override
	public void onEnable() {
		A.setInstance(this);
		AB.setDataFile();
		
		
	}

	@Override
	public void onDisable() {

	}

}
