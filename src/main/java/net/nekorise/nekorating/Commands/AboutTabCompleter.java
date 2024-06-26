package net.nekorise.nekorating.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AboutTabCompleter implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args)
    {
        if (args.length == 1 && sender.hasPermission("nekorating.reload"))
        {
            return List.of("reload");
        }
        else if (args.length >= 2)
        {
            return List.of("");
        }
        return null;
    }
}
