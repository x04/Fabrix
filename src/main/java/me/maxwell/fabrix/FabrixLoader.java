package me.maxwell.fabrix;

import net.fabricmc.api.ModInitializer;

/**
 * @author lazy
 * @since 5/26/2019 at 12:55 AM
 */
public class FabrixLoader implements ModInitializer {

	@Override
	public void onInitialize() { Fabrix.INSTANCE.start(); }
}
