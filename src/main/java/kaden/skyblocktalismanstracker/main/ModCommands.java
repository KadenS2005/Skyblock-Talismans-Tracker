package kaden.skyblocktalismanstracker.main;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;

public class ModCommands extends CommandBase {

	private String command = "skyblocktalismans";
	private List<String> aliases = new ArrayList<String>();
	private int level = 0;
	private Minecraft mc = Minecraft.getMinecraft();

	@Override
	public int getRequiredPermissionLevel() {
		return level;
	}

	@Override
	public String getCommandName() {
		return command;
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return command;
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		if (!mc.isSingleplayer() && mc.getCurrentServerData().serverIP.equals("mc.hypixel.net")) {
			ModEventHandler.openGui();
		} else {
			sender.addChatMessage(new ChatComponentText("Please play on Hypixel to view your talismans.")
					.setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
		}
	}

	@Override
	public List<String> getCommandAliases() {
		aliases.add("sbt");
		aliases.add("skyblocktalismantracker");
		aliases.add("skyblocktalismanstracker");
		return aliases;
	}
}
