package co.uk.RandomPanda30.Murge.Values.Enums;

import java.util.ArrayList;

public enum ItemsValues {

	EXIT {
		public Object getValue() {
			return "%BExit";
		}
	},

	BACK {
		public Object getValue() {
			return "%TGo back";
		}
	},

	EXITEDITMODE_NAME {
		public Object getValue() {
			return "%BExit edit mode";
		}
	},

	EXITEDITMODE_LORES {
		public Object getValue() {
			return new ArrayList<String>();
		}
	},

	/* Adjust fly menu */

	ADJUSTFLY_NAME {
		public Object getValue() {
			return "&3Adjust fly speed";
		}
	},

	ADJUSTFLY_LORES {
		public Object getValue() {
			return new ArrayList<String>();
		}
	},

	/* Details */

	DETAILS_NAME {
		public Object getValue() {
			return "%AMurge";
		}
	},

	DETAILS_LORES {
		public Object getValue() {
			return new ArrayList<String>();
		}
	},

	/* Head */

	HEAD_NAME {
		public Object getValue() {
			return "%A%player";
		}
	},

	HEAD_LORES {
		public Object getValue() {
			return new ArrayList<String>();
		}
	},

	/* Tools */

	LEAVETOOL_NAME {
		public Object getValue() {
			return "%BLeave the server";
		}
	},

	LEAVETOOL_LORES {
		public Object getValue() {
			return new ArrayList<String>();
		}
	},

	COMPASS_NAME {
		public Object getValue() {
			return "&eTracking compass";
		}
	},

	COMPASS_LORES {
		public Object getValue() {
			return new ArrayList<String>();
		}
	},

	/* Locations */

	EDIT_SPAWNLOCATIONNAME {
		public Object getValue() {
			return "%NEdit %ASpawn %Nlocation";
		}
	},

	EDIT_SPAWNLOCATIONLORES {
		public Object getValue() {
			return new ArrayList<String>();
		}
	},

	EDIT_SPECTATORLOCATIONNAME {
		public Object getValue() {
			return "%NEdit %ASpectator %Nspawn location";
		}
	},

	EDIT_SPECTATORLOCATIONLORES {
		public Object getValue() {
			return new ArrayList<String>();
		}
	},

	/* Settings */

	SETTINGS_LOCATIONNAME {
		public Object getValue() {
			return "%NEdit: %ALocations";
		}
	},

	SETTINGS_LOCATIONLORES {
		public Object getValue() {
			return new ArrayList<String>();
		}
	},

	SETTINGS_WORLDBORDERNAME {
		public Object getValue() {
			return "%NEdit: %AWorld Border";
		}
	},

	SETTINGS_WORLDBORDERLORES {
		public Object getValue() {
			return new ArrayList<String>();
		}
	},

	SETTINGS_PURGEVALUENAME {
		public Object getValue() {
			return "%NEdit: %APurge values";
		}
	},

	SETTINGS_PURGEVALUELORES {
		public Object getValue() {
			return new ArrayList<String>();
		}
	},

	SETTINGS_PURGEOPTIONSNAME {
		public Object getValue() {
			return "%NEdit: %APurge options";
		}
	},

	SETTINGS_PURGEOPTIONSLORES {
		public Object getValue() {
			return new ArrayList<String>();
		}
	},

	SETTINGS_BROADCASTOPTIONSNAME {
		public Object getValue() {
			return "%NEdit: %ABroadcast options";
		}
	},

	SETTINGS_BROADCASTOPTIONSLORES {
		public Object getValue() {
			return new ArrayList<String>();
		}
	},

	SETTINGS_CONTROLPANELNAME {
		public Object getValue() {
			return "%AMurge: %Ncontrol panel";
		}
	},

	SETTINGS_CONTROLPANELLORES {
		public Object getValue() {
			return new ArrayList<String>();
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