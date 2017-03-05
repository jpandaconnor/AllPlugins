package co.uk.randompanda30.arearestrict;

import co.uk.randompanda30.arearestrict.command.handle.CommandHandler;
import co.uk.randompanda30.arearestrict.config.Config;
import co.uk.randompanda30.arearestrict.config.Data;
import co.uk.randompanda30.arearestrict.config.Messages;
import co.uk.randompanda30.arearestrict.string.Dispatch;
import co.uk.randompanda30.arearestrict.task.RegionTask;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

/**
 * Created by panda on 05/07/16.
 *
 *
 *    Copyright 2016 JPanda (Connor Brady)
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

public class AreaRestrict extends JavaPlugin {

    static AreaRestrict plugin;
    static WorldEditPlugin wePlugin;

    static PluginDescriptionFile pdfFile;

    static CommandHandler commandHandler;

    BukkitTask regionTask;

    @Override
    public void onEnable() {
        plugin = this;
        pdfFile = this.getDescription();

        start();
    }

    @Override
    public void onDisable() {
        stop();

        plugin = null;
    }

    void start() {
        Dispatch.sendMessage("&aStarting...", true, null);

        new Config(this);
        new Messages(this);
        new Data(this);

        commandHandler = new CommandHandler(this);
        regionTask = new RegionTask().runTaskTimerAsynchronously(this, 0, 20L);

        setupDepends();
    }

    void stop() {
        Dispatch.sendMessage("&cStopping...", true, null);
    }

    void setupDepends() {
        if(getServer().getPluginManager().getPlugin("WorldEdit") == null) {
                Dispatch.sendMessage("&cWorldEdit plugin has not been found. Stopping the plugin", true, null);
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        wePlugin = (WorldEditPlugin) getServer().getPluginManager().getPlugin("WorldEdit");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return commandHandler.handleCommand(sender, command, label, args);
    }

    /*
    Non-API static methods
     */

    public static AreaRestrict getPlugin() {
        return plugin;
    }

    public static WorldEditPlugin getWorldEdit() {
        return wePlugin;
    }
}