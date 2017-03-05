package co.uk.randompanda30.PLUGIN.object;

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

import co.uk.randompanda30.PLUGIN.string.Dispatch;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Sender {

    public boolean isPlayer = false;
    private Player player = null;

    public Sender(CommandSender sender) {
        if (sender instanceof Player) {
            player = (Player) sender;
            isPlayer = true;
        }
    }

    public void send(String message, boolean tag) {
        if (player == null) {
            Dispatch.sendMessage(message, tag, null);
        } else {
            Dispatch.sendMessage(message, tag, player);
        }
    }

    public Player getPlayer() {
        return player != null ? player : null;
    }
}