package co.uk.RandomPanda30.CityRP.Configs.Enums.Citizen;

@SuppressWarnings("unused")
public enum CitizenV {

	TEST {
		public Object getTest() {
			return "Test";
		}
	},

	SALARY {
		public Object getSalary() {
			return 500;
		}

	},

	COLOUR {

		public Object getColour() {
			return "&a";
		}

	};

	public String toString() {
		return name();
	}

	public Object getValue() {
		throw new AbstractMethodError("This error should never be shown.");
	}
}