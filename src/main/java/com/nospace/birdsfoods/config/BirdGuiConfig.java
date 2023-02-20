package com.nospace.birdsfoods.config;

import com.nospace.birdsfoods.BirdsFoods;
import cpw.mods.fml.client.config.GuiConfig;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;

public class BirdGuiConfig extends GuiConfig {

   public BirdGuiConfig(GuiScreen parentScreen) {
      super(parentScreen, (new ConfigElement(BirdsFoods.config.getCategory("general"))).getChildElements(), "birdsfoods", false, false, GuiConfig.getAbridgedConfigPath(BirdsFoods.config.toString()));
   }
}
