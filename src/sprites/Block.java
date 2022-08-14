package sprites;

import biuoop.DrawSurface;
import collisionobject.Collidable;
import game.GameLevel;
import game.Velocity;
import geometry.Point;
import geometry.Rectangle;
import listeners.HitListener;
import notifier.Notifier;


import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * @author sivan Jhirad, ID: 209193481
 * sprites.Block
 */
public class Block extends Notifier implements Collidable, Sprite {
    private static final int HORIZONTAL = 1;
    private static final int VERTICAL = 2;
    private Rectangle rect;
    private Color color;

    /**
     * constructor.
     * @param react react
     * @param color color
     */
    public Block(Rectangle react, java.awt.Color color) {
        this.rect = react;
        this.color = color;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * @return color of ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.getCollisionRectangle().getUpperLeft().getY(),
                (int) this.getCollisionRectangle().getWidth(), (int) this.getCollisionRectangle().getHeight());
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.getCollisionRectangle().getUpperLeft().getY(),
                (int) this.getCollisionRectangle().getWidth(), (int) this.getCollisionRectangle().getHeight());
    }

    @Override
    public void timePassed() {
    }

    /**
     * This method will be in charge of adding the block to the game.
     *
     * @param gameLevel game.Game game
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
    }

    @Override
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.removeCollidable(this);
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity newVelocity = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());
        if (collisionPoint.equalsX(rect.getUpperLeft()) || collisionPoint.equalsX(rect.getUpperRight())) {
            newVelocity = newVelocity.changeVelocityHelp(HORIZONTAL);
        }
        if (collisionPoint.equalsY(rect.getUpperLeft()) || collisionPoint.equalsY(rect.getBottomLeft())) {
            newVelocity = newVelocity.changeVelocityHelp(VERTICAL);
        }
            this.notifyHit(hitter);

        return newVelocity;
    }

    /**
     * Notify all listeners about a hit event.
     * @param hitter ball that hit
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.getHitListeners());
        // Notify all listeners about a hit event:
        for (HitListener hitListener : listeners) {
            hitListener.hitEvent(this, hitter);
        }
    }
}
