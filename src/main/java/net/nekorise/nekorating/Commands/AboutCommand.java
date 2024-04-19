package net.nekorise.nekorating.Commands;

import net.nekorise.nekorating.NekoRating;
import net.nekorise.nekorating.Utils.*;
import net.nekorise.nekorating.Utils.ConfigStorage.LanguageConfigData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.jetbrains.annotations.NotNull;

public class AboutCommand implements CommandExecutor
{

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args)
    {
        Player player = (Player) sender;

        if (args.length == 0 || !sender.hasPermission("nekorating.reload"))
        {
            String aboutMessage1 = HEX.gradientText("NekoRating v"+ NekoRating.getPlugin().getPluginMeta().getVersion(), HEX.parseHexColor("#f2941f"), HEX.parseHexColor("#bff21f"));
            String aboutMessage2 = HEX.gradientText("\nby Nekorise", HEX.parseHexColor("#f2941f"), HEX.parseHexColor("#bff21f"));
            sender.sendMessage(aboutMessage1 + aboutMessage2);
            return true;
        }

        if (args[0].equalsIgnoreCase("reload") && sender.hasPermission("nekorating.reload"))
        {
            try
            {
                Configs.loadConfig();
                String reloadMessage = ParsePlaceholders.getParsed(LanguageConfigData.cfgReload);
                sender.sendMessage(HEX.ApplyColor(reloadMessage));
                NekoRating.getPlugin().getLogger().info("Configs and database connection reloaded");
            } catch (Exception ex)
            {
                ex.printStackTrace();
            }

            return true;
        }
        return false;
    }

}
