package co.uk.randompanda30.sao.view.data;

/* 
   Created by panda on 16/08/16.
   
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

public enum ViewNames {

    SAO_MENU("SAO Menu"),
    HOUSE_MENU("Housing menu"),
    HOUSE_OWNED_MENU("Configure house"),
    HOUSE_UNOWNED_MENU("Buy a house"),

    PARTY_CREATE("Create a party"),
    PARTY_LEADER("Party management"),

    ADMIN_PH("Housing A-Menu"),

    CONFIRMATION_MENU("Are you sure?"),

    SELECTION_DISTRICT_MENU("Select a district (%page)");

    public String value;

    ViewNames(String value) {
        this.value = value;
    }
}