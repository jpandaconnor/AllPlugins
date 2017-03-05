package co.uk.randompanda30.sao.commands.booster;

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

import co.uk.randompanda30.sao.commands.handle.Command;
import co.uk.randompanda30.sao.config.Messages;
import co.uk.randompanda30.sao.items.BoosterItems;
import co.uk.randompanda30.sao.string.Dispatch;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CommandBoosterTransfer extends Command {

    final String OLD_TITLE = ChatColor.translateAlternateColorCodes('&',
            "&B&lSAO XP Booster");
    final Material OLD_MATERIAL = Material.ARROW;

    public CommandBoosterTransfer() {
        super("booster transfer", "sao.booster.transfer", "Transfers old boosters into new ones", false);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        if (args.length != 1) {
            Dispatch.sendMessage(Messages.MessagesValues.SYNTAX.value.toString().
                    replaceAll("%syntax", getUsage()), player);
            return true;
        }

        HashMap<ItemStack, Integer> newboosters = new HashMap<>();

        for (ItemStack is : player.getInventory().getContents()) {
            if (is != null) {
                if (is.getItemMeta().hasDisplayName() && is.getItemMeta().getDisplayName().equals(OLD_TITLE)
                        && is.getType().equals(OLD_MATERIAL)) {
                    int amount = is.getAmount();

                    String time = ChatColor.stripColor(is.getItemMeta().getLore().get(7)).replace("Time: ", "");
                    String perc = ChatColor.stripColor(is.getItemMeta().getLore().get(8));
                    perc = perc.replace("Boost: ", "");
                    perc = perc.replace("%", "");

                    int newPerc = Integer.parseInt(perc);

                    player.getInventory().remove(is);

                    newboosters.put(BoosterItems.getItems().getBoosterItem(time, newPerc), amount);
                }
            }
        }

        Iterator iterator = newboosters.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry) iterator.next();
            ItemStack items = (ItemStack) pair.getKey();
            items.setAmount((Integer) pair.getValue());

            player.getInventory().addItem(items);

            iterator.remove();
        }

        Dispatch.sendMessage((String) Messages.MessagesValues.COMMAND_BOOSTER_TRANSFER_DONE.value, player);

        return true;
    }
}