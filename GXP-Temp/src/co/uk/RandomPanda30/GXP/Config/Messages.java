package co.uk.RandomPanda30.GXP.Config;

import co.uk.RandomPanda30.Guilds.Data;
import co.uk.RandomPanda30.Guilds.Guilds;
import co.uk.RandomPanda30.Guilds.Util.Basics.EUtil;
import co.uk.RandomPanda30.Guilds.Util.Basics.EUtil.Cause;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Messages {

	public enum MessagesValues {

		ARG ("&4"),
		ERROR ("&c"),
		HEADER ("&6"),
		NORMAL ("&f"),
		TEXT ("&7"),
		TAG ("%A[%NGuilds%A]%N"),
		WARNING ("&4"),
		GOOD ("&a"),
		BAD ("&c"),

		NOPERM ("%TAG %EYou don't have permission to do this"),
		NOGUILDPERM ("%TAG %EYour rank does not have the permission: %A%perm"),

		MYSQL_LOADING ("%TAG %NLoading MySQL"),
		MYSQL_BADSETTINGS ("%TAG %EShutting down due to bad MySQL settings"),

		COMMAND_NULL ("%TAG %EThis command does not exist"),
		COMMAND_PLAYERONLY ("%TAG %EThis command can only be done by console"),

		NOTIFICATIONS_NOMYSQL (
				"%TAG %EMySQL has not been configued. Entering setup in %time seconds..."),
		NOTIFICATIONS_DATABASESETUP (
				"%TAG %NYour database has not been setup yet. Please look in console to setup MySQL"),

		SETUP_DATABASE_STEP1 ("%TAG %NPlease enter the IP of the MySQL server"),
		SETUP_DATABASE_STEP2 (
				"%TAG %NThank you! Now please enter the database name"),
		SETUP_DATABASE_STEP3 ("%TAG %NAwesome, now enter the database user"),
		SETUP_DATABASE_STEP4 (
				"%TAG %NSweet, almost there! Enter the database password %A(This will be encrypted!)"),
		SETUP_DATABASE_STEP5 (
				"%TAG %NPhew complicated stuff. Now enter the port of the MySQL database"),
		SETUP_DATABASE_STEP6 (
				"%TAG %NNice, now did you enter everything correctly? Type Y if you did or N if you didn't"),

		SETUP_DATABASE_REETNER_PORT (
				"%TAG %NYou spoon! You entered letters instead of numbers. Please try again"),
		SETUP_DATABASE_BACKTOSTART (
				"%TAG %BSending you back to the start of the setup"),

		SETUP_DATABASE_FINISHED (
				"%TAG %G&lMySQL has been configured. Reloading server"),

		COMMAND_CREATE_SYNTAX (
				"%TAG %EIncorrect usage. %NTry doing /guild create <Guild name> <Guild tag>"),
		COMMAND_CREATE_NOSPACES (
				"%TAG %EYou cannot have spaces in guild names"),
		COMMAND_CREATE_TAGEXISTS (
				"%TAG %EA guild with this tag already exists"),
		COMMAND_CREATE_NAMEEXISTS (
				"%TAG %EA guild with this name already exists"),
		COMMAND_CREATE_DONE ("%TAG %GYou have created your new guild!"),

		COMMAND_INVITE_SYNTAX (
				"%TAG %EIncorrect usage. %NTry doing /guild invite <Player Name>"),
		COMMAND_INVITE_ALREADYINVITED (
				"%TAG %EThis player already has an invite pending"),
		COMMAND_INVITE_INVITED (
				"%TAG %NYou have invited %A%player %Nto your guild"),
		COMMAND_INVITE_YOUINVITED (
				"%TAG %NYou have been invited to join %A%guild%N. Do /guild accept or /guild deny"),
		COMMAND_INVITE_EXPIRED ("%TAG %NYour invite has timed out"),
		COMMAND_INVITE_WHYINVITESELF (
				"%TAG %EWhy you are trying to invite yourself?"),

		COMMAND_DISBANEDED ("%TAG %EYou have disbaneded your guild"),

		COMMAND_GMOTD ("%TAG %NYou have changed your GMOTD for your guild"),

		COMMAND_ACCEPT_NOINVITE ("%TAG %EYou do not have any invites pending"),
		COMMAND_ACCEPT_JOINED_YOU ("%TAG %NYou have joined %A%guild"),

		COMMAND_ACCEPT_JOINED_THEY ("%TAG %A%player %Nhas joined the guild"),

		COMMAND_LEAVE_LEFT ("%TAG %EYou have left this guild"),

		COMMAND_DENY_DENIED_YOU ("%TAG %BYou have denied this invite"),
		COMMAND_DENY_DENIED_THEY (
				"%TAG %A%player %Bhas denied to join the guild"),

		COMMAND_GINVITE_SYTAX (
				"%TAG %EIncorrect usage. %NTry doing /guild ginvite <Guild name>"),
		COMMAND_GINVITE_ALREADYINVITED (
				"%TAG %EThis guild already has an ally invite pending"),
		COMMAND_GINVITE_INVITED (
				"%TAG %A&guild %Nhas been invited to ally with your guild"),
		COMMAND_GINVITE_YOUINVITED (
				"%TAG %NYour guild has been invited to ally with %A%guild%N. Do /guild gaccept or /guild gdeny"),
		COMMAND_GINVITE_EXPIRED ("%TAG %NYour guild invite has timed out"),
		COMMAND_GINVITE_WHYINVITESELF (
				"%TAG %EWhy you are trying to invite your own guild?"),
		COMMAND_GINVITE_GUILDNOTEXIST (
				"%TAG %EA guild with this name does not exist"),
		COMMAND_GINVITE_ATWAR (
				"%TAG %EYour guild is at war with this guild so you cannot ally"),
		COMMAND_GINVITE_ALREADYALLY (
				"%TAG %EYou already have an alliance with this guild"),

		COMMAND_GACCEPT_NOINVITE (
				"%TAG %EYou do not have any guild ally requests"),
		COMMAND_GACCEPT_JOINED_YOU (
				"%TAG %NYour guild has allied with %A%guild"),

		COMMAND_KICK_SYNTAX (
				"%TAG %EIncorrect usage. %NTry doing /guild kick <Player name>"),
		COMMAND_KICK_NOTINGUILD ("%TAG %EThis player is not in your guild"),
		COMMAND_KICK_YOUHAVE (
				"%TAG %NYou have kicked this player from your guild"),
		COMMAND_KICK_KICKED ("%TAG %NYou have been kicked out of the guild"),
		COMMAND_KICK_NOTFOUND ("%TAG %EThis player could not be found"),

		COMMAND_GKICK_SYNTAX (
				"%TAG %EIncorrect usage. %NTry doing /guild gkick <Guild name>"),
		COMMAND_GKICK_YOUHAVE ("%TAG %NYou have unallied this guild"),
		COMMAND_GKICK_KICKED ("%TAG %NYou have been unallied out of the guild"),
		COMMAND_GKICK_NOTFOUND ("%TAG %EThis guild could not be found"),
		COMMAND_GKICK_NOTALLIES ("%TAG %EYou are not even allies"),
		COMMAND_GKICK_ATWAR ("%TAG %EYour guild is at war with this guild"),

		COMMAND_RANK_PERMS_SYNTAX (
				"%TAG %EIncorrect usage. %NTry doing /guild rank perms <Rank name>"),
		COMMAND_RANK_PERMS_NOEDITLEASDER (
				"%TAG %EYou cannot edit the leader's permissions"),
		COMMAND_RANK_PERMS_RANKNULL ("%TAG %EThis rank does not exist"),

		COMMAND_RANK_CREATE_ALREADYEXISTS ("%TAG %EThis rank already exists"),
		COMMAND_RANK_CREATE_SYNTAX (
				"%TAG %EIncorrect usage. %NTry doing /guild rank create <Rank name> <Rank title>"),

		COMMAND_RANK_DELETE_SYNTAX (
				"%TAG %EIncorrect usage. %NTry doing /guild rank delete <Rank name>"),
		COMMAND_RANK_DELETE_RANKNULL ("%TAG %EThis rank does not exist"),

		COMMAND_RANK_SET_SYNTAX (
				"%TAG %EIncorrect usage. %NTry doing /guild rank set <Player name> <Rank name>"),
		COMMAND_RANK_SET_RANKNULL ("%TAG %EThis rank does not exist"),
		COMMAND_RANK_SET_NOTTOLEADER (
				"%TAG %EYou cannot set someone's rank to leader"),
		COMMAND_RANK_SET_PLAYERNOTFOUND ("%TAG %EThis player cannot be found"),
		COMMAND_RANK_SET_PLAYERNOTINGUILD (
				"%TAG %EThis player is not in your guild or does not have a guild"),

		COMMAND_PLOT_CREATE_SYNTAX (
				"%TAG %EIncorrect usage. %NTry doing /guild plot create"),

		COMMAND_RANK_SYNTAX (new ArrayList<String>() {
			private static final long serialVersionUID = -7792251961429798803L;

			{
				add("%TAG %NRank command list");
				add("%NThere's a list of commands you can do to edit ranks");
				add("%NMake sure you use the right command! - Panda");
				add(" ");
				add("%A/guild rank create <Rank name> <Rank title> %N- Create a new rank for your guild with the default Rookie permissions");
				add("%A/guild rank delete <Rank name> %N- Deletes a rank %EWarning: All players who have this rank will be set to Rookie permissions as default");
				add("%A/guild rank perms %N- Shows the GUI that allows you to edit what a rank can and can't do");
				add("%A/guild rank set <Player name> <Rank name> %N- Sets a player's rank");
				add("%A/guild rank list %N- Lists all the ranks in your guild");
			}
		}),

		GENERAL_PLAYERALREADYINGUILD (
				"%TAG %EThis player is already in a guild. Ask them to leave it and then reinvite"),
		GENERAL_TAG ("%TAG %EGuild tags must be 4 letters long"),
		GENERAL_GUILDEXISTS ("%TAG %EThis guild already exists"),
		GENERAL_PLAYERNOTFOUND ("%TAG %EThis player cannot be found"),
		GENERAL_ALREADYINAGUILD (
				"%TAG %EYou cannot do this! You are already in a guild"),
		GENERAL_NOTINGUILD (
				"%TAG %EYou cannot do this as you are not in a guild"),

		START (new ArrayList<String>() {
			private static final long serialVersionUID = 6845029486259327038L;

			{
				add("%TAG %NLoading...");
				add("%TAG %NMade by %ARandomPanda30");
				add("%TAG %NVersion: %A%no");
			}
		}),

		STOP (new ArrayList<String>() {
			private static final long serialVersionUID = -5539926430413405204L;

			{
				add("%TAG %NStopping...");
				add("%TAG %NMade by %ARandomPanda30");
				add("%TAG %NVersion: %A%no");
			}
		}),

		SETUP_DATABASE_DETAILS (new ArrayList<String>() {
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

		COMMANDS_LIST (new ArrayList<String>() {
			private static final long serialVersionUID = -7250317616873575359L;

			{
				add("%TAG %AGuild Commands:");
				add("%TAG Don't put the <> when doing a command...");
				add("%A/guild create <Guild name> <Guild tag> %N- Creates a guild");
				add("%A/guild invite <Player name> %N- Invites a player to your guild");
				add("%A/guild kick <Player name> %N - Kicks a player from your guild");
				add("%A/guild ginvite <Player name> %N- Invites a guild to ally");
				add("%A/guild gkick <Guild name> %N - Kicks a guild from your ally list");
				add("%A/guild war <Guild name> %N - Declare war on a guild");
				add("%A/guild surrender %N - Surrender");
				add("%A/guild leave %N - Allows you to leave your guild (Creators cannot do this)");
				add("%A/guild gmotd <Message of the tag> %N - Sets the message of the day for your guild");
				add("%A/guild disband %N - Disband your guild (Creators only)");
				add("%A/guild pvp %N - Toggles PVP between memebrs of your guild");
				add("%TAG %AGuild plot Commands:");
				add("%A/guild plot create %N - Create a guild plot");
				add("%A/guild plot delete %N - Deletes your guild plot");
				add("%A/guild plot home %N - Sends you back to your plot");
				add("%A/guild plot reset %N - Resets your guild plot");
				add("%A/guild plot kick <Player name> %N - Kicks players off your plots");
				add("%A/guild plot tp %N - Toggles whether non-guild memebers can teleport to your plot");
			}
		}),

		EXCEPTIONLAYOUT_CONSOLE (new ArrayList<String>() {
			private static final long serialVersionUID = 6845029486259327038L;

			{
				add("%H=========************************************************************=========");
				add("%ACityRP %Nerror report");
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

		MessagesValues (Object value) {
			this.value = value;
		}

	}

	public Guilds plugin;

	public Messages (Guilds plugin) {
		this.plugin = plugin;
		init();
	}

	public void init() {
		try {
			Data.messages = new File(
					"plugins/" + plugin.getName() + "/messages.yml");
			if (!Data.messages.exists()) {
				Data.messages.getParentFile().mkdirs();
				Data.messages.createNewFile();
			}

			Data.messagesf = new YamlConfiguration();
			Data.messagess = Data.messagesf.getConfigurationSection("");
			Data.messagesf.load(Data.messages);

			for (MessagesValues value : MessagesValues.values()) {
				if (Data.messagess
						.get(value.name().replaceAll("_", ".")) == null) {
					Data.messagess.set(value.name().replaceAll("_", "."),
							value.value);
					save();
				}

				value.value = Data.messagess
						.get(value.name().replaceAll("_", "."));
			}
			Data.messagesf.load(Data.messages);
		} catch (IOException | InvalidConfigurationException e) {
			new EUtil(e.getStackTrace(), Cause.FILEWENTWRONG,
					"%EFailed to init the messages file");
		}
	}

	public void save() {
		try {
			Data.messagesf.save(Data.messages);
		} catch (IOException e) {
			new EUtil(e.getStackTrace(), Cause.FILEWENTWRONG,
					"%EFailed to save the messages file");
		}
	}
}