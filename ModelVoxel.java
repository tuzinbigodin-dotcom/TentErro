/*     */ package ichun.client.voxel;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class ModelVoxel
/*     */   extends ModelBase
/*     */ {
/*  13 */   public Random rand = new Random();
/*     */   
/*     */   public ModelRenderer[] modelHead;
/*     */   
/*     */   public ModelRenderer[] modelBody;
/*     */   public ModelRenderer[] modelLimb;
/*     */   public ModelRenderer[] modelLimbMirrored;
/*     */   
/*     */   public ModelVoxel() {
/*  22 */     this.modelHead = new ModelRenderer[512];
/*  23 */     this.modelBody = new ModelRenderer[384];
/*  24 */     this.modelLimb = new ModelRenderer[192];
/*  25 */     this.modelLimbMirrored = new ModelRenderer[192];
/*     */ 
/*     */     
/*  28 */     int sizeX = 8;
/*  29 */     int sizeY = 8;
/*  30 */     int sizeZ = 8;
/*     */     
/*  32 */     this.field_78090_t = 48;
/*  33 */     this.field_78089_u = 24;
/*     */     int i;
/*  35 */     for (i = 0; i < sizeX; i++) {
/*     */       
/*  37 */       for (int j = 0; j < sizeY; j++) {
/*     */         
/*  39 */         for (int k = 0; k < sizeZ; k++) {
/*     */           
/*  41 */           if (i == 0 || i == sizeX - 1 || j == 0 || j == sizeY - 1 || k == 0 || k == sizeZ - 1) {
/*     */             
/*  43 */             int x = (j == sizeY - 1 && i != 0 && i != sizeX - 1 && k != 0 && k != sizeZ - 1) ? (sizeX + sizeZ + i) : ((k == 0) ? (sizeX + sizeZ + sizeX + sizeZ + i - 3) : ((k == sizeZ - 1) ? (sizeX + sizeZ + i - 1) : ((k > 0 && i < sizeX / 2) ? (sizeX + sizeZ + sizeX + sizeZ - 3 - k) : (sizeX - 1 + k))));
/*  44 */             int y = (j == sizeY - 1 && i != 0 && i != sizeX - 1 && k != 0 && k != sizeZ - 1) ? (sizeZ - k) : (sizeZ + sizeY - j - 2);
/*     */             
/*  46 */             ModelRenderer pixel = new ModelRenderer(this, x, sizeZ + sizeY - j - 2);
/*  47 */             this.modelHead[i + j * sizeX + k * sizeY * sizeX] = pixel;
/*  48 */             pixel.func_78789_a(-0.5F, -0.5F, -0.5F, 1, 1, 1);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  54 */     sizeX = 8;
/*  55 */     sizeY = 12;
/*  56 */     sizeZ = 4;
/*     */     
/*  58 */     for (i = 0; i < sizeX; i++) {
/*     */       
/*  60 */       for (int j = 0; j < sizeY; j++) {
/*     */         
/*  62 */         for (int k = 0; k < sizeZ; k++) {
/*     */           
/*  64 */           if (i == 0 || i == sizeX - 1 || j == 0 || j == sizeY - 1 || k == 0 || k == sizeZ - 1) {
/*     */             
/*  66 */             int x = (j == sizeY - 1 && i != 0 && i != sizeX - 1 && k != 0 && k != sizeZ - 1) ? (sizeX + sizeZ + i) : ((k == 0) ? (sizeX + sizeZ + sizeX + sizeZ + i - 3) : ((k == sizeZ - 1) ? (sizeX + sizeZ + i - 1) : ((k > 0 && i < sizeX / 2) ? (sizeX + sizeZ + sizeX + sizeZ - 3 - k) : (sizeX - 1 + k))));
/*  67 */             int y = (j == sizeY - 1 && i != 0 && i != sizeX - 1 && k != 0 && k != sizeZ - 1) ? (sizeZ - k) : (sizeZ + sizeY - j - 2);
/*     */             
/*  69 */             ModelRenderer pixel = new ModelRenderer(this, x, sizeZ + sizeY - j - 2);
/*  70 */             this.modelBody[i + j * sizeX + k * sizeY * sizeX] = pixel;
/*  71 */             pixel.func_78789_a(-0.5F, -0.5F, -0.5F, 1, 1, 1);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  77 */     sizeX = 4;
/*  78 */     sizeY = 12;
/*  79 */     sizeZ = 4;
/*     */     
/*  81 */     for (i = 0; i < sizeX; i++) {
/*     */       
/*  83 */       for (int j = 0; j < sizeY; j++) {
/*     */         
/*  85 */         for (int k = 0; k < sizeZ; k++) {
/*     */           
/*  87 */           if (i == 0 || i == sizeX - 1 || j == 0 || j == sizeY - 1 || k == 0 || k == sizeZ - 1) {
/*     */             
/*  89 */             int x = (j == sizeY - 1 && i != 0 && i != sizeX - 1 && k != 0 && k != sizeZ - 1) ? (sizeX + sizeZ + i) : ((k == 0) ? (sizeX + sizeZ + sizeX + sizeZ + i - 3) : ((k == sizeZ - 1) ? (sizeX + sizeZ + i - 1) : ((k > 0 && i < sizeX / 2) ? (sizeX + sizeZ + sizeX + sizeZ - 3 - k) : (sizeX - 1 + k))));
/*  90 */             int y = (j == sizeY - 1 && i != 0 && i != sizeX - 1 && k != 0 && k != sizeZ - 1) ? (sizeZ - k) : (sizeZ + sizeY - j - 2);
/*     */             
/*  92 */             ModelRenderer pixel = new ModelRenderer(this, x, sizeZ + sizeY - j - 2);
/*  93 */             this.modelLimb[i + j * sizeX + k * sizeY * sizeX] = pixel;
/*  94 */             pixel.func_78789_a(-0.5F, -0.5F, -0.5F, 1, 1, 1);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 100 */     for (i = 0; i < sizeX; i++) {
/*     */       
/* 102 */       for (int j = 0; j < sizeY; j++) {
/*     */         
/* 104 */         for (int k = 0; k < sizeZ; k++) {
/*     */           
/* 106 */           if (i == 0 || i == sizeX - 1 || j == 0 || j == sizeY - 1 || k == 0 || k == sizeZ - 1) {
/*     */             
/* 108 */             int x = (j == sizeY - 1 && i != 0 && i != sizeX - 1 && k != 0 && k != sizeZ - 1) ? (sizeX + sizeZ + i) : ((k == 0) ? (sizeX + sizeZ + sizeX + sizeZ + i - 3) : ((k == sizeZ - 1) ? (sizeX + sizeZ + i - 1) : ((k > 0 && i < sizeX / 2) ? (sizeX + sizeZ + sizeX + sizeZ - 3 - k) : (sizeX - 1 + k))));
/* 109 */             int y = (j == sizeY - 1 && i != 0 && i != sizeX - 1 && k != 0 && k != sizeZ - 1) ? (sizeZ - k) : (sizeZ + sizeY - j - 2);
/*     */             
/* 111 */             ModelRenderer pixel = new ModelRenderer(this, x, sizeZ + sizeY - j - 2);
/* 112 */             pixel.field_78809_i = true;
/* 113 */             this.modelLimbMirrored[i + j * sizeX + k * sizeY * sizeX] = pixel;
/* 114 */             pixel.func_78789_a(-0.5F, -0.5F, -0.5F, 1, 1, 1);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderPlayer(EntityTrail trail, long time, int seedBase, ArrayList<LocationInfo> loc, double pX, double pY, double pZ, float f5, float renderTick, int[] skins) {
/* 124 */     GL11.glPushMatrix();
/*     */     
/* 126 */     GL11.glAlphaFunc(516, 0.003921569F);
/*     */     
/* 128 */     GL11.glEnable(3042);
/* 129 */     GL11.glBlendFunc(770, 771);
/*     */     
/* 131 */     GL11.glDisable(2884);
/* 132 */     GL11.glEnable(2929);
/* 133 */     GL11.glDepthMask(true);
/*     */ 
/*     */ 
/*     */     
/* 137 */     for (int j = loc.size() - 1; j >= 0; j--) {
/*     */       
/* 139 */       LocationInfo info = loc.get(j);
/*     */       
/* 141 */       if (info.lastTick % 2L != 0L && info.canRender) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 146 */         int prog = (int)(time - info.lastTick);
/* 147 */         float prog2 = MathHelper.func_76131_a((prog + renderTick) / 100.0F, 0.0F, 1.0F);
/*     */         
/* 149 */         GL11.glPushMatrix();
/* 150 */         double tX = trail.field_70169_q + (trail.field_70165_t - trail.field_70169_q) * renderTick;
/* 151 */         double tY = trail.field_70167_r + (trail.field_70163_u - trail.field_70167_r) * renderTick;
/* 152 */         double tZ = trail.field_70166_s + (trail.field_70161_v - trail.field_70166_s) * renderTick;
/* 153 */         GL11.glTranslated(info.posX - tX + pX, info.posY - tY + trail.parent.field_70129_M + pY, info.posZ - tZ + pZ);
/*     */         
/* 155 */         GL11.glScalef(-1.0F, -1.0F, 1.0F);
/*     */         
/* 157 */         float scale = 0.9375F;
/* 158 */         GL11.glScalef(scale, scale, scale);
/* 159 */         GL11.glTranslatef(0.0F, -0.6825F + trail.parent.field_70129_M, 0.0F);
/*     */         
/* 161 */         GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/*     */         
/* 163 */         this.rand.setSeed(info.lastTick + seedBase);
/*     */         
/* 165 */         int i = this.rand.nextInt(6);
/*     */         
/* 167 */         if (skins == null) {
/*     */           
/* 169 */           GL11.glDisable(3553);
/* 170 */           GL11.glColor4f(0.7F, 0.7F, 0.7F, 0.8F);
/*     */         }
/*     */         else {
/*     */           
/* 174 */           GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.9F - 0.9F * MathHelper.func_76131_a((prog + renderTick) / 100.0F, 0.0F, 1.0F));
/*     */         } 
/*     */         
/* 177 */         if (skins != null)
/*     */         {
/* 179 */           if (i < 2) {
/*     */             
/* 181 */             GL11.glBindTexture(3553, skins[0]);
/*     */           }
/* 183 */           else if (i < 4) {
/*     */             
/* 185 */             GL11.glBindTexture(3553, skins[1]);
/*     */           }
/*     */           else {
/*     */             
/* 189 */             GL11.glBindTexture(3553, skins[i - 2]);
/*     */           } 
/*     */         }
/* 192 */         renderLimb(prog, i, info.sneaking, info.renderYawOffset, info.rotationYawHead, info.rotationPitch, info.limbSwing, info.limbSwingAmount, info.yawChange * prog * (1.0F - 0.4F * prog2), info.pitchChange * prog * (1.0F - 0.4F * prog2), f5, renderTick);
/* 193 */         GL11.glPopMatrix();
/*     */       } 
/*     */     } 
/* 196 */     GL11.glDisable(3042);
/*     */     
/* 198 */     if (skins == null)
/*     */     {
/* 200 */       GL11.glEnable(3553);
/*     */     }
/*     */     
/* 203 */     GL11.glAlphaFunc(516, 0.1F);
/*     */     
/* 205 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   public void renderLimb(int progInt, int limb, boolean sneaking, float renderYaw, float rotationYaw, float rotationPitch, float limbSwing, float limbSwingAmount, float yaw, float pitch, float f5, float renderTick) {
/*     */     ModelRenderer[] list;
/* 210 */     GL11.glPushMatrix();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 218 */     float prog = MathHelper.func_76131_a((progInt + renderTick) / 100.0F, 0.0F, 1.0F);
/*     */     
/* 220 */     float shatterProg = 1.0F + 0.7F * prog;
/* 221 */     float properShatterProg = 1.0F - MathHelper.func_76131_a((float)Math.pow((1.0F - MathHelper.func_76131_a((prog - 0.025F) / 0.2F, 0.0F, 1.0F)), 2.0D), 0.0F, 1.0F);
/*     */     
/* 223 */     rotationPitch *= shatterProg;
/*     */     
/* 225 */     if (limb != 5) {
/*     */       
/* 227 */       GL11.glRotatef(renderYaw, 0.0F, 1.0F, 0.0F);
/*     */     }
/*     */     else {
/*     */       
/* 231 */       GL11.glRotatef(rotationYaw, 0.0F, 1.0F, 0.0F);
/*     */     } 
/*     */ 
/*     */     
/* 235 */     float f8 = limbSwing - limbSwingAmount;
/*     */     
/* 237 */     int sizeX = 4;
/* 238 */     int sizeY = 12;
/* 239 */     int sizeZ = 4;
/*     */     
/* 241 */     float offsetX = 0.0F;
/* 242 */     float offsetY = 0.0F;
/* 243 */     float offsetZ = 0.0F;
/*     */     
/* 245 */     float spread = 0.65F;
/*     */     
/* 247 */     if (limb == 4) {
/*     */       
/* 249 */       sizeX = 8;
/*     */     }
/* 251 */     else if (limb == 5) {
/*     */       
/* 253 */       sizeX = 8;
/* 254 */       sizeY = 8;
/* 255 */       sizeZ = 8;
/*     */     } 
/*     */     
/* 258 */     int i = 0, j = 0, k = 0;
/* 259 */     while (i != -(sizeX / 2) && i != sizeX / 2 - 1 && j != -sizeY && j != -1 && k != -(sizeZ / 2) && k != sizeZ / 2 - 1) {
/*     */       
/* 261 */       i = this.rand.nextInt(sizeX) - sizeX / 2;
/* 262 */       j = this.rand.nextInt(sizeY) - sizeY;
/* 263 */       k = this.rand.nextInt(sizeZ) - sizeZ / 2;
/*     */     } 
/* 265 */     GL11.glPushMatrix();
/*     */ 
/*     */     
/* 268 */     if (limb < 4) {
/*     */       
/* 270 */       list = (limb % 2 == 0) ? this.modelLimbMirrored : this.modelLimb;
/*     */     }
/* 272 */     else if (limb == 4) {
/*     */       
/* 274 */       list = this.modelBody;
/*     */     }
/*     */     else {
/*     */       
/* 278 */       list = this.modelHead;
/*     */     } 
/*     */     
/* 281 */     ModelRenderer pixel = list[sizeX / 2 + i + (sizeY + j) * sizeX + (sizeZ / 2 + k) * sizeX * sizeY];
/*     */ 
/*     */     
/* 284 */     pixel.field_78800_c = 0.0F;
/* 285 */     pixel.field_78797_d = 0.0F;
/* 286 */     pixel.field_78798_e = 0.0F;
/*     */     
/* 288 */     if (limb == 0) {
/*     */       
/* 290 */       GL11.glTranslatef(0.125F + (this.rand.nextFloat() - 0.5F) * spread * properShatterProg, 0.0F, 0.0F);
/*     */     }
/* 292 */     else if (limb == 1) {
/*     */       
/* 294 */       GL11.glTranslatef(-0.125F + (this.rand.nextFloat() - 0.5F) * spread * properShatterProg, 0.0F, 0.0F);
/*     */     }
/* 296 */     else if (limb == 2) {
/*     */       
/* 298 */       GL11.glTranslatef(0.25F + (this.rand.nextFloat() - 0.5F) * spread * properShatterProg, -0.625F * shatterProg, 0.0F);
/* 299 */       pixel.field_78800_c = 2.0F;
/* 300 */       pixel.field_78797_d = -2.0F * shatterProg;
/*     */     }
/* 302 */     else if (limb == 3) {
/*     */       
/* 304 */       GL11.glTranslatef(-0.25F + (this.rand.nextFloat() - 0.5F) * spread * properShatterProg, -0.625F * shatterProg, 0.0F);
/* 305 */       pixel.field_78800_c = -2.0F;
/* 306 */       pixel.field_78797_d = -2.0F * shatterProg;
/*     */     }
/* 308 */     else if (limb == 4) {
/*     */       
/* 310 */       GL11.glTranslatef(0.0F, -0.75F * shatterProg, 0.0F);
/*     */     }
/* 312 */     else if (limb == 5) {
/*     */       
/* 314 */       GL11.glTranslatef(0.0F, sneaking ? -0.5625F : (-0.75F * shatterProg), 0.0F);
/*     */       
/* 316 */       GL11.glRotatef(rotationPitch, 1.0F, 0.0F, 0.0F);
/* 317 */       pixel.field_78797_d = -8.0F * shatterProg;
/*     */     } 
/*     */     
/* 320 */     if (limb < 4) {
/*     */       
/* 322 */       if (limb == 0 || limb == 3)
/*     */       {
/* 324 */         GL11.glRotated(Math.toDegrees(Math.cos((f8 * 0.6662F + 3.1415927F)) * 1.399999976158142D * limbSwingAmount) * shatterProg, 1.0D, 0.0D, 0.0D);
/*     */       }
/* 326 */       if (limb == 1 || limb == 2)
/*     */       {
/* 328 */         GL11.glRotated(Math.toDegrees(Math.cos((f8 * 0.6662F)) * 1.399999976158142D * limbSwingAmount) * shatterProg, 1.0D, 0.0D, 0.0D);
/*     */       }
/*     */     } 
/*     */     
/* 332 */     GL11.glTranslatef(0.0F, 0.6875F * (1.0F - shatterProg) + this.rand.nextFloat() * 0.01F, 0.0F);
/*     */     
/* 334 */     GL11.glTranslatef(-(offsetX + i + 0.5F) / 16.0F + (this.rand.nextFloat() - 0.5F) * spread * properShatterProg, -(offsetY + j + 0.5F) / 16.0F * shatterProg, -(offsetZ + k + 0.5F) / 16.0F + (this.rand.nextFloat() - 0.5F) * spread * properShatterProg);
/* 335 */     pixel.field_78795_f = pitch / 57.295776F;
/* 336 */     pixel.field_78796_g = yaw / 57.295776F;
/*     */     
/* 338 */     double vScale = Math.pow(1.0D - prog, 0.75D);
/* 339 */     GL11.glScaled(vScale, vScale, vScale);
/* 340 */     pixel.func_78785_a(f5);
/*     */     
/* 342 */     GL11.glPopMatrix();
/* 343 */     GL11.glPopMatrix();
/*     */   }
/*     */ }


/* Location:              C:\DADOS\Jogos\Minecraft Legends - Copia\mods\iChunUtil-4.2.2.jar!\ichun\client\voxel\ModelVoxel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */