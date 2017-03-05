package co.uk.randompanda30.sao.commands.handle;

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

import co.uk.randompanda30.sao.SAO;
import co.uk.randompanda30.sao.commands.CommandSAO;
import co.uk.randompanda30.sao.commands.booster.*;
import co.uk.randompanda30.sao.commands.levelbound.CommandItemRestrictAdd;
import co.uk.randompanda30.sao.commands.levelbound.CommandItemRestrictDelete;
import co.uk.randompanda30.sao.commands.levelbound.CommandItemRestrictEdit;
import co.uk.randompanda30.sao.commands.levelbound.CommandItemRestrictRetrieve;
import co.uk.randompanda30.sao.config.Messages;
import co.uk.randompanda30.sao.string.Dispatch;
import co.uk.randompanda30.sao.string.Format;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandHandler {

    SAO plugin;

    public CommandHandler(SAO plugin) {
        this.plugin = plugin;
    }

    private Command getCommandMatch(org.bukkit.command.Command command, String[] args) {
        if (command.getName().equals("sao")) {
            if (args.length == 0) return new CommandSAO();

            // Something something something
        } else if (command.getName().equals("booster")) {
            if (args.length == 0) return null;

            if (args.length >= 1) {
                if (args[0].equalsIgnoreCase("cancel")) return new CommandBoosterCancel();
                if (args[0].equalsIgnoreCase("info")) return new CommandBoosterInfo();
                if (args[0].equalsIgnoreCase("transfer")) return new CommandBoosterTransfer();
                if (args[0].equalsIgnoreCase("redeem")) return new CommandBoosterRedeem();
                if (args[0].equalsIgnoreCase("give")) return new CommandBoosterGive();
            }
        } else if (command.getName().equals("levelrestrict")) {
            if (args.length == 0) return null;

            if (args.length >= 1) {
                if (args[0].equalsIgnoreCase("add")) return new CommandItemRestrictAdd();
                if (args[0].equalsIgnoreCase("delete")) return new CommandItemRestrictDelete();
                if (args[0].equalsIgnoreCase("edit")) return new CommandItemRestrictEdit();
                if (args[0].equalsIgnoreCase("retrieve")) return new CommandItemRestrictRetrieve();
            }
        }
        return null;
    }

    public List<Command> getBoosterCommands() {
        List<Command> commands = new ArrayList<>();
        commands.add(new CommandBoosterGive());
        commands.add(new CommandBoosterInfo());
        commands.add(new CommandBoosterTransfer());
        commands.add(new CommandBoosterRedeem());
        commands.add(new CommandBoosterCancel());
        return commands;
    }

    public List<Command> getItemRestrictCommands() {
        List<Command> commands = new ArrayList<>();
        commands.add(new CommandItemRestrictAdd());
        commands.add(new CommandItemRestrictDelete());
        commands.add(new CommandItemRestrictRetrieve());
        commands.add(new CommandItemRestrictEdit());
        return commands;
    }

    public boolean handleCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        Command command = getCommandMatch(cmd, args);

        if (command == null) return false;

        if (!command.isConsoleAllowed() && !(sender instanceof Player)) {
            Dispatch.sendMessage((String) Messages.MessagesValues.NOCONSOLE.value, null);
            return true;
        }

        if (!command.getPermission().equals("") && !sender.hasPermission(command.getPermission())) {
            sender.sendMessage(Format.format(Messages.MessagesValues.NOPERMISSION.value.toString().
                    replace("%perm", command.getPermission())));
            return true;
        }
        command.runCommand(sender, args);
        return true;
    }
}