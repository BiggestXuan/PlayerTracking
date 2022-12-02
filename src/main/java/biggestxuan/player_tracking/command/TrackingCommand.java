package biggestxuan.player_tracking.command;

/*
 *  Tracking
 *  @Author Biggest_Xuan
 *  2022/12/01
 */

import biggestxuan.player_tracking.PlayerTracking;
import biggestxuan.player_tracking.capability.ITracking;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;

public class TrackingCommand implements Command<CommandSource> {
    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = EntityArgument.getPlayer(context,"player");
        ITracking tracking = PlayerTracking.getTracking(player);
        if(tracking.isTracked()){
            tracking.setTracked(false);
            context.getSource().sendSuccess(PlayerTracking.tc("tracking.command.unable"),false);
        }else{
            tracking.setTracked(true);
            context.getSource().sendSuccess(PlayerTracking.tc("tracking.command.able"),false);
        }
        return 0;
    }
}
