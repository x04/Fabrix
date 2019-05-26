package me.maxwell.fabrix.event;

/**
 * @author lazy
 * @since 5/26/2019 at 1:15 AM
 */
public class MoveEvent {
    private State state;

    public MoveEvent(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public enum State {
        PRE, POST;
    }
}
