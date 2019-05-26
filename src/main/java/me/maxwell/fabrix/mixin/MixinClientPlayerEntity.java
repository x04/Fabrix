package me.maxwell.fabrix.mixin;

import me.maxwell.fabrix.Fabrix;
import me.maxwell.fabrix.event.MoveEvent;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author lazy
 * @since 5/26/2019 at 12:57 AM
 */
@Mixin(ClientPlayerEntity.class)
public class MixinClientPlayerEntity {

    @Inject(method = "move", at = @At("HEAD"))
    private void preMove(CallbackInfo callbackInfo) {
        Fabrix.INSTANCE.EVENT_BUS.post(new MoveEvent(MoveEvent.State.PRE));
    }

    @Inject(method = "move", at = @At("RETURN"))
    private void postMove(CallbackInfo callbackInfo) {
        Fabrix.INSTANCE.EVENT_BUS.post(new MoveEvent(MoveEvent.State.POST));
    }
}