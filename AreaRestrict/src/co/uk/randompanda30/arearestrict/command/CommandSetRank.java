package co.uk.randompanda30.arearestrict.command;

import co.uk.randompanda30.arearestrict.AreaRestrict;
import co.uk.randompanda30.arearestrict.TEMP;
import co.uk.randompanda30.arearestrict.command.handle.Command;
import co.uk.randompanda30.arearestrict.config.Data;
import co.uk.randompanda30.arearestrict.config.Messages;
import co.uk.randompanda30.arearestrict.string.Dispatch;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.util.Collection;

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

public class CommandSetRank extends Command {

    public CommandSetRank() {
        super("ar setrank <Rank name>", "ar.setrank", "Sets the rank for a certain area", false);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        // args[0] = setrank
        // args[1] = rankname

        if (args.length != 2) {
            Dispatch.sendMessage(Messages.MessagesValues.PLUGIN_SYNTAX.value.toString().
                    replaceAll("%syntax", getUsage()), true, player);
            return true;
        }

        if(AreaRestrict.getWorldEdit().getSelection(player) == null) {
            Dispatch.sendMessage((String) Messages.MessagesValues.SETRANK_NOSELECTION.value, true, player);
            return true;
        }

        Collection<String> groups = PermissionsEx.getPermissionManager().getGroupNames();

        if(!groups.contains(args[1])) {
            Dispatch.sendMessage((String) Messages.MessagesValues.SETRANK_GROUPDOESNTEXIST.value, true, player);
            return true;
        }

        Location min = AreaRestrict.getWorldEdit().getSelection(player).getMinimumPoint();
        Location max = AreaRestrict.getWorldEdit().getSelection(player).getMaximumPoint();

        // Name
            // rank
            // min
            // max

        int pos = 0;

        if(TEMP.pdatac.getConfigurationSection("") != null) {
            for(String s : TEMP.pdatac.getConfigurationSection("").getKeys(false)) {
                pos++;
            }
        }

        pos++;

        TEMP.pdatac.set(pos + ".rank", args[1]);
        TEMP.pdatac.set(pos + ".min", min);
        TEMP.pdatac.set(pos + ".max", max);

        Data.save();

        Dispatch.sendMessage((String) Messages.MessagesValues.SETRANK_DONE.value, true, player);
        return true;
    }

}