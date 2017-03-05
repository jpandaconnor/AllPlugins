package co.uk.randompanda30.arearestrict.string;

import co.uk.randompanda30.arearestrict.config.Messages;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

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

public class Dispatch {

    public static void sendMessage(String message, boolean tag, Player player) {
        if(player != null) {
            player.sendMessage(Format.format((tag ? Messages.MessagesValues.TAG.value + message : message)));
        } else {
            Bukkit.getConsoleSender().sendMessage(Format.format((tag ? Messages.MessagesValues.TAG.value
                    + message : message)));
        }
    }
}