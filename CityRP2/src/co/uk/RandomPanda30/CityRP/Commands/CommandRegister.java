package co.uk.RandomPanda30.CityRP.Commands;

import co.uk.RandomPanda30.CityRP.CityRP;
import co.uk.RandomPanda30.CityRP.Commands.CMD.CMD_CityRP;

public class CommandRegister {

	public CommandRegister(CityRP plugin) {
		CommandHandler ch = new CommandHandler();
		ch.register("cityrp", new CMD_CityRP());
		
		plugin.getCommand("cityrp").setExecutor(ch);
	}
}