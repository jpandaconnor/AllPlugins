package co.uk.RandomPanda30.SAO.Plugin;

/*
This class is mainly for all the event classes and commands
 */

import co.uk.RandomPanda30.SAO.Events.OnPlayerJoinEvent;
import co.uk.RandomPanda30.SAO.Events.OnPlayerQuitEvent;
import co.uk.RandomPanda30.SAO.Modules.IO.Entry;
import co.uk.RandomPanda30.SAO.Modules.IO.Login;
import co.uk.RandomPanda30.SAO.Modules.Items.ItemTransfer;
import co.uk.RandomPanda30.SAO.Modules.Misc.RankReinstate;
import co.uk.RandomPanda30.SAO.Modules.Utility.Admin;
import co.uk.RandomPanda30.SAO.Modules.Utility.Backup;
import co.uk.RandomPanda30.SAO.Modules.Utility.FloorRestart;
import co.uk.RandomPanda30.SAO.SAO;

public class Register {

    public SAO plugin;

    public Register(SAO plugin) {
        this.plugin = plugin;
        init();
    }

    private void init() {
        new OnPlayerJoinEvent(plugin);
        new OnPlayerQuitEvent(plugin);

        new ItemTransfer(plugin);
        new Backup(plugin);
        new Entry(plugin);
        new Login(plugin);
        new Admin(plugin);
        // new Reward(plugin);

        new RankReinstate(plugin);

        new FloorRestart(plugin);
    }
}