package co.uk.randompanda30.houseshop.events;

import co.uk.randompanda30.houseshop.HouseShop;
import co.uk.randompanda30.houseshop.TEMP;
import co.uk.randompanda30.houseshop.config.Data;
import co.uk.randompanda30.houseshop.config.Messages;
import co.uk.randompanda30.houseshop.item.Items;
import co.uk.randompanda30.houseshop.query.HouseQ;
import co.uk.randompanda30.houseshop.query.PlayerQ;
import co.uk.randompanda30.houseshop.query.SelectionQ;
import co.uk.randompanda30.houseshop.string.Dispatch;
import co.uk.randompanda30.houseshop.string.Format;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by panda on 02/05/16.
 */
public class OnInventoryClickEvent implements Listener {

    HouseShop plugin;

    public OnInventoryClickEvent(HouseShop plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        UUID uuid = player.getUniqueId();

        if (event.getCurrentItem() == null) {
            return;
        }

        if (event.getCurrentItem().getType().equals(Material.AIR)) {
            return;
        }

        if (event.getInventory().getName().equals("House options")) {
            if (event.getCurrentItem().hasItemMeta() && event.getCurrentItem().getItemMeta().getDisplayName().equals(
                    Format.format("%NPay rent!"))) {
                if (!(HouseShop.getEconomy().getBalance(player) >= HouseQ.getHousePrice(PlayerQ.getHouse(player)))) {
                    Dispatch.sendMessage((String) Messages.MessagesValues.HS_NOTENOUGH.value, player);
                } else {
                    HouseShop.getEconomy().withdrawPlayer(player, HouseQ.getHousePrice(PlayerQ.getHouse(player)));

                    long time = TEMP.datac.getLong(PlayerQ.getHouse(player) + ".rent");
                    long l = time;
                    l = l + (1000 * 60 * 60 * 24);
                    TEMP.datac.set(PlayerQ.getHouse(player) + ".rent", l);
                    Data.save();

                    player.closeInventory();
                    String house = PlayerQ.getHouse(player);
                    Inventory menu = Bukkit.createInventory(null, 9, "House options");

                    menu.setItem(4, Items.getItems().getHouseDetails(house));

                    player.openInventory(menu);
                }
            }

            event.setCancelled(true);
        }

        if (TEMP.editingHouses.containsKey(uuid)) {
            String houseEditing = SelectionQ.getHouse(player);

            HashMap<String, Inventory> current = TEMP.editingHouses.get(uuid);
            if (event.getInventory().equals(current.get(houseEditing))) {
                Inventory inv = current.get(houseEditing);

                if (event.getCurrentItem().equals(Items.getItems().minus_1)) {
                    HouseQ.setHousePrice(houseEditing, (HouseQ.getHousePrice(houseEditing) - 1 < 0 ? 0 :
                            HouseQ.getHousePrice(houseEditing) - 1));
                    refreshConfigure(houseEditing, inv);
                }

                if (event.getCurrentItem().equals(Items.getItems().minus_10)) {
                    HouseQ.setHousePrice(houseEditing, (HouseQ.getHousePrice(houseEditing) - 10 < 0 ? 0 :
                            HouseQ.getHousePrice(houseEditing) - 10));
                    refreshConfigure(houseEditing, inv);
                }

                if (event.getCurrentItem().equals(Items.getItems().minus_100)) {
                    HouseQ.setHousePrice(houseEditing, (HouseQ.getHousePrice(houseEditing) - 100 < 0 ? 0 :
                            HouseQ.getHousePrice(houseEditing) - 100));
                    refreshConfigure(houseEditing, inv);
                }

                if (event.getCurrentItem().equals(Items.getItems().minus_1000)) {
                    HouseQ.setHousePrice(houseEditing, (HouseQ.getHousePrice(houseEditing) - 1000 < 0 ? 0 :
                            HouseQ.getHousePrice(houseEditing) - 1000));
                    refreshConfigure(houseEditing, inv);
                }

                if (event.getCurrentItem().equals(Items.getItems().plus_1)) {
                    HouseQ.setHousePrice(houseEditing, HouseQ.getHousePrice(houseEditing) + 1);
                    refreshConfigure(houseEditing, inv);
                }

                if (event.getCurrentItem().equals(Items.getItems().plus_10)) {
                    HouseQ.setHousePrice(houseEditing, HouseQ.getHousePrice(houseEditing) + 10);
                    refreshConfigure(houseEditing, inv);
                }

                if (event.getCurrentItem().equals(Items.getItems().plus_100)) {
                    HouseQ.setHousePrice(houseEditing, HouseQ.getHousePrice(houseEditing) + 100);
                    refreshConfigure(houseEditing, inv);
                }

                if (event.getCurrentItem().equals(Items.getItems().plus_1000)) {
                    HouseQ.setHousePrice(houseEditing, HouseQ.getHousePrice(houseEditing) + 1000);
                    refreshConfigure(houseEditing, inv);
                }


                event.setCancelled(true);
            }
        }
    }

    private void refreshConfigure(String name, Inventory inventory) {
        inventory.clear();

        inventory.setItem(26, Items.getItems().plus_1000);
        inventory.setItem(25, Items.getItems().plus_100);
        inventory.setItem(24, Items.getItems().plus_10);
        inventory.setItem(23, Items.getItems().plus_1);

        inventory.setItem(22, Items.getItems().getPaperDetails(name));

        inventory.setItem(18, Items.getItems().minus_1000);
        inventory.setItem(19, Items.getItems().minus_100);
        inventory.setItem(20, Items.getItems().minus_10);
        inventory.setItem(21, Items.getItems().minus_1);
    }
}