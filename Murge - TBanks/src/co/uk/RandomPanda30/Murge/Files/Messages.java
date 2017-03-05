package co.uk.RandomPanda30.Murge.Files;

import java.util.ArrayList;

import co.uk.RandomPanda30.Murge.MurgeData;

@SuppressWarnings("serial")
public enum Messages {

	ARG ("&4"),
	ERROR ("&c"),
	HEADER ("&6"),
	NORMAL ("&f"),
	TEXT ("&7"),
	TAG ("%A[%N" + MurgeData.getDataFile().getName() + "%A]%N"),
	WARNING ("&4"),
	ON ("&a"),
	OFF ("&c"),

	SM (new ArrayList<String>() {
		{

		}
	}),

	SHM (new ArrayList<String>() {
		{

		}
	}),

	REFUSEMESSAGE ("%TAG\n%BThere is already an event going on!\n%NPlease try again later"),

	LOGGEDIN ("%A%player %Nhas joined the server"),
	LOGGEDOUT_VIANORMAL ("%A%player %Nhas left the server"),
	LOGGEDOUT_VIAITEM ("%TAG\n%AYou have quit the server.\n"),

	MOVEDTO_SPECTATE ("%A%player %Nwas moved to the spectators"),
	MOVEDTO_GAME ("%TAG %NYou have been moved into the game. Good luck!"),

	TARGET ("%TAG %NTargeted: %A%player"),
	SPEED ("%TAG %NFlight speed adjusted to: %A%no"),

	STARTSIN_5 ("%B&lPurge starts in 5"),
	STARTSIN_4 ("%B&lPurge starts in 4"),
	STARTSIN_3 ("%B&lPurge starts in 3"),
	STARTSIN_2 ("%B&lPurge starts in 2"),
	STARTSIN_1 ("%B&lPurge starts in 1"),

	ENDSIN_5 ("%B&lPurge ends in 5"),
	ENDSIN_4 ("%B&lPurge ends in 4"),
	ENDSIN_3 ("%B&lPurge ends in 3"),
	ENDSIN_2 ("%B&lPurge ends in 2"),
	ENDSIN_1 ("%B&lPurge ends in 1"),

	TOSTART_10 ("%TAG %BThe purge will start in 10 minutes..."),
	TOSTART_5 ("%TAG %BThe purge will start in 5 minutes..."),
	TOSTART_1 ("%TAG %BThe purge will start in 1 minute"),

	TOEND_10 ("%TAG %BThe purge will end in 10 minutes..."),
	TOEND_5 ("%TAG %BThe purge will end in 5 minutes..."),
	TOEND_1 ("%TAG %BThe purge will end in 1 minute"),

	COUNTDOWN_STARTED ("%TAG %BThe countdown to the purge has started...Build and get ready"),

	STARTED ("%TAG %E&lTHE PURGE HAS STARTED! ALL CRIME IS LEGAL. GOOD LUCK!"),
	ENDED ("%TAG %E&lTHE PURGE HAS ENDED. GET READY FOR THE NEXT PURGE"),

	TITLE_LOGINPLAYER ("&cWelcome to the Purge"),
	SUBTITLE_LOGINPLAYER ("You are now in-game"),

	TITLE_LOGINSPECTATOR ("&cWelcome to the Purge"),
	SUBTITLE_LOGINSPECTATOR ("You are now spectating"),

	TITLE_PURGESTARTED ("&cThe Purge has started"),
	SUBTITLE_PURGESTARTED ("Good luck!"),

	TITLE_PURGEENDED ("&cThe Purge has ended"),
	SUBTITLE_PURGEENDED ("Prepare for the next purge..."),

	PURGECOUNTDOWN_START ("%N&lTime until Purge:%A%time"),
	PURGECOUNTDOWN_END ("%N&lTime until Purge ends:%A%time"),

	INCOMBAT ("%TAG %EYou are now in combat. Do not logout or you will be slain"),
	OUTCOMBAT ("%TAG %NYou can now logout safely"),
	QUITCOMBAT ("%TAG %A%player %Nleft during combat and has been punished"),
	SINBIN ("%TAG %NYour items were dropped since you logged out during combat"),

	LOCATIONS_INEDIT ("%TAG %NYou are now in edit mode. Your inventory and stats have been saved until you leave edit mode"),
	LOCATIONS_OUTEDIT ("%TAG %NYou are now out of edit mode. Your inventory and stats have been restored"),

