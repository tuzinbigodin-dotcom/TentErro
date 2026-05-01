/*    */ package ichun.client.voxel;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import ichun.common.core.CommonProxy;
/*    */ import net.minecraft.client.entity.AbstractClientPlayer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class EntityTrail
/*    */   extends Entity
/*    */ {
/*    */   public AbstractClientPlayer parent;
/*    */   public long lastUpdate;
/*    */   public ModelVoxel model;
/*    */   
/*    */   public EntityTrail(World par1World) {
/* 25 */     super(par1World);
/* 26 */     func_70105_a(0.1F, 0.1F);
/*    */     
/* 28 */     this.lastUpdate = par1World.func_72820_D();
/* 29 */     this.field_70158_ak = true;
/* 30 */     this.field_70155_l = 10.0D;
/*    */     
/* 32 */     this.model = new ModelVoxel();
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityTrail(World par1World, AbstractClientPlayer ent) {
/* 37 */     super(par1World);
/* 38 */     func_70105_a(0.1F, 0.1F);
/* 39 */     this.parent = ent;
/* 40 */     func_70012_b(this.parent.field_70165_t, this.parent.field_70121_D.field_72338_b, this.parent.field_70161_v, this.parent.field_70177_z, this.parent.field_70125_A);
/* 41 */     this.lastUpdate = par1World.func_72820_D();
/* 42 */     this.field_70158_ak = true;
/* 43 */     this.field_70155_l = 10.0D;
/*    */     
/* 45 */     this.model = new ModelVoxel();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_70071_h_() {
/* 51 */     this.field_70173_aa++;
/*    */     
/* 53 */     if (this.parent != null && this.parent.func_70089_S() && !this.parent.func_70631_g_()) if (CommonProxy.trailTicker.streaks.get(this.parent.func_70005_c_()) == this) {
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 59 */         this.lastUpdate = this.field_70170_p.func_72820_D();
/*    */         return;
/*    */       }  
/*    */     func_70106_y();
/*    */   }
/*    */   public boolean shouldRenderInPass(int pass) {
/* 65 */     return (pass == 1);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int func_70070_b(float par1) {
/* 71 */     return super.func_70070_b(par1);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_70106_y() {
/* 77 */     super.func_70106_y();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_70088_a() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_70039_c(NBTTagCompound par1NBTTagCompound) {
/* 88 */     return false;
/*    */   }
/*    */   
/*    */   public void func_70037_a(NBTTagCompound nbttagcompound) {}
/*    */   
/*    */   public void func_70014_b(NBTTagCompound nbttagcompound) {}
/*    */ }


/* Location:              C:\DADOS\Jogos\Minecraft Legends - Copia\mods\iChunUtil-4.2.2.jar!\ichun\client\voxel\EntityTrail.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */