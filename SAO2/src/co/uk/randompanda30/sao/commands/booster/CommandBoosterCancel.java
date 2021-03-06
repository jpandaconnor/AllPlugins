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
import co.uk.randompanda30.sao.modules.enhancement.BoosterHandler;
import co.uk.randompanda30.sao.pluginobject.Sender;
import org.bukkit.command.CommandSender;

public class CommandBoosterCancel extends Command {

    public CommandBoosterCancel() {
        super("booster cancel", "sao.booster.cancel", "Cancels an on-going booster", true);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Sender s = new Sender(sender);

        // args[0] = cancel

        if (args.length != 1) {
            s.send(Messages.MessagesValues.SYNTAX.value.toString().
                    replaceAll("%syntax", getUsage()));
            return true;
        }

        if (!BoosterHandler.isBoosterRunning()) {
            s.send((String) Messages.MessagesValues.COMMAND_BOOSTER_CANCEL_NOBOOSTERUNNING.value);
            return true;
        }

        BoosterHandler.stopBooster(BoosterHandler.getRunningBooster());
        s.send((String) Messages.MessagesValues.COMMAND_BOOSTER_CANCEL_CANCELLED.value);

        return true;
    }
}