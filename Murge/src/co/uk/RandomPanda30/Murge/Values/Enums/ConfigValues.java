package co.uk.RandomPanda30.Murge.Values.Enums;

import org.bukkit.Bukkit;

public enum ConfigValues {

	WORLD {
		public Object getValue() {
			return Bukkit.getWorld("world");
		}
	},

	WORLDBORDER_ENABLED {
		public Object getValue() {
			return false;
		}
	},

	WORLDBORDER_CENTRE {
		public Object getValue() {
			return "CENTRE";
		}
	},

	WORLDBORDER_SIZE {
		public Object getValue() {
			return 10.0;
		}
	},

	WORLDBORDER_DAMAGE {
		public Object getValue() {
			return 5;
		}
	},

	WORLDBORDER_CLOSES {
		public Object getValue() {
			return true;
		}
	},

	WORLDBORDER_CLOSELIMIT {
		public Object getValue() {
			return 100;
		}
	},

	WORLDBORDER_CLOSETIME {
		public Object getValue() {
			return 60;
		}
	},

	TIMEBEFOREPURGE {
		public Object getValue() {
			return 30;
		}
	},

	PURGEDURATION {
		public Object getValue() {
			return 20;
		}
	},

	WORLDBORDERDEATHTHRESHOLD {
		public Object getValue() {
			return 5;
		}
	},

	EFFECT_BLEED {
		public Object getValue() {
			return true;
		}
	},

	LIGHTNINGONDEATH {
		public Object getValue() {
			return true;
		}
	},

	MOVETOSPECTATEWHENNOPURGE {
		public Object getValue() {
			return false;
		}
	},

	USINGVAULT {
		public Object getValue() {
			return false;
		}
	},

	BROADCAST_PLAYERLEAVESCOMBAT {
		public Object getValue() {
			return true;
		}
	},

	CURRENCYSTRING {
		public Object getValue() {
			return "$";
		}
	},

	FIGHTCOOLDOWN {
		public Object getValue() {
			return 1;
		}
	},

	WEATHERENABLED {
		public Object getValue() {
			return false;
		}
	},

	BLOCKBREAKDURINGPURGE {
		public Object getValue() {
			return false;
		}
	},

	BLOCKPLACEURINGPURGE {
		public Object getValue() {
			return false;
		}
	},

	MOBDAMAGEASCOMBAT {
		public Object getValue() {
			return false;
		}
	},

	PVPDURINGGRACE {
		public Object getValue() {
			return false;
		}
	},

	MONEY_ONKILL {
		public Object getValue() {
			return 30;
		}
	},

	SPAWNLOCATION {
		public Object getValue() {
			return "";
		}
	},

	SPECTATORLOCATION {
		public Object getValue() {
			return "";
		}
	},

	MONEY_ONPURGESURVIVE {
		public Object getValue() {
			return 60;
		}
	},

	/* Bungee Settings */

	BUNGEE_ENABLE {
		public Object getValue() {
			return false;
		}
	},

	BUNGEE_LOBBY {
		public Object getValue() {
			return "lobby";
		}
	},

	BUNGEE_SENDDELAY {
		public Object getValue() {
			return 2;
		}
	},

	BUNGEE_SENDONLEAVE {
		public Object getValue() {
			return true;
		}
	},

	BUNGEE_SENDONDEATH {
		public Object getValue() {
			return false;
		}
	},

	/* Commands */

	COMMANDS_START {
		public Object getValue() {
			return true;
		}
	},

	COMMANDS_STOP {
		public Object getValue() {
			return true;
		}
	},

	COMMANDS_FIXWB {
		public Object getValue() {
			return true;
		}
	},

	/* MySQL */

	MYSQL_ENABLE {
		public Object getValue() {
			return false;
		}
	},

	MYSQL_HOST {
		public Object getValue() {
			return "localhost";
		}
	},

	MYSQL_DATABASE {
		public Object getValue() {
			return "database";
		}
	},

	MYSQL_USER {
		public Object getValue() {
			return "User";
		}
	},

	MYSQL_PASSWORD {
		public Object getValue() {
			return "password";
		}
	},

	MYSQL_PORT {
		public Object getValue() {
			return 3306;
		}
	},

	/* Any join methods here */

	JOIN_BLOCKINPROGRESS {
		public Object getValue() {
			return false;
		}
	},

	/* Regeneration */

	REGENERATION_ORES {
		public Object getValue() {
			return "1d1h30m10s";
		}
	},

	REGENERATION_TREES {
		public Object getValue() {
			return "1d1h30m10s";
		}
	},

	// Enabled things here

	REGENERATION_OREENABLED {
		public Object getValue() {
			return true;
		}
	},

	REGENERATION_TREEENABLED {
		public Object getValue() {
			return true;
		}
	},
	
	CHAT_SEPERATE {
		public Object getValue() {
			return true;
		}
	},

	/* Times */

	TIME_ORES {
		public Object getValue() {
			return "10m";
		}
	},

	TIME_TREES {
		public Object getValue() {
			return "10m";
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