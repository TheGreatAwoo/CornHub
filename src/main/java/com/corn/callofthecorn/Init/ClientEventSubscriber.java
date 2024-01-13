package com.corn.callofthecorn.Init;

import com.corn.callofthecorn.Main;
import com.corn.callofthecorn.entities.CropWatcher.CropWatcherModel;
import com.corn.callofthecorn.entities.CropWatcher.RenderCropWatcher;
import com.corn.callofthecorn.entities.Crow.CrowModel;
import com.corn.callofthecorn.entities.Crow.RenderCrow;
import com.corn.callofthecorn.entities.FarmHand.FarmhandModel;
import com.corn.callofthecorn.entities.FarmHand.RenderFarmhand;
import com.corn.callofthecorn.entities.HarvestCrow.HarvestModel;
import com.corn.callofthecorn.entities.HarvestCrow.RenderHarvest;
import com.corn.callofthecorn.entities.Harvester.HarvesterModel;
import com.corn.callofthecorn.entities.Harvester.RenderHarvester;
import com.corn.callofthecorn.entities.PumpKing.PumpKingModel;
import com.corn.callofthecorn.entities.PumpKing.RenderPumpKing;
import com.corn.callofthecorn.entities.Scarecrow.RenderScarecrow;
import com.corn.callofthecorn.entities.Scarecrow.ScarecrowModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
//import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;

