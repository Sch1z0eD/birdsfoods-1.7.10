package com.nospace.birdsfoods.items;

import com.nospace.birdsfoods.config.BirdConfig;
import com.nospace.birdsfoods.effects.BirdPotion;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSoup;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class BirdSoup extends ItemSoup {

   private boolean isPalliative;
   private boolean isChili;


   public BirdSoup(int healAmount, String itemname) {
      super(healAmount);
      this.setUnlocalizedName(itemname);
      this.setTextureName("birdsfoods:" + itemname);
      this.setCreativeTab(CreativeTabs.tabFood);
   }

   public BirdSoup isPalliative() {
      this.isPalliative = true;
      return this;
   }

   public BirdSoup isChili() {
      this.isChili = true;
      return this;
   }

   protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
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

      if(!worldIn.isRemote && player.getFoodStats().getFoodLevel() == 20 && (float)this.func_150905_g(stack) * this.func_150906_h(stack) * 2.0F + (float)this.func_150905_g(stack) >= 19.0F && BirdConfig.WellFed) {
         player.addPotionEffect(new PotionEffect(BirdPotion.fullness.id, 1200, 9));
      }

      if(!worldIn.isRemote && this.isChili && BirdConfig.SuperFoodEffects) {
         player.addPotionEffect(new PotionEffect(12, 1200, 0));
      }

   }

   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) {
      if(this.isPalliative && BirdConfig.Palliatives && BirdConfig.PalliativeTweak) {
         tooltip.add("Removes negative potion effects");
         tooltip.add("(Vanilla effects only)");
      }

      if(this.isPalliative && BirdConfig.Palliatives && !BirdConfig.PalliativeTweak) {
         tooltip.add("Removes all potion effects");
      }

      if(this.isChili && BirdConfig.SuperFoodEffects) {
         tooltip.add(EnumChatFormatting.RED + "Strike a balance with the fire");
      }

   }
}
