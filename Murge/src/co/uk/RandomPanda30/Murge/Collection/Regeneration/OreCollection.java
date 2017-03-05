package co.uk.RandomPanda30.Murge.Collection.Regeneration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.Collection.Bases.RegenerationBase;
import co.uk.RandomPanda30.Murge.Handlers.Regeneration.OreRHandler;
import co.uk.RandomPanda30.Murge.Handlers.Splitters.OreSplitter;
import co.uk.RandomPanda30.Murge.Methods.LocationMethods;
import co.uk.RandomPanda30.Murge.Stats.StatsHandler;
import co.uk.RandomPanda30.Murge.Values.Enums.ConfigValues;

@SuppressWarnings("unchecked")
public class OreCollection implements RegenerationBase {

	private static OreCollection instance = new OreCollection();

	public static OreCollection getCollection() {
		return instance;
	}

	@Override
	public boolean isEnabled() {
		return (Boolean) Murge.cMap
				.getStat(ConfigValues.REGENERATION_OREENABLED);
	}

	@Override
	public void setEnabled(boolean c) {
		Murge.cMap.setStat(ConfigValues.REGENERATION_OREENABLED, c);
	}

	@Override
	public void loadData() {
		if (isEnabled()) {
			setSection();
			ArrayList<String> keys = (ArrayList<String>) Murge.cso.getDumpC()
					.get("Ores");
			if (!keys.isEmpty()) {
				for (Iterator<String> iterator = keys.iterator(); iterator
						.hasNext();) {
					OreSplitter sh = new OreSplitter(iterator.next());
					OreRHandler ore = new OreRHandler(sh.getLocation(),
							sh.getType());
					if (Calendar.getInstance().getTimeInMillis() > sh.getTime()) {
						ore.place();
						ore.remove();
						iterator.remove();
					} else {
						StatsHandler.addOre(ore);
					}
				}
			}
		}
	}

	@Override
	public void dumpData() {
		if (isEnabled()) {
			setSection();
			ArrayList<String> toDump = new ArrayList<String>();
			checkData();
			for (OreRHandler ore : StatsHandler.getOres()) {
				toDump.add(LocationMethods.compileLocation(ore.getLocation())
						+ ";" + ore.getOre() + ";" + ore.getTime());
			}
			Murge.cso.getDumpC().set("Ores", toDump);
			try {
				Murge.cso.getDumpC().save(Murge.cso.getDump());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void checkData() {
		if (isEnabled()) {
			setSection();
			ArrayList<String> keys = (ArrayList<String>) Murge.cso.getDumpC()
					.get("Ores");
			if (!keys.isEmpty()) {
				for (Iterator<String> iterator = keys.iterator(); iterator
						.hasNext();) {
					OreSplitter sh = new OreSplitter(iterator.next());
					OreRHandler ore = new OreRHandler(sh.getLocation(),
							sh.getType());
					if (Calendar.getInstance().getTimeInMillis() > sh.getTime()) {
						ore.place();
						ore.remove();
						iterator.remove();
					} else {
						StatsHandler.addOre(ore);
					}
				}
			}

			if (!StatsHandler.getOres().isEmpty()) {
				for (Iterator<OreRHandler> iterator = StatsHandler.getOres()
						.iterator(); iterator.hasNext();) {
					OreRHandler ore = iterator.next();
					if (Calendar.getInstance().getTimeInMillis() > ore
							.getTime()) {
						ore.place();
						ore.remove();
						iterator.remove();
						StatsHandler.getOres().remove(ore);
					}
				}
			}
		}
	}

	public void setSection() {
		if (Murge.cso.getDumpC().get("Ores") == null) {
			Murge.cso.getDumpC().set("Ores", new ArrayList<String>());
			try {
				Murge.cso.getDumpC().save(Murge.cso.getDump());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}