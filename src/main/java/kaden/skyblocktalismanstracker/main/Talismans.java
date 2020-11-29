package kaden.skyblocktalismanstracker.main;

import java.lang.reflect.Field;
import java.util.Arrays;

import kaden.skyblocktalismanstracker.json.Variables;
import net.minecraft.util.ResourceLocation;

public class Talismans {
	private Talismans() {
		Arrays.asList(this.getClass().getFields()).forEach(i -> {
			try {
				Talisman.register((Talisman) ((Field) i).get(new Talisman("")));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		});
	}

	static void register() {
		new Talismans();
	}

	public static final Talisman FARMING_TALISMAN = new Talisman("Farming Talisman", 0, false);
	public static final Talisman FEATHER_TALISMAN = new Talisman("Feather Talisman", 0, true);
	public static final Talisman FIRE_TALISMAN = new Talisman("Fire Talisman", 0, false);
	public static final Talisman HEALING_TALISMAN = new Talisman("Healing Talisman", 0, true);
	public static final Talisman INTIMIDATION_TALISMAN = new Talisman("Intimidation Talisman", 0, true);
	public static final Talisman MINE_AFFINITY_TALISMAN = new Talisman("Mine Affinity Talisman", 0, false);
	public static final Talisman POTATO_TALISMAN = new Talisman("Potato Talisman", 0, false);
	public static final Talisman POTION_AFFINITY_TALISMAN = new Talisman("Potion Affinity Talisman", 0, true);
	public static final Talisman SCAVENGER_TALISMAN = new Talisman("Scavenger Talisman", 0, false);
	public static final Talisman SEA_CREATURE_TALISMAN = new Talisman("Sea Creature Talisman", 0, true);
	public static final Talisman SKELETON_TALISMAN = new Talisman("Skeleton Talisman", 0, false);
	public static final Talisman SPEED_TALISMAN = new Talisman("Speed Talisman", 0, true);
	public static final Talisman COIN_TALISMAN = new Talisman("Talisman of Coins", 0, false);
	public static final Talisman VACCINE_TALISMAN = new Talisman("Vaccine Talisman", 0, false);
	public static final Talisman VILLAGE_AFFINITY_TALISMAN = new Talisman("Village Affinity Talisman", 0, false);
	public static final Talisman WOLF_TALISMAN = new Talisman("Wolf Talisman", 0, true);
	public static final Talisman ZOMBIE_TALISMAN = new Talisman("Zombie Talisman", 0, true);
	public static final Talisman CAMPFIRE_INIATE_BADGE = new Talisman("Campfire Initiate Badge", 0, true)
			.setImage(new ResourceLocation(Variables.MODID, "textures/gui/talismans/Campfire_Badge.png"));
	public static final Talisman NIGHT_VISION_CHARM = new Talisman("Night Vision Charm", 0, false);
	public static final Talisman SHINY_YELLOW_ROCK = new Talisman("Shiny Yellow Rock", 0, true);
	public static final Talisman YELLOW_LOVE_ROCK = new Talisman("Yellow Rock of Love", 0, true);
	public static final Talisman CRAB_HAT_OF_CELEBRATION = new Talisman("Crab Hat of Celebration", 0, false);
	public static final Talisman SPEED_RING = new Talisman("Speed Ring", 1, true);
	public static final Talisman CANDY_TALISMAN = new Talisman("Candy Talisman", 1, true);
	public static final Talisman CAT_TALISMAN = new Talisman("Cat Talisman", 1, true);
	public static final Talisman GRAVITY_TALISMAN = new Talisman("Gravity Talisman", 1, false);
	public static final Talisman HUNTER_TALISMAN = new Talisman("Hunter Talisman", 1, true);
	public static final Talisman LAVA_TALISMAN = new Talisman("Lava Talisman", 1, false);
	public static final Talisman MAGNETIC_TALISMAN = new Talisman("Magnetic Talisman", 1, false);
	public static final Talisman RED_CLAW_TALISMAN = new Talisman("Red Claw Talisman", 1, true);
	public static final Talisman SPIDER_TALISMAN = new Talisman("Spider Talisman", 1, true);
	public static final Talisman WOOD_AFFINITY_TALISMAN = new Talisman("Wood Affinity Talisman", 1, false);
	public static final Talisman FEATHER_RING = new Talisman("Feather Ring", 1, true);
	public static final Talisman HEALING_RING = new Talisman("Healing Ring", 1, false);
	public static final Talisman INTIMIDATION_RING = new Talisman("Intimidation Ring", 1, true);
	public static final Talisman POTION_AFFINITY_RING = new Talisman("Potion Affinity Ring", 1, true);
	public static final Talisman SEA_CREATURE_RING = new Talisman("Sea Creature Ring", 1, true);
	public static final Talisman SHADY_RING = new Talisman("Shady Ring", 1, true);
	public static final Talisman ZOMBIE_RING = new Talisman("Zombie Ring", 1, true);
	public static final Talisman MEDIOCRE_LOVE_RING = new Talisman("Mediocre Ring of Love", 1, true)
			.setImage(new ResourceLocation(Variables.MODID, "textures/gui/talismans/Ring_of_Love.png"));
	public static final Talisman RUBBISH_LOVE_RING = new Talisman("Rubbish Ring of Love", 1, true)
			.setImage(new ResourceLocation(Variables.MODID, "textures/gui/talismans/Ring_of_Love.png"));
	public static final Talisman CAMPFIRE_ADEPT_BADGE = new Talisman("Campfire Adept Badge", 1, true)
			.setImage(new ResourceLocation(Variables.MODID, "textures/gui/talismans/Campfire_Badge.png"));
	public static final Talisman FARMING_ORB = new Talisman("Farmer Orb", 1, false);
	public static final Talisman NEW_YEAR_CAKE_BAG = new Talisman("New Year Cake Bag", 1, false);
	public static final Talisman PERSONAL_COMPACTOR_4000 = new Talisman("Personal Compactor 4000", 1, true)
			.setImage(new ResourceLocation(Variables.MODID, "textures/gui/talismans/Compactor.png"));
	public static final Talisman PIGGY_BANK = new Talisman("Piggy Bank", 1, false).setCustomColor(0xFFFF55FF);
	public static final Talisman CRACKED_PIGGY_BANK = new Talisman("Cracked Piggy Bank", 1, true)
			.setCustomColor(0xFFFF5555);
	public static final Talisman BROKEN_PIGGY_BANK = new Talisman("Broken Piggy Bank", 1, true)
			.setCustomColor(0xFFAAAAAA);
	public static final Talisman WOLF_PAW = new Talisman("Wolf Paw", 1, false);
	public static final Talisman BAT_TALISMAN = new Talisman("Bat Talisman", 2, true);
	public static final Talisman FISH_AFFINITY_TALISMAN = new Talisman("Fish Affinity Talisman", 2, false);
	public static final Talisman LYNX_TALISMAN = new Talisman("Lynx Talisman", 2, true);
	public static final Talisman TREASURE_TALISMAN = new Talisman("Treasure Talisman", 2, true);
	public static final Talisman BAIT_TALISMAN = new Talisman("Bait Ring", 2, false);
	public static final Talisman CANDY_RING = new Talisman("Candy Ring", 2, true);
	public static final Talisman DEVOUR_RING = new Talisman("Devour Ring", 2, false);
	public static final Talisman HASTE_RING = new Talisman("Haste Ring", 2, false);
	public static final Talisman HUNTER_RING = new Talisman("Hunter Ring", 2, false);
	public static final Talisman RED_CLAW_RING = new Talisman("Red Claw Ring", 2, true);
	public static final Talisman SPIDER_RING = new Talisman("Spider Ring", 2, true);
	public static final Talisman WOLF_RING = new Talisman("Wolf Ring", 2, true);
	public static final Talisman MODEST_LOVE_RING = new Talisman("Modest Ring of Love", 2, true)
			.setImage(new ResourceLocation(Variables.MODID, "textures/gui/talismans/Ring_of_Love.png"));
	public static final Talisman REFINED_LOVE_RING = new Talisman("Refined Ring of Love", 2, true)
			.setImage(new ResourceLocation(Variables.MODID, "textures/gui/talismans/Ring_of_Love.png"));
	public static final Talisman CLASSY_LOVE_RING = new Talisman("Classy Ring of Love", 2, true)
			.setImage(new ResourceLocation(Variables.MODID, "textures/gui/talismans/Ring_of_Love.png"));
	public static final Talisman CROOKED_ARTIFACT = new Talisman("Crooked Artifact", 2, true);
	public static final Talisman FEATHER_ARTIFACT = new Talisman("Feather Artifact", 2, false);
	public static final Talisman INTIMIDATION_ARTIFACT = new Talisman("Intimidation Artifact", 2, false);
	public static final Talisman POTION_AFFINITY_ARTIFACT = new Talisman("Potion Affinity Artifact", 2, false);
	public static final Talisman SEA_CREATURE_ARTIFACT = new Talisman("Sea Creature Artifact", 2, false);
	public static final Talisman ZOMBIE_ARTIFACT = new Talisman("Zombie Artifact", 2, false);
	public static final Talisman SPEED_ARTIFACT = new Talisman("Speed Artifact", 2, false);
	public static final Talisman CAMPFIRE_CULTIST_BADGE = new Talisman("Campfire Cultist Badge", 2, true)
			.setImage(new ResourceLocation(Variables.MODID, "textures/gui/talismans/Campfire_Badge.png"));
	public static final Talisman DAY_CRYSTAL = new Talisman("Day Crystal", 2, false);
	public static final Talisman FROZEN_CHICKEN = new Talisman("Frozen Chicken", 2, false);
	public static final Talisman NIGHT_CRYSTAL = new Talisman("Night Crystal", 2, false);
	public static final Talisman PERSONAL_COMPACTOR_5000 = new Talisman("Personal Compactor 5000", 2, true)
			.setImage(new ResourceLocation(Variables.MODID, "textures/gui/talismans/Compactor.png"));
	public static final Talisman PIGS_FOOT = new Talisman("Pig's Foot", 2, false);
	public static final Talisman SURVIVOR_CUBE = new Talisman("Survivor Cube", 2, false);
	public static final Talisman SCARFS_STUDIES = new Talisman("Scarf's Studies", 2, true);
	public static final Talisman CHEETAH_TALISMAN = new Talisman("Cheetah Talisman", 3, false);
	public static final Talisman TARANTULA_TALISMAN = new Talisman("Tarantula Talisman", 3, false);
	public static final Talisman BAT_RING = new Talisman("Bat Ring", 3, true);
	public static final Talisman EXQUISITE_LOVE_RING = new Talisman("Exquisite Ring of Love", 3, true)
			.setImage(new ResourceLocation(Variables.MODID, "textures/gui/talismans/Ring_of_Love.png"));
	public static final Talisman INVALUABLE_LOVE_RING = new Talisman("Invaluable Ring of Love", 3, true)
			.setImage(new ResourceLocation(Variables.MODID, "textures/gui/talismans/Ring_of_Love.png"));
	public static final Talisman TREASURE_RING = new Talisman("Treasure Ring", 3, true);
	public static final Talisman CANDY_ARTIFACT = new Talisman("Candy Artifact", 3, false);
	public static final Talisman ENDER_ARTIFACT = new Talisman("Ender Artifact", 3, false);
	public static final Talisman EXPERIENCE_ARTIFACT = new Talisman("Experience Artifact", 3, false);
	public static final Talisman RED_CLAW_ARTIFACT = new Talisman("Red Claw Artifact", 3, false);
	public static final Talisman SPIDER_ARTIFACT = new Talisman("Spider Artifact", 3, false);
	public static final Talisman WITHER_ARTIFACT = new Talisman("Wither Artifact", 3, false);
	public static final Talisman CAMPFIRE_SCION_BADGE = new Talisman("Campfire Scion Badge", 3, true)
			.setImage(new ResourceLocation(Variables.MODID, "textures/gui/talismans/Campfire_Badge.png"));
	public static final Talisman MELODYS_HAIR = new Talisman("Melody's Hair", 3, false).setCustomColor(0xFFFF55FF);
	public static final Talisman PERSONAL_COMPACTOR_6000 = new Talisman("Personal Compactor 6000", 3, false)
			.setImage(new ResourceLocation(Variables.MODID, "textures/gui/talismans/Compactor.png"));
	public static final Talisman SEAL_OF_THE_FAMILY = new Talisman("Seal of the Family", 3, false);
	public static final Talisman SCARFS_THESIS = new Talisman("Scarf's Thesis", 3, true);
	public static final Talisman LEGENDARY_LOVE_RING = new Talisman("Legendary Ring of Love", 4, false)
			.setImage(new ResourceLocation(Variables.MODID, "textures/gui/talismans/Ring_of_Love.png"));
	public static final Talisman BAT_ARTIFACT = new Talisman("Bat Artifact", 4, false);
	public static final Talisman TREASURE_ARTIFACT = new Talisman("Treasure Artifact", 4, false);
	public static final Talisman CAMPFIRE_GOD_BADGE = new Talisman("Campfire God Badge", 4, false)
			.setImage(new ResourceLocation(Variables.MODID, "textures/gui/talismans/Campfire_Badge.png"));
	public static final Talisman SCARFS_GRIMOIRE = new Talisman("Scarf's Grimoire", 4, false);
}