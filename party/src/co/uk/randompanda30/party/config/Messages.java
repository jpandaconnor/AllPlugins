package co.uk.randompanda30.party.config;

import co.uk.randompanda30.party.Party;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

import static co.uk.randompanda30.party.TEMP.*;

public class Messages {

    public Messages(Party plugin) {
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
        TAG("%A[%N" + Party.getPlugin().getName() + "%A]%N"),
        WARNING("&4"),
        GOOD("&a"),
        BAD("&c"),

        PARTY_NOTFORCONSOLE("%TAG %EYou cannot do this command in console"),
        PARTY_NOPERM("%TAG %EYou do not have permission to do this. Permission needed: %A%perm"),
        PARTY_SYNTAX("%TAG %EIncorrect syntax. Please try: %A/%syntax"),
        PARTY_ALREADYIN("%TAG %EYou are already in a party"),
        PARTY_NOTINPARTY("%TAG %EYou are not in a party"),

        PARTY_CREATE_PARTYCREATED("%TAG %GYou have created a new party"),

        PARTY_LEAVE_HASLEFT("%TAG %A%player %Nhas left the party"),
        PARTY_LEAVE_YOULEFT("%TAG %GYou have left the party"),
        PARTY_LEAVE_DISBANDED("%TAG &c&lYour leader left the party. As a result, the party has been deleted"),

        PARTY_INVITE_INVITED("%TAG %NYou have been invited to party with %A%name%N. Do /party accept to join or " +
                "/party deny to ignore"),
        PARTY_INVITE_INVITEDPLAYER("%TAG %GYou have invited %A%player %Gto your party"),
        PARTY_INVITE_TIMEDOUT("%TAG %BYour invite has timed out"),
        PARTY_INVITE_SENDERLEFT("%TAG %BInvited expired as the invite sender has left"),
        PARTY_INVITE_TARGETLEFT("%TAG %BInvited expired as the target has left"),
        PARTY_INVITE_ALREADYIN("%TAG %EThis player is already in a party"),
        PARTY_INVITE_TOOBIG("%TAG %EYou cannot invite anymore players as your party is full"),

        PARTY_ACCEPT_NOINVITEPENDING("%TAG %EYou do not have any invites pending"),
        PARTY_ACCEPT_JOINED("%TAG %GYou have joined the party"),
        PARTY_ACCEPT_HASJOINED("%TAG %A%player %Nhas joined the party"),

        PARTY_DENY_DENIED("%TAG %NYou have denied the party request"),
        PARTY_DENY_HASJOINED("%TAG %A%player %Nhas not joined the party"),

        PARTY_KICK_NOTLEADER("%TAG %EYou cannot do this as you are not the party leader"),
        PARTY_KICK_KICKED("%TAG %BYou have been kicked from the party"),
        PARTY_KICK_HASKICKED("%TAG %A%player %Ehas been kicked from the party"),
        PARTY_KICK_ITSYOU("%TAG %EWhy are you trying to kick yourself?... Do /leave"),

        PARTY_REGROUP_ALREADYDONE("%TAG %EYou cannot do this as you must wait 15 minute between regroups"),
        PARTY_REGROUP_SUMMONED("%TAG %GYou have been summoned to the party leaders location");

        public Object value;

        MessagesValues(Object value) {
            this.value = value;
        }
    }
}