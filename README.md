# PlayerTracking  
这是一个用于在服务器中跟踪玩家的小模组。 
  
当执行指令 `/tracking <玩家名>` 后，该玩家就会被跟踪，该玩家以下的操作全部会以独立的log输出：  
- 玩家攻击，受击；
- 玩家放置，破坏方块；
- 捡起，丢掉东西；
  
暂时就这么多。  
  
存在以下原因，该模组并不适合发布：  
- 仅限一个存档，切换存档将会混合（主要）；
- 跟踪内容不足。
  
这只是为我服务器设计的一个东东，版本 `Forge 1.16.5`，以后会修复上面的问题，但近期暂时没时间修改了。 
  
可能还有其他BUG，如果你想拿去用请便，不要汇报任何BUG。  
  
你可以用以下的办法添加自己的输出信息：  
```
biggestxuan.player_tracking.utils.TrackingWriter.writeInfo(net.minecraft.entity.player,biggestxuan.player_tracking.utils.EventInfo);
  
public EventInfo(net.minecraft.util.text.TextComponent name,net.minecraft.util.text.TextComponent info);
```
