package co.uk.randompanda30.fileguilds.string;

import static co.uk.randompanda30.fileguilds.config.Messages.MessagesValues.*;

public class Format {

    public static String format(String message) {
        return message.replaceAll("%TAG", (String) TAG.value)
                .replaceAll("%N", (String) NORMAL.value)
                .replaceAll("%W", (String) WARNING.value)
                .replaceAll("%E", (String) ERROR.value)
                .replaceAll("%A", (String) ARG.value)
                .replaceAll("%H", (String) HEADER.value)
                .replaceAll("%G", (String) GOOD.value)
                .replaceAll("%B", (String) BAD.value)
                .replaceAll("(&([a-fk-or0-9]))", "\u00A7$2")
                .replaceAll("&u", "\n");
    }
}