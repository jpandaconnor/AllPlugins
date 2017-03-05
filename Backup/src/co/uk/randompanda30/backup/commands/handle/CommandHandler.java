package co.uk.randompanda30.backup.commands.handle;

import co.uk.randompanda30.backup.Backup;
import co.uk.randompanda30.backup.config.Messages;
import co.uk.randompanda30.backup.string.Dispatch;
import co.uk.randompanda30.backup.string.Format;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by panda on 20/04/16.
 */
public class CommandHandler {

    Backup plugin;

    public CommandHandler(Backup plugin) {
        this.plugin = plugin;
    }

    private Command getCommandMatch(org.bukkit.command.Command command, String[] args) {
        if (command.getName().equals("backup")) {
            if (args.length == 0) return null;

            if (args.length >= 1) {

            }
        }
        return null;
    }

    public List<Command> getCommands() {
        List<Command> commands = new ArrayList<>();
        return commands;
    }

    public boolean handleCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        Command command = getCommandMatch(cmd, args);

        if (command == null) return false;

        if (!command.isConsoleAllowed() && !(sender instanceof Player)) {
            Dispatch.sendMessage((String) Messages.MessagesValues.BACKUP_NOTFORCONSOLE.value, null);
            return true;
        }

        if (!command.getPermission().equals("") && !sender.hasPermission(command.getPermission())) {
            sender.sendMessage(Format.format(Messages.MessagesValues.BACKUP_NOPERM.value.toString().
                    replace("%perm", command.getPermission())));
            return true;
        }

        command.runCommand(sender, args);
        return true;
    }
}