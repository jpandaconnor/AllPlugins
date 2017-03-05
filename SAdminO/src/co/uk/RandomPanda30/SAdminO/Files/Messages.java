package co.uk.RandomPanda30.SAdminO.Files;

import co.uk.RandomPanda30.SAdminO.Data;
import co.uk.RandomPanda30.SAdminO.SAdminO;
import co.uk.RandomPanda30.SAdminO.Util.Basic.EUtil;
import co.uk.RandomPanda30.SAdminO.Util.Basic.EUtil.Cause;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Messages {

    public SAdminO plugin;

    public Messages(SAdminO plugin) {
        this.plugin = plugin;
        init();
    }

    public void init() {
        try {
            Data.messagesF = new File("plugins/" + plugin.getName() + "/messages.yml");
            if (!Data.messagesF.exists()) {
                Data.messagesF.getParentFile().mkdirs();
                Data.messagesF.createNewFile();
            }

            Data.messages = new YamlConfiguration();
            Data.messagesC = Data.messages.getConfigurationSection("");
            Data.messages.load(Data.messagesF);

            for (MessagesValues value : MessagesValues.values()) {
                if (Data.messages.get(value.name().replaceAll("_", ".")) == null) {
                    Data.messages.set(value.name().replaceAll("_", "."), value.value);
                    save();
                }

                value.value = Data.messages.get(value.name().replaceAll("_", "."));
            }

            Data.messages.load(Data.messagesF);
        } catch (IOException | InvalidConfigurationException e) {
            new EUtil(e.getStackTrace(), Cause.FILEWENTWRONG, "%EFailed to init the config");
        }
    }

    public void save() {
        try {
            Data.messages.save(Data.messagesF);
        } catch (IOException e) {
            new EUtil(e.getStackTrace(), Cause.FILEWENTWRONG, "%EFailed to save the config");
        }
    }

    public enum MessagesValues {
        ARG("&4"), ERROR("&c"), HEADER("&6"), NORMAL("&f"), TEXT("&7"), TAG("%A[%NSAdminO%A]%N"), WARNING("&4"), GOOD(
                "&a"), BAD("&c"),

        MYSQL_LOADING("%TAG %NLoading MySQL"), MYSQL_BADSETTINGS("%TAG %EShutting down due to bad MySQL settings"),

        COMMAND_NULL("%TAG %EThis command does not exist"), COMMAND_PLAYERONLY(
                "%TAG %EThis command can only be done by console"),

        BAN_YOUBANNED("%EYou have been banned! Reason: \n%A"), BAN_NOREASON("No reason given"), BAN_LENGTH(
                "%NBan length:"), BAN_DAYS("%Nday(s)"), BAN_HOURS("%Nhour(s)"), BAN_MINUTES("%Nminute(s)"), BAN_SECONDS(
                "%Nsecond(s)"), BAN_PERM("%E&lPermanently"), BAN_THEYBANNED(
                "%TAG %A%player %Nhas been banned for %A%time %N. Reason: %A%reason"),

        NOTIFICATIONS_PLUGINLOADING(new ArrayList<String>() {
            private static final long serialVersionUID = -5207622435960348071L;

            {
                add("%TAG %GIs now being enabled");
                add("%TAG %NMade by: %ARandomPanda30");
            }
        }),

        NOTIFICATIONS_PLUGINDISABLING(new ArrayList<String>() {
            private static final long serialVersionUID = -5207622435960348071L;

            {
                add("%TAG %BIs now being disabled");
                add("%TAG %NMade by: %ARandomPanda30");
            }
        }),

        NOTIFICATIONS_NOMYSQL(
                "%TAG %EMySQL has not been configued. Entering setup in %time seconds..."), NOTIFICATIONS_DATABASESETUP(
                "%TAG %NYour database has not been setup yet. Please look in console to setup MySQL"),

        SETUP_DATABASE_STEP1("%TAG %NPlease enter the IP of the MySQL server"), SETUP_DATABASE_STEP2(
                "%TAG %NThank you! Now please enter the database name"), SETUP_DATABASE_STEP3(
                "%TAG %NAwesome, now enter the database user"), SETUP_DATABASE_STEP4(
                "%TAG %NSweet, almost there! Enter the database password %A(This will be encrypted!)"), SETUP_DATABASE_STEP5(
                "%TAG %NPhew complicated stuff. Now enter the port of the MySQL database"), SETUP_DATABASE_STEP6(
                "%TAG %NNice, now did you enter everything correctly? Type Y if you did or N if you didn't"),

        SETUP_DATABASE_REETNER_PORT(
                "%TAG %NYou spoon! You entered letters instead of numbers. Please try again"), SETUP_DATABASE_BACKTOSTART(
                "%TAG %BSending you back to the start of the setup"),

        SETUP_DATABASE_FINISHED("%TAG %G&lMySQL has been configured. Reloading server"),

        SETUP_DATABASE_DETAILS(new ArrayList<String>() {
            private static final long serialVersionUID = -5207622435960348071L;

            {
                add("%H=========************************************************************=========");
                add("%ASAdminO %Ndatabase setup");
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
            private static final long serialVersionUID = 6845029486259327038L;

            {
                add("%H=========************************************************************=========");
                add("%ASAdminO %Nerror report");
                add("  ");
                add("%ESomething went very wrong then. Please check the details below");
                add("%Eand the solutions");
                add("   ");
                add("%EException type: %A%ex");
                add("    ");
                add("%EReason: %A%reason");
                add("     ");
                add("%GSolutions: ");
                add("      ");
                add("%solutions");
                add("       ");
                add("%NIf all else fails, contact RandomPanda30 (Developer)");
                add("%EStack trace (Send this to the developer if you're having issues)");
                add("         ");
                add("%trace");
                add("          ");
                add("%etx");
                add("%H=====************************************************************=====");
            }
        });

        public Object value;

        MessagesValues(Object value) {
            this.value = value;
        }
    }
}