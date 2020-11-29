package kaden.skyblocktalismanstracker.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kaden.skyblocktalismanstracker.json.Variables;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Variables.MODID, version = Variables.VERSION, name = Variables.NAME)
public class SkyBlockTalismansTracker {

	@Instance
	public static SkyBlockTalismansTracker instance = new SkyBlockTalismansTracker();

	public static final Logger logger = LogManager.getLogger();

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ClientCommandHandler.instance.registerCommand(new ModCommands());
		MinecraftForge.EVENT_BUS.register(new ModEventHandler());
		Talismans.register();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}
}