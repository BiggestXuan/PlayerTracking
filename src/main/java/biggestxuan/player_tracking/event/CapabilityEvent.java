package biggestxuan.player_tracking.event;

/*
 *  Tracking
 *  @Author Biggest_Xuan
 *  2022/12/01
 */

import biggestxuan.player_tracking.PlayerTracking;
import biggestxuan.player_tracking.capability.ITracking;
import biggestxuan.player_tracking.capability.ModCapability;
import biggestxuan.player_tracking.capability.TrackingCapabilityProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CapabilityEvent {
    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<Entity> event){
        if(event.getObject() instanceof PlayerEntity){
            event.addCapability(PlayerTracking.rl("tracking"),new TrackingCapabilityProvider());
        }
    }

    @SubscribeEvent
    public void playerCloned(PlayerEvent.Clone event){
        LazyOptional<ITracking> oldSpeedCap = event.getOriginal().getCapability(ModCapability.TRACKING);
        LazyOptional<ITracking> newSpeedCap = event.getPlayer().getCapability(ModCapability.TRACKING);
        if (oldSpeedCap.isPresent() && newSpeedCap.isPresent()) {
            newSpeedCap.ifPresent((newCap) -> oldSpeedCap.ifPresent((oldCap) -> newCap.deserializeNBT(oldCap.serializeNBT())));
        }
    }
}
