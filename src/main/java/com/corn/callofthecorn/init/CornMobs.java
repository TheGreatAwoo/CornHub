package com.corn.callofthecorn.init;

import com.corn.callofthecorn.Main;
import com.corn.callofthecorn.entities.crop_watcher.CropWatcher;
import com.corn.callofthecorn.entities.crow.Crow;
import com.corn.callofthecorn.entities.farmhand.Farmhand;
import com.corn.callofthecorn.entities.harvest_crow.HarvestCrow;
import com.corn.callofthecorn.entities.harvester.Harvester;
import com.corn.callofthecorn.entities.pumpking.PumpKing;
import com.corn.callofthecorn.entities.scarecrow.Scarecrow;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class CornMobs {


    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, Main.MOD_ID);


    public static final Supplier<EntityType<Scarecrow>> SCARECROW = ENTITY_TYPES.register("scarecrow",
            () -> EntityType.Builder.<Scarecrow>of(Scarecrow::new, MobCategory.MONSTER)
                    .sized(EntityType.SKELETON.getWidth(), EntityType.SKELETON.getHeight())
                    .setTrackingRange(80)
                 //   .setUpdateInterval(10)
                    .setShouldReceiveVelocityUpdates(true)
                    .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "scarecrow")))

    );




    public static final Supplier<EntityType<Farmhand>> FARMHAND = ENTITY_TYPES.register("farmhand",
            () -> EntityType.Builder.<Farmhand>of(Farmhand::new, MobCategory.MONSTER)
                    .sized(EntityType.ZOMBIE.getWidth(), EntityType.ZOMBIE.getHeight())
                    .setTrackingRange(50)
                    .fireImmune()
                  //  .setUpdateInterval(10)
                    .setShouldReceiveVelocityUpdates(true)
                    .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "farmhand")))

    );



    public static final Supplier<EntityType<Harvester>> HARVESTER = ENTITY_TYPES.register("harvester",
            () -> EntityType.Builder.<Harvester>of(Harvester::new, MobCategory.MONSTER)
                    .sized(EntityType.SKELETON.getWidth(), EntityType.SKELETON.getHeight())
                    .setTrackingRange(60)
                    .fireImmune()
                    //  .setUpdateInterval(10)
                    .setShouldReceiveVelocityUpdates(true)
                    .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "harvester")))

    );


    public static final Supplier<EntityType<HarvestCrow>> HARVEST = ENTITY_TYPES.register("harvest",
            () -> EntityType.Builder.<HarvestCrow>of(HarvestCrow::new, MobCategory.MONSTER)
                    .sized(EntityType.GHAST.getWidth(), EntityType.GHAST.getHeight())
                    .setTrackingRange(500)
                    .fireImmune()
                    .setShouldReceiveVelocityUpdates(true)
                    .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "harvest")))

    );


    public static final Supplier<EntityType<CropWatcher>> CROPWATCHER = ENTITY_TYPES.register("cropwatcher",
            () -> EntityType.Builder.<CropWatcher>of(CropWatcher::new, MobCategory.MONSTER)
                    .sized(EntityType.SKELETON.getWidth(), EntityType.SKELETON.getHeight())
                    .setTrackingRange(20)
//                    .fireImmune()
                    .setShouldReceiveVelocityUpdates(true)
                    .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "cropwatcher")))

    );

    public static final Supplier<EntityType<PumpKing>> PUMPKING = ENTITY_TYPES.register("pumpking",
            () -> EntityType.Builder.<PumpKing>of(PumpKing::new, MobCategory.MONSTER)
                    .sized(EntityType.SKELETON.getWidth(), EntityType.SKELETON.getHeight())
                    .setTrackingRange(40)
                    .setShouldReceiveVelocityUpdates(true)
                    .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "pumpking")))

    );


    public static final Supplier<EntityType<Crow>> CROW = ENTITY_TYPES.register("crow",
            () -> EntityType.Builder.<Crow>of(Crow::new, MobCategory.CREATURE)
                    .sized(EntityType.PARROT.getWidth(),EntityType.PARROT.getHeight())
                    .setTrackingRange(40)
                    .setShouldReceiveVelocityUpdates(true)
                    .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "crow")))

    );





    public static void registerAttributes(EntityAttributeCreationEvent event) {


        event.put(SCARECROW.get(), Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, .5)
                .add(Attributes.MAX_HEALTH, Scarecrow.MAX_HP)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0)
                .add(Attributes.ATTACK_DAMAGE,3)
                .add(Attributes.ATTACK_SPEED,1)
                .add(Attributes.ATTACK_KNOCKBACK,.6)
                .build());


        event.put(FARMHAND.get(), Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.MAX_HEALTH, Farmhand.MAX_HP)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.6)
                .add(Attributes.ATTACK_DAMAGE,Farmhand.ATTACK_DAMAGE)
                .add(Attributes.ATTACK_SPEED,1)
                .add(Attributes.ATTACK_KNOCKBACK,1)
                .build());

        event.put(HARVESTER.get(), Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.MAX_HEALTH, Harvester.MAX_HP)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.8)
                .add(Attributes.ATTACK_DAMAGE,Harvester.AttackDamage)
                .add(Attributes.ATTACK_SPEED,1)
                .add(Attributes.ATTACK_KNOCKBACK,1)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE,.8)
                .build());


        event.put(HARVEST.get(), Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.MAX_HEALTH, HarvestCrow.MAX_HP)
                .add(Attributes.KNOCKBACK_RESISTANCE, .9)
                .add(Attributes.ATTACK_DAMAGE, HarvestCrow.AttackDamage)
                .add(Attributes.ATTACK_SPEED,1)
                .add(Attributes.ATTACK_KNOCKBACK,1F)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE,1)
                .add(Attributes.FLYING_SPEED,0.8F)
                .build());


        event.put(CROPWATCHER.get(), Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.35)
                .add(Attributes.MAX_HEALTH, CropWatcher.MAX_HP)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0)
                .add(Attributes.ATTACK_DAMAGE,CropWatcher.AttackDamage)
                .add(Attributes.ATTACK_SPEED,1)
                .add(Attributes.ATTACK_KNOCKBACK,1F)
                .build());

        event.put(PUMPKING.get(), Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.4)
                .add(Attributes.MAX_HEALTH, PumpKing.MAX_HP)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.6)
                .add(Attributes.ATTACK_DAMAGE,PumpKing.AttackDamage)
                .add(Attributes.ATTACK_SPEED,1)
                .add(Attributes.ATTACK_KNOCKBACK,1)
                .build());


        event.put(CROW.get(), Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.4)
                .add(Attributes.FLYING_SPEED,1.8F)
                .add(Attributes.MAX_HEALTH, Crow.MAX_HP)
                .add(Attributes.ATTACK_DAMAGE,Crow.DAMAGE)
                .add(Attributes.ATTACK_SPEED,1)
                .build());

    }



}