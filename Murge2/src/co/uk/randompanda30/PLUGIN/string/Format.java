package co.uk.randompanda30.PLUGIN.string;

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
import static co.uk.randompanda30.PLUGIN.config.Messages.MessagesValues.*;

public class Format {

    public static String format(String message) {
        return message.replaceAll("%TAG", (String) TAG.value)
                .replaceAll("%N", (String) NORMAL.value)
                .replaceAll("%W", (String) WARNING.value)
                .replaceAll("%E", (String) ERROR.value)
                .replaceAll("%A", (String) ARG.value)
                .replaceAll("%H", (String) HEADER.value)
                .replaceAll("%G", (String) GOOD.value)
                .replaceAll("%B", (String) BAD.value)
                .replaceAll("(&([a-fk-or0-9]))", "\u00A7$2")
                .replaceAll("&u", "\n");
    }
}