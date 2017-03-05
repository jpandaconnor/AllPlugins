package co.uk.randompanda30.fileguilds.object.invite;

import co.uk.randompanda30.fileguilds.Guilds;
import co.uk.randompanda30.fileguilds.TEMP;
import co.uk.randompanda30.fileguilds.config.Messages;
import co.uk.randompanda30.fileguilds.string.Dispatch;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Invite implements Listener {

    Guilds plugin;
    int task;
    private String guild;
    private Player sender;
    private Player target;

    public Invite(String guild, Player sender, Player target, Guilds plugin) {
        this.guild = guild;
        this.sender = sender;
        this.target = target;
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        start();
    }

    public void start() {
        TEMP.invites.put(target.getUniqueId(), this);
        Dispatch.sendMessage(Messages.MessagesValues.GUILD_INVITE_INVITED.value.toString()
                .replace("%guild", guild), target);
        Dispatch.sendMessage(Messages.MessagesValues.GUILD_INVITE_INVITEDPLAYER.value.toString()
                .replace("%player", target.getName()), sender);
        task = Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                if (TEMP.invites.containsKey(target.getUniqueId())) {
                    TEMP.invites.remove(target.getUniqueId());
                }

                Dispatch.sendMessage((String) Messages.MessagesValues.GUILD_INVITE_TIMEDOUT.value, sender);
                Dispatch.sendMessage((String) Messages.MessagesValues.GUILD_INVITE_TIMEDOUT.value, target);
            }
        }, 60 * 2 * 20);
    }

    public void stop() {
        Bukkit.getScheduler().cancelTask(task);
        if (TEMP.invites.containsValue(this)) {
            TEMP.invites.remove(this);
        }
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if (player.equals(sender)) {
            Bukkit.getScheduler().cancelTask(task);
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILD_INVITE_SENDERLEFT.value, target);
        }

        if (player.equals(target)) {
            Bukkit.getScheduler().cancelTask(task);
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILD_INVITE_TARGETLEFT.value, sender);
        }
    }

    public String getGuild() {
        return guild;
    }
}