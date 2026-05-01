/*     */ package ichun.client.voxel;
/*     */ 
/*     */ import cpw.mods.fml.common.ObfuscationReflectionHelper;
/*     */ import ichun.common.core.CommonProxy;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.AbstractClientPlayer;
/*     */ import net.minecraft.client.renderer.ThreadDownloadImageData;
/*     */ import net.minecraft.client.renderer.entity.Render;
/*     */ import net.minecraft.client.renderer.texture.ITextureObject;
/*     */ import net.minecraft.client.renderer.texture.TextureUtil;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class RenderVoxels
/*     */   extends Render
/*     */ {
/*  23 */   public HashMap<ResourceLocation, BufferedImage[]> restitchedSkins = (HashMap)new HashMap<ResourceLocation, BufferedImage>();
/*  24 */   public HashMap<ResourceLocation, int[]> restitchedSkinsId = (HashMap)new HashMap<ResourceLocation, int>();
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation func_110775_a(Entity entity) {
/*  29 */     return AbstractClientPlayer.field_110314_b;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_76986_a(Entity entity, double d, double d1, double d2, float f, float f1) {
/*  35 */     EntityTrail sd = (EntityTrail)entity;
/*     */     
/*  37 */     if (sd.parent.func_70005_c_().equals((Minecraft.func_71410_x()).field_71451_h.func_70005_c_()) && (Minecraft.func_71410_x()).field_71474_y.field_74320_O == 0) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/*  42 */     GL11.glPushMatrix();
/*     */ 
/*     */     
/*  45 */     ArrayList<LocationInfo> loc = CommonProxy.trailTicker.getPlayerLocationInfo((EntityPlayer)sd.parent);
/*     */     
/*  47 */     ResourceLocation rl = sd.parent.func_110306_p();
/*     */     
/*  49 */     if (((LocationInfo)loc.get(20)).txLocation != null)
/*     */     {
/*  51 */       rl = ((LocationInfo)loc.get(20)).txLocation;
/*     */     }
/*     */     
/*  54 */     BufferedImage[] skins = this.restitchedSkins.get(rl);
/*     */     
/*  56 */     if (skins == null) {
/*     */       
/*  58 */       ITextureObject obj = Minecraft.func_71410_x().func_110434_K().func_110581_b(rl);
/*  59 */       if (obj instanceof ThreadDownloadImageData) {
/*     */         
/*     */         try {
/*     */           
/*  63 */           ThreadDownloadImageData imgDat = (ThreadDownloadImageData)obj;
/*  64 */           BufferedImage img = (BufferedImage)ObfuscationReflectionHelper.getPrivateValue(ThreadDownloadImageData.class, imgDat, new String[] { "field_110560_d", "bufferedImage" });
/*  65 */           if (img != null)
/*     */           {
/*  67 */             int[] imgId = new int[4];
/*  68 */             skins = new BufferedImage[4];
/*     */             
/*  70 */             int[] dimsX = { 4, 4, 8, 8 };
/*  71 */             int[] dimsZ = { 4, 4, 4, 8 };
/*  72 */             int[] dimsY = { 12, 12, 12, 8 };
/*     */             
/*  74 */             int[] startX = { 0, 40, 16, 0 };
/*  75 */             int[] startY = { 16, 16, 16, 0 };
/*     */             
/*  77 */             for (int j = 0; j < dimsX.length; j++) {
/*     */               
/*  79 */               int[] dim = { dimsX[j], dimsY[j], dimsZ[j] };
/*  80 */               int[] rots = { -90, 180, 0, 0, 90, 0, -90, 180, 90 };
/*  81 */               BufferedImage tmp = new BufferedImage(48, 24, 1);
/*     */               
/*  83 */               Graphics2D gfx = tmp.createGraphics();
/*     */               
/*  85 */               int[] xSource = { dim[2], dim[2], dim[2] + dim[0] + dim[2], 0, dim[2] + dim[0], dim[2] + dim[0], dim[2] + dim[0], dim[2] + dim[0], dim[2] };
/*  86 */               int[] ySource = { 0, 0, dim[2], dim[2], 0, 0, 0, 0, 0 };
/*     */               
/*  88 */               int[] xCoord = { dim[0], dim[0] + dim[2] + dim[0] + dim[2], 0, dim[0] + dim[2] + dim[0] + dim[2] + dim[0], dim[0], dim[0] + dim[2], dim[0] + dim[2] + dim[0], dim[0] + dim[2] + dim[0] + dim[2], dim[2] + dim[0] + dim[2] };
/*  89 */               int[] yCoord = { 0, 0, dim[2], dim[2], dim[2] + dim[1], dim[2] + dim[1], dim[2] + dim[1], dim[2] + dim[1], 0 };
/*     */               
/*  91 */               int[] dimX = { dim[0], dim[0], dim[0], dim[2], dim[0], dim[0], dim[0], dim[0], dim[0] };
/*  92 */               int[] dimY = { dim[2], dim[2], dim[1], dim[1], dim[2], dim[2], dim[2], dim[2], dim[2] };
/*     */               
/*  94 */               for (int i = 0; i < rots.length; i++) {
/*     */                 
/*  96 */                 if (i == rots.length - 1)
/*     */                 {
/*  98 */                   gfx.drawImage(img, dim[0], 0, dim[0] + dim[2] + dim[0] + dim[2] + dim[0], dim[2] + dim[1], startX[j], startY[j], startX[j] + 2 * dim[0] + 2 * dim[2], startY[j] + dim[1] + dim[2], null);
/*     */                 }
/*     */                 
/* 101 */                 BufferedImage temp = img.getSubimage(startX[j] + xSource[i], startY[j] + ySource[i], dimX[i], dimY[i]);
/*     */                 
/* 103 */                 BufferedImage temp1 = new BufferedImage(dimX[i], dimY[i], 1);
/*     */                 
/* 105 */                 Graphics2D gfx1 = temp1.createGraphics();
/* 106 */                 gfx1.rotate(Math.toRadians(rots[i]), (dimX[i] / 2), (dimY[i] / 2));
/*     */                 
/* 108 */                 gfx1.drawImage(temp, 0, 0, dimX[i], dimY[i], 0, 0, dimX[i], dimY[i], null);
/* 109 */                 gfx1.dispose();
/*     */                 
/* 111 */                 gfx.drawImage(temp1, xCoord[i], yCoord[i], xCoord[i] + dimX[i], yCoord[i] + dimY[i], 0, 0, dimX[i], dimY[i], null);
/*     */               } 
/*     */               
/* 114 */               imgId[j] = TextureUtil.func_110987_a(TextureUtil.func_110996_a(), tmp);
/* 115 */               skins[j] = tmp;
/*     */             } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 126 */             this.restitchedSkinsId.put(rl, imgId);
/* 127 */             this.restitchedSkins.put(rl, skins);
/*     */           }
/*     */         
/* 130 */         } catch (Exception e) {
/*     */           
/* 132 */           e.printStackTrace();
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 137 */     sd.model.renderPlayer(sd, entity.field_70170_p.func_72820_D(), entity.hashCode(), loc, d, d1, d2, 0.0625F, f1, this.restitchedSkinsId.get(rl));
/*     */     
/* 139 */     GL11.glPopMatrix();
/*     */   }
/*     */ }


/* Location:              C:\DADOS\Jogos\Minecraft Legends - Copia\mods\iChunUtil-4.2.2.jar!\ichun\client\voxel\RenderVoxels.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */