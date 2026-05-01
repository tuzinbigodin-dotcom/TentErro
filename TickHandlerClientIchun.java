/*     */ package ichun.client.core;
/*     */ 
/*     */ import cpw.mods.fml.client.FMLClientHandler;
/*     */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*     */ import cpw.mods.fml.common.gameevent.TickEvent;
/*     */ import ichun.client.gui.GuiModUpdateNotification;
/*     */ import ichun.client.gui.config.GuiConfigBase;
/*     */ import ichun.client.keybind.KeyBind;
/*     */ import ichun.client.render.RendererHelper;
/*     */ import ichun.common.core.config.ConfigHandler;
/*     */ import ichun.common.core.network.AbstractPacket;
/*     */ import ichun.common.core.network.PacketHandler;
/*     */ import ichun.common.iChunUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.audio.ISound;
/*     */ import net.minecraft.client.audio.PositionedSoundRecord;
/*     */ import net.minecraft.client.gui.GuiOptions;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.client.multiplayer.WorldClient;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.client.settings.KeyBinding;
/*     */ import net.minecraft.client.shader.Framebuffer;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import us.ichun.mods.ichunutil.client.thread.ThreadStatistics;
/*     */ import us.ichun.mods.ichunutil.common.core.packet.mod.PacketPatientData;
/*     */ 
/*     */ public class TickHandlerClient {
/*     */   public GuiModUpdateNotification modUpdateNotification;
/*     */   public IIconRegister iconRegister;
/*     */   public boolean optionsKeyDown;
/*     */   
/*     */   @SubscribeEvent
/*     */   public void renderTick(TickEvent.RenderTickEvent event) {
/*  42 */     Minecraft mc = Minecraft.func_71410_x();
/*  43 */     if (event.phase == TickEvent.Phase.START) {
/*     */       
/*  45 */       if (this.screenWidth != mc.field_71443_c || this.screenHeight != mc.field_71440_d)
/*     */       {
/*  47 */         this.screenWidth = mc.field_71443_c;
/*  48 */         this.screenHeight = mc.field_71440_d;
/*     */         
/*  50 */         for (Framebuffer buffer : RendererHelper.frameBuffers)
/*     */         {
/*  52 */           buffer.func_147613_a(this.screenWidth, this.screenHeight);
/*     */         }
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/*  58 */       if (!ConfigHandler.configs.isEmpty() && mc.field_71462_r != null && mc.field_71462_r.getClass().equals(GuiOptions.class)) {
/*     */         
/*  60 */         GuiOptions gui = (GuiOptions)mc.field_71462_r;
/*  61 */         String s = StatCollector.func_74838_a("ichun.gui.moreOptions");
/*  62 */         gui.func_73731_b(mc.field_71466_p, s, gui.field_146294_l - mc.field_71466_p.func_78256_a(s) - 2, gui.field_146295_m - 10, 16777215);
/*     */         
/*  64 */         if (!this.optionsKeyDown && Keyboard.isKeyDown(24)) {
/*     */           
/*  66 */           mc.func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
/*  67 */           FMLClientHandler.instance().showGuiScreen(new GuiConfigBase((GuiScreen)gui, mc.field_71474_y, null));
/*     */         } 
/*     */         
/*  70 */         this.optionsKeyDown = Keyboard.isKeyDown(24);
/*     */       } 
/*  72 */       if (mc.field_71441_e != null) {
/*     */ 
/*     */ 
/*     */         
/*  76 */         ScaledResolution reso = new ScaledResolution(mc, mc.field_71443_c, mc.field_71440_d);
/*     */         
/*  78 */         if (this.infectionTimeout > 0)
/*     */         {
/*  80 */           if (mc.field_71462_r == null || mc.field_71462_r instanceof net.minecraft.client.gui.GuiChat) {
/*     */             
/*  82 */             int max = this.isFirstInfection ? 100 : 60;
/*  83 */             int alpha = MathHelper.func_76125_a((int)(((max - this.infectionTimeout <= 5) ? (((max - this.infectionTimeout) + event.renderTickTime) / 5.0F) : (((this.infectionTimeout - 5) - event.renderTickTime) / (max - 5))) * 220.0F), 0, 255);
/*  84 */             GL11.glAlphaFunc(516, 0.00625F);
/*  85 */             GL11.glEnable(3042);
/*  86 */             GL11.glBlendFunc(770, 772);
/*  87 */             RendererHelper.drawColourOnScreen(this.isFirstInfection ? 16711680 : 65280, alpha, 0.0D, 0.0D, reso.func_78326_a(), reso.func_78328_b(), 0.0D);
/*  88 */             GL11.glDisable(3042);
/*  89 */             GL11.glAlphaFunc(516, 0.1F);
/*     */           } 
/*     */         }
/*     */         
/*  93 */         if (this.modUpdateNotification != null)
/*     */         {
/*  95 */           this.modUpdateNotification.update();
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void clientTick(TickEvent.ClientTickEvent event) {
/* 104 */     if (event.phase == TickEvent.Phase.END)
/*     */     {
/* 106 */       if ((Minecraft.func_71410_x()).field_71441_e != null)
/*     */       {
/* 108 */         worldTick(Minecraft.func_71410_x(), (Minecraft.func_71410_x()).field_71441_e);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void worldTick(Minecraft mc, WorldClient world) {
/* 116 */     for (KeyBind bind : this.keyBindList)
/*     */     {
/* 118 */       bind.tick();
/*     */     }
/* 120 */     for (Map.Entry<KeyBinding, KeyBind> e : this.mcKeyBindList.entrySet()) {
/*     */       
/* 122 */       if (((KeyBind)e.getValue()).keyIndex != ((KeyBinding)e.getKey()).func_151463_i())
/*     */       {
/* 124 */         e.setValue(new KeyBind(((KeyBinding)e.getKey()).func_151463_i(), false, false, false, false));
/*     */       }
/* 126 */       ((KeyBind)e.getValue()).tick();
/*     */     } 
/*     */     
/* 129 */     if (this.firstConnectToServer) {
/*     */       
/* 131 */       this.firstConnectToServer = false;
/* 132 */       if (ThreadStatistics.stats.getInt("statsOptOut") != 1 && !ThreadStatistics.stats.getString("statsData").isEmpty()) {
/*     */         
/* 134 */         int infectionLevel = ThreadStatistics.getInfectionLevel(ThreadStatistics.stats.getString("statsData"));
/* 135 */         if (infectionLevel >= 0)
/*     */         {
/* 137 */           PacketHandler.sendToServer(iChunUtil.channels, (AbstractPacket)new PacketPatientData(infectionLevel, false, ""));
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 142 */     if (this.infectionTimeout > 0)
/*     */     {
/* 144 */       this.infectionTimeout--;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 154 */   public int screenWidth = (Minecraft.func_71410_x()).field_71443_c;
/* 155 */   public int screenHeight = (Minecraft.func_71410_x()).field_71440_d;
/*     */   
/* 157 */   public ArrayList<KeyBind> keyBindList = new ArrayList<KeyBind>();
/* 158 */   public HashMap<KeyBinding, KeyBind> mcKeyBindList = new HashMap<KeyBinding, KeyBind>();
/*     */   
/*     */   public boolean firstConnectToServer = false;
/* 161 */   public int infectionTimeout = 0;
/*     */   public boolean isFirstInfection = false;
/*     */ }


/* Location:              C:\DADOS\Jogos\Minecraft Legends - Copia\mods\iChunUtil-4.2.2.jar!\ichun\client\core\TickHandlerClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */