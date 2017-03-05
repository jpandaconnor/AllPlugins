package co.uk.randompanda30.houseshop.config;

import co.uk.randompanda30.houseshop.HouseShop;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static co.uk.randompanda30.houseshop.TEMP.*;

/**
 * Created by panda on 02/05/16.
 */
public class Messages {

    public Messages(HouseShop plugin) {
        try {
            messages = new File("plugins/" + plugin.getName() + "/messages.yml");
            if (!messages.exists()) {
                messages.getParentFile().mkdirs();
                messages.createNewFile();
            }

            messagesc = new YamlConfiguration();
            messagescs = messagesc.getConfigurationSection("");
            messagesc.load(messages);

            for (MessagesValues value : MessagesValues.values()) {
                if (messagesc.get(value.name().replaceAll("_", ".")) == null) {
                    messagesc.set(value.name().replaceAll("_", "."), value.value);
                    save();
                }

                value.value = messagesc.get(value.name().replaceAll("_", "."));
            }

            messagesc.load(messages);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    private void save() {
        try {
            messagesc.save(messages);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public enum MessagesValues {
        ARG("&4"),
        ERROR("&c"),
        HEADER("&6"),
        NORMAL("&f"),
        TEXT("&7"),
        TAG("%A[%N" + HouseShop.getPlugin().getName() + "%A]%N"),
        WARNING("&4"),
        GOOD("&a"),
        BAD("&c"),

        HS_NOTFORCONSOLE("%TAG %EYou cannot do this command in console"),
        HS_NOPERM("%TAG %EYou do not have permission to do this. Permission needed: %A%perm"),
        HS_SYNTAX("%TAG %EIncorrect syntax. Please try: %A/%syntax"),
        HS_ALREADYEDITING("%TAG %EYou are already in edit mode!"),
        HS_NOTINHOUSE("%TAG %EYou are not in a house"),
        HS_ALREADYEDITINGHOUSE("%TAG %ESomeone is already editing this house"),
        HS_NOVAULT("%TAG %EPlugin's not going to load as Vault has not been found"),
        HS_NOTENOUGH("%TAG %EYou cannot afford this house!"),
        HS_DONTHAVEAHOUSE("%TAG %EYou cannot do this as you don't have a house"),
        HS_PLAYERNOTFOUND("%TAG %EThis player could not be found"),
        HS_DOESNTEXIST("%TAG %EA house with this name doesn't exist"),


        /*
        Editor shite here
         */

        EDITOR_JOIN_DETAILS(new ArrayList<String>() {
            {
                add("%TAG %GYou have been placed into the house editor mode");
                add(" ");
                add("%NPlease place the block anywhere in the dedicated area");
                add("%Nchecking that all doors and windows have been sealed first");
                add("   ");
                add("%NTo reset your selection, simply right click the %Areset %Nitem");
                add("    ");
                add("%NTo leave, simply right click the %Aleave %Nitem");
                add("     ");
                add("%NAll your items + stats have been stored and will be returned");
                add("%Nupon leaving");
            }
        }),

        EDITOR_SELECTION_OVERLIMIT("%TAG %ESomething went wrong then. The selection you made is outside the limit. Please" +
                " check all doors and windows are filled and try again"),
        EDITOR_SELECTION_OVERLAP("%TAG %EThe selection you made overlaps another selection. Please try again"),
        EDITOR_SELECTION_NOTSELECTED("%TAG %EYou have not selected an area. Please select an area and try again"),
        EDITOR_RESET_RESET("%TAG %GAll selections have been reset"),
        EDITOR_LEAVE_WHYHAVETHISITEM("%TAG %EHow the hell did you get this item?...Magic I tell you"),
        EDITOR_LEAVE_LEFT("%TAG %GYou have left editing mode"),
        EDITOR_TYPINGNAME_PLEASETYPE("%TAG %NNow type a name in the chat to set the name of this selection/house"),
        EDITOR_TYPINGNAME_NOSPACES("%TAG %NThe name must not contain spaces"),
        EDITOR_TYPINGNAME_ALREADYEXISTS("%TAG %EA house with this name already exists"),
        EDITOR_DONE_FINISHED("%TAG %GYou have created a new house called %A%name"),
        EDITOR_NEEDSSPAWN("%TAG %EPlace down the spawn block before you continue"),

        /*
        When they enter the house and stuff
         */

        ENTERED_HOUSEFORSALE("%TAG &b&lTHIS HOUSE IS UP FOR SALE ONLY &a&l%price&b&l! DO &a&l/houseshop purchase " +
                "&b&lTO BUY TODAY!"),
        ENTERED_MAYBENEXTTIME("%TAG %NNot today? That's fine we won't bother you anymore. Just do %A/houseshop purchase " +
                "%Nnext time"),

        PURCHASE_NOTINHOUSE("%TAG %EYou must be standing in a house to purchase it"),
        PURCHASE_ALREADYHAD("%TAG %EYou already have a house!"),
        PRUCHASE_ALREADYOWNED("%TAG %EThis house is already owned"),
        PURCAHSE_DONE("%TAG %GHouse purchased!"),

        SELL_NOTINHOUSE("%TAG %Eyou must be in your house to sell it"),
        SELL_HASNOHOUSE("%TAG %EYou do not have a house to sell in the first place"),
        SELL_SOLD("%TAG %EYou have sold your house!"),
        SELL_MOVEDTOKICKOUT("%TAG %GSince you do not own this house anymore, you have been placed on the streets!"),

        FRIEND_ADD_INLIST("%TAG %EThis friend is already in your friends list"),
        FRIEND_ADD_ADDED("%TAG %GThis player has been added to your friends list"),
        FRIEND_ADD_CANTINVITESELF("%TAG %EYou cannot invite yourself"),

        FRIEND_DELETE_NOTINLIST("%TAG %EThis player is not in your friends list"),
        FRIEND_DELETE_DELETED("%TAG %GThis player has been removed from your friends list"),
        FRIEND_DELETE_CANTDELETESELF("%TAG %EYou cannot delete yourself"),

        VISIT_SENT("%TAG %GYou have been sent to %A%name's %Ghouse"),
        VISIT_NOTINFRIENDSLIST("%TAG %EYou are not in this player's friend list"),
        VISIT_NOTHASHOUSE("%TAG %EThis player does not have a house"),

        TP_NOTAHOUSE("%TAG %EA house with this name does not exist"),
        TP_TELEPORTED("%TAG %GYou have been teleported!"),

        TOGGLE_DONE("%TAG %GNon-friend access: %status"),

        MOVEMENT_NOTHOUSE("%TAG %EYou cannot enter this property as it's not yours"),

        KICKOUTSET("%TAG %GYou have set the kickout"),

        SELECTION_STARTED("%TAG %GSelection started! Please do not log out or move until the selection has finished!"),
        SELECTION_FINISHED("%TAG %GSelection finished!"),

        REQUEST_ALREADYAHOUSE("%TAG %EYou cannot request this house to be setup as it is already a house"),
        REQUEST_OLDNOTDONE("%TAG %EYou may only post one house request at a time"),
        REQUEST_DONE("%TAG %GHouse requested! An admin will set this house up shortly"),
        REQUEST_NOREQUEST("%TAG %GNo requests have been found. Yay!"),
        REQUEST_TEMPLATE("%NRequest from: %A%name"),
        REQUEST_NOTREQUESTED("%TAG %EThere is no requests to be cancelled"),
        REQUEST_CANCELLED("%TAG %EYour house request has been cancelled"),
        REQUEST_NOTFOUND("%TAG %EA request from this player has not been found. Check the player name and try again"),
        REQUEST_PLAYERINFORED("%TAG %GRequest resolved! Player is online and will be informed"),
        REQUEST_REQUESTDONE("%TAG %GYour house request has been completed"),
        REQUEST_REQUESTWILLSEND("%TAG %GThe player who requested this house is not online. They will be informed when " +
                "they next log on"),

        DELETE_DELETED("%TAG %GHouse and region have been deleted"),

        TPDIR_MUSTHAVE("%TAG %EMake sure you're typing a direction. For example, /houseshop tpdir N (N for North)"),
        TPDIR_TELEPORTED("%TAG %GYou have been teleported"),
        TPDIR_NOTFOUND("%TAG %BA warp to this direction has not been found. Please tell an admin...like quickly"),

        RANDOM_NOTHOUSES("%TAG %EThere are no houses to be teleported to. You probably should report this...");

        /*
        Configure command stuff
         */

        public Object value;

        MessagesValues(Object value) {
            this.value = value;
        }
    }
}