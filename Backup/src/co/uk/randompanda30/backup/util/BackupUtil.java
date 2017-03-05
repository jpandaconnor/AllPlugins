package co.uk.randompanda30.backup.util;

import co.uk.randompanda30.backup.Backup;
import co.uk.randompanda30.backup.TEMP;
import co.uk.randompanda30.backup.config.Config;
import co.uk.randompanda30.backup.config.Messages;
import co.uk.randompanda30.backup.string.Dispatch;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by panda on 20/04/16.
 */
public class BackupUtil {

    static boolean doing = false;

    Backup plugin;

    public File logFile;

    public String time = "";

    public double size = 0;
    public double files = 0;

    public int p = 0;
    public int lp = -1;

    public long delay = 0L;

    String toBackup;
    String toStore;
    String toStoreLogs;

    public BackupUtil(Backup plugin) {
        this.plugin = plugin;

        if(Config.ConfigValues.BACKUP_PATHTOBACKUP.value.toString().equalsIgnoreCase("changeme") ||
                Config.ConfigValues.BACKUP_PATHTOSTORE.value.toString().equalsIgnoreCase("changeme") ||
                Config.ConfigValues.BACKUP_TOSTORELOGS.value.toString().equalsIgnoreCase("changeme")) {
            return;
        }

        toBackup = Config.ConfigValues.BACKUP_PATHTOBACKUP.value + (Config.ConfigValues.BACKUP_PATHTOBACKUP.value.
                toString().endsWith("/") ? "" : "/");
        toStore = Config.ConfigValues.BACKUP_PATHTOSTORE.value + (Config.ConfigValues.BACKUP_PATHTOSTORE.value.
                toString().endsWith("/") ? "" : "/");
        toStoreLogs = Config.ConfigValues.BACKUP_TOSTORELOGS.value + (Config.ConfigValues.BACKUP_TOSTORELOGS.value.
                toString().endsWith("/") ? "" : "/");

        SimpleDateFormat f = new SimpleDateFormat("dd-MMM-yyyy-HH:mm:ss-z");
        f.setTimeZone(TimeZone.getTimeZone("Europe/London"));
        time = f.format(GregorianCalendar.getInstance().getTime());

        if (!new File(toStore).exists()) {
            new File(toStore).mkdir();
        }

        String time = (String) Config.ConfigValues.BACKUP_DELAY.value;
        String[] splitter = {time};

        int days = 0;
        int hours = 0;
        int minutes = 0;
        int seconds = 0;

        if (!time.contains("d") && !time.contains("h") && !time.contains("m")
                && !time.contains("s")) {
            if (Number.isNumber(time)) {
                minutes = Number.getNumber(time);
            } else {
                time = "0";
            }
        } else {
            if (time.contains("d")) {
                splitter = splitter[0].split("d");
                days = Number.getNumber(splitter[0]);
                if (splitter.length == 2) {
                    splitter[0] = splitter[1];
                }
            }

            if (time.contains("h")) {
                splitter = splitter[0].split("h");
                hours = Number.getNumber(splitter[0]);
                if (splitter.length == 2) {
                    splitter[0] = splitter[1];
                }
            }

            if (time.contains("m")) {
                splitter = splitter[0].split("m");
                minutes = Number.getNumber(splitter[0]);
                if (splitter.length == 2) {
                    splitter[0] = splitter[1];
                }
            }

            if (time.contains("s")) {
                splitter = splitter[0].split("s");
                seconds = Number.getNumber(splitter[0]);
            }

            if (time.equals("0")) {
                // Message here
            }

            delay = (days * 1000 * 60 * 60 * 24) + (hours * 1000 * 60 * 60)
                    + (minutes * 1000 * 60) + (seconds * 1000);
        }

        if(!TEMP.configc.contains("lastbackup")) {
            TEMP.configc.set("lastbackup", System.currentTimeMillis());
            Config.save();
            backup();
        } else {
            startTimer();
        }
    }

