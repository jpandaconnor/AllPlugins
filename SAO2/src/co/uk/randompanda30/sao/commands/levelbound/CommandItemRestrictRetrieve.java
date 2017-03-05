package co.uk.randompanda30.sao.commands.levelbound;

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

import co.uk.randompanda30.sao.TEMP;
import co.uk.randompanda30.sao.commands.handle.Command;
import co.uk.randompanda30.sao.config.Messages;
import co.uk.randompanda30.sao.config.modules.CLevelBound;
import co.uk.randompanda30.sao.string.Dispatch;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.UUID;

public class CommandItemRestrictRetrieve extends Command {

    public CommandItemRestrictRetrieve() {
        super("levelrestrict retrieve", "sao.levelbound.retrieve", "Retrieve level restricted items", false);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        UUID uuid = player.getUniqueId();

        if (args.length != 1) {
            Dispatch.sendMessage(Messages.MessagesValues.SYNTAX.value.toString().
                    replaceAll("%syntax", getUsage()), player);
            return true;
        }

        if (!TEMP.boosterf.contains("dump." + uuid.toString())) {

            return true;
        }

        boolean ntg = false;
        boolean fi = false;

        if (TEMP.levelboundf.contains("dump." + uuid.toString())) {
            for (String s : TEMP.levelboundf.getConfigurationSection("dump." + uuid.toString()).getKeys(false)) {
                int level = Integer.parseInt(s);

                if (player.getLevel() >= level) {
                    ArrayList<ItemStack> items = (ArrayList<ItemStack>) TEMP.levelboundf.get
                            ("dump." + uuid.toString() + "." + s);
                    int i = 0;
                    for (ItemStack item : player.getInventory()) {
                        if (item == null) {
                            i++;
                        }
                    }

                    if (i < items.size()) {
                        fi = true;
                    } else {
                        ntg = true;
                        TEMP.levelboundf.set("dump." + uuid.toString() + "." + s, null);
                        CLevelBound.save();
                    }
                }
            }

            if (fi) {
                Dispatch.sendMessage((String) Messages.MessagesValues.MODULES_ITEMRESTRICT_NOSPACE.value, player);
            }

            if (ntg) {
                Dispatch.sendMessage((String) Messages.MessagesValues.MODULES_ITEMRESTRICT_GIVENBACK.value, player);
            }
        }
        return true;
    }
}