@Mod.EventBusSubscriber(modid = Main.MODID,bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class ClientEventSubscriber {



//    @SubscribeEvent
//    public static void onRegisterItems(RegistryEvent.Register<Item> event) {
//        final IForgeRegistry<Item> reg = event.getRegistry();
//        BlocksInit.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(
//                block -> {
//                    final Item.Properties properties = new Item.Properties().tab(CornTab.TAB_Corn);
//                    final BlockItem blockItem = new BlockItem(block, properties);
//                    blockItem.setRegistryName(block.getRegistryName());
//                    reg.register(blockItem);
//                }
//        );
//    }


//    @SubscribeEvent
//    public static void onRegisterBlocks(RegisterEvent.Register<Block> event) {
//        ItemBlockRenderTypes.setRenderLayer(BlocksInit.CORN_SEED.get(), RenderType.cutout());
//    }



    public static final ModelLayerLocation SCARECROW_LAYER = new ModelLayerLocation(new ResourceLocation(Main.MODID,"scarecrow"),"scarecrow");
    public static final ModelLayerLocation FARMHAND_LAYER = new ModelLayerLocation(new ResourceLocation(Main.MODID,"farmhand"),"farmhand");
    public static final ModelLayerLocation HARVESTER_LAYER = new ModelLayerLocation(new ResourceLocation(Main.MODID,"harvester"),"harvester");
    public static final ModelLayerLocation HARVEST_LAYER = new ModelLayerLocation(new ResourceLocation(Main.MODID,"harvest"),"harvest");
    public static final ModelLayerLocation CROPWATCHER_LAYER = new ModelLayerLocation(new ResourceLocation(Main.MODID,"cropwatcher"),"cropwatcher");
    public static final ModelLayerLocation PUMPKING_LAYER = new ModelLayerLocation(new ResourceLocation(Main.MODID,"pumpking"),"pumpking");
    public static final ModelLayerLocation CROW_LAYER = new ModelLayerLocation(new ResourceLocation(Main.MODID,"crow"),"crow");



    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event){

        event.registerEntityRenderer(MobInit.SCARECROW.get(), RenderScarecrow::new);
        event.registerEntityRenderer(MobInit.FARMHAND.get(), RenderFarmhand::new);
        event.registerEntityRenderer(MobInit.HARVESTER.get(), RenderHarvester::new);
        event.registerEntityRenderer(MobInit.HARVEST.get(), RenderHarvest::new);
        event.registerEntityRenderer(MobInit.CROPWATCHER.get(), RenderCropWatcher::new);
        event.registerEntityRenderer(MobInit.PUMPKING.get(), RenderPumpKing::new);
        event.registerEntityRenderer(MobInit.CROW.get(), RenderCrow::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinition(EntityRenderersEvent.RegisterLayerDefinitions event){

        event.registerLayerDefinition(SCARECROW_LAYER, ScarecrowModel::createBodyLayer);
        event.registerLayerDefinition(FARMHAND_LAYER, FarmhandModel::createBodyLayer);
        event.registerLayerDefinition(HARVESTER_LAYER, HarvesterModel::createBodyLayer);
        event.registerLayerDefinition(HARVEST_LAYER, HarvestModel::createBodyLayer);
        event.registerLayerDefinition(CROPWATCHER_LAYER, CropWatcherModel::createBodyLayer);
        event.registerLayerDefinition(PUMPKING_LAYER, PumpKingModel::createBodyLayer);
        event.registerLayerDefinition(CROW_LAYER, CrowModel::createBodyLayer);
    }



//    @SubscribeEvent
//    public void LivingEquipmentChangeEvent(LivingEquipmentChangeEvent event){
//        if(event.getSlot() == EquipmentSlot.FEET){
//            ItemStack stack = event.getTo();
//
//           // if(hasFleetFoot(stack)){
//
//                Player player = (Player) event.getEntityLiving();
//                Player.setViewScale(0);
//                ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
//                AttributeModifier speedAt = new AttributeModifier(UUID.fromString("91AEAA56-376B-4498-935B-2F7F68070635"), "Armor modifier", 1, AttributeModifier.Operation.ADDITION);
//                player.getItemBySlot(EquipmentSlot.FEET).addAttributeModifier(Attributes.MOVEMENT_SPEED,speedAt,EquipmentSlot.FEET );
//           // }
//
//
//           // else{
//            //   Player player = (Player) event.getEntityLiving();
//             //   ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
//             //   AttributeModifier speedAt = new AttributeModifier(UUID.fromString("91AEAA56-376B-4498-935B-2F7F68070635"), "Armor modifier", 1, AttributeModifier.Operation.ADDITION);
//            //    player.getItemBySlot(EquipmentSlot.FEET).addAttributeModifier(Attributes.MOVEMENT_SPEED,speedAt,EquipmentSlot.FEET );
//
//           // }
//        }
//    }

//    @SubscribeEvent
//    public  void onEntityMoved(LivingEntity p_45019_, Level p_45020_, BlockPos p_45021_, int p_45022_) {
//        if (p_45019_.isOnGround()) {
//            BlockState blockstate = Blocks.FROSTED_ICE.defaultBlockState();
//            float f = (float)Math.min(16, 2 + p_45022_);
//            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
//
//            for(BlockPos blockpos : BlockPos.betweenClosed(p_45021_.offset((double)(-f), -1.0D, (double)(-f)), p_45021_.offset((double)f, -1.0D, (double)f))) {
//                if (blockpos.closerThan(p_45019_.position(), (double)f)) {
//                    blockpos$mutableblockpos.set(blockpos.getX(), blockpos.getY() + 1, blockpos.getZ());
//                    BlockState blockstate1 = p_45020_.getBlockState(blockpos$mutableblockpos);
//                    if (blockstate1.isAir()) {
//                        BlockState blockstate2 = p_45020_.getBlockState(blockpos);
//                        boolean isFull = blockstate2.getBlock() == Blocks.WATER && blockstate2.getValue(LiquidBlock.LEVEL) == 0; //TODO: Forge, modded waters?
//                        if (blockstate2.getMaterial() == Material.WATER && isFull && blockstate.canSurvive(p_45020_, blockpos) && p_45020_.isUnobstructed(blockstate, blockpos, CollisionContext.empty()) && !net.minecraftforge.event.ForgeEventFactory.onBlockPlace(p_45019_, net.minecraftforge.common.util.BlockSnapshot.create(p_45020_.dimension(), p_45020_, blockpos), net.minecraft.core.Direction.UP)) {
//                            p_45020_.setBlockAndUpdate(blockpos, blockstate);
//                            p_45020_.getBlockTicks().scheduleTick(blockpos, Blocks.FROSTED_ICE, Mth.nextInt(p_45019_.getRandom(), 60, 120));
//                        }
//                    }
//                }
//            }
//
//        }
//    }





}
