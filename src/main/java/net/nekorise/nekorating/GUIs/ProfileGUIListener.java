package net.nekorise.nekorating.GUIs;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class ProfileGUIListener implements Listener
{
    @EventHandler
    public void onGUIClick(InventoryClickEvent event)
    {
        if (event.getClickedInventory() == null)
        {
            return;
        }

        Inventory inventory = event.getInventory();
        if (event.getWhoClicked() instanceof Player)
        {
            if (inventory != null && inventory.getHolder() instanceof ProfileGUI)
            {
                event.setCancelled(true);
                new ProfileGUI().onInventoryClick(event);
            }
        }
    }
}
