package net.nekorise.nekorating.GUIs;

import net.nekorise.nekorating.Utils.ConfigStorage.LanguageConfigData;
import net.nekorise.nekorating.Utils.HEX;
import net.nekorise.nekorating.Utils.MySQL;
import net.nekorise.nekorating.Utils.ParsePlaceholders;
import net.nekorise.nekorating.Utils.TopPlayers;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.*;


public class ProfileGUI implements InventoryHolder
{


    @Override
    public @NotNull Inventory getInventory()
    {
        return Bukkit.createInventory(this, 54, LanguageConfigData.profileGuiName);
    }

    public Inventory getFilledInventory(Player player)
    {
        MySQL mySQL = new MySQL();
        Inventory inventory = getInventory();

        // Предмет профиля
        ItemStack profileItem = new ItemStack(LanguageConfigData.profileMaterial);
        ItemMeta profileMeta = profileItem.getItemMeta();
        List<String> profileLore = new ArrayList<>();
        profileMeta.setDisplayName(HEX.ApplyColor(LanguageConfigData.profileName));

        for (String list : LanguageConfigData.profileLore)
        {
            list = ParsePlaceholders.getParsed(list, player, mySQL.getPlayerPoints(player.getName()));
            profileLore.add(HEX.ApplyColor(list));
        }

        profileMeta.setLore(profileLore);
        profileItem.setItemMeta(profileMeta);
        inventory.setItem(19, profileItem);

        // Предметы топ 1-15
        ItemStack otherTopItem = new ItemStack(Material.COPPER_BLOCK);
        ItemMeta otherMeta = otherTopItem.getItemMeta();
        List<String> otherTopLore = new ArrayList<>();
        String otherUsername;
        int[] cell = {12, 13, 14, 15, 16, 21, 22, 23, 24, 25, 30, 31, 32, 33, 34}; // Ячейки, которые надо заполнить

        int topPosition = 0;
        for (int i = 0; i <= 53; i++)
        {
            for (int k : cell) {
                if (i == k)
                {
                    topPosition++;

                    switch (topPosition)
                    {
                        case 1:
                            otherTopItem = new ItemStack(LanguageConfigData.top1Material);
                            break;
                        case 2:
                            otherTopItem = new ItemStack(LanguageConfigData.top2Material);
                            break;
                        case 3:
                            otherTopItem = new ItemStack(LanguageConfigData.top3Material);
                            break;
                        default:
                            otherTopItem = new ItemStack(LanguageConfigData.topOtherMaterial);
                            break;
                    }

                    otherUsername = TopPlayers.getTopPlayerName(topPosition);
                    otherMeta.setDisplayName(HEX.ApplyColor(ParsePlaceholders.getParsed(LanguageConfigData.topOtherName, otherUsername, 0, topPosition)));
                    for (String list : LanguageConfigData.topOtherLore)
                    {
                        otherTopLore.add((HEX.ApplyColor(ParsePlaceholders.getParsed(list, otherUsername, mySQL.getPlayerPoints(otherUsername), topPosition))));
                    }
                    otherMeta.setLore(otherTopLore);
                    otherTopLore.clear();
                    otherTopItem.setItemMeta(otherMeta);
                    inventory.setItem(i, otherTopItem);
                }
            }
        }

        // Рамка снизу
        ItemStack barrierItem = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta barrierMeta = barrierItem.getItemMeta();
        barrierMeta.setDisplayName(" ");
        barrierItem.setItemMeta(barrierMeta);

        for (int i = 45; i < 54; i++)
        {
            inventory.setItem(i, barrierItem);
        }

        // Предмет меню списка
        /*
        ItemStack aboutItem = new ItemStack(LanguageConfigData.toEventListMaterial);
        ItemMeta aboutMeta = aboutItem.getItemMeta();
        List<String> aboutLore = new ArrayList<>();
        aboutMeta.setDisplayName(HEX.ApplyColor(LanguageConfigData.toEventListName));

        for (String list : LanguageConfigData.toEventListLore)
        {
            aboutLore.add(HEX.ApplyColor(list));
        }

        aboutMeta.setLore(aboutLore);
        aboutMeta.addItemFlags(ItemFlag.HIDE_ITEM_SPECIFICS);
        aboutItem.setItemMeta(aboutMeta);
        inventory.setItem(49, aboutItem);
        */

        return inventory;
    }

    public void onInventoryClick(InventoryClickEvent event)
    {
        if (event.getClickedInventory().getHolder() instanceof ProfileGUI)
        {
            event.setCancelled(true);
            // Логика после клика
            /*
            if (event.getRawSlot() == 49)
            {
                Player player = (Player) event.getWhoClicked();
                Inventory inventory = event.getInventory();
                inventory.clear();
                inventory.close();
                player.openInventory(new EventListGUI().getFilledInventory(player));
            }
            */
        }
    }


}
