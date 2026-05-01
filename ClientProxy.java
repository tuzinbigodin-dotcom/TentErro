/*     */ package ichun.client.core;
/*     */ 
/*     */ import com.mojang.authlib.GameProfileRepository;
/*     */ import com.mojang.authlib.minecraft.MinecraftSessionService;
/*     */ import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
/*     */ import cpw.mods.fml.client.registry.RenderingRegistry;
/*     */ import cpw.mods.fml.common.FMLCommonHandler;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import ichun.client.gui.GuiModUpdateNotification;
/*     */ import ichun.client.keybind.KeyBind;
/*     */ import ichun.client.thread.ThreadGetPatrons;
/*     */ import ichun.client.voxel.EntityTrail;
/*     */ import ichun.client.voxel.RenderVoxels;
/*     */ import ichun.client.voxel.TrailTicker;
/*     */ import ichun.common.core.CommonProxy;
/*     */ import ichun.common.core.config.Config;
/*     */ import ichun.common.core.util.ResourceHelper;
/*     */ import java.io.File;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.entity.Render;
/*     */ import net.minecraft.client.settings.KeyBinding;
/*     */ import net.minecraftforge.common.config.Configuration;
/*     */ import us.ichun.mods.ichunutil.client.thread.ThreadStatistics;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ClientProxy
/*     */   extends CommonProxy
/*     */ {
/*     */   public void init() {
/*  38 */     super.init();
/*  39 */     ResourceHelper.init();
/*     */     
/*  41 */     File file = new File(ResourceHelper.getConfigFolder(), "iChunUtil_KeyBinds.cfg");
/*  42 */     Config.configKeybind = new Configuration(file);
/*  43 */     Config.configKeybind.load();
/*     */     
/*  45 */     tickHandlerClient = new TickHandlerClient();
/*  46 */     FMLCommonHandler.instance().bus().register(tickHandlerClient);
/*     */     
/*  48 */     trailTicker = new TrailTicker();
/*  49 */     FMLCommonHandler.instance().bus().register(trailTicker);
/*  50 */     RenderingRegistry.registerEntityRenderingHandler(EntityTrail.class, (Render)new RenderVoxels());
/*     */     
/*  52 */     (new ThreadGetPatrons()).start();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void postInit() {
/*  58 */     super.postInit();
/*  59 */     ThreadStatistics.checkFirstLaunch();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public GameProfileRepository createProfileRepo() {
/*  65 */     return (new YggdrasilAuthenticationService(Minecraft.func_71410_x().func_110437_J(), UUID.randomUUID().toString())).createProfileRepository();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public MinecraftSessionService getSessionService() {
/*  71 */     return Minecraft.func_71410_x().func_152347_ac();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void notifyNewUpdate(String modName, String version) {
/*  77 */     versionChecker.put(modName, version);
/*  78 */     if (tickHandlerClient.modUpdateNotification == null)
/*     */     {
/*  80 */       tickHandlerClient.modUpdateNotification = new GuiModUpdateNotification(Minecraft.func_71410_x());
/*     */     }
/*  82 */     tickHandlerClient.modUpdateNotification.addModUpdate(modName, version);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public KeyBind registerKeyBind(KeyBind bind, KeyBind replacing) {
/*  89 */     if (replacing != null) {
/*     */       
/*  91 */       if (bind.equals(replacing))
/*     */       {
/*  93 */         return replacing;
/*     */       }
/*  95 */       for (int i = tickHandlerClient.keyBindList.size() - 1; i >= 0; i--) {
/*     */         
/*  97 */         KeyBind keybind = tickHandlerClient.keyBindList.get(i);
/*  98 */         if (keybind.equals(replacing)) {
/*     */           
/* 100 */           keybind.usages--;
/* 101 */           if (keybind.usages <= 0)
/*     */           {
/* 103 */             tickHandlerClient.keyBindList.remove(i);
/*     */           }
/* 105 */           bind.setPulse(keybind.canPulse, keybind.pulseTime);
/* 106 */           bind.ignoreHold = keybind.ignoreHold;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 111 */     for (KeyBind keybind : tickHandlerClient.keyBindList) {
/*     */       
/* 113 */       if (keybind.equals(bind)) {
/*     */         
/* 115 */         keybind.usages++;
/* 116 */         return keybind;
/*     */       } 
/*     */     } 
/* 119 */     bind.usages++;
/* 120 */     tickHandlerClient.keyBindList.add(bind);
/* 121 */     return bind;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void registerMinecraftKeyBind(KeyBinding bind) {
/* 128 */     tickHandlerClient.mcKeyBindList.put(bind, (new KeyBind(bind.func_151463_i(), false, false, false, true)).setIsMinecraftBind());
/*     */   }
/*     */ }


/* Location:              C:\DADOS\Jogos\Minecraft Legends - Copia\mods\iChunUtil-4.2.2.jar!\ichun\client\core\ClientProxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */