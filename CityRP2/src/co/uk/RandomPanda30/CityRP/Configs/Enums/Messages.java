package co.uk.RandomPanda30.CityRP.Configs.Enums;

import java.util.ArrayList;

public enum Messages {

	ARG ("&4"),
	ERROR ("&c"),
	HEADER ("&6"),
	NORMAL ("&f"),
	TEXT ("&7"),
	TAG ("%A[%NCityRP%A]%N"),
	WARNING ("&4"),
	GOOD ("&a"),
	BAD ("&c"),

	/*
	 * All the other formatting will be done in other areas of the code
	 */

	COMMAND_NULL ("%TAG %EThis command does not exist"),
	COMMAND_PLAYERONLY ("%TAG %EThis command can only be done by console"),

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

	SCOREBOARD_TITLE ("%A%player %N: %A%job"),
	SCOREBOARD_MONEY ("%NMoney: %A%money"),
	SCOREBOARD_LOOKINGAT ("%NLooking at:"),
	SCOREBOARD_NAME ("%NName: %A"),
	SCOREBOARD_JOB ("%NJob: "),
	SCOREBOARD_JOB2 ("%NYour Job: "),
	SCOREBOARD_BALANCE ("%NBalance: %A'money'"),

	NOTIFICATIONS_NOMYSQL (
			"%TAG %EMySQL has not been configued. Entering setup in %time seconds..."),
	NOTIFICATIONS_DATABASESETUP (
			"%TAG %NYour database has not been setup yet. Please look in console to setup MySQL"),

	MYSQL_LOADING ("%TAG %NLoading MySQL"),
	MYSQL_BADSETTINGS ("%TAG %EShutting down due to bad MySQL settings"),

	ITEM_MAYNOTDROP ("%TAG %EYou may not drop this item"),

	LOCATIONSETUP_ALREADYEDITING (
			"%TAG %A%name %Eis already editing these locations"),
	LOCATIONSETUP_EDITING ("%TAG %NYou are now editing all locations"),
	LOCATIONSETUP_ERRRM (
			"%TAG %EYou are not even in the variable that says who's editing the locations...How did you get these items? D:"),

	@SuppressWarnings ("serial") SETUP_DATABASE_DETAILS(
			new ArrayList<String>() {
				{
					add("%H=========************************************************************=========");
					add("%ACityRP %Ndatabase setup");
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

	Messages (Object value) {
		this.value = value;
	}

}
