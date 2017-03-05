package co.uk.randompanda30.houseshop.command;

import co.uk.randompanda30.houseshop.HouseShop;
import co.uk.randompanda30.houseshop.command.handle.Command;
import co.uk.randompanda30.houseshop.string.Dispatch;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by panda on 02/05/16.
 */
public class CommandHelp extends Command {

    public CommandHelp() {
        super("houseshop help [page]", "", "Use /houseshop help [page] for help", false);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        List<String> lines = new ArrayList<>();
        lines.add(getHelp());

        for (Command command : HouseShop.getCommandHandler().getCommands()) {
            if (command instanceof CommandHelp) {
                continue;
            }

            if (player.hasPermission(command.getPermission())) {
                lines.add("&f/%A" + command.getUsage() + ": %N" + command.getHelp());
            }
        }

        int pageNumber;
        int maxPages = (lines.size() / 6) + 1;

        if (args.length < 2) {
            pageNumber = 1;
        } else {
            try {
                pageNumber = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                Dispatch.sendMessage("%TAG &c'" + args[1] + "' is not a valid number", player);
                return true;
            }
        }

        if (pageNumber > maxPages) {
            pageNumber = maxPages;
        }

        Dispatch.sendMessage("%H---------- %AHouseShop: %NIndex (%A" + pageNumber + "/" + maxPages + "%N) %H----------",
                player);
        int linesLeft = 6;

        for (int index = (pageNumber - 1) * 6; index < (pageNumber * 6); index++) {
            if ((linesLeft <= 0) || (index >= lines.size())) {
                break;
            }

            String helpLine = lines.get(index);

            Dispatch.sendMessage(helpLine, player);
            linesLeft--;
        }

        for (int i = 0; i < linesLeft; i++) {
            Dispatch.sendMessage(" ", player);
        }
        return true;
    }
}