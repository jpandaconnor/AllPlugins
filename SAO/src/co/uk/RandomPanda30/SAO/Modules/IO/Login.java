package co.uk.RandomPanda30.SAO.Modules.IO;

import co.uk.RandomPanda30.SAO.Config.Config;
import co.uk.RandomPanda30.SAO.Config.Messages;
import co.uk.RandomPanda30.SAO.Manager.DataManager;
import co.uk.RandomPanda30.SAO.SAO;
import co.uk.RandomPanda30.SAO.Utilities.Dispatch;
import co.uk.RandomPanda30.SAO.Utilities.String.Format;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

/*

Kits -

    For First timers
    Daily kits

    Rank kits

    Inventories

 */

public class Login implements Listener, CommandExecutor {

    public SAO plugin;

    public Login(SAO plugin) {
        this.plugin = plugin;

        if ((Boolean) Config.ConfigValues.MODULES_LOGIN_ENABLED.value) {
            plugin.getServer().getPluginManager().registerEvents(this, plugin);
        } else {
            Dispatch.sendMessage(Format.format(Messages.MessagesValues.MODULE_OFF.value.toString().replace("mod", "Login")), null);
        }
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        if ((Boolean) Config.ConfigValues.MODULES_LOGIN_GLOBALXP.value) {
            int level = DataManager.getInt("playerdata", "sao_level", "uuid", uuid.toString());
            int xp = DataManager.getInt("playerdata", "sao_exp", "uuid", uuid.toString());

            if (level > player.getLevel()) {
                player.setLevel(0);
                player.setExp(0);
                player.setLevel(level);
                player.setExp(xp);
            } else {
                SAO.mysql.updateTable("playerdata", "sao_level", "uuid", player.getUniqueId().toString(), player.getLevel());
                SAO.mysql.updateTable("playerdata", "sao_exp", "uuid", player.getUniqueId().toString(), (int) player.getExp());
            }
        }

        if ((Boolean) Config.ConfigValues.MODULES_LOGIN_ENABLED.value) {
            if (player.hasPermission("sao.beaterplus")) {
                if ((Boolean) Config.ConfigValues.MODULES_LOGIN_JOINMESSAGE_BEATERPLUS.value) {
                    event.setJoinMessage(Format.format(Messages.MessagesValues.LOGIN_BEATERPLUS.value.toString().replace("%player", player.getName())));
                }
            } else if (player.hasPermission("sao.beater")) {
                if ((Boolean) Config.ConfigValues.MODULES_LOGIN_JOINMESSAGE_BEATER.value) {
                    event.setJoinMessage(Format.format(Messages.MessagesValues.LOGIN_BEATER.value.toString().replace("%player", player.getName())));
                }
            } else {
                if ((Boolean) Config.ConfigValues.MODULES_LOGIN_JOINMESSAGE_PLAYER.value) {
                    event.setJoinMessage(Format.format(Messages.MessagesValues.LOGIN_PLAYER.value.toString().replace("%player", player.getName())));
                }
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        return true;
    }

}