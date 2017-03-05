package co.uk.RandomPanda30.ExpIntercept.Enums;

import java.util.ArrayList;

import co.uk.RandomPanda30.ExpIntercept.ExpInterceptData;

@SuppressWarnings("serial")
public enum Messages {

	ARG ("&5"),
	ERROR ("&c"),
	HEADER ("&6"),
	NORMAL ("&f"),
	TAG ("%A[%NExpIntercept%A]%N"),
	WARNING ("&4"),
	ON ("&a"),
	OFF ("&c"),

	SM (new ArrayList<String>() {
		{
			add("%TAG %NLoading...");
			add("%TAG %NMade by: %A" + ExpInterceptData.getAuthor(0));
		}
	}),

	SHM (new ArrayList<String>() {
		{
			add("%TAG %NStopping...");
			add("%TAG %NMade by: %A" + ExpInterceptData.getAuthor(0));
		}
	}),

	BOOSTER_ACTIVATED ("%AYour booster has been activated!"),
	BOOSTER_ADDEDTOQUEUE (
			"%AYour booster has been added to the queue as there is already a booster active!"),

	EXPINTERCEPT_CHANGED (
			"%TAG %NYou have changed the global xp level to: %A%arg"),

	CRITICAL_WRONGARGS (
			"%TAG %EError, please execute the command as: /gxplevel <Level no>"),
	CRITICAL_ONLYCONSOLE (
			"%TAG %EError, this command can also be done by console"),
	CRITICAL_PLAYERNOTFOUND ("%TAG %EError, this player could not be found"),

	SYNTAX_BOOSTER (
			"%EError, wrong sytax. Use: %A/booster <Player name> <Time in minutes> <% multiplier>"),

	TEST ("TEST");

	public Object value;

	Messages (Object value) {
		this.value = value;
	}
}