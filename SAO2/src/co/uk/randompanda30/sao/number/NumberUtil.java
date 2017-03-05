package co.uk.randompanda30.sao.number;

/* 
   Created by panda on 18/08/16.
   
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

import java.util.ArrayList;
import java.util.Collections;

public class NumberUtil {

    public static boolean isNumber(String number) {
        try {
            Integer.valueOf(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static int getNumber(String number) {
        try {
            return Integer.valueOf(number);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public int getMax(ArrayList<String> list) {
        ArrayList<Integer> newList = new ArrayList<>();
        for (String s : list) {
            newList.add(Integer.parseInt(s));
        }
        return Collections.max(newList);
    }
}