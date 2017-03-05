package co.uk.RandomPanda30.MWarn.Files;

import java.util.ArrayList;

import co.uk.RandomPanda30.MWarn.B;

@SuppressWarnings("serial")
public enum Items {

	PLAYERINV_NAME ("%A'playername'"),

	PLAYERINV_PLAYERDETAILS_LORES (new ArrayList<String>() {
		{
			add("%NName: %A'playername'");
			add("%NUUID: %A'uuid'");
			add("%NActive warnings: %A'aw'");
			add("%NExpired warnings: %A'ew'");
		}
	}),

	PLAYERINV_ACTIVEWARNINGS_NAME ("%NActive warnings for: %A'playername'"),
	PLAYERINV_ACTIVEWARNINGS_LORES (new ArrayList<String>() {
		{
			add("%NWarnings count: %E'aw'");
		}
	}),

	PLAYERINV_EXPIREDWARNINGS_NAME ("%NExpired warnings for: %A'playername'"),
	PLAYERINV_EXPIREDWARNINGS_LORES (new ArrayList<String>() {
		{
			add("%NWarnings counts: %E'ew'");
		}
	}),

	PLAYERINV_BANS_NAME ("%NPrevious bans"),
	PLAYERINV_BANS_LORES (new ArrayList<String>() {
		{
			add("%NBan counts: %E'bc'");
		}
	}),

	EXITBUTTON_NAME ("%EExit button"),
	EXITBUTTON_LORES (new ArrayList<String>() {
		{
			add("%EClick here to close this menu");
		}
	}),

	NEXTBUTTON_NAME ("%ANext page"),
	NEXTBUTTON_LORES (new ArrayList<String>() {
		{
			add("%AClick here to access the next page");
		}
	}),

	PREVIOUSBUTTON_NAME ("%ALast page"),
	PREVIOUSBUTTON_LORES (new ArrayList<String>() {
		{
			add("%AClick here to access the last page");
		}
	}),

	ADMININV_ACCESSBUTTON_NAME ("%EAdmin access"),
	ADMININV_ACCESSBUTTON_LORES (new ArrayList<String>() {
		{
			add("%EClick here to gain access");
			add("%Eto the %AMWarn %Econtrol panel");
		}
	}),

	ADMININV_ADMINDETAILS_LORES (new ArrayList<String>() {
		{
			add("%EUse this menu to change MWarn settings");
			add("%Eand to add/remove warnings from players");
		}
	}),

	ADMININV_ADDWARNING_NAME ("%NAdd warning"),
	ADMININV_ADDWARNING_LORES (new ArrayList<String>() {
		{
			add("%NClick here to add a warning");
			add("%Nfor an online player");
		}
	}),

	ADMININV_REMOVEWARNING_NAME ("%NRemove warning"),
	ADMININV_REMOVEWARNING_LORES (new ArrayList<String>() {
		{
			add("%NClick here to remove a warning");
			add("%Nfor an online player");
		}
	}),

	ADMININV_MAINBUTTON_NAME ("%AMain menu"),
	ADMININV_MAINBUTTON_LORES (new ArrayList<String>() {
		{
			add("%NClick here to go back to the");
			add("%Nmain menu");
		}
	}),

	ADMININV_CLEARWARNINGS_NAME ("%NClear warnings"),
	ADMININV_CLEARWARNINGS_LORES (new ArrayList<String>() {
		{
			add("%NClick here to clear all warnings");
			add("%Nfor an online player");
		}
	}),

	ADMININV_INITCONFIGS_NAME ("%NInit configs"),
	ADMININV_INITCONFIGS_LORES (new ArrayList<String>() {
		{
			add("%NClick here to reload all config files");
		}
	}),

	ADMININV_BAN_NAME ("%NBan"),
	ADMININV_BAN_LORES (new ArrayList<String>() {
		{
			add("%NClick here to ban a player");
		}
	}),

	ADMININV_UNBAN_NAME ("%NUnban"),
	ADMININV_UNBAN_LORES (new ArrayList<String>() {
		{
			add("%NClick here to unban a player");
		}
	}),

	ADMININV_OFFONOPTIONS_NAME ("%NTurn features on/off"),
	ADMININV_OFFONOPTIONS_LORES (new ArrayList<String>() {
		{
			add("%NUse this menu to turn MWarn's");
			add("%Nfeatures on or off");
		}
	}),

	ADMININV_CONFIGVALUES_NAME ("%NEdit config values"),
	ADMININV_CONFIGVALUES_LORES (new ArrayList<String>() {
		{
			add("%NUse this menu to edit values");
			add("%Nin the configuration");
		}
	}),

	ONOFFINV_MWARNONOFF_NAME ("%NTurn MWarn on/off"),
	ONOFFINV_MWARNONOFF_LORES (new ArrayList<String>() {
		{
			add("%NMWarn is currently... 'set'");
		}
	}),

