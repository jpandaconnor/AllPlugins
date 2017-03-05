package co.uk.randompanda30.fileguilds.config;

import co.uk.randompanda30.fileguilds.Guilds;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

import static co.uk.randompanda30.fileguilds.TEMP.*;

public class Messages {

    public Messages(Guilds plugin) {
        try {
            messages = new File("plugins/" + plugin.getName() + "/messages.yml");
            if (!messages.exists()) {
                messages.getParentFile().mkdirs();
                messages.createNewFile();
            }

            messagesc = new YamlConfiguration();
            messagescs = messagesc.getConfigurationSection("");
            messagesc.load(messages);

            for (MessagesValues value : MessagesValues.values()) {
                if (messagesc.get(value.name().replaceAll("_", ".")) == null) {
                    messagesc.set(value.name().replaceAll("_", "."), value.value);
                    save();
                }

                value.value = messagesc.get(value.name().replaceAll("_", "."));
            }

            messagesc.load(messages);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    private void save() {
        try {
            messagesc.save(messages);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public enum MessagesValues {
        ARG("&4"),
        ERROR("&c"),
        HEADER("&6"),
        NORMAL("&f"),
        TEXT("&7"),
        TAG("%A[%NCityRP%A]%N"),
        WARNING("&4"),
        GOOD("&a"),
        BAD("&c"),

        GUILDS_ALREADYINGUILD("%TAG %EYou are already in a guild"),
        GUILDS_NOTINGUILD("%TAG %EYou cannot do this as you are not in a guild"),
        GUILDS_NORANKPERM("%TAG %EYour guild rank does not have permission to do this"),
        GUILDS_TARGETNOTINGUILD("%TAG %EThis player is not in a guild"),
        GUILD_NOTONLINE("%TAG %EThis player is not online"),
        GUILDS_TARGETINGUILD("%TAG %EThis player is already in a guild"),
        GUILDS_TARGETNOTFOUND("%TAG %EThis player cannot be found. Please check the name you have entered and try again"),
        GUILDS_NOTFORCONSOLE("%TAG %EYou cannot do this command in console"),
        GUILDS_NOPERM("%TAG %EYou do not have permission to do this. Permission needed: %A%perm"),
        GUILDS_SYNTAX("%TAG %EIncorrect syntax. Please try: %A/%syntax"),

        GUILDS_CREATE_CREATED("%TAG %GYour guild %A%guild %Ghas been created"),
        GUILDS_CREATE_TAGNOT4("%TAG %BYour guild tag must contain 4 letters in total"),
        GUILDS_CREATE_NAMEEXISTS("%TAG %BA guild with this name already exists"),
        GUILDS_CREATE_TAGEXISTS("%TAG %BA guild with this tag already exists"),
        GUILDS_CREATE_NODOTS("%TAG %BNo, your guild cannot have periods/dots in the name"),

        GUILD_KICK_KICKEDPLAYER("%TAG %GYou have kicked %A%player %Gfrom the guild"),
        GUILD_KICK_CANNOTKICKLEADER("%TAG %BYou cannot kick the leader of the guild"),
        GUILD_KICK_KICKED("%TAG %BYou have been kicked from your guild"),

        GUILD_INVITE_INVITEDPLAYER("%TAG %GYou have invited %A%player %Gto your guild"),
        GUILD_INVITE_INVITED("%TAG %NYou have been invited to join %A%guild. %NDo %A/guild accept/deny %Nto either " +
                "accept or deny the request"),
        GUILD_INVITE_TIMEDOUT("%TAG %BYour invite has timed out"),
        GUILD_INVITE_PALREADYINGUILD("%TAG %BThis player is already in the same guild as you"),
        GUILD_INVITE_TARGETLEFT("%TAG %BInvited expired as the target has left"),
        GUILD_INVITE_ALREADYINVITED("%TAG %BThis player already has an invite pending"),
        GUILD_INVITE_SENDERLEFT("%TAG %BInvited expired as the invite sender has left"),

        GUILD_ACCEPT_NOINVITEPENDING("%TAG %BYou do not have any invites pending"),
        GUILD_ACCEPT_YOUHAVEJOINED("%TAG %NWelcome to &A%guild"),
        GUILD_ACCEPT_HASJOINED("%TAG %A%player %Nhas joined the guild!"),

        GUILD_DENY_NOINVITEPENDING("%TAG %BYou do not have any invites pending"),
        GUILD_DENY_YOUHAVEDENIED("%TAG %NYou have denied the invite"),
        GUILD_DENY_HASNOTJOINED("%TAG %A%player %Nhas denied to join the guild!"),

        GUILD_ALLY_INVITEDGUILD("%TAG %GYOu have invited %A%guild %Gto ally with your guild"),
        GUILD_ALLY_INVITED("%TAG %NYou have been invited to ally with %A%guild. %NDo %A/guild gaccept/gdeny %Nto either " +
                "accept or deny the request"),
        GUILD_ALLY_TIMEDOUT("%TAG %BYour ally invite has timed out"),

        GUILD_GACCEPT_NOINVITEPENDING("%TAG %BYou do not have any invites pending"),
        GUILD_GACCEPT_JOINED("%TAG You have allied with %A%guild"),

        GUILD_GDENY_YOUHAVEDENIED("%TAG %NYour guild has denied the ally invite"),
        GUILD_GDENY_NOINVITEPENDING("%TAG %BYou do not have any invites pending"),
        GUILD_GDENY_HASNOTJOINED("%TAG %A%guild %Nhas denied to join the guild!"),

        GUILD_GINVITE_NOTAGUILD("%TAG %EThis guild does not exist"),

        GUILD_UNALLY_NOTALLIED("%TAG %EThis guild is not one of your allies"),
        GUILD_UNALLY_UNALLIED("%TAG %GYour guild has removed %A%guild %Gfrom your allies"),
        GUILD_UNALLY_THEYUNALLIED("%TAG %A%guild %Bhas removed you from their allies"),

        GUILD_RANK_CREATE_CREATED("%TAG %A%rank %Gcreated!"),

        GUILD_RANK_DELETE_NOTEXISTS("%TAG %EThis rank does not exist"),
        GUILD_RANK_DELETE_DELETED("%TAG %GRank %A%rank %Gdeleted"),
        GUILD_RANK_DELETE_CANNOTTARGET("%TAG %EYou cannot edit the Leader or Rookie rank"),
        GUILD_RANK_DELETE_DELETEDINFO("%TAG %NGuild members who had this rank have been set back to Rookie"),

        GUILD_RANK_SET_NOTARANK("%TAG %EThis rank does not exist"),
        GUILD_RANK_SET_NOTINGUILD("%TAG %EThis player is not in a guild, or they are not in your guild"),
        GUILD_RANK_SET_SETTED("%TAG %GYou have set this players rank to %A%rank"),
        GUILD_RANK_SET_YOUHAVE("%TAG %GYour rank has been changed to %A%rank"),

        GUILD_RANK_TITLE_CHANGED("%TAG %GYou have change the title of this rank to %A%title"),

        GUILD_RANK_LIST_STARTED("%H===== %ARanks%H ====="),
        GUILD_RANK_LIST_ENDED("%H===== %AFinished%H ====="),

        GUILD_WAR_DECLARE("%TAG &c&lYOUR GUILD HAS DECLARED WAR ON: %A%guild"),
        GUILD_WAR_DECLARED("%TAG &c&lWAR HAS BEEN DECLARED UPON YOU BY: %A%guild"),

        GUILD_WAR_SURRENDER("%TAG &c&lYOUR GUILD HAS SURRENDERED TO: %A%guild"),
        GUILD_WAR_SURRENDERED("%TAG %A%guild &a&lHAS SURRENDERED"),

        GUILD_PLOT_CREATE_ALREADYHAS("%TAG %EYour guild alraedy has a plot"),
        GUILD_PLOT_CREATE_CREATED("%TAG %GYou have created a new plot"),
        GUILD_PLOT_CREATE_COULDNOTFIND("%TAG %EThere was an error whilst finding you a spot for your guild. " +
                "Please do /guild plot create again to fix this"),

        GUILD_PLOT_DELETE_DELETED("%TAG %GYour guild's plot has been deleted"),
        GUILD_PLOT_DELETE_NOPLOTINFIRSTPLACE("%TAG %BYou never had a plot in the first place!"),

        GUILD_PLOT_HOME_SENT("%TAG %GYou have been sent to your guild plot"),

        GUILD_PVP_TOGGLED("%TAG %GPVP has been toggled %tog"),

        GUILD_MOTD_SET("%TAG %GMessage of the day set! Player's will receive this when they next log in"),

        GUILD_TO_NOTINYOURGUILD("%TAG %EThis player is not in your guild"),

        GUILD_LEAVE_OWNER("%TAG %EYou cannot leave your guild as you are the leader"),
        GUILD_LEAVE_THEYLEFT("%TAG %A%player %Nhas left the guild!"),
        GUILD_LEAVE_YOU("%TAG %GYou have left your guild"),

        GUILD_PLOT_SPAWN_NOTONPLOT("%TAG %EYou cannot set a spawn outside of your plot"),
        GUILD_PLOT_SPAWN_SPAWNSET("%TAG %GYour plot spawn has been set to your location"),

        GUILD_DISBAND_NOTOWNER("%TAG %EYou must be owner to do this"),
        GUILD_DISBAND_DISBANDED("%TAG &c&lYou have quit your guild"),
        GUILD_DISBAND_HAS("%TAG &c&lThe guild leader has left! The guild has fallen!");

        public Object value;

        MessagesValues(Object value) {
            this.value = value;
        }
    }
}