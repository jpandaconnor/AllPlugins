package co.uk.RandomPanda30.Murge.Values.Enums;

import java.util.ArrayList;

public enum MessagesValues {

	ARG {
		public Object getValue() {
			return "&4";
		}
	},

	ERROR {
		public Object getValue() {
			return "&c";
		}
	},

	HEADER {
		public Object getValue() {
			return "&6";
		}
	},

	NORMAL {
		public Object getValue() {
			return "&f";
		}
	},

	TEXT {
		public Object getValue() {
			return "&7";
		}
	},

	TAG {
		public Object getValue() {
			return "%A[%NMurge%A]%N";
		}
	},

	WARNING {
		public Object getValue() {
			return "&4";
		}
	},

	ON {
		public Object getValue() {
			return "&a";
		}
	},

	OFF {
		public Object getValue() {
			return "&c";
		}
	},

	SM {
		public Object getValue() {
			return new ArrayList<String>();
		}
	},

	SHM {
		public Object getValue() {
			return new ArrayList<String>();
		}
	},

	/*************************************************************************
	 * 
	 * GENERAL
	 * 
	 *************************************************************************/

	/* Logged in and out */

	LOGGEDIN {
		public Object getValue() {
			return "%A%player %Nhas joined the server";
		}
	},

	LOGGEDOUT_VIANORMAL {
		public Object getValue() {
			return "%A%player %Nhas left the server";
		}
	},

	LOGGEDOUT_VIAITEM {
		public Object getValue() {
			return "%TAG\n%AYou have quit the server.\n";
		}
	},

	/* Moved to 'whatever' */

	MOVEDTO_SPECTATE {
		public Object getValue() {
			return "%A%player %Nwas moved to the spectators";
		}
	},

	MOVEDTO_GAME {
		public Object getValue() {
			return "%TAG %NYou have been moved into the game. Good luck!";
		}
	},

	// Response from click on item

	TARGET {
		public Object getValue() {
			return "%TAG %NTargeted: %A%player";
		}
	},

	SPEED {
		public Object getValue() {
			return "%TAG %NFlight speed adjusted to: %A%no";
		}
	},

	/* Countdown */

	STARTSIN_5 {
		public Object getValue() {
			return "%B&lPurge starts in 5";
		}
	},

	STARTSIN_4 {
		public Object getValue() {
			return "%B&lPurge starts in 4";
		}
	},

	STARTSIN_3 {
		public Object getValue() {
			return "%B&lPurge starts in 3";
		}
	},

	STARTSIN_2 {
		public Object getValue() {
			return "%B&lPurge starts in 2";
		}
	},

	STARTSIN_1 {
		public Object getValue() {
			return "%B&lPurge starts in 1";
		}
	},

	ENDSIN_5 {
		public Object getValue() {
			return "%B&lPurge ends in 5";
		}
	},

	ENDSIN_4 {
		public Object getValue() {
			return "%B&lPurge ends in 4";
		}
	},

	ENDSIN_3 {
		public Object getValue() {
			return "%B&lPurge ends in 3";
		}
	},

	ENDSIN_2 {
		public Object getValue() {
			return "%B&lPurge ends in 2";
		}
	},

	ENDSIN_1 {
		public Object getValue() {
			return "%B&lPurge ends in 1";
		}
	},

	TOSTART_10 {
		public Object getValue() {
			return "%TAG %BThe purge will start in 10 minutes";
		}
	},

	TOSTART_5 {
		public Object getValue() {
			return "%TAG %BThe purge will start in 5 minutes";
		}
	},

	TOSTART_1 {
		public Object getValue() {
			return "%TAG %BThe purge will start in 1 minute";
		}
	},

	TOEND_10 {
		public Object getValue() {
			return "%TAG %BThe purge will end in 10 minutes";
		}
	},

	TOEND_5 {
		public Object getValue() {
			return "%TAG %BThe purge will end in 5 minutes";
		}
	},

	TOEND_1 {
		public Object getValue() {
			return "%TAG %BThe purge will end in 1 minute";
		}
	},

	COUNTDOWN_STARTED {
		public Object getValue() {
			return "%TAG %BThe countdown to the purge has started...Build and get ready";
		}
	},

