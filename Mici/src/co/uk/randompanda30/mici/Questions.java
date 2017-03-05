package co.uk.randompanda30.mici;

/* 
   Created by panda on 18/07/16.
   
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

import java.util.HashMap;

public enum Questions {

    // How do I create a new guild
    // How do I make a new guild

    /*
    General
     */

    OWNER_WHO(new String[] {
            "who", "owns", "server"
    }, "The owner of the server is BrightPrimates. The co-owner is leolele"),

    OWNER_WHO2(new String[] {
            "who", "is", "owner"
    }, "The owner of the server is BrightPrimates. The co-owner is leolele"),

    MODPACK_OWNER(new String[] {
            "who", "owns", "modpack"
    }, "Leolele is the owner of the modpack"),

    MODPACK_OWNER2(new String[] {
        "who", "made", "modpack"
    }, "Leolele is the owner of the modpack"),

    BUY_COINS(new String[] {
            "where", "buy", "coins"
    }, "You can buy Ancient Coins at: http://donate.ancientrpg.net/"),

    BUY_BOOSTERS(new String[] {
            "where", "buy", "boosters"
    }, "You can buy XP Boosters at: http://donate.ancientrpg.net/"),

    BUY_BOOSTER(new String[] {
            "where", "buy", "booster"
    }, "You can buy XP Boosters at: http://donate.ancientrpg.net/"),

    BUY_BEATER(new String[] {
            "where", "buy", "beater"
    }, "You can buy Beater ranks at: http://donate.ancientrpg.net/"),

    BUY_BEATERPLUS(new String[] {
            "where", "buy", "beaterplus"
    }, "You can buy Beater ranks at: http://donate.ancientrpg.net/"),

    BUY_BEATERPLUS2(new String[] {
            "where", "buy", "beater+"
    }, "You can buy Beater ranks at: http://donate.ancientrpg.net/"),

    COST_BOOSTER(new String[] {
            "how", "much", "booster",
    }, "Boosters cost $3.99 each"),

    COST_BOOSTER2(new String[] {
            "how", "much",  "boostesr"
    }, "Boosters cost $3.99 each"),

    COST_AC(new String[] {
            "how", "much", "ancient", "coin"
    }, "Ancient coins cost $0.20 each"),

    COST_AC2(new String[] {
            "how", "much", "ac",
    }, "Ancient coins cost $0.20 each"),

    COST_BEATER(new String[] {
            "how", "much", "beater"
    }, "The Beater rank costs $8.99 and the Beater+ rank costs $16.99"),

    COST_BEATERPLUS(new String[] {
            "how", "much", "beaterplus"
    }, "The Beater+ rank costs $16.99"),

    COST_BEATERPLUS2(new String[] {
            "how", "much", "beater+"
    }, "The Beater+ rank costs $16.99"),

    FORUMS_WHERE(new String[] {
            "where", "forums"
    }, "The forums can be found at: http://ancientrpg.net/"),

    BUG_FOUND(new String[] {
            "found", "bug"
    }, "You can report bugs here: http://swordart.ancientrpg.net/index.php?forums/server-bug-exploit-reports.8/"),

    EXPLOIT_FOUND(new String[] {
        "found", "exploit"
    }, "You can report exploits here: http://swordart.ancientrpg.net/index.php?forums/server-bug-exploit-reports.8/"),

    /*
    Fun
     */

    HAVEITEMS(new String[] {
        "can", "have", "items"
    }, "Nah"),

    /*
    Guilds
     */

    GUILD_CREATE(new String[] {
       "how", "create", "guild"
    }, "You can create a new guild by doing /guild create and following the format shown. You must have the beater rank" +
            " to do this"),

    GUILD_CREATE2(new String[] {
        "how", "make", "guild"
    }, "You can create a new guild by doing /guild create and following the format shown. You must have the beater rank" +
            " to do this"),

    /*
    Housing
     */

    HOUSE_EXTENDTIME(new String[] {
            "how", "add", "time", "house"
    }, "You can add more rent time to your house by doing /houseshop menu and adding a day! Remember that the cost" +
            " of adding a day is the same cost as you bought the house"),

    HOUSE_BUY(new String[] {
        "how", "buy", "house"
    }, "At the moment housing is pending an update. Please try again in a few days!"),

    HOUSE_BUY2(new String[] {
            "how", "get", "house"
    }, "At the moment housing is pending an update. Please try again in a few days!"),

    /*
    Server related
     */

    GORGON_WHERE(new String[] {
            "where", "gorgon"
    }, "Gorgon is found in the swamp dungeon");


    public static void initList() {
        HashMap<String[], String> list = new HashMap<>();

        /*
        General
         */

        list.put(Questions.OWNER_WHO.value, Questions.OWNER_WHO.response);
        list.put(Questions.OWNER_WHO2.value, Questions.OWNER_WHO2.response);
        list.put(Questions.MODPACK_OWNER.value, Questions.MODPACK_OWNER.response);
        list.put(Questions.MODPACK_OWNER2.value, Questions.MODPACK_OWNER2.response);
        list.put(Questions.COST_BOOSTER.value, Questions.COST_BOOSTER.response);
        list.put(Questions.COST_BOOSTER2.value, Questions.COST_BOOSTER2.response);
        list.put(Questions.COST_AC.value, Questions.COST_AC.response);
        list.put(Questions.COST_AC2.value, Questions.COST_AC2.response);
        list.put(Questions.COST_BEATER.value, Questions.COST_BEATER.response);
        list.put(Questions.COST_BEATERPLUS.value, Questions.COST_BEATERPLUS.response);
        list.put(Questions.COST_BEATERPLUS2.value, Questions.COST_BEATERPLUS2.response);
        list.put(Questions.FORUMS_WHERE.value, Questions.FORUMS_WHERE.response);
        list.put(Questions.BUG_FOUND.value, Questions.BUG_FOUND.response);
        list.put(Questions.EXPLOIT_FOUND.value, Questions.EXPLOIT_FOUND.response);
        list.put(Questions.BUY_COINS.value, Questions.BUY_COINS.response);
        list.put(Questions.BUY_BOOSTERS.value, Questions.BUY_BOOSTERS.response);
        list.put(Questions.BUY_BOOSTER.value, Questions.BUY_BOOSTER.response);
        list.put(Questions.BUY_BEATER.value, Questions.BUY_BEATER.response);
        list.put(Questions.BUY_BEATERPLUS.value, Questions.BUY_BEATERPLUS.response);
        list.put(Questions.BUY_BEATERPLUS2.value, Questions.BUY_BEATERPLUS2.response);

        /*
        Fun
         */

        list.put(Questions.HAVEITEMS.value, Questions.HAVEITEMS.response);

        /*
        Adding guilds questions here
         */

        list.put(Questions.GUILD_CREATE.value, Questions.GUILD_CREATE.response);
        list.put(Questions.GUILD_CREATE2.value, Questions.GUILD_CREATE.response);

        /*
        Housing
         */

        list.put(Questions.HOUSE_BUY.value, Questions.HOUSE_BUY.response);
        list.put(Questions.HOUSE_BUY2.value, Questions.HOUSE_BUY2.response);
        list.put(Questions.HOUSE_EXTENDTIME.value, Questions.HOUSE_EXTENDTIME.response);

        /*
        Server related
         */

        list.put(Questions.GORGON_WHERE.value, Questions.GORGON_WHERE.response);

        TEMP.questions = list;
    }

    public String[] value;
    public String response;

    Questions(String[] value, String response) {
        this.value = value;
        this.response = response;
    }
}