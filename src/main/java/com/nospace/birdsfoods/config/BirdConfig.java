package com.nospace.birdsfoods.config;

import com.nospace.birdsfoods.BirdsFoods;
import cpw.mods.fml.common.FMLCommonHandler;

public class BirdConfig {

   public static boolean QualityEffects = true;
   public static boolean AlcoholEffects = true;
   public static boolean AlcoholSideEffects = true;
   public static boolean SuperFoodEffects = true;
   public static boolean DangerousEffects = true;
   public static boolean Palliatives = true;
   public static boolean PalliativeTweak = true;
   public static boolean NauseaEffects = true;
   public static boolean WellFed = true;


   public static void reloadConfig() {
      FMLCommonHandler.instance().bus().register(BirdsFoods.instance);
      QualityEffects = BirdsFoods.config.getBoolean("Quality Effects", "general", true, "Enable/Disable health boost from certain foods");
      AlcoholEffects = BirdsFoods.config.getBoolean("Alcohol Effects", "general", true, "Enable/Disable all effects from alcoholic drinks");
      AlcoholSideEffects = BirdsFoods.config.getBoolean("Alcohol Side Effects", "general", true, "Enable/Disable all negative side effects from alcoholic drinks");
      SuperFoodEffects = BirdsFoods.config.getBoolean("Super Food Effects", "general", true, "Enable/Disable bonus effects from certain super foods. If this is disabled, they will be converted to Quality Foods if they are enabled.");
      DangerousEffects = BirdsFoods.config.getBoolean("Dangerous Effects", "general", true, "Enable/Disable dangerous effects from certain foods");
      Palliatives = BirdsFoods.config.getBoolean("Palliatives", "general", true, "Enable/Disable effect-clearing foods");
      PalliativeTweak = BirdsFoods.config.getBoolean("Palliative Tweak", "general", true, "Enabled: palliative foods will clear only negative effects. Disabled: palliative foods will function like vanilla milk buckets and remove all effects");
      NauseaEffects = BirdsFoods.config.getBoolean("Nausea Effects", "general", true, "Enable/Disable all nausea (screen distorting) effects");
      WellFed = BirdsFoods.config.getBoolean("Well Fed", "general", true, "Enable/Disable Well Fed buff after filling up on good food");
      if(BirdsFoods.config.hasChanged()) {
         BirdsFoods.config.save();
      }

   }

}
