/*     */ package shatter.client.entity;
/*     */ 
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.world.World;
/*     */ import shatter.client.model.ModelShattered;
/*     */ import shatter.common.Shatter;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityShattered
/*     */   extends EntityLivingBase
/*     */ {
/*     */   public EntityLivingBase acquired;
/*     */   public int progress;
/*     */   public ModelShattered model;
/*     */   
/*     */   public EntityShattered(World par1World) {
/*  22 */     super(par1World);
/*  23 */     this.model = new ModelShattered(this);
/*  24 */     this.field_70129_M = -0.5F;
/*  25 */     func_70105_a(0.1F, 0.1F);
/*  26 */     this.field_70145_X = true;
/*  27 */     this.field_70155_l = 10.0D;
/*  28 */     this.field_70158_ak = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityShattered(World par1World, EntityLivingBase ac) {
/*  33 */     super(par1World);
/*  34 */     this.acquired = ac;
/*  35 */     this.model = new ModelShattered(this);
/*  36 */     this.progress = 0;
/*  37 */     this.field_70129_M = -0.5F;
/*  38 */     func_70105_a(0.1F, 0.1F);
/*  39 */     this.field_70145_X = true;
/*  40 */     this.field_70155_l = 10.0D;
/*  41 */     this.field_70158_ak = true;
/*  42 */     func_70012_b(this.acquired.field_70165_t, this.acquired.field_70163_u - this.acquired.field_70129_M, this.acquired.field_70161_v, this.acquired.field_70177_z, this.acquired.field_70125_A);
/*  43 */     this.field_70159_w = ac.field_70159_w * 0.4D;
/*  44 */     this.field_70181_x = ac.field_70181_x * 0.15D;
/*  45 */     this.field_70179_y = ac.field_70179_y * 0.4D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/*  51 */     this.field_70169_q = this.field_70165_t;
/*  52 */     this.field_70167_r = this.field_70163_u;
/*  53 */     this.field_70166_s = this.field_70161_v;
/*     */     
/*  55 */     this.progress++;
/*  56 */     Shatter.tickHandlerClient.getClass(); if (this.progress > 100 + 5) {
/*     */       
/*  58 */       func_70106_y();
/*     */       
/*     */       return;
/*     */     } 
/*  62 */     this.field_70165_t += this.field_70159_w;
/*  63 */     this.field_70163_u += this.field_70181_x * 0.2D;
/*  64 */     this.field_70161_v += this.field_70179_y;
/*     */     
/*  66 */     this.field_70159_w *= 0.97D;
/*  67 */     this.field_70181_x *= 0.97D;
/*  68 */     this.field_70179_y *= 0.97D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldRenderInPass(int pass) {
/*  74 */     return (pass == 1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70067_L() {
/*  80 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70104_M() {
/*  86 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70089_S() {
/*  92 */     return !this.field_70128_L;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70606_j(float par1) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70039_c(NBTTagCompound par1NBTTagCompound) {
/* 103 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {
/* 109 */     super.func_70088_a();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70037_a(NBTTagCompound nbttagcompound) {}
/*     */ 
/*     */   
/*     */   public void func_70014_b(NBTTagCompound nbttagcompound) {}
/*     */ 
/*     */   
/*     */   public ItemStack func_70694_bm() {
/* 120 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack func_71124_b(int i) {
/* 125 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70062_b(int i, ItemStack itemstack) {}
/*     */ 
/*     */   
/*     */   public ItemStack[] func_70035_c() {
/* 134 */     return new ItemStack[0];
/*     */   }
/*     */ }


/* Location:              C:\Users\SALA\Downloads\Nova pasta (2)\Shatter-4.0.0.jar!\shatter\client\entity\EntityShattered.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */