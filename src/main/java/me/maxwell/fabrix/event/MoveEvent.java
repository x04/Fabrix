package me.maxwell.fabrix.event;

import lombok.Getter;

/**
 * @author lazy
 * @since 5/26/2019 at 1:15 AM
 */
public class MoveEvent {
    @Getter
    private State state;

    public MoveEvent(State state) {
        this.state = state;
    }

    public enum State {
        PRE, POST;
    }
}
