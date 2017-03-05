package co.uk.RandomPanda30.MineRP.Files.JobEnums;

import java.util.ArrayList;

@SuppressWarnings("serial")
public enum CitizenE {

	DESCRIPTION (new ArrayList<String>() {
		{
			add("%NAs a citzen, you don't do much really.");
			add("%NGo round, meet new players and get involved");
		}
	}),

	SALARY (500), COLOUR ("&a"), VOTE (false);

	public Object value;

	CitizenE (Object value) {
		this.value = value;
	}
}