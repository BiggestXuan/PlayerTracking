package biggestxuan.player_tracking;

import biggestxuan.player_tracking.capability.ITracking;
import biggestxuan.player_tracking.capability.ModCapability;
import biggestxuan.player_tracking.event.CapabilityEvent;
import biggestxuan.player_tracking.event.CommandEvent;
import biggestxuan.player_tracking.event.PlayerEvent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.io.File;

@Mod(PlayerTracking.MODID)
public class PlayerTracking {

    public static final String MODID = "player_tracking";
    private static final Logger LOGGER = LogManager.getLogger();

    public PlayerTracking() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new CapabilityEvent());
        MinecraftForge.EVENT_BUS.register(new CommandEvent());
        MinecraftForge.EVENT_BUS.register(new PlayerEvent());
    }

    private void setup(final FMLCommonSetupEvent event) {
        File file = new File("/tracker");
        if(!file.exists()){
            file.mkdir();
        }
        event.enqueueWork(()-> CapabilityManager.INSTANCE.register(
                ITracking.class,
                new Capability.IStorage<ITracking>() {
                    @Nullable
                    @Override
                    public INBT writeNBT(Capability<ITracking> capability, ITracking instance, Direction side) {
                        return null;
                    }

                    @Override
                    public void readNBT(Capability<ITracking> capability, ITracking instance, Direction side, INBT nbt) {

                    }
                },
                () -> null
        ));
    }

    public static ResourceLocation rl(String name){
        return new ResourceLocation(MODID,name);
    }

    public static ITracking getTracking(PlayerEntity player){
        return player.getCapability(ModCapability.TRACKING).orElseThrow(NullPointerException::new);
    }

    public static TranslationTextComponent tc(String key,Object ... objects){
        return new TranslationTextComponent(key,objects);
    }

    public static TranslationTextComponent et(String key,Object ... objects){
        return tc("tracker.event."+key,objects);
    }

    public static TranslationTextComponent n(String key){
        return tc("tracker.name."+key);
    }
}
