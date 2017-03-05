package co.uk.randompanda30.houseshop;

/* 
   Created by panda on 20/07/16.
   
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

import co.uk.randompanda30.houseshop.command.handle.Command;
import co.uk.randompanda30.houseshop.config.Messages;
import co.uk.randompanda30.houseshop.string.Dispatch;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTpdir extends Command {

    public CommandTpdir() {
        super("houseshop tpdir n/e/s/w", "houseshop.tpdir", "Teleports you to a house location", false);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        // args[0] = tpdir
        // args[1] = n/e/s/w

        if(args.length != 2) {
            Dispatch.sendMessage(Messages.MessagesValues.HS_SYNTAX.value.toString().
                    replaceAll("%syntax", getUsage()), player);
        }

        boolean isco = false;

        for(String s : args) {
            if(s.equalsIgnoreCase("n") ||
                    s.equalsIgnoreCase("e") ||
                    s.equalsIgnoreCase("s") ||
                    s.equalsIgnoreCase("w")) {
                isco = true;
            }
        }

        if(!isco) {
            Dispatch.sendMessage((String) Messages.MessagesValues.TPDIR_MUSTHAVE.value, player);
            return true;
        }

        switch(args[1].toLowerCase()) {
            case "n":
                if(TEMP.configc.contains("nwarp")) {
                    Location location = (Location) TEMP.configc.get("nwarp");
                    player.teleport(location);
                    Dispatch.sendMessage((String) Messages.MessagesValues.TPDIR_TELEPORTED.value, player);
                } else {
                    Dispatch.sendMessage((String) Messages.MessagesValues.TPDIR_NOTFOUND.value, player);
                }
                break;
            case "e":
                if(TEMP.configc.contains("ewarp")) {
                    Location location = (Location) TEMP.configc.get("ewarp");
                    player.teleport(location);
                    Dispatch.sendMessage((String) Messages.MessagesValues.TPDIR_TELEPORTED.value, player);
                } else {
                    Dispatch.sendMessage((String) Messages.MessagesValues.TPDIR_NOTFOUND.value, player);
                }
                break;
            case "s":
                if(TEMP.configc.contains("swarp")) {
                    Location location = (Location) TEMP.configc.get("swarp");
                    player.teleport(location);
                    Dispatch.sendMessage((String) Messages.MessagesValues.TPDIR_TELEPORTED.value, player);
                } else {
                    Dispatch.sendMessage((String) Messages.MessagesValues.TPDIR_NOTFOUND.value, player);
                }
                break;
            case "w":
                if(TEMP.configc.contains("wwarp")) {
                    Location location = (Location) TEMP.configc.get("wwarp");
                    player.teleport(location);
                    Dispatch.sendMessage((String) Messages.MessagesValues.TPDIR_TELEPORTED.value, player);
                } else {
                    Dispatch.sendMessage((String) Messages.MessagesValues.TPDIR_NOTFOUND.value, player);
                }
                break;
        }
        return true;
    }
}