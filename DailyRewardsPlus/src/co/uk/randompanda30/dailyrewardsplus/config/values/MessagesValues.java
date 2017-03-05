package co.uk.randompanda30.dailyrewardsplus.config.values;

import co.uk.randompanda30.dailyrewardsplus.Main;

import java.util.ArrayList;
import java.util.List;

public enum MessagesValues {

    ARG("&4"),
    ERROR("&c"),
    HEADER("&6"),
    NORMAL("&f"),
    TEXT("&7"),
    TAG("%A[%NGuilds%A]%N"),
    WARNING("&c"),
    GOOD("&a"),
    BAD("&4"),

    NOCONSOLE("%EYou cannot do this as console"),
    NOPERM("%EYou do not have permission to do this"),

    NOTIFICATIONS_NOMYSQL(
            "%TAG %EMySQL has not been configued. Entering setup in %time seconds..."),
    NOTIFICATIONS_DATABASESETUP(
            "%TAG %NYour database has not been setup yet. Please look in console to setup MySQL"),

    START(new ArrayList<String>() {
        private static final long serialVersionUID = 6845029486259327038L;

        {
            add("%TAG %NLoading...");
            add("%TAG %NMade by %ARandomPanda30");
            add("%TAG %NVersion: %A%no");
        }
    }),

    STOP(new ArrayList<String>() {
        private static final long serialVersionUID = -5539926430413405204L;

        {
            add("%TAG %NStopping...");
            add("%TAG %NMade by %ARandomPanda30");
            add("%TAG %NVersion: %A%no");
        }
    }),

    EXCEPTIONLAYOUT_CONSOLE(new ArrayList<String>() {
        {
            add("%H=========************************************************************=========");
            add("%A" + ((List<String>) Main.getProperty("authors")).get(0));
            add("  ");
            add("%EAn error occurred. See below for possible solutions and more details");
            add("   ");
            add("%EException type: %A%ex");
            add("    ");
            add("%EReason: %A%reason");
            add("     ");
            add("%etx");
            add("      ");
            add("%GSolutions: ");
            add("       ");
            add("%solutions");
            add("         ");
            add("%NIf no solutions work, contact: " + ((List<String>) Main.getProperty("authors")).get(0));
            add("          ");
            add("%trace");
            add("%H=====************************************************************=====");
        }
    });

    public Object value;

    MessagesValues(Object value) {
        this.value = value;
    }
}