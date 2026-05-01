/*    */ package shatter.client.render;
/*    */ 
/*    */ import ichun.common.core.util.ObfHelper;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.client.renderer.entity.RendererLivingEntity;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import shatter.client.entity.EntityShattered;
/*    */ 
/*    */ 
/*    */ public class RenderShattered
/*    */   extends RendererLivingEntity
/*    */ {
/*    */   public RenderShattered(ModelBase par1ModelBase, float par2) {
/* 18 */     super(par1ModelBase, par2);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity entity) {
/* 24 */     if (entity instanceof EntityShattered)
/*    */     {
/* 26 */       setMainModel((ModelBase)((EntityShattered)entity).model);
/*    */     }
/* 28 */     return ObfHelper.invokeGetEntityTexture(RenderManager.field_78727_a.func_78713_a((Entity)((EntityShattered)entity).acquired), RenderManager.field_78727_a.func_78713_a((Entity)((EntityShattered)entity).acquired).getClass(), ((EntityShattered)entity).acquired);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_77033_b(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_76986_a(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9) {
/* 39 */     GL11.glAlphaFunc(516, 0.003921569F);
/* 40 */     super.func_76986_a(par1EntityLivingBase, par2, par4, par6, par8, par9);
/* 41 */     GL11.glAlphaFunc(516, 0.1F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setMainModel(ModelBase base) {
/* 46 */     this.field_77045_g = base;
/*    */   }
/*    */ }


/* Location:              C:\Users\SALA\Downloads\Nova pasta (2)\Shatter-4.0.0.jar!\shatter\client\render\RenderShattered.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */