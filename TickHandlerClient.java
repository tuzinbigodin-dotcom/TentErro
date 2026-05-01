/*     */ package shatter.client.core;
/*     */ 
/*     */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*     */ import cpw.mods.fml.common.gameevent.TickEvent;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.multiplayer.WorldClient;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.world.World;
/*     */ import shatter.client.entity.EntityShattered;
/*     */ import shatter.client.model.ModelShattered;
/*     */ import shatter.client.render.RenderShattered;
/*     */ import shatter.common.Shatter;
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
/*     */ public class TickHandlerClient
/*     */ {
/*     */   public long clock;
/*     */   public RenderShattered renderShatteredInstance;
/*     */   public HashMap<EntityLivingBase, Integer> shatterTimeout;
/*     */   public ArrayList<EntityPlayer> deadPlayers;
/*     */   public final int maxShatterProgress = 100;
/*     */   
/*     */   public TickHandlerClient() {
/* 108 */     this.shatterTimeout = new HashMap<EntityLivingBase, Integer>();
/* 109 */     this.deadPlayers = new ArrayList<EntityPlayer>();
/*     */     
/* 111 */     this.maxShatterProgress = 100;
/*     */     this.renderShatteredInstance = new RenderShattered((ModelBase)new ModelShattered(), 0.0F);
/*     */     this.renderShatteredInstance.func_76976_a(RenderManager.field_78727_a);
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void worldTick(TickEvent.ClientTickEvent event) {
/*     */     if (event.phase != TickEvent.Phase.END || (Minecraft.func_71410_x()).field_71441_e == null)
/*     */       return; 
/*     */     Minecraft mc = Minecraft.func_71410_x();
/*     */     WorldClient world = mc.field_71441_e;
/*     */     if (this.clock != world.func_72820_D() || !world.func_82736_K().func_82766_b("doDaylightCycle")) {
/*     */       this.clock = world.func_72820_D();
/*     */       Iterator<Map.Entry<EntityLivingBase, Integer>> ite = this.shatterTimeout.entrySet().iterator();
/*     */       if (ite.hasNext()) {
/*     */         Map.Entry<EntityLivingBase, Integer> e = ite.next();
/*     */         e.setValue(Integer.valueOf(((Integer)e.getValue()).intValue() - 1));
/*     */         ((EntityLivingBase)e.getKey()).field_70737_aN = 0;
/*     */         ((EntityLivingBase)e.getKey()).field_70725_aQ = 0;
/*     */         Entity explo = null;
/*     */         double dist = 1000.0D;
/*     */         if (((Integer)e.getValue()).intValue() <= 0) {
/*     */           if (((EntityLivingBase)e.getKey()).field_70170_p == world) {
/*     */             ((EntityLivingBase)e.getKey()).field_70170_p.func_72838_d((Entity)new EntityShattered(((EntityLivingBase)e.getKey()).field_70170_p, e.getKey()));
/*     */             ((EntityLivingBase)e.getKey()).func_70106_y();
/*     */           } 
/*     */           ite.remove();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void playerTick(TickEvent.PlayerTickEvent event) {
/*     */     if (event.side == Side.SERVER || event.phase != TickEvent.Phase.END)
/*     */       return; 
/*     */     World world = event.player.field_70170_p;
/*     */     EntityPlayer player = event.player;
/*     */     if (Shatter.config.getInt("enablePlayerShatter") == 1) {
/*     */       if (!player.func_70089_S() && !this.shatterTimeout.containsKey(player) && !this.deadPlayers.contains(player)) {
/*     */         this.deadPlayers.add(player);
/*     */         this.shatterTimeout.put(player, Integer.valueOf(2));
/*     */       } 
/*     */       for (int k = this.deadPlayers.size() - 1; k >= 0; k--) {
/*     */         EntityPlayer deadPlayer = this.deadPlayers.get(k);
/*     */         if (deadPlayer.field_70170_p != world || (deadPlayer.func_70005_c_().equals(player.func_70005_c_()) && deadPlayer != player))
/*     */           this.deadPlayers.remove(k); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\SALA\Downloads\Nova pasta (2)\Shatter-4.0.0.jar!\shatter\client\core\TickHandlerClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */