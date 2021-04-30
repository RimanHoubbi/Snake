package logic;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardListener extends KeyAdapter {

    public void keyPressed(KeyEvent e) {
        // &begin[Snake::Move]
        switch (e.getKeyCode()) {
            case 39:    // -> Right
                //if it's not the opposite Direction
                if (ThreadsController.directionSnake != Direction.LEFT)
                    ThreadsController.directionSnake = Direction.RIGHT;
                break;
            case 38:    // -> Top
                if (ThreadsController.directionSnake != Direction.BOTTOM)
                    ThreadsController.directionSnake = Direction.TOP;
                break;

            case 37:    // -> Left
                if (ThreadsController.directionSnake != Direction.RIGHT)
                    ThreadsController.directionSnake = Direction.LEFT;
                break;

            case 40:    // -> Bottom
                if (ThreadsController.directionSnake != Direction.TOP)
                    ThreadsController.directionSnake = Direction.BOTTOM;
                break;

            default:
                break;
        }
        // &end[Snake::Move]
    }

}
