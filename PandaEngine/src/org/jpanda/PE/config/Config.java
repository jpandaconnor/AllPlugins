package org.jpanda.PE.config;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jpanda.PE.PE;

import java.io.File;
import java.io.IOException;

public class Config {

    public Enum values;
    public File file;
    public FileConfiguration filef;
    public ConfigurationSection files;
    private String name;
    private String cs;
    private String location;

    /*
    "." = Normal config location
    Anything else is a custom section
     */

    public Config(String name, String cs, String location, Enum values) {
        if (name != null) {
            this.name = name;
        } else {
            // Exception here
        }

        this.cs = (cs == null ? null : cs);
        this.location = (location == null ? null : location);
        this.values = (values == null ? null : values);

        init();
    }

    private void init() {
        try {
            file = new File((location.equals(".") || location == null ? "plugins/" + PE.getProperty("name") + "/" +
                    name + ".yml" : location + name + ".yml"));
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            filef = new YamlConfiguration();
            files = filef.getConfigurationSection((cs == null ? "" : cs));
            filef.load(file);

            if (values == null) {
                for (Object value : values.getDeclaringClass().getEnumConstants()) {
                    if (files.get(value.toString().replaceAll("_", ".")) == null) {
                        files.set(value.toString().replaceAll("_", "."), Enum.valueOf(values.getDeclaringClass(),
                                value.toString()));
                        save();
                    }
                }
            }
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    private void save() {
        try {
            filef.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}