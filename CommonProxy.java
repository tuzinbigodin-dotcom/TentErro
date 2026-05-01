/*    */ package ichun.common.core;
/*    */ 
/*    */ import com.mojang.authlib.GameProfileRepository;
/*    */ import com.mojang.authlib.minecraft.MinecraftSessionService;
/*    */ import cpw.mods.fml.common.FMLCommonHandler;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import ichun.client.core.TickHandlerClient;
/*    */ import ichun.client.keybind.KeyBind;
/*    */ import ichun.client.voxel.TrailTicker;
/*    */ import ichun.common.core.util.EventCalendar;
/*    */ import ichun.common.iChunUtil;
/*    */ import java.util.HashMap;
/*    */ import net.minecraft.client.settings.KeyBinding;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ import us.ichun.mods.ichunutil.common.core.TickHandlerServer;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommonProxy
/*    */ {
/*    */   public static TickHandlerClient tickHandlerClient;
/*    */   public static TickHandlerServer tickHandlerServer;
/*    */   public static TrailTicker trailTicker;
/* 25 */   public static HashMap<String, String> versionChecker = new HashMap<String, String>();
/* 26 */   public static HashMap<String, String> prevVerChecker = new HashMap<String, String>();
/*    */ 
/*    */   
/*    */   public void init() {
/* 30 */     EventCalendar.checkDate();
/*    */     
/* 32 */     tickHandlerServer = new TickHandlerServer();
/* 33 */     FMLCommonHandler.instance().bus().register(tickHandlerServer);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void postInit() {}
/*    */ 
/*    */   
/*    */   public GameProfileRepository createProfileRepo() {
/* 42 */     return FMLCommonHandler.instance().getMinecraftServerInstance().func_152359_aw();
/*    */   }
/*    */ 
/*    */   
/*    */   public MinecraftSessionService getSessionService() {
/* 47 */     return MinecraftServer.func_71276_C().func_147130_as();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void notifyNewUpdate(String modName, String version) {
/* 60 */     versionChecker.put(modName, version);
/* 61 */     iChunUtil.console("[NEW UPDATE AVAILABLE] " + modName + " - " + version);
/*    */   }
/*    */   @SideOnly(Side.CLIENT)
/*    */   public KeyBind registerKeyBind(KeyBind bind, KeyBind replacing) {
/* 65 */     return bind;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void registerMinecraftKeyBind(KeyBinding bind) {}
/*    */ }


/* Location:              C:\DADOS\Jogos\Minecraft Legends - Copia\mods\iChunUtil-4.2.2.jar!\ichun\common\core\CommonProxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */