package co.uk.RandomPanda30.SAO.Manager;

import co.uk.RandomPanda30.Mojang.profiles.HttpProfileRepository;
import co.uk.RandomPanda30.Mojang.profiles.Profile;

import java.util.UUID;
import java.util.regex.Pattern;

public class ServerManager {

    private static final Pattern UUID_PATTERN = Pattern.compile("([0-9a-fA-F]{8})([0-9a-fA-F]{4})([0-9a-fA-F]{4})([0-9a-fA-F]{4})([0-9a-fA-F]+)");
    private static final HttpProfileRepository repository = new HttpProfileRepository("minecraft");

    public static UUID getUUID(String username) {
        Profile[] profile = repository.findProfilesByNames(username);
        if (profile.length == 1) {
            return UUID.fromString(UUID_PATTERN.matcher(profile[0].getId()).replaceFirst("$1-$2-$3-$4-$5"));
        }
        return null;
    }
}