package co.uk.RandomPanda30.CasinoM.Files;

import co.uk.RandomPanda30.CasinoM.CasinoData;

public enum Messages {

	ARG ("&4"),
	ERROR ("&c"),
	HEADER ("&6"),
	NORMAL ("&f"),
	TAG ("%A[%N" + CasinoData.getDataFile().getName() + "%A]%N"),
	WARNING ("&4"),
	ON ("&a"),
	OFF ("&c"),

	GENERAL_GIVENWAND ("%TAG %NYou have been given the creation wand"),

	CREATION_SELECTION ("%TAG %NSelection %A%selection%N - X: %A%xLoc%N. Y: %A%yLoc%N. Z: %A%zLoc%N."),

	CREATION_SLOTMACHINE_CREATED ("%TAG %NSlot machine with the name %A%name"),

	CRITICAL_INVALIDCOMMAND ("%TAG %EThis command has not been found. Do /casino help for commands"),
	CRITICAL_INVALIDMACHINE ("%TAG %EError, the machine type is invalid."),

	CRITICAL_SLOTMACHINE_INCORRECTBLOCKS ("%TAG %EError, in order to create a slot machine, there must be 3 items frames and 1 lever in the selection"),
	CRITICAL_SLOTMACHINE_ALREADYEXISTS ("%TAG %EError, a slot machine with this name already exists"),
	CRITICAL_SLOTMACHINE_NOTINVECTOR ("%TAG %EError, you are too far away to use this"),

	CRITICAL_ALREADYHAVE ("%TAG %EError, you already have this item in your inventory");

	public Object value;

	Messages (Object value) {
		this.value = value;
	}
}