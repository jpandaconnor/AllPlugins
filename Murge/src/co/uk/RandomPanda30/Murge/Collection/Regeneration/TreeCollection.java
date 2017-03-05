package co.uk.RandomPanda30.Murge.Collection.Regeneration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.Collection.Bases.RegenerationBase;
import co.uk.RandomPanda30.Murge.Handlers.Regeneration.TreeRHandler;
import co.uk.RandomPanda30.Murge.Handlers.Splitters.TreeSplitter;
import co.uk.RandomPanda30.Murge.Methods.LocationMethods;
import co.uk.RandomPanda30.Murge.Stats.StatsHandler;
import co.uk.RandomPanda30.Murge.Values.Enums.ConfigValues;

@SuppressWarnings("unchecked")
public class TreeCollection implements RegenerationBase {

	private static TreeCollection instance = new TreeCollection();

	public static TreeCollection getCollection() {
		return instance;
	}

	@Override
	public boolean isEnabled() {
		return (Boolean) Murge.cMap
				.getStat(ConfigValues.REGENERATION_TREEENABLED);
	}

	@Override
	public void setEnabled(boolean c) {
		Murge.cMap.setStat(ConfigValues.REGENERATION_TREEENABLED, c);
	}

	@Override
	public void loadData() {
		if (isEnabled()) {
			setSection();
			ArrayList<String> keys = (ArrayList<String>) Murge.cso.getDumpC()
					.get("Trees");
			if (!keys.isEmpty()) {
				for (Iterator<String> iterator = keys.iterator(); iterator
						.hasNext();) {
					TreeSplitter sh = new TreeSplitter(iterator.next());
					TreeRHandler tree = new TreeRHandler(sh.getLocation(),
							sh.getTreeType());
					if (Calendar.getInstance().getTimeInMillis() > sh.getTime()) {
						tree.grow();
						tree.remove();
						iterator.remove();
					} else {
						StatsHandler.addTree(tree);
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
			for (TreeRHandler tree : StatsHandler.getTrees()) {
				toDump.add(LocationMethods.compileLocation(tree.getLocation())
						+ ";" + tree.getType().toString() + ";"
						+ tree.getTime());
			}
			Murge.cso.getDumpC().set("Trees", toDump);
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
					.get("Trees");
			if (!keys.isEmpty()) {
				for (Iterator<String> iterator = keys.iterator(); iterator
						.hasNext();) {
					TreeSplitter sh = new TreeSplitter(iterator.next());
					TreeRHandler tree = new TreeRHandler(sh.getLocation(),
							sh.getTreeType());
					if (Calendar.getInstance().getTimeInMillis() > sh.getTime()) {
						tree.grow();
						tree.remove();
						iterator.remove();
					} else {
						StatsHandler.addTree(tree);
					}
				}
			}

			if (!StatsHandler.getTrees().isEmpty()) {
				for (Iterator<TreeRHandler> iterator = StatsHandler.getTrees()
						.iterator(); iterator.hasNext();) {
					TreeRHandler tree = iterator.next();
					if (Calendar.getInstance().getTimeInMillis() > tree
							.getTime()) {
						tree.grow();
						tree.remove();
						iterator.remove();
						StatsHandler.getTrees().remove(tree);
					}
				}
			}
		}
	}

	public void setSection() {
		if (Murge.cso.getDumpC().get("Trees") == null) {
			Murge.cso.getDumpC().set("Trees", new ArrayList<String>());
			try {
				Murge.cso.getDumpC().save(Murge.cso.getDump());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}