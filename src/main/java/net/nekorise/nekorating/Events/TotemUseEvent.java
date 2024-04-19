package net.nekorise.nekorating.Events;

import net.nekorise.nekorating.Utils.ConfigStorage.RewardConfigData;
import net.nekorise.nekorating.Utils.MySQL;
import net.nekorise.nekorating.Utils.SendRewardInfo;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityResurrectEvent;

public class TotemUseEvent implements Listener
{
    @EventHandler
    public void onTotemUse(EntityResurrectEvent event)
    {
        if (!(event.getEntity() instanceof Player) || !RewardConfigData.isTotemUseEnabled)
        {
            return;
        }

        Player player = (Player) event.getEntity();
        MySQL mySQL = new MySQL();
        SendRewardInfo sendRewardInfo = new SendRewardInfo(player, RewardConfigData.totemUseReward);

        if (!player.hasPermission("nekorating.enableget")) { return; }
        // true - Без тотема
        // false - С тотемом
        if (!event.isCancelled())
        {
            mySQL.addPoints(player.getName(), RewardConfigData.totemUseReward);
            sendRewardInfo.sendRewardMessage();
        }
    }
}
