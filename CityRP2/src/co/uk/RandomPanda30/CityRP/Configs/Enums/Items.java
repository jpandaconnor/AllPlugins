package co.uk.RandomPanda30.CityRP.Configs.Enums;

import java.util.ArrayList;

public enum Items {

	LOCATION_SPAWN_NAME ("%ASpawn location"),
	LOCATION_SPAWN_LORES (new ArrayList<String>() {
		private static final long serialVersionUID = 2123479415148721431L;

		{
			add("%NPlace this item to set the spawn location");
			add("%NBreak this item to remove the spawn location");
		}
	});

	public Object value;

	Items (Object value) {
		this.value = value;
	}
}