package co.uk.randompanda30.arearestrict.command.handle;

import co.uk.randompanda30.arearestrict.AreaRestrict;
import org.bukkit.command.CommandSender;

/**
 * Created by panda on 05/07/16.
 *
 *
 *   Copyright 2016 JPanda (Connor Brady)
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

public abstract class Command {

    private String help;
    private String permission;
    private String usage;

    private boolean console;

    private AreaRestrict plugin;

    protected Command(String usage, String permission, String help, boolean console) {
        plugin = AreaRestrict.getPlugin();

        this.usage = usage;
        this.permission = permission;
        this.help = help;

        this.console = console;
    }

    public String getUsage() {
        return usage;
    }

    public String getPermission() {
        return permission;
    }

    public String getHelp() {
        return help;
    }

    protected boolean isConsoleAllowed() {
        return console;
    }

    public abstract boolean runCommand(CommandSender sender, String[] args);
}