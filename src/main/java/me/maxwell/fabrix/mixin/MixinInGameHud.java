package me.maxwell.fabrix.mixin;

import me.maxwell.fabrix.Fabrix;
import me.maxwell.fabrix.event.RenderEvent;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author lazy
 * @since 5/26/2019 at 12:57 AM
 */
@Mixin(InGameHud.class)
public class MixinInGameHud {

    @Inject(method = "draw", at = @At("RETURN"))
    private void draw(CallbackInfo callbackInfo) {
        Fabrix.INSTANCE.getEventBus().post(new RenderEvent());
    }
}
