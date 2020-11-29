package kaden.skyblocktalismanstracker.gui;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import kaden.skyblocktalismanstracker.json.Variables;
import kaden.skyblocktalismanstracker.main.ModEventHandler;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class LoadingScreen extends GuiScreen {

	private String loadingCount;
	private ResourceLocation loading;
	Variables var;

	public LoadingScreen(Variables var) {
		this.var = var;
	}

	@Override
	public void handleKeyboardInput() throws IOException {
		if (Keyboard.getEventCharacter() == 27 || Keyboard.getEventCharacter() == 101) {
			onPageChange();
			ModEventHandler.closeGui();
		}

	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		int centerY = height / 2;
		int centerX = width / 2;

		this.drawDefaultBackground();
		updateLoadingTexture(ModEventHandler.guiTicks);
		if (loadingCount == null) {
			loading = new ResourceLocation(Variables.MODID, "textures/gui/loading/loading" + 0 + ".png");
		} else {
			loading = new ResourceLocation(Variables.MODID, "textures/gui/loading/loading" + loadingCount + ".png");
		}
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.enableBlend();
		GlStateManager.enableAlpha();
		this.drawCenteredString(fontRendererObj, "Loading...", centerX, centerY - 20, 0xFFFFFF);
		mc.renderEngine.bindTexture(loading);
		LoadingScreen.drawModalRectWithCustomSizedTexture(centerX - 16, centerY - 8, 0, 0, 32, 32, 32, 32);

		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	public void onPageChange() {
		mc.currentScreen.updateScreen();
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	public void updateLoadingTexture(int ticks) {
		loadingCount = String.valueOf(Math.floor(ticks / 2)).replace(".0", "");
	}

	@Override
	public void updateScreen() {
		super.updateScreen();
	}
}