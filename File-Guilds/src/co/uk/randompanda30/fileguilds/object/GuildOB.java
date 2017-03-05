package co.uk.randompanda30.fileguilds.object;

import co.uk.randompanda30.fileguilds.Guilds;
import co.uk.randompanda30.fileguilds.config.Data;
import co.uk.randompanda30.fileguilds.config.Guild;
import co.uk.randompanda30.fileguilds.data.GuildData;
import co.uk.randompanda30.fileguilds.object.invite.GInvite;
import co.uk.randompanda30.fileguilds.object.invite.Invite;
import co.uk.randompanda30.fileguilds.plot.Plot;
import co.uk.randompanda30.fileguilds.plot.PlotHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static co.uk.randompanda30.fileguilds.TEMP.guildsc;
import static co.uk.randompanda30.fileguilds.TEMP.pdatac;

public class GuildOB {

    private String name;

    public GuildOB(String name) {
        this.name = name;
    }

    public List<String> getMembers() {
        return (List<String>) guildsc.get(name + ".members");
    }

    public String getRankString() {
        return (String) guildsc.get(name + ".ranks");
    }

    private List<String> getRankStrings() {
        String[] rs = getRankString().split("\\$");
        return new ArrayList<>(Arrays.asList(rs));
    }

    public String getTag() {
        return (String) guildsc.get(name + ".tag");
    }

    public String getOwnerUUID() {
        return (String) guildsc.get(name + ".uuid");
    }

    public void setOwner(Player player) {
        if (pdatac.contains(getOwnerUUID())) {
            pdatac.set(getOwnerUUID(), null);
        }

        kickPlayer(UUID.fromString(getOwnerUUID()));

        guildsc.set(name + ".uuid", player.getUniqueId().toString());
        guildsc.set(name + ".name", player.getName());

        Guild.save();
        Data.save();
    }

    public String getMOTD() {
        return (String) guildsc.get(name + ".motd");
    }

    public void setMOTD(String message) {
        guildsc.set(name + ".motd", message);
        Guild.save();
    }

    public void togglePVP() {
        if (getPVP()) {
            guildsc.set(name + ".pvp", false);
        } else {
            guildsc.set(name + ".pvp", true);
        }
        Guild.save();
    }

    public boolean getPVP() {
        return (boolean) guildsc.get(name + ".pvp");
    }

    public ArrayList<Rank> getRanks() {
        ArrayList<Rank> ranks = new ArrayList<>();
        for (String rank : getRankStrings()) {
            ranks.add(new Rank(rank));
        }
        return ranks;
    }

    public boolean isRank(String rank) {
        boolean is = false;
        for (Rank r : getRanks()) {
            if (r.getName().equals(rank)) {
                is = true;
                break;
            }
        }
        return is;
    }

    public Rank getRank(String rank) {
        if (!isRank(rank)) {
            return null;
        }
        Rank newRank = null;
        for (Rank r : getRanks()) {
            if (r.getName().equals(rank)) {
                newRank = r;
            }
        }
        return newRank;
    }

    public Plot getPlot() {
        PlotHandler plotHandler = new PlotHandler(name);
        return new Plot(plotHandler.getWorld(), new Location(Bukkit.getServer().getWorld(plotHandler.getWorld()),
                plotHandler.getCo('X', 1), plotHandler.getCo('Y', 1), plotHandler.getCo('Z', 1)),
                new Location(Bukkit.getServer().getWorld(plotHandler.getWorld()), plotHandler.getCo('X', 2),
                        plotHandler.getCo('Y', 2), plotHandler.getCo('Z', 2)),
                new Location(Bukkit.getServer().getWorld(plotHandler.getWorld()), plotHandler.getCo('X', 3),
                        plotHandler.getCo('Y', 3), plotHandler.getCo('Z', 3)));
    }

    public String getName() {
        return name;
    }

    public void kickPlayer(UUID uuid) {
        List<String> members = (List<String>) guildsc.get(name + ".members");
        if (members.contains(uuid.toString())) {
            members.remove(uuid.toString());
        }

        guildsc.set(name + ".members", members);
        pdatac.set(uuid.toString(), null);

        Guild.save();
        Data.save();
    }

    public void kickPlayer(Player player) {
        List<String> members = (List<String>) guildsc.get(name + ".members");
        if (members.contains(player.getUniqueId().toString())) {
            members.remove(player.getUniqueId().toString());
        }

        guildsc.set(name + ".members", members);
        pdatac.set(player.getUniqueId().toString(), null);

        Guild.save();
        Data.save();
    }

