package co.uk.RandomPanda30.CityRP.Configs.Enums;

public enum Config {

	TEST ("test"),

	PLAYER_RANGE (10),

	MYSQL_SET (false),
	MYSQL_SETUPDELAY (5),

	MYSQL_DBIP ("null"),
	MYSQL_DBNAME ("null"),
	MYSQL_DBUSER ("null"),
	MYSQL_DBPASS ("null"),
	MYSQL_DBPORT (0),

	LOCATION_SPAWN_SET (false),
	LOCATION_SPAWN_LOCATION ("");

	public Object value;

	Config (Object value) {
		this.value = value;
	}
}