package biggestxuan.player_tracking.event;

/*
 *  Tracking
 *  @Author Biggest_Xuan
 *  2022/12/02
 */

import biggestxuan.player_tracking.PlayerTracking;
import biggestxuan.player_tracking.utils.EventInfo;
import biggestxuan.player_tracking.utils.TrackingWriter;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PlayerEvent {
    @SubscribeEvent
    public void attackEvent(LivingDamageEvent event) {
        if(event.getSource().getDirectEntity() instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity) event.getSource().getDirectEntity();
            EventInfo info = new EventInfo(PlayerTracking.n("attack"),PlayerTracking.et("attack",event.getEntityLiving().getName().getString(),event.getAmount(),getDimensionName(player.level),getPos(event.getEntityLiving())));
            TrackingWriter.writeInfo(player, info);
        }
        if(event.getEntityLiving() instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity) event.getEntityLiving();
            EventInfo info = new EventInfo(PlayerTracking.n("hurt"),PlayerTracking.et("hurt",event.getSource().msgId,event.getAmount(),getDimensionName(player.level),getPos(player)));
            TrackingWriter.writeInfo(player, info);
        }
    }

    @SubscribeEvent
    public void placeEvent(BlockEvent.EntityPlaceEvent event){
        Entity entity = event.getEntity();
        if(entity instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity) entity;
            EventInfo info = new EventInfo(PlayerTracking.n("place"),PlayerTracking.et("place",event.getPlacedBlock().getBlock().getName().getString(),getDimensionName(player.level),getPos(event.getPos()),getPos(player)));
            TrackingWriter.writeInfo(player,info);
        }
    }

    @SubscribeEvent
    public void breakEvent(BlockEvent.BreakEvent event){
        PlayerEntity player = event.getPlayer();
        EventInfo info = new EventInfo(PlayerTracking.n("place"),PlayerTracking.et("place",event.getState().getBlock().getName().getString(),getDimensionName(player.level),getPos(event.getPos()),getPos(player)));
        TrackingWriter.writeInfo(player,info);
    }

    @SubscribeEvent
    public void pickEvent(EntityItemPickupEvent event){
        PlayerEntity player = event.getPlayer();
        ItemStack stack = event.getItem().getItem();
        EventInfo info = new EventInfo(PlayerTracking.n("pick"),PlayerTracking.et("pick",getItem(stack),getDimensionName(player.level),getPos(player)));
        TrackingWriter.writeInfo(player,info);
    }

    @SubscribeEvent
    public void tossEvent(ItemTossEvent event){
        PlayerEntity player = event.getPlayer();
        ItemStack stack = event.getEntityItem().getItem();
        EventInfo info = new EventInfo(PlayerTracking.n("toss"),PlayerTracking.et("toss",getItem(stack),getDimensionName(player.level),getPos(player)));
        TrackingWriter.writeInfo(player,info);
    }

    @SubscribeEvent
    public void rightClickEvent(PlayerInteractEvent.RightClickBlock event){
        PlayerEntity player = event.getPlayer();
        Block block = event.getWorld().getBlockState(event.getPos()).getBlock();
        EventInfo info = new EventInfo(PlayerTracking.n("tclick_blockoss"),PlayerTracking.et("click_block",block.getName().getString(),getDimensionName(player.level),getPos(event.getPos())));
    }

    private static String getItem(ItemStack stack){
        String out = stack.getDisplayName().getString() + "*" +stack.getCount();
        if(stack.hasTag()){
            out += stack.getOrCreateTag().toString();
        }
        return out;
    }

    private static String getDimensionName(World world){
        return world.dimension().location().toString();
    }

    private static String getPos(BlockPos pos){
        return new TrackingPos(pos.getX(),pos.getY(),pos.getZ()).toString();
    }

    private static String getPos(Entity entity){
        return new TrackingPos(format(entity.getX()), format(entity.getY()), format(entity.getX())).toString();

    }

    private static String format(double d){
        return String.format("%.2f",d);
    }

    static class TrackingPos{
        private final String x;
        private final String y;
        private final String z;

        TrackingPos(int x,int y,int z){
            this.x = String.valueOf(x);
            this.y = String.valueOf(y);
            this.z = String.valueOf(z);
        }

        TrackingPos(String x,String y,String z){
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public String toString(){
            return "[X="+x+",Y="+y+",Z="+z+"]";
        }
    }
}
