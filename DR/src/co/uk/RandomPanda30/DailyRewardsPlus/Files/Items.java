package co.uk.RandomPanda30.DailyRewardsPlus.Files;

import java.util.ArrayList;

public enum Items {

	EXITBUTTON_NAME ("%BExit"), EDITITEMS_NAME ("%AEdit reward items here"),

	TIME_NAME ("%BCannot claim"), @SuppressWarnings("serial")
	TIME_LORES (new ArrayList<String>() {
		{
			add("%NYou cannot claim for another");
			add("%N24 hours");
			add("%NTime remaining: %A'tijd'");
		}
	}),

	CLAIMNOW_NAME ("%GClaim now!"), @SuppressWarnings("serial")
	CLAIMNOW_LORES (new ArrayList<String>() {
		{
			add("%NClick here to claim your");
			add("%Ndaily reward");
		}
	});

	public Object value;

	Items (Object value) {
		this.value = value;
	}
}