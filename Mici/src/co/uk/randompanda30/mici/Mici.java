package co.uk.randompanda30.mici;

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

import co.uk.randompanda30.mici.command.handle.CommandHandler;
import co.uk.randompanda30.mici.config.Config;
import co.uk.randompanda30.mici.config.Messages;
import co.uk.randompanda30.mici.event.OnPlayerAsyncChatEvent;
import co.uk.randompanda30.mici.string.Dispatch;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Mici extends JavaPlugin {

    static Mici plugin;
    static PluginDescriptionFile pdfFile;

    static CommandHandler commandHandler;

    /*
    Basic Overriding of the methods extended from JavaPlugin
     */

    @Override
    public void onEnable() {
        plugin = this;
        pdfFile = this.getDescription();

        commandHandler = new CommandHandler(this);

        start();
    }

    @Override
    public void onDisable() {
        stop();

        plugin = null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return commandHandler.handleCommand(sender, command, label, args);
    }

    /*
    Instead of cluttering up onEnable and onDisable with random things, I'll just stick them in a new
    method and hope for the best
     */

    void start() {
        Dispatch.sendMessage("%GStarting...", true, null);

        new Config(this);
        new Messages(this);

        new OnPlayerAsyncChatEvent();

        Questions.initList();

        setDepends();
    }

    void stop() {
        Dispatch.sendMessage("%BStopping...", true, null);
    }

    void setDepends() {

    }

    /*
    Static methods. Just easier to get a static instance of important stuffs
     */

    public static Mici getPlugin() {
        return plugin;
    }

    public static PluginDescriptionFile getInformation() {
        return pdfFile;
    }
}