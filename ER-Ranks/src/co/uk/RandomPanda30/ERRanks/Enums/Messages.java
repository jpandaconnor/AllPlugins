package co.uk.RandomPanda30.ERRanks.Enums;

import java.util.ArrayList;

import co.uk.RandomPanda30.ERRanks.ERRanksData;

@SuppressWarnings("serial")
public enum Messages {

	ARG ("&e"),
	ERROR ("&c"),
	HEADER ("&6"),
	NORMAL ("&f"),
	TAG ("%A[%NER-Ranks%A]%N"),
	WARNING ("&4"),
	ON ("&a"),
	OFF ("&c"),

	SM (new ArrayList<String>() {
		{
			add("%TAG %NLoading...");
			add("%TAG %NMade by: %A" + ERRanksData.getAuthor(0));
		}
	}),

	SHM (new ArrayList<String>() {
		{
			add("%TAG %NStopping...");
			add("%TAG %NMade by: %A" + ERRanksData.getAuthor(0));
		}
	}),

	TEST ("TEST");

	public Object value;

	Messages (Object value) {
		this.value = value;
	}
}