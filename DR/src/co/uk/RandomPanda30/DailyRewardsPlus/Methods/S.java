package co.uk.RandomPanda30.DailyRewardsPlus.Methods;

import java.util.ArrayList;

import co.uk.RandomPanda30.DailyRewardsPlus.B;

public class S {

	@SuppressWarnings("unchecked")
	public static ArrayList<String> getCommandsList() {
		return (ArrayList<String>) B.configC.get("COMMANDSLIST");
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<String> getConsoleCommandsList() {
		return (ArrayList<String>) B.configC.get("CONSOLECOMMANDSLIST");
	}
}