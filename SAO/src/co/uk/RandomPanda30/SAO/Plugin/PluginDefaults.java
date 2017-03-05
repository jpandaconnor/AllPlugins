package co.uk.RandomPanda30.SAO.Plugin;

import co.uk.RandomPanda30.SAO.SAO;

import java.util.List;

public class PluginDefaults {

    private String name;
    private String version;
    private String website;

    private List<String> authors;

    public PluginDefaults(SAO plugin) {
        name = plugin.getDescription().getName();
        version = plugin.getDescription().getVersion();
        website = plugin.getDescription().getWebsite();
        authors = plugin.getDescription().getAuthors();
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getWebsite() {
        return website;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public String getAuthor(int index) {
        if (authors.get(index) != null) {
            return authors.get(index);
        }
        return "";
    }
}