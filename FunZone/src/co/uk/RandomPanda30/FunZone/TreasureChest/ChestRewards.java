package co.uk.RandomPanda30.FunZone.TreasureChest;

import java.util.Random;

import co.uk.RandomPanda30.FunZone.TreasureChest.Enums.GadgetsType;
import co.uk.RandomPanda30.FunZone.TreasureChest.Enums.RewardChest;

public class ChestRewards {

	public static RewardChest randomReward() {
		Random random = new Random();
		int ran = random.nextInt(101 - 1) + 1;
		if (ran > 0 && ran < 85) {
			return RewardChest.Normal;
		}

		if (ran > 85 && ran < 95) {
			return RewardChest.Uncommon;
		}

		if (ran >= 95 && ran < 99) {
			return RewardChest.Rare;
		}

		if (ran >= 99 && ran <= 100) {
			return RewardChest.Legendary;
		}
		return RewardChest.Normal;
	}

	public static GadgetsType randomGadgets(RewardChest chest) {
		GadgetsType type = null;
		Random random = new Random();

		if (chest == RewardChest.Normal) {
			int ran = random.nextInt(10 - 1) + 1;

			switch (ran) {
			case 1:
				GadgetsType gt = GadgetsType.Coins;
				gt.rc = RewardChest.Normal;
				type = gt;
				break;
			case 2:
				type = GadgetsType.FireWorks;
				break;
			case 3:
				type = GadgetsType.TNT;
				break;
			case 4:
				type = GadgetsType.WaterBomb;
				break;
			case 5:
				type = GadgetsType.Melon;
				break;
			case 6:
				type = GadgetsType.PaintBallGunAmmo;
				break;
			case 7:
				type = GadgetsType.BatBlast;
				break;
			case 8:
				type = GadgetsType.Pee;
				break;
			case 9:
				type = GadgetsType.Flamethrower;
				break;
			}
			if (type != null) {
				return type;
			}

			GadgetsType gt = GadgetsType.Coins;
			gt.rc = RewardChest.Normal;
			return gt;
		}

		if (chest == RewardChest.Rare) {
			int ran = random.nextInt(30 - 1) + 1;

			switch (ran) {
			case 1:
				GadgetsType gt = GadgetsType.Coins;
				gt.rc = RewardChest.Rare;
				type = gt;
				break;
			case 2:
				type = GadgetsType.CoinPartyBomb;
				break;
			case 3:
				type = GadgetsType.TreasureKey;
				break;
			case 4:
				type = GadgetsType.MuleHorse;
				break;
			case 5:
				type = GadgetsType.FlameRing;
				break;
			case 6:
				type = GadgetsType.WaterRing;
				break;
			case 7:
				type = GadgetsType.HeartsRing;
				break;
			case 8:
				type = GadgetsType.HalloweenHelmet;
				break;
			case 9:
				type = GadgetsType.HalloweenChestPlate;
				break;
			case 10:
				type = GadgetsType.HalloweenLeggings;
				break;
			case 11:
				type = GadgetsType.HalloweenBoots;
				break;
			case 12:
				type = GadgetsType.Cloud;
				break;
			case 13:
				type = GadgetsType.Trampoline;
				break;
			case 14:
				type = GadgetsType.Tornado;
				break;
			case 15:
				type = GadgetsType.SkeletonMorph;
				break;
			case 16:
				type = GadgetsType.VillagerMorph;
				break;
			case 17:
				type = GadgetsType.BlazeMorph;
				break;
			case 18:
				type = GadgetsType.EndermanMorph;
				break;
			case 19:
				type = GadgetsType.WitchMorph;
				break;
			case 20:
				type = GadgetsType.ZombieMorph;
				break;
			case 21:
				type = GadgetsType.PigmanMorph;
				break;
			case 22:
				type = GadgetsType.PigMorph;
				break;
			case 23:
				type = GadgetsType.CreeperMorph;
				break;
			case 24:
				type = GadgetsType.SantaHelmet;
				break;
			case 25:
				type = GadgetsType.SantaChestPlate;
				break;
			case 26:
				type = GadgetsType.SantaLeggings;
				break;
			case 27:
				type = GadgetsType.SantaBoots;
				break;
			case 28:
				type = GadgetsType.SparksRing;
				break;
			case 29:
				type = GadgetsType.TornadoEffect;
				break;
			}
			if (type != null) {
				return type;
			}

			GadgetsType gt = GadgetsType.Coins;
			gt.rc = RewardChest.Rare;
			return gt;
		}

		if (chest == RewardChest.Uncommon) {
			int ran = random.nextInt(24 - 1) + 1;
			switch (ran) {
			case 1:
				GadgetsType gt = GadgetsType.Coins;
				gt.rc = RewardChest.Uncommon;
				type = gt;
				break;
			case 2:
				type = GadgetsType.DogPet;
				break;
			case 3:
				type = GadgetsType.MousePet;
				break;
			case 4:
				type = GadgetsType.ChickenPet;
				break;
			case 5:
				type = GadgetsType.CowPet;
				break;
			case 6:
				type = GadgetsType.MushroomCowPet;
				break;
			case 7:
				type = GadgetsType.AngryWolfPet;
				break;
			case 8:
				type = GadgetsType.CatPet;
				break;
			case 9:
				type = GadgetsType.AngeryCatPet;
				break;
			case 10:
				type = GadgetsType.SheepPet;
				break;
			case 11:
				type = GadgetsType.PigPet;
				break;
			case 12:
				type = GadgetsType.CatDisc;
				break;
			case 13:
				type = GadgetsType.ChripDisc;
				break;
			case 14:
				type = GadgetsType.MellohiDisc;
				break;
			case 15:
				type = GadgetsType.StalDisc;
				break;
			case 16:
				type = GadgetsType.WaitDisc;
				break;
			case 17:
				type = GadgetsType.StradDisc;
				break;
			case 18:
				type = GadgetsType.Disc13;
				break;
			case 19:
				type = GadgetsType.BlocksDisc;
				break;
			case 20:
				type = GadgetsType.FarDisc;
				break;
			case 21:
				type = GadgetsType.WardDisc;
				break;
			case 22:
				type = GadgetsType.Disc11;
				break;
			case 23:
				type = GadgetsType.MallDisc;
				break;
			}

			if (type != null) {
				return type;
			}

			return GadgetsType.Coins;
		}

		if (chest == RewardChest.Legendary) {
			int ran = random.nextInt(13 - 1) + 1;
			switch (ran) {
			case 1:
				GadgetsType gt = GadgetsType.Coins;
				gt.rc = RewardChest.Legendary;
				type = gt;
				break;
			case 2:
				type = GadgetsType.DiscoBoots;
				break;
			case 3:
				type = GadgetsType.DiscoHelmet;
				break;
			case 4:
				type = GadgetsType.DiscoChestPlate;
				break;
			case 5:
				type = GadgetsType.DiscoLeggings;
				break;
			case 6:
				type = GadgetsType.Enchanted;
				break;
			case 7:
				type = GadgetsType.InfernalHorse;
				break;
			case 8:
				type = GadgetsType.GlacialHorse;
				break;
			case 9:
				type = GadgetsType.SantaHorse;
				break;
			case 10:
				type = GadgetsType.HalloweenHorse;
				break;
			case 11:
				type = GadgetsType.NaturalHorse;
				break;
			case 12:
				type = GadgetsType.ZombieHorse;
				break;
			}

			if (type != null) {
				return type;
			}

			return GadgetsType.Coins;
		}

		GadgetsType gt = GadgetsType.Coins;
		gt.rc = RewardChest.Normal;
		return gt;
	}

}