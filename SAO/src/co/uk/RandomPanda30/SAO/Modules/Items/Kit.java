package co.uk.RandomPanda30.SAO.Modules.Items;

import co.uk.RandomPanda30.SAO.SAO;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

public class Kit implements Listener, CommandExecutor {

    public SAO plugin;

    public Kit(SAO plugin) {
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        return true;
    }

}