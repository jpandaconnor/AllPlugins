package co.uk.RandomPanda30.DShops.Files;

import java.util.ArrayList;

import co.uk.RandomPanda30.DShops.B;

@SuppressWarnings("serial")
public enum Messages {

	ARG ("&4"),
	ERROR ("&c"),
	HEADER ("&6"),
	NORMAL ("&f"),
	TAG ("%A[%N" + B.getDataFile().getName() + "%A]%N"),
	WARNING ("&4"),
	ON ("&a"),
	OFF ("&c"),

	SM (new ArrayList<String>() {
		{
			add("%TAG %Nis being %Genabled");
			add("%TAG %Nmade by: %A" + B.getAuthor(0));
		}
	}),

	SHM (new ArrayList<String>() {
		{
			add("%TAG %Nis being %Bdisabled");
			add("%TAG %Nmade by: %A" + B.getAuthor(0));
		}
	}),

	DSHOPS_DOORBOUGHT ("%TAG %NYou have bought this door for %A'price'"),
	DSHOPS_FRIENDADDED ("%TAG %GFriend added"),
	DSHOPS_FRIENDREMOVED ("%TAG %BFriend removed"),
	DSHOPS_DOORSOLD ("%TAG %NYou have sold this door for %A'price'"),

	DSHOPS_DOORRENTED ("%TAG %NYou have rented this shop for %A'dayno' %Ndays at a price of %A'price'"),
	DSHOPS_RENTUPDATED ("%TAG %NYou have updated your rent"),

	CRITICAL_TOOMANYDOORS ("%TAG %EError, you own too many doors. Please sell some and try again"),
	CRITICAL_NOTENOUGH ("%TAG %EError, you cannot afford this"),
	CRITICAL_NOVAULT ("%TAG %EError, Vault is needed for this plugin to work"),
	CRITICAL_NOTOWNER ("%TAG %EError, you do not own this door");

	public Object value;

	Messages (Object value) {
		this.value = value;
	}

}
