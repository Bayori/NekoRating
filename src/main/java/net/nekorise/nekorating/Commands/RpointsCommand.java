package net.nekorise.nekorating.Commands;

import net.nekorise.nekorating.Utils.ConfigStorage.DefaultConfigData;
import net.nekorise.nekorating.Utils.ConfigStorage.LanguageConfigData;
import net.nekorise.nekorating.Utils.HEX;
import net.nekorise.nekorating.Utils.MySQL;
import net.nekorise.nekorating.Utils.ParsePlaceholders;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class RpointsCommand implements CommandExecutor
{

    String username = "";
    int points = 0;


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args)
    {
        if (args.length <= 2 || !isNumeric(args[2]) || !isNotFullInt(args[2]))
        {
            String usageMessage = ParsePlaceholders.getParsed(LanguageConfigData.rpointsUsage);
            sender.sendMessage(HEX.ApplyColor(usageMessage));
            return false;
        }

        points = Math.abs(Integer.parseInt(args[2]));
        username = args[1];

        Player operationPlayer = null;
        for (Player p : Bukkit.getServer().getOnlinePlayers())
        {
            if (p.getName().equals(username))
            {
                operationPlayer = p;
            }
        }


        MySQL mySQL = new MySQL();

        if (args[0].equalsIgnoreCase("set"))
        {
            return setPoints(sender, mySQL, operationPlayer);
        }

        else if (args[0].equalsIgnoreCase("add"))
        {
            return addPoints(sender, mySQL, operationPlayer);
        }

        else if (args[0].equalsIgnoreCase("take"))
        {
            return takePoints(sender, mySQL, operationPlayer);
        }

        return false;
    }



    private boolean addPoints(CommandSender sender, MySQL mySQL, Player operPlayer)
    {
        String errMessage = ParsePlaceholders.getParsed(LanguageConfigData.rpointsError);
        if (mySQL.addPoints(username, points))
        {
            String senderMessage = ParsePlaceholders.getParsed(LanguageConfigData.rpointsAddSender, username, Math.abs(points));
            sender.sendMessage(HEX.ApplyColor(senderMessage));

            if (operPlayer != null && DefaultConfigData.notifyAdd)
            {
                String operPlayerMessage = ParsePlaceholders.getParsed(LanguageConfigData.rpointsAddPlayer, username, points);
                operPlayer.sendMessage(HEX.ApplyColor(operPlayerMessage));
            }
            return true;
        }
        else
        {
            sender.sendMessage(HEX.ApplyColor(errMessage));
            return false;
        }
    }

    private boolean takePoints(CommandSender sender, MySQL mySQL, Player operPlayer)
    {
        String errMessage = ParsePlaceholders.getParsed(LanguageConfigData.rpointsError);
        if (mySQL.takePoints(username, points))
        {
            String senderMessage = ParsePlaceholders.getParsed(LanguageConfigData.rpointsTakeSender, username, Math.abs(points));
            sender.sendMessage(HEX.ApplyColor(senderMessage));
            if (operPlayer != null && DefaultConfigData.notifyTake)
            {
                String operPlayerMessage = ParsePlaceholders.getParsed(LanguageConfigData.rpointsTakePlayer, username, points);
                operPlayer.sendMessage(HEX.ApplyColor(operPlayerMessage));
            }
            return true;
        }
        else
        {
            sender.sendMessage(HEX.ApplyColor(errMessage));
            return false;
        }
    }

    private boolean setPoints(CommandSender sender, MySQL mySQL, Player operPlayer)
    {
        String errMessage = ParsePlaceholders.getParsed(LanguageConfigData.rpointsError);
        if (mySQL.setPoints(username, points))
        {
            String senderMessage = ParsePlaceholders.getParsed(LanguageConfigData.rpointsSetSender, username, Math.abs(points));
            sender.sendMessage(HEX.ApplyColor(senderMessage));
            if (operPlayer != null && DefaultConfigData.notifySet)
            {
                String operPlayerMessage = ParsePlaceholders.getParsed(LanguageConfigData.rpointsSetPlayer, username, points);
                operPlayer.sendMessage(HEX.ApplyColor(operPlayerMessage));
            }
            return true;
        }
        else
        {
            sender.sendMessage(HEX.ApplyColor(errMessage));
            return false;
        }
    }

    private boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private boolean isNotFullInt(String integer)
    {
        try
        {
            Integer.parseInt(integer);
            return true;
        } catch (NumberFormatException ex)
        {
            return false;
        }
    }
}
