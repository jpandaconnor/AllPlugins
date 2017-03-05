package co.uk.randompanda30.mici.command.handle;

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

import co.uk.randompanda30.mici.Mici;
import co.uk.randompanda30.mici.command.CommandHelp;
import co.uk.randompanda30.mici.config.Messages;
import co.uk.randompanda30.mici.string.Dispatch;
import co.uk.randompanda30.mici.string.Format;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandHandler {

    Mici plugin;

    public CommandHandler(Mici plugin) {
        this.plugin = plugin;
    }

    private Command getCommandMatch(org.bukkit.command.Command command, String[] args) {
        if (command.getName().equals("BASE")) {
            if (args.length == 0)
                return new CommandHelp();

            if (args.length >= 1) {

            }
        }
        return null;
    }

    public List<Command> getCommands() {
        List<Command> commands = new ArrayList<>();
        return commands;
    }

    public boolean handleCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        Command command = getCommandMatch(cmd, args);

        if (command == null) return false;

        if (!command.isConsoleAllowed() && !(sender instanceof Player)) {
            Dispatch.sendMessage((String) Messages.MessagesValues.PLUGIN_NOTFORCONSOLE.value, false, null);
            return true;
        }

        if (!command.getPermission().equals("") && !sender.hasPermission(command.getPermission())) {
            sender.sendMessage(Format.format(Messages.MessagesValues.PLUGIN_NOPERM.value.toString().
                    replace("%perm", command.getPermission())));
            return true;
        }

        command.runCommand(sender, args);
        return true;
    }
}