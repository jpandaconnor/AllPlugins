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

public enum PATHS {

    BOOSTER_DUMP("dump"),

    BOOSTER_NAME("dump.name"),
    BOOSTER_UUID("dump.uuid"),

    BOOSTER_ST("dump.st"),
    BOOSTER_DT("dump.dt"),
    BOOSTER_ET("dump.et"),

    BOOSTER_D("dump.d"),
    BOOSTER_P("dump.p");

    public Object value;

    PATHS(Object value) {
        this.value = value;
    }
}