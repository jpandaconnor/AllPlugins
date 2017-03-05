package org.jpanda.PE.config.values;

public enum ConfigValues {

    MYSQL_SET(false),
    MYSQL_SETUPDELAY(5),

    MYSQL_DBIP("null"),
    MYSQL_DBNAME("null"),
    MYSQL_DBUSER("null"),
    MYSQL_DBPASS("null"),
    MYSQL_DBPORT(0);

    public Object value;

    ConfigValues(Object value) {
        this.value = value;
    }
}