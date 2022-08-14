package game;

import biuoop.DrawSurface;

/**
 * @author sivan Jhirad, ID: 209193481
 * game.Animation
 */
public interface Animation {

    /**
     * Running a single action.
     * the logic from the previous run method goes here.
     * @param d d
     */
    void doOneFrame(DrawSurface d);

    /**
     * @return Should the animated run stop
     */
    boolean shouldStop();
}

