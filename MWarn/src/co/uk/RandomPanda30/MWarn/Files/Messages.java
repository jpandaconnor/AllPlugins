package co.uk.RandomPanda30.MWarn.Files;

import java.util.ArrayList;

import co.uk.RandomPanda30.MWarn.B;

public enum Messages {

	ARG ("&4"),
	ERROR ("&c"),
	HEADER ("&6"),
	NORMAL ("&f"),
	TAG ("%A[%N" + B.getDataFile().getName() + "%A]%N"),
	WARNING ("&4"),
	ON ("&a"),
	OFF ("&c"),

	INVENTORY_PLAYERINVENTORY ("%NMWarn menu for - %A'player'"),
	INVENTORY_ADMININVENTORY ("%NMWarn menu for - %A'player'"),
	INVENTORY_OPENED ("%TAG %A'type' %Ninventory opened!"),

	MWARN_PLAYERWARNED ("%TAG %A'playername' %Nhas been warned with the reason: %A'reason'"),
	MWARN_TARGETWARNED ("%TAG %NYou have been warned by %A'playername' %Nwith the reason: %A'reason'"),
	MWARN_BROADCASTWARNED ("%TAG Player %A'playername' %Nhas been warned by %A'playername2' %Nwith the reason: %A'reason'"),

	MWARN_PLAYERBANNED ("%TAG %A'playername' %Nhas been banned"),
	MWARN_BROADCASTBANNED ("%TAG Player %A'playername' %Nhas been banned by %A'playername2'"),

	MWARN_PLAYERCLEARED ("%TAG %A'playername' %Nhas had all their warnings cleared"),
	MWARN_TARGETCLEARED ("%TAG %NYou have had all your warnings cleared by: %A'playername'"),
	MWARN_BROADCASTCLEARED ("%TAG Player %A'playername' %Nhas has their warnings cleared by %A'playername2'"),

	MWARN_PLAYERUNBANNED ("%TAG %A'playername' %Nhas been unbanned"),
	MWARN_BROADCASTUNBANNED ("%TAG UUID %A'playername' %Nhas been unbanned by %A'playername2'"),

	MWARN_WARNINGSEXPIRED ("%TAG %A'warnings' %Nwarnings have expired"),

	MWARN_CONFGISINIT ("%TAG %NAll configs have been reloaded"),

	MWARN_WARNINGREMOVED ("%TAG %NWarning %A'warning' %Nhas been removed from the config"),
	MWARN_TARGETWARNINGREMOVE ("%TAG %NWarning %A'warning' %Nhas been removed from the config"),
	MWARN_BROADCASTREMOVEWARNING ("%TAG %NWarning %A'warning' %Nfrom player %A'playername' %Nhas been removed by %A'playername2'"),

	BAN_REASON ("%TAG\n%NYou've been banned. \nReason: \n%A"),
	BAN_NOREASON ("%ANo reason"),
	BAN_LENGTH ("%NBan length: \n"),
	BAN_DAYS (" %NDay(s)"),
	BAN_HOURS (" %NHour(s)"),
	BAN_MINUTES (" %NMinute(s)"),
	BAN_SECONDS (" %NSecond(s)"),
	BAN_PERM (" %EPermanently"),

	@SuppressWarnings("serial")
	START_MESSAGES (new ArrayList<String>() {
		{
			add("%TAG %NLoading...");
			add("%TAG %NMade by: %A" + B.getAuthor(0));
		}
	}),

	@SuppressWarnings("serial")
	STOP_MESSAGES (new ArrayList<String>() {
		{
			add("%TAG %NStopping...");
			add("%TAG %NMade by: %A" + B.getAuthor(0));
		}
	}),

	CRITICAL_INVALIDCOMMAND ("%TAG %EError, this command does not exist or does not have the correct arguments. Do /mwarn help for help"),
	CRITICAL_WARNINGSOFF ("%TAG %EError, MWarn is turned off. Access the admin panel using /mwarn and turn it on"),
	CRITICAL_COMMANDNOTENABLED ("%TAG %EError, this command is not enabled"),
	CRITICAL_PLAYERONLY ("%TAG %EError, this command can only be done by a player"),
	CRITICAL_COMMANDNULL ("%TAG %EError, this command does not exist"),
	CRITICAL_REASONCANNOTBENULL ("%TAG %EError, you must have a reason"),
	CRITICAL_NOPERM ("%TAG %EError, you don't have permission to do this"),
	CRITICAL_PLAYERINVALID ("%TAG %EError, this is not a valid player or they are not online at the moment");

	public Object value;

	Messages (Object value) {
		this.value = value;
	}
}