    public void invitePlayer(Player sender, Player target) {
        new Invite(name, sender, target, Guilds.getPlugin());
    }

    public void inviteGuild(String guild, String guild2) {
        new GInvite(guild, guild2, Guilds.getPlugin());
    }

    public void addPlayer(Player player) {
        addPlayer(player.getUniqueId());
    }

    public void addPlayer(UUID uuid) {
        pdatac.set(uuid.toString() + ".guild", name);
        pdatac.set(uuid.toString() + ".rank", "Rookie");

        ArrayList<String> members2 = new ArrayList<>(getMembers());
        members2.add(uuid.toString());

        guildsc.set(name + ".members", members2);

        Guild.save();
        Data.save();
    }

    public List<String> getAllies() {
        if (!guildsc.contains(name + ".allies")) {
            return null;
        }
        return (List<String>) guildsc.get(name + ".allies");
    }

    public List<String> getEnemys() {
        if (!guildsc.contains(name + ".ens")) {
            return null;
        }
        return (List<String>) guildsc.get(name + ".ens");
    }

    public boolean isAlly(String guild) {
        boolean t = false;
        ArrayList<String> allies = new ArrayList<>();
        if (getAllies() != null) {
            for (String s : new ArrayList<>(getAllies())) {
                allies.add(s);
            }
            if (allies.contains(guild)) {
                t = true;
            }
        }
        return t;
    }

    public void allyGuild(final String guild) {
        ArrayList<String> allies = new ArrayList<>();
        if (getAllies() != null) {
            for (String s : new ArrayList<>(getAllies())) {
                allies.add(s);
            }

            guildsc.set(name + ".allies", allies);
        } else {
            allies.add(name);

            guildsc.set(name + ".allies", allies);
        }
        Guild.save();
    }

    public void declareWar(final String guild) {
        ArrayList<String> ens = new ArrayList<>();
        if (getEnemys() != null) {
            for (String s : new ArrayList<>(getEnemys())) {
                ens.add(s);
            }

            guildsc.set(name + ".ens", ens);
        } else {
            ens.add(name);
            guildsc.set(name + ".ens", ens);
        }
        Guild.save();
    }

    public void surrenderWar(final String guild) {
        ArrayList<String> ens = new ArrayList<>();
        if (getEnemys() != null) {
            for (String s : new ArrayList<>(getEnemys())) {
                ens.add(s);
            }

            if (ens.contains(guild)) {
                ens.remove(guild);
                guildsc.set(name + ".ens", ens);
            }
        }
        Guild.save();
    }

    public void unallyGuild(String guild) {
        ArrayList<String> allies = new ArrayList<>();
        if (getAllies() != null) {
            for (String s : new ArrayList<>(getAllies())) {
                allies.add(s);
            }

            if (allies.contains(guild)) {
                allies.remove(guild);
                guildsc.set(name + ".allies", allies);
            }
        }
        Guild.save();
    }

    public void addRank(String name, String title) {
        ArrayList<String> ranks = new ArrayList<>(GuildData.getRookieRanks());

        if (ranks.contains("Rookie:Rookie:")) {
            ranks.remove("Rookie:Rookie:");
        }

        ranks.add(0, name + ":" + title + ":");

        String n = this.getRankString();

        n += "$";

        for (String s : ranks) {
            n += s;
        }

        guildsc.set(getName() + ".ranks", n);
        Guild.save();
    }

    public void removeRank(String name) {

    }

    public void removeRank(Rank rank) {
        String n = "$" + rank.getOrg();

        String rankString = this.getRankString();
        String nn = rankString.replace(n, "");

        guildsc.set(this.getName() + ".ranks", nn);
        Guild.save();

        for (String s : pdatac.getConfigurationSection("").getKeys(false)) {
            if (pdatac.get(s + ".guild").equals(this.getName())) {
                if (pdatac.get(s + ".rank").equals(rank.getName())) {
                    pdatac.set(s + ".rank", "Rookie");
                    Data.save();
                }
            }
        }
    }

    public void changeTitle(String rank, String title) {
        String currentTitle = getRank(rank).getTitle();
        String rankString = getRankString();

        String newString = rankString.replace(rank + ":" + currentTitle, rank + ":" + title);
        guildsc.set(getName() + ".ranks", newString);
        Guild.save();
    }

    public boolean hasPlot() {
        return guildsc.contains(name + ".plot");
    }
}