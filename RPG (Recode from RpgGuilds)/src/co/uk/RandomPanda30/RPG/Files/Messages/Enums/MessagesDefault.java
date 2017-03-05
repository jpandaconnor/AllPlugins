package co.uk.RandomPanda30.RPG.Files.Messages.Enums;

import java.util.ArrayList;

@SuppressWarnings("serial")
public enum MessagesDefault {

	ARG ("&4"),
	ERROR ("&c"),
	HEADER ("&6"),
	NORMAL ("&f"),
	TEXT ("&7"),
	TAG ("&4[&fRPG&4]&f"),
	WARNING ("&4"),
	ON ("&a"),
	OFF ("&c"),

	// All the list of default config objects will go in here

	TEST ("TEST"),

	NOPERM ("%EYou do not have permission to do this"),
	ONLYCONSOLE ("%EOnly players are allowed to do this command"),
	NOTINAGUILD ("%EYou are not in a guild"),

	SH (new ArrayList<String>() {
		{
			add("%TAG %GIs being enabled");
		}
	}),

	SYNTAX_GRANKCREATE ("%EImproper usage! Please use /grank create rank title"),
	SYNTAX_GRANKDELETE ("%EYou must include a rank to delete!"),
	SYNTAX_GRANKSET ("%EImproper usage! Please use /grank set playername rank"),
	SYNTAX_GRANKTITLE ("%EImproper usage! Please use /grank title rankname newtitle"),
	SYNTAX_GRANKPERMS ("%EImproper usage! Please use /grank perms rankname permission_name true/false"),

	RANK_CREATED ("%NYou have created the rank: %A%rank"),
	RANK_DELETED ("%NYou have deleted the rank: %A%rank"),
	RANK_ALREADYEXISTS ("%EError, this rank already exists"),
	RANK_CHANGEDTO ("%NYour rank has been changed to %A%rank"),
	RANK_NOEXIST ("%EError, this rank does not exist"),
	RANK_CANNOTPROMOTETOLEADER ("%EError, you cannot promote someone to leader in this way"),
	RANK_NOLONGERLEADER ("%NYou are no longer the leader of the guild: %A%guild"),
	RANK_ISNOWTHELEADER ("%A%player %Nis now the leader of %A%guild"),
	RANK_YOUARENOWTHELEADER ("%NYou are now the leader of %A%guild"),
	RANK_THEIRRANKCHANGEDTO ("%A%player %Nhas had their rank changed to %A%rank"),
	RANK_YOURRANKCHANGETO ("%NYour rank has been changed to: %A%rank"),
	RANK_NOMOREPERMISSION ("%NMemebrs with the %A%rank %Nno longer have %A%perm %Npermissions"),
	RANK_YESMOREPERMISSION ("%NMemebrs with the %A%rank %Nnow have %A%perm %Npermissions"),
	RANK_CHANGEDLEADERRANKNAME ("%NThe leader rank name has now been changed to %A%rank"),
	RANK_CHANGEDDEFAULTRANKNAME ("%NThe default rank name has now been changed to %A%rank"),

	SHM (new ArrayList<String>() {
		{
			add("%TAG %BIs being disabled");
		}
	});

	public Object value;

	MessagesDefault (Object value) {
		this.value = value;
	}
}