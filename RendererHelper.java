/*     */ package ichun.client.render;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.client.renderer.ItemRenderer;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.client.shader.Framebuffer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.client.MinecraftForgeClient;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class RendererHelper
/*     */ {
/*  32 */   public static boolean hasStencilBits = (MinecraftForgeClient.getStencilBits() > 0);
/*  33 */   public static final ResourceLocation texEnchant = new ResourceLocation("textures/misc/enchanted_item_glint.png");
/*     */ 
/*     */ 
/*     */   
/*     */   public static void renderItemIn3d(ItemStack stack) {
/*  38 */     TextureManager textureManager = Minecraft.func_71410_x().func_110434_K();
/*     */     
/*  40 */     if (textureManager == null)
/*  41 */       return;  Item item = stack.func_77973_b();
/*     */     
/*  43 */     GL11.glPushMatrix();
/*     */     
/*  45 */     Tessellator tessellator = Tessellator.field_78398_a;
/*  46 */     GL11.glEnable(32826);
/*  47 */     GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/*  48 */     GL11.glTranslatef(-0.5F, -0.5F, 0.03125F);
/*     */     
/*  50 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/*  52 */     int passes = item.getRenderPasses(stack.func_77960_j());
/*  53 */     for (int pass = 0; pass < passes; pass++) {
/*  54 */       textureManager.func_110577_a((stack.func_94608_d() == 0) ? TextureMap.field_110575_b : TextureMap.field_110576_c);
/*  55 */       IIcon icon = item.getIcon(stack, pass);
/*  56 */       float minU = icon.func_94209_e();
/*  57 */       float maxU = icon.func_94212_f();
/*  58 */       float minV = icon.func_94206_g();
/*  59 */       float maxV = icon.func_94210_h();
/*  60 */       setColorFromInt(item.func_82790_a(stack, pass));
/*  61 */       ItemRenderer.func_78439_a(tessellator, maxU, minV, minU, maxV, icon.func_94211_a(), icon.func_94216_b(), 0.0625F);
/*     */     } 
/*     */     
/*  64 */     if (stack.hasEffect(0)) {
/*  65 */       GL11.glDepthFunc(514);
/*  66 */       GL11.glDisable(2896);
/*  67 */       textureManager.func_110577_a(texEnchant);
/*  68 */       GL11.glEnable(3042);
/*  69 */       GL11.glBlendFunc(768, 1);
/*  70 */       float f7 = 0.76F;
/*  71 */       GL11.glColor4f(0.5F * f7, 0.25F * f7, 0.8F * f7, 1.0F);
/*  72 */       GL11.glMatrixMode(5890);
/*  73 */       GL11.glPushMatrix();
/*  74 */       float f8 = 0.125F;
/*  75 */       GL11.glScalef(f8, f8, f8);
/*  76 */       float f9 = (float)(Minecraft.func_71386_F() % 3000L) / 3000.0F * 8.0F;
/*  77 */       GL11.glTranslatef(f9, 0.0F, 0.0F);
/*  78 */       GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
/*  79 */       ItemRenderer.func_78439_a(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
/*  80 */       GL11.glPopMatrix();
/*  81 */       GL11.glPushMatrix();
/*  82 */       GL11.glScalef(f8, f8, f8);
/*  83 */       f9 = (float)(Minecraft.func_71386_F() % 4873L) / 4873.0F * 8.0F;
/*  84 */       GL11.glTranslatef(-f9, 0.0F, 0.0F);
/*  85 */       GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
/*  86 */       ItemRenderer.func_78439_a(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
/*  87 */       GL11.glPopMatrix();
/*  88 */       GL11.glMatrixMode(5888);
/*  89 */       GL11.glDisable(3042);
/*  90 */       GL11.glEnable(2896);
/*  91 */       GL11.glDepthFunc(515);
/*     */     } 
/*     */     
/*  94 */     GL11.glDisable(32826);
/*     */     
/*  96 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   public static void setColorFromInt(int color) {
/* 100 */     float r = (color >> 16 & 0xFF) / 255.0F;
/* 101 */     float g = (color >> 8 & 0xFF) / 255.0F;
/* 102 */     float b = (color & 0xFF) / 255.0F;
/* 103 */     GL11.glColor4f(r, g, b, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawTextureOnScreen(ResourceLocation resource, double posX, double posY, double width, double height, double zLevel) {
/* 108 */     Minecraft.func_71410_x().func_110434_K().func_110577_a(resource);
/* 109 */     Tessellator tessellator = Tessellator.field_78398_a;
/* 110 */     tessellator.func_78382_b();
/* 111 */     tessellator.func_78374_a(posX, posY + height, zLevel, 0.0D, 1.0D);
/* 112 */     tessellator.func_78374_a(posX + width, posY + height, zLevel, 1.0D, 1.0D);
/* 113 */     tessellator.func_78374_a(posX + width, posY, zLevel, 1.0D, 0.0D);
/* 114 */     tessellator.func_78374_a(posX, posY, zLevel, 0.0D, 0.0D);
/* 115 */     tessellator.func_78381_a();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawColourOnScreen(int colour, int alpha, double posX, double posY, double width, double height, double zLevel) {
/* 120 */     int r = colour >> 16 & 0xFF;
/* 121 */     int g = colour >> 8 & 0xFF;
/* 122 */     int b = colour & 0xFF;
/* 123 */     drawColourOnScreen(r, g, b, alpha, posX, posY, width, height, zLevel);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawColourOnScreen(int r, int g, int b, int alpha, double posX, double posY, double width, double height, double zLevel) {
/* 128 */     if (width <= 0.0D || height <= 0.0D) {
/*     */       return;
/*     */     }
/*     */     
/* 132 */     GL11.glDisable(3553);
/* 133 */     Tessellator tessellator = Tessellator.field_78398_a;
/* 134 */     tessellator.func_78382_b();
/* 135 */     tessellator.func_78370_a(r, g, b, alpha);
/* 136 */     tessellator.func_78377_a(posX, posY + height, zLevel);
/* 137 */     tessellator.func_78377_a(posX + width, posY + height, zLevel);
/* 138 */     tessellator.func_78377_a(posX + width, posY, zLevel);
/* 139 */     tessellator.func_78377_a(posX, posY, zLevel);
/* 140 */     tessellator.func_78381_a();
/* 141 */     GL11.glEnable(3553);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void startGlScissor(int x, int y, int width, int height) {
/* 146 */     Minecraft mc = Minecraft.func_71410_x();
/*     */     
/* 148 */     ScaledResolution reso = new ScaledResolution(mc, mc.field_71443_c, mc.field_71440_d);
/*     */     
/* 150 */     double scaleW = mc.field_71443_c / reso.func_78327_c();
/* 151 */     double scaleH = mc.field_71440_d / reso.func_78324_d();
/*     */     
/* 153 */     if (width <= 0 || height <= 0) {
/*     */       return;
/*     */     }
/*     */     
/* 157 */     if (x < 0)
/*     */     {
/* 159 */       x = 0;
/*     */     }
/* 161 */     if (y < 0)
/*     */     {
/* 163 */       y = 0;
/*     */     }
/*     */     
/* 166 */     GL11.glEnable(3089);
/*     */     
/* 168 */     GL11.glScissor((int)Math.floor(x * scaleW), (int)Math.floor(mc.field_71440_d - (y + height) * scaleH), (int)Math.floor((x + width) * scaleW) - (int)Math.floor(x * scaleW), (int)Math.floor(mc.field_71440_d - y * scaleH) - (int)Math.floor(mc.field_71440_d - (y + height) * scaleH));
/*     */   }
/*     */ 
/*     */   
/*     */   public static void endGlScissor() {
/* 173 */     GL11.glDisable(3089);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void renderTestStencil() {
/* 180 */     Minecraft mc = Minecraft.func_71410_x();
/* 181 */     ScaledResolution reso1 = new ScaledResolution(mc, mc.field_71443_c, mc.field_71440_d);
/*     */     
/* 183 */     GL11.glEnable(2960);
/*     */     
/* 185 */     GL11.glColorMask(false, false, false, false);
/* 186 */     GL11.glDepthMask(false);
/*     */     
/* 188 */     GL11.glStencilFunc(512, 1, 255);
/* 189 */     GL11.glStencilOp(7681, 7680, 7680);
/*     */     
/* 191 */     GL11.glStencilMask(255);
/* 192 */     GL11.glClear(1024);
/*     */     
/* 194 */     drawColourOnScreen(16777215, 255, 0.0D, 0.0D, 60.0D, 60.0D, 0.0D);
/*     */     
/* 196 */     GL11.glColorMask(true, true, true, true);
/* 197 */     GL11.glDepthMask(true);
/*     */     
/* 199 */     GL11.glStencilMask(0);
/*     */     
/* 201 */     GL11.glStencilFunc(514, 0, 255);
/*     */     
/* 203 */     GL11.glStencilFunc(514, 1, 255);
/*     */     
/* 205 */     drawColourOnScreen(16777215, 255, 0.0D, 0.0D, reso1.func_78327_c(), reso1.func_78324_d(), 0.0D);
/*     */     
/* 207 */     GL11.glDisable(2960);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void renderTestSciccor() {
/* 213 */     Minecraft mc = Minecraft.func_71410_x();
/* 214 */     ScaledResolution reso1 = new ScaledResolution(mc, mc.field_71443_c, mc.field_71440_d);
/*     */     
/* 216 */     startGlScissor(reso1.func_78326_a() / 2 - 50, reso1.func_78328_b() / 2 - 50, 100, 100);
/*     */     
/* 218 */     GL11.glPushMatrix();
/*     */     
/* 220 */     GL11.glTranslatef(-15.0F, 15.0F, 0.0F);
/*     */     
/* 222 */     drawColourOnScreen(16777215, 255, 0.0D, 0.0D, reso1.func_78327_c(), reso1.func_78324_d(), 0.0D);
/*     */     
/* 224 */     GL11.glPopMatrix();
/*     */     
/* 226 */     endGlScissor();
/*     */   }
/*     */   
/* 229 */   public static ArrayList<Framebuffer> frameBuffers = new ArrayList<Framebuffer>();
/*     */ 
/*     */   
/*     */   public static Framebuffer createFrameBuffer(String modId, boolean useDepth) {
/* 233 */     Framebuffer render = new Framebuffer((Minecraft.func_71410_x()).field_71443_c, (Minecraft.func_71410_x()).field_71440_d, useDepth);
/* 234 */     frameBuffers.add(render);
/* 235 */     return render;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void deleteFrameBuffer(Framebuffer buffer) {
/* 240 */     GL11.glEnable(2929);
/* 241 */     if (buffer.field_147616_f >= 0)
/*     */     {
/* 243 */       buffer.func_147608_a();
/*     */     }
/* 245 */     frameBuffers.remove(buffer);
/*     */   }
/*     */ }


/* Location:              C:\DADOS\Jogos\Minecraft Legends - Copia\mods\iChunUtil-4.2.2.jar!\ichun\client\render\RendererHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */