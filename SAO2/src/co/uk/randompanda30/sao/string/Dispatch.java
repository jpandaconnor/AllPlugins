package co.uk.randompanda30.sao.string;

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

import co.uk.randompanda30.sao.pluginobject.Sender;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Dispatch {

    public static void sendMessage(String message, Player player) {
        if (message == null)
            return;

        if (player == null) {
            Bukkit.getConsoleSender().sendMessage(Format.format(message));
        } else {
            player.getPlayer().sendMessage(Format.format(message));
        }
    }

    public static void sendListMessage(ArrayList<String> messages, Player player) {
        if (messages == null)
            return;

        if (player == null) {
            for (String message : messages) {
                Bukkit.getConsoleSender().sendMessage(Format.format(message));
            }
        } else {
            for (String message : messages) {
                player.getPlayer().sendMessage(Format.format(message));
            }
        }
    }

    public static void senderMessage(String message, Sender sender) {
        if (message == null)
            return;

        if (!sender.isPlayer) {
            Bukkit.getConsoleSender().sendMessage(Format.format(message));
        } else {
            sender.getPlayer().getPlayer().sendMessage(Format.format(message));
        }
    }

    public static void broadcastMessage(String message, boolean useBukkit) {
        if (useBukkit) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                sendMessage(message, player);
            }
            // Bukkit.broadcastMessage(Format.format(message));
        } else {
            for (Player player : Bukkit.getOnlinePlayers()) {
                sendMessage(message, player);
            }
        }
    }

    public static class Start {
        public Start() {
            Dispatch.sendMessage("%TAG %GLoading...", null);
        }
    }

    public static class Stop {
        public Stop() {
            Dispatch.sendMessage("%TAG %BStopping", null);
        }
    }
}