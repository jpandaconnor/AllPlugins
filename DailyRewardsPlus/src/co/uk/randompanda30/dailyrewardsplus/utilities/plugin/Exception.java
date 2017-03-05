package co.uk.randompanda30.dailyrewardsplus.utilities.plugin;

import co.uk.randompanda30.dailyrewardsplus.config.values.MessagesValues;
import co.uk.randompanda30.dailyrewardsplus.utilities.string.Dispatch;
import co.uk.randompanda30.dailyrewardsplus.utilities.string.Format;

import java.util.ArrayList;
import java.util.List;

public class Exception {

    private StackTraceElement[] trace;
    private Cause cause;
    private String etx;

    public Exception(StackTraceElement[] trace, Cause cause, String etx) {
        this.trace = trace;
        this.cause = cause;
        this.etx = etx;
        throwe();
    }

    @SuppressWarnings("unchecked")
    public void throwe() {
        for (String m : (List<String>) MessagesValues.EXCEPTIONLAYOUT_CONSOLE.value) {
            m = m.replace("%ex", this.cause.toString());
            m = m.replace("%reason", cause.getReason());
            m = m.replace("%etx", etx);

            if (m.contains("%solutions")) {
                for (String s : cause.getSolutions()) {
                    Dispatch.sendMessage(s, null);
                }
                continue;
            }

            if (m.contains("%trace")) {
                for (StackTraceElement s : trace) {
                    Dispatch.sendMessage(Format.format("&e" + s.toString()), null);
                }
                continue;
            }
            Dispatch.sendMessage(m, null);
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
        FILE_ERROR {
            public String getReason() {
                return "%NFile does not exist or a permission issue on Unix systems";
            }

            public ArrayList<String> getSolutions() {
                return new ArrayList<String>() {
                    {
                        add("%A - %NCheck to see if the file exists");
                        add("%A - %NCheck file permissions (On Unix systems)");
                    }
                };
            }
        },

        ENCRYPTION_ERROR {
            public String getReason() {
                return "%NEncryption failed. %AAll information has been deleted";
            }

            public ArrayList<String> getSolutions() {
                return new ArrayList<String>() {
                    {
                        add("%A - %NReport this to the developer as soon as possible");
                    }
                };
            }
        },

        MSQL_ERROR {
            public String getReason() {
                return "%NThere was a problem with MySQL";
            }

            public ArrayList<String> getSolutions() {
                return new ArrayList<String>() {
                    {
                        add("%A - %NCheck all your MySQL details. Set MYSQL SETUP to false in the config and set up MySQL again");
                        add("%A - %NCheck the MySQL user has permission");
                    }
                };
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