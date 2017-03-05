package co.uk.RandomPanda30.WastedMC;

import java.util.ArrayList;

import org.bukkit.plugin.java.JavaPlugin;

public class A extends JavaPlugin{
	
	ArrayList<String> sm;
	ArrayList<String> shm;
	
	@Override
	public void onEnable() {
		B.plugin = this;
		B.initDataFile();
		
		
	}
	
	@Override
	public void onDisable() {
		B.plugin = null;
	}

}
