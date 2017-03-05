package co.uk.randompanda30.fix;

import net.minecraft.server.v1_7_R4.PlayerConnection;
import org.bukkit.plugin.java.JavaPlugin;

public class Fix extends JavaPlugin {

    Fix fix;

    @Override
    public void onEnable() {
        fix = this;
        new PlayerConnection(this.getc)
    }

    @Override
    public void onDisable() {

    }

}