	SET_SPAWN ("%TAG %ASpawn %Nlocation set at - %AX%N: %x%N. %AY%N: %y%N. %AZ%N: %z%N."),
	SET_SPECTATORSPAWN ("%TAG %ASpectator Spawn %Nlocation set at - %AX%N: %x%N. %AY%N: %y%N. %AZ%N: %z%N."),

	REMOVE_SPAWN ("%TAG %NRemoved %ASpawn %Nlocation set at - %AX%N: %x%N. %AY%N: %y%N. %AZ%N: %z%N."),
	REMOVE_SPECTATORSPAWN ("%TAG %NRemoved %ASpectator Spawn %Nlocation set at - %AX%N: %x%N. %AY%N: %y%N. %AZ%N: %z%N."),
	STILLEDITING ("%TAG %NYou have not been placed in the game because you are currently editing locations. You have been made invisable and will respawn as a spectator once you are finished"),

	DIED ("%A%player %Ndied"),

	WORLDBORDER_DISABLED ("%TAG %EAutomatic systems have disabled the world border as there have been too many deaths caused by it. Reconfigure the world border and check all spawn points are inside it"),

	ENDSTATS (new ArrayList<String>() {
		{
			add("%A===============");
			add("%NPurge no: %A%purgeno");
			add("%NTotal kills: %A%no");
			add("%NBest player: %A%player");
			add("%A===============");
		}
	}),

	RELOADED ("%TAG %E&lThe purge has been stopped due to a server reload!"),

	WORLDNULL ("%TAG %EThe world specified in the config does not exist!"),
	NOTRACK ("%TAG %ECannot find player to track"),

	NOVAULT ("%TAG %EThe 'Vault' plugin cannot be found. Make sure it is installed in your plugins folder"),

	ERRMBLOCKS ("%TAG %EHow did you manage to get these blocks?..."),
	PVPNOTALLOWED ("%TAG %EPVP is not allowed when the purge is not active"),

	SPECTATE_CANNOTBREAKBLOCKS ("%TAG %EError, you cannot break blocks whilst in spectate mode"),
	SPECTATE_CANNOTPLACEBLOCKS ("%TAG %EError, you cannot place blocks whilst in spectate mode"),
	SPECTATE_CANNOTDROPITEMS ("%TAG %EError, you cannot drop items whilst in spectate mode"),
	SPECTATE_NOPVP ("%TAG %EError, you cannot damage other players whilst you are a spectator"),
	SPECTATE_NOINTERACT ("%TAG %EError, you may not interact with these blocks whilst spectating"),

	PLAYER_CANNOTBREAKBLOCKS ("%TAG %EError, you cannot break blocks during the purge"),
	PLAYER_CANNOTPLACEBLOCKS ("%TAG %EError, you cannot place blocks during the purge"),

	LOCATIONS_NOBREAK ("%TAG %EError, you may not break these blocks since you are not editing the locations"),
	LOCATIONS_NOPLACE ("%TAG %EError, you may not place these blocks since you are not editing the locations"),

	MYSQLSETUP_GENERAL (new ArrayList<String>() {
		{
			add("%N===== %AMySQL %N=====");
			add("%NAny messages you send will not be send to other players");
			add("%NAny passwords entered will also be encrypted and then stored");
		}
	}),

	MYSQLSETUP_HOST ("%TAG %NOpen the chat and type the %Ahost %Nof the MySQL server"),
	MYSQLSETUP_DATABASE ("%TAG %NNow open the chat and type the name of the %Adatabase"),
	MYSQLSETUP_USER ("%TAG %NNext, type the name of the %Auser"),
	MYSQLSETUP_PASS ("%TAG %NNow type your MySQL %Apassword"),
	MYSQLSETUP_PORT ("%TAG %NFinally, enter the %Aport %Nof your MySQL server"),

	MYSQL_BADDETAILS ("%TAG %EThere was a problem loading into MySQL. Check your details and the console for the error, and try again"),
	MYSQL_CONFIRMED ("%TAG %AMySQL %Ghas been setup"),

	LOCATIONS_ALREADYEDITING ("%TAG %EPlayer: %A%player %Eis already editing the location");

	public Object value;

	Messages (Object value) {
		this.value = value;
	}
}