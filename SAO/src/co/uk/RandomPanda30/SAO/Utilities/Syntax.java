package co.uk.RandomPanda30.SAO.Utilities;

import co.uk.RandomPanda30.SAO.Objects.Sender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Syntax {

    public Syntax(String command, String correctSyntax, Player player) {
        String message = "%EIncorrect syntax for %A" + command + "%E. Correct syntax: %G" + correctSyntax;
        if (player == null) {
            Dispatch.sendMessage(message, null);
        } else {
            Dispatch.sendMessage(message, player);
        }
    }

    public Syntax(String command, String correctSyntax, Sender sender) {
        String message = "%EIncorrect syntax for %A" + command + "%E. Correct syntax: %G" + correctSyntax;
        if (sender.isPlayer) {
            Dispatch.sendMessage(message, null);
        } else {
            Dispatch.sendMessage(message, sender.getPlayer());
        }
    }

    public Syntax(String command, ArrayList<String> syntaxOptions, Player player) {
        String message = "%NCommand options for: %A" + command;
        if (player == null) {
            Dispatch.sendMessage(message, null);
            for (String s : syntaxOptions) {
                Dispatch.sendMessage("%A" + s, null);
            }
        } else {
            Dispatch.sendMessage(message, player);
            for (String s : syntaxOptions) {
                Dispatch.sendMessage("%A" + s, player);
            }
        }
    }

    public Syntax(String command, ArrayList<String> syntaxOptions, Sender sender) {
        String message = "%NCommand options for: %A" + command;
        if (sender.isPlayer) {
            Dispatch.sendMessage(message, sender.getPlayer());
            for (String s : syntaxOptions) {
                Dispatch.sendMessage("%A" + s, sender.getPlayer());
            }
        } else {
            Dispatch.sendMessage(message, null);
            for (String s : syntaxOptions) {
                Dispatch.sendMessage("%A" + s, null);
            }
        }
    }
}