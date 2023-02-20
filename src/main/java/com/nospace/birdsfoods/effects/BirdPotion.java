package com.nospace.birdsfoods.effects;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class BirdPotion extends Potion {

   public static final ResourceLocation icon = new ResourceLocation("birdsfoods", "textures/gui/inventory.png");
   public static Potion fullness;


   public BirdPotion(int id, boolean isBadEffect, int color) {
      super(id, isBadEffect, color);
   }

   public Potion setIconIndex(int x, int y) {
      super.setIconIndex(x, y);
      return this;
   }

   public int getStatusIconIndex() {
      ITextureObject texture = Minecraft.getMinecraft().renderEngine.getTexture(icon);
      Minecraft.getMinecraft().renderEngine.bindTexture(icon);
      return super.getStatusIconIndex();
   }

   public boolean isReady(int duration, int amplifier) {
      return true;
   }

   public boolean isBadEffect() {
      return false;
   }

   @SideOnly(Side.CLIENT)
   public boolean hasStatusIcon() {
      return true;
   }

   public static final void init() {
      fullness = (new BirdPotion(30, false, 10512937)).setIconIndex(0, 0).setPotionName("potion.fullness");
   }

   public void performEffect(EntityLivingBase entity, int amp) {
      if(super.id == fullness.id && entity instanceof EntityPlayer) {
         ((EntityPlayer)entity).addExhaustion(-0.01F * (float)(amp + 1));
      }

   }

}
