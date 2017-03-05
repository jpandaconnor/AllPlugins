package co.uk.randompanda30.sao.modules.housing;

/* 
   Created by panda on 31/08/16.
   
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

import java.util.Calendar;
import java.util.UUID;

public class HouseHomeQuery {

    public static boolean isHouse(String house) {
        return TEMP.playerhousingdatac.contains(house);
    }

    public static HouseState getHouseState(String house) {
        return HouseStateParse.stateFromString(TEMP.playerhousingdatac.getString(house + ".state"));
    }

    public static UUID getHomeOwner(String house) {
        return UUID.fromString(TEMP.playerhousingdatac.getString(house + ".owner"));
    }

    public static int getHomePrice(String house) {
        return TEMP.playerhousingdatac.getInt(house + ".price");
    }

    public static long getHomeTime(String house) {
        return TEMP.playerhousingdatac.getLong(house + ".time");
    }

    public static long getHomeTimeInDays(String house) {
        long t = getHomeTime(house);
        long d = 0;
        long ct = Calendar.getInstance().getTimeInMillis();
        long ft = t - ct;

        d = ft / 1000 / 60 / 60 / 24;
        return d;
    }
}