package kaden.skyblocktalismanstracker.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Nullable;

import org.apache.logging.log4j.Level;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import kaden.skyblocktalismanstracker.main.ApiKeyInvalidException;
import kaden.skyblocktalismanstracker.main.ModEventHandler;
import kaden.skyblocktalismanstracker.main.SkyBlockTalismansTracker;

public class APIJsonPaser {

	private JsonObject[] profiles;

	APIJsonPaser() throws ApiKeyInvalidException {
		this.profiles = getProfiles(null);
	}

	APIJsonPaser(String UUID) throws ApiKeyInvalidException {
		this.profiles = getProfiles(UUID);
	}

	private JsonObject[] getProfiles(@Nullable String UUID) throws ApiKeyInvalidException {
		List<JsonObject> list = new ArrayList<JsonObject>();
		JsonArray profileArray;
		try {
			URL url;
			if (UUID != null) {
				url = new URL(
						"https://api.hypixel.net/skyblock/profiles?key=" + Variables.getApiKey() + "&uuid=" + UUID);
				SkyBlockTalismansTracker.logger.log(Level.INFO,
						"Contacting https://api.hypixel.net/skyblock/profiles?key=*REDACTED*&uuid=" + UUID);
			} else {
				url = new URL("https://api.hypixel.net/skyblock/profiles?key=" + Variables.getApiKey() + "&uuid="
						+ Variables.getUUID());
				SkyBlockTalismansTracker.logger.log(Level.INFO,
						"Contacting https://api.hypixel.net/skyblock/profiles?key=*REDACTED*&uuid="
								+ Variables.getUUID());
			}
			JsonReader reader = new JsonReader(new BufferedReader(new InputStreamReader(url.openStream())));
			profileArray = new JsonParser().parse(reader).getAsJsonObject().getAsJsonArray("profiles");
			reader.close();

		} catch (IOException e) {
			SkyBlockTalismansTracker.logger.log(Level.ERROR, "FAILED TO CONNECT");
			if (e.getMessage().contains(HttpURLConnection.HTTP_FORBIDDEN + "")) {
				throw new ApiKeyInvalidException();
			}
			ModEventHandler.error();
			return null;
		}
		SkyBlockTalismansTracker.logger.log(Level.ERROR, "SUCCESS");
		for (int i = 0; i != profileArray.size(); i++)
			list.add(profileArray.get(i).getAsJsonObject());
		return list.toArray(new JsonObject[] {});
	}

	public String[] getInventoryInBase64() {
		List<String> list = new ArrayList<>();
		for (int i = 0; i != 5; i++) {
			try {
				String inv = profiles[i].get("members").getAsJsonObject().get(Variables.getUUID()).getAsJsonObject()
						.get("inv_contents").getAsJsonObject().get("data").getAsString();
				list.add(inv);
			} catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
				list.add("empty");
			}
		}
		return list.toArray(new String[] {});
	}

	public String[] getAccessoriesInBase64() {
		List<String> list = new ArrayList<>();
		for (int i = 0; i != 5; i++) {
			try {
				String bag = profiles[i].get("members").getAsJsonObject().get(Variables.getUUID()).getAsJsonObject()
						.get("talisman_bag").getAsJsonObject().get("data").getAsString();
				list.add(bag);
			} catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
				list.add("empty");
			}
		}
		return list.toArray(new String[] {});
	}

	public String[] getArmourInBase64() {
		List<String> list = new ArrayList<>();
		for (int i = 0; i != 5; i++) {
			try {
				String armour = profiles[i].get("members").getAsJsonObject().get(Variables.getUUID()).getAsJsonObject()
						.get("inv_armor").getAsJsonObject().get("data").getAsString();
				list.add(armour);
			} catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
				list.add("empty");
			}
		}
		return list.toArray(new String[] {});
	}

	public String[] getMemberList() {
		List<String> list = new ArrayList<>();
		for (int i = 0; i != 5; i++) {
			try {
				String members = profiles[i].get("members").toString();
				list.add(members);
			} catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
				list.add("empty");
			}
		}
		return list.toArray(new String[] {});
	}

	public String[] getCuteNames() {
		List<String> list = new ArrayList<>();
		for (int i = 0; i != 5; i++) {
			try {
				String cuteNames = profiles[i].get("cute_name").getAsString();
				list.add(cuteNames);
			} catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
				list.add("empty");
			}
		}
		return list.toArray(new String[] {});
	}

	public String[] getProfileIDs() {
		List<String> list = new ArrayList<>();
		for (int i = 0; i != 5; i++) {
			try {
				String profileIDs = profiles[i].get("profile_id").getAsString();
				list.add(profileIDs);
			} catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
				list.add("empty");
			}
		}
		return list.toArray(new String[] {});
	}

	public int[] getBankBalance() {
		List<String> list = new ArrayList<>();
		for (int i = 0; i != 5; i++) {
			try {
				BigInteger balance = new BigDecimal(profiles[i].getAsJsonObject("banking").get("balance").getAsString())
						.toBigInteger();
				list.add(balance.toString());
			} catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
				list.add("0");
			}
		}
		return Arrays.stream(list.toArray(new String[] {})).mapToInt(value -> Integer.parseInt(value)).toArray();
	}

	public int getProfileCount() {
		List<String> list = new LinkedList<String>(Arrays.asList(getProfileIDs()));
		while (list.contains("empty")) {
			list.remove("empty");
		}
		return list.toArray().length;
	}
}