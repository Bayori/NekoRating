package net.nekorise.nekorating.Utils;

import net.nekorise.nekorating.NekoRating;
import net.nekorise.nekorating.Utils.ConfigStorage.DefaultConfigData;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQL {

    private static String url = "jdbc:mysql://localhost:3306/";
    private static String user = "root";
    private static String password = "";
    private static String schema = "nekorating";
    private static String table = "players";

    private static String schemaTable = schema + "." + table;

    private static Connection connection;

    private static final String errConnectionMessage = "Error. Check connection to the database.";
    private static Plugin plugin = NekoRating.getPlugin();

    public static Connection getConnection()
    {
        if (connection != null)
        {
           return connection;
        }

        try
        {
            // Открываем соединение
            connection = DriverManager.getConnection(url, user, password);
            return connection;
        }
        catch (SQLException ex)
        {
            NekoRating.getPlugin().getLogger().info(errConnectionMessage);
            ex.printStackTrace();
        }
        return null;
    }

    private static void createDB() {

        String querySchema = "CREATE DATABASE IF NOT EXISTS " + schema;
        String queryTable = "CREATE TABLE IF NOT EXISTS " + schemaTable + "(ID INT AUTO_INCREMENT PRIMARY KEY, username varchar(40) NOT NULL, points INT unsigned NOT NULL DEFAULT " + DefaultConfigData.startPoints + ");";

        try
        {
            Statement stmt = getConnection().createStatement();
            stmt.execute(querySchema);
            stmt.execute(queryTable);
            stmt.close();
        }
        catch (SQLException ex)
        {
            NekoRating.getPlugin().getLogger().info(errConnectionMessage + " Disabling plugin...");
            NekoRating.getPlugin().getServer().getPluginManager().disablePlugin(NekoRating.getPlugin());
            ex.printStackTrace();
        }
    }

    private void createNewUser(String username)
    {
        String query = "INSERT INTO " + schemaTable + " (username, points) VALUES ('" + username + "', " + DefaultConfigData.startPoints + ");";
        try
        {
            Statement stmt = getConnection().createStatement();
            stmt.executeUpdate(query);
            stmt.close();
        }
        catch (SQLException ex)
        {
            NekoRating.getPlugin().getLogger().info(errConnectionMessage);
            ex.printStackTrace();
        }
    }

    private boolean checkUserAvailableSuper(String username, boolean createNewUser)
    {
        String query = "SELECT * from " + schemaTable + " WHERE username = '" + username + "';";

        try
        {
            Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next())
            {
                // Игрок есть в бд
                stmt.close();
                rs.close();
                return true;
            }
            else
            {
                // Игрока нет в бд
                if (createNewUser) { createNewUser(username); }
                stmt.close();
                rs.close();
                return false;
            }

        }
        catch (SQLException ex)
        {
            NekoRating.getPlugin().getLogger().info(errConnectionMessage);
            ex.printStackTrace();
        }
        return false;
    }

    public List<String> getAllPlayers()
    {
        String query = "SELECT * from " + schemaTable + ";";
        List<String> playersList = new ArrayList<>();
        try
        {
            Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next())
            {
                playersList.add(rs.getString("username"));
            }

            if (playersList.size() <= 0)
            {
                // Во избежание null
                return List.of("");
            }
            return playersList;
        }
        catch (SQLException ex)
        {
            NekoRating.getPlugin().getLogger().info(errConnectionMessage);
            ex.printStackTrace();
        }
        return List.of("");
    }

    public boolean checkUserAvailable(String username)
    {
        return checkUserAvailableSuper(username, false);
    }
    public boolean checkUserAvailable(String username, boolean createNewUser)
    {
        return checkUserAvailableSuper(username, createNewUser);
    }

    public Integer getPlayerPoints(String username)
    {
        String query = "SELECT * from " + schemaTable + " WHERE username = '" + username + "';";
        if (!checkUserAvailable(username)){ return 0; }
        try
        {
            Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            return rs.getInt(3);


        }
        catch (SQLException ex)
        {
            NekoRating.getPlugin().getLogger().info(errConnectionMessage);
            ex.printStackTrace();
        }
        return 0;
    }

    public boolean setPoints(String username, int operationPoints)
    {
        String query = "UPDATE " + schemaTable + " SET points = " + operationPoints + " WHERE username = '" + username + "';";
        if (!checkUserAvailable(username))
        {
            return false;
        }

        try
        {
            Statement stmt = getConnection().createStatement();
            stmt.executeUpdate(query);
            stmt.close();
            return true;
        }
        catch (SQLException ex)
        {
            NekoRating.getPlugin().getLogger().warning("Error while trying to set points for " + username + " (Possibly lost connection to the database or the value of points has reached the maximum (2 147 483 647))");
        }
        return false;
    }

    public boolean addPoints(String username, int pointsToAdd)
    {
        if (!checkUserAvailable(username)) { return false; }

        int userPoints = getPlayerPoints(username);
        userPoints += Math.abs(pointsToAdd);

        setPoints(username, userPoints);
        return true;
    }

    public boolean takePoints(String username, int pointsToTake)
    {
        if (!checkUserAvailable(username)) { return false; }

        int userPoints = getPlayerPoints(username);
        userPoints -= Math.abs(pointsToTake);

        if (userPoints < 0)
        {
            userPoints = 0;
        }
        setPoints(username, userPoints);
        return true;
    }

    public static void startupLogic()
    {
        if (getConnection() == null)
        {
            // Отключение плагина если не удалось подключиться к MySQL
            plugin.getLogger().info(errConnectionMessage + " Disabling plugin...");
            Bukkit.getPluginManager().disablePlugin(NekoRating.getPlugin());
            return;
        }

        createDB();
    }

    public static void loadData()
    {
        try
        {
            url = "jdbc:mysql://" + DefaultConfigData.sqlServer + ":" + DefaultConfigData.sqlPort + "/";
            user = DefaultConfigData.sqlUser;
            password = DefaultConfigData.sqlPassword;
            schema = DefaultConfigData.sqlSchema;
            table = DefaultConfigData.sqlTable;
            schemaTable = schema + "." + table;

            if (connection != null)
            {
                connection = null;
                startupLogic();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
