/*    */ package shatter.common;
/*    */ 
/*    */ import cpw.mods.fml.client.registry.RenderingRegistry;
/*    */ import cpw.mods.fml.common.FMLCommonHandler;
/*    */ import cpw.mods.fml.common.Mod;
/*    */ import cpw.mods.fml.common.Mod.EventHandler;
/*    */ import cpw.mods.fml.common.Mod.Instance;
/*    */ import cpw.mods.fml.common.event.FMLPreInitializationEvent;
/*    */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import ichun.common.core.config.Config;
/*    */ import ichun.common.core.config.ConfigHandler;
/*    */ import ichun.common.core.config.IConfigUser;
/*    */ import ichun.common.core.updateChecker.ModVersionChecker;
/*    */ import ichun.common.core.updateChecker.ModVersionInfo;
/*    */ import net.minecraft.client.renderer.entity.Render;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.common.config.Property;
/*    */ import net.minecraftforge.event.entity.living.LivingDeathEvent;
/*    */ import org.apache.logging.log4j.Level;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import shatter.client.core.TickHandlerClient;
/*    */ import shatter.client.entity.EntityShattered;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mod(modid = "Shatter", name = "Shatter", version = "4.0.0", dependencies = "required-after:iChunUtil@[4.0.0,)")
/*    */ public class Shatter
/*    */   implements IConfigUser
/*    */ {
/*    */   public static final String version = "4.0.0";
/* 38 */   private static final Logger logger = LogManager.getLogger("Shatter");
/*    */   
/*    */   @Instance("Shatter")
/*    */   public static Shatter instance;
/*    */   
/*    */   public static Config config;
/*    */   
/*    */   public static TickHandlerClient tickHandlerClient;
/*    */   
/*    */   public boolean onConfigChange(Config cfg, Property prop) {
/* 48 */     return true;
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void preLoad(FMLPreInitializationEvent event) {
/* 53 */     if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
/*    */       
/* 55 */       console("You're loading Shatter on a server! This is a client-only mod!", true);
/*    */       
/*    */       return;
/*    */     } 
/* 59 */     config = ConfigHandler.createConfig(event.getSuggestedConfigurationFile(), "shatter", "Shatter", logger, instance);
/*    */     
/* 61 */     config.setCurrentCategory("clientOnly", "shatter.config.cat.clientOnly.name", "shatter.config.cat.clientOnly.comment");
/* 62 */     config.createIntBoolProperty("enableBossShatter", "shatter.config.prop.enableBossShatter.name", "shatter.config.prop.enableBossShatter.comment", true, false, false);
/* 63 */     config.createIntBoolProperty("enablePlayerShatter", "shatter.config.prop.enablePlayerShatter.name", "shatter.config.prop.enablePlayerShatter.comment", true, false, true);
/* 64 */     config.createIntBoolProperty("enableChildShatter", "shatter.config.prop.enableChildShatter.name", "shatter.config.prop.enableChildShatter.comment", true, false, false);
/*    */     
/* 66 */     ModVersionChecker.register_iChunMod(new ModVersionInfo("Shatter", "1.7.10", "4.0.0", false));
/*    */     
/* 68 */     init();
/*    */   }
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public static void init() {
/* 74 */     tickHandlerClient = new TickHandlerClient();
/* 75 */     FMLCommonHandler.instance().bus().register(tickHandlerClient);
/*    */     
/* 77 */     MinecraftForge.EVENT_BUS.register(instance);
/*    */     
/* 79 */     RenderingRegistry.registerEntityRenderingHandler(EntityShattered.class, (Render)tickHandlerClient.renderShatteredInstance);
/*    */   }
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onLivingDeath(LivingDeathEvent event) {
/* 85 */     if (FMLCommonHandler.instance().getEffectiveSide().isClient()) {
/*    */       
/* 87 */       if ((config.getInt("enableBossShatter") == 0 && event.entityLiving instanceof net.minecraft.entity.boss.IBossDisplayData) || (config.getInt("enableChildShatter") == 0 && event.entityLiving.func_70631_g_())) {
/*    */         return;
/*    */       }
/*    */       
/* 91 */       tickHandlerClient.shatterTimeout.put(event.entityLiving, Integer.valueOf(2));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void console(String s, boolean warning) {
/* 97 */     StringBuilder sb = new StringBuilder();
/* 98 */     logger.log(warning ? Level.WARN : Level.INFO, sb.append("[").append("4.0.0").append("] ").append(s).toString());
/*    */   }
/*    */ }


/* Location:              C:\Users\SALA\Downloads\Nova pasta (2)\Shatter-4.0.0.jar!\shatter\common\Shatter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */