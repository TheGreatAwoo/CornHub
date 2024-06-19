package com.corn.callofthecorn;

import com.corn.callofthecorn.init.CornItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;

@Mod.EventBusSubscriber(modid = Main.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CombatEventHandler {

    @SubscribeEvent
    public static void onAttack(AttackEntityEvent event) {
        if(event.getTarget() instanceof LivingEntity e) {
            CuriosApi.getCuriosInventory(event.getEntity()).ifPresent((itemHandler)-> {
                itemHandler.findFirstCurio(CornItems.CROWSFOOT.get()).ifPresent((result) -> {
                    e.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 0));
                });
            });
        }
    }
}
