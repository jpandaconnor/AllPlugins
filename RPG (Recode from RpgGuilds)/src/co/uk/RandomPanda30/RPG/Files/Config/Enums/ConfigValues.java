package co.uk.RandomPanda30.RPG.Files.Config.Enums;

import java.util.ArrayList;

public enum ConfigValues {

	TEST {
		public Object getValue() {
			return "Test";
		}
	},

	TP {
		public Object getValue() {
			return true;
		}
	},

	Rewards {
		public Object getValue() {
			return new ArrayList<String>();
		}
	},

	RewardDelay {
		public Object getValue() {
			return 10;
		}
	},

	GuildPrefixes {
		public Object getValue() {
			return true;
		}
	},

	Chat {
		public Object getValue() {
			return true;
		}
	},

	NoBuild {
		public Object getValue() {
			return true;
		}
	},

	Signs {
		public Object getValue() {
			return new ArrayList<String>();
		}
	},

	VAULT {
		public Object getValue() {
			return true;
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