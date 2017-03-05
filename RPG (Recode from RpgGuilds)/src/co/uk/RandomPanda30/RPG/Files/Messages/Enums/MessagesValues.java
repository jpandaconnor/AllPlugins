package co.uk.RandomPanda30.RPG.Files.Messages.Enums;

import java.util.ArrayList;

public enum MessagesValues {

	ARG {
		public Object getValue() {
			return "&4";
		}
	},

	ERROR {
		public Object getValue() {
			return "&c";
		}
	},

	HEADER {
		public Object getValue() {
			return "&6";
		}
	},

	NORMAL {
		public Object getValue() {
			return "&f";
		}
	},

	TEXT {
		public Object getValue() {
			return "&7";
		}
	},

	TAG {
		public Object getValue() {
			return "&4[&fRPG&4]&f";
		}
	},

	WARNING {
		public Object getValue() {
			return "&4";
		}
	},

	ON {
		public Object getValue() {
			return "&a";
		}
	},

	OFF {
		public Object getValue() {
			return "&c";
		}
	},

	SH {
		public Object getValue() {
			return new ArrayList<String>();
		}
	},

	SHM {
		public Object getValue() {
			return new ArrayList<String>();
		}
	},

	NOPERM,
	ONLYCONSOLE,
	NOTINAGUILD,

	SYNTAX_GRANKCREATE,
	SYNTAX_GRANKDELETE,
	SYNTAX_GRANKSET,
	SYNTAX_GRANKTITLE,
	SYNTAX_GRANKPERMS,

	RANK_ALREADYEXISTS,
	RANK_CREATED,
	RANK_CHANGEDTO,
	RANK_NOEXIST,
	RANK_DELETED,
	RANK_CANNOTPROMOTETOLEADER,
	RANK_NOLONGERLEADER,
	RANK_ISNOWTHELEADER,
	RANK_YOUARENOWTHELEADER,
	RANK_THEIRRANKCHANGEDTO,
	RANK_YOURRANKCHANGETO,
	RANK_YESMOREPERMISSION,
	RANK_NOMOREPERMISSION,
	RANK_CHANGEDLEADERRANKNAME,
	RANK_CHANGEDDEFAULTRANKNAME,

	TEST {
		public Object getValue() {
			return "TEST";
		}
	};

	@Override
	public String toString() {
		return name();
	}

	public Object getValue() {
		throw new AbstractMethodError("This error should never be shown.");
	}
}