	STARTED {
		public Object getValue() {
			return "%TAG %E&lTHE PURGE HAS STARTED! ALL CRIME IS LEGAL. GOOD LUCK!";
		}
	},

	ENDED {
		public Object getValue() {
			return "'%TAG %E&lTHE PURGE HAS ENDED. GET READY FOR THE NEXT PURGE";
		}
	},

	/* Title + subtitle messages */

	TITLE_LOGINPLAYER {
		public Object getValue() {
			return "&cWelcome to the purge";
		}
	},

	TITLE_LOGINSPECTATOR {
		public Object getValue() {
			return "&cWelcome to the purge";
		}
	},

	TITLE_PURGESTARTED {
		public Object getValue() {
			return "&cThe purge has started";
		}
	},

	TITLE_PURGEENDED {
		public Object getValue() {
			return "&cThe Purge has ended";
		}
	},

	SUBTITLE_LOGINPLAYER {
		public Object getValue() {
			return "You are now in-game";
		}
	},

	SUBTITLE_LOGINSPECTATOR {
		public Object getValue() {
			return "You are now spectating";
		}
	},

	SUBTITLE_PURGESTARTED {
		public Object getValue() {
			return "Good luck!";
		}
	},

	SUBTITLE_PURGEENDED {
		public Object getValue() {
			return "Prepare for the next purge...";
		}
	},

	/* Action bar messages */

	PURGECOUNTDOWN_START {
		public Object getValue() {
			return "%N&lTime until Purge:%A%time";
		}
	},

	PURGECOUNTDOWN_END {
		public Object getValue() {
			return "%N&lTime until Purge ends:%A%time";
		}
	},

	/* Combat messages */

	INCOMBAT {
		public Object getValue() {
			return "%TAG %EYou are now in combat. Do not logout or you will be slain";
		}
	},

	OUTCOMBAT {
		public Object getValue() {
			return "%TAG %NYou can now logout safely";
		}
	},

	QUITCOMBAT {
		public Object getValue() {
			return "%TAG %A%player %Nleft during combat and has been punished";
		}
	},

	SINBIN {
		public Object getValue() {
			return "%TAG %NYour items were dropped since you logged out during combat";
		}
	},

	/* Edit locations here */

	LOCATIONS_INEDIT {
		public Object getValue() {
			return "%TAG %NYou are now in edit mode. Your inventory and stats have been saved until you leave edit mode";
		}
	},

	LOCATIONS_OUTEDIT {
		public Object getValue() {
			return "%TAG %NYou are now out of edit mode. Your inventory and stats have been restored";
		}
	},

	SET_SPAWN {
		public Object getValue() {
			return "%TAG %ASpawn %Nlocation set at - %AX%N: %x%N. %AY%N: %y%N. %AZ%N: %z%N.";
		}
	},

	SET_SPECTATORSPAWN {
		public Object getValue() {
			return "%TAG %ASpectator Spawn %Nlocation set at - %AX%N: %x%N. %AY%N: %y%N. %AZ%N: %z%N.";
		}
	},

	REMOVE_SPAWN {
		public Object getValue() {
			return "%TAG %NRemoved %ASpawn %Nlocation set at - %AX%N: %x%N. %AY%N: %y%N. %AZ%N: %z%N.";
		}
	},

	REMOVE_SPECTATORSPAWN {
		public Object getValue() {
			return "%TAG %NRemoved %ASpectator Spawn %Nlocation set at - %AX%N: %x%N. %AY%N: %y%N. %AZ%N: %z%N.";
		}
	},

	STILLEDITING {
		public Object getValue() {
			return "%TAG %NYou have not been placed in the game because you are currently editing locations. You have been made invisable and will respawn as a spectator once you are finished";
		}
	},

	/* Events */

	DIED {
		public Object getValue() {
			return "%A%player %Ndied";
		}
	},

	/* Stats */

	ENDSTATS {
		public Object getValue() {
			return new ArrayList<String>();
		}
	},

	/* Auto bot */

	WORLDBORDER_DISABLED {
		public Object getValue() {
			return "%TAG %EAutomatic systems have disabled the world border as there have been too many deaths caused by it. Reconfigure the world border and check all spawn points are inside it";
		}
	},

