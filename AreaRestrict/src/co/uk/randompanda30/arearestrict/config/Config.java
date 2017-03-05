package co.uk.randompanda30.arearestrict.config;

import co.uk.randompanda30.arearestrict.AreaRestrict;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

import static co.uk.randompanda30.arearestrict.TEMP.*;

/**
 * Created by panda on 05/07/16.
 *
 *
 *   Copyright 2016 JPanda (Connor Brady)
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

public class Config {

    public Config(AreaRestrict plugin) {
        try {
            config = new File("plugins/" + plugin.getName() + "/config.yml");
            if (!config.exists()) {
                config.getParentFile().mkdirs();
                config.createNewFile();
            }

            configc = new YamlConfiguration();
            configcs = configc.getConfigurationSection("");
            configc.load(config);

            for (ConfigValues value : ConfigValues.values()) {
                if (configc.get(value.name().replaceAll("_", ".")) == null) {
                    configc.set(value.name().replaceAll("_", "."), value.value);
                    save();
                }

                value.value = configc.get(value.name().replaceAll("_", "."));
            }

            configc.load(config);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        try {
            configc.save(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public enum ConfigValues {
        ENABLED(true);

        public Object value;

        ConfigValues(Object value) {
            this.value = value;
        }
    }
}