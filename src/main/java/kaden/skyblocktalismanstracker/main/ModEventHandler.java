package kaden.skyblocktalismanstracker.main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.annotation.Nullable;

import kaden.skyblocktalismanstracker.gui.LoadingScreen;
import kaden.skyblocktalismanstracker.gui.ProfileScreen;
import kaden.skyblocktalismanstracker.gui.ProfileSelectionScreen;
import kaden.skyblocktalismanstracker.gui.ScreenTabs;
import kaden.skyblocktalismanstracker.gui.ScreenTypes;
import kaden.skyblocktalismanstracker.json.Variables;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModEventHandler {

	private enum LoadingStage {
		PRE, EXECUTING, DONE, CLOSED, ERROR
	}

	public static LoadingStage loadingStage;
	private static ScreenTypes currentPage;
	private static ScreenTabs currentTab;
	private static boolean openGui;
	private static boolean updateGui;
	private static File key = new File("config\\SBT\\apikey");
	public static int guiTicks = 0;
	public static Variables var;

	@SubscribeEvent
	public void readChat(ClientChatReceivedEvent event) {
		try {
			String message;
			try {
				if ((message = event.message.getUnformattedText()).substring(0, 20).equals("Your new API key is ")) {
					Variables.setApiKey(message.substring(20));
					openGui();
					event.setCanceled(true);
					if (!key.exists()) {
						key.getParentFile().mkdir();
						key.createNewFile();
					}
					Files.write(key.toPath(), message.substring(20).getBytes());
				}
			} catch (StringIndexOutOfBoundsException e) {
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SubscribeEvent
	public void renderGui(TickEvent.ClientTickEvent event) {
		if (openGui && Variables.pageNumber == 0) {
			guiTicks++;
			if(currentTab == null) {
				currentTab = ScreenTabs.COLLECTED_TALISMANS;
			}
			if (guiTicks == 1) {
				openGui(ScreenTypes.LOADING);
				loadingStage = LoadingStage.PRE;
			}
			if (guiTicks == 2 && loadingStage != LoadingStage.DONE) {
				loadingStage = LoadingStage.EXECUTING;
				runCommand();
			}
			if (loadingStage == LoadingStage.DONE) {
				guiTicks = 0;
				Variables.pageNumber = Variables.previousPage;
				switch (Variables.pageNumber) {
				case (0):
				case (1):
				default:
					Variables.pageNumber = 1;
					currentPage = ScreenTypes.SELECTION;
					break;
				case (2):
				case (3):
				case (4):
				case (5):
				case (6):
					currentPage = ScreenTypes.PROFILE;
					break;
				}
				openGui(currentPage);
			}
		} else if (updateGui) {
			if (guiTicks == 1) {
				openGui(currentPage);
				guiTicks = 0;
				updateGui = false;
			} else {
				guiTicks++;
			}
		} else {
			guiTicks = 0;
		}
	}

	public static void error() {
		closeGui();
		SkyBlockTalismansTracker.logger.error("An error occured.");
		loadingStage = LoadingStage.ERROR;
	}

	/**
	 * Only to be called from the <tt>renderGui()</tt> method after a tick, or
	 * nothing will render!
	 * 
	 * @param type The screen to be rendered.
	 */
	@SideOnly(value = Side.CLIENT)
	private static void openGui(ScreenTypes type) {
		try {
			switch (type) {
			case LOADING:
				Minecraft.getMinecraft().displayGuiScreen(new LoadingScreen(var));
				break;
			case PROFILE:
				Minecraft.getMinecraft().displayGuiScreen(new ProfileScreen(var, Variables.pageNumber - 2, currentTab));
				break;
			case SELECTION:
				Minecraft.getMinecraft().displayGuiScreen(new ProfileSelectionScreen(var));
				break;
			case PREVIOUS:
				if (Variables.pageNumber > 1) {
					Minecraft.getMinecraft().displayGuiScreen(new ProfileScreen(var, Variables.pageNumber - 2, currentTab));
				} else {
					Minecraft.getMinecraft().displayGuiScreen(new ProfileSelectionScreen(var));
				}
				break;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			closeGui();
		}
	}

	@SideOnly(value = Side.CLIENT)
	public static void closeGui() {
		Minecraft.getMinecraft().displayGuiScreen((GuiScreen) null);
		Variables.previousPage = Variables.pageNumber;
		guiTicks = 0;
		openGui = false;
		loadingStage = LoadingStage.CLOSED;
	}

	/**
	 * Used to start the GUI opening process. To flip screens use
	 * <tt>updateScreen(ScreenTypes, Integer)</tt> or
	 * <tt>updateTab(ScreenTabs, Integer)</tt>.
	 */
	public static void openGui() {
		Variables.pageNumber = 0;
		openGui = true;
	}

	private static void runCommand() {
		var = new Variables();
		loadingStage = LoadingStage.DONE;
	}

	/**
	 * Used to update the screen to another. To open the screen use
	 * <tt>openGui()</tt>.
	 * 
	 * @param screen  The screen you wish to display, <tt>ScreenTypes.SELECTION</tt>
	 *                or <tt>ScreenTypes.PROFILE</tt>, anything else will default to
	 *                <tt>ScreenTypes.SELECTION.</tt>
	 * @param profile The profile number you wish to display, 0-4 or <tt>null</tt>
	 *                for <tt>ScreenTypes.SELECTION</tt>
	 */
	public static void updateScreen(ScreenTypes screen, @Nullable Integer profile) {
		updateGui = true;
		switch (screen) {
		case SELECTION:
		default:
			Variables.pageNumber = 1;
			currentPage = ScreenTypes.SELECTION;
			break;
		case PROFILE:
			if (profile != null && profile >= 0 && profile < 5) {
				Variables.pageNumber = profile + 2;
				currentPage = ScreenTypes.PROFILE;
			}
			break;
		}
	}

	public static void updateTab(ScreenTabs tab) {
		updateGui = true;
		switch (tab) {
		case MISSING_TALISMANS:
		default:
			currentTab = ScreenTabs.MISSING_TALISMANS;
			break;
		case COLLECTED_TALISMANS:
			currentTab = ScreenTabs.COLLECTED_TALISMANS;
			break;
		}
	}

	public static ScreenTabs getCurrentTab() {
		return currentTab;
	}
}



