package co.uk.randompanda30.sao.config.modules;

/*
   Created by panda on 16/07/16.

   Copyright 2016 JPanda (Connor Brady)

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

import co.uk.randompanda30.sao.SAO;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

import static co.uk.randompanda30.sao.TEMP.*;

public class CBooster {

    public CBooster() {
        try {
            booster = new File("plugins/" + SAO.getPlugin() + "/modules/booster.yml");
            if (!booster.exists()) {
                booster.getParentFile().mkdirs();
                booster.createNewFile();
            }

            boosterf = new YamlConfiguration();
            boosterc = boosterf.getConfigurationSection("");
            boosterf.load(booster);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        try {
            boosterf.save(booster);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}