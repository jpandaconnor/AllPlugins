package co.uk.RandomPanda30.ERHub.Enums;

import java.util.ArrayList;

import co.uk.RandomPanda30.ERHub.ERHubData;

@SuppressWarnings("serial")
public enum Messages {

	ARG ("&e"),
	ERROR ("&c"),
	HEADER ("&6"),
	NORMAL ("&f"),
	TAG ("%A[%NER-Hub%A]%N"),
	WARNING ("&4"),
	ON ("&a"),
	OFF ("&c"),

	SM (new ArrayList<String>() {
		{
			add("%TAG %NLoading...");
			add("%TAG %NMade by: %A" + ERHubData.getAuthor(0));
		}
	}),

	SHM (new ArrayList<String>() {
		{
			add("%TAG %NStopping...");
			add("%TAG %NMade by: %A" + ERHubData.getAuthor(0));
		}
	}),

	TEST ("TEST");

	public Object value;

	Messages (Object value) {
		this.value = value;
	}
}