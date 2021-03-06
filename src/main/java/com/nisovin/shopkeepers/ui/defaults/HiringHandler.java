package com.nisovin.shopkeepers.ui.defaults;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.InventoryView;

import com.nisovin.shopkeepers.Settings;
import com.nisovin.shopkeepers.api.ShopkeepersPlugin;
import com.nisovin.shopkeepers.shopkeeper.AbstractShopkeeper;
import com.nisovin.shopkeepers.ui.AbstractUIType;
import com.nisovin.shopkeepers.ui.AbstractShopkeeperUIHandler;
import com.nisovin.shopkeepers.util.Log;
import com.nisovin.shopkeepers.util.PermissionUtils;
import com.nisovin.shopkeepers.util.TextUtils;

public abstract class HiringHandler extends AbstractShopkeeperUIHandler {

	protected HiringHandler(AbstractUIType uiType, AbstractShopkeeper shopkeeper) {
		super(uiType, shopkeeper);
	}

	@Override
	protected boolean canOpen(Player player) {
		assert player != null;
		// Check for hire permission:
		if (!PermissionUtils.hasPermission(player, ShopkeepersPlugin.HIRE_PERMISSION)) {
			Log.debug(() -> "Blocked hire window opening for " + player.getName() + ": Missing hire permission.");
			TextUtils.sendMessage(player, Settings.msgMissingHirePerm);
			return false;
		}
		return true;
	}

	@Override
	protected boolean isWindow(InventoryView view) {
		return view != null && view.getTitle().equals(Settings.forHireTitle);
	}

	@Override
	protected void onInventoryClose(Player player, InventoryCloseEvent closeEvent) {
		// Nothing to do by default.
	}

	@Override
	protected void onInventoryClickEarly(InventoryClickEvent event, Player player) {
		assert event != null && player != null;
		event.setCancelled(true);
	}

	@Override
	protected void onInventoryDragEarly(InventoryDragEvent event, Player player) {
		assert event != null && player != null;
		event.setCancelled(true);
	}
}
