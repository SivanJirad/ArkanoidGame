import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import game.AnimationRunner;
import gamelevels.DirectHit;
import gamelevels.GameFlow;
import gamelevels.LevelInformation;
import gamelevels.WideEasy;
import gamelevels.PinkThree;
import gamelevels.FinalFour;
import listeners.Counter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sivan Jhirad, ID: 209193481
 * Ass6Game
 */

public class Ass6Game {

    //gui
    private static final int BOARD_WIDTH = 800;
    private static final int BOARD_HEIGHT = 600;

    /**
     * @param args strings of levels
     */
    public static void main(String[] args) {


       GUI gui = new GUI(" Arkanoid", BOARD_WIDTH, BOARD_HEIGHT);

        AnimationRunner runner = new AnimationRunner(gui, 60, new Sleeper());
        KeyboardSensor keyboardSensor = gui.getKeyboardSensor();
        Counter counterScore  = new Counter(0);

        GameFlow gameFlow = new GameFlow(gui, runner, keyboardSensor, counterScore);

        List<LevelInformation> levels = new ArrayList<>();

        for (String numLevel: args) {
            if (numLevel.equals("1")) {
                levels.add(new DirectHit());
            } else if (numLevel.equals("2")) {
                levels.add(new WideEasy());
            } else if (numLevel.equals("3")) {
                    levels.add(new PinkThree());
            } else if (numLevel.equals("4")) {
                levels.add(new FinalFour());
            }
        }

        if (levels.isEmpty()) {
            levels.add(new DirectHit());
            levels.add(new WideEasy());
            levels.add(new PinkThree());
            levels.add(new FinalFour());
        }

        gameFlow.runLevels(levels);
    }
}
