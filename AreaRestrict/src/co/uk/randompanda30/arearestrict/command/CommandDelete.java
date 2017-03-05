package co.uk.randompanda30.arearestrict.command;

/* 
   Created by panda on 13/07/16.
   
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

import co.uk.randompanda30.arearestrict.TEMP;
import co.uk.randompanda30.arearestrict.command.handle.Command;
import co.uk.randompanda30.arearestrict.config.Data;
import co.uk.randompanda30.arearestrict.config.Messages;
import co.uk.randompanda30.arearestrict.string.Dispatch;
import co.uk.randompanda30.arearestrict.util.VectorUtil;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandDelete extends Command {

    public CommandDelete() {
        super("ar delete", "ar.delete", "Deletes a region", false);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        if (args.length < 0) {
            Dispatch.sendMessage(Messages.MessagesValues.PLUGIN_SYNTAX.value.toString().
                    replaceAll("%syntax", getUsage()), true, player);
            return true;
        }

        boolean inr = false;

        if(TEMP.pdatac.getConfigurationSection("").getKeys(false) != null) {
            for (String regions : TEMP.pdatac.getConfigurationSection("").getKeys(false)) {
                Location min = (Location) TEMP.pdatac.get(regions + ".min");
                Location max = (Location) TEMP.pdatac.get(regions + ".max");

                VectorUtil vec = new VectorUtil(min.toVector(), max.toVector());

                if (vec.contains(player.getLocation())) {
                    inr = true;
                    break;
                }
            }
        } else {
            Dispatch.sendMessage((String) Messages.MessagesValues.DELETE_NOREGIONS.value, false, player);
            return true;
        }

        if(!inr) {
            Dispatch.sendMessage((String) Messages.MessagesValues.DELETE_NOTINREGION.value, false, player);
            return true;
        }

        for (String regions : TEMP.pdatac.getConfigurationSection("").getKeys(false)) {
            Location min = (Location) TEMP.pdatac.get(regions + ".min");
            Location max = (Location) TEMP.pdatac.get(regions + ".max");

            VectorUtil vec = new VectorUtil(min.toVector(), max.toVector());

            if (vec.contains(player.getLocation())) {
                TEMP.pdatac.set(regions, null);
                Data.save();

                Dispatch.sendMessage((String) Messages.MessagesValues.DELETE_REGIONDELETED.value, true, player);

                break;
            }
        }
        return true;
    }
}