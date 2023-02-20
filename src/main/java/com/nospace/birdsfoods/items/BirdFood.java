package com.nospace.birdsfoods.items;

import com.nospace.birdsfoods.config.BirdConfig;
import com.nospace.birdsfoods.effects.BirdPotion;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class BirdFood extends ItemFood {

   private boolean isQuality;
   private boolean isDangerous;
   private boolean isTaako;
   private boolean isPalliative;
   private boolean isRaw;
   private boolean isDagwood;
   private PotionEffect potionId;
   private float potionEffectProbability;
   private float poisoncheck;


   public BirdFood(int amount, float saturation, boolean isWolfFood, String itemname) {
      super(amount, saturation, isWolfFood);
      this.setUnlocalizedName(itemname);
      this.setTextureName("birdsfoods:" + itemname);
      this.setCreativeTab(CreativeTabs.tabFood);
   }

   public BirdFood isDangerous() {
      this.isDangerous = true;
      return this;
   }

   public BirdFood isQuality() {
      this.isQuality = true;
      return this;
   }

   public BirdFood isTaako() {
      this.isTaako = true;
      return this;
   }

   public BirdFood isPalliative() {
      this.isPalliative = true;
      return this;
   }

   public BirdFood isRaw() {
      this.isRaw = true;
      return this;
   }

   public BirdFood isDagwood() {
      this.isDagwood = true;
      return this;
   }

   public boolean hasEffect(ItemStack stack) {
      return this.isTaako;
   }

   protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
      player.getFoodStats();
      if(!worldIn.isRemote && this.isDangerous && BirdConfig.DangerousEffects) {
         this.poisoncheck = worldIn.rand.nextFloat();
         if(this.poisoncheck < 0.01F) {
            player.addPotionEffect(new PotionEffect(7, 0, 10));
         }

         if(this.poisoncheck < 0.05F) {
            player.addPotionEffect(new PotionEffect(20, 400, 1));
         }

         if(this.poisoncheck < 0.1F) {
            player.addPotionEffect(new PotionEffect(15, 400, 1));
         }

         if(this.poisoncheck < 0.25F && BirdConfig.NauseaEffects) {
            player.addPotionEffect(new PotionEffect(9, 400, 1));
         }

         if(this.poisoncheck > 0.8F) {
            player.addPotionEffect(new PotionEffect(11, 2400, 0));
         }
      }

      if(!worldIn.isRemote && this.potionId != null && worldIn.rand.nextFloat() < this.potionEffectProbability) {
         player.addPotionEffect(new PotionEffect(this.potionId));
      }

      if(!worldIn.isRemote && this.isQuality && BirdConfig.QualityEffects) {
         player.addPotionEffect(new PotionEffect(21, 2400, 0));
      }

      if(!worldIn.isRemote && this.isDagwood && BirdConfig.SuperFoodEffects) {
         player.addPotionEffect(new PotionEffect(6, 60, 10));
         player.addPotionEffect(new PotionEffect(11, 200, 2));
         player.addPotionEffect(new PotionEffect(21, 2400, 0));
      }

      if(!worldIn.isRemote && this.isDagwood && !BirdConfig.SuperFoodEffects && BirdConfig.QualityEffects) {
         player.addPotionEffect(new PotionEffect(21, 2400, 0));
      }

      if(!worldIn.isRemote && this.isPalliative && BirdConfig.Palliatives && BirdConfig.PalliativeTweak) {
         player.removePotionEffect(15);
         player.removePotionEffect(19);
         player.removePotionEffect(9);
         player.removePotionEffect(20);
         player.removePotionEffect(2);
         player.removePotionEffect(4);
         player.removePotionEffect(18);
         player.removePotionEffect(7);
         player.removePotionEffect(17);
      }

      if(!worldIn.isRemote && this.isPalliative && BirdConfig.Palliatives && !BirdConfig.PalliativeTweak) {
         player.clearActivePotions();
      }

      if(!worldIn.isRemote && this.isTaako && BirdConfig.SuperFoodEffects) {
         // 3600 -> 3200 and 4 > 3
         player.addPotionEffect(new PotionEffect(21, 3200, 3));
         player.addPotionEffect(new PotionEffect(12, 3600, 1));
         // 1200 -> 900 and 2 > 1
         player.addPotionEffect(new PotionEffect(10, 900, 1));
         player.addPotionEffect(new PotionEffect(11, 200, 1));
         player.addPotionEffect(new PotionEffect(2, 200, 1));
      }

      if(!worldIn.isRemote && this.isTaako && !BirdConfig.SuperFoodEffects && BirdConfig.QualityEffects) {
         player.addPotionEffect(new PotionEffect(21, 2400, 0));
      }

      if(!worldIn.isRemote && player.getFoodStats().getFoodLevel() == 20 && (float)this.func_150905_g(stack) * this.func_150906_h(stack) * 2.0F + (float)this.func_150905_g(stack) >= 20.0F && BirdConfig.WellFed) {
         player.addPotionEffect(new PotionEffect(BirdPotion.fullness.id, 1200, 9));
      }

   }

   public ItemFood setPotionEffect(PotionEffect effect, float probability) {
      this.potionId = effect;
      this.potionEffectProbability = probability;
      return this;
   }

   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) {
      if(this.isDangerous && BirdConfig.DangerousEffects) {
         tooltip.add(EnumChatFormatting.RED + "Caution: May cause serious illness or death");
         tooltip.add("...but bravery can make you strong, right?");
      }

      if(this.isRaw) {
         tooltip.add("Might make you sick");
      }

      if(this.isTaako && BirdConfig.SuperFoodEffects) {
         tooltip.add(EnumChatFormatting.GOLD + "A legendary food for legendary buffs");
      }

      if(this.isQuality && BirdConfig.QualityEffects) {
         tooltip.add("Grants a temporary health boost");
      }

      if(this.isPalliative && BirdConfig.Palliatives && BirdConfig.PalliativeTweak) {
         tooltip.add("Removes negative potion effects");
         tooltip.add("(Vanilla effects only)");
      }

      if(this.isPalliative && BirdConfig.Palliatives && !BirdConfig.PalliativeTweak) {
         tooltip.add("Removes all potion effects");
      }

      if(this.isDagwood && BirdConfig.SuperFoodEffects) {
         tooltip.add("Grants near-invulnerability for a short time");
      }

      if(this.isDagwood && !BirdConfig.SuperFoodEffects && BirdConfig.QualityEffects) {
         tooltip.add("Grants a temporary health boost");
      }

      if(this.isTaako && !BirdConfig.SuperFoodEffects && BirdConfig.QualityEffects) {
         tooltip.add("Grants a temporary health boost");
      }

   }
}