    private void startTimer() {
        if((Boolean) Config.ConfigValues.BACKUP_AUTOMATICMODE.value) {
            new BukkitRunnable() {
                public void run() {
                    if(System.currentTimeMillis() - (Long) TEMP.configc.get("lastbackup") >= delay) {
                        TEMP.configc.set("lastbackup", System.currentTimeMillis());
                        Config.save();
                        if(!doing) {
                            backup();
                        }
                    }
                    // 500...1200
                    // 72000
                }
            }.runTaskTimer(plugin, 500L, 72000L);
        }
    }

    public void backup() {
        doing = true;

        SimpleDateFormat f = new SimpleDateFormat("dd-MMM-yyyy-HH:mm:ss-z");
        f.setTimeZone(TimeZone.getTimeZone("Europe/London"));

        ArrayList<File> fq = new ArrayList<>();

        File ff = new File(toStore);

        FileUtils.listFiles(ff, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE).stream().
                filter(fff -> fff.getName().endsWith(".zip")).forEach(fq::add);

        if(!fq.isEmpty() && fq.size() >= 3) {
            File[] fil = fq.toArray(new File[fq.size()]);
            Arrays.sort(fil, LastModifiedFileComparator.LASTMODIFIED_COMPARATOR);

            fil[0].delete();
        }

        size = 0;
        files = 0;

        p = 0;
        lp = -1;

        time = f.format(GregorianCalendar.getInstance().getTime());

        logFile = new File(toStoreLogs + time + ".log");

        if (!new File(toStore).exists()) {
            new File(toStore).mkdir();
        }

        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Dispatch.sendMessage((String) Messages.MessagesValues.BACKUP_STARTED.value, null);

        Bukkit.getOnlinePlayers().stream().filter(player -> player.hasPermission("backup.notification")).
                forEach(player -> Dispatch.sendMessage((String) Messages.MessagesValues.BACKUP_STARTED.value, player));

        Thread t = new Thread(new Runnable() {
            boolean nc = false;

            @Override
            public void run() {
                String s = "zip -r " + toStore + time + ".zip " + toBackup;

                for(String exclusion : (List<String>) Config.ConfigValues.BACKUP_EXCLUSIONPATHS.value) {
                    String nex = exclusion.endsWith("/") ? exclusion : exclusion + "/";
                    s += " -x " + nex + "\\" + "*";
                }

                startBackupTimer();

                File file = new File(toBackup);

                // FileUtils.listFiles(file, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE)
                listFDIR(file.getAbsolutePath());

                try {
                    Process process = Runtime.getRuntime().exec(s);
                    InputStreamReader is = new InputStreamReader(process.getInputStream());
                    BufferedReader buff = new BufferedReader(is);

                    FileOutputStream fos = new FileOutputStream(logFile);
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

                    String line;

                    while((line = buff.readLine()) != null) {
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

                Bukkit.getOnlinePlayers().stream().filter(player -> player.hasPermission("backup.notification")).
                        forEach(player -> Dispatch.sendMessage((String) Messages.MessagesValues.BACKUP_STOPPED.value, player));

            }

            private void updatePercentage() {
                double d = ((double) files / size) * 100;
                p = (int) d;
            }

            private void startBackupTimer() {
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
                                    if (player.hasPermission("backup.notification")) {
                                        Dispatch.sendMessage(m, player);
                                    }
                                }
                            }
                        } else {
                            doing = false;
                            startTimer();
                            this.cancel();
                        }
                    }
                }.runTaskTimer(plugin, 0L, 100L);
            }
        });
        t.start();
    }

    public void listFDIR(String dir) {
        File directory = new File(dir);
        File[] files = directory.listFiles();
        for(File file : files) {
            if(file.isFile()) {
                size++;
            } else if(file.isDirectory()) {
                listFDIR(file.getAbsolutePath());
            }
        }
    }
}