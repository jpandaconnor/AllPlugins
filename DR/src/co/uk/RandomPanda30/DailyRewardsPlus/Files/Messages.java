package co.uk.RandomPanda30.DailyRewardsPlus.Files;

import java.util.ArrayList;

import co.uk.RandomPanda30.DailyRewardsPlus.B;

@SuppressWarnings("serial")
public enum Messages {

	ARG ("&3"),
	ERROR ("&c"),
	HEADER ("&6"),
	NORMAL ("&f"),
	TAG ("%A[%NDR+%A]%N"),
	WARNING ("&4"),
	ON ("&a"),
	OFF ("&c"),

	START_MESSAGES (new ArrayList<String>() {
		{
			add("%TAG %NLoading...");
			add("%TAG %NMade by: %A" + B.getAuthor(0));
		}
	}),

	STOP_MESSAGES (new ArrayList<String>() {
		{
			add("%TAG %NStopping...");
			add("%TAG %NMade by: %A" + B.getAuthor(0));
		}
	}),

	CRITICAL_INVFULL ("%TAG %EError, your inventory cannot hold the items. Please make room and try again"),
	CRITICAL_COMMANDNULL ("%TAG %EThis command does not exist!"),
	CRITICAL_PLAYERONLY ("%TAG %EThis command can only be used by a player in-game"),

	DR_OPENEDMENU ("%TAG Menu opened");

	public Object value;

	Messages (Object value) {
		this.value = value;
	}
}