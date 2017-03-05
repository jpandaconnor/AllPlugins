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
import co.uk.randompanda30.sao.pluginobject.Sender;
import co.uk.randompanda30.sao.string.Dispatch;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class CommandBoosterGive extends Command {

    public CommandBoosterGive() {
        super("booster give <Player name> <Percentage> <Time>", "sao.booster.give", "Gives a player a booster", true);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Sender s = new Sender(sender);

        // args[0] = give
        // args[1] = player
        // args[2] = percentage
        // args[3] = time

        if (args.length != 4) {
            s.send(Messages.MessagesValues.SYNTAX.value.toString().
                    replaceAll("%syntax", getUsage()));
            return true;
        }

        String time = args[3];

        int percentage = 0;

        try {
            percentage = Integer.parseInt(args[2].replace("%", ""));
        } catch (NumberFormatException e) {
            s.send((String) Messages.MessagesValues.COMMAND_BOOSTER_GIVE_NOTNUMBER.value);
            return true;
        }

        UUID uuid = Bukkit.getOfflinePlayer(args[1]).getUniqueId();

        if (Bukkit.getOfflinePlayer(uuid).isOnline()) {
            Player player = Bukkit.getPlayer(args[1]);

            player.getInventory().addItem(BoosterItems.getItems().getBoosterItem(time, percentage));

            s.send(Messages.MessagesValues.COMMAND_BOOSTER_GIVE_GIVEN.value.toString().replace("%player",
                    player.getName()));
            Dispatch.sendMessage((String) Messages.MessagesValues.COMMAND_BOOSTER_GIVE_YOUGIVE.value, player);
        } else {
            if (TEMP.boosterf.contains("tg." + uuid.toString())) {
                ArrayList<String> soFar = (ArrayList<String>) TEMP.boosterf.get("tg." + uuid.toString());
                soFar.add(time + ";" + Integer.toString(percentage));
                TEMP.boosterf.set("tg." + uuid.toString(), soFar);
                CBooster.save();
            } else {
                ArrayList<String> soFar = new ArrayList<>();
                soFar.add(time + ";" + Integer.toString(percentage));
                TEMP.boosterf.set("tg." + uuid.toString(), soFar);
                CBooster.save();
            }

            s.send(Messages.MessagesValues.COMMAND_BOOSTER_GIVE_GIVEN.value.toString().replace("%player",
                    args[1]));
        }
        return true;
    }
}