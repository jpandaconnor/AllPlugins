package co.uk.randompanda30.houseshop.item;

import co.uk.randompanda30.houseshop.TEMP;
import co.uk.randompanda30.houseshop.query.HouseQ;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by panda on 02/05/16.
 */
public class Items {

    private static Items instance = new Items();

    /*
    Editor items here
     */
    public ItemStack editor_leave =
            ItemBuilder.buildItem("&c&lLeave edit mode", Material.WOOL, 1, 14, new ArrayList<>());

    public ItemStack editor_block =
            ItemBuilder.buildItem("&3&lPlace to select region!", Material.WOOL, 1, 9, new ArrayList<>());
    public ItemStack editor_spawn =
            ItemBuilder.buildItem("&7&lPlace to select the outside spawn!", Material.WOOL, 1, 7, new ArrayList<>());
    public ItemStack editor_reset =
            ItemBuilder.buildItem("&e&lReset selection", Material.WOOL, 1, 4, new ArrayList<>());
    public ItemStack editor_done =
            ItemBuilder.buildItem("&a&lFinished", Material.WOOL, 1, 5, new ArrayList<>());

    public ItemStack minus_1 =
            ItemBuilder.buildItem("&c-1", Material.GOLD_NUGGET, 1, 0, new ArrayList<>());
    public ItemStack minus_10 =
            ItemBuilder.buildItem("&c-10", Material.GOLD_NUGGET, 1, 0, new ArrayList<>());
    public ItemStack minus_100 =
            ItemBuilder.buildItem("&c-100", Material.GOLD_NUGGET, 1, 0, new ArrayList<>());
    public ItemStack minus_1000 =
            ItemBuilder.buildItem("&c-1000", Material.GOLD_NUGGET, 1, 0, new ArrayList<>());

    public ItemStack plus_1 =
            ItemBuilder.buildItem("&a+1", Material.GOLD_NUGGET, 1, 0, new ArrayList<>());
    public ItemStack plus_10 =
            ItemBuilder.buildItem("&a+10", Material.GOLD_NUGGET, 1, 0, new ArrayList<>());
    public ItemStack plus_100 =
            ItemBuilder.buildItem("&a+100", Material.GOLD_NUGGET, 1, 0, new ArrayList<>());
    public ItemStack plus_1000 =
            ItemBuilder.buildItem("&a+1000", Material.GOLD_NUGGET, 1, 0, new ArrayList<>());

    public static Items getItems() {
        return instance;
    }

    public ItemStack getPaperDetails(String houseName) {
        return ItemBuilder.buildItem("%NPrice: %A" + Integer.toString(HouseQ.getHousePrice(houseName)),
                Material.PAPER, 1, 0, new ArrayList<>());
    }

    /*
     House menu items
     */

    public ItemStack getHouseDetails(String houseName) {
        long time = TEMP.datac.getLong(houseName + ".rent");

        long days = 0;
        long currentTime = Calendar.getInstance().getTimeInMillis();
        long finalTime = time - currentTime;

        days = finalTime / 1000 / 60 / 60 / 24;

        ArrayList<String> lores = new ArrayList<>();
        lores.add((days != 0 ? "%A" + Long.toString(days) + " %Ndays left" : "%E> 1 day left"));
        lores.add("%AIncrease your rent time by 1 day");

        return ItemBuilder.buildItem("%NPay rent!", Material.LEVER, 1, 0, lores);
    }
}