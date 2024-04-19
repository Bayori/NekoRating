package net.nekorise.nekorating.Events;

import net.nekorise.nekorating.Utils.ConfigStorage.RewardConfigData;
import net.nekorise.nekorating.Utils.MySQL;
import net.nekorise.nekorating.Utils.SendRewardInfo;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;

public class EnchantmentEvent implements Listener
{
    @EventHandler
    public void onEnchantment(EnchantItemEvent event)
    {
        if (!(event.getEnchanter() instanceof Player) || !RewardConfigData.isEnchantmentEnabled || !event.getEnchanter().hasPermission("nekorating.enableget"))
        {
            return;
        }

        Player player = event.getEnchanter();
        MySQL mySQL = new MySQL();
        SendRewardInfo sendRewardInfo;

        switch (event.whichButton())
        {
            case 0:
                mySQL.addPoints(player.getName(), RewardConfigData.enchantmentLvl1Reward);
                sendRewardInfo = new SendRewardInfo(player, RewardConfigData.enchantmentLvl1Reward);
                sendRewardInfo.sendRewardMessage();
                break;

            case 1:
                mySQL.addPoints(player.getName(), RewardConfigData.enchantmentLvl2Reward);
                sendRewardInfo = new SendRewardInfo(player, RewardConfigData.enchantmentLvl2Reward);
                sendRewardInfo.sendRewardMessage();
                break;

            case 2:
                mySQL.addPoints(player.getName(), RewardConfigData.enchantmentLvl3Reward);
                sendRewardInfo = new SendRewardInfo(player, RewardConfigData.enchantmentLvl3Reward);
                sendRewardInfo.sendRewardMessage();
                break;

            default:
                break;
        }
    }
}
