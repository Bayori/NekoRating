package net.nekorise.nekorating.Utils;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class HookPlaceholderAPI extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "nrating";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Nekorise";
    }

    @Override
    public @NotNull String getVersion() {
        return "0.1";
    }

    @Override
    public @Nullable String onRequest(OfflinePlayer offlinePlayer, @NotNull String params) {

        if (offlinePlayer == null && !offlinePlayer.isOnline())
        {
            return null;
        }
        StringBuilder topNumberBld = new StringBuilder();
        char[] paramsArray = params.toCharArray();

        for (int i = 0; i < params.length(); i++)
        {
            if (Character.isDigit(paramsArray[i]))
            {
                topNumberBld.append(paramsArray[i]);
            }
        }

        int topNumber = 0;
        if (isNumeric(topNumberBld.toString()))
        {
            topNumber = Integer.parseInt(topNumberBld.toString());
            topNumber = Math.abs(topNumber);
        }

        if (params.contains("top_username_"))
        {
            return TopPlayers.getTopPlayerName(topNumber);
        }

        if (params.contains("top_points_"))
        {
            return TopPlayers.getTopPlayerPoints(topNumber);
        }

        if (params.contains("player_points"))
        {
            MySQL mySQL = new MySQL();
            int points = mySQL.getPlayerPoints(offlinePlayer.getName());
            return String.valueOf(points);
        }

        if (params.contains("player_top"))
        {
            return TopPlayers.getPlayerTop(offlinePlayer);
        }

        return null;
    }

    public static void registerHook()
    {
        new HookPlaceholderAPI().register();
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
}
