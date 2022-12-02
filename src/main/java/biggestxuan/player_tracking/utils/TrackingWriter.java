package biggestxuan.player_tracking.utils;

/*
 *  Tracking
 *  @Author Biggest_Xuan
 *  2022/12/02
 */

import biggestxuan.player_tracking.PlayerTracking;
import net.minecraft.entity.player.PlayerEntity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class TrackingWriter {
    private static final String path = "/tracker";
    public static void writeInfo(PlayerEntity player, EventInfo text) {
        if(!PlayerTracking.getTracking(player).isTracked()){
            return;
        }
        try{
            String info = "["+text.getName().getString()+"]"+" "+text.getInfo().getString();
            File file = new File(getPath(player));
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file.getName(),true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(getTime()+info+"\n");
            bufferedWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static String getPath(PlayerEntity player){
        return player.getScoreboardName()+"_"+player.getStringUUID()+".log";
    }

    private static String getTime(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int mi = calendar.get(Calendar.MINUTE);
        int se = calendar.get(Calendar.SECOND);
        return "["+year+"/"+month+"/"+day+" "+hour+":"+mi+":"+se+"]";
    }
}
