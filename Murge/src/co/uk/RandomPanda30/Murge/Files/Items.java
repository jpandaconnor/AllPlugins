package co.uk.RandomPanda30.Murge.Files;

import java.util.ArrayList;

@SuppressWarnings("serial")
public enum Items {

	EXIT ("%BExit"),
	BACK ("%TGo back"),

	EXITEDITMODE_NAME ("%BExit edit mode"),
	EXITEDITMODE_LORES (new ArrayList<String>() {
		{
			add("%TRight click to exit location");
			add("%Tedit mode");
		}
	}),

	EDIT_SPAWNLOCATIONNAME ("%NEdit %ASpawn %Nlocation"),
	EDIT_SPAWNLOCATIONLORES (new ArrayList<String>() {
		{
			add("%TPlace this block to edit the");
			add("%Tspawn location");
		}
	}),

	EDIT_SPECTATORLOCATIONNAME ("%NEdit %ASpectator%N spawn location"),
	EDIT_SPECTATORLOCATIONLORES (new ArrayList<String>() {
		{
			add("%TPlace this block to edit the");
			add("%Tspawn location for spectators");
		}
	}),

	SETTINGS_LOCATIONNAME ("%NEdit: %ALocations"),
	SETTINGS_LOCATIONLORES (new ArrayList<String>() {
		{
			add("%TClick here to edit different spawn");
			add("%Tlocations in this world");
		}
	}),

	ADJUSTFLY_NAME ("&3Adjust fly speed"),
	ADJUSTFLY_LORES (new ArrayList<String>() {
		{
			add("%TClick here to adjust your");
			add("%Tflying speed");
		}
	}),

	DETAILS_NAME ("%AMurge"),
	DETAILS_LORES (new ArrayList<String>() {
		{
			add("%TMurge information menu");
		}
	}),

	SETTINGS_WORLDBORDERNAME ("%NEdit: %AWorld Border"),
	SETTINGS_WORLDBORDERLORES (new ArrayList<String>() {
		{
			add("%TClick here to toggle the world");
			add("%Tborder %Gon%T/%Boff %Tand");
			add("%Tchange world border size");
		}
	}),

	SETTINGS_PURGEVALUENAME ("%NEdit: %APurge values"),
	SETTINGS_PURGEVALUELORES (new ArrayList<String>() {
		{
			add("%TClick here to change purge values");
			add("%Tsuch as times, cooldowns + more");
		}
	}),

	SETTINGS_PURGEOPTIONSNAME ("%NEdit: %APurge options"),
	SETTINGS_PURGEOPTIONSLORES (new ArrayList<String>() {
		{
			add("%TClick here to edit different purge");
			add("%Toptions such as PVP, building + more");
		}
	}),

	SETTINGS_BROADCASTOPTIONSNAME ("%NEdit: %ABroadcast options"),
	SETTINGS_BROADCASTOPTIONSLORES (new ArrayList<String>() {
		{
			add("%TClick here to edit what messages");
			add("%Tcan be broadcasted and what can't");
		}
	}),

	SETTINGS_CONTROLPANELNAME ("%AMurge %Ncontrol panel"),
	SETTINGS_CONTROLPANELLORES (new ArrayList<String>() {
		{
			add("%TClick here to access the control panel.");
			add("%TChange a range of settings from world");
			add("%Tborder size to purge length");
		}
	}),

	HEAD_NAME ("%A%player"),
	HEAD_LORES (new ArrayList<String>() {
		{
			add("   ");
			add("%N&lMoney: %A%money");
			add(" ");
			add("%N&lTotal kills: %A%totalKills");
			add("%N&lTotal deaths: %A%totalDeaths");
			add("  ");
			add("%N&lPurges survived: %A%purgesSurvived");
		}
	}),

	LEAVETOOL_NAME ("%BLeave the server"),
	LEAVETOOL_LORES (new ArrayList<String>() {
		{
			add("%TRight click this item");
			add("%Tto leave the server");
		}
	}),

	COMPASS_NAME ("&eTracking compass"),
	COMPASS_LORES (new ArrayList<String>() {
		{
			add("%TUse this item to track");
			add("%Tthe nearest player");
		}
	}),

	SETTINGS_COMMANDSMENUNAME ("%NEdit: %ACommands"),
	SETTINGS_COMMANDSMENULORES (new ArrayList<String>() {
		{
			add("%TClick here to edit what commands");
			add("%Tshould and shouldn't be enabled");
		}
	}),

	SETTINGS_KITSMENUNAME ("%NEdit: %AKits"),
	SETTINGS_KITSMENULORES (new ArrayList<String>() {
		{
			add("%TClick here to edit different");
			add("%Tkits for the Murge");
		}
	});

	public Object value;

	Items (Object value) {
		this.value = value;
	}
}