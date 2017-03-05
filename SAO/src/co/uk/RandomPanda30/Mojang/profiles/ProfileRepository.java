package co.uk.RandomPanda30.Mojang.profiles;

public interface ProfileRepository {
    public Profile[] findProfilesByNames(String... names);
}