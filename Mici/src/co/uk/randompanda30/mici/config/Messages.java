package co.uk.randompanda30.mici.config;

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

import co.uk.randompanda30.mici.Mici;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

import static co.uk.randompanda30.mici.TEMP.*;

public class Messages {

    public Messages(Mici plugin) {
        try {
            messages = new File("plugins/" + plugin.getName() + "/messages.yml");

            if (!messages.exists()) {
                messages.getParentFile().mkdirs();
                messages.createNewFile();
            }

            messagesc = new YamlConfiguration();
            messagescs = messagesc.getConfigurationSection("");
            messagesc.load(messages);

            for (MessagesValues value : MessagesValues.values()) {
                if (messagesc.get(value.name().replaceAll("_", ".")) == null) {
                    messagesc.set(value.name().replaceAll("_", "."), value.value);
                    save();
                }

                value.value = messagesc.get(value.name().replaceAll("_", "."));
            }

            messagesc.load(messages);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        try {
            messagesc.save(messages);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public enum MessagesValues {
        ARG("&4"),
        ERROR("&c"),
        HEADER("&6"),
        NORMAL("&f"),
        TEXT("&7"),
        TAG("%A[%N" + Mici.getPlugin().getName() + "%A]%N "),
        HEAD("&3&l****************************************"),
        ATTENTION("&c&lMici (Bot) -> &a&lHi &5&l%player&a&l, "),
        WARNING("&4"),
        GOOD("&a"),
        BAD("&c"),

        PLUGIN_NOTFORCONSOLE("%EYou cannot do this command in console"),
        PLUGIN_NOPERM("%EYou do not have permission to do this. Permission needed: %A%perm"),
        PLUGIN_SYNTAX("%EIncorrect syntax. Please try: %A/%syntax");

        public Object value;

        MessagesValues(Object value) {
            this.value = value;
        }
    }
}