	ONOFFINV_CMDWARN_NAME ("%NTurn '/mwarn warn' command on/off"),
	ONOFFINV_CMDWARN_LORES (new ArrayList<String>() {
		{
			add("%NThis command is currently... 'set'");
		}
	}),

	ONOFFINV_CMDCLEAR_NAME ("%NTurn '/mwarn clear' command on/off"),
	ONOFFINV_CMDCLEAR_LORES (new ArrayList<String>() {
		{
			add("%NThis command is currently... 'set'");
		}
	}),

	ONOFFINV_CMDREMOVE_NAME ("%NTurn '/mwarn remove' command on/off"),
	ONOFFINV_CMDREMOVE_LORES (new ArrayList<String>() {
		{
			add("%NThis command is currently... 'set'");
		}
	}),

	ONOFFINV_BROADCASTWARNINGS_NAME ("%NBroadcast when a player is warned?"),
	ONOFFINV_BROADCASTWARNINGS_LORES (new ArrayList<String>() {
		{
			add("%NThis option is currently... 'set'");
		}
	}),

	ONOFFINV_BROADCASTBAN_NAME ("%NBroadcast when a player is banned?"),
	ONOFFINV_BROADCASTBAN_LORES (new ArrayList<String>() {
		{
			add("%NThis option is currently... 'set'");
		}
	}),

	ONOFFINV_BROADCASTUNBAN_NAME ("%NBroadcast when a player is unbanned?"),
	ONOFFINV_BROADCASTUNBAN_LORES (new ArrayList<String>() {
		{
			add("%NThis option is currently... 'set'");
		}
	}),

	ONOFFINV_BROADCASTCLEARWARNINGS_NAME ("%NBroadcast when a player has their warnings cleared?"),
	ONOFFINV_BROADCASTCLEARWARNINGS_LORES (new ArrayList<String>() {
		{
			add("%NThis option is currently... 'set'");
		}
	}),

	ONOFFINV_BROADCASTREMOVEWARNING_NAME ("%NBroadcast when a player has a warning removed?"),
	ONOFFINV_BROADCASTREMOVEWARNING_LORES (new ArrayList<String>() {
		{
			add("%NThis option is currently... 'set'");
		}
	}),

	CONFIGVALUE_THRESHOLD_NAME ("%NChange how many warnings until a ban"),
	CONFIGVALUE_THRESHOLD_LORES (new ArrayList<String>() {
		{
			add("%NAmount of warnings: %A'set'");
		}
	}),

	CONFIGVALUE_BANLENGTH_NAME ("%NChange how many days a player should stay banned for"),
	CONFIGVALUE_BANLENGTH_LORES (new ArrayList<String>() {
		{
			add("%NAmount of days: %A'set'");
		}
	}),

	SINV_SBFL_NAME ("%NSearch by first letter of a name"),
	SINV_SBFL_LORES (new ArrayList<String>() {
		{
			add("%NClick here to search for a player");
			add("%Nusing the first letter of their name");
		}
	}),

	MWARN_WELCOMEMESSAGES_PLAYER_NAME ("%AMWarn"),
	MWARN_WELCOMEMESSAGES_PLAYER_LORES (new ArrayList<String>() {
		{
			add("%NWelcome to the %AMWarn %Nmenu.");
			add("%A- %NUse this menu to check all your warnings and bans");
			add("%A- %NCreated by: %NRandomPanda30");
			add(" ");
			add("%A- %NVersion: %A" + B.getDataFile().getVersion());
		}
	}),

	MWARN_WELCOMEMESSAGES_ADMIN_NAME ("%AMWarn"),
	MWARN_WELCOMEMESSAGES_ADMIN_LORES (new ArrayList<String>() {
		{
			add("%NWelcome to the %AMWarn %Nmenu.");
			add("%A- %NUse this menu to check all your warnings and bans.");
			add("%A- %NClick on the admin control to access the admin panel.");
			add("%A- %NCreated by: %NRandomPanda30");
			add(" ");
			add("%NVersion: %A" + B.getDataFile().getVersion());
		}
	}),

	AWARNINGSINV_NAME ("%A'no'"),
	AWARNINGSINV_REASON ("%NReason: %E'reason'"),
	AWARNINGSINV_LASTNAME ("%NLast name: %E'name'"),
	AWARNINGSINV_DATE ("%NDate + time: %E'date'"),
	AWARNINGSINV_KEY ("%NKey: %A'key'"),

	SINV_AW ("%NActive warnings: %E'no'"),
	SINV_EW ("%NExpired warnings: %E'no'");

	public Object value;

	Items (Object value) {
		this.value = value;
	}

}
