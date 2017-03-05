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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CommandItemRestrictEdit extends Command {

    public CommandItemRestrictEdit() {
        super("levelrestrict edit <Level>", "sao.levelbound.edit", "Edits the level of a restriction on an item", false);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        // args[0] = edit
        // args[1] = level

        if (args.length != 2) {
            Dispatch.sendMessage(Messages.MessagesValues.SYNTAX.value.toString().
                    replaceAll("%syntax", getUsage()), player);
            return true;
        }

        if (player.getItemInHand() == null) {
            Dispatch.sendMessage((String) Messages.MessagesValues.COMMAND_ITEMRESTRICT_EDIT_NOTHINGINHAND.value, player);
            return true;
        }

        ItemStack inHand = player.getItemInHand();
        HashMap<Integer, ArrayList<ItemStack>> items = new HashMap<>();

        boolean inconf = false;

        if (TEMP.levelboundf.contains("levels")) {
            for (String s : TEMP.levelboundf.getConfigurationSection("levels").getKeys(false)) {
                ArrayList<ItemStack> is = (ArrayList<ItemStack>) TEMP.levelboundf.get("levels." +
                        Integer.parseInt(s) + ".items");

                items.put(Integer.parseInt(s), is);
            }

            Iterator i = items.entrySet().iterator();
            while (i.hasNext()) {
                Map.Entry pair = (Map.Entry) i.next();

                ArrayList<ItemStack> ilist = (ArrayList<ItemStack>) pair.getValue();
                if (ilist.contains(inHand)) {
                    inconf = true;
                }
            }
        }

        if (!inconf) {
            Dispatch.sendMessage((String) Messages.MessagesValues.COMMAND_ITEMRESTRICT_EDIT_NORESTRICTION.value, player);
            return true;
        }

        int level = 0;

        try {
            level = Integer.parseInt(args[1]);
        } catch (NumberFormatException event) {
            Dispatch.sendMessage((String) Messages.MessagesValues.COMMAND_ITEMRESTRICT_EDIT_NOTNUMBER.value, player);
            return true;
        }

        Iterator i = items.entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry pair = (Map.Entry) i.next();

            int ilevel = (int) pair.getKey();
            ArrayList<ItemStack> ilist = (ArrayList<ItemStack>) pair.getValue();

            if (ilist.contains(inHand)) {
                ilist.remove(inHand);

                if (ilist.isEmpty()) {
                    TEMP.levelboundf.set("levels." + Integer.toString(ilevel), null);
                } else {
                    TEMP.levelboundf.set("levels." + Integer.toString(ilevel) + ".items", ilist);
                }

                if (TEMP.levelboundf.contains("levels." + Integer.toString(level) + ".items")) {
                    ArrayList<ItemStack> ii = (ArrayList<ItemStack>) TEMP.levelboundf.get("levels." + Integer.toString(level)
                            + ".items");

                    ii.add(inHand);

                    TEMP.levelboundf.set("levels." + Integer.toString(level) + ".items", ii);
                    CLevelBound.save();
                } else {
                    TEMP.levelboundf.set("levels." + Integer.toString(level) + ".items", new ArrayList<ItemStack>() {
                        {
                            add(inHand);
                        }
                    });
                }
                CLevelBound.save();
            }
        }
        Dispatch.sendMessage((String) Messages.MessagesValues.COMMAND_ITEMRESTRICT_EDIT_EDITED.value, player);
        return true;
    }
}