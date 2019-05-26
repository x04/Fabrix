package me.maxwell.fabrix.event;

import lombok.Getter;

/**
 * @author lazy
 * @since 5/26/2019 at 2:30 AM
 */
public class SendChatEvent {
    @Getter
    private String message;

    public SendChatEvent(String message) {
        this.message = message;
    }
}
