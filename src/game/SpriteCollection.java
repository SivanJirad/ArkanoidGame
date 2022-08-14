package game;

import biuoop.DrawSurface;
import sprites.Sprite;

import java.util.ArrayList;

/**
 * @author sivan Jhirad, ID: 209193481
 * game.SpriteCollection
 */
public class SpriteCollection {
    private ArrayList<Sprite> spriteCollection;

    /**
     * constructor.
     */
    public SpriteCollection() {
        this.spriteCollection = new ArrayList<>();
    }

    /**
     * add sprite to sprite collection.
     * @param s sprites.Sprite
     */
    public void addSprite(Sprite s) {
        this.spriteCollection.add(s);
    }

    /**
     * remove sprite.
     * @param s sprite
     */
    public void removeSprite(Sprite s) {
        this.spriteCollection.remove(s);

    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        ArrayList<Sprite> tempSprites = new ArrayList<Sprite>(spriteCollection);
        for (Sprite sprite: tempSprites) {
            sprite.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     * @param d DrawSurface
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite: spriteCollection) {
            sprite.drawOn(d);
        }
    }

    /**
     * @return spriteCollection
     */
    public ArrayList<Sprite> getSpriteCollection() {
        return spriteCollection;
    }
}
