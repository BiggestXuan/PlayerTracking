package biggestxuan.player_tracking.capability;

/*
 *  Tracking
 *  @Author Biggest_Xuan
 *  2022/12/01
 */

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TrackingCapabilityProvider implements ICapabilityProvider, INBTSerializable<CompoundNBT> {
    private ITracking tracking;

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return cap == ModCapability.TRACKING ? LazyOptional.of(this::getTracking).cast() : LazyOptional.empty();
    }

    @Nonnull
    private ITracking getTracking(){
        if(tracking == null){
            tracking = new TrackingCapability();
        }
        return tracking;
    }

    @Override
    public CompoundNBT serializeNBT() {
        return getTracking().serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        getTracking().deserializeNBT(nbt);
    }
}
