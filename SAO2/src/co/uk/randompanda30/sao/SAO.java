package co.uk.randompanda30.sao;

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

import co.uk.randompanda30.sao.commands.handle.CommandHandler;
import co.uk.randompanda30.sao.config.Config;
import co.uk.randompanda30.sao.config.Messages;
import co.uk.randompanda30.sao.config.modules.CBackup;
import co.uk.randompanda30.sao.config.modules.CBooster;
import co.uk.randompanda30.sao.config.modules.CLevelBound;
import co.uk.randompanda30.sao.config.modules.CPlayerDataReset;
import co.uk.randompanda30.sao.config.modules.housing.CPlayerHousingData;
import co.uk.randompanda30.sao.config.modules.housing.CPlayerHousingDistricts;
import co.uk.randompanda30.sao.events.handle.EventHandler;
import co.uk.randompanda30.sao.modules.enhancement.BoosterHandler;
import co.uk.randompanda30.sao.modules.restrict.LevelStrictChecker;
import co.uk.randompanda30.sao.modules.util.BackupUtil;
import co.uk.randompanda30.sao.modules.util.mici.Questions;
import co.uk.randompanda30.sao.string.Dispatch;
import co.uk.randompanda30.sao.task.AutoShutdownTask;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class SAO extends JavaPlugin {

    static SAO plugin;

    static CommandHandler commandHandler;

    public static SAO getPlugin() {
        return plugin;
    }

    public static CommandHandler getCommandHandler() {
        return commandHandler;
    }

    public static WorldGuardPlugin getWorldGuard() {
        Plugin p = SAO.getPlugin().getServer().getPluginManager().getPlugin("WorldGuard");
        if (p == null || !(p instanceof WorldGuardPlugin)) {
            return null;
        }

        return (WorldGuardPlugin) p;
    }

    @Override
    public void onEnable() {
        plugin = this;

        new Config();
        new Messages();

        new CBooster();
        new CLevelBound();
        new CPlayerDataReset();
        new CBackup();

        new CPlayerHousingData();
        new CPlayerHousingDistricts();

        new EventHandler();

        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        if (BoosterHandler.isDumped()) {
            BoosterHandler.startDumpedBooster();
        }

        commandHandler = new CommandHandler(this);

        new LevelStrictChecker().runTaskTimerAsynchronously(this, 0L, 100L);
        new AutoShutdownTask().runTaskTimerAsynchronously(this, 0L, 100L);

        new BackupUtil();

        Questions.initList();

        new Dispatch.Start();
    }

    @Override
    public void onDisable() {

        if (BoosterHandler.isBoosterRunning()) {
            BoosterHandler.dumpBooster(BoosterHandler.getRunningBooster());
        }

        if(TEMP.isBackingup) {
            // Fix here
        }

        new Dispatch.Stop();

        plugin = null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return commandHandler.handleCommand(sender, command, label, args);
    }
}