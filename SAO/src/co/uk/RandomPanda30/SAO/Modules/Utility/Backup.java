package co.uk.RandomPanda30.SAO.Modules.Utility;

import co.uk.RandomPanda30.SAO.Config.Config.ConfigValues;
import co.uk.RandomPanda30.SAO.Config.Messages;
import co.uk.RandomPanda30.SAO.Objects.Sender;
import co.uk.RandomPanda30.SAO.SAO;
import co.uk.RandomPanda30.SAO.Utilities.Dispatch;
import co.uk.RandomPanda30.SAO.Utilities.No;
import co.uk.RandomPanda30.SAO.Utilities.String.Format;
import co.uk.RandomPanda30.SAO.Utilities.Syntax;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class Backup implements CommandExecutor {

    public static boolean inProgress = false;
    public SAO plugin;

    public File backupFolder;

    public File logFile;

    public String time = "";

    public double size = 0;
    public double files = 0;

    public int p = 0;
    public int lp = -1;

    public long delay = 0L;

    public Backup(SAO plugin) {
        this.plugin = plugin;

        if ((Boolean) ConfigValues.MODULES_BACKUP_ENABLED.value) {
            plugin.getCommand("backup").setExecutor(this);

            SimpleDateFormat f = new SimpleDateFormat("dd-MMM-yyyy-HH:mm:ss-z");
            f.setTimeZone(TimeZone.getTimeZone("Europe/London"));
            time = f.format(GregorianCalendar.getInstance().getTime());

            backupFolder = new File(ConfigValues.MODULES_BACKUP_PATH_BACKUP.value.toString() + (ConfigValues.MODULES_BACKUP_PATH_LOGS.value.toString().endsWith("/") ? "" : "/"));
            logFile = new File(ConfigValues.MODULES_BACKUP_PATH_LOGS.value + (ConfigValues.MODULES_BACKUP_PATH_LOGS.value.toString().endsWith("/") ? "" : "/") + time + ".log");


            if (!backupFolder.exists()) {
                backupFolder.mkdir();
            }

            if (!logFile.exists()) {
                try {
                    logFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (!co.uk.RandomPanda30.SAO.Config.Modules.Backup.backupf.contains("lastbackup")) {
                if (!inProgress) {
                    co.uk.RandomPanda30.SAO.Config.Modules.Backup.backupf.set("lastbackup", System.currentTimeMillis());
                    co.uk.RandomPanda30.SAO.Config.Modules.Backup.save();
                    backup();
                }
            } else {
                String time = (String) ConfigValues.MODULES_BACKUP_BACKUPDELAY.value;
                String[] splitter = {time};

                int days = 0;
                int hours = 0;
                int minutes = 0;
                int seconds = 0;

                if (!time.contains("d") && !time.contains("h") && !time.contains("m")
                        && !time.contains("s")) {
                    if (No.isNumber(time)) {
                        minutes = No.getNumber(time);
                    } else {
                        time = "0";
                    }
                } else {
                    if (time.contains("d")) {
                        splitter = splitter[0].split("d");
                        days = No.getNumber(splitter[0]);
                        if (splitter.length == 2) {
                            splitter[0] = splitter[1];
                        }
                    }

                    if (time.contains("h")) {
                        splitter = splitter[0].split("h");
                        hours = No.getNumber(splitter[0]);
                        if (splitter.length == 2) {
                            splitter[0] = splitter[1];
                        }
                    }

                    if (time.contains("m")) {
                        splitter = splitter[0].split("m");
                        minutes = No.getNumber(splitter[0]);
                        if (splitter.length == 2) {
                            splitter[0] = splitter[1];
                        }
                    }

                    if (time.contains("s")) {
                        splitter = splitter[0].split("s");
                        seconds = No.getNumber(splitter[0]);
                    }

                    if (time.equals("0")) {
                        // Message here
                    }

                    delay = (days * 1000 * 60 * 60 * 24) + (hours * 1000 * 60 * 60)
                            + (minutes * 1000 * 60) + (seconds * 1000);
                }
            }
            startTimer();
        } else {
            Dispatch.sendMessage(Format.format(Messages.MessagesValues.MODULE_OFF.value.toString().replace("mod", "Backup")), null);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(Boolean) ConfigValues.MODULES_BACKUP_ENABLED.value) {
            sender.sendMessage(Format.format(Messages.MessagesValues.MODULE_OFF.value.toString().replace("mod", "Backup")));
            return true;
        }

        Sender s = new Sender(sender);

        if (args.length != 1) {
            new Syntax("/backup", new ArrayList<String>() {
                {
                    add("/backup create - Creates a backup");
                    add("/backup stop - Stops a backup");
                }
            }, s);
            return true;
        }

        switch (args[0]) {
            case "create":
                if (s.isPlayer) {
                    if (!s.getPlayer().hasPermission("sao.backup.create")) {
                        Dispatch.sendMessage((String) Messages.MessagesValues.NOPERM.value, s.getPlayer());
                        return true;
                    }
                }
                backup();
                break;
        }
        return true;
    }

    private void startTimer() {
        if ((Boolean) ConfigValues.MODULES_BACKUP_AUTOMATICBACKUP.value) {
            new BukkitRunnable() {
                public void run() {
                    if (System.currentTimeMillis() - co.uk.RandomPanda30.SAO.Config.Modules.Backup.backupf.getLong("lastbackup") >= delay) {
                        co.uk.RandomPanda30.SAO.Config.Modules.Backup.backupf.set("lastbackup", System.currentTimeMillis());
                        co.uk.RandomPanda30.SAO.Config.Modules.Backup.save();
                        if (!inProgress) {
                            backup();
                        }
                    }
                }
            }.runTaskTimer(plugin, 500L, 72000L);
        }
    }

    private void backup() {
        inProgress = true;

        Dispatch.sendMessage((String) Messages.MessagesValues.BACKUP_STARTED.value, null);

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.hasPermission("sao.backup.notification")) {
                Dispatch.sendMessage((String) Messages.MessagesValues.BACKUP_STARTED.value, player);
            }
        }

        Thread t = new Thread(new Runnable() {
            boolean nc = false;

            @Override
            public void run() {
                String s = "tar " + "--exclude=" + plugin.getServer().getWorldContainer().getAbsolutePath().replace("/", "") + "/plugins -czvf " +
                        ConfigValues.MODULES_BACKUP_PATH_BACKUP.value +
                        (ConfigValues.MODULES_BACKUP_PATH_BACKUP.value.toString().endsWith("/") ? "" : "/") +
                        time + ".tar.gz " + ConfigValues.MODULES_BACKUP_PATH_FOLDER.value;
                Bukkit.broadcastMessage(ChatColor.BLUE + s);

                startTimer();

                File file = new File((String) ConfigValues.MODULES_BACKUP_PATH_FOLDER.value);
                for (File f : FileUtils.listFiles(file, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE))
                    size++;

                try {
                    Process process = Runtime.getRuntime().exec(s);
                    InputStreamReader is = new InputStreamReader(process.getInputStream());
                    BufferedReader buff = new BufferedReader(is);

                    FileOutputStream fos = new FileOutputStream(logFile);
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

                    String line;
                    while ((line = buff.readLine()) != null) {
                        files++;

                        bw.write(line);
                        bw.newLine();

                        updatePercentage();
                    }
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();

                }
                nc = true;

                Dispatch.sendMessage((String) Messages.MessagesValues.BACKUP_STOPPED.value, null);

                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.hasPermission("sao.backup.notification")) {
                        Dispatch.sendMessage((String) Messages.MessagesValues.BACKUP_STOPPED.value, player);
                    }
                }
            }

            private void updatePercentage() {
                double d = ((double) files / size) * 100;
                p = (int) d;
            }

            private void startTimer() {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (!nc) {
                            String m = (String) Messages.MessagesValues.BACKUP_PERCENTAGE.value;
                            m = m.replace("%perc", Integer.toString(p));
                            m = m.replace("%done", Integer.toString((int) files));
                            m = m.replace("%files", Integer.toString((int) size));

                            if (p != lp) {
                                lp = p;

                                Dispatch.sendMessage(m, null);

                                for (Player player : Bukkit.getOnlinePlayers()) {
                                    if (player.hasPermission("sao.backup.notification")) {
                                        Dispatch.sendMessage(m, player);
                                    }
                                }
                            }
                        } else {
                            inProgress = false;
                            this.cancel();
                        }
                    }
                }.runTaskTimer(plugin, 0L, 100L);
            }
        });
        t.start();
    }
}