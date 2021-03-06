package co.uk.RandomPanda30.FunZone.TreasureChest.Enums;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("deprecation")
public enum GadgetsType {

	Coins (new ItemStack(Material.DIAMOND),
			ChatColor.GREEN + "%l%%c% Coins",
			RewardChest.Normal,
			"non"),
	PaintBallGunAmmo (new ItemStack(Material.GOLD_BARDING),
			ChatColor.GREEN + "%c% PaintBall Ammo",
			RewardChest.Normal,
			"non"),
	CoinPartyBomb (new ItemStack(Material.DIAMOND),
			ChatColor.GOLD + "1 Party Bomb",
			RewardChest.Rare,
			"non"),
	TNT (new ItemStack(Material.TNT),
			ChatColor.GREEN + "%c% TNT",
			RewardChest.Normal,
			"non"),
	Flamethrower (new ItemStack(Material.DIAMOND_BARDING),
			ChatColor.GREEN + "%c% Flamethrower",
			RewardChest.Normal,
			"non"),
	FireWorks (new ItemStack(Material.FIREWORK),
			ChatColor.GREEN + "%c% FireWorks",
			RewardChest.Normal,
			"non"),
	Melon (new ItemStack(Material.MELON_BLOCK),
			ChatColor.GREEN + "%c% Melon",
			RewardChest.Normal,
			"non"),
	BatBlast (new ItemStack(Material.IRON_BARDING),
			ChatColor.GREEN + "%c% BatBlast Ammo",
			RewardChest.Normal,
			"non"),
	WaterBomb (new ItemStack(373),
			ChatColor.GREEN + "%c% WaterBomb Ammo",
			RewardChest.Normal,
			"non"),
	Pee (new ItemStack(Material.GOLD_BLOCK),
			ChatColor.GREEN + "%c% Pee Ammo",
			RewardChest.Normal,
			"non"),
	TreasureKey (new ItemStack(Material.TRIPWIRE_HOOK),
			ChatColor.GOLD + "1 Treasure Key",
			RewardChest.Rare,
			"non"),
	InfernalHorse (new ItemStack(Material.BONE),
			ChatColor.RED + "Infernal Horse",
			RewardChest.Legendary,
			"gadgetsbox.infernalhorse"),
	GlacialHorse (new ItemStack(Material.SNOW_BALL),
			ChatColor.RED + "Glacial Horse",
			RewardChest.Legendary,
			"gadgetsbox.glacialhorse"),
	MuleHorse (new ItemStack(Material.HAY_BLOCK),
			ChatColor.GOLD + "Mule Horse",
			RewardChest.Rare,
			"gadgetsbox.mulehorse"),
	SantaHorse (new ItemStack(Material.RED_MUSHROOM),
			ChatColor.RED + "Santa Horse",
			RewardChest.Legendary,
			"gadgetsbox.santahorse"),
	HalloweenHorse (new ItemStack(Material.SKULL_ITEM, 1, (short) 1),
			ChatColor.RED + "Halloween Horse",
			RewardChest.Legendary,
			"gadgetsbox.halloweenhorse"),
	NaturalHorse (new ItemStack(Material.WATER_LILY),
			ChatColor.RED + "Natural Horse",
			RewardChest.Legendary,
			"gadgetsbox.naturalhorse"),
	ZombieHorse (new ItemStack(Material.ROTTEN_FLESH),
			ChatColor.RED + "Zombie Horse",
			RewardChest.Legendary,
			"gadgetsbox.zombiehorse"),
	Enchanted (new ItemStack(Material.ENCHANTMENT_TABLE),
			ChatColor.RED + "Enchanted",
			RewardChest.Legendary,
			"gadgetsbox.enchanted"),
	FlameRing (new ItemStack(Material.BLAZE_POWDER),
			ChatColor.GOLD + "Flame Ring",
			RewardChest.Rare,
			"gadgetsbox.flamering"),
	WaterRing (new ItemStack(Material.WATER_BUCKET),
			ChatColor.GOLD + "Water Ring",
			RewardChest.Rare,
			"gadgetsbox.waterring"),
	HeartsRing (new ItemStack(Material.REDSTONE),
			ChatColor.GOLD + "Hearts Ring",
			RewardChest.Rare,
			"gadgetsbox.heartsring"),
	SparksRing (new ItemStack(Material.NETHER_STAR),
			ChatColor.GOLD + "Sparks Ring",
			RewardChest.Rare,
			"gadgetsbox.sparksring"),
	TornadoEffect (new ItemStack(Material.INK_SACK, 1, (short) 15),
			ChatColor.GOLD + "Tornado Effect",
			RewardChest.Rare,
			"gadgetsbox.tornadoeffect"),
	Cloud (new ItemStack(Material.INK_SACK, 1, (short) 15),
			ChatColor.GOLD + "Cloud",
			RewardChest.Rare,
			"gadgetsbox.cloud"),
	Trampoline (new ItemStack(Material.HOPPER),
			ChatColor.GOLD + "Trampoline",
			RewardChest.Rare,
			"gadgetsbox.trampoline"),
	Tornado (new ItemStack(Material.WEB),
			ChatColor.GOLD + "Tornado",
			RewardChest.Rare,
			"gadgetsbox.tornado"),

	DogPet (new ItemStack(Material.MONSTER_EGG, 1, (short) 95),
			ChatColor.AQUA + "Dog Pet",
			RewardChest.Uncommon,
			"gadgetsbox.dogpet"),
	MousePet (new ItemStack(Material.MONSTER_EGG, 1, (short) 60),
			ChatColor.AQUA + "Mouse Pet",
			RewardChest.Uncommon,
			"gadgetsbox.mousepet"),
	ChickenPet (new ItemStack(Material.MONSTER_EGG, 1, (short) 93),
			ChatColor.AQUA + "Chicken Pet",
			RewardChest.Uncommon,
			"gadgetsbox.chickenpet"),
	CowPet (new ItemStack(Material.MONSTER_EGG, 1, (short) 92),
			ChatColor.AQUA + "Cow Pet",
			RewardChest.Uncommon,
			"gadgetsbox.cowpet"),
	MushroomCowPet (new ItemStack(Material.MONSTER_EGG, 1, (short) 96),
			ChatColor.AQUA + "MushroomCow Pet",
			RewardChest.Uncommon,
			"gadgetsbox.mushroomcowpet"),
	AngryWolfPet (new ItemStack(Material.MONSTER_EGG, 1, (short) 52),
			ChatColor.AQUA + "AngryWolf Pet",
			RewardChest.Uncommon,
			"gadgetsbox.angrywolfpet"),
	CatPet (new ItemStack(Material.MONSTER_EGG, 1, (short) 51),
			ChatColor.AQUA + "Cat Pet",
			RewardChest.Uncommon,
			"gadgetsbox.catpet"),
	AngeryCatPet (new ItemStack(Material.MONSTER_EGG, 1, (short) 56),
			ChatColor.AQUA + "AngeryCat Pet",
			RewardChest.Uncommon,
			"gadgetsbox.angrycatpet"),
	SheepPet (new ItemStack(Material.MONSTER_EGG, 1, (short) 91),
			ChatColor.AQUA + "Sheep Pet",
			RewardChest.Uncommon,
			"gadgetsbox.sheeppet"),
	PigPet (new ItemStack(Material.MONSTER_EGG, 1, (short) 90),
			ChatColor.AQUA + "Pig Pet",
			RewardChest.Uncommon,
			"gadgetsbox.pigpet"),

	CatDisc (new ItemStack(Material.GREEN_RECORD),
			ChatColor.AQUA + "Cat Disc",
			RewardChest.Uncommon,
			"gadgetsbox.catdisc"),
	ChripDisc (new ItemStack(Material.RECORD_4),
			ChatColor.AQUA + "Chrip Disc",
			RewardChest.Uncommon,
			"gadgetsbox.chripdisc"),
	MellohiDisc (new ItemStack(Material.RECORD_7),
			ChatColor.AQUA + "Mellohi Disc",
			RewardChest.Uncommon,
			"gadgetsbox.mellohidisc"),
	StalDisc (new ItemStack(Material.RECORD_8),
			ChatColor.AQUA + "Stal Disc",
			RewardChest.Uncommon,
			"gadgetsbox.staldisc"),
	WaitDisc (new ItemStack(Material.RECORD_12),
			ChatColor.AQUA + "Wait Disc",
			RewardChest.Uncommon,
			"gadgetsbox.waitdisc"),
	StradDisc (new ItemStack(Material.RECORD_9),
			ChatColor.AQUA + "Strad Disc",
			RewardChest.Uncommon,
			"gadgetsbox.straddisc"),
	Disc13 (new ItemStack(Material.GOLD_RECORD),
			ChatColor.AQUA + "13 Disc",
			RewardChest.Uncommon,
			"gadgetsbox.13disc"),
	BlocksDisc (new ItemStack(Material.RECORD_3),
			ChatColor.AQUA + "Blocks Disc",
			RewardChest.Uncommon,
			"gadgetsbox.blocksdisc"),
	FarDisc (new ItemStack(Material.RECORD_5),
			ChatColor.AQUA + "Far Disc",
			RewardChest.Uncommon,
			"gadgetsbox.fardisc"),
	WardDisc (new ItemStack(Material.RECORD_10),
			ChatColor.AQUA + "Ward Disc",
			RewardChest.Uncommon,
			"gadgetsbox.warddisc"),
	Disc11 (new ItemStack(Material.RECORD_11),
			ChatColor.AQUA + "11 Disc",
			RewardChest.Uncommon,
			"gadgetsbox.11disc"),
	MallDisc (new ItemStack(Material.RECORD_6),
			ChatColor.AQUA + "Mall Disc",
			RewardChest.Uncommon,
			"gadgetsbox.malldisc"),

	SkeletonMorph (new ItemStack(Material.BONE),
			ChatColor.GOLD + "Skeleton Morph",
			RewardChest.Rare,
			"gadgetsbox.skeletonmorph"),
	VillagerMorph (new ItemStack(Material.EMERALD),
			ChatColor.GOLD + "Villager Morph",
			RewardChest.Rare,
			"gadgetsbox.villagermorph"),
	BlazeMorph (new ItemStack(Material.BLAZE_ROD),
			ChatColor.GOLD + "Blaze Morph",
			RewardChest.Rare,
			"gadgetsbox.blazemorph"),
	EndermanMorph (new ItemStack(Material.ENDER_PEARL),
			ChatColor.GOLD + "Enderman Morph",
			RewardChest.Rare,
			"gadgetsbox.endermanmorph"),
	WitchMorph (new ItemStack(Material.BREWING_STAND_ITEM),
			ChatColor.GOLD + "Witch Morph",
			RewardChest.Rare,
			"gadgetsbox.witchmorph"),
	ZombieMorph (new ItemStack(Material.SKULL_ITEM, 1, (short) 2),
			ChatColor.GOLD + "Zombie Morph",
			RewardChest.Rare,
			"gadgetsbox.zombiemorph"),
	PigmanMorph (new ItemStack(Material.GOLD_NUGGET),
			ChatColor.GOLD + "Pigman Morph",
			RewardChest.Rare,
			"gadgetsbox.pigmanmorph"),
	PigMorph (new ItemStack(Material.PORK),
			ChatColor.GOLD + "Pig Morph",
			RewardChest.Rare,
			"gadgetsbox.pigmorph"),
	CreeperMorph (new ItemStack(Material.TNT),
			ChatColor.GOLD + "Creeper Morph",
			RewardChest.Rare,
			"gadgetsbox.creepermorph"),

	FancyHelmet (new ItemStack(Material.DIAMOND_HELMET),
			ChatColor.AQUA + "Fancy Helmet",
			RewardChest.Uncommon,
			"gadgetsbox.fancyhelmet"),
	FancyChestPlate (new ItemStack(Material.DIAMOND_CHESTPLATE),
			ChatColor.AQUA + "Fancy Chestplate",
			RewardChest.Uncommon,
			"gadgetsbox.fancychestplate"),
	FancyLeggings (new ItemStack(Material.DIAMOND_LEGGINGS),
			ChatColor.AQUA + "Fancy Leggings",
			RewardChest.Uncommon,
			"gadgetsbox.fancyleggings"),
	FancyBoots (new ItemStack(Material.DIAMOND_BOOTS),
			ChatColor.AQUA + "Fancy Boots",
			RewardChest.Uncommon,
			"gadgetsbox.fancyboots"),
	DiscoHelmet (new ItemStack(Material.LEATHER_HELMET),
			ChatColor.RED + "Disco Helmet",
			RewardChest.Legendary,
			"gadgetsbox.discohelmet"),
	DiscoChestPlate (new ItemStack(Material.LEATHER_CHESTPLATE),
			ChatColor.RED + "Disco Chestplate",
			RewardChest.Legendary,
			"gadgetsbox.discochestplate"),
	DiscoLeggings (new ItemStack(Material.LEATHER_LEGGINGS),
			ChatColor.RED + "Disco Leggings",
			RewardChest.Legendary,
			"gadgetsbox.discoleggings"),
	DiscoBoots (new ItemStack(Material.LEATHER_BOOTS),
			ChatColor.RED + "Disco Boots",
			RewardChest.Legendary,
			"gadgetsbox.discoboots"),

	SantaHelmet (
			new ItemStack(Material.LEATHER_HELMET, 1, DyeColor.WHITE.getData()),
			ChatColor.GOLD + "Santa Helmet",
			RewardChest.Rare,
			"gadgetsbox.santahelmet"),
	SantaChestPlate (
			new ItemStack(Material.LEATHER_CHESTPLATE, 1,
					DyeColor.RED.getData()),
			ChatColor.GOLD + "Santa Chestplate",
			RewardChest.Rare,
			"gadgetsbox.santachestplate"),
	SantaLeggings (
			new ItemStack(Material.LEATHER_LEGGINGS, 1,
					DyeColor.WHITE.getData()),
			ChatColor.GOLD + "Santa Leggings",
			RewardChest.Rare,
			"gadgetsbox.santaleggings"),
	SantaBoots (
			new ItemStack(Material.LEATHER_BOOTS, 1, DyeColor.RED.getData()),
			ChatColor.GOLD + "Santa Boots",
			RewardChest.Rare,
			"gadgetsbox.santaboots"),

	HalloweenHelmet (new ItemStack(Material.JACK_O_LANTERN),
			ChatColor.GOLD + "Halloween Helmet",
			RewardChest.Rare,
			"gadgetsbox.halloweenhelmet"),
	HalloweenChestPlate (
			new ItemStack(Material.LEATHER_CHESTPLATE, 1,
					DyeColor.BLACK.getData()),
			ChatColor.GOLD + "Halloween Chestplate",
			RewardChest.Rare,
			"gadgetsbox.halloweenchestplate"),
	HalloweenLeggings (
			new ItemStack(Material.LEATHER_LEGGINGS, 1,
					DyeColor.BLACK.getData()),
			ChatColor.GOLD + "Halloween Leggings",
			RewardChest.Rare,
			"gadgetsbox.halloweenleggings"),
	HalloweenBoots (
			new ItemStack(Material.LEATHER_BOOTS, 1, DyeColor.BLACK.getData()),
			ChatColor.GOLD + "Halloween Boots",
			RewardChest.Rare,
			"gadgetsbox.halloweenboots");

	public ItemStack mat;
	public String name;
	public RewardChest rc;
	public GadgetsType dv;
	public String per;

	private GadgetsType (ItemStack mat, String name, RewardChest rc,
			String per) {
		this.mat = mat;
		this.name = name;
		this.rc = rc;
		this.per = per;
	}

	public boolean isSame(GadgetsType gt) {
		if (mat.getType() == gt.mat.getType() && rc == gt.rc)
			return true;
		else
			return false;
	}
}