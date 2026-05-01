/*     */ package ichun.common;
/*     */ 
/*     */ import cpw.mods.fml.common.FMLCommonHandler;
/*     */ import cpw.mods.fml.common.Loader;
/*     */ import cpw.mods.fml.common.Mod;
/*     */ import cpw.mods.fml.common.Mod.EventHandler;
/*     */ import cpw.mods.fml.common.Mod.Instance;
/*     */ import cpw.mods.fml.common.SidedProxy;
/*     */ import cpw.mods.fml.common.event.FMLInitializationEvent;
/*     */ import cpw.mods.fml.common.event.FMLPostInitializationEvent;
/*     */ import cpw.mods.fml.common.event.FMLPreInitializationEvent;
/*     */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*     */ import cpw.mods.fml.common.gameevent.PlayerEvent;
/*     */ import cpw.mods.fml.common.network.FMLEmbeddedChannel;
/*     */ import cpw.mods.fml.common.network.FMLNetworkEvent;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import ichun.common.core.CommonProxy;
/*     */ import ichun.common.core.config.Config;
/*     */ import ichun.common.core.config.ConfigHandler;
/*     */ import ichun.common.core.config.IConfigUser;
/*     */ import ichun.common.core.network.AbstractPacket;
/*     */ import ichun.common.core.network.ChannelHandler;
/*     */ import ichun.common.core.network.PacketHandler;
/*     */ import ichun.common.core.packet.PacketPatrons;
/*     */ import ichun.common.core.packet.PacketShowPatronReward;
/*     */ import ichun.common.core.updateChecker.ModVersionChecker;
/*     */ import ichun.common.core.updateChecker.ModVersionInfo;
/*     */ import ichun.common.core.updateChecker.PacketModsList;
/*     */ import ichun.common.core.util.ObfHelper;
/*     */ import java.util.ArrayList;
/*     */ import java.util.EnumMap;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraftforge.client.event.TextureStitchEvent;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.common.config.Property;
/*     */ import org.apache.logging.log4j.Level;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import us.ichun.mods.ichunutil.common.core.packet.mod.PacketPatientData;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ @Mod(modid = "iChunUtil", name = "iChunUtil", version = "4.2.2", dependencies = "required-after:Forge@[10.13.2.1291,)")
/*     */ public class iChunUtil
/*     */   implements IConfigUser
/*     */ {
/*     */   public static final int versionMC = 4;
/*     */   public static final String versionOfMC = "1.7.10";
/*     */   public static final String version = "4.2.2";
/*     */   private static boolean hasPostLoad = false;
/*  62 */   private static Logger logger = LogManager.getLogger("iChunUtil");
/*     */   
/*     */   public static EnumMap<Side, FMLEmbeddedChannel> channels;
/*     */   
/*     */   public static Config config;
/*     */   
/*     */   public static boolean hasMorphMod;
/*     */   
/*     */   public static boolean isPatron;
/*  71 */   public static ArrayList<String> patronList = new ArrayList<String>();
/*     */ 
/*     */   
/*     */   @Instance("iChunUtil")
/*     */   public static iChunUtil instance;
/*     */   
/*     */   @SidedProxy(clientSide = "ichun.client.core.ClientProxy", serverSide = "ichun.common.core.CommonProxy")
/*     */   public static CommonProxy proxy;
/*     */ 
/*     */   
/*     */   public boolean onConfigChange(Config cfg, Property prop) {
/*  82 */     if (prop.getName().equalsIgnoreCase("showPatronReward"))
/*     */     {
/*  84 */       CommonProxy.trailTicker.tellServerAsPatron = true;
/*     */     }
/*  86 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void preLoad(FMLPreInitializationEvent event) {
/*  96 */     ObfHelper.detectObfuscation();
/*     */     
/*  98 */     proxy.init();
/*     */     
/* 100 */     FMLCommonHandler.instance().bus().register(this);
/* 101 */     MinecraftForge.EVENT_BUS.register(this);
/*     */     
/* 103 */     config = ConfigHandler.createConfig(event.getSuggestedConfigurationFile(), "ichunutil", "iChunUtil", logger, instance);
/*     */     
/* 105 */     config.setCurrentCategory("versionCheck", "ichun.config.versionCheck.name", "ichun.config.versionCheck.comment");
/* 106 */     config.createIntProperty("versionNotificationTypes", "ichun.config.versionNotificationTypes.name", "ichun.config.versionNotificationTypes.comment", true, false, 1, 0, 2);
/* 107 */     config.createIntProperty("versionNotificationFrequency", "ichun.config.versionNotificationFrequency.name", "ichun.config.versionNotificationFrequency.comment", true, false, 0, 0, 3);
/*     */     
/* 109 */     config.setCurrentCategory("versionSave", "ichun.config.versionSave.name", "ichun.config.versionSave.comment");
/* 110 */     String lastCheck = config.createStringProperty("lastCheck", "Last Check", "", false, false, "");
/* 111 */     config.createIntProperty("dayCheck", "Day Check", "", false, false, 0, 0, 35);
/*     */     
/* 113 */     String[] split = lastCheck.split(", ");
/*     */     
/* 115 */     for (String s : split) {
/*     */       
/* 117 */       String[] str = s.split(": ");
/* 118 */       if (str.length >= 2)
/*     */       {
/* 120 */         CommonProxy.prevVerChecker.put(str[0], str[1]);
/*     */       }
/*     */     } 
/*     */     
/* 124 */     ModVersionChecker.register_iChunMod(new ModVersionInfo("iChunUtil", "1.7.10", "4.2.2", false));
/*     */     
/* 126 */     channels = ChannelHandler.getChannelHandlers("iChunUtil", new Class[] { PacketModsList.class, PacketPatrons.class, PacketShowPatronReward.class, PacketPatientData.class });
/*     */   }
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void load(FMLInitializationEvent event) {
/* 132 */     ModVersionChecker.init();
/*     */   }
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void postLoad(FMLPostInitializationEvent event) {
/* 138 */     proxy.postInit();
/*     */     
/* 140 */     hasPostLoad = true;
/* 141 */     for (Config cfg : ConfigHandler.configs)
/*     */     {
/* 143 */       cfg.setup();
/*     */     }
/* 145 */     if (FMLCommonHandler.instance().getEffectiveSide().isClient() && Config.configKeybind != null)
/*     */     {
/* 147 */       Config.configKeybind.save();
/*     */     }
/*     */     
/* 150 */     hasMorphMod = Loader.isModLoaded("Morph");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean getPostLoad() {
/* 161 */     return hasPostLoad;
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   @SubscribeEvent
/*     */   public void onTextureStitched(TextureStitchEvent.Pre event) {
/* 168 */     if (event.map.func_130086_a() == 0)
/*     */     {
/* 170 */       CommonProxy.tickHandlerClient.iconRegister = (IIconRegister)event.map;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   @SubscribeEvent
/*     */   public void onClientConnection(FMLNetworkEvent.ClientConnectedToServerEvent event) {
/* 178 */     if (isPatron)
/*     */     {
/* 180 */       CommonProxy.trailTicker.tellServerAsPatron = true;
/*     */     }
/* 182 */     CommonProxy.tickHandlerClient.firstConnectToServer = true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
/* 189 */     PacketHandler.sendToPlayer(channels, (AbstractPacket)new PacketModsList(config.getInt("versionNotificationTypes"), FMLCommonHandler.instance().getMinecraftServerInstance().func_71203_ab().func_152596_g(event.player.func_146103_bH())), event.player);
/* 190 */     PacketHandler.sendToPlayer(channels, (AbstractPacket)new PacketPatrons(), event.player);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void console(String s, boolean warning) {
/* 195 */     StringBuilder sb = new StringBuilder();
/* 196 */     logger.log(warning ? Level.WARN : Level.INFO, sb.append("[").append("4.2.2").append("] ").append(s).toString());
/*     */   }
/*     */ 
/*     */   
/*     */   public static void console(String s) {
/* 201 */     console(s, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void console(int i) {
/* 206 */     console((new Integer(i)).toString());
/*     */   }
/*     */ 
/*     */   
/*     */   public static void console(boolean b) {
/* 211 */     console((new Boolean(b)).toString());
/*     */   }
/*     */ 
/*     */   
/*     */   public static void console(float f) {
/* 216 */     console((new Float(f)).toString());
/*     */   }
/*     */ 
/*     */   
/*     */   public static void console(double d) {
/* 221 */     console((new Double(d)).toString());
/*     */   }
/*     */ }


/* Location:              C:\DADOS\Jogos\Minecraft Legends - Copia\mods\iChunUtil-4.2.2.jar!\ichun\common\iChunUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */