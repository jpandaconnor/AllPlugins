package co.uk.RandomPanda30.SAdminO.Util;

import co.uk.RandomPanda30.SAdminO.Util.Basic.MUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Sender {

    public boolean isConsole = true;

    public CommandSender sender;

    private Player player;

    public Sender(CommandSender sender) {
        this.sender = sender;

        if (!(sender instanceof Player)) {
            isConsole = false;
        } else {
            player = (Player) sender;
        }
    }

    public void sendMessage(String message) {
        if (!isConsole) {
            MUtil.sendMessage(message, player);
        } else {
            MUtil.sendMessage(message, null);
        }
    }
}