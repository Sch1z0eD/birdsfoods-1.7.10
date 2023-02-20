package com.nospace.birdsfoods;

import com.nospace.birdsfoods.BaseProxy;
import com.nospace.birdsfoods.config.BirdConfig;
import com.nospace.birdsfoods.crafting.BirdCrafting;
import com.nospace.birdsfoods.effects.BirdPotion;
import com.nospace.birdsfoods.items.BirdItems;
import com.nospace.birdsfoods.items.BirdOreDict;
import cpw.mods.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.common.config.Configuration;

@Mod(
   modid = "birdsfoods",
   name = "Bird\'s Foods",
   guiFactory = "com.nospace.birdsfoods.config.BirdGuiConfigFactory",
   version = "3.1 - [1.7.10]",
   acceptedMinecraftVersions = ""
)
public class BirdsFoods {

   public static final String MOD_ID = "birdsfoods";
   public static final String NAME = "Bird\'s Foods";
   @SidedProxy(
      clientSide = "com.nospace.birdsfoods.ClientProxy",
      serverSide = "com.nospace.birdsfoods.ServerProxy"
   )
   public static BaseProxy proxy;
   public static Configuration config;
   @Instance("birdsfoods")
   public static BirdsFoods instance;
   public static SimpleNetworkWrapper network;


   @EventHandler
   public void preInit(FMLPreInitializationEvent event) {
      config = new Configuration(event.getSuggestedConfigurationFile());
      BirdConfig.reloadConfig();
      BirdItems.init();
      BirdPotion.init();
      BirdCrafting.init();
      proxy.preInit();
      BirdOreDict.initDict();
      network = NetworkRegistry.INSTANCE.newSimpleChannel("birdsfoods");
   }

   @SubscribeEvent
   public void onConfigChanged(OnConfigChangedEvent event) {
      if(event.modID.equals("birdsfoods")) {
         BirdConfig.reloadConfig();
      }

   }

   @EventHandler
   public void init(FMLInitializationEvent event) {}

   @EventHandler
   public void postInit(FMLPostInitializationEvent event) {}
}
