package co.uk.randompanda30.jarfix;

import net.minecraft.server.v1_7_R4.*;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class Main extends PlayerConnection {

    public Main(MinecraftServer minecraftserver, NetworkManager networkmanager, EntityPlayer entityplayer) {
        super(minecraftserver, networkmanager, entityplayer);
    }

    public void a(PacketPlayInHeldItemSlot packetPlayerInHeldItemSlot) {
        if(this.player.dead) {
            return;
        }
    }

    public static void main(String[] args) {
	// write your code here
    }
}
