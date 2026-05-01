/*     */ package ichun.client.model;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.ReflectionHelper;
/*     */ import ichun.common.core.util.ObfHelper;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelBiped;
/*     */ import net.minecraft.client.model.ModelBox;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.client.renderer.GLAllocation;
/*     */ import net.minecraft.client.renderer.entity.Render;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.renderer.entity.RendererLivingEntity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ 
/*     */ public class ModelHelper {
/*  23 */   public static Random rand = new Random();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArrayList<ModelRenderer> getModelCubesCopy(ArrayList<ModelRenderer> modelList, ModelBase base, EntityLivingBase ent) {
/*  29 */     if (RenderManager.field_78727_a.field_78724_e != null && RenderManager.field_78727_a.field_78734_h != null && ent != null) {
/*     */       
/*  31 */       for (int j = 0; j < modelList.size(); j++) {
/*     */         
/*  33 */         ModelRenderer cube = modelList.get(j);
/*  34 */         if (cube.field_78812_q) {
/*     */           
/*  36 */           GLAllocation.func_74523_b(cube.field_78811_r);
/*  37 */           cube.field_78812_q = false;
/*     */         } 
/*     */       } 
/*  40 */       RenderManager.field_78727_a.func_78713_a((Entity)ent).func_76986_a((Entity)ent, 0.0D, -500.0D, 0.0D, 0.0F, 1.0F);
/*     */       
/*  42 */       ArrayList<ModelRenderer> modelListCopy = new ArrayList<ModelRenderer>(modelList);
/*  43 */       ArrayList<ModelRenderer> arrayList1 = new ArrayList<ModelRenderer>();
/*     */       
/*  45 */       for (int k = modelListCopy.size() - 1; k >= 0; k--) {
/*     */         
/*  47 */         ModelRenderer cube = modelListCopy.get(k);
/*     */         
/*     */         try {
/*  50 */           if (!cube.field_78812_q)
/*     */           {
/*  52 */             modelListCopy.remove(k);
/*     */           }
/*     */         }
/*  55 */         catch (Exception e) {
/*     */           
/*  57 */           ObfHelper.obfWarning();
/*  58 */           e.printStackTrace();
/*     */         } 
/*     */       } 
/*  61 */       for (ModelRenderer cube : modelListCopy)
/*     */       {
/*  63 */         arrayList1.add(buildCopy(cube, base, 0, true, true));
/*     */       }
/*  65 */       return arrayList1;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  70 */     ArrayList<ModelRenderer> list = new ArrayList<ModelRenderer>();
/*     */     
/*  72 */     for (int i = 0; i < modelList.size(); i++) {
/*     */       
/*  74 */       ModelRenderer cube = modelList.get(i);
/*  75 */       list.add(buildCopy(cube, base, 0, true, true));
/*     */     } 
/*     */     
/*  78 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static ModelRenderer buildCopy(ModelRenderer original, ModelBase copyBase, int depth, boolean hasFullModelBox, boolean exactDupe) {
/*  84 */     int txOffsetX = original.field_78803_o;
/*  85 */     int txOffsetY = original.field_78813_p;
/*     */     
/*  87 */     ModelRenderer cubeCopy = new ModelRenderer(copyBase, txOffsetX, txOffsetY);
/*  88 */     cubeCopy.field_78809_i = original.field_78809_i;
/*  89 */     cubeCopy.field_78799_b = original.field_78799_b;
/*  90 */     cubeCopy.field_78801_a = original.field_78801_a;
/*     */     
/*  92 */     for (int j = 0; j < original.field_78804_l.size(); j++) {
/*     */       
/*  94 */       ModelBox box = original.field_78804_l.get(j);
/*  95 */       float param7 = 0.0F;
/*     */       
/*  97 */       if (exactDupe) {
/*     */         
/*  99 */         ModelBox boxCopy = new ModelBox(cubeCopy, txOffsetX, txOffsetY, box.field_78252_a, box.field_78250_b, box.field_78251_c, (int)Math.abs(box.field_78248_d - box.field_78252_a), (int)Math.abs(box.field_78249_e - box.field_78250_b), (int)Math.abs(box.field_78246_f - box.field_78251_c), 0.0F);
/* 100 */         boxCopy.field_78254_i = box.field_78254_i;
/* 101 */         cubeCopy.field_78804_l.add(boxCopy);
/*     */ 
/*     */       
/*     */       }
/* 105 */       else if (hasFullModelBox) {
/*     */         
/* 107 */         cubeCopy.func_78789_a(box.field_78252_a, box.field_78250_b, box.field_78251_c, (int)Math.abs(box.field_78248_d - box.field_78252_a), (int)Math.abs(box.field_78249_e - box.field_78250_b), (int)Math.abs(box.field_78246_f - box.field_78251_c));
/*     */       }
/*     */       else {
/*     */         
/* 111 */         ModelBox randBox = original.field_78804_l.get(rand.nextInt(original.field_78804_l.size()));
/*     */         
/* 113 */         float x = randBox.field_78252_a + ((randBox.field_78248_d - randBox.field_78252_a > 0.0F) ? rand.nextInt(((int)(randBox.field_78248_d - randBox.field_78252_a) > 0) ? (int)(randBox.field_78248_d - randBox.field_78252_a) : 1) : 0.0F);
/* 114 */         float y = randBox.field_78250_b + ((randBox.field_78249_e - randBox.field_78250_b > 0.0F) ? rand.nextInt(((int)(randBox.field_78249_e - randBox.field_78250_b) > 0) ? (int)(randBox.field_78249_e - randBox.field_78250_b) : 1) : 0.0F);
/* 115 */         float z = randBox.field_78251_c + ((randBox.field_78246_f - randBox.field_78251_c > 0.0F) ? rand.nextInt(((int)(randBox.field_78246_f - randBox.field_78251_c) > 0) ? (int)(randBox.field_78246_f - randBox.field_78251_c) : 1) : 0.0F);
/*     */         
/* 117 */         cubeCopy.func_78789_a(x, y, z, (int)Math.abs(box.field_78248_d - box.field_78252_a), (int)Math.abs(box.field_78249_e - box.field_78250_b), (int)Math.abs(box.field_78246_f - box.field_78251_c));
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 122 */     if (original.field_78805_m != null && depth < 20)
/*     */     {
/* 124 */       for (int i = 0; i < original.field_78805_m.size(); i++) {
/*     */         
/* 126 */         ModelRenderer child = original.field_78805_m.get(i);
/* 127 */         cubeCopy.func_78792_a(buildCopy(child, copyBase, depth + 1, hasFullModelBox, exactDupe));
/*     */       } 
/*     */     }
/*     */     
/* 131 */     cubeCopy.func_78793_a(original.field_78800_c, original.field_78797_d, original.field_78798_e);
/*     */     
/* 133 */     cubeCopy.field_78795_f = original.field_78795_f;
/* 134 */     cubeCopy.field_78796_g = original.field_78796_g;
/* 135 */     cubeCopy.field_78808_h = original.field_78808_h;
/* 136 */     return cubeCopy;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ArrayList<ModelRenderer> getModelCubes(ModelBase parent) {
/* 141 */     ArrayList<ModelRenderer> list = new ArrayList<ModelRenderer>();
/*     */     
/* 143 */     ArrayList<ModelRenderer[]> list1 = (ArrayList)new ArrayList<ModelRenderer>();
/*     */     
/* 145 */     if (parent != null) {
/*     */       
/* 147 */       Class<?> clz = parent.getClass();
/* 148 */       while (clz != ModelBase.class && ModelBase.class.isAssignableFrom(clz)) {
/*     */ 
/*     */         
/*     */         try {
/* 152 */           Field[] fields = clz.getDeclaredFields();
/* 153 */           for (Field f : fields) {
/*     */             
/* 155 */             f.setAccessible(true);
/* 156 */             if (f.getType() == ModelRenderer.class) {
/*     */               
/* 158 */               if ((clz == ModelBiped.class && !f.getName().equalsIgnoreCase("bipedCloak") && !f.getName().equalsIgnoreCase("k") && !f.getName().equalsIgnoreCase("field_78122_k")) || clz != ModelBiped.class)
/*     */               {
/* 160 */                 ModelRenderer rend = (ModelRenderer)f.get(parent);
/* 161 */                 if (rend != null)
/*     */                 {
/* 163 */                   list.add(rend);
/*     */                 }
/*     */               }
/*     */             
/* 167 */             } else if (f.getType() == ModelRenderer[].class) {
/*     */               
/* 169 */               ModelRenderer[] rend = (ModelRenderer[])f.get(parent);
/* 170 */               if (rend != null)
/*     */               {
/* 172 */                 list1.add(rend);
/*     */               }
/*     */             } 
/*     */           } 
/* 176 */           clz = clz.getSuperclass();
/*     */         }
/* 178 */         catch (Exception e) {
/*     */           
/* 180 */           throw new ReflectionHelper.UnableToAccessFieldException(new String[0], e);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 185 */     for (ModelRenderer[] cubes : list1) {
/*     */       
/* 187 */       for (ModelRenderer cube : cubes) {
/*     */         
/* 189 */         if (cube != null && !list.contains(cube))
/*     */         {
/* 191 */           list.add(cube);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 196 */     ArrayList<ModelRenderer> children = new ArrayList<ModelRenderer>();
/*     */     
/* 198 */     for (ModelRenderer cube : list) {
/*     */       
/* 200 */       for (ModelRenderer child : getChildren(cube, true, 0)) {
/*     */         
/* 202 */         if (!children.contains(child))
/*     */         {
/* 204 */           children.add(child);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 209 */     for (ModelRenderer child : children)
/*     */     {
/* 211 */       list.remove(child);
/*     */     }
/*     */     
/* 214 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   public static HashMap<String, ModelRenderer> getModelCubesWithNames(ModelBase parent) {
/* 219 */     HashMap<String, ModelRenderer> list = new HashMap<String, ModelRenderer>();
/*     */     
/* 221 */     HashMap<String, ModelRenderer[]> list1 = (HashMap)new HashMap<String, ModelRenderer>();
/*     */     
/* 223 */     if (parent != null) {
/*     */       
/* 225 */       Class<?> clz = parent.getClass();
/* 226 */       while (clz != ModelBase.class && ModelBase.class.isAssignableFrom(clz)) {
/*     */ 
/*     */         
/*     */         try {
/* 230 */           Field[] fields = clz.getDeclaredFields();
/* 231 */           for (Field f : fields) {
/*     */             
/* 233 */             f.setAccessible(true);
/* 234 */             if (f.getType() == ModelRenderer.class) {
/*     */               
/* 236 */               if ((clz == ModelBiped.class && !f.getName().equalsIgnoreCase("bipedCloak") && !f.getName().equalsIgnoreCase("k") && !f.getName().equalsIgnoreCase("field_78122_k")) || clz != ModelBiped.class) {
/*     */                 
/* 238 */                 ModelRenderer rend = (ModelRenderer)f.get(parent);
/* 239 */                 if (rend != null)
/*     */                 {
/* 241 */                   String name = f.getName();
/* 242 */                   if (rend.field_78802_n != null) {
/*     */                     
/* 244 */                     name = rend.field_78802_n;
/* 245 */                     while (list.containsKey(name))
/*     */                     {
/* 247 */                       name = name + "_";
/*     */                     }
/*     */                   } 
/* 250 */                   list.put(name, rend);
/*     */                 }
/*     */               
/*     */               } 
/* 254 */             } else if (f.getType() == ModelRenderer[].class) {
/*     */               
/* 256 */               ModelRenderer[] rend = (ModelRenderer[])f.get(parent);
/* 257 */               if (rend != null)
/*     */               {
/* 259 */                 list1.put(f.getName(), rend);
/*     */               }
/*     */             } 
/*     */           } 
/* 263 */           clz = clz.getSuperclass();
/*     */         }
/* 265 */         catch (Exception e) {
/*     */           
/* 267 */           throw new ReflectionHelper.UnableToAccessFieldException(new String[0], e);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 272 */     for (Map.Entry<String, ModelRenderer[]> e : list1.entrySet()) {
/*     */       
/* 274 */       int count = 1;
/* 275 */       for (ModelRenderer cube : (ModelRenderer[])e.getValue()) {
/*     */         
/* 277 */         if (cube != null && !list.containsValue(cube)) {
/*     */           
/* 279 */           list.put((String)e.getKey() + count, cube);
/* 280 */           count++;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 285 */     ArrayList<ModelRenderer> children = new ArrayList<ModelRenderer>();
/*     */     
/* 287 */     for (Map.Entry<String, ModelRenderer> e : list.entrySet()) {
/*     */       
/* 289 */       ModelRenderer cube = e.getValue();
/* 290 */       for (ModelRenderer child : getChildren(cube, true, 0)) {
/*     */         
/* 292 */         if (!children.contains(child))
/*     */         {
/* 294 */           children.add(child);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 299 */     for (ModelRenderer child : children) {
/*     */       
/* 301 */       Iterator<Map.Entry<String, ModelRenderer>> ite = list.entrySet().iterator();
/* 302 */       while (ite.hasNext()) {
/*     */         
/* 304 */         Map.Entry<String, ModelRenderer> e = ite.next();
/* 305 */         if (e.getValue() == child)
/*     */         {
/* 307 */           ite.remove();
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 312 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ArrayList<ModelRenderer> getMultiModelCubes(ArrayList<ModelBase> parent) {
/* 317 */     ArrayList<ModelRenderer> list = new ArrayList<ModelRenderer>();
/* 318 */     for (ModelBase base : parent)
/*     */     {
/* 320 */       list.addAll(getModelCubes(base));
/*     */     }
/* 322 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ArrayList<ModelRenderer> getChildren(ModelRenderer parent, boolean recursive, int depth) {
/* 327 */     ArrayList<ModelRenderer> list = new ArrayList<ModelRenderer>();
/* 328 */     if (parent.field_78805_m != null && depth < 20)
/*     */     {
/* 330 */       for (int i = 0; i < parent.field_78805_m.size(); i++) {
/*     */         
/* 332 */         ModelRenderer child = parent.field_78805_m.get(i);
/* 333 */         if (recursive) {
/*     */           
/* 335 */           ArrayList<ModelRenderer> children = getChildren(child, recursive, depth + 1);
/* 336 */           for (ModelRenderer child1 : children) {
/*     */             
/* 338 */             if (!list.contains(child1))
/*     */             {
/* 340 */               list.add(child1);
/*     */             }
/*     */           } 
/*     */         } 
/* 344 */         if (!list.contains(child))
/*     */         {
/* 346 */           list.add(child);
/*     */         }
/*     */       } 
/*     */     }
/* 350 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ModelBase getPossibleModel(Render rend) {
/* 355 */     ArrayList<ArrayList<ModelBase>> models = new ArrayList<ArrayList<ModelBase>>();
/*     */     
/* 357 */     if (rend != null) {
/*     */       
/*     */       try {
/*     */         
/* 361 */         Class<?> clz = rend.getClass();
/* 362 */         while (clz != Render.class)
/*     */         {
/* 364 */           ArrayList<ModelBase> arrayList = new ArrayList<ModelBase>();
/*     */           
/* 366 */           Field[] fields = clz.getDeclaredFields();
/* 367 */           for (Field f : fields) {
/*     */             
/* 369 */             f.setAccessible(true);
/* 370 */             if (ModelBase.class.isAssignableFrom(f.getType())) {
/*     */               
/* 372 */               ModelBase base = (ModelBase)f.get(rend);
/* 373 */               if (base != null)
/*     */               {
/* 375 */                 arrayList.add(base);
/*     */               }
/*     */             }
/* 378 */             else if (ModelBase[].class.isAssignableFrom(f.getType())) {
/*     */               
/* 380 */               ModelBase[] modelBases = (ModelBase[])f.get(rend);
/* 381 */               if (modelBases != null)
/*     */               {
/* 383 */                 for (ModelBase base : modelBases)
/*     */                 {
/* 385 */                   arrayList.add(base);
/*     */                 }
/*     */               }
/*     */             } 
/*     */           } 
/*     */           
/* 391 */           models.add(arrayList);
/*     */           
/* 393 */           if (clz == RendererLivingEntity.class) {
/*     */             
/* 395 */             ArrayList<ModelBase> topPriority = new ArrayList<ModelBase>();
/* 396 */             for (Field f : fields) {
/*     */               
/* 398 */               f.setAccessible(true);
/* 399 */               if (ModelBase.class.isAssignableFrom(f.getType()) && (f.getName().equalsIgnoreCase("mainModel") || f.getName().equalsIgnoreCase("field_77045_g"))) {
/*     */                 
/* 401 */                 ModelBase base = (ModelBase)f.get(rend);
/* 402 */                 if (base != null)
/*     */                 {
/* 404 */                   topPriority.add(base);
/*     */                 }
/*     */               } 
/*     */             } 
/* 408 */             models.add(topPriority);
/*     */           } 
/* 410 */           clz = clz.getSuperclass();
/*     */         }
/*     */       
/* 413 */       } catch (Exception e) {
/*     */         
/* 415 */         throw new ReflectionHelper.UnableToAccessFieldException(new String[0], e);
/*     */       } 
/*     */     }
/*     */     
/* 419 */     ModelBase base1 = null;
/* 420 */     int priorityLevel = -1;
/* 421 */     int size = -1;
/*     */     
/* 423 */     int currentPriority = 0;
/*     */     
/* 425 */     for (ArrayList<ModelBase> modelList : models) {
/*     */       
/* 427 */       for (ModelBase base : modelList) {
/*     */         
/* 429 */         ArrayList<ModelRenderer> mrs = getModelCubes(base);
/* 430 */         if (mrs.size() > size || (mrs.size() == size && currentPriority > priorityLevel)) {
/*     */           
/* 432 */           size = mrs.size();
/* 433 */           base1 = base;
/* 434 */           priorityLevel = currentPriority;
/*     */         } 
/*     */       } 
/* 437 */       currentPriority++;
/*     */     } 
/*     */     
/* 440 */     return base1;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ArrayList<ModelRenderer> getModelCubes(EntityLivingBase living) {
/* 445 */     ArrayList<ModelRenderer> map = classToModelRendererMap.get(living.getClass());
/* 446 */     if (map == null) {
/*     */       
/* 448 */       map = getModelCubes(getPossibleModel(RenderManager.field_78727_a.func_78713_a((Entity)living)));
/* 449 */       classToModelRendererMap.put(living.getClass(), map);
/*     */     } 
/* 451 */     return map;
/*     */   }
/*     */   
/* 454 */   public static HashMap<Class<? extends EntityLivingBase>, ArrayList<ModelRenderer>> classToModelRendererMap = new HashMap<Class<? extends EntityLivingBase>, ArrayList<ModelRenderer>>();
/*     */ }


/* Location:              C:\DADOS\Jogos\Minecraft Legends - Copia\mods\iChunUtil-4.2.2.jar!\ichun\client\model\ModelHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */