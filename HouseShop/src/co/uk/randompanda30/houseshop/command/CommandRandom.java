package co.uk.randompanda30.houseshop.command;

import co.uk.randompanda30.houseshop.HouseState;
import co.uk.randompanda30.houseshop.TEMP;
import co.uk.randompanda30.houseshop.command.handle.Command;
import co.uk.randompanda30.houseshop.config.Messages;
import co.uk.randompanda30.houseshop.query.SelectionQ;
import co.uk.randompanda30.houseshop.string.Dispatch;
import co.uk.randompanda30.houseshop.string.LocationS;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by panda on 03/07/16.
 */
public class CommandRandom extends Command {

    public CommandRandom() {
        super("houseshop random", "houseshop.random", "Sends you to a random avaliable house", false);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        if (args.length < 0) {
            Dispatch.sendMessage(Messages.MessagesValues.HS_SYNTAX.value.toString().
                    replaceAll("%syntax", getUsage()), player);
            return true;
        }

        ArrayList<String> houses = TEMP.datac.getConfigurationSection("").getKeys(false).stream().filter(s ->
                SelectionQ.getHouseState(s) == HouseState.FORSALE).collect(Collectors.toCollection(ArrayList::new));

        if(!houses.isEmpty()) {
            Random rand = new Random();
            int index = rand.nextInt(houses.size());

            String house = houses.get(index);

            player.teleport(LocationS.compileLocation((String) TEMP.datac.get(house + ".spawn")));
            Dispatch.sendMessage((String) Messages.MessagesValues.TP_TELEPORTED.value, player);
        } else {
            Dispatch.sendMessage((String) Messages.MessagesValues.RANDOM_NOTHOUSES.value, player);
        }

        return true;
    }
}