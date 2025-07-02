package com.obijuanbonomi.revitalizedcompass;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.minecraft.text.Text;

public class ClientInit implements ClientModInitializer {

	private static final Logger LOGGER = LoggerFactory.getLogger("RevitalizedCompass");

	@Override
	public void onInitializeClient() {
		LOGGER.info("✅ ClientInit loaded");

		HudRenderCallback.EVENT.register((ctx, tickCounter) -> {

			// 1 · prove callback is running
			LOGGER.info("HUD tick");

			MinecraftClient mc = MinecraftClient.getInstance();
			PlayerEntity player = mc.player;
			if (player == null)
				return;

			boolean hasCompass = player.getMainHandStack().isOf(Items.COMPASS) ||
					player.getOffHandStack().isOf(Items.COMPASS);

			if (!hasCompass) {
				PlayerInventory inv = player.getInventory();
				for (int i = 0; i < inv.size(); i++) {
					if (inv.getStack(i).isOf(Items.COMPASS)) {
						hasCompass = true;
						break;
					}
				}
			}

			// 2 · log result of test
			LOGGER.trace("hasCompass = {}", hasCompass);
			LOGGER.info("hasCompass = {}", hasCompass);
			if (!hasCompass)

				return;

			String pos = String.format("Position: %.1f, %.1f, %.1f",
					player.getX(), player.getY(), player.getZ());

			// 3 · log the text we plan to draw
			LOGGER.trace("Draw '{}'", pos);

			int x = 4; // 4 píxeles desde el borde izquierdo
			int y = 4; // 4 píxeles desde la parte superior
			int padding = 2; // margen interno
			int width = mc.textRenderer.getWidth(pos);
			int height = mc.textRenderer.fontHeight;
			/* --- dibujar fondo gris semitransparente (AARRGGBB) --- */
			int bgColor = 0x88000000; // alfa 0x88 (~53 %), negro
			ctx.fill(x - padding,
					y - padding,
					x + width + padding,
					y + height + padding,
					bgColor);

			/* --- dibujar texto blanco opaco --- */
			ctx.drawTextWithShadow(mc.textRenderer,
					pos,
					x,
					y,
					0xFFFFFFFF); // blanco AA=FF
		});
	}

}
