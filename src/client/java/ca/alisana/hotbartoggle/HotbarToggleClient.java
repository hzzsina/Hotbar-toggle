package ca.alisana.hotbartoggle;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;

/**
 * Makes the 1 key toggle between hotbar slots 1 and 2.
 */
public final class HotbarToggleClient implements ClientModInitializer {
    private boolean slotOneKeyWasDown = false;
    private int pendingSlot = -1;

    @Override
    public void onInitializeClient() {
        ClientTickEvents.START_CLIENT_TICK.register(this::detectKeyPress);
        ClientTickEvents.END_CLIENT_TICK.register(this::applySelection);
    }

    private void detectKeyPress(Minecraft client) {
        boolean slotOneKeyIsDown =
                client.options.keyHotbarSlots[0].isDown();

        if (slotOneKeyIsDown
                && !slotOneKeyWasDown
                && client.player != null) {

            int selectedSlot =
                    client.player.getInventory().getSelectedSlot();

            if (selectedSlot == 0) {
                // Hotbar slot 1 is selected, so switch to slot 2.
                pendingSlot = 1;
            } else if (selectedSlot == 1) {
                // Hotbar slot 2 is selected, so switch to slot 1.
                pendingSlot = 0;
            }
        }

        // Prevent holding the key from repeatedly toggling the selection.
        slotOneKeyWasDown = slotOneKeyIsDown;
    }

    private void applySelection(Minecraft client) {
        if (pendingSlot == -1) {
            return;
        }

        if (client.player != null) {
            client.player
                    .getInventory()
                    .setSelectedSlot(pendingSlot);
        }

        pendingSlot = -1;
    }
}
