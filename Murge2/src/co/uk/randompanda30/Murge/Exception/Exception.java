package co.uk.RandomPanda30.Murge.Exception;

import java.util.ArrayList;
import java.util.List;

import co.uk.RandomPanda30.Murge.Configs.Enums.Messages;

public class Exception {

	/*
	 * 
	 * This class will allow us to handle the expection is a way that well...Is
	 * different;
	 * 
	 * To do - Custom player handling here
	 * 
	 */

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
		};

		public String getReason() {
			throw new AbstractMethodError("This error should never be shown.");
		}

		public ArrayList<String> getSolutions() {
			throw new AbstractMethodError("This error should never be shown.");
		}
	}

	private StackTraceElement[] trace;
	private Cause cause;
	private String etx;

	public ExceptionUtil (StackTraceElement[] trace, Cause cause, String etx) {
		this.trace = trace;
		this.cause = cause;
		this.etx = etx;
		throwe();
	}

	/* Throwing the custom exception to the config */

	public void throwe() {
		for (String m : (List<String>) Messages.EXCEPTIONLAYOUT_CONSOLE.value) {
			m = m.replace("%ex", this.cause.toString());
			m = m.replace("%reason", cause.getReason());
			m = m.replace("%etx", etx);

			if (m.contains("%solutions")) {
				for (String s : cause.getSolutions()) {
					MessageUtil.sendMessage(s, null);
				}
				continue;
			}

			if (m.contains("%trace")) {
				for (StackTraceElement s : trace) {
					MessageUtil.sendMessage(s.toString(), null);
				}
				continue;
			}

			MessageUtil.sendMessage(m, null);
		}
	}

	/* Returning the stack trace */
	public StackTraceElement[] getStackTrace() {
		return trace;
	}

	/* Simply returning the cause here */
	public Cause getCause() {
		return cause;
	}

	/* Returning the notes in the code */
	public String getEtx() {
		return etx;
	}

	/* Returns the player if there is one */	
}