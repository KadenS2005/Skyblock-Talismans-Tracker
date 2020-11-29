package kaden.skyblocktalismanstracker.gui;

import java.io.IOException;

import kaden.skyblocktalismanstracker.json.Variables;
import kaden.skyblocktalismanstracker.main.ModEventHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class ProfileSelectionScreen extends GuiScreen {

	private static ResourceLocation profileLocation0;
	private static ResourceLocation profileLocation1;
	private static ResourceLocation profileLocation2;
	private static ResourceLocation profileLocation3;
	private static ResourceLocation profileLocation4;

	private Variables var;
	private int profileCount;

	public ProfileSelectionScreen(Variables var) {

		this.var = var;
		profileCount = var.getProfileCount() - 1;

		switch (profileCount) {
		case (0):
			profileLocation0 = new ResourceLocation(Variables.MODID,
					"textures/gui/fruits/" + var.getCuteNames()[0] + ".png");
			break;
		case (1):
			profileLocation0 = new ResourceLocation(Variables.MODID,
					"textures/gui/fruits/" + var.getCuteNames()[0] + ".png");
			profileLocation1 = new ResourceLocation(Variables.MODID,
					"textures/gui/fruits/" + var.getCuteNames()[1] + ".png");
			break;
		case (2):
			profileLocation0 = new ResourceLocation(Variables.MODID,
					"textures/gui/fruits/" + var.getCuteNames()[0] + ".png");
			profileLocation1 = new ResourceLocation(Variables.MODID,
					"textures/gui/fruits/" + var.getCuteNames()[1] + ".png");
			profileLocation2 = new ResourceLocation(Variables.MODID,
					"textures/gui/fruits/" + var.getCuteNames()[2] + ".png");
			break;
		case (3):
			profileLocation0 = new ResourceLocation(Variables.MODID,
					"textures/gui/fruits/" + var.getCuteNames()[0] + ".png");
			profileLocation1 = new ResourceLocation(Variables.MODID,
					"textures/gui/fruits/" + var.getCuteNames()[1] + ".png");
			profileLocation2 = new ResourceLocation(Variables.MODID,
					"textures/gui/fruits/" + var.getCuteNames()[2] + ".png");
			profileLocation3 = new ResourceLocation(Variables.MODID,
					"textures/gui/fruits/" + var.getCuteNames()[3] + ".png");
			break;
		case (4):
			profileLocation0 = new ResourceLocation(Variables.MODID,
					"textures/gui/fruits/" + var.getCuteNames()[0] + ".png");
			profileLocation1 = new ResourceLocation(Variables.MODID,
					"textures/gui/fruits/" + var.getCuteNames()[1] + ".png");
			profileLocation2 = new ResourceLocation(Variables.MODID,
					"textures/gui/fruits/" + var.getCuteNames()[2] + ".png");
			profileLocation3 = new ResourceLocation(Variables.MODID,
					"textures/gui/fruits/" + var.getCuteNames()[3] + ".png");
			profileLocation4 = new ResourceLocation(Variables.MODID,
					"textures/gui/fruits/" + var.getCuteNames()[4] + ".png");
			break;
		}
	}

	@Override
	public void initGui() {
		super.initGui();
		int centerX = width / 2;
		int centerY = height / 2;
		int boxLength = width / 4;
		int offset = boxLength / 6;
		int boxPos[][] = {
				// Box X Box Y
				/* Box # */ { centerX - boxLength / 2 - boxLength, centerY - boxLength },
				{ centerX - boxLength / 2, centerY - boxLength },
				{ centerX - boxLength / 2 + boxLength, centerY - boxLength }, { centerX - boxLength, centerY },
				{ centerX, centerY } };
		if (!mc.isFullScreen())
			offset = boxLength / 4;

		int buttonPos[][] = { { boxPos[0][0] + boxLength / 8, boxPos[0][1] + boxLength - offset },
				{ boxPos[1][0] + boxLength / 8, boxPos[1][1] + boxLength - offset },
				{ boxPos[2][0] + boxLength / 8, boxPos[2][1] + boxLength - offset },
				{ boxPos[3][0] + boxLength / 8, boxPos[3][1] + boxLength - offset },
				{ boxPos[4][0] + boxLength / 8, boxPos[4][1] + boxLength - offset } };

		if (profileCount >= 0)
			this.buttonList.add(new GuiButton(1, buttonPos[0][0], buttonPos[0][1], boxLength / 2 + boxLength / 4, 20,
					"Select Profile"));
		if (profileCount >= 1)
			this.buttonList.add(new GuiButton(2, buttonPos[1][0], buttonPos[1][1], boxLength / 2 + boxLength / 4, 20,
					"Select Profile"));
		if (profileCount >= 2)
			this.buttonList.add(new GuiButton(3, buttonPos[2][0], buttonPos[2][1], boxLength / 2 + boxLength / 4, 20,
					"Select Profile"));
		if (profileCount >= 3)
			this.buttonList.add(new GuiButton(4, buttonPos[3][0], buttonPos[3][1], boxLength / 2 + boxLength / 4, 20,
					"Select Profile"));
		if (profileCount >= 4)
			this.buttonList.add(new GuiButton(5, buttonPos[4][0], buttonPos[4][1], boxLength / 2 + boxLength / 4, 20,
					"Select Profile"));
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		if (profileLocation0 == null) {
			ModEventHandler.closeGui();
			return;
		}
		float textScale = 1.5F;

		int boxLength = width / 4;
		int fruitLength = boxLength / 12;
		int centerX = width / 2;
		int centerY = height / 2;
		int boxPos[][] = {
				// Box X Box Y
				/* Box # */ { centerX - boxLength / 2 - boxLength, centerY - boxLength },
				{ centerX - boxLength / 2, centerY - boxLength },
				{ centerX - boxLength / 2 + boxLength, centerY - boxLength }, { centerX - boxLength, centerY },
				{ centerX, centerY } };
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.enableBlend();
		GlStateManager.enableAlpha();
		if (profileCount >= 0) {
			textScale = 1.5F;
			if (!mc.isFullScreen())
				textScale = 1.0F;
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			mc.renderEngine.bindTexture(new ResourceLocation(Variables.MODID, "textures/gui/profilebox.png"));
			LoadingScreen.drawModalRectWithCustomSizedTexture(boxPos[0][0], boxPos[0][1], 0, 0, boxLength, boxLength,
					boxLength, boxLength);
			mc.renderEngine.bindTexture(profileLocation0);
			LoadingScreen.drawModalRectWithCustomSizedTexture(boxPos[0][0] + boxLength / 24,
					boxPos[0][1] + boxLength / 22, 0, 0, fruitLength, fruitLength, fruitLength, fruitLength);
			GlStateManager.pushMatrix();
			{
				GlStateManager.scale(textScale, textScale, textScale);
				fontRendererObj.drawStringWithShadow(var.getCuteNames()[0], (boxPos[0][0] + boxLength / 7) / textScale,
						(boxPos[0][1] + boxLength / 20) / textScale, 0xFFFFFF);
			}
			GlStateManager.popMatrix();
			String[] armour;
			textScale = 0.8F;
			String helmet = "No helmet equipped";
			String chestplate = "No chestplate equipped";
			String leggings = "No leggings equipped";
			String boots = "No boots equipped";
			if (!(armour = var.getArmourWorn(0))[0].equals("empty")) {
				helmet = armour[3];
				chestplate = armour[2];
				leggings = armour[1];
				boots = armour[0];
			}
			GlStateManager.pushMatrix();
			{
				GlStateManager.scale(textScale, textScale, textScale);
				fontRendererObj.drawStringWithShadow(helmet, (boxPos[0][0] + boxLength / 7) / textScale,
						(boxPos[0][1] + (boxLength / 20) * 3) / textScale, 0xFFFFFF);
				fontRendererObj.drawStringWithShadow(chestplate, (boxPos[0][0] + boxLength / 7) / textScale,
						(boxPos[0][1] + (boxLength / 20) * 4) / textScale, 0xFFFFFF);
				fontRendererObj.drawStringWithShadow(leggings, (boxPos[0][0] + boxLength / 7) / textScale,
						(boxPos[0][1] + (boxLength / 20) * 5) / textScale, 0xFFFFFF);
				fontRendererObj.drawStringWithShadow(boots, (boxPos[0][0] + boxLength / 7) / textScale,
						(boxPos[0][1] + (boxLength / 20) * 6) / textScale, 0xFFFFFF);
			}
			GlStateManager.popMatrix();
		}
		if (profileCount >= 1) {
			textScale = 1.5F;
			if (!mc.isFullScreen())
				textScale = 1.0F;
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			mc.renderEngine.bindTexture(new ResourceLocation(Variables.MODID, "textures/gui/profilebox.png"));
			LoadingScreen.drawModalRectWithCustomSizedTexture(boxPos[1][0], boxPos[1][1], 0, 0, boxLength, boxLength,
					boxLength, boxLength);
			mc.renderEngine.bindTexture(profileLocation1);
			LoadingScreen.drawModalRectWithCustomSizedTexture(boxPos[1][0] + boxLength / 24,
					boxPos[1][1] + boxLength / 22, 0, 0, fruitLength, fruitLength, fruitLength, fruitLength);
			GlStateManager.pushMatrix();
			{
				GlStateManager.scale(textScale, textScale, textScale);
				fontRendererObj.drawStringWithShadow(var.getCuteNames()[1], (boxPos[1][0] + boxLength / 7) / textScale,
						(boxPos[1][1] + boxLength / 20) / textScale, 0xFFFFFF);
			}
			GlStateManager.popMatrix();
			String[] armour;
			textScale = 0.8F;
			String helmet = "No helmet equipped";
			String chestplate = "No chestplate equipped";
			String leggings = "No leggings equipped";
			String boots = "No boots equipped";
			if (!(armour = var.getArmourWorn(1))[0].equals("empty")) {
				helmet = armour[3];
				chestplate = armour[2];
				leggings = armour[1];
				boots = armour[0];
			}
			GlStateManager.pushMatrix();
			{
				GlStateManager.scale(textScale, textScale, textScale);
				fontRendererObj.drawStringWithShadow(helmet, (boxPos[1][0] + boxLength / 7) / textScale,
						(boxPos[0][1] + (boxLength / 20) * 3) / textScale, 0xFFFFFF);
				fontRendererObj.drawStringWithShadow(chestplate, (boxPos[1][0] + boxLength / 7) / textScale,
						(boxPos[0][1] + (boxLength / 20) * 4) / textScale, 0xFFFFFF);
				fontRendererObj.drawStringWithShadow(leggings, (boxPos[1][0] + boxLength / 7) / textScale,
						(boxPos[0][1] + (boxLength / 20) * 5) / textScale, 0xFFFFFF);
				fontRendererObj.drawStringWithShadow(boots, (boxPos[1][0] + boxLength / 7) / textScale,
						(boxPos[0][1] + (boxLength / 20) * 6) / textScale, 0xFFFFFF);
			}
			GlStateManager.popMatrix();
		}
		if (profileCount >= 2) {
			textScale = 1.5F;
			if (!mc.isFullScreen())
				textScale = 1.0F;
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			mc.renderEngine.bindTexture(new ResourceLocation(Variables.MODID, "textures/gui/profilebox.png"));
			LoadingScreen.drawModalRectWithCustomSizedTexture(boxPos[2][0], boxPos[2][1], 0, 0, boxLength, boxLength,
					boxLength, boxLength);
			mc.renderEngine.bindTexture(profileLocation2);
			LoadingScreen.drawModalRectWithCustomSizedTexture(boxPos[2][0] + boxLength / 24,
					boxPos[2][1] + boxLength / 22, 0, 0, fruitLength, fruitLength, fruitLength, fruitLength);
			GlStateManager.pushMatrix();
			{
				GlStateManager.scale(textScale, textScale, textScale);
				fontRendererObj.drawStringWithShadow(var.getCuteNames()[2], (boxPos[2][0] + boxLength / 7) / textScale,
						(boxPos[2][1] + boxLength / 20) / textScale, 0xFFFFFF);
			}
			GlStateManager.popMatrix();
			String[] armour;
			textScale = 0.8F;
			String helmet = "No helmet equipped";
			String chestplate = "No chestplate equipped";
			String leggings = "No leggings equipped";
			String boots = "No boots equipped";
			if (!(armour = var.getArmourWorn(2))[0].equals("empty")) {
				helmet = armour[3];
				chestplate = armour[2];
				leggings = armour[1];
				boots = armour[0];
			}
			GlStateManager.pushMatrix();
			{
				GlStateManager.scale(textScale, textScale, textScale);
				fontRendererObj.drawStringWithShadow(helmet, (boxPos[2][0] + boxLength / 7) / textScale,
						(boxPos[0][1] + (boxLength / 20) * 3) / textScale, 0xFFFFFF);
				fontRendererObj.drawStringWithShadow(chestplate, (boxPos[2][0] + boxLength / 7) / textScale,
						(boxPos[0][1] + (boxLength / 20) * 4) / textScale, 0xFFFFFF);
				fontRendererObj.drawStringWithShadow(leggings, (boxPos[2][0] + boxLength / 7) / textScale,
						(boxPos[0][1] + (boxLength / 20) * 5) / textScale, 0xFFFFFF);
				fontRendererObj.drawStringWithShadow(boots, (boxPos[2][0] + boxLength / 7) / textScale,
						(boxPos[0][1] + (boxLength / 20) * 6) / textScale, 0xFFFFFF);
			}
			GlStateManager.popMatrix();
		}
		if (profileCount >= 3) {
			textScale = 1.5F;
			if (!mc.isFullScreen())
				textScale = 1.0F;
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			mc.renderEngine.bindTexture(new ResourceLocation(Variables.MODID, "textures/gui/profilebox.png"));
			LoadingScreen.drawModalRectWithCustomSizedTexture(boxPos[3][0], boxPos[3][1], 0, 0, boxLength, boxLength,
					boxLength, boxLength);
			mc.renderEngine.bindTexture(profileLocation3);
			LoadingScreen.drawModalRectWithCustomSizedTexture(boxPos[3][0] + boxLength / 24,
					boxPos[3][1] + boxLength / 22, 0, 0, fruitLength, fruitLength, fruitLength, fruitLength);
			GlStateManager.pushMatrix();
			{
				GlStateManager.scale(textScale, textScale, textScale);
				fontRendererObj.drawStringWithShadow(var.getCuteNames()[3], (boxPos[3][0] + boxLength / 7) / textScale,
						(boxPos[3][1] + boxLength / 20) / textScale, 0xFFFFFF);
			}
			GlStateManager.popMatrix();
			String[] armour;
			textScale = 0.8F;
			String helmet = "No helmet equipped";
			String chestplate = "No chestplate equipped";
			String leggings = "No leggings equipped";
			String boots = "No boots equipped";
			if (!(armour = var.getArmourWorn(3))[0].equals("empty")) {
				helmet = armour[3];
				chestplate = armour[2];
				leggings = armour[1];
				boots = armour[0];
			}
			GlStateManager.pushMatrix();
			{
				GlStateManager.scale(textScale, textScale, textScale);
				fontRendererObj.drawStringWithShadow(helmet, (boxPos[3][0] + boxLength / 7) / textScale,
						(boxPos[0][1] + (boxLength / 20) * 3 + boxLength) / textScale, 0xFFFFFF);
				fontRendererObj.drawStringWithShadow(chestplate, (boxPos[3][0] + boxLength / 7) / textScale,
						(boxPos[0][1] + (boxLength / 20) * 4 + boxLength) / textScale, 0xFFFFFF);
				fontRendererObj.drawStringWithShadow(leggings, (boxPos[3][0] + boxLength / 7) / textScale,
						(boxPos[0][1] + (boxLength / 20) * 5 + boxLength) / textScale, 0xFFFFFF);
				fontRendererObj.drawStringWithShadow(boots, (boxPos[3][0] + boxLength / 7) / textScale,
						(boxPos[0][1] + (boxLength / 20) * 6 + boxLength) / textScale, 0xFFFFFF);
			}
			GlStateManager.popMatrix();
		}
		if (profileCount >= 4) {
			textScale = 1.5F;
			if (!mc.isFullScreen())
				textScale = 1.0F;
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			mc.renderEngine.bindTexture(new ResourceLocation(Variables.MODID, "textures/gui/profilebox.png"));
			LoadingScreen.drawModalRectWithCustomSizedTexture(boxPos[4][0], boxPos[4][1], 0, 0, boxLength, boxLength,
					boxLength, boxLength);
			mc.renderEngine.bindTexture(profileLocation4);
			LoadingScreen.drawModalRectWithCustomSizedTexture(boxPos[4][0] + boxLength / 24,
					boxPos[4][1] + boxLength / 22, 0, 0, fruitLength, fruitLength, fruitLength, fruitLength);
			GlStateManager.pushMatrix();
			{
				GlStateManager.scale(textScale, textScale, textScale);
				fontRendererObj.drawStringWithShadow(var.getCuteNames()[4], (boxPos[4][0] + boxLength / 7) / textScale,
						(boxPos[4][1] + boxLength / 20) / textScale, 0xFFFFFF);
			}
			GlStateManager.popMatrix();
			String[] armour;
			textScale = 0.8F;
			String helmet = "No helmet equipped";
			String chestplate = "No chestplate equipped";
			String leggings = "No leggings equipped";
			String boots = "No boots equipped";
			if (!(armour = var.getArmourWorn(4))[0].equals("empty")) {
				helmet = armour[3];
				chestplate = armour[2];
				leggings = armour[1];
				boots = armour[0];
			}
			GlStateManager.pushMatrix();
			{
				GlStateManager.scale(textScale, textScale, textScale);
				fontRendererObj.drawStringWithShadow(helmet, (boxPos[4][0] + boxLength / 7) / textScale,
						(boxPos[0][1] + (boxLength / 20) * 3 + boxLength) / textScale, 0xFFFFFF);
				fontRendererObj.drawStringWithShadow(chestplate, (boxPos[4][0] + boxLength / 7) / textScale,
						(boxPos[0][1] + (boxLength / 20) * 4 + boxLength) / textScale, 0xFFFFFF);
				fontRendererObj.drawStringWithShadow(leggings, (boxPos[4][0] + boxLength / 7) / textScale,
						(boxPos[0][1] + (boxLength / 20) * 5 + boxLength) / textScale, 0xFFFFFF);
				fontRendererObj.drawStringWithShadow(boots, (boxPos[4][0] + boxLength / 7) / textScale,
						(boxPos[0][1] + (boxLength / 20) * 6 + boxLength) / textScale, 0xFFFFFF);
			}
			GlStateManager.popMatrix();
		}
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		switch (button.id) {
		case (1):
			ModEventHandler.updateScreen(ScreenTypes.PROFILE, 0);
			break;
		case (2):
			ModEventHandler.updateScreen(ScreenTypes.PROFILE, 1);
			break;
		case (3):
			ModEventHandler.updateScreen(ScreenTypes.PROFILE, 2);
			break;
		case (4):
			ModEventHandler.updateScreen(ScreenTypes.PROFILE, 3);
			break;
		case (5):
			ModEventHandler.updateScreen(ScreenTypes.PROFILE, 4);
			break;
		}
	}
}