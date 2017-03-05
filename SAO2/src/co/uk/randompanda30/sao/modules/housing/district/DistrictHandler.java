package co.uk.randompanda30.sao.modules.housing.district;

/* 
   Created by panda on 02/09/16.
   
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

import co.uk.randompanda30.sao.TEMP;
import co.uk.randompanda30.sao.config.modules.housing.CPlayerHousingDistricts;

import java.util.ArrayList;

public class DistrictHandler {

    public static boolean isDistrict(String name) {
        return TEMP.playerhousingdisc.getStringList("districts").contains(name);
    }

    public static void addDistrct(String name) {
        if (!isAnyDistricts()) {
            ArrayList<String> dis = new ArrayList<>();
            dis.add(name);
            TEMP.playerhousingdisc.set("districts", dis);
            CPlayerHousingDistricts.save();
        } else {
            ArrayList<String> dis = getDistricts();
            dis.add(name);
            TEMP.playerhousingdisc.set("districts", dis);
            CPlayerHousingDistricts.save();
        }
    }

    public static void removeDistrict(String name) {
        ArrayList<String> dis = getDistricts();
        dis.remove(name);
        TEMP.playerhousingdisc.set("districts", dis);
        CPlayerHousingDistricts.save();
    }

    public static boolean isAnyDistricts() {
        return TEMP.playerhousingdisc.contains("districts");
    }

    public static ArrayList<String> getDistricts() {
        return (ArrayList<String>) TEMP.playerhousingdisc.getStringList("districts");
    }
}