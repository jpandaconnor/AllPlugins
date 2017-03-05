package co.uk.randompanda30.sao.config.modules;

/* 
   Created by panda on 18/08/16.
   
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
import java.util.ArrayList;

import static co.uk.randompanda30.sao.TEMP.*;

public class CPlayerDataReset {

    public CPlayerDataReset() {
        try {
            playerdata = new File("plugins/" + SAO.getPlugin() + "/modules/playerdata.yml");
            if (!playerdata.exists()) {
                playerdata.getParentFile().mkdirs();
                playerdata.createNewFile();
            }

            playerdataf = new YamlConfiguration();
            playerdatac = playerdataf.getConfigurationSection("");
            playerdataf.load(playerdata);

            for (PlayerDataValues value : PlayerDataValues.values()) {
                if (playerdatac.get(value.name().replaceAll("_", ".")) == null) {
                    playerdatac.set(value.name().replaceAll("_", "."), value.value);
                    save();
                }
                value.value = playerdatac.get(value.name().replaceAll("_", "."));
            }

            playerdataf.load(playerdata);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        try {
            playerdataf.save(playerdata);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public enum PlayerDataValues {
        WORLDS_PATH(new ArrayList<String>() {
            {
                add("AincradFloor1");
            }
        }),

        RESET_PATH("/home/sao/floor1/");

        public Object value;

        PlayerDataValues(Object value) {
            this.value = value;
        }
    }
}