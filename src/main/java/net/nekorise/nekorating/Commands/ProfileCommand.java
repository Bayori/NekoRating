package net.nekorise.nekorating.Commands;

import net.nekorise.nekorating.GUIs.ProfileGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class ProfileCommand implements CommandExecutor
{
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args)
    {
        Player player = (Player) sender;

        Inventory profileInv = new ProfileGUI().getFilledInventory(player);
        player.openInventory(profileInv);
        return false;
    }
}
