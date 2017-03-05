package co.uk.randompanda30.sao.events.handle;

/*
   Created by Connor Brady on 13/10/2016.

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

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ICECheck {

    InventoryClickEvent event;

    public ICECheck(InventoryClickEvent event) {
        this.event = event;
    }

    public boolean check() {
        if (event.getCurrentItem() == null) {
            return false;
        }

        if (event.getCurrentItem().getType().equals(Material.AIR)) {
            return false;
        }
        return true;
    }
}