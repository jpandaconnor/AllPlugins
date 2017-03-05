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
import co.uk.randompanda30.sao.modules.enhancement.Booster;
import co.uk.randompanda30.sao.modules.enhancement.BoosterHandler;
import co.uk.randompanda30.sao.pluginobject.Sender;
import org.bukkit.command.CommandSender;

import java.util.Calendar;
import java.util.List;

public class CommandBoosterInfo extends Command {

    public CommandBoosterInfo() {
        super("booster info", "sao.booster.info", "Displays information about an on-going booster", true);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Sender s = new Sender(sender);

        // args[0] = info

        if (args.length != 1) {
            s.send(Messages.MessagesValues.SYNTAX.value.toString().
                    replaceAll("%syntax", getUsage()));
            return true;
        }

        if (!BoosterHandler.isBoosterRunning()) {
            s.send((String) Messages.MessagesValues.COMMAND_BOOSTER_INFO_NOBOOSTERUNNING.value);
            return true;
        }

        List<String> messages = (List<String>) Messages.MessagesValues.COMMAND_BOOSTER_INFO_INFORMATION.value;
        Booster booster = BoosterHandler.getRunningBooster();

        String finalTime = "";

        long a = Calendar.getInstance().getTimeInMillis() - booster.getStartTime();
        long b = booster.getDuration() - a;

        long days = 0;
        long hours = 0;
        long minutes = 0;
        long seconds = 0;

        days = b / 1000 / 60 / 60 / 24;
        hours = (b / 1000 / 60 / 60) - (days * 24);
        minutes = (b / 1000 / 60) - (hours * 60) - (days * 24 * 60);
        seconds = (b / 1000) - (minutes * 60) - (hours * 60 * 60)
                - (days * 24 * 60 * 60);

        if (days != 0) {
            finalTime += "%A" + days + "%Nd";
        }

        if (hours != 0) {
            finalTime += " %A" + hours + "%Nh";
        }

        if (minutes != 0) {
            finalTime += " %A" + minutes + "%Nm";
        }

        if (seconds != 0) {
            finalTime += " %A" + seconds + "%Ns";
        }

        for (String m : messages) {
            String newS = m;
            newS = newS.replaceAll("%player", BoosterHandler.getRunningBooster().getName());
            newS = newS.replaceAll("%perc", Integer.toString(BoosterHandler.getRunningBooster().getPercentage()));
            newS = newS.replaceAll("%time", finalTime);

            // Working out time left here

            // Get current time - start time
            // Remove that value from end time
            // Do fancy shit

            s.send(newS);
            s.send(" ");
        }
        return true;
    }
}