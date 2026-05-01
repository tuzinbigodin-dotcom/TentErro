/*     */ package ichun.common.core;
/*     */ import com.google.common.collect.Iterables;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import com.mojang.authlib.ProfileLookupCallback;
/*     */ import com.mojang.authlib.properties.Property;
/*     */ import ichun.common.core.util.ObfHelper;
/*     */ import ichun.common.iChunUtil;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.entity.Render;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.StringUtils;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityHelperBase {
/*  30 */   public static final String[] volunteers = new String[] { "0b7509f0-2458-4160-9ce1-2772b9a45ac2", "45438340-ced5-4c4f-82b4-ef1496f0319e", "6993a191-3741-41ec-9f76-ff89025b38b6", "b47935c3-757d-4a3a-9f03-5fe55efb9464", "aeb4e423-0d5d-491f-8d2d-d6b3fe6679a6", "4e532a7f-ad64-4d43-9ee1-a345d0a5eed1", "a32d6e2c-7936-43b1-aa20-396c7a298314", "5e0acde3-e629-4acc-b0c7-f30cb6783b8d", "a5ea0925-0afa-48eb-9512-58027bda77d1", "88d02c0e-c895-40d0-bb1b-16b292b277d9", "f7aea342-2ee0-4980-9358-6b59ec935b0f", "6e8be0ba-e4bb-46af-aea8-2c1f5eec5bc2", "ee64800c-2dd5-468e-9cda-914f07592c4b", "ec167010-a390-42a6-a3a9-ab3ca4996508", "5d40840f-4a08-4559-8d0d-aac28dfbbd26", "fd303402-d627-4de0-8a02-eb8c7fd2acb6", "28b6d151-c2df-46b8-b9e4-2bf453fde455", "9f662928-1078-4fcd-b728-4798db6edf94", "a4e85c95-b704-4637-8559-7f93a7ec5cc6", "78b2adca-ee34-47b6-ac7a-21c239c44815", "6b384367-de1d-4764-aa64-367d98e22c2b", "bfa9edae-6127-4841-bbc1-6fc9347e1273" };
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
/*  54 */   public static final float[] RARITY = new float[] { 0.01F, 0.03F, 0.075F, 0.17F, 0.275F, 0.235F, 0.115F, 0.05F, 0.03F, 10.0F };
/*  55 */   public static final Random infectionRand = new Random();
/*     */   
/*     */   public static int getImmunityLevel(String uuid) {
/*  58 */     uuid = uuid.replaceAll("-", "");
/*  59 */     for (String s : volunteers) {
/*     */       
/*  61 */       if (s.replaceAll("-", "").equalsIgnoreCase(uuid))
/*     */       {
/*  63 */         return 0;
/*     */       }
/*     */     } 
/*     */     
/*  67 */     infectionRand.setSeed(Math.abs(uuid.hashCode()));
/*     */     
/*  69 */     float immunity = infectionRand.nextFloat();
/*     */     
/*  71 */     int level = -1;
/*  72 */     int i = 0;
/*     */     
/*  74 */     while (immunity > 0.0F) {
/*     */       
/*  76 */       level++;
/*  77 */       immunity -= RARITY[i];
/*  78 */       i++;
/*     */     } 
/*     */     
/*  81 */     return level;
/*     */   }
/*     */   
/*  84 */   private static final UUID uuidExample = UUID.fromString("DEADBEEF-DEAD-BEEF-DEAD-DEADBEEFD00D");
/*     */   private static GameProfileRepository profileRepo;
/*  86 */   private static HashMap<String, GameProfile> nameToPartialProfileMap = new HashMap<String, GameProfile>();
/*  87 */   private static HashMap<String, GameProfile> nameToFullProfileMap = new HashMap<String, GameProfile>();
/*     */ 
/*     */   
/*     */   public static GameProfile getFullGameProfileFromName(String name) {
/*  91 */     if (nameToFullProfileMap.containsKey(name))
/*     */     {
/*  93 */       return nameToFullProfileMap.get(name);
/*     */     }
/*     */     
/*  96 */     GameProfile gp = new GameProfile(null, name);
/*  97 */     if (!StringUtils.func_151246_b(gp.getName()))
/*     */     {
/*  99 */       if (!gp.isComplete() || !gp.getProperties().containsKey("textures")) {
/*     */         
/* 101 */         GameProfile gameprofile = getPartialGameProfileFromName(gp.getName());
/*     */         
/* 103 */         if (gameprofile != null) {
/*     */           
/* 105 */           Property property = (Property)Iterables.getFirst(gameprofile.getProperties().get("textures"), null);
/*     */           
/* 107 */           if (property == null) {
/*     */             
/* 109 */             gameprofile = iChunUtil.proxy.getSessionService().fillProfileProperties(gameprofile, true);
/*     */             
/* 111 */             nameToFullProfileMap.put(gameprofile.getName(), gameprofile);
/*     */           } 
/* 113 */           return gameprofile;
/*     */         } 
/*     */       } 
/*     */     }
/* 117 */     return new GameProfile(uuidExample, name);
/*     */   }
/*     */ 
/*     */   
/*     */   public static GameProfile getSimpleGameProfileFromName(String name) {
/* 122 */     return new GameProfile(uuidExample, name);
/*     */   }
/*     */ 
/*     */   
/*     */   public static GameProfile getPartialGameProfileFromName(String name) {
/* 127 */     if (nameToFullProfileMap.containsKey(name))
/*     */     {
/* 129 */       return nameToFullProfileMap.get(name);
/*     */     }
/* 131 */     if (nameToPartialProfileMap.containsKey(name))
/*     */     {
/* 133 */       return nameToPartialProfileMap.get(name);
/*     */     }
/* 135 */     final GameProfile[] agameprofile = new GameProfile[1];
/* 136 */     ProfileLookupCallback profilelookupcallback = new ProfileLookupCallback()
/*     */       {
/*     */         public void onProfileLookupSucceeded(GameProfile p_onProfileLookupSucceeded_1_)
/*     */         {
/* 140 */           agameprofile[0] = p_onProfileLookupSucceeded_1_;
/*     */         }
/*     */         
/*     */         public void onProfileLookupFailed(GameProfile p_onProfileLookupFailed_1_, Exception p_onProfileLookupFailed_2_) {
/* 144 */           agameprofile[0] = null;
/*     */         }
/*     */       };
/* 147 */     if (profileRepo == null)
/*     */     {
/* 149 */       profileRepo = iChunUtil.proxy.createProfileRepo();
/*     */     }
/* 151 */     profileRepo.findProfilesByNames(new String[] { name }, Agent.MINECRAFT, profilelookupcallback);
/*     */     
/* 153 */     if (agameprofile[0] == null) {
/*     */       
/* 155 */       UUID uuid = EntityPlayer.func_146094_a(new GameProfile((UUID)null, name));
/* 156 */       GameProfile gameprofile = new GameProfile(uuid, name);
/* 157 */       profilelookupcallback.onProfileLookupSucceeded(gameprofile);
/*     */     }
/*     */     else {
/*     */       
/* 161 */       nameToPartialProfileMap.put(agameprofile[0].getName(), agameprofile[0]);
/*     */     } 
/*     */     
/* 164 */     return agameprofile[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public static void getUUIDFromUsernames(String... names) {
/* 169 */     System.out.println("UUIDs from Names");
/* 170 */     ArrayList<String> namesList = new ArrayList<String>();
/* 171 */     ArrayList<String> uuidList = new ArrayList<String>();
/* 172 */     for (String s : names) {
/*     */       
/* 174 */       GameProfile gp = getPartialGameProfileFromName(s);
/* 175 */       namesList.add(gp.getName());
/* 176 */       uuidList.add("  \"" + gp.getId().toString() + "\"");
/*     */     }  int i;
/* 178 */     for (i = 0; i < namesList.size(); i++)
/*     */     {
/* 180 */       System.out.println(namesList.get(i));
/*     */     }
/* 182 */     for (i = 0; i < uuidList.size(); i++)
/*     */     {
/* 184 */       System.out.println(uuidList.get(i));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static MovingObjectPosition getEntityLook(EntityLivingBase ent, double d) {
/* 190 */     return getEntityLook(ent, d, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public static MovingObjectPosition getEntityLook(EntityLivingBase ent, double d, boolean ignoreEntities) {
/* 195 */     return getEntityLook(ent, d, ignoreEntities, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public static MovingObjectPosition getEntityLook(EntityLivingBase ent, double d, boolean ignoreEntities, float renderTick) {
/* 200 */     if (ent == null)
/*     */     {
/* 202 */       return null;
/*     */     }
/*     */     
/* 205 */     double d1 = d;
/* 206 */     MovingObjectPosition mop = rayTrace(ent, d, renderTick);
/* 207 */     Vec3 vec3d = getPosition((Entity)ent, renderTick);
/*     */     
/* 209 */     if (mop != null)
/*     */     {
/* 211 */       d1 = mop.field_72307_f.func_72438_d(vec3d);
/*     */     }
/*     */     
/* 214 */     double dd2 = d;
/*     */     
/* 216 */     if (d1 > dd2)
/*     */     {
/* 218 */       d1 = dd2;
/*     */     }
/*     */     
/* 221 */     d = d1;
/* 222 */     Vec3 vec3d1 = ent.func_70676_i(renderTick);
/* 223 */     Vec3 vec3d2 = vec3d.func_72441_c(vec3d1.field_72450_a * d, vec3d1.field_72448_b * d, vec3d1.field_72449_c * d);
/*     */     
/* 225 */     if (!ignoreEntities) {
/*     */       
/* 227 */       Entity entity1 = null;
/* 228 */       float f1 = 1.0F;
/* 229 */       List<Entity> list = ent.field_70170_p.func_72839_b((Entity)ent, ent.field_70121_D.func_72321_a(vec3d1.field_72450_a * d, vec3d1.field_72448_b * d, vec3d1.field_72449_c * d).func_72314_b(f1, f1, f1));
/* 230 */       double d2 = 0.0D;
/*     */       
/* 232 */       for (int i = 0; i < list.size(); i++) {
/*     */         
/* 234 */         Entity entity = list.get(i);
/*     */         
/* 236 */         if (entity.func_70067_L()) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 241 */           float f2 = entity.func_70111_Y();
/* 242 */           AxisAlignedBB axisalignedbb = entity.field_70121_D.func_72314_b(f2, f2, f2);
/* 243 */           MovingObjectPosition movingobjectposition = axisalignedbb.func_72327_a(vec3d, vec3d2);
/*     */           
/* 245 */           if (axisalignedbb.func_72318_a(vec3d)) {
/*     */             
/* 247 */             if (0.0D < d2 || d2 == 0.0D)
/*     */             {
/* 249 */               entity1 = entity;
/* 250 */               d2 = 0.0D;
/*     */             
/*     */             }
/*     */ 
/*     */           
/*     */           }
/* 256 */           else if (movingobjectposition != null) {
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 261 */             double d3 = vec3d.func_72438_d(movingobjectposition.field_72307_f);
/*     */             
/* 263 */             if (d3 < d2 || d2 == 0.0D) {
/*     */               
/* 265 */               entity1 = entity;
/* 266 */               d2 = d3;
/*     */             } 
/*     */           } 
/*     */         } 
/* 270 */       }  if (entity1 != null)
/*     */       {
/* 272 */         mop = new MovingObjectPosition(entity1);
/*     */       }
/*     */     } 
/*     */     
/* 276 */     return mop;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Vec3 getPosition(Entity ent, float par1) {
/* 281 */     return getPosition(ent, par1, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Vec3 getPosition(Entity ent, float par1, boolean midPoint) {
/* 286 */     if (par1 == 1.0F)
/*     */     {
/* 288 */       return Vec3.func_72443_a(ent.field_70165_t, midPoint ? ((ent.field_70121_D.field_72338_b + ent.field_70121_D.field_72337_e) / 2.0D) : (ent.field_70163_u + (ent.field_70170_p.field_72995_K ? 0.0D : (ent.func_70047_e() - 0.09D))), ent.field_70161_v);
/*     */     }
/*     */ 
/*     */     
/* 292 */     double var2 = ent.field_70169_q + (ent.field_70165_t - ent.field_70169_q) * par1;
/* 293 */     double var4 = midPoint ? ((ent.field_70121_D.field_72338_b + ent.field_70121_D.field_72337_e) / 2.0D) : (ent.field_70167_r + (ent.field_70170_p.field_72995_K ? 0.0D : (ent.func_70047_e() - 0.09D)) + (ent.field_70163_u - ent.field_70167_r) * par1);
/* 294 */     double var6 = ent.field_70166_s + (ent.field_70161_v - ent.field_70166_s) * par1;
/* 295 */     return Vec3.func_72443_a(var2, var4, var6);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static MovingObjectPosition rayTrace(EntityLivingBase ent, double distance, float par3) {
/* 301 */     return rayTrace(ent, distance, par3, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public static MovingObjectPosition rayTrace(EntityLivingBase ent, double distance, float par3, boolean midPoint) {
/* 306 */     Vec3 var4 = getPosition((Entity)ent, par3, midPoint);
/* 307 */     Vec3 var5 = ent.func_70676_i(par3);
/* 308 */     Vec3 var6 = var4.func_72441_c(var5.field_72450_a * distance, var5.field_72448_b * distance, var5.field_72449_c * distance);
/* 309 */     return ent.field_70170_p.func_147447_a(var4, var6, false, false, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public static MovingObjectPosition rayTrace(World world, Vec3 vec3d, Vec3 vec3d1, boolean flag, boolean flag1, boolean goThroughTransparentBlocks) {
/* 314 */     return rayTrace(world, vec3d, vec3d1, flag, flag1, goThroughTransparentBlocks, 200);
/*     */   }
/*     */ 
/*     */   
/*     */   public static MovingObjectPosition rayTrace(World world, Vec3 vec3d, Vec3 vec3d1, boolean flag, boolean flag1, boolean goThroughTransparentBlocks, int distance) {
/* 319 */     if (Double.isNaN(vec3d.field_72450_a) || Double.isNaN(vec3d.field_72448_b) || Double.isNaN(vec3d.field_72449_c))
/*     */     {
/* 321 */       return null;
/*     */     }
/*     */     
/* 324 */     if (Double.isNaN(vec3d1.field_72450_a) || Double.isNaN(vec3d1.field_72448_b) || Double.isNaN(vec3d1.field_72449_c))
/*     */     {
/* 326 */       return null;
/*     */     }
/*     */     
/* 329 */     int i = MathHelper.func_76128_c(vec3d1.field_72450_a);
/* 330 */     int j = MathHelper.func_76128_c(vec3d1.field_72448_b);
/* 331 */     int k = MathHelper.func_76128_c(vec3d1.field_72449_c);
/* 332 */     int l = MathHelper.func_76128_c(vec3d.field_72450_a);
/* 333 */     int i1 = MathHelper.func_76128_c(vec3d.field_72448_b);
/* 334 */     int j1 = MathHelper.func_76128_c(vec3d.field_72449_c);
/* 335 */     int i2 = world.func_72805_g(l, i1, j1);
/* 336 */     Block block = world.func_147439_a(l, i1, j1);
/*     */     
/* 338 */     if ((!flag1 || block.func_149668_a(world, l, i1, j1) != null) && block.func_149678_a(i2, flag)) {
/*     */       
/* 340 */       MovingObjectPosition movingobjectposition = block.func_149731_a(world, l, i1, j1, vec3d, vec3d1);
/*     */       
/* 342 */       if (movingobjectposition != null)
/*     */       {
/* 344 */         return movingobjectposition;
/*     */       }
/*     */     } 
/*     */     
/* 348 */     for (int l1 = distance; l1-- >= 0; ) {
/*     */       
/* 350 */       if (Double.isNaN(vec3d.field_72450_a) || Double.isNaN(vec3d.field_72448_b) || Double.isNaN(vec3d.field_72449_c))
/*     */       {
/* 352 */         return null;
/*     */       }
/*     */       
/* 355 */       if (l == i && i1 == j && j1 == k)
/*     */       {
/* 357 */         return null;
/*     */       }
/*     */       
/* 360 */       boolean flag2 = true;
/* 361 */       boolean flag3 = true;
/* 362 */       boolean flag4 = true;
/* 363 */       double d = 999.0D;
/* 364 */       double d1 = 999.0D;
/* 365 */       double d2 = 999.0D;
/*     */       
/* 367 */       if (i > l) {
/*     */         
/* 369 */         d = l + 1.0D;
/*     */       }
/* 371 */       else if (i < l) {
/*     */         
/* 373 */         d = l + 0.0D;
/*     */       }
/*     */       else {
/*     */         
/* 377 */         flag2 = false;
/*     */       } 
/*     */       
/* 380 */       if (j > i1) {
/*     */         
/* 382 */         d1 = i1 + 1.0D;
/*     */       }
/* 384 */       else if (j < i1) {
/*     */         
/* 386 */         d1 = i1 + 0.0D;
/*     */       }
/*     */       else {
/*     */         
/* 390 */         flag3 = false;
/*     */       } 
/*     */       
/* 393 */       if (k > j1) {
/*     */         
/* 395 */         d2 = j1 + 1.0D;
/*     */       }
/* 397 */       else if (k < j1) {
/*     */         
/* 399 */         d2 = j1 + 0.0D;
/*     */       }
/*     */       else {
/*     */         
/* 403 */         flag4 = false;
/*     */       } 
/*     */       
/* 406 */       double d3 = 999.0D;
/* 407 */       double d4 = 999.0D;
/* 408 */       double d5 = 999.0D;
/* 409 */       double d6 = vec3d1.field_72450_a - vec3d.field_72450_a;
/* 410 */       double d7 = vec3d1.field_72448_b - vec3d.field_72448_b;
/* 411 */       double d8 = vec3d1.field_72449_c - vec3d.field_72449_c;
/*     */       
/* 413 */       if (flag2)
/*     */       {
/* 415 */         d3 = (d - vec3d.field_72450_a) / d6;
/*     */       }
/*     */       
/* 418 */       if (flag3)
/*     */       {
/* 420 */         d4 = (d1 - vec3d.field_72448_b) / d7;
/*     */       }
/*     */       
/* 423 */       if (flag4)
/*     */       {
/* 425 */         d5 = (d2 - vec3d.field_72449_c) / d8;
/*     */       }
/*     */       
/* 428 */       byte byte0 = 0;
/*     */       
/* 430 */       if (d3 < d4 && d3 < d5) {
/*     */         
/* 432 */         if (i > l) {
/*     */           
/* 434 */           byte0 = 4;
/*     */         }
/*     */         else {
/*     */           
/* 438 */           byte0 = 5;
/*     */         } 
/*     */         
/* 441 */         vec3d.field_72450_a = d;
/* 442 */         vec3d.field_72448_b += d7 * d3;
/* 443 */         vec3d.field_72449_c += d8 * d3;
/*     */       }
/* 445 */       else if (d4 < d5) {
/*     */         
/* 447 */         if (j > i1) {
/*     */           
/* 449 */           byte0 = 0;
/*     */         }
/*     */         else {
/*     */           
/* 453 */           byte0 = 1;
/*     */         } 
/*     */         
/* 456 */         vec3d.field_72450_a += d6 * d4;
/* 457 */         vec3d.field_72448_b = d1;
/* 458 */         vec3d.field_72449_c += d8 * d4;
/*     */       }
/*     */       else {
/*     */         
/* 462 */         if (k > j1) {
/*     */           
/* 464 */           byte0 = 2;
/*     */         }
/*     */         else {
/*     */           
/* 468 */           byte0 = 3;
/*     */         } 
/*     */         
/* 471 */         vec3d.field_72450_a += d6 * d5;
/* 472 */         vec3d.field_72448_b += d7 * d5;
/* 473 */         vec3d.field_72449_c = d2;
/*     */       } 
/*     */       
/* 476 */       Vec3 vec3d2 = Vec3.func_72443_a(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c);
/* 477 */       l = (int)(vec3d2.field_72450_a = MathHelper.func_76128_c(vec3d.field_72450_a));
/*     */       
/* 479 */       if (byte0 == 5) {
/*     */         
/* 481 */         l--;
/* 482 */         vec3d2.field_72450_a++;
/*     */       } 
/*     */       
/* 485 */       i1 = (int)(vec3d2.field_72448_b = MathHelper.func_76128_c(vec3d.field_72448_b));
/*     */       
/* 487 */       if (byte0 == 1) {
/*     */         
/* 489 */         i1--;
/* 490 */         vec3d2.field_72448_b++;
/*     */       } 
/*     */       
/* 493 */       j1 = (int)(vec3d2.field_72449_c = MathHelper.func_76128_c(vec3d.field_72449_c));
/*     */       
/* 495 */       if (byte0 == 3) {
/*     */         
/* 497 */         j1--;
/* 498 */         vec3d2.field_72449_c++;
/*     */       } 
/*     */       
/* 501 */       Block block1 = world.func_147439_a(l, i1, j1);
/*     */       
/* 503 */       if (goThroughTransparentBlocks && isTransparent(block1)) {
/*     */         continue;
/*     */       }
/*     */ 
/*     */       
/* 508 */       int k2 = world.func_72805_g(l, i1, j1);
/*     */       
/* 510 */       if ((!flag1 || block1.func_149668_a(world, l, i1, j1) != null) && block1.func_149678_a(k2, flag)) {
/*     */         
/* 512 */         MovingObjectPosition movingobjectposition1 = block1.func_149731_a(world, l, i1, j1, vec3d, vec3d1);
/*     */         
/* 514 */         if (movingobjectposition1 != null)
/*     */         {
/* 516 */           return movingobjectposition1;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 521 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean hasFuel(InventoryPlayer inventory, Item item, int damage, int amount) {
/* 526 */     if (amount <= 0)
/*     */     {
/* 528 */       return true;
/*     */     }
/*     */     
/* 531 */     int amountFound = 0;
/*     */     
/* 533 */     for (int var3 = 0; var3 < inventory.field_70462_a.length; var3++) {
/*     */       
/* 535 */       if (inventory.field_70462_a[var3] != null && inventory.field_70462_a[var3].func_77973_b() == item && inventory.field_70462_a[var3].func_77960_j() == damage) {
/*     */         
/* 537 */         amountFound += (inventory.field_70462_a[var3]).field_77994_a;
/*     */         
/* 539 */         if (amountFound >= amount)
/*     */         {
/* 541 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 546 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isTransparent(Block block) {
/* 551 */     return (block.func_149717_k() != 255);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isLookingAtMoon(World world, EntityLivingBase ent, float renderTick, boolean goThroughTransparentBlocks) {
/* 556 */     if (ent.field_71093_bK == -1 || ent.field_71093_bK == 1)
/*     */     {
/* 558 */       return false;
/*     */     }
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
/* 576 */     double de = 2.71828183D;
/* 577 */     float f = world.func_72826_c(1.0F);
/*     */     
/* 579 */     if (f < 0.26D || f > 0.74D)
/*     */     {
/* 581 */       return false;
/*     */     }
/*     */     
/* 584 */     float f2 = (f > 0.5F) ? (f - 0.5F) : (0.5F - f);
/* 585 */     float f3 = (ent.field_70177_z > 0.0F) ? 270.0F : -90.0F;
/* 586 */     f3 = (f > 0.5F) ? ((ent.field_70177_z > 0.0F) ? 90.0F : -270.0F) : f3;
/* 587 */     f = (f > 0.5F) ? (1.0F - f) : f;
/*     */     
/* 589 */     if (f <= 0.475D) {
/*     */       
/* 591 */       de = 2.71828183D;
/*     */     }
/* 593 */     else if (f <= 0.4875D) {
/*     */       
/* 595 */       de = 3.88377D;
/*     */     }
/* 597 */     else if (f <= 0.4935D) {
/*     */       
/* 599 */       de = 4.91616D;
/*     */     }
/* 601 */     else if (f <= 0.4965D) {
/*     */       
/* 603 */       de = 5.40624D;
/*     */     }
/* 605 */     else if (f <= 0.5D) {
/*     */       
/* 607 */       de = 9.8D;
/*     */     } 
/*     */ 
/*     */     
/* 611 */     boolean yawCheck = ((ent.field_70177_z % 360.0F) <= Math.pow(de, 4.92574D * world.func_72826_c(1.0F)) + f3 && (ent.field_70177_z % 360.0F) >= -Math.pow(de, 4.92574D * world.func_72826_c(1.0F)) + f3);
/* 612 */     float ff = world.func_72826_c(1.0F);
/* 613 */     ff = (ff > 0.5F) ? (1.0F - ff) : ff;
/* 614 */     ff -= 0.26F;
/* 615 */     ff = ff / 0.26F * -94.0F - 4.0F;
/*     */     
/* 617 */     boolean pitchCheck = (ent.field_70125_A <= ff + 2.5F && ent.field_70125_A >= ff - 2.5F);
/* 618 */     Vec3 vec3d = getPosition((Entity)ent, renderTick);
/* 619 */     Vec3 vec3d1 = ent.func_70676_i(renderTick);
/* 620 */     Vec3 vec3d2 = vec3d.func_72441_c(vec3d1.field_72450_a * 500.0D, vec3d1.field_72448_b * 500.0D, vec3d1.field_72449_c * 500.0D);
/* 621 */     boolean mopCheck = (rayTrace(ent.field_70170_p, vec3d, vec3d2, true, false, goThroughTransparentBlocks, 500) == null);
/* 622 */     return (yawCheck && pitchCheck && mopCheck);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean consumeInventoryItem(InventoryPlayer inventory, Item item, int damage, int amount) {
/* 627 */     if (amount <= 0)
/*     */     {
/* 629 */       return true;
/*     */     }
/*     */     
/* 632 */     int amountFound = 0;
/*     */     int var3;
/* 634 */     for (var3 = 0; var3 < inventory.field_70462_a.length; var3++) {
/*     */       
/* 636 */       if (inventory.field_70462_a[var3] != null && inventory.field_70462_a[var3].func_77973_b() == item && inventory.field_70462_a[var3].func_77960_j() == damage) {
/*     */         
/* 638 */         amountFound += (inventory.field_70462_a[var3]).field_77994_a;
/*     */         
/* 640 */         if (amountFound >= amount) {
/*     */           break;
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 647 */     if (amountFound >= amount)
/*     */     {
/* 649 */       for (var3 = 0; var3 < inventory.field_70462_a.length; var3++) {
/*     */         
/* 651 */         if (inventory.field_70462_a[var3] != null && inventory.field_70462_a[var3].func_77973_b() == item && inventory.field_70462_a[var3].func_77960_j() == damage)
/*     */         {
/* 653 */           while (amount > 0 && inventory.field_70462_a[var3] != null && (inventory.field_70462_a[var3]).field_77994_a > 0) {
/*     */             
/* 655 */             amount--;
/* 656 */             (inventory.field_70462_a[var3]).field_77994_a--;
/*     */             
/* 658 */             if ((inventory.field_70462_a[var3]).field_77994_a <= 0)
/*     */             {
/* 660 */               inventory.field_70462_a[var3] = null;
/*     */             }
/*     */             
/* 663 */             if (amount <= 0)
/*     */             {
/* 665 */               return true;
/*     */             }
/*     */           } 
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 672 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getDeathSound(Class<EntityLivingBase> clz, EntityLivingBase ent) {
/*     */     try {
/* 679 */       Method m = clz.getDeclaredMethod(ObfHelper.obfuscation ? "func_70673_aS" : "getDeathSound", new Class[0]);
/* 680 */       m.setAccessible(true);
/* 681 */       return (String)m.invoke(ent, new Object[0]);
/*     */     }
/* 683 */     catch (NoSuchMethodException e) {
/*     */       
/* 685 */       if (clz != EntityLivingBase.class)
/*     */       {
/* 687 */         return getDeathSound(clz.getSuperclass(), ent);
/*     */       }
/*     */     }
/* 690 */     catch (Exception e) {
/*     */       
/* 692 */       e.printStackTrace();
/*     */     } 
/* 694 */     return "game.neutral.die";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getHurtSound(Class<EntityLivingBase> clz, EntityLivingBase ent) {
/*     */     try {
/* 701 */       Method m = clz.getDeclaredMethod(ObfHelper.obfuscation ? "func_70621_aR" : "getHurtSound", new Class[0]);
/* 702 */       m.setAccessible(true);
/* 703 */       return (String)m.invoke(ent, new Object[0]);
/*     */     }
/* 705 */     catch (NoSuchMethodException e) {
/*     */       
/* 707 */       if (clz != EntityLivingBase.class)
/*     */       {
/* 709 */         return getHurtSound(clz.getSuperclass(), ent);
/*     */       }
/*     */     }
/* 712 */     catch (Exception e) {
/*     */       
/* 714 */       e.printStackTrace();
/*     */     } 
/* 716 */     return "game.neutral.hurt";
/*     */   }
/*     */ 
/*     */   
/*     */   public static float updateRotation(float oriRot, float intendedRot, float maxChange) {
/* 721 */     float var4 = MathHelper.func_76142_g(intendedRot - oriRot);
/*     */     
/* 723 */     if (var4 > maxChange)
/*     */     {
/* 725 */       var4 = maxChange;
/*     */     }
/*     */     
/* 728 */     if (var4 < -maxChange)
/*     */     {
/* 730 */       var4 = -maxChange;
/*     */     }
/*     */     
/* 733 */     return oriRot + var4;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static float interpolateRotation(float prevRotation, float nextRotation, float partialTick) {
/*     */     float f3;
/* 740 */     for (f3 = nextRotation - prevRotation; f3 < -180.0F; f3 += 360.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 745 */     while (f3 >= 180.0F)
/*     */     {
/* 747 */       f3 -= 360.0F;
/*     */     }
/*     */     
/* 750 */     return prevRotation + partialTick * f3;
/*     */   }
/*     */ 
/*     */   
/*     */   public static float interpolateValues(float prevVal, float nextVal, float partialTick) {
/* 755 */     return prevVal + partialTick * (nextVal - prevVal);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void faceEntity(Entity facer, Entity faced, float maxYaw, float maxPitch) {
/* 760 */     double d2, d0 = faced.field_70165_t - facer.field_70165_t;
/* 761 */     double d1 = faced.field_70161_v - facer.field_70161_v;
/*     */ 
/*     */     
/* 764 */     if (faced instanceof EntityLivingBase) {
/*     */       
/* 766 */       EntityLivingBase entitylivingbase = (EntityLivingBase)faced;
/* 767 */       d2 = entitylivingbase.field_70163_u + entitylivingbase.func_70047_e() - facer.field_70163_u + facer.func_70047_e();
/*     */     }
/*     */     else {
/*     */       
/* 771 */       d2 = (faced.field_70121_D.field_72338_b + faced.field_70121_D.field_72337_e) / 2.0D - facer.field_70163_u + facer.func_70047_e();
/*     */     } 
/*     */     
/* 774 */     double d3 = MathHelper.func_76133_a(d0 * d0 + d1 * d1);
/* 775 */     float f2 = (float)(Math.atan2(d1, d0) * 180.0D / Math.PI) - 90.0F;
/* 776 */     float f3 = (float)-(Math.atan2(d2, d3) * 180.0D / Math.PI);
/* 777 */     facer.field_70125_A = updateRotation(facer.field_70125_A, f3, maxPitch);
/* 778 */     facer.field_70177_z = updateRotation(facer.field_70177_z, f2, maxYaw);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setVelocity(Entity entity, double d, double d1, double d2) {
/* 783 */     entity.field_70159_w = d;
/* 784 */     entity.field_70181_x = d1;
/* 785 */     entity.field_70179_y = d2;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean destroyBlocksInAABB(Entity ent, AxisAlignedBB aabb) {
/* 790 */     int i = MathHelper.func_76128_c(aabb.field_72340_a);
/* 791 */     int j = MathHelper.func_76128_c(aabb.field_72338_b);
/* 792 */     int k = MathHelper.func_76128_c(aabb.field_72339_c);
/* 793 */     int l = MathHelper.func_76128_c(aabb.field_72336_d);
/* 794 */     int i1 = MathHelper.func_76128_c(aabb.field_72337_e);
/* 795 */     int j1 = MathHelper.func_76128_c(aabb.field_72334_f);
/* 796 */     boolean flag = false;
/* 797 */     boolean flag1 = false;
/*     */     
/* 799 */     for (int k1 = i; k1 <= l; k1++) {
/*     */       
/* 801 */       for (int l1 = j; l1 <= i1; l1++) {
/*     */         
/* 803 */         for (int i2 = k; i2 <= j1; i2++) {
/*     */           
/* 805 */           Block block = ent.field_70170_p.func_147439_a(k1, l1, i2);
/*     */           
/* 807 */           if (block != null)
/*     */           {
/* 809 */             if (block.func_149712_f(ent.field_70170_p, k1, l1, i2) >= 0.0F && block.canEntityDestroy((IBlockAccess)ent.field_70170_p, k1, l1, i2, ent) && ent.field_70170_p.func_82736_K().func_82766_b("mobGriefing")) {
/*     */               
/* 811 */               flag1 = ent.field_70170_p.field_72995_K ? true : ((ent.field_70170_p.func_147468_f(k1, l1, i2) || flag1));
/*     */             }
/*     */             else {
/*     */               
/* 815 */               flag = true;
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 822 */     if (flag1) {
/*     */       
/* 824 */       double d0 = aabb.field_72340_a + (aabb.field_72336_d - aabb.field_72340_a) * ent.field_70170_p.field_73012_v.nextFloat();
/* 825 */       double d1 = aabb.field_72338_b + (aabb.field_72337_e - aabb.field_72338_b) * ent.field_70170_p.field_73012_v.nextFloat();
/* 826 */       double d2 = aabb.field_72339_c + (aabb.field_72334_f - aabb.field_72339_c) * ent.field_70170_p.field_73012_v.nextFloat();
/* 827 */       ent.field_70170_p.func_72869_a("largeexplode", d0, d1, d2, 0.0D, 0.0D, 0.0D);
/*     */     } 
/*     */     
/* 830 */     return flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void addPosition(Entity living, double offset, boolean subtract, int axis) {
/* 835 */     if (axis == 0) {
/*     */       
/* 837 */       if (subtract)
/*     */       {
/* 839 */         living.field_70142_S -= offset;
/* 840 */         living.field_70169_q -= offset;
/* 841 */         living.field_70165_t -= offset;
/*     */       }
/*     */       else
/*     */       {
/* 845 */         living.field_70142_S += offset;
/* 846 */         living.field_70169_q += offset;
/* 847 */         living.field_70165_t += offset;
/*     */       }
/*     */     
/* 850 */     } else if (axis == 1) {
/*     */       
/* 852 */       if (subtract)
/*     */       {
/* 854 */         living.field_70137_T -= offset;
/* 855 */         living.field_70167_r -= offset;
/* 856 */         living.field_70163_u -= offset;
/*     */       }
/*     */       else
/*     */       {
/* 860 */         living.field_70137_T += offset;
/* 861 */         living.field_70167_r += offset;
/* 862 */         living.field_70163_u += offset;
/*     */       }
/*     */     
/* 865 */     } else if (axis == 2) {
/*     */       
/* 867 */       if (subtract) {
/*     */         
/* 869 */         living.field_70136_U -= offset;
/* 870 */         living.field_70166_s -= offset;
/* 871 */         living.field_70161_v -= offset;
/*     */       }
/*     */       else {
/*     */         
/* 875 */         living.field_70136_U += offset;
/* 876 */         living.field_70166_s += offset;
/* 877 */         living.field_70161_v += offset;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public static Render getEntityClassRenderObject(Class<Entity> par1Class) {
/* 885 */     Render render = (Render)RenderManager.field_78727_a.field_78729_o.get(par1Class);
/* 886 */     if (render == null && par1Class != Entity.class)
/*     */     {
/* 888 */       render = getEntityClassRenderObject(par1Class.getSuperclass());
/*     */     }
/* 890 */     return render;
/*     */   }
/*     */ 
/*     */   
/*     */   public static NBTTagCompound getPlayerPersistentData(EntityPlayer player) {
/* 895 */     NBTTagCompound persistentTag = player.getEntityData().func_74775_l("PlayerPersisted");
/* 896 */     player.getEntityData().func_74782_a("PlayerPersisted", (NBTBase)persistentTag);
/* 897 */     return persistentTag;
/*     */   }
/*     */ }


/* Location:              C:\DADOS\Jogos\Minecraft Legends - Copia\mods\iChunUtil-4.2.2.jar!\ichun\common\core\EntityHelperBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */