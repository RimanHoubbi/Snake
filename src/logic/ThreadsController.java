package logic;

import java.util.ArrayList;

import pojo.Tuple;
import graphics.Window;

//Controls all the game logic .. most important class in this project.
public class ThreadsController extends Thread {
    private final ArrayList<ArrayList<DataOfSquare>> Squares;
    private final Tuple headSnakePos;
    private int sizeSnake = 4;
    private final long delay = 75;
    public static Direction directionSnake;
    private final ArrayList<Tuple> positions = new ArrayList<>();
    private Tuple foodPosition; // &line[Food]
    private Tuple poisonPosition;  // &line[Poison]
    private Tuple mildPoisonPosition;  // &line[MildPoison]

    //Constructor of ControllerThread
    public ThreadsController(Tuple positionDepart) {
        //Get all the threads
        Squares = Window.getGrid(); //&line[Playing_Area]

        // &begin[Position]
        headSnakePos = new Tuple(positionDepart.x, positionDepart.y);
        directionSnake = Direction.RIGHT;

        //!!! Pointer !!!!
        Tuple headPos = new Tuple(headSnakePos.getX(), headSnakePos.getY());
        positions.add(headPos);
        // &end[Position]

        // &begin[Food]
        foodPosition = new Tuple(Window.getWindowHeight() - 1, Window.getWindowWidth() - 1);
        spawnFood(foodPosition);//&line[Spawn]
        // &end[Food]

        // &begin[Poison]
        poisonPosition = new Tuple(Window.getWindowHeight() - 3, Window.getWindowWidth() - 3);
        spawnFood(poisonPosition);  // &line[Spawn]
        // &end[Poison]

        // &begin[MildPoison]
        poisonPosition = new Tuple(Window.getWindowHeight() - 5, Window.getWindowWidth() - 5);
        spawnFood(poisonPosition);  // &line[Spawn]
        // &end[MildPoison]
    }

    //Important part :
    public void run() {
        while (true) {
            moveInternal(directionSnake);
            checkCollision();
            moveExternal();
            deleteTail();
            pause();
        }
    }

    //delay between each move of the snake
    // &begin[GameState]
    private void pause() {
        try {
            sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    // &end[GameState]

    //Checking if the snake bites itself or is eating
    private void checkCollision() {
        Tuple posCritique = positions.get(positions.size() - 1);
        // &begin[Collision]
        for (int i = 0; i <= positions.size() - 2; i++) {
            boolean biteItself = posCritique.getX() == positions.get(i).getX() && posCritique.getY() == positions.get(i).getY();
            if (biteItself) {
                stopTheGame("Collision");
            }
        }
        // &end[Collision]

        // &begin[Food]
        boolean eatingFood = posCritique.getX() == foodPosition.y && posCritique.getY() == foodPosition.x;
        if (eatingFood) {
            System.out.println("Yummy!");
            sizeSnake = sizeSnake + 1;
            foodPosition = getTileNotInSnake();

            spawnFood(foodPosition); // &line[Spawn]
        }
        // &end[Food]


        // &begin[Poison]
        boolean eatingPoison = posCritique.getX() == poisonPosition.y && posCritique.getY() == poisonPosition.x;
        if (eatingPoison) {
            System.out.println("Ouch");
            sizeSnake = sizeSnake - 3;
            poisonPosition = getTileNotInSnake();

            spawnPoison(poisonPosition);  // &line[Spawn]
        }
        // &end[Poison]

        // &begin[MildPoison]
        boolean eatingMildPoison = posCritique.getX() == mildPoisonPosition.y && posCritique.getY() == mildPoisonPosition.x;
        if (eatingMildPoison) {
            System.out.println("Mild Poison eaten");
            sizeSnake = sizeSnake - 1;
            mildPoisonPosition = getTileNotInSnake();
            spawnMildPoison(mildPoisonPosition);  // &line[Spawn]
        }
        // &end[MildPoison]
    }

    //Stops The Game
    // &begin[Collision]
    private void stopTheGame(String s) {
        System.out.println(s + "\n");
        while (true) {
            pause();
        }
    }
    // &end[Collision]


    //Put food in a position and displays it
    // &begin[Spawn]
    private void spawnFood(Tuple foodPositionIn) {
        Squares.get(foodPositionIn.x).get(foodPositionIn.y).lightMeUp(SquareToLightUp.FOOD);
    }
    private void spawnPoison(Tuple poisonPositionIn) {
        Squares.get(poisonPositionIn.x).get(poisonPositionIn.y).lightMeUp(SquareToLightUp.POISON);
    }
    private void spawnMildPoison(Tuple mildPoisonPositionIn) {
        Squares.get(mildPoisonPositionIn.x).get(mildPoisonPositionIn.y).lightMeUp(SquareToLightUp.MILD_POISON);
    }
    // &end[Spawn]


    // &begin[Blank]
    //return a position not occupied by the snake
    private Tuple getTileNotInSnake() {
        Tuple p;
        int ranX = (int) (Math.random() * 19);
        int ranY = (int) (Math.random() * 19);
        p = new Tuple(ranX, ranY);
        for (int i = 0; i <= positions.size() - 1; i++) {
            if (p.getY() == positions.get(i).getX() && p.getX() == positions.get(i).getY()) {
                ranX = (int) (Math.random() * 19);
                ranY = (int) (Math.random() * 19);
                p = new Tuple(ranX, ranY);
                i = 0;
            }
        }
        return p;
    }
    // &end[Blank]

    /**
     * Moves the head of the snake and refreshes the positions in the arraylist
     * 1:right 2:left 3:top 4:bottom 0:nothing
     **/
    // &begin[Move, Position]
    private void moveInternal(Direction dir) {
        switch (dir) {
            case DOWN:
                headSnakePos.ChangeData(headSnakePos.x, (headSnakePos.y + 1) % 20);
                positions.add(new Tuple(headSnakePos.x, headSnakePos.y));
                break;
            case UP:
                if (headSnakePos.y - 1 < 0) {
                    headSnakePos.ChangeData(headSnakePos.x, 19);
                } else {
                    headSnakePos.ChangeData(headSnakePos.x, Math.abs(headSnakePos.y - 1) % 20);
                }
                positions.add(new Tuple(headSnakePos.x, headSnakePos.y));
                break;
            case LEFT:
                if (headSnakePos.x - 1 < 0) {
                    headSnakePos.ChangeData(19, headSnakePos.y);
                } else {
                    headSnakePos.ChangeData(Math.abs(headSnakePos.x - 1) % 20, headSnakePos.y);
                }
                positions.add(new Tuple(headSnakePos.x, headSnakePos.y));

                break;
            case RIGHT:
                headSnakePos.ChangeData(Math.abs(headSnakePos.x + 1) % 20, headSnakePos.y);
                positions.add(new Tuple(headSnakePos.x, headSnakePos.y));
                break;
        }
    }
    // &end[Position]


    /**
     * Refresh the squares that needs to be
     **/
    private void moveExternal() {
        for (Tuple t : positions) {
            int y = t.getX();
            int x = t.getY();
            Squares.get(x).get(y).lightMeUp(SquareToLightUp.SNAKE);

        }
    }
    // &end[Move]

    /**
     * Refreshes the tail of the snake, by removing the superfluous data
     * in positions arraylist and refreshing the display of the things that is removed
     **/
    // &begin[Tail]
    private void deleteTail() {
        int cmpt = sizeSnake;
        for (int i = positions.size() - 1; i >= 0; i--) {
            if (cmpt == 0) {
                Tuple t = positions.get(i);
                Squares.get(t.y).get(t.x).lightMeUp(SquareToLightUp.BACK_GROUND); // &line[Update]
            } else {
                cmpt--;
            }
        }
        cmpt = sizeSnake;
        for (int i = positions.size() - 1; i >= 0; i--) {
            if (cmpt == 0) {
                positions.remove(i);
            } else {
                cmpt--;
            }
        }
    }
    // &end[Tail]

}

