package gamelevels;

import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.AnimationRunner;
import game.EndScreen;
import game.GameLevel;
import listeners.Counter;

import java.util.List;

/**
 * @author sivan Jhirad, ID: 209193481
 * game.GameFlow
 */
public class GameFlow {

    private GUI gui;
    private AnimationRunner runner;
    private KeyboardSensor keyboardSensor;
    private Counter counterScore;

    /**
     * constructor.
     * @param gui the board of the game
     * @param runner Animation runner
     * @param keyboardSensor keyboard sensor
     * @param counterScore counter score
     */
    public GameFlow(GUI gui, AnimationRunner runner, KeyboardSensor keyboardSensor, Counter counterScore) {
        this.gui = gui;
        this.runner = runner;
        this.keyboardSensor = keyboardSensor;
        this.counterScore = counterScore;
    }

    /**
     * Running the levels one after the other.
     * @param levels A list of game levels in the order entered by the user or in the default order.
     */
    public void runLevels(List<LevelInformation> levels) {

        int indexInList = 0;
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(this.gui, levelInfo, this.keyboardSensor, this.runner, this.counterScore);
            level.initialize();
            while (level.getCountOfBalls() != 0 && level.getCountOfBlock()  != 0) {
                level.run();
            }
            indexInList++;
            level.endLevel();
            if (level.getCountOfBalls() == 0) {
                this.runner.run(new EndScreen(keyboardSensor, " Game Over. Your score is "
                        + counterScore.getValue()));
                gui.close();
            }
            if (indexInList == levels.size() && level.getCountOfBlock() == 0) {
                this.runner.run(new EndScreen(keyboardSensor, " You Win! Your score is "
                        + counterScore.getValue()));
                gui.close();
            }
        }
    }
}
