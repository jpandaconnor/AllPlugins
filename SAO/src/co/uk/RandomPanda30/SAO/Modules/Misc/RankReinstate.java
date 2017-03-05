package co.uk.RandomPanda30.SAO.Modules.Misc;

import co.uk.RandomPanda30.SAO.Config.Config;
import co.uk.RandomPanda30.SAO.SAO;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class RankReinstate implements Listener {

    public SAO plugin;

    public RankReinstate(SAO plugin) {
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerLoginEvent(PlayerLoginEvent event) {
        for (String s : co.uk.RandomPanda30.SAO.Config.Misc.RankReinstate.beaterf.getStringList("Beaters")) {
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(),
                    Config.ConfigValues.MODULES_MISC_RANKREINSTATE_BEATERCOMMAND.value.toString().replace("%player", s));
        }

        for (String s : co.uk.RandomPanda30.SAO.Config.Misc.RankReinstate.beaterplusf.getStringList("BeatersPlus")) {
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(),
                    Config.ConfigValues.MODULES_MISC_RANKREINSTATE_BEATERPLUSCOMMAND.value.toString().replace("%player", s));
        }
    }
}