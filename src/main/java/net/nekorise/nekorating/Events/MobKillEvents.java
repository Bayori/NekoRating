package net.nekorise.nekorating.Events;

import net.nekorise.nekorating.Utils.ConfigStorage.RewardConfigData;
import net.nekorise.nekorating.Utils.MySQL;
import net.nekorise.nekorating.Utils.SendRewardInfo;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Map;

public class MobKillEvents implements Listener
{
    @EventHandler
    public void onMobKill(EntityDeathEvent event)
    {
        if (!(event.getEntity().getKiller() instanceof Player))
        {
            return;
        }

        if (!event.getEntity().getKiller().hasPermission("nekorating.enableget"))
        {
            return;
        }

        MySQL mySQL = new MySQL();
        EntityType entity = event.getEntityType();
        Player player = event.getEntity().getKiller();
        SendRewardInfo sendRewardInfo;

        for (Map.Entry<EntityType, Integer> cfgMobs : RewardConfigData.mobList.entrySet())
        {

            if (entity == cfgMobs.getKey())
            {
                mySQL.addPoints(player.getName(), cfgMobs.getValue());
                sendRewardInfo = new SendRewardInfo(player, cfgMobs.getValue());
                sendRewardInfo.sendRewardMessage();
                return;
            }
        }

        if (RewardConfigData.isOtherMobsEnabled)
        {
            mySQL.addPoints(player.getName(), RewardConfigData.otherMobsReward);
            sendRewardInfo = new SendRewardInfo(player, RewardConfigData.otherMobsReward);
            sendRewardInfo.sendRewardMessage();
        }
    }
}
