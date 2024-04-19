package net.nekorise.nekorating.Utils;

import org.bukkit.entity.Player;

public class ParsePlaceholders
{

    public static String getParsed(String textToParse)
    {
        return parse(textToParse, "", 0, null, 0);
    }
    public static String getParsed(String textToParse, String playerUsername)
    {
        return parse(textToParse, playerUsername, 0, null, 0);
    }

    public static String getParsed(String textToParse, int points)
    {
        return parse(textToParse, "", points, null, 0);
    }

    public static String getParsed(String textToParse, String playerUsername, int points)
    {
        return parse(textToParse, playerUsername, points, null, 0);
    }
    public static String getParsed(String textToParse, String playerUsername, int points, int place)
    {
        return parse(textToParse, playerUsername, points, null, place);
    }

    public static String getParsed(String textToParse, Player player, int points)
    {
        return parse(textToParse, null, points, player, 0);
    }

    private static String parse(String textToParse, String playerUsername, int points, Player player, int place)
    {
        if (playerUsername != null)
        {
            textToParse = textToParse.replace("{player}", playerUsername);
        } else if (player != null)
        {
            textToParse = textToParse.replace("{player}", player.getName());
        }

        textToParse = textToParse.replace("{points}", String.valueOf(points));
        if (player != null)
        {
            textToParse = textToParse.replace("{top_place}", TopPlayers.getPlayerTop(player));
        }
        else
        {
            textToParse = textToParse.replace("{top_place}", String.valueOf(place));
        }



        return textToParse;
    }
}
