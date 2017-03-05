package co.uk.randompanda30.sao;

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

import co.uk.randompanda30.sao.modules.enhancement.Booster;
import co.uk.randompanda30.sao.modules.housing.HouseEditor;
import co.uk.randompanda30.sao.modules.party.Party;
import co.uk.randompanda30.sao.view.data.ViewProcedure;
import co.uk.randompanda30.sao.view.selection.SELECT_DISTRICT;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class TEMP {

    public static File config;
    public static FileConfiguration configf;
    public static ConfigurationSection configc;

    public static File messages;
    public static FileConfiguration messagesf;
    public static ConfigurationSection messagesc;

    public static File booster;
    public static FileConfiguration boosterf;
    public static ConfigurationSection boosterc;

    public static File levelbound;
    public static FileConfiguration levelboundf;
    public static ConfigurationSection levelboundc;

    public static File playerdata;
    public static FileConfiguration playerdataf;
    public static ConfigurationSection playerdatac;

    public static File backup;
    public static FileConfiguration backupf;
    public static ConfigurationSection backupc;

    public static File playerhousingdata;
    public static FileConfiguration playerhousingdataf;
    public static ConfigurationSection playerhousingdatac;

    public static File playerhousingdis;
    public static FileConfiguration playerhousingdisf;
    public static ConfigurationSection playerhousingdisc;

    public static Booster currentBooster = null;
    public static boolean showingBoosterMessage = false;

    public static HashMap<ViewProcedure, ArrayList<UUID>> viewonwards = new HashMap<>();
    public static HashMap<UUID, SELECT_DISTRICT> selectionDistricts = new HashMap<>();

    public static ArrayList<UUID> playerdata_beingreset = new ArrayList<>();
    public static ArrayList<Party> parties = new ArrayList<>();
    public static ArrayList<HouseEditor> editingHouses = new ArrayList<>();

    public static long restartcounter = 0;

    public static boolean isBackingup = false;
    public static boolean pendingRestart = false;

    public static HashMap<String[], String> questions;
}