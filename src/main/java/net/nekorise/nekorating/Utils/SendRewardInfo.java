package net.nekorise.nekorating.Utils;

import net.nekorise.nekorating.Utils.ConfigStorage.DefaultConfigData;
import net.nekorise.nekorating.Utils.ConfigStorage.LanguageConfigData;
import org.bukkit.entity.Player;

public class SendRewardInfo
{
    Player player;
    int rewardSize;

    public SendRewardInfo(Player player, int rewardSize)
    {
        this.player = player;
        this.rewardSize = rewardSize;
    }

    public void sendRewardMessage()
    {
        String message = "";
        switch (DefaultConfigData.notifyReward)
        {
            case "ACTION_BAR":
                message = ParsePlaceholders.getParsed(LanguageConfigData.rewardActionbarMessage, player.getName(), rewardSize);
                player.sendActionBar(HEX.ApplyColor(message));
                break;
            case "CHAT_MESSAGE":
                message = ParsePlaceholders.getParsed(LanguageConfigData.rewardChatMessage, player.getName(), rewardSize);
                player.sendMessage(HEX.ApplyColor(message));
                break;
            case "TITLE":
                String title = ParsePlaceholders.getParsed(LanguageConfigData.rewardTitleMessage, player.getName(), rewardSize);
                String subtitle = ParsePlaceholders.getParsed(LanguageConfigData.rewardSubtitleMessage, player.getName(), rewardSize);
                player.sendTitle(HEX.ApplyColor(title), HEX.ApplyColor(subtitle), 5, 20, 5);
                break;
            default:
                break;
        }

        if (DefaultConfigData.isNotifyRewardSoundEnabled)
        {
            player.playSound(player, DefaultConfigData.notifyRewardSoundId, 1, 1);
        }
    }
}
