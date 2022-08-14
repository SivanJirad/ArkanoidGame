package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author sivan Jhirad, ID: 209193481
 * game.KeyPressStoppableAnimation
 * decorator-class that will wrap an existing animation and add a "waiting-for-key" behavior to it.
 */
public abstract class KeyPressStoppableAnimation implements Animation {


    private KeyboardSensor keyboard;
    private boolean stop;
    private String key;
    private boolean isAlreadyPressed = true;

    /**
     * @param keyboardSensor keyboard sensor
     * @param key identification key for performing the operation
     */
    public KeyPressStoppableAnimation(KeyboardSensor keyboardSensor, String key) {
        this.keyboard = keyboardSensor;
        this.key = key;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (!this.keyboard.isPressed(key)) {
            this.isAlreadyPressed = false;
        }
        if (!isAlreadyPressed && this.keyboard.isPressed(key)) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop; }
    }

