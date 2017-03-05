package co.uk.RandomPanda30.SAdminO.Commands.List;

import co.uk.RandomPanda30.SAdminO.Anodes.NoConsole;
import co.uk.RandomPanda30.SAdminO.Commands.CommandInterface;
import co.uk.RandomPanda30.SAdminO.SAdminO;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CommandSaoa implements CommandInterface {

    public SAdminO plugin;

    public CommandSaoa(SAdminO plugin) {
        this.plugin = plugin;
    }

    @NoConsole
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // TODO Auto-generated method stub
        return false;
    }
}