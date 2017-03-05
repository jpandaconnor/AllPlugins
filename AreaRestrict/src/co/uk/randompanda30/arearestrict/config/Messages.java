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

public class Messages {

    public Messages(AreaRestrict plugin) {
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
        TAG("%A[%N" + AreaRestrict.getPlugin().getName() + "%A]%N "),
        WARNING("&4"),
        GOOD("&a"),
        BAD("&c"),

        PLUGIN_NOTFORCONSOLE("%EYou cannot do this command in console"),
        PLUGIN_NOPERM("%EYou do not have permission to do this. Permission needed: %A%perm"),
        PLUGIN_SYNTAX("%EIncorrect syntax. Please try: %A/%syntax"),

        SETRANK_GROUPDOESNTEXIST("%EA rank with this name does not exist"),
        SETRANK_NOSELECTION("%EPlease select both points with the WorldEdit wand and try again"),
        SETRANK_OVERLAPS("%EYour selection overlaps with an existing region. Please reselect the region and try again"),
        SETRANK_DONE("%GAn area with a new rank has been set!"),

        DELETE_NOTINREGION("%EYou must be in a region to delete it"),
        DELETE_NOREGIONS("%EThere are no regions to delete"),
        DELETE_REGIONDELETED("%GRegion deleted"),

        RESTRICTION_CANNOTGOIN("%EYou cannot go in this region as you do not have the rank: %A%rank");

        /*
        Configure command stuff
         */

        public Object value;

        MessagesValues(Object value) {
            this.value = value;
        }
    }
}