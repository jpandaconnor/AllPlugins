package co.uk.randompanda30.PLUGIN.utilities;

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

import org.bukkit.Location;
import org.bukkit.util.Vector;

public class VectorUtil {

    private Vector p1;
    private Vector p2;

    public VectorUtil (Vector p1, Vector p2) {
        int x1 = Math.min(p1.getBlockX(), p2.getBlockX());
        int y1 = Math.min(p1.getBlockY(), p2.getBlockY());
        int z1 = Math.min(p1.getBlockZ(), p2.getBlockZ());

        int x2 = Math.max(p1.getBlockX(), p2.getBlockX());
        int y2 = Math.max(p1.getBlockY(), p2.getBlockY());
        int z2 = Math.max(p1.getBlockZ(), p2.getBlockZ());
        this.p1 = new Vector(x1, y1, z1);
        this.p2 = new Vector(x2, y2, z2);
    }

    public boolean contains(Location loc) {
        if (loc == null) {
            return false;
        }

        return loc.getBlockX() >= p1.getBlockX()
                && loc.getBlockX() <= p2.getBlockX()
                && loc.getBlockY() >= p1.getBlockY()
                && loc.getBlockY() <= p2.getBlockY()
                && loc.getBlockZ() >= p1.getBlockZ()
                && loc.getBlockZ() <= p2.getBlockZ();
    }
}