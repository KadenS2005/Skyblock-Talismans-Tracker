package kaden.skyblocktalismanstracker.json;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import kaden.skyblocktalismanstracker.main.ApiKeyInvalidException;
import kaden.skyblocktalismanstracker.main.ModEventHandler;
import net.minecraft.client.Minecraft;

public class Variables {

	public static final String MODID = "skyblocktalismanstracker";
	public static final String VERSION = "0.2.2";
	public static final String NAME = "SkyBlock Talismans Tracker";
	public static final String[] error = { "error" };
	public static int pageNumber = 0;
	public static int previousPage = 1;
	static Variables instance;

	private static final String UUID = Minecraft.getMinecraft().thePlayer.getGameProfile().getId()
			.toString().replaceAll("-", "");
	private static String apiKey;
	private Map<Integer, List<String>> collectedTalismans;
	private Map<Integer, List<String>> armourWorn;
	private String[] cuteNames;
	private String[] profileIDs;
	private int[] bankBalances;

	APIJsonPaser jsonReader;

	public Variables() {
		instance = ModEventHandler.var;
		apiKey = getApiKey();
		try {
			if (apiKey == null) {
				throw new ApiKeyInvalidException();
			}
			jsonReader = new APIJsonPaser();
			cuteNames = jsonReader.getCuteNames();
			bankBalances = jsonReader.getBankBalance();
			profileIDs = jsonReader.getProfileIDs();
			collectedTalismans = Decoder.getTalismans(jsonReader.getAccessoriesInBase64(),
					jsonReader.getInventoryInBase64());
			armourWorn = Decoder.getArmour(jsonReader.getArmourInBase64());
		} catch (ApiKeyInvalidException e) {
			e.printStackTrace();
			File key = new File("config\\SBT\\apikey");
			if (key.exists()) {
				key.delete();
			}
			Minecraft.getMinecraft().thePlayer.sendChatMessage("/api new");
			ModEventHandler.error();
		}
	}

	static String getApiKey() {
		if (!new File("config\\SBT\\apikey").exists()) {
			return null;
		}
		try {
			apiKey = new String(Files.readAllBytes(new File("config\\SBT\\apikey").toPath()));
			ModEventHandler.openGui();
		} catch (IOException e) {
			return null;
		}
		return apiKey;
	}

	public List<String> getCollectedTalismans(int profileNumber) {
		return collectedTalismans.get(profileNumber);
	}

	public String[] getArmourWorn(int profileNumber) {
		return armourWorn.get(profileNumber).toArray(new String[] {});
	}

	public String[] getCuteNames() {
		return cuteNames;
	}

	public int[] getBankBalances() {
		return bankBalances;
	}

	public String[] getProfileIDs() {
		return profileIDs;
	}

	public int getProfileCount() {
		return jsonReader.getProfileCount();
	}

	public static String getUUID() {
		return UUID;
	}

	public static void setApiKey(String key) {
		apiKey = key;
	}

	public static InputStream getInputStream(String filePath) throws NoSuchFileException {
		String data = "";
		try {
			data = new String(Files.readAllBytes(Paths.get(filePath)));
		} catch (IOException e) {
		}
		return IOUtils.toInputStream(data);
	}
}
