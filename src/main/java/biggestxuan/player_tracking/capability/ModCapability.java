package biggestxuan.player_tracking.capability;

/*
 *  Tracking
 *  @Author Biggest_Xuan
 *  2022/12/01
 */

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class ModCapability {
    @CapabilityInject(ITracking.class)
    public static Capability<ITracking> TRACKING;
}