	/*************************************************************************
	 * 
	 * CRITICAL
	 * 
	 *************************************************************************/

	REFUSEMESSAGE {
		public Object getValue() {
			return "Refused";
		}
	},

	RELOADED {
		public Object getValue() {
			return "%TAG %E&lThe purge has been stopped due to a server reload!";
		}
	},

	WORLDNULL {
		public Object getValue() {
			return "%TAG %EThe world specified in the config does not exist!";
		}
	},

	NOTRACK {
		public Object getValue() {
			return "%TAG %ECannot find player to track";
		}
	},

	NOVAULT {
		public Object getValue() {
			return "%TAG %EThe ''Vault'' plugin cannot be found. Make sure it is installed in your plugins folder";
		}
	},

	ERRMBLOCKS {
		public Object getValue() {
			return "%TAG %EHow did you manage to get these blocks?...";
		}
	},

	PVPNOTALLOWED {
		public Object getValue() {
			return "%TAG %EPVP is not allowed when the purge is not active";
		}
	},

	SPECTATE_CANNOTBREAKBLOCKS {
		public Object getValue() {
			return "%TAG %EError, you cannot break blocks whilst in spectate mode";
		}
	},

	SPECTATE_CANNOTPLACEBLOCKS {
		public Object getValue() {
			return "%TAG %EError, you cannot place blocks whilst in spectate mode";
		}
	},

	SPECTATE_CANNOTDROPITEMS {
		public Object getValue() {
			return "%TAG %EError, you cannot drop items whilst in spectate mode";
		}
	},

	SPECTATE_NOPVP {
		public Object getValue() {
			return "%TAG %EError, you cannot damage other players whilst you are a spectator";
		}
	},

	SPECTATE_NOINTERACT {
		public Object getValue() {
			return "%TAG %EError, you may not interact with these blocks whilst spectating";
		}
	},

	PLAYER_CANNOTBREAKBLOCKS {
		public Object getValue() {
			return "%TAG %EError, you cannot break blocks during the purge";
		}
	},

	PLAYER_CANNOTPLACEBLOCKS {
		public Object getValue() {
			return "%TAG %EError, you cannot place blocks during the purge";
		}
	},

	LOCATIONS_NOBREAK {
		public Object getValue() {
			return "%TAG %EError, you may not break these blocks since you are not editing the locations";
		}
	},

	LOCATIONS_NOPLACE {
		public Object getValue() {
			return "%TAG %EError, you may not place these blocks since you are not editing the locations";
		}
	},

	LOCATIONS_ALREADYEDITING {
		public Object getValue() {
			return "%TAG %EPlayer: %A%player %Eis already editing the location";
		}
	},

	MYSQL_BADDETAILS {
		public Object getValue() {
			return "%TAG %EThere was a problem loading into MySQL. Check your details and the console for the error, and try again";
		}
	},

	/* MySQL Messages */

	MYSQLSETUP_GENERAL {
		public Object getValue() {
			return new ArrayList<String>();
		}
	},

	MYSQLSETUP_HOST {
		public Object getValue() {
			return "%TAG %NOpen the chat and type the %Ahost %Nof the MySQL server";
		}
	},

	MYSQLSETUP_DATABASE {
		public Object getValue() {
			return "%TAG %NNow open the chat and type the name of the %Adatabase";
		}
	},

	MYSQLSETUP_USER {
		public Object getValue() {
			return "%TAG %NNext, type the name of the %Auser";
		}
	},

	MYSQLSETUP_PASS {
		public Object getValue() {
			return "%TAG %NNow type your MySQL %Apassword";
		}
	},

	MYSQLSETUP_PORT {
		public Object getValue() {
			return "%TAG %NFinally, enter the %Aport %Nof your MySQL server";
		}
	},

	MYSQL_CONFIRMED {
		public Object getValue() {
			return "%TAG %AMySQL %Ghas been setup";
		}
	};

	@Override
	public String toString() {
		return name();
	}

	public Object getValue() {
		throw new AbstractMethodError("This error should never be shown.");
	}
}