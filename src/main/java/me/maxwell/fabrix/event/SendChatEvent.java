package me.maxwell.fabrix.event;

import lombok.Getter;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author lazy
 * @since 5/26/2019 at 2:30 AM
 */
public class SendChatEvent {
    @Getter
    private String message;

    @Getter
    private CallbackInfo callbackInfo;

    public SendChatEvent(String message, CallbackInfo callbackInfo) {
        this.message = message;
        this.callbackInfo = callbackInfo;
    }
}
