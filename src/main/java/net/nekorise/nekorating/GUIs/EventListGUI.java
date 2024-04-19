package net.nekorise.nekorating.GUIs;

import net.nekorise.nekorating.Utils.ConfigStorage.RewardConfigData;
import net.nekorise.nekorating.Utils.HEX;
import net.nekorise.nekorating.Utils.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

// Этот gui на данный момент может быть создан в игре, но не используюется.
public class EventListGUI implements InventoryHolder
{
    @Override
    public @NotNull Inventory getInventory()
    {
        return Bukkit.createInventory(this, 36, "Где взять очки?");
    }

    public Inventory getFilledInventory(Player player)
    {
        MySQL mySQL = new MySQL();
        Inventory inventory = getInventory();
        List<ItemStack> itemList = new ArrayList<>();

        // Предмет профиля
        ItemStack profileItem = new ItemStack(Material.CONDUIT);
        ItemMeta profileMeta = profileItem.getItemMeta();
        List<String> profileLore = new ArrayList<>();
        profileMeta.setDisplayName(HEX.ApplyColor("&fТвоя статистика"));
        profileLore.add(HEX.ApplyColor("&fНик: " + player.getName()));
        profileLore.add(HEX.ApplyColor("&fОчки: " + mySQL.getPlayerPoints(player.getName())));
        profileMeta.setLore(profileLore);
        profileItem.setItemMeta(profileMeta);
        //inventory.setItem(19, profileItem);

        // Предмет ивента за килл мобов
        ItemStack mobKillItem = new ItemStack(Material.ZOMBIE_HEAD);
        ItemMeta mobKillMeta = mobKillItem.getItemMeta();

        mobKillMeta.setDisplayName(HEX.ApplyColor("Убийство мобов"));
        List<String> mobKillLore = new ArrayList<>();
        mobKillLore.add(HEX.ApplyColor("&aЗа убийство мобов ты будешь лутать особое кол-во очков"));
        mobKillMeta.setLore(mobKillLore);
        mobKillMeta.addEnchant(Enchantment.SWEEPING_EDGE, 1, false);
        mobKillMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        mobKillItem.setItemMeta(mobKillMeta);

        itemList.add(mobKillItem);


        // Предмет ивента за трейды
        ItemStack tradeItem = new ItemStack(Material.EMERALD);
        ItemMeta tradeMeta = tradeItem.getItemMeta();

        tradeMeta.setDisplayName(HEX.ApplyColor("Торгование с житаками"));
        List<String> tradeLore = new ArrayList<>();
        tradeLore.add(HEX.ApplyColor("&aЗа каждую сделку ты лутаешь " + RewardConfigData.tradesReward + " очков"));
        tradeMeta.setLore(tradeLore);
        tradeMeta.addEnchant(Enchantment.SWEEPING_EDGE, 1, false);
        tradeMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        tradeItem.setItemMeta(tradeMeta);

        itemList.add(tradeItem);

        // Предмет ивента за тотем
        ItemStack totemItem = new ItemStack(Material.TOTEM_OF_UNDYING);
        ItemMeta totemMeta = totemItem.getItemMeta();

        totemMeta.setDisplayName(HEX.ApplyColor("Использование тотема"));
        List<String> totemLore = new ArrayList<>();
        totemLore.add(HEX.ApplyColor("&aКогда тотем рушится, то ты лутаешь " + RewardConfigData.totemUseReward + " очков"));
        totemMeta.setLore(totemLore);
        totemMeta.addEnchant(Enchantment.SWEEPING_EDGE, 1, false);
        totemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        totemItem.setItemMeta(totemMeta);

        itemList.add(totemItem);

        // Предмет ивента за начало рейда
        ItemStack raidTriggerItem = new ItemStack(Material.BELL);
        ItemMeta raidTriggerMeta = raidTriggerItem.getItemMeta();

        raidTriggerMeta.setDisplayName(HEX.ApplyColor("Провокация рейда"));
        List<String> raidTriggerLore = new ArrayList<>();
        raidTriggerLore.add(HEX.ApplyColor("&aЕсли ты начинаешь рейд, то лутаешь " + RewardConfigData.raidTriggerReward + " очков"));
        raidTriggerMeta.setLore(raidTriggerLore);

        raidTriggerMeta.addEnchant(Enchantment.SWEEPING_EDGE, 1, false);
        raidTriggerMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        raidTriggerItem.setItemMeta(raidTriggerMeta);

        itemList.add(raidTriggerItem);

        // Предмет ивента за победу в рейде
        ItemStack raidWinItem = new ItemStack(Material.CROSSBOW);
        ItemMeta raidWinMeta = raidWinItem.getItemMeta();

        raidWinMeta.setDisplayName(HEX.ApplyColor("Победа в рейде"));
        List<String> raidWinLore = new ArrayList<>();
        raidWinLore.add(HEX.ApplyColor("&aВсе победители рейда лутают " + RewardConfigData.raidWinReward + " очков"));
        raidWinMeta.setLore(raidWinLore);

        raidWinMeta.addEnchant(Enchantment.SWEEPING_EDGE, 1, false);
        raidWinMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        raidWinItem.setItemMeta(raidWinMeta);

        itemList.add(raidWinItem);

        // Предмет ивента за победу в рейде
        ItemStack enchantItem = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta enchantMeta = enchantItem.getItemMeta();

        enchantMeta.setDisplayName(HEX.ApplyColor("Зачарование предметов"));
        List<String> enchantLore = new ArrayList<>();
        enchantLore.add(HEX.ApplyColor("&aЗа юзание енчант тейбла ты лутанёшь " + RewardConfigData.enchantmentLvl1Reward + ", " + RewardConfigData.enchantmentLvl2Reward + ", " + RewardConfigData.enchantmentLvl3Reward + " очков"));
        enchantMeta.setLore(enchantLore);

        enchantMeta.addEnchant(Enchantment.SWEEPING_EDGE, 1, false);
        enchantMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        enchantItem.setItemMeta(enchantMeta);

        itemList.add(enchantItem);


        // Предмет ивента за достижения
        ItemStack achievementItem = new ItemStack(Material.DRAGON_EGG);
        ItemMeta achievementMeta = achievementItem.getItemMeta();

        achievementMeta.setDisplayName(HEX.ApplyColor("Получение ачивок"));
        List<String> achievementLore = new ArrayList<>();
        achievementLore.add(HEX.ApplyColor("&aЗа каждую ачивку ты получишь " + RewardConfigData.achievementReward + " очков"));
        achievementMeta.setLore(achievementLore);

        achievementMeta.addEnchant(Enchantment.SWEEPING_EDGE, 1, false);
        achievementMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        achievementItem.setItemMeta(achievementMeta);

        itemList.add(achievementItem);


        for (int i = 0; i < itemList.size(); i++)
        {
            inventory.setItem(i+10, itemList.get(i));
        }

        // Рамка снизу
        ItemStack barrierItem = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta barrierMeta = barrierItem.getItemMeta();
        barrierMeta.setDisplayName(" ");
        barrierItem.setItemMeta(barrierMeta);

        for (int i = 27; i < 36; i++)
        {
            inventory.setItem(i, barrierItem);
        }

        // Предмет меню топа
        ItemStack aboutItem = new ItemStack(Material.MOJANG_BANNER_PATTERN);
        ItemMeta aboutMeta = aboutItem.getItemMeta();
        List<String> aboutLore = new ArrayList<>();
        aboutMeta.setDisplayName(HEX.ApplyColor("&fТоп игроков"));
        aboutLore.add(HEX.ApplyColor("&fНажми шоб перейти к топу игроков"));
        aboutMeta.setLore(aboutLore);
        aboutMeta.addItemFlags(ItemFlag.HIDE_ITEM_SPECIFICS);
        aboutItem.setItemMeta(aboutMeta);
        inventory.setItem(31, aboutItem);

        return inventory;
    }
    public void onInventoryClick(InventoryClickEvent event)
    {
        if (event.getClickedInventory().getHolder() instanceof EventListGUI)
        {
            event.setCancelled(true);

            // Логика после клика
            if (event.getRawSlot() == 31)
            {
                Player player = (Player) event.getWhoClicked();
                Inventory inventory = event.getInventory();
                inventory.clear();
                inventory.close();
                player.openInventory(new ProfileGUI().getFilledInventory(player));
            }
        }
    }
}
