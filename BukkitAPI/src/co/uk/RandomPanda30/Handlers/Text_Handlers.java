package co.uk.RandomPanda30.Handlers;

import co.uk.RandomPanda30.Main.Main;

public class Text_Handlers {

	// Anything tag related
	public static String tag = "§F[§BT - MC§F] ";
	public static String Btag = "§F[§BTournamentMC§F] ";
	public static String noPerm = Btag.toString()
			+ "§CYou don't have permission to do this";
	public static String website = "";
	public static String startM = tag.toString() + "§aStarting up API";
	public static String startAPIV = tag.toString() + "§aUsing API version "
			+ Misc_Handlers.apiV.toString();
	public static String startBV = tag.toString() + "§aUsing Bukkit version "
			+ Misc_Handlers.serverV.toString();
	public static String closeS = tag.toString() + "§cUnmounting API";
	public static String teleportingSpawn = "§BTeleporting you to the Spawn";
	public static String teleportingPlayer = "§B has been teleported to the Spawn";
	public static String welcome = "§BWelcome";
	public static String ip = "§BServer IP: tournamentmc.ax.tl";
	public static String compassName = "§BTeleport Menu";
	public static String lore1 = "§BClick to teleport to a game";
	public static String toHub = "§BHub";
	public static String toSG = "§FLast Man Standing";

	public static String sgLore1 = "§5§K||§BEpic, custom coded Hunger Games!";
	public static String sgLore2 = "§BSurvive for as long as you can";
	public static String sgLore2C = "§Band become the next victor§5§K||";
	public static String sgLoreSpace = "";

	public static String teleportMenu = "Teleport Menu";
	public static String banReason = Btag.toString()
			+ "§CYou have been banned. Appeal at" + website.toString();
	public static String banSyntax = Btag.toString() + "§BBan §F<§BPlayer§F>";
	public static String kickReason = Btag.toString()
			+ "§CYou have been kicked from the server";
	public static String kickSyntax = Btag.toString() + "§BKick §F<§BPlayer§F>";
	public static String muteSyntax = Btag.toString() + "§BMute §F<§BPlayer§F>";
	public static String slapSyntax = Btag.toString() + "§BSlap §F<§BPlayer§F>";
	public static String infoSyntax = Btag.toString() + "§BInfo";
	public static String voteKicksyntax = Btag.toString()
			+ "§BVotekick §F<§BPlayer§F>";
	public static String yvoteKick = Btag.toString()
			+ "§BYvotekick <§FPlayer§B>";
	public static String nvoteSyntax = Btag.toString() + "§BNkick <§FPlayer§B>";
	public static String unmuteSyntax = Btag.toString()
			+ "§BUnmute §F<§BPlayer§F>";
	public static String nullBanned = tag.toString()
			+ "§CNull banned.yml, making a new one";
	public static String createdBanned = tag.toString()
			+ "§Abanned.yml has been made";
	public static String errorBanned = tag.toString()
			+ "§CError while loading banned.yml";
	public static String loadingBanned = tag.toString()
			+ "§BLoading banned.yml";
	public static String killSyntax = "§BKill §F<§BPlayer§F>";
	public static String teleportSyntax = "§BT§F<§BPlayer§F>";
	public static String teleportHereSyntax = "§BTh §F<§BPlayer§F>";
	public static String opSyntax = Btag.toString() + "§BOp §F<§BPlayer§F>";
	public static String DeopSyntax = Btag.toString() + "§BDeop §F<§BPlayer§F>";
	public static String distressSyntax = Btag.toString()
			+ "§BDistress §F<§BPlayer§F>";
	public static String modSyntax = Btag.toString() + "§BMod";
	public static String toggleOn = "§BPlayers " + "§COn";
	public static String voteKicked = Btag.toString()
			+ "§CYou have been vote kicked!";
	public static String nullPlayer = "§CPlayer is null";

	public static String mEen = "§6Info for §BTournamentMFC - ";
	public static String mTwee = Text_Handlers.Btag.toString()
			+ "§BServer IP: " + Main.plugin.getServer().getIp().toString();
}
