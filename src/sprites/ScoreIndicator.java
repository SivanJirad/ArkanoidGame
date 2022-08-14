package sprites;

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Rectangle;
import listeners.Counter;

import java.awt.Color;

/**
 * @author sivan Jhirad, ID: 209193481
 * sprites.ScoreIndicator
 */
public class ScoreIndicator implements Sprite {
    private Rectangle rectangle;
    private Color color;
    private Counter score;
    private String levelName;

    /**
     * constructor.
     * @param rectangle rectangle of score indicator
     * @param color color of score indicator
     * @param score score in begin of the game
     */
    public ScoreIndicator(Rectangle rectangle, Color color, Counter score) {
        this.rectangle = rectangle;
        this.color = color;
        this.score = score;
    }

    /**
     * @param nameLevel name of level
     */
    public void setLevelName(String nameLevel) {
        this.levelName = nameLevel;
    }
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
        (int) rectangle.getWidth(), (int) rectangle.getHeight());
        surface.setColor(Color.black);
        surface.drawText((int) (rectangle.getUpperRight().getX() / 5), (int) (rectangle.getBottomLeft().getY() / 1.2),
                "Lives: " , 15);
        surface.drawText((int) (rectangle.getUpperRight().getX() / 2.2), (int) (rectangle.getBottomLeft().getY() / 1.2),
                "Score: " + score.getValue(), 15);
        surface.drawText((int) (rectangle.getUpperRight().getX() / 1.5), (int) (rectangle.getBottomLeft().getY() / 1.2),
                "Level Name: " + levelName , 15);

    }

    /**
     * add sprite to game.
     * @param gameLevel game
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }

    @Override
    public void timePassed() {

    }

}
