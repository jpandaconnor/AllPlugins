package co.uk.randompanda30.publicchat.config;

import co.uk.randompanda30.publicchat.PublicChat;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

import static co.uk.randompanda30.publicchat.TEMP.*;

public class Messages {

    public Messages(PublicChat plugin) {
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
        TAG("%A[%NPublicChat%A]%N"),
        WARNING("&4"),
        GOOD("&a"),
        BAD("&c"),

        PC_NOPERM("%TAG %EYou do not have permission to do this. Permission needed: %A%perm"),
        PC_SYNTAX("%TAG %EIncorrect syntax. Please try: %A/%syntax"),
        PC_NOTFORCONSOLE("%TAG %EYou cannot do this command in console"),

        TEST("TEST");
        public Object value;

        MessagesValues(Object value) {
            this.value = value;
        }
    }
}