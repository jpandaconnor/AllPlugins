package co.uk.randompanda30.sao.config;

/*
   Created by panda on 16/07/16.

   Copyright 2016 JPanda (Connor Brady)

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

import co.uk.randompanda30.sao.SAO;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static co.uk.randompanda30.sao.TEMP.*;

public class Messages {

    public Messages() {
        try {
            messages = new File("plugins/" + SAO.getPlugin().getName() + "/messages.yml");
            if (!messages.exists()) {
                messages.getParentFile().mkdirs();
                messages.createNewFile();
            }

            messagesf = new YamlConfiguration();
            messagesc = messagesf.getConfigurationSection("");
            messagesf.load(messages);

            for (MessagesValues value : MessagesValues.values()) {
                if (messagesc.get(value.name().replaceAll("_", ".")) == null) {
                    messagesc.set(value.name().replaceAll("_", "."), value.value);
                    save();
                }
                value.value = messagesc.get(value.name().replaceAll("_", "."));
            }

            messagesf.load(messages);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        try {
            messagesf.save(messages);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public enum MessagesValues {
        TAG("&f&l[&b&lSAO&f&l]%N"),

        HEADER("&6"),
        TEXT("&7"),

        ARG("&b"),
        NORMAL("&f"),
        ERROR("&c"),

        GOOD("&a"),
        BAD("&c"),

        NOPERMISSION("%TAG %EYou do not have permission to do this"),
        NOCONSOLE("%TAG %EYou cannot do this as console"),
        SYNTAX("%TAG %EIncorrect syntax. Please try: %A/%syntax"),

        MODULES_BOOSTER_BOOSTERSTARTED(new ArrayList<String>() {
            {
                add("%A&lBooster Activated");
                add("%A&l%player %Nhas activated their booster");
                add("%N&lBoosts XP by: %A&l%perc%");
            }
        }),

        MODULES_BOOSTER_BOOSTERDUMPEDSTARTED(new ArrayList<String>() {
            {
                add("%A&lOngoing Booster Activated");
                add("%A&l%player %Nhas activated their booster");
                add("%N&lBoosts XP by: %A&l%perc%");
            }
        }),

        MODULES_BOOSTER_FULLINVENTORY("%TAG %EYou have purchased boosters since you last logged off. Your boosters " +
                "cannot be given to you as your inventory is full. Please make space in your inventory and " +
                "do %A/booster redeem %Eto obtain your boosters"),
        MODULES_BOOSTER_BOOSTERSGIVEN("%TAG %GYou have purchased boosters since you last logged off. They have been " +
                "place in your inventory"),
        MODULES_BOOSTER_ACTIVATED("%TAG %GYou have activated your booster!"),
        MODULES_BOOSTER_RUNNING("%TAG %EYou cannot activate a booster as there is one already running"),
        MODULES_BOOSTER_EXPIRED("%A&l%player's %E&lbooster has expired"),
        MODULES_BOOSTER_ONGOING("%TAG %A&l%player %G&lhas a booster activated"),

        MODULES_ITEMRESTRICT_ITEMS("%TAG %E&lThe server has found %A&l%amount %E&lrestricted items in your inventory. " +
                "These items have been taken out of your inventory and will be given back to you when you reach the " +
                "appropriate level"),
        MODULES_ITEMRESTRICT_GIVENBACK("%TAG %GYou have been given back some restricted items as you are now at the " +
                "correct level"),
        MODULES_ITEMRESTRICT_NOSPACE("%TAG %EYou have restricted items ready to be given back to you, but you have no" +
                " space in your inventory. Please make room in your inventory and do %A/itemrestrict retrieve"),

        MODULES_PLAYERDATA_RESET(new ArrayList<String>() {
            {
                add("&c&lPLAYER DATA RESET");
                add(" ");
                add("&cYou will not be able to join the server until the reset has been completed");
                add("  ");
                add("&cStarting reset...");
            }
        }),

        MODULES_PLAYERDATA_RESETDONE("%TAG &a&lPlayer data reset completed!"),
        MODULES_PLAYERDATA_RESETFAILED("%TAG &c&lPlayer data failed! Please report this to an admin"),

        MODULES_AUTOSHUTDOWN_1HOURLEFT("%TAG %A1 hour %Nleft until server restart"),
        MODULES_AUTOSHUTDOWN_15MINSLEFT("%TAG %A15 minutes %Nleft until server restart"),
        MODULES_AUTOSHUTDOWN_10MINSLEFT("%TAG %A10 minutes %Nleft until server restart"),
        MODULES_AUTOSHUTDOWN_5MINSLEFT("%TAG %A5 minutes %Nleft until server restart"),
        MODULES_AUTOSHUTDOWN_1MINLEFT("%TAG %A1 minute %Nleft until server restart"),
        MODULES_AUTOSHUTDOWN_30SECONDSLEFT("%TAG %A30 seconds %Nleft until server restart"),
        MODULES_AUTOSHUTDOWN_SHUTDOWNIN("%TAG %NServer shutting down in %A%time"),
        MODULES_AUTOSHUTDOWN_STOPPEDFORBACKUP("%TAG %EScheduled shutdown stopped as backup is still going"),

        MODULES_BACKUP_PERCENTAGE("%TAG %NBackup progress: %A%perc%N% %A%done%N/%A%files"),
        MODULES_BACKUP_STARTED("%TAG %GBackup started!"),
        MODULES_BACKUP_STOPPED("%TAG %NBackup finished!"),

        MODULES_CHATFILTER_DONOTSWEAR("%TAG &c&lOffensive language will get you banned"),

        MODULES_PLAYERHOUSING_EDITOR_JOIN_DETAILS(new ArrayList<String>() {
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
                add("The spawn block must be placed down and a district must be selected before you");
                add("can create a new house");
                add("      ");
                add("%NAll your items + stats have been stored and will be returned");
                add("%Nupon leaving");
            }
        }),

        MODULES_PLAYERHOUSING_NOHOUSE("%TAG %EYou cannot do this as you have not bought a house yet"),
        MODULES_PLAYERHOUSING_NODISTRICTS("%TAG %EThere are no districts. Please setup a district first"),

        MODULES_PLAYERHOUSING_EDITOR_SELECTION_OVERLIMIT("%TAG %ESomething went wrong then. The selection you made is " +
                "outside the limit. Please check all doors and windows are filled and try again"),
        MODULES_PLAYERHOUSING_EDITOR_SELECTION_OVERLAP("%TAG %EThe selection you made overlaps another selection. " +
                "Please try again"),
        MODULES_PLAYERHOUSING_EDITOR_SELECTION_NOTSELECTED("%TAG %EYou have not selected an area. Please select an area " +
                "and try again"),
        MODULES_PLAYERHOUSING_EDITOR_RESET_RESET("%TAG %GAll selections have been reset"),
        MODULES_PLAYERHOUSING_EDITOR_LEAVE_WHYHAVETHISITEM("%TAG %EHow the hell did you get this item?...Magic I tell you"),
        MODULES_PLAYERHOUSING_EDITOR_LEAVE_LEFT("%TAG %GYou have left editing mode"),
        MODULES_PLAYERHOUSING_EDITOR_TYPINGNAME_PLEASETYPE("%TAG %NNow type a name in the chat to set the name of this " +
                "selection/house"),
        MODULES_PLAYERHOUSING_EDITOR_TYPINGNAME_NOSPACES("%TAG %NThe name must not contain spaces"),
        MODULES_PLAYERHOUSING_EDITOR_TYPINGNAME_ALREADYEXISTS("%TAG %EA house with this name already exists"),
        MODULES_PLAYERHOUSING_EDITOR_DONE_FINISHED("%TAG %GYou have created a new house called %A%name"),
        MODULES_PLAYERHOUSING_EDITOR_NEEDSSPAWN("%TAG %EPlace down the spawn block before you continue"),
        MODULES_PLAYERHOUSING_SELECTION_STARTED("%TAG %GSelection started! Please do not log out or move until the selection has finished!"),
        MODULES_PLAYERHOUSING_SELECTION_FINISHED("%TAG %GSelection finished!"),

        MODULES_PLAYERHOUSING_DISTRICTCREATE_START("%TAG %NA GUI will appear in a second that will allow you to enter " +
                "a district name"),
        MODULES_PLAYERHOUSING_DISTRICTCREATE_NOTEXT("%TAG %EYou must enter a name to create a district"),
        MODULES_PLAYERHOUSING_DISTRICTCREATE_ALREADYDIS("%TAG %EA district with this name already exists"),
        MODULES_PLAYERHOUSING_DISTRICTCREATE_CREATED("%TAG %GDistrict created!"),
        MODULES_PLAYERHOUSING_DISTRICTCHOSEN("%TAG %GDistrict selected. Please wait..."),

        MODULES_PARTY_PARTYCREATED(new ArrayList<String>() {
            {
                add("%TAG %GYou have created a new party");
                add(" ");
                add("%TUse the command %A/sao %Tto access the party GUI");
            }
        }),


        MODULES_MICI_ATTENTION("&c&lMici (Bot) -> &a&lHi &5&l%player&a&l, "),

        COMMAND_BOOSTER_CANCEL_NOBOOSTERUNNING("%TAG %EThere is no booster running that can be cancelled"),
        COMMAND_BOOSTER_CANCEL_CANCELLED("%TAG %GThis booster has been cancelled"),

        COMMAND_BOOSTER_INFO_NOBOOSTERUNNING("%TAG %EThere is no booster running!"),
        COMMAND_BOOSTER_INFO_INFORMATION(new ArrayList<String>() {
            {
                add("%A&lBooster information %N&l- ");
                add("%NName: %A%player");
                add("%NTime left: %A%time");
                add("%NPercentage: %A%perc");
            }
        }),

        COMMAND_BOOSTER_TRANSFER_DONE("%TAG %GScan complete! Your old boosters have been turned into new ones!"),

        COMMAND_BOOSTER_REDEEM_NONTO("%TAG %EYou don't have any boosters to redeem"),
        COMMAND_BOOSTER_REDEEM_NOTENOUGHSPACE("%TAG %EYou don't have enogh space in your inventory for your boosters"),
        COMMAND_BOOSTER_REDEEM_DONE("%TAG %GYou have been given your boosters! Enjoy!"),

        COMMAND_BOOSTER_GIVE_NULL("%TAG %EThis player cannot be found! Please try again"),
        COMMAND_BOOSTER_GIVE_NOTNUMBER("%TAG %EThe percentage you entered is not a number/contains letters"),
        COMMAND_BOOSTER_GIVE_GIVEN("%TAG %GYou have given %A%player %Ga booster!"),
        COMMAND_BOOSTER_GIVE_YOUGIVE("%TAG %GYou have been given your booster. Enjoy!"),

        COMMAND_ITEMRESTRICT_ADD_ITEMRESTRICTED("%TAG %EThis item is already restricted for level: %A%level%E. To edit " +
                "the restriction for this item, do %A/itemrestrict edit <Level>"),
        COMMAND_ITEMRESTRICT_ADD_NOTNUMBER("%TAG %EThe level you entered is not a number"),
        COMMAND_ITEMRESTRICT_ADD_NOTHINGINHAND("%TAG %EYou must have an item in your hand to use this command"),
        COMMAND_ITEMRESTRICT_ADD_DONE("%TAG %GThis item has been restricted successfully"),

        COMMAND_ITEMRESTRICT_DELETE_NOTHINGINHAND("%TAG %E%EYou must have an item in your hand to use this command"),
        COMMAND_ITEMRESTRICT_DELETE_NOTINCONFIG("%TAG %EThere is no restriction against this item"),
        COMMAND_ITEMRESTRICT_DELETE_DONE("%TAG %GYou have deleted the restriction from this item"),

        COMMAND_ITEMRESTRICT_EDIT_NOTHINGINHAND("%TAG %E%EYou must have an item in your hand to use this command"),
        COMMAND_ITEMRESTRICT_EDIT_NORESTRICTION("%TAG %EYou cannot edit this item as there is no restriction against it"),
        COMMAND_ITEMRESTRICT_EDIT_NOTNUMBER("%TAG %EThe level you entered is not a number"),
        COMMAND_ITEMRESTRICT_EDIT_EDITED("%TAG %GThe restriction level of this item has been edited");

        public Object value;

        MessagesValues(Object value) {
            this.value = value;
        }
    }
}