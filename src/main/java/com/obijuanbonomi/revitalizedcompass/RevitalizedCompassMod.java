package com.obijuanbonomi.revitalizedcompass;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RevitalizedCompassMod implements ModInitializer {
	public static final String MOD_ID = "revitalized_compass";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Revitalized Compass cargado (lado común)");
	}
}
