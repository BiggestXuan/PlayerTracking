package biggestxuan.player_tracking.capability;

/*
 *  Tracking
 *  @Author Biggest_Xuan
 *  2022/12/01
 */

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public interface ITracking extends INBTSerializable<CompoundNBT> {
    boolean isTracked();

    void setTracked(boolean value);
}
