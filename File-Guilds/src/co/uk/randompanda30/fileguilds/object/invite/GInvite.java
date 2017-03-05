package co.uk.randompanda30.fileguilds.object.invite;

import co.uk.randompanda30.fileguilds.Guilds;
import co.uk.randompanda30.fileguilds.TEMP;
import co.uk.randompanda30.fileguilds.config.Messages;
import co.uk.randompanda30.fileguilds.object.GuildOB;
import co.uk.randompanda30.fileguilds.string.Dispatch;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.UUID;

public class GInvite implements Listener {

    Guilds plugin;
    int task;
    private String guild;
    private String guild2;

    private GuildOB sender;
    private GuildOB target;

    public GInvite(String guild, String guild2, Guilds plugin) {
        this.guild = guild;
        this.guild2 = guild2;
        this.plugin = plugin;

        this.sender = new GuildOB(guild);
        this.target = new GuildOB(guild2);

        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        start();
    }

    public void start() {
        TEMP.ginvites.put(guild2, this);

        for (String s : new ArrayList<>(sender.getMembers())) {
            if (Bukkit.getPlayer(UUID.fromString(s)) != null && Bukkit.getPlayer(UUID.fromString(s)).isOnline()) {
                Dispatch.sendMessage(Messages.MessagesValues.GUILD_ALLY_INVITEDGUILD.value.toString()
                        .replace("%guild", guild2), Bukkit.getPlayer(UUID.fromString(s)));
            }
        }

        for (String s : new ArrayList<>(target.getMembers())) {
            if (Bukkit.getPlayer(UUID.fromString(s)) != null && Bukkit.getPlayer(UUID.fromString(s)).isOnline()) {
                Dispatch.sendMessage(Messages.MessagesValues.GUILD_ALLY_INVITED.value.toString()
                        .replace("%guild", guild), Bukkit.getPlayer(UUID.fromString(s)));
            }
        }

        task = Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                if (TEMP.ginvites.containsKey(guild2)) {
                    TEMP.ginvites.remove(guild2);
                }

                for (String s : new ArrayList<>(sender.getMembers())) {
                    if (Bukkit.getPlayer(UUID.fromString(s)) != null && Bukkit.getPlayer(UUID.fromString(s)).isOnline()) {
                        Dispatch.sendMessage((String) Messages.MessagesValues.GUILD_ALLY_TIMEDOUT.value,
                                Bukkit.getPlayer(UUID.fromString(s)));
                    }
                }

                for (String s : new ArrayList<>(target.getMembers())) {
                    if (Bukkit.getPlayer(UUID.fromString(s)) != null && Bukkit.getPlayer(UUID.fromString(s)).isOnline()) {
                        Dispatch.sendMessage((String) Messages.MessagesValues.GUILD_ALLY_TIMEDOUT.value,
                                Bukkit.getPlayer(UUID.fromString(s)));
                    }
                }
            }
        }, 60 * 2 * 20);
    }

    public void stop() {
        Bukkit.getScheduler().cancelTask(task);
        if (TEMP.ginvites.containsValue(this)) {
            TEMP.ginvites.remove(this);
        }
    }

    public String getGuild() {
        return guild;
    }

    public GuildOB getSender() {
        return sender;
    }

    public GuildOB getTarget() {
        return target;
    }
}