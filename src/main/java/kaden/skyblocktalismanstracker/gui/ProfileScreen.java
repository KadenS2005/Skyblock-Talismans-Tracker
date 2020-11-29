package kaden.skyblocktalismanstracker.gui;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import org.lwjgl.input.Keyboard;

import kaden.skyblocktalismanstracker.json.Variables;
import kaden.skyblocktalismanstracker.main.ModEventHandler;
import kaden.skyblocktalismanstracker.main.Talisman;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.ClickEvent.Action;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class ProfileScreen extends GuiScreen {

	int talismanX = 125;
	int talismanY = 0;
	int profileNumber;
	boolean mouseClicked = false;
	List<String> collectedTalismans;

	Variables var;
	Talisman talisman;
	ScreenTabs tab;

	GuiButton backButton = new GuiButton(1, 0, 0, 60, 20, "Back");
	GuiButton collectedTalismansButton = new GuiButton(2, 60, 0, 120, 20, "Collected Talismans");
	GuiButton missingTalismansButton = new GuiButton(3, 180, 0, 120, 20, "Missing Talismans");

	public ProfileScreen(Variables var, int profileNumber, ScreenTabs tab) {
		this.var = var;
		this.profileNumber = profileNumber;
		this.tab = tab;
		this.collectedTalismans = var.getCollectedTalismans(profileNumber);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		switch (tab) {
		case COLLECTED_TALISMANS:
			collectedTalismansButton.enabled = false;
			try {
				talismanY = 25;
				talismanX = 50;
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				GlStateManager.enableBlend();
				GlStateManager.enableAlpha();
				collectedTalismans.forEach((NBT) -> {
					if (NBT.equals("empty") || NBT == null) {
						drawString(fontRendererObj, "No talismans collected.", talismanX, talismanY, 0xFFFFFF);
						return;
					} else {
						if (NBT.equals(collectedTalismans.get(0))) {
							drawString(fontRendererObj, "Talismans collected:", talismanX - 25, talismanY, 0xFFFFFF);
							talismanY = 35;
						}
						talisman = new Talisman(NBT);
						drawString(fontRendererObj, NBT, talismanX, talismanY, talisman.getColor());
						GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
						mc.renderEngine.bindTexture(talisman.getImage());
						drawModalRectWithCustomSizedTexture(talismanX - 15, talismanY, 0, 0, 10, 10, 10, 10);
						talismanY = talismanY + 10;
						if (talismanY + 20 > height) {
							talismanX = talismanX + 150;
							talismanY = 35;
						}
					}
				});
				talismanY = 35;
				talismanX = 50;
				collectedTalismans.forEach((NBT) -> {
					if (!NBT.equals("empty") && NBT != null) {
						IChatComponent component;
						if (mouseX > talismanX - 15 && talismanX + fontRendererObj.getStringWidth(NBT) > mouseX
								&& talismanY <= mouseY && mouseY < talismanY + 10) {
							component = new ChatComponentText(NBT);
							component.getChatStyle()
									.setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, component));
							this.handleComponentHover(component, mouseX, mouseY);
							component.getChatStyle().setChatClickEvent(new ClickEvent(Action.OPEN_URL,
									"https://hypixel-skyblock.fandom.com/wiki/" + NBT.replaceAll(" ", "_")));
							if (mouseClicked) {
								mouseClicked = false;
								this.handleComponentClick(component);
							}
						}
						talismanY = talismanY + 10;
						if (talismanY + 20 > height) {
							talismanX = talismanX + 150;
							talismanY = 35;
						}
					}
				});
			} catch (ArrayIndexOutOfBoundsException | NullPointerException | NoSuchElementException e) {
				e.printStackTrace();
			}
			break;
		case MISSING_TALISMANS:
			//TODO
			missingTalismansButton.enabled = false;
			break;
		case STATS:
			break;
		default:
			break;
		}
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	public void initGui() {
		super.initGui();
		buttonList.add(missingTalismansButton);
		buttonList.add(collectedTalismansButton);
		buttonList.add(backButton);
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		mouseClicked = true;
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		switch (button.id) {
		case (1):
			ModEventHandler.updateScreen(ScreenTypes.SELECTION, null);
			break;
		case (2):
			ModEventHandler.updateTab(ScreenTabs.COLLECTED_TALISMANS);
			break;
		case (3):
			ModEventHandler.updateTab(ScreenTabs.MISSING_TALISMANS);
			break;
		case (4):
			IChatComponent test = new ChatComponentText("https://google.ca/");
			test.getChatStyle().setChatClickEvent(new ClickEvent(Action.OPEN_URL, "https://google.ca/"));
			this.handleComponentClick(test);
			break;
		}
	}

	@Override
	public void handleKeyboardInput() throws IOException {
		super.handleKeyboardInput();
		if (Keyboard.getEventCharacter() == 27 || Keyboard.getEventCharacter() == 101) {
			ModEventHandler.closeGui();
		}

	}

}