/*     */ package shatter.client.model;
/*     */ 
/*     */ import ichun.client.model.ModelHelper;
/*     */ import ichun.common.core.util.ObfHelper;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.client.renderer.GLAllocation;
/*     */ import net.minecraft.client.renderer.entity.Render;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import shatter.client.entity.EntityShattered;
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
/*     */ public class ModelShattered
/*     */   extends ModelBase
/*     */ {
/*     */   public EntityShattered shatteredEnt;
/*     */   public Render entRenderer;
/*     */   public Random rand;
/*     */   public ArrayList<ModelRenderer> modelList;
/*     */   public float renderYaw;
/*     */   
/*     */   public ModelShattered() {}
/*     */   
/*     */   public ModelShattered(EntityShattered ent) {
/*  40 */     this.shatteredEnt = ent;
/*  41 */     this.rand = new Random();
/*     */     
/*  43 */     if (ent != null) {
/*     */       
/*  45 */       this.entRenderer = RenderManager.field_78727_a.func_78713_a((Entity)ent.acquired);
/*     */       
/*  47 */       if (RenderManager.field_78727_a.field_78724_e != null && RenderManager.field_78727_a.field_78734_h != null && ent.acquired != null)
/*     */       {
/*  49 */         RenderManager.field_78727_a.func_78713_a((Entity)ent.acquired).func_76986_a((Entity)ent.acquired, 0.0D, -500.0D, 0.0D, 0.0F, 1.0F);
/*     */       }
/*     */       
/*  52 */       this.modelList = ModelHelper.getModelCubesCopy(ModelHelper.getModelCubes(ent.acquired), this, ent.acquired);
/*     */       
/*  54 */       for (ModelRenderer cube : this.modelList)
/*     */       {
/*  56 */         cube.field_78797_d = (float)(cube.field_78797_d - 8.0D);
/*     */       }
/*     */     } 
/*     */     
/*  60 */     this.renderYaw = ent.acquired.field_70761_aq;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/*  66 */     if (this.shatteredEnt.acquired == (Minecraft.func_71410_x()).field_71439_g && (Minecraft.func_71410_x()).field_71474_y.field_74320_O == 0) {
/*     */       return;
/*     */     }
/*     */     
/*  70 */     GL11.glPushMatrix();
/*     */     
/*  72 */     GL11.glRotatef(this.renderYaw, 0.0F, 1.0F, 0.0F);
/*     */     
/*  74 */     FloatBuffer buffer = GLAllocation.func_74529_h(16);
/*  75 */     FloatBuffer buffer1 = GLAllocation.func_74529_h(16);
/*     */ 
/*     */ 
/*     */     
/*  79 */     ObfHelper.invokePreRenderCallback(this.entRenderer, this.entRenderer.getClass(), (Entity)this.shatteredEnt.acquired, f5);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  89 */     Shatter.tickHandlerClient.getClass(); float progress = MathHelper.func_76131_a((float)Math.pow((this.shatteredEnt.progress + f5) / 100.0D, 0.99D), 0.0F, 1.0F);
/*     */     
/*  91 */     GL11.glDepthMask(true);
/*  92 */     GL11.glEnable(3042);
/*  93 */     GL11.glBlendFunc(770, 771);
/*  94 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F - progress);
/*     */     
/*  96 */     for (int i = 0; i < this.modelList.size(); i++) {
/*     */       
/*  98 */       ModelRenderer cube = this.modelList.get(i);
/*     */       
/* 100 */       GL11.glPushMatrix();
/* 101 */       this.rand.setSeed((this.rand.nextInt() * this.shatteredEnt.func_145782_y() * i * 1000));
/* 102 */       GL11.glTranslated((this.rand.nextFloat() * ((this.rand.nextFloat() > 0.5F) ? -1 : true) * progress) * this.shatteredEnt.field_70179_y * 5.0D, this.rand.nextDouble() * progress * (this.shatteredEnt.field_70181_x + this.rand.nextDouble() - 1.0D), (this.rand.nextFloat() * ((this.rand.nextFloat() > 0.5F) ? -1 : true) * progress) * this.shatteredEnt.field_70159_w * 5.0D);
/* 103 */       GL11.glRotatef(180.0F * this.rand.nextFloat() * progress, this.rand.nextFloat() * ((this.rand.nextFloat() > 0.5F) ? -1 : true) * progress, this.rand.nextFloat() * ((this.rand.nextFloat() > 0.5F) ? -1 : true) * progress, this.rand.nextFloat() * ((this.rand.nextFloat() > 0.5F) ? -1 : true) * progress);
/* 104 */       cube.func_78785_a(f5);
/* 105 */       GL11.glPopMatrix();
/*     */     } 
/*     */     
/* 108 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/* 110 */     GL11.glDisable(3042);
/* 111 */     GL11.glPopMatrix();
/*     */   }
/*     */ }


/* Location:              C:\Users\SALA\Downloads\Nova pasta (2)\Shatter-4.0.0.jar!\shatter\client\model\ModelShattered.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */