package kaden.skyblocktalismanstracker.json;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.GZIPInputStream;

import org.apache.commons.codec.binary.Base64;

import kaden.skyblocktalismanstracker.main.ModEventHandler;
import kaden.skyblocktalismanstracker.main.Talisman;

class Decoder {
	private static enum type {
		TALISMAN, ARMOUR
	}

	private static String decoded;
	private static String decodedString;
	private static String result;

	private static List<String> decode(String encoded, type decoderType) {
		if (!encoded.equals("empty") && encoded != null) {
			byte[] byteArray = Base64.decodeBase64(encoded);
			List<String> contents = new LinkedList<String>();
			result = "";
			try {

				GZIPInputStream gunzip = new GZIPInputStream(new ByteArrayInputStream(byteArray));
				BufferedReader read = new BufferedReader(new InputStreamReader(gunzip));

				while ((decoded = read.readLine()) != null) {
					result += decoded;
				}
				int indexStart = result.indexOf("Name");
				int indexEnd = result.indexOf("ExtraAttributes");
				for (int a = 0; a <= 100; a++) {
					try {
						if (result.equals("error") || result.equals("")) {
							ModEventHandler.error();
							return Arrays.asList(Variables.error);
						} else if (!result.contains("Name")) {
							return Arrays.asList(new String[] { "empty" });
						} else {
							decodedString = result
									.substring(result.indexOf("Name", indexStart) + 9,
											result.indexOf("ExtraAttributes", indexEnd))
									.replaceAll("[^a-zA-Z0-9\' ]", "").replace("Bizarre ", "").replace("Demonic ", "")
									.replace("Forceful ", "").replace("Godly ", "").replace("Hurtful ", "")
									.replace("Itchy ", "").replace("Keen ", "").replace("Ominous ", "")
									.replace("Pleasant ", "").replace("Pretty ", "").replace("Shiny ", "")
									.replace("Simple ", "").replace("Strange ", "").replace("Strong ", "")
									.replace("Superiour ", "").replace("Unpleasant ", "").replace("Zealous ", "")
									.replace("Fierce ", "").trim();
							if (decodedString.contains("Melody's Hair")) {
								decodedString = "Melody's Hair";
							}
							if (new Talisman(decodedString).isTalisman() || decoderType.equals(type.ARMOUR)) {
								contents.add(decodedString);
							}
							indexStart = result.indexOf("Name", indexStart + 1);
							indexEnd = result.indexOf("ExtraAttributes", indexEnd + 1);
							if (indexStart < result.indexOf("Name")) {
								break;
							}
						}
					} catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
						e.printStackTrace();
						a = 100;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			return contents;
		}
		return Arrays.asList(new String[] { encoded });
	}

	static Map<Integer, List<String>> getTalismans(String[] bagEncoded, String[] invEncoded) {
		Map<Integer, List<String>> map = new HashMap<>();
		map.put(0, Stream
				.concat(decode(bagEncoded[0], type.TALISMAN).stream(), decode(invEncoded[0], type.TALISMAN).stream())
				.collect(Collectors.toList()));
		map.put(1, Stream
				.concat(decode(bagEncoded[1], type.TALISMAN).stream(), decode(invEncoded[1], type.TALISMAN).stream())
				.collect(Collectors.toList()));
		map.put(2, Stream
				.concat(decode(bagEncoded[2], type.TALISMAN).stream(), decode(invEncoded[2], type.TALISMAN).stream())
				.collect(Collectors.toList()));
		map.put(3, Stream
				.concat(decode(bagEncoded[3], type.TALISMAN).stream(), decode(invEncoded[3], type.TALISMAN).stream())
				.collect(Collectors.toList()));
		map.put(4, Stream
				.concat(decode(bagEncoded[4], type.TALISMAN).stream(), decode(invEncoded[4], type.TALISMAN).stream())
				.collect(Collectors.toList()));
		return map;
	}

	static Map<Integer, List<String>> getArmour(String[] armourEncoded) {
		Map<Integer, List<String>> map = new HashMap<>();
		map.put(0, decode(armourEncoded[0], type.ARMOUR));
		map.put(1, decode(armourEncoded[1], type.ARMOUR));
		map.put(2, decode(armourEncoded[2], type.ARMOUR));
		map.put(3, decode(armourEncoded[3], type.ARMOUR));
		map.put(4, decode(armourEncoded[4], type.ARMOUR));
		return map;
	}
}