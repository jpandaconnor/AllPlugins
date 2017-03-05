package co.uk.RandomPanda30.SAdminO.Util.Basic;

import co.uk.RandomPanda30.SAdminO.Files.Messages.MessagesValues;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class EUtil {

    private StackTraceElement[] trace;
    private Cause cause;
    private String etx;
    public EUtil(StackTraceElement[] trace, Cause cause, String etx) {
        this.trace = trace;
        this.cause = cause;
        this.etx = etx;
        throwe();
    }

    public void throwe() {
        for (String m : (List<String>) MessagesValues.EXCEPTIONLAYOUT_CONSOLE.value) {
            m = m.replace("%ex", this.cause.toString());
            m = m.replace("%reason", cause.getReason());
            m = m.replace("%etx", etx);

            if (m.contains("%solutions")) {
                for (String s : cause.getSolutions()) {
                    MUtil.sendMessage(s, null);
                }
                continue;
            }

            if (m.contains("%trace")) {
                for (StackTraceElement s : trace) {
                    MUtil.sendMessage(MUtil.format("&e" + s.toString()), null);
                }
                continue;
            }
            MUtil.sendMessage(m, null);
        }
    }

    public StackTraceElement[] getStackTrace() {
        return trace;
    }

    public Cause getCause() {
        return cause;
    }

    public String getEtx() {
        return etx;
    }

    public enum Cause {
        FILEWENTWRONG {
            public String getReason() {
                return "%NFile does not exist or a permission issue on Unix systems";
            }

            public ArrayList<String> getSolutions() {
                ArrayList<String> s = new ArrayList<String>();
                s.add("%A - %NCheck to see if all files exist.");
                s.add("%A - %NOn Unix system, check permissions of directories");
                return s;
            }
        },

        ENCRYPTIONERROR {
            public String getReason() {
                return "%NThere was a problem with the encryption process. %AYOUR INFORMATION HAS NOT BEEN STORED AND WILL BE DELETED";
            }

            public ArrayList<String> getSolutions() {
                ArrayList<String> s = new ArrayList<>();
                s.add("%A - %NReport this to the developer as soon as possible along with the stack trace");
                return s;
            }
        },

        MYSQLERROR {
            public String getReason() {
                return "%NThere was a problem with MySQL";
            }

            public ArrayList<String> getSolutions() {
                ArrayList<String> s = new ArrayList<>();
                s.add("%A - %NCheck all your MySQL details. Set MYSQL SETUP to false in the config and set up MySQL again");
                s.add("%A - %NCheck the MySQL user has permission");
                return s;
            }
        },

        STRINGTONUMBER {
            public String getReason() {
                return "%NPut letters instead of numbers";
            }

            public ArrayList<String> getSolutions() {
                ArrayList<String> s = new ArrayList<String>();
                s.add("%A - %NMake sure you don't include any letters or symbols");
                return s;
            }
        };

        public String getReason() {
            throw new AbstractMethodError("This error should never be shown.");
        }

        public ArrayList<String> getSolutions() {
            throw new AbstractMethodError("This error should never be shown.");
        }
    }
}