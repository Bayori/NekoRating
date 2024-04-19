package net.nekorise.nekorating.Utils;

import net.nekorise.nekorating.Utils.ConfigStorage.LanguageConfigData;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.*;

public class TopPlayers {

    TreeMap<String, Integer> topPlayers = new TreeMap<>();
    public static List<Map.Entry<String, Integer>> sortPlayers = new ArrayList<>(List.of(
            new AbstractMap.SimpleEntry<>("empty", 1)
    ));

    MySQL mySQL = new MySQL();



    public void refreshPlayers()
    {
        for (String playerName : mySQL.getAllPlayers())
        {
            topPlayers.putIfAbsent(playerName, mySQL.getPlayerPoints(playerName));
        }

        //System.out.println("Выгружено!");

        // Сортировка тримапы
        sortPlayers = new ArrayList<>(topPlayers.entrySet());
        sortPlayers.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        //System.out.println("Отсортировано!");

        topPlayers.clear();
    }
    public static String getTopPlayerName(int topNumber)
    {
        int i = 0;
        for ( Map.Entry<String, Integer> topPlayers : sortPlayers)
        {
            if (i == topNumber-1)
            {
                return topPlayers.getKey();
            }
            i++;
        }
        return LanguageConfigData.papiZeroTopUsername;
    }

    public static String getTopPlayerPoints(int topNumber)
    {
        int i = 0;
        for ( Map.Entry<String, Integer> topPlayers : sortPlayers)
        {
            if (i == topNumber-1)
            {
                return topPlayers.getValue()+"";
            }
            i++;
        }
        return LanguageConfigData.papiZeroTopPoints;
    }
    public static String getPlayerTop(OfflinePlayer offlinePlayer)
    {
        int i = 0;
        for ( Map.Entry<String, Integer> topPlayers : TopPlayers.sortPlayers)
        {
            if (Objects.equals(topPlayers.getKey(), offlinePlayer.getName()))
            {
                return i + 1 + "";
            }
            i++;
        }
        return LanguageConfigData.papiZeroPlayerTop;
    }
    public static String getPlayerTop(Player player)
    {
        int i = 0;
        for ( Map.Entry<String, Integer> topPlayers : TopPlayers.sortPlayers)
        {
            if (Objects.equals(topPlayers.getKey(), player.getName()))
            {
                return i + 1 + "";
            }
            i++;
        }
        return LanguageConfigData.papiZeroPlayerTop;
    }
}
