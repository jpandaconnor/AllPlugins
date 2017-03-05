package co.uk.RandomPanda30.SAO.Config;

import co.uk.RandomPanda30.SAO.SAO;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Messages {

    public static File messages;
    public static FileConfiguration messagesf;
    public static ConfigurationSection messagess;

    public Messages() {
        init();
    }

    public void init() {
        try {
            messages = new File("plugins/" + SAO.defaults.getName() + "/messages.yml");
            if (!messages.exists()) {
                messages.getParentFile().mkdirs();
                messages.createNewFile();
            }

            messagesf = new YamlConfiguration();
            messagess = messagesf.getConfigurationSection("");
            messagesf.load(messages);

            for (MessagesValues value : MessagesValues.values()) {
                if (messagess.get(value.name().replaceAll("_", ".")) == null) {
                    messagess.set(value.name().replaceAll("_", "."), value.value);
                    save();
                }

                value.value = messagess
                        .get(value.name().replaceAll("_", "."));
            }

            messagesf.load(messages);
        } catch (IOException | InvalidConfigurationException e) {

        }
    }

    public void save() {
        try {
            messagesf.save(messages);
        } catch (IOException e) {

        }
    }

    public enum MessagesValues {

        ARG("&4"),
        ERROR("&c"),
        HEADER("&6"),
        NORMAL("&f"),
        TEXT("&7"),
        TAG("%A[%NGuilds%A]%N"),
        WARNING("&c"),
        GOOD("&a"),
        BAD("&4"),

        NOCONSOLE("%EYou cannot do this as console"),
        NOPERM("%EYou do not have permission to do this"),

        NOTIFICATIONS_NOMYSQL(
                "%TAG %EMySQL has not been configued. Entering setup in %time seconds..."),
        NOTIFICATIONS_DATABASESETUP(
                "%TAG %NYour database has not been setup yet. Please look in console to setup MySQL"),

        BAN_PLAYERDOESNOTEXIST("%EThis play does not exist in the database. Please try again!"),
        BAN_REASON("%TAG\n%NYou've been banned! Reason: \n%A"),
        BAN_YOUBANNED("%TAG %NYou have banned %A%player%N."),
        BAN_NOREASON("%ANo reason given"),
        BAN_LENGTH("%NBan length:"),
        BAN_DAYS(" %Nday(s)"),
        BAN_HOURS(" %Nhour(s)"),
        BAN_MINUTES(" %Nminutes(s)"),
        BAN_SECONDS(" %Nseconds(s)"),
        BAN_PERM(" %E%lPermanently"),

        KICK_PLAYERNOTONLINE("%A%player %Eplayer is not online"),
        KICK_YOUKICKED("%NYou have kicked %A%player%N."),
        KICK_HAVEKICKED("%EYou have been kicked for \n%reason"),

        ITEMTRANSFER_ALREADYTRANSFERRED("%EYou have already transferred items. If this is an error, please contact an admin"),
        ITEMTRANSFER_GETREADY(new ArrayList<String>() {
            {
                add("%NAn inventory will now open in front of you");
                add("%NStacks are not allowed. One of every item only");
                add("%NAncient Coins are not allowed to be transferred");
                add("%BIf you're...");
                add("%ABeater+ %N - Add 7 items to the inventory");
                add("%ABeater %N - Add 5 items to the inventory");
                add("%APlayer %N - Add 3 items to the inventory");
                add("%NOnce you close this inventory, your items will be transferred");
            }
        }),

        ITEMTRANSFER_SERVERDOESNOTEXIST("%TAG %EThe server %ser %Edoes not exist"),
        ITEMTRANSFER_TRANSFERCREATED("%TAG %NTransfer %A%tra %Nhas been created"),
        ITEMTRANSFER_TRANSFERNAMEEXISTS("%TAG %EAn item transfer module with this name already exists"),
        ITEMTRANSFER_TRANSFERNOTFOUND("%TAG %EAn item transfer module with this name does not  exist"),
        ITEMTRANSFER_TRANSFERDELETED("%TAG %NThis item transfer module has been deleted"),
        ITEMTRANSFER_STRINGFORNUMBER("%TAG %EYou did not enter a number for the item limit"),

        BACKUP_PERCENTAGE("%TAG %NBackup progress: %A%perc%N% %A%done%N/%A%files"),
        BACKUP_STARTED("%TAG %GBackup started!"),
        BACKUP_STOPPED("%TAG %NBackup finished!"),

        ENTRY_XPNOTMET("You cannot join as your level is not %level"),

        LOGIN_PLAYER("&7%player has joined the server"),
        LOGIN_BEATER("%A&l%player &6&lhas joined the server"),
        LOGIN_BEATERPLUS("%A&l%player &5&lhas joined the server"),
        LOGIN_WHITELIST("%BYou cannot join this server as you not whitelisted"),

        WHITELIST_ALREADYIN("%A%pl %Nis already in the whitelist"),
        WHITELIST_NOTIN("%A%pl %Nis not in the whitelist"),
        WHITELIST_ADDED("%A%pl %Nhas been added to the whitelist"),
        WHITELIST_REMOVED("%A%pl %Nhas been removed from the whitelist"),

        MODULE_OFF("%TAG %EModule %A%mod %Ehas been turned off in the config"),

        REWARD_ALREADYEXISTS("%TAG %EThe reward name %A%rwd %Ealready exists"),
        REWARD_NOSPACES("%TAG %EThe reward name cannot contain spaces"),
        REWARD_DOESNTEXIST("%TAG %EThe reward name %A%rwd %Edoes not exist"),
        REWARD_ADDED("%TAG %NCreated new reward name %A%rwd"),
        REWARD_REMOVED("%TAG %NRemoved reward name %A%rwd"),

        FLOORRESTART_TYPEALEVEL("%TAG %NPlese type a level to edit the items for that level"),
        FLOORRESTART_NOTANUMBER("%EPlease try again typing just one number"),
        FLOORRESTART_WELCOME(new ArrayList<String>() {
            {
                add("%H======================================");
                add("%AWelcome %Nto Floor %A%floor");
                add(" ");
                add("%NThis floor has recently been reset.");
                add("%NYour levels may have been reset or your");
                add("%Ninventory may have been cleared");
                add("  ");
                add("%NIf you think this was done in error,");
                add("%Ncontact %ARandomPanda30");
                add("%H====================================== ");
            }
        }),

        SETUP_DATABASE_STEP1("%TAG %NPlease enter the IP of the MySQL server"),
        SETUP_DATABASE_STEP2(
                "%TAG %NThank you! Now please enter the database name"),
        SETUP_DATABASE_STEP3("%TAG %NAwesome, now enter the database user"),
        SETUP_DATABASE_STEP4(
                "%TAG %NSweet, almost there! Enter the database password %A(This will be encrypted!)"),
        SETUP_DATABASE_STEP5(
                "%TAG %NPhew complicated stuff. Now enter the port of the MySQL database"),
        SETUP_DATABASE_STEP6(
                "%TAG %NNice, now did you enter everything correctly? Type Y if you did or N if you didn't"),

        SETUP_DATABASE_REETNER_PORT(
                "%TAG %NYou spoon! You entered letters instead of numbers. Please try again"),
        SETUP_DATABASE_BACKTOSTART(
                "%TAG %BSending you back to the start of the setup"),

        SETUP_DATABASE_FINISHED(
                "%TAG %G&lMySQL has been configured. Reloading server"),

        START(new ArrayList<String>() {
            private static final long serialVersionUID = 6845029486259327038L;

            {
                add("%TAG %NLoading...");
                add("%TAG %NMade by %ARandomPanda30");
                add("%TAG %NVersion: %A%no");
            }
        }),

        STOP(new ArrayList<String>() {
            private static final long serialVersionUID = -5539926430413405204L;

            {
                add("%TAG %NStopping...");
                add("%TAG %NMade by %ARandomPanda30");
                add("%TAG %NVersion: %A%no");
            }
        }),

        SETUP_DATABASE_DETAILS(new ArrayList<String>() {
            private static final long serialVersionUID = 8788996163191664318L;

            {
                add("%H=========************************************************************=========");
                add("%AGuilds %Ndatabase setup");
                add(" ");
                add("%NYou will now be asked to type numerous details into the console in");
                add("%Norder to setup MySQL");
                add("  ");
                add("%NIf you make a mistake, carry on until the end of the setup where");
                add("%Nyou can choose to re-do the setup");
                add("   ");
                add("%NAll passwords are encrypted using specially designed methods");
                add("    ");
                add("%NThings will error! Do not worry about any errors that have occured");
                add("%Nalready with MySQL. They will be fixed once MySQL has been configured");
                add("     ");
                add("%NThe plugin will stop logging any data you type");
                add("      ");
                add("%NSetup will now commence under this message");
                add("%H=====************************************************************=====");
                add("       ");
            }
        }),

        EXCEPTIONLAYOUT_CONSOLE(new ArrayList<String>() {
            {
                add("%H=========************************************************************=========");
                add("%A" + SAO.defaults.getName() + " %Nerror report");
                add("  ");
                add("%EAn error occurred. See below for possible solutions and more details");
                add("   ");
                add("%EException type: %A%ex");
                add("    ");
                add("%EReason: %A%reason");
                add("     ");
                add("%etx");
                add("      ");
                add("%GSolutions: ");
                add("       ");
                add("%solutions");
                add("         ");
                add("%NIf no solutions work, contact: " + SAO.defaults.getAuthor(0));
                add("          ");
                add("%trace");
                add("%H=====************************************************************=====");
            }
        });

        public Object value;

        MessagesValues(Object value) {
            this.value = value;
        }
    }
}