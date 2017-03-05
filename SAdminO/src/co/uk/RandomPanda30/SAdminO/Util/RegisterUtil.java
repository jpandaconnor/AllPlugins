package co.uk.RandomPanda30.SAdminO.Util;

import co.uk.RandomPanda30.SAdminO.Commands.List.NoArg.CommandBan;
import co.uk.RandomPanda30.SAdminO.Events.OnPlayerJoinEvent;
import co.uk.RandomPanda30.SAdminO.SAdminO;

public class RegisterUtil {

	/*
     *
	 * Stops all the random events and commands being all clotted in the
	 * onEnable method
	 * 
	 */

    public SAdminO plugin;

    public RegisterUtil(SAdminO plugin) {
        this.plugin = plugin;
        init();
    }

    public void init() {
        new OnPlayerJoinEvent(plugin);

        new CommandBan(plugin);
    }
}