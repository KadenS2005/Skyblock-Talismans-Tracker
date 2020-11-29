package kaden.skyblocktalismanstracker.main;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import kaden.skyblocktalismanstracker.json.Variables;
import net.minecraft.util.ResourceLocation;
import java.util.Arrays;

/**
 * In-game Hypixel Skyblock Talisman Object
 */
public class Talisman {
	/**
	 * The in-game name of the talisman.
	 */
	private String name;
	/**
	 * The rarity of the talisman as an int; 0 is common, 1 uncommon, 2 is rare, 3
	 * is epic and 4 is legendary.
	 */
	private int rarity;
	/**
	 * Is the talisman able to be upgraded?
	 */
	private boolean isUpgradable;
	/**
	 * Color of the Talisman in Hexadecimal.
	 */
	private int color;
	/**
	 * 
	 */
	private ResourceLocation textureLocation;
	/**
	 * List of all registered talismans.
	 */
	private static List<Talisman> talismanList = new LinkedList<>();

	/**
	 * Used to get the rarity, upgradability and existence of a talisman by
	 * specifying its name.
	 * 
	 * @param name The name of a possible talisman.
	 */
	public Talisman(String name) {
		this.name = name;
		this.rarity = getRarityFromName();
		this.isUpgradable = getUpgradabilityFromName();
		this.color = getColorFromName();
		this.textureLocation = getTextureLocationFromName();
	}

	/**
	 * Used to register talismans.
	 * 
	 * @param name         Name of the talisman.
	 * @param rarity       Rarity of the talisman.
	 * @param isUpgradable Upgradability of the talisman.
	 */
	public Talisman(String name, int rarity, boolean isUpgradable) {
		this.name = name;
		this.rarity = rarity;
		this.isUpgradable = isUpgradable;
		textureLocation = new ResourceLocation(Variables.MODID, "textures/gui/talismans/" + name.replaceAll(" ", "_") + ".png");
		switch (rarity) {
		case (0):
			color = 0xFFFFFF;
			break;
		case (1):
			color = 0x55FF55;
			break;
		case (2):
			color = 0x5555FF;
			break;
		case (3):
			color = 0xAA00AA;
			break;
		case (4):
			color = 0xFFAA00;
			break;
		default:
			color = 0xFFFFFF;
			break;
		}
	}

	private boolean getUpgradabilityFromName() {
		Iterator<Talisman> i = talismanList.iterator();
		while (i.hasNext()) {
			Talisman talisman = i.next();
			if (talisman.getName().equals(name)) {
				return talisman.getUpgradability();
			}
		}
		return false;
	}

	private int getRarityFromName() {
		Iterator<Talisman> i = talismanList.iterator();
		while (i.hasNext()) {
			Talisman talisman = i.next();
			if (talisman.getName().equals(name)) {
				return talisman.getRarity();
			}
		}
		return 5;
	}

	private int getColorFromName() {
		Iterator<Talisman> i = talismanList.iterator();
		while (i.hasNext()) {
			Talisman talisman = i.next();
			if (talisman.getName().equals(name)) {
				return talisman.getColor();
			}
		}
		return 0xFFFFFF;
	}
	
	 private ResourceLocation getTextureLocationFromName() {
		 Iterator<Talisman> i = talismanList.iterator();
			while (i.hasNext()) {
				Talisman talisman = i.next();
				if (talisman.getName().equals(name)) {
					return talisman.getImage();
				}
			}
			return new ResourceLocation("");
	}
	
	/**
	 * @return If the talisman is upgradable or not.
	 */
	public boolean getUpgradability() {
		return isUpgradable;
	}

	/**
	 * @return The rarity of the talisman as an int, 0 is common, 1 uncommon, 2 is
	 *         rare, 3 is epic, 4 is legendary and 5 means unknown or its not a
	 *         talisman.
	 */
	public int getRarity() {
		return rarity;
	}

	/**
	 * @return The rarity of the talisman as a readable string, if the talisman is
	 *         unknown or not a talisman it will
	 */
	public String getRarityAsAString() {
		switch (rarity) {
		case (0):
			return "common";
		case (1):
			return "uncommon";
		case (2):
			return "rare";
		case (3):
			return "epic";
		case (4):
			return "legendary";
		case (5):
			return "unknown";
		default:
			return "error";
		}
	}

	/**
	 * @return The color, in hexadecimal, of the talisman's in-game text based on
	 *         it's <tt>rarity</tt> or the custom hex color specified using
	 *         <tt>setCustomColor(int)</tt>.
	 */
	public int getColor() {
		return color;
	}

	/**
	 * Used to specify custom talisman colors.
	 * 
	 * @param color An ARGB color value. Eg. 0xAABBCCDD, 
	 * AA is the alpha or opacity of the color, FF is for 100% opacity.
	 * BB is the red value.
	 * CC is the blue value.
	 * DD is the green value.
	 * In decimal this number is converted to -1430532899.
	 * @return The instance of the Talisman to chain setters.
	 */
	public Talisman setCustomColor(Integer color) {
		this.color = color;
		return this;
	}

	/**
	 * @return The in-game <tt>name</tt> of the talisman.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return An array of Talismans that have been registered.
	 */
	public static Talisman[] talismanList() {
		return talismanList.toArray(new Talisman[] {});
	}

	/**
	 * @return 
	 * @return If the talisman is on the talismanList.
	 */
	public boolean isTalisman() {
		return talismanList.stream().anyMatch(i ->{
			return name.contains(i.name);
		});
	}

	/**
	 * @return A ResourceLocation to the image of the talisman.
	 */
	public ResourceLocation getImage() {
		return textureLocation;
	}
	
	/**
	 * 
	 */
	public Talisman setImage(ResourceLocation loc) {
		textureLocation = loc;
		return this;
	}
	
	/**
	 * @return A JSON Object as a String with the name, rarity and upgrability of
	 *         the talisman.
	 */
	@Override
	public String toString() {
		JsonObject obj = new JsonObject();
		obj.addProperty("name", name);
		obj.addProperty("rarity", getRarityAsAString());
		obj.addProperty("isUpgradable", isUpgradable);
		return new GsonBuilder().setPrettyPrinting().create().toJson(obj);
	}

	/**
	 * Adds the talisman to the list of talismans.
	 * 
	 * @param talisman
	 */
	public static void register(Talisman ...talisman) {
		Arrays.asList(talisman).forEach(i-> talismanList.add((Talisman) i));
	}

	

}