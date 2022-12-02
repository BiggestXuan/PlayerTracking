package biggestxuan.player_tracking.event;

/*
 *  Tracking
 *  @Author Biggest_Xuan
 *  2022/12/01
 */

import biggestxuan.player_tracking.command.TrackingCommand;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CommandEvent {
    @SubscribeEvent
    public void commandRegister(RegisterCommandsEvent event){
        event.getDispatcher().register(
                Commands.literal("tracking").requires((permission)-> permission.hasPermission(4))
                        .then(Commands.argument("player", EntityArgument.player()).executes(new TrackingCommand()))
        );
    }
}
