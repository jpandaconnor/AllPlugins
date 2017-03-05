package co.uk.RandomPanda30.MineRP.Files;

import java.util.ArrayList;

import co.uk.RandomPanda30.MineRP.MineRPData;

@SuppressWarnings("serial")
public enum Messages {

	ARG ("&4"),
	ERROR ("&c"),
	HEADER ("&6"),
	NORMAL ("&f"),
	TAG ("%A[%N" + MineRPData.getDataFile().getName() + "%A]%N"),
	WARNING ("&4"),
	ON ("&a"),
	OFF ("&c"),

	SM (new ArrayList<String>() {
		{
			add("%TAG is being %Genabled");
			add("%TAG made by %A" + MineRPData.getAuthor(0));
		}
	}),

	SHM (new ArrayList<String>() {
		{
			add("%TAG is being %Bdisabled");
			add("%TAG made by %A" + MineRPData.getAuthor(0));
		}
	}),

	STARTUP_LOADINGWORLD ("%TAG %NLoading world..."),

	SCOREBOARD_TITLE ("%A'player' %N: %A'job'"),
	SCOREBOARD_NAME ("%NName: %A"),
	SCOREBOARD_JOB ("%NJob: "),
	SCOREBOARD_MONEY ("%NMoney: %A'money'"),
	SCOREBOARD_JOB2 ("%NYour Job: "),
	SCOREBOARD_BALANCE ("%NBalance: %A'money'"),

	SCOREBOARD_LOOKINGAT ("%NLooking at:"),

	WELCOME_MESSAGE ("%NWelcome %A'player' %Nto the server!"),
	JOINED_MESSAGE ("%A'player' %Nhas joined the server!"),

	DENIED_MAPLOADING ("%TAG\n %NThe map is still loading. Please try joining again");

	public Object value;

	Messages (Object value) {
		this.value = value;
	}
}