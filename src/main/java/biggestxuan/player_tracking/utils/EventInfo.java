package biggestxuan.player_tracking.utils;

/*
 *  Tracking
 *  @Author Biggest_Xuan
 *  2022/12/02
 */

import net.minecraft.util.text.TextComponent;

public class EventInfo {
    private final TextComponent name;
    private final TextComponent info;

    public EventInfo(TextComponent name,TextComponent info){
        this.name = name;
        this.info = info;
    }

    public TextComponent getInfo() {
        return info;
    }

    public TextComponent getName() {
        return name;
    }
}
