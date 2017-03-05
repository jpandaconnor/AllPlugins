package co.uk.randompanda30.fileguilds.object;

import java.util.LinkedHashMap;

public class Rank {

    String name;
    String title;

    String org;

    boolean kick;
    boolean invite;
    boolean gchat;
    boolean achat;

    boolean ally;
    boolean unally;
    boolean allyaccept;
    boolean allydeny;

    boolean rank_set;
    boolean rank_title;
    boolean rank_create;
    boolean rank_delete;
    boolean rank_perms;

    boolean plot_create;
    boolean plot_delete;
    boolean plot_home;
    boolean plot_build;
    boolean plot_tp;
    boolean plot_reset;

    boolean war_declare;
    boolean war_surrender;

    boolean pvp;
    boolean motd;
    boolean transowner;

    LinkedHashMap<String, Boolean> toMap = new LinkedHashMap<>();

    public Rank(String rank) {
        this.org = rank;

        name = rank.split(":")[0];
        title = rank.split(":")[1];
        String[] one = rank.split(":");
        String[] two = one[2].split("!");

        // Kick;false
        // gchat;true

        for (String s : two) {

            if (s.equals("")) continue;

            switch (s.split(";")[0]) {

                /*
                Player stuff here
                 */

                case "Kick":
                    kick = Boolean.parseBoolean(s.split(";")[1]);
                    toMap.put("Kick", kick);
                    break;
                case "Invite":
                    invite = Boolean.parseBoolean(s.split(";")[1]);
                    toMap.put("Invite", invite);
                    break;
                case "GChat":
                    gchat = Boolean.parseBoolean(s.split(";")[1]);
                    toMap.put("GChat", gchat);
                    break;
                case "AChat":
                    achat = Boolean.parseBoolean(s.split(";")[1]);
                    toMap.put("AChat", achat);
                    break;

                /*
                Guild things
                 */

                case "Ally":
                    ally = Boolean.parseBoolean(s.split(";")[1]);
                    toMap.put("Ally", ally);
                    break;
                case "Unally":
                    unally = Boolean.parseBoolean(s.split(";")[1]);
                    toMap.put("Unally", unally);
                    break;
                case "AllyAccept":
                    allyaccept = Boolean.parseBoolean(s.split(";")[1]);
                    toMap.put("Ally Accept", allyaccept);
                    break;
                case "AllyDeny":
                    allydeny = Boolean.parseBoolean(s.split(";")[1]);
                    toMap.put("Ally Deny", allydeny);
                    break;
                /*
                Rank things
                 */

                case "RankSet":
                    rank_set = Boolean.parseBoolean(s.split(";")[1]);
                    toMap.put("Rank Set", rank_set);
                    break;
                case "RankTitle":
                    rank_title = Boolean.parseBoolean(s.split(";")[1]);
                    toMap.put("Rank Title", rank_title);
                    break;
                case "RankCreate":
                    rank_create = Boolean.parseBoolean(s.split(";")[1]);
                    toMap.put("Rank Create", rank_create);
                    break;
                case "RankDelete":
                    rank_delete = Boolean.parseBoolean(s.split(";")[1]);
                    toMap.put("Rank Delete", rank_delete);
                    break;
                case "RankPerms":
                    rank_perms = Boolean.parseBoolean(s.split(";")[1]);
                    toMap.put("Rank Perms", rank_perms);
                    break;

                /*
                Plot stuff
                 */

                case "PlotCreate":
                    plot_create = Boolean.parseBoolean(s.split(";")[1]);
                    toMap.put("Plot Create", plot_create);
                    break;
                case "PlotDelete":
                    plot_delete = Boolean.parseBoolean(s.split(";")[1]);
                    toMap.put("Plot Delete", plot_delete);
                    break;
                case "PlotHome":
                    plot_home = Boolean.parseBoolean(s.split(";")[1]);
                    toMap.put("Plot Home", plot_home);
                    break;
                case "PlotBuild":
                    plot_build = Boolean.parseBoolean(s.split(";")[1]);
                    toMap.put("Plot Build", plot_build);
                    break;
                case "PlotTP":
                    plot_tp = Boolean.parseBoolean(s.split(";")[1]);
                    toMap.put("Plot TP", plot_tp);
                    break;
                case "PlotReset":
                    plot_reset = Boolean.parseBoolean(s.split(";")[1]);
                    toMap.put("Plot Reset", plot_reset);
                    break;
                /*
                War things
                 */

                case "WarDeclare":
                    war_declare = Boolean.parseBoolean(s.split(";")[1]);
                    toMap.put("War Declare", war_declare);
                    break;
                case "WarSurrender":
                    war_surrender = Boolean.parseBoolean(s.split(";")[1]);
                    toMap.put("War Surrender", war_surrender);
                    break;

                /*
                Other guild things
                 */

                case "PVP":
                    pvp = Boolean.parseBoolean(s.split(";")[1]);
                    toMap.put("PVP", pvp);
                    break;
                case "MOTD":
                    motd = Boolean.parseBoolean(s.split(";")[1]);
                    toMap.put("MOTD", motd);
                    break;
                case "TransferOwnership":
                    transowner = Boolean.parseBoolean(s.split(";")[1]);
                    toMap.put("Transfer Ownership", transowner);
                    break;
            }
        }
    }

    public LinkedHashMap<String, Boolean> getMap() {
        return toMap;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getOrg() {
        return org;
    }

    public boolean canKick() {
        return kick;
    }

    public boolean canInvite() {
        return invite;
    }

    public boolean canGChat() {
        return gchat;
    }

    public boolean canAChat() {
        return achat;
    }

    public boolean canAlly() {
        return ally;
    }

    public boolean canUnally() {
        return unally;
    }

    public boolean canRankSet() {
        return rank_set;
    }

    public boolean canRankTitle() {
        return rank_title;
    }

    public boolean canRankCreate() {
        return rank_create;
    }

    public boolean canRankDelete() {
        return rank_delete;
    }

    public boolean canRankPerms() {
        return rank_perms;
    }

    public boolean canPlotCreate() {
        return plot_create;
    }

    public boolean canPlotDelete() {
        return plot_delete;
    }

    public boolean canPlotHome() {
        return plot_home;
    }

    public boolean canPlotBuild() {
        return plot_build;
    }

    public boolean canPlotTP() {
        return plot_tp;
    }

    public boolean canPlotReset() {
        return plot_reset;
    }

    public boolean canWarDeclare() {
        return war_declare;
    }

    public boolean canWarSurrender() {
        return war_surrender;
    }

    public boolean canPVP() {
        return pvp;
    }

    public boolean canMOTD() {
        return motd;
    }

    public boolean canTransferOwnership() {
        return transowner;
    }

    public boolean canAcceptAlly() {
        return allyaccept;
    }

    public boolean canDenyAlly() {
        return allydeny;
    }

}