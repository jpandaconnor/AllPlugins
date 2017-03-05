package co.uk.RandomPanda30.ItemHandlerPlus.Enums;

import java.util.ArrayList;

import co.uk.RandomPanda30.ItemHandlerPlus.B;

@SuppressWarnings("serial")
public enum Messages {

	ARG ("&3"),
	ERROR ("&c"),
	HEADER ("&6"),
	NORMAL ("&f"),
	TAG ("%A[%NIH+%A]%N"),
	WARNING ("&4"),
	ON ("&a"),
	OFF ("&c"),

	SM (new ArrayList<String>() {
		{
			add("%TAG %NLoading...");
			add("%TAG %NMade by: %A" + B.getAuthor(0));
		}
	}),

	SHM (new ArrayList<String>() {
		{
			add("%TAG %NStopping...");
			add("%TAG %NMade by: %A" + B.getAuthor(0));
		}
	}),

	CRITICAL_COMMANDNULL ("%TAG %EThis command does not exist!"),
	CRITICAL_PLAYERONLY ("%TAG %EThis command can only be used by a player in-game"),

	TEST ("TEST");

	public Object value;

	Messages (Object value) {
		this.value = value;
	}
}