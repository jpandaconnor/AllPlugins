package co.uk.randompanda30.dailyrewardsplus.utilities.string;

import co.uk.randompanda30.dailyrewardsplus.config.values.MessagesValues;

public class Format {

    public static String format(String message) {
        return message.replaceAll("%TAG", (String) MessagesValues.TAG.value)
                .replaceAll("%N", (String) MessagesValues.NORMAL.value)
                .replaceAll("%W", (String) MessagesValues.WARNING.value)
                .replaceAll("%E", (String) MessagesValues.ERROR.value)
                .replaceAll("%A", (String) MessagesValues.ARG.value)
                .replaceAll("%H", (String) MessagesValues.HEADER.value)
                .replaceAll("%G", (String) MessagesValues.GOOD.value)
                .replaceAll("%B", (String) MessagesValues.BAD.value)
                .replaceAll("(&([a-fk-or0-9]))", "\u00A7$2")
                .replaceAll("&u", "\n");
    }
}