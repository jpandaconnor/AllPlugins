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

import co.uk.randompanda30.sao.TEMP;
import co.uk.randompanda30.sao.commands.handle.Command;
import co.uk.randompanda30.sao.config.Messages;
import co.uk.randompanda30.sao.config.modules.CBooster;
import co.uk.randompanda30.sao.items.BoosterItems;
import co.uk.randompanda30.sao.string.Dispatch;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.UUID;

public class CommandBoosterRedeem extends Command {

    public CommandBoosterRedeem() {
        super("booster redeem", "sao.booster.redeem", "Redeems any boosters given to offline players", false);
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

        if (!TEMP.boosterf.contains("tg." + uuid.toString())) {
            Dispatch.sendMessage((String) Messages.MessagesValues.COMMAND_BOOSTER_REDEEM_NONTO.value, player);
            return true;
        }

        ArrayList<String> dumps = (ArrayList<String>) TEMP.boosterf.get("tg." + uuid.toString());

        int i = 0;
        for (ItemStack item : player.getInventory()) {
            if (item == null) {
                i++;
            }
        }

        if (i < dumps.size()) {
            Dispatch.sendMessage((String) Messages.MessagesValues.COMMAND_BOOSTER_REDEEM_NOTENOUGHSPACE.value, player);
        } else {
            // args[0] = time = 2d5h
            // args[1] = percentage;

            for (String s : dumps) {
                String[] ss = s.split(";");
                String time = ss[0];
                int percentage = Integer.parseInt(ss[1]);

                player.getInventory().addItem(BoosterItems.getItems().getBoosterItem(time, percentage));
            }

            TEMP.boosterf.set("tg." + uuid.toString(), null);
            CBooster.save();

            Dispatch.sendMessage((String) Messages.MessagesValues.COMMAND_BOOSTER_REDEEM_DONE.value, player);
        }
        return true;
    }
}