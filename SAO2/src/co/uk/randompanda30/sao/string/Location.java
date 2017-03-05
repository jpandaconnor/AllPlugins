package co.uk.randompanda30.sao.string;

/* 
   Created by panda on 20/08/16.
   
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

import org.bukkit.Bukkit;

public class Location {

    /*
    worldname;x;y;z
    0         1 2 3
     */

    public static String decompileLocation(org.bukkit.Location location) {
        String s = location.getWorld().getName() + ";" + Integer.toString((int) location.getX()) + ";" +
                Integer.toString((int) location.getY()) + ";" + Integer.toString((int) location.getZ());
        return s;
    }

    public static org.bukkit.Location compileLocation(String s) {
        String[] ss = s.split(";");
        return new org.bukkit.Location(Bukkit.getWorld(ss[0]),
                Integer.parseInt(ss[1]), Integer.parseInt(ss[2]), Integer.parseInt(ss[3]));
    }
}