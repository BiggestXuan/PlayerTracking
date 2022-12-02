package biggestxuan.player_tracking.capability;

/*
 *  Tracking
 *  @Author Biggest_Xuan
 *  2022/12/01
 */

import net.minecraft.nbt.CompoundNBT;

public class TrackingCapability implements ITracking{
    private boolean isTracked;

    public TrackingCapability(){
        isTracked = false;
    }

    @Override
    public boolean isTracked() {
        return isTracked;
    }

    @Override
    public void setTracked(boolean value) {
        isTracked = value;
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT tag = new CompoundNBT();
        tag.putBoolean("isTracked",isTracked);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        isTracked = nbt.getBoolean("isTracked");
    }
}
