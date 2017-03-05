package co.uk.randompanda30.party.command.nohandle;

import co.uk.randompanda30.party.Party;
import co.uk.randompanda30.party.config.Messages;
import co.uk.randompanda30.party.data.PlayerData;
import co.uk.randompanda30.party.object.PartyOB;
import co.uk.randompanda30.party.object.Sender;
import co.uk.randompanda30.party.string.Dispatch;
import co.uk.randompanda30.party.string.Format;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Created by panda on 23/04/16.
 */
public class CommandPChat implements CommandExecutor {

    Party plugin;

    public CommandPChat(Party plugin) {
        this.plugin = plugin;
        plugin.getCommand("pc").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Sender s = new Sender(sender);

        if (!s.isPlayer) {
            Dispatch.sendMessage((String) Messages.MessagesValues.PARTY_NOTFORCONSOLE.value, null);
            return true;
        }

        Player player = s.getPlayer();
        UUID uuid = player.getUniqueId();

        if(args.length < 0) {
            Dispatch.sendMessage("&cPlease add your message after doing this command", player);
            return true;
        }

        if(!PlayerData.inParty(uuid)) {
            Dispatch.sendMessage((String) Messages.MessagesValues.PARTY_NOTINPARTY.value, player);
            return true;
        }

        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            buffer.append(' ').append(args[i]);
        }

        String message = buffer.toString();
        PartyOB party = PlayerData.getParty(uuid);

        party.getPlayers().stream().filter(uuids -> Bukkit.getPlayer(uuid).isOnline()).forEach(uuids ->
                Dispatch.sendMessage(Format.format("&d&lPChat> &c&l" + player.getName() + "&f:" + message),
                Bukkit.getServer().getPlayer(uuids)));

        return true;
    }
}