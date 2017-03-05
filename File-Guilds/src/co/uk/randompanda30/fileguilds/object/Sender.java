package co.uk.randompanda30.fileguilds.object;

import co.uk.randompanda30.fileguilds.string.Dispatch;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Sender {

    public boolean isPlayer = false;
    private Player player = null;

    public Sender(CommandSender sender) {
        if (sender instanceof Player) {
            player = (Player) sender;
            isPlayer = true;
        }
    }

    public void send(String message) {
        if (player == null) {
            Dispatch.sendMessage(message, null);
        } else {
            Dispatch.sendMessage(message, player);
        }
    }

    public Player getPlayer() {
        return player != null ? player : null;
    }
}