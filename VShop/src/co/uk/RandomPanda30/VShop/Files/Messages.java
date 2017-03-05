package co.uk.RandomPanda30.VShop.Files;

import java.util.ArrayList;

import co.uk.RandomPanda30.VShop.A;

public enum Messages {

	ARG ("&6"),
	ERROR ("&c"),
	HEADER ("&6"),
	NORMAL ("&f"),
	TAG ("%A[%N" + A.pdfFile.getName() + "%A]%N"),
	WARNING ("&4"),

	@SuppressWarnings("serial")
	STARTUP_MESSAGES_LIST (new ArrayList<String>() {
		{
			add("%TAG is being enabled");
		}
	}),

	@SuppressWarnings("serial")
	SHUTDOWN_MESSAGES_LIST (new ArrayList<String>() {
		{
			add("%TAG is being disabled");
		}
	}),

	POSITIONS_POSITION1 ("%TAG %NPosition %A1 %N set at - %AX%N: %A'x'%N. %AY%N: %A'y'%N. %AZ%N: %A'z'%N."),
	POSITIONS_POSITION2 ("%TAG %NPosition %A2 %N set at - %AX%N: %A'x'%N. %AY%N: %A'y'%N. %AZ%N: %A'z'%N."),

	@SuppressWarnings("serial")
	SIGNS_FORSALE (new ArrayList<String>() {
		{
			add("%TAG");
			add("%EUnclaimed");
			add("%A'price'");
			add("%N[%AFor sale%N]");
		}
	}),

	@SuppressWarnings("serial")
	SIGNS_OWNED (new ArrayList<String>() {
		{
			add("%TAG");
			add("%A'player'");
			add("%A'price'");
			add("%N[%AClaimed%N]");
		}
	}),

	@SuppressWarnings("serial")
	SIGNS_NENABLED (new ArrayList<String>() {
		{
			add("%TAG");
			add("%N[%EDisabled%N]");
		}
	}),

	@SuppressWarnings("serial")
	VSHOP_COMMANDS (new ArrayList<String>() {
		{
			add("%TAG %ACommands - ");
			add("%NWand: /%Avshop wand");
			add("%NCreate: /%Avshop create <shop name> <price>");
		}
	}),

	VSHOPS_PLOTCREATED ("%TAG %NShop %A'plot' %Nhas been created"),
	VSHOPS_ARG ("%TAG %NBasic argument for Vshop"),
	VSHOPS_WANDGIVEN ("%TAG %NYou have been given a wand"),

	VSHOPS_BOUGHT ("%TAG %NYou have bought the shop %A'name'"),
	VSHOPS_SOLD ("%TAG %NYou have sold this shop"),

	CRITICAL_INVALIDCMD ("%TAG %EError, you have entered an invalid command. Do /vshop help to see a list of avaliable commands"),
	CRITICAL_NOPERM ("%TAG %EError, you do not have permission to do this"),
	CRITICAL_NOVAULT ("%TAG %EError, Vault is not installed"),
	CRITICAL_ALREADYININV ("%TAG %EError, you already have the wand in your inventory"),
	CRITICAL_CANNOTAFFORD ("%TAG %EError, you cannot afford this"),
	CRITICAL_CMDDISABLED ("%TAG %EError, the command %A'cmd' %Ehas been disabled in the config"),
	CRITICAL_CANNOTEDIT ("%TAG %EYou cannot edit this shop as it is not yours"),
	CRITICAL_NOTSELECTED ("%TAG %EError, please select the two points with the wand and then execute this command"),
	CRITICAL_ALREADYEXISTS ("%TAG %EError, a shop with this name already exists");

	public Object value;

	Messages (Object value) {
		this.value = value;
	}

}
