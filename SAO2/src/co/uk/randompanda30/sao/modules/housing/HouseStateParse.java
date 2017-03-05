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

public class HouseStateParse {

    public static HouseState stateFromString(String state) {
        HouseState st = null;

        switch (state) {
            case "FORSALE":
                st = HouseState.FORSALE;
                break;
            case "SOLD":
                st = HouseState.SOLD;
                break;
        }

        return st;
    }

    public static String stringFromState(HouseState state) {
        switch (state) {
            case FORSALE:
                return "FORSALE";
            case SOLD:
                return "SOLD";
        }
        return null;
    }
}