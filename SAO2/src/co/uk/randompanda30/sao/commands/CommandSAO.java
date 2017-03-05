package co.uk.randompanda30.sao.commands;

/* 
   Created by panda on 16/08/16.
   
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
import co.uk.randompanda30.sao.string.Dispatch;
import co.uk.randompanda30.sao.view.V_SAO;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSAO extends Command {

    public CommandSAO() {
        super("sao", "sao.menu", "Shows SAO menu", false);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        Bukkit.broadcastMessage("Cunt");

        if (args.length > 0) {
            Dispatch.sendMessage(Messages.MessagesValues.SYNTAX.value.toString().
                    replaceAll("%syntax", getUsage()), player);
            Bukkit.broadcastMessage("Cunt2");
            return true;
        }

        Bukkit.broadcastMessage("Cunt33");

        V_SAO view = new V_SAO();

        Bukkit.broadcastMessage("Cunt4");
        view.openView(player);
        Bukkit.broadcastMessage("Cunt5");

        player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);

        return true;
    }
}