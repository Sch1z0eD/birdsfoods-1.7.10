package com.nospace.birdsfoods.config;

import com.nospace.birdsfoods.config.BirdGuiConfig;
import cpw.mods.fml.client.IModGuiFactory;
import cpw.mods.fml.client.IModGuiFactory.RuntimeOptionCategoryElement;
import cpw.mods.fml.client.IModGuiFactory.RuntimeOptionGuiHandler;
import java.util.Set;
import net.minecraft.client.Minecraft;

public class BirdGuiConfigFactory implements IModGuiFactory {

   public void initialize(Minecraft minecraftInstance) {}

   public Class mainConfigGuiClass() {
      return BirdGuiConfig.class;
   }

   public Set runtimeGuiCategories() {
      return null;
   }

   public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {
      return null;
   }
}
