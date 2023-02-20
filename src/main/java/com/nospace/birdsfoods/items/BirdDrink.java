package com.nospace.birdsfoods.items;

import com.nospace.birdsfoods.config.BirdConfig;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class BirdDrink extends ItemFood {

   private boolean isAlcoholic;
   private boolean isPalliative;
   private boolean isScumble;
   private boolean isCider;
   private boolean isWine;
   private boolean isBeer;
   private boolean isLiquor;
   private boolean isVodka;
   private boolean isPoitin;
   private boolean isTequila;
   private boolean isRum;
   private boolean isUmeshu;
   private float poisoncheck;
   private PotionEffect potionId;
   private float potionEffectProbability;
   private float blindness;
   private float slowness;
   private float nausea;
   private float health;


   public BirdDrink(int amount, float saturation, boolean isWolfFood, String itemname) {
      super(amount, saturation, isWolfFood);
      this.setUnlocalizedName(itemname);
      this.setTextureName("birdsfoods:" + itemname);
      this.setCreativeTab(CreativeTabs.tabFood);
   }

   public BirdDrink isAlcoholic(float blindness, float slowness, float nausea) {
      this.isAlcoholic = true;
      this.blindness = blindness;
      this.slowness = slowness;
      this.nausea = nausea;
      return this;
   }

   public BirdDrink isPalliative() {
      this.isPalliative = true;
      return this;
   }

   public BirdDrink isScumble() {
      this.isScumble = true;
      return this;
   }

   public BirdDrink isCider() {
      this.isCider = true;
      return this;
   }

   public BirdDrink isWine() {
      this.isWine = true;
      return this;
   }

   public BirdDrink isBeer() {
      this.isBeer = true;
      return this;
   }

   public BirdDrink isLiquor() {
      this.isLiquor = true;
      return this;
   }

   public BirdDrink isVodka() {
      this.isVodka = true;
      return this;
   }

   public BirdDrink isPoitin() {
      this.isPoitin = true;
      return this;
   }

   public BirdDrink isTequila() {
      this.isTequila = true;
      return this;
   }

   public BirdDrink isRum() {
      this.isRum = true;
      return this;
   }

   public BirdDrink isUmeshu() {
      this.isUmeshu = true;
      return this;
   }

   public EnumAction getItemUseAction(ItemStack stack) {
      return EnumAction.drink;
   }

   public boolean hasEffect(ItemStack stack) {
      return this.isAlcoholic;
   }

   protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
      if(!worldIn.isRemote && this.isAlcoholic && BirdConfig.AlcoholEffects) {
         if(BirdConfig.AlcoholSideEffects) {
            this.poisoncheck = worldIn.rand.nextFloat();
            if(this.poisoncheck < this.blindness) {
               player.addPotionEffect(new PotionEffect(15, 200, 1));
            }

            if(this.poisoncheck < this.slowness) {
               player.addPotionEffect(new PotionEffect(2, 400, 2));
            }

            if(this.poisoncheck < this.nausea && BirdConfig.NauseaEffects) {
               player.addPotionEffect(new PotionEffect(9, 300, 1));
            }

            if(this.poisoncheck < 0.1F) {
               player.addPotionEffect(new PotionEffect(17, 300, 1));
            }
         }

         if(!worldIn.isRemote && this.isScumble) {
            player.addPotionEffect(new PotionEffect(12, 1200, 0));
            player.addPotionEffect(new PotionEffect(3, 1200, 1));
         }

         if(!worldIn.isRemote && this.isPoitin) {
            player.addPotionEffect(new PotionEffect(1, 600, 0));
            player.addPotionEffect(new PotionEffect(8, 600, 1));
         }

         if(!worldIn.isRemote && this.isCider) {
            player.addPotionEffect(new PotionEffect(3, 1200, 1));
         }

         if(!worldIn.isRemote && this.isWine) {
            player.addPotionEffect(new PotionEffect(10, 600, 0));
         }

         if(!worldIn.isRemote && this.isBeer) {
            player.addPotionEffect(new PotionEffect(5, 1200, 0));
         }

         if(!worldIn.isRemote && this.isLiquor) {
            player.addPotionEffect(new PotionEffect(6, 0, 2));
         }

         if(!worldIn.isRemote && this.isVodka) {
            player.addPotionEffect(new PotionEffect(11, 1200, 1));
         }

         if(!worldIn.isRemote && this.isTequila) {
            player.addPotionEffect(new PotionEffect(22, 300, -3));
         }

         if(!worldIn.isRemote && this.isRum) {
            player.addPotionEffect(new PotionEffect(13, 1200, 1));
         }

         if(!worldIn.isRemote && this.isUmeshu) {
            player.addPotionEffect(new PotionEffect(14, 1200, 1));
         }
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

      player.inventory.addItemStackToInventory(new ItemStack(Items.glass_bottle));
   }

   public ItemFood setPotionEffect(PotionEffect effect, float probability) {
      this.potionId = effect;
      this.potionEffectProbability = probability;
      return this;
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

      if(this.isAlcoholic && BirdConfig.AlcoholEffects) {
         tooltip.add(EnumChatFormatting.RED + "May impair your senses. Drink responsibly");
         if(this.isCider) {
            tooltip.add("Dig deeper, faster");
         }

         if(this.isWine) {
            tooltip.add("Fruit is healthy, right?");
         }

         if(this.isBeer) {
            tooltip.add("Hit like a truck");
         }

         if(this.isLiquor) {
            tooltip.add("Like a healing potion, with side effects");
         }

         if(this.isVodka) {
            tooltip.add("Hard headed and thick skinned");
         }

         if(this.isPoitin) {
            tooltip.add("Put a spring in your step");
         }

         if(this.isTequila) {
            tooltip.add("You\'ll feel much better when it wears off");
         }

         if(this.isRum) {
            tooltip.add("Drink like a fish, breathe like one too");
         }

         if(this.isUmeshu) {
            tooltip.add("You\'re certain you had a body just a moment ago");
         }

         if(this.isScumble) {
            tooltip.add("You could probably walk through fire, IF you\'re still standing");
         }
      }

   }
}
