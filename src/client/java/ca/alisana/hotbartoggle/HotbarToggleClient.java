package ca.alisana.hotbartoggle;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;

/**
 * Changes the normal hotbar-slot-1 key into a toggle between slots 1 and 2.
 */
public final class HotbarToggleClient implements ClientModInitializer {
	private boolean slotOneKeyWasDown;
	private int pendingSlot = -1;

	@Override
	public void onInitializeClient() {
		ClientTickEvents.START_CLIENT_TICK.register(this::readSlotOneKey);
		ClientTickEvents.END_CLIENT_TICK.register(this::applyToggle);
	}

	private void readSlotOneKey(Minecraft client) {
		boolean slotOneKeyIsDown = client.options.keyHotbarSlots[0].isDown();

		// Act only on the key-down edge, so holding 1 does not toggle every tick.
		if (slotOneKeyIsDown && !slotOneKeyWasDown && client.player != null && client.screen == null) {
			int selectedSlot = client.player.getInventory().getSelectedSlot();
			if (selectedSlot == 0) {
				pendingSlot = 1;
			} else if (selectedSlot == 1) {
				pendingSlot = 0;
			}
		}

		slotOneKeyWasDown = slotOneKeyIsDown;
	}

	private void applyToggle(Minecraft client) {
		if (pendingSlot < 0) {
			return;
		}

		if (client.player != null && client.screen == null) {
			client.player.getInventory().setSelectedSlot(pendingSlot);
		}
		pendingSlot = -1;
	}
}
