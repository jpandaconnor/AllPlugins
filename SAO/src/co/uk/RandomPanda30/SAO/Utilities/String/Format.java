package co.uk.RandomPanda30.SAO.Utilities.String;

import co.uk.RandomPanda30.SAO.Config.Messages;

import java.util.UUID;

public class Format {

    public static String format(String message) {
        return message.replaceAll("%TAG", (String) Messages.MessagesValues.TAG.value)
                .replaceAll("%N", (String) Messages.MessagesValues.NORMAL.value)
                .replaceAll("%W", (String) Messages.MessagesValues.WARNING.value)
                .replaceAll("%E", (String) Messages.MessagesValues.ERROR.value)
                .replaceAll("%A", (String) Messages.MessagesValues.ARG.value)
                .replaceAll("%H", (String) Messages.MessagesValues.HEADER.value)
                .replaceAll("%G", (String) Messages.MessagesValues.GOOD.value)
                .replaceAll("%B", (String) Messages.MessagesValues.BAD.value)
                .replaceAll("(&([a-fk-or0-9]))", "\u00A7$2")
                .replaceAll("&u", "\n");
    }

    public static UUID rawStringToUUID(String uuidAsString) {
        return UUID.fromString(uuidAsString.replaceAll(
                "(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})",
                "$1-$2-$3-$4-$5"));
    }
}