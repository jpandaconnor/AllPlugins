package co.uk.randompanda30.houseshop.command.friend;

import co.uk.randompanda30.houseshop.HouseShop;
import co.uk.randompanda30.houseshop.command.handle.Command;
import co.uk.randompanda30.houseshop.config.Messages;
import co.uk.randompanda30.houseshop.query.PlayerQ;
import co.uk.randompanda30.houseshop.string.Dispatch;
import com.sk89q.worldguard.domains.DefaultDomain;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.managers.storage.StorageException;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by panda on 10/05/16.
 */
public class CommandFriendDelete extends Command {

    public CommandFriendDelete() {
        super("houseshop friend delete <Player name>", "houseshop.friend.delete", "Removes a player from your friends list", false);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        // args[0] = friend
        // args[1] = delete
        // args[2] = playername

        if (args.length != 3) {
            Dispatch.sendMessage(Messages.MessagesValues.HS_SYNTAX.value.toString().
                    replaceAll("%syntax", getUsage()), player);
            return true;
        }

        if (!PlayerQ.hasHouse(player)) {
            Dispatch.sendMessage((String) Messages.MessagesValues.HS_DONTHAVEAHOUSE.value, player);
            return true;
        }

        if (Bukkit.getServer().getPlayer(args[2]) == null) {
            Dispatch.sendMessage((String) Messages.MessagesValues.HS_PLAYERNOTFOUND.value, player);
            return true;
        }

        Player target = Bukkit.getPlayer(args[2]);

        if (player.equals(target)) {
            Dispatch.sendMessage((String) Messages.MessagesValues.FRIEND_DELETE_CANTDELETESELF.value, player);
            return true;
        }

        String house = PlayerQ.getHouse(player);
        World world = Bukkit.getWorld("AincradFloor1");

        RegionManager manager = HouseShop.getWorldGuard().getRegionManager(world);
        ProtectedRegion region = manager.getRegion(house);

        if(region.getMembers() != null) {
            DefaultDomain members = region.getMembers();
            if(!members.contains(target.getUniqueId())) {
                Dispatch.sendMessage((String) Messages.MessagesValues.FRIEND_DELETE_NOTINLIST.value, player);
                return true;
            } else {
                members.removePlayer(target.getUniqueId());

                region.setMembers(members);

                Dispatch.sendMessage((String) Messages.MessagesValues.FRIEND_DELETE_DELETED.value, player);
                try {
                    manager.save();
                } catch (StorageException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
}