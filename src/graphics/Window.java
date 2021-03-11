package graphics;

import java.awt.GridLayout;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import logic.DataOfSquare;
import logic.KeyboardListener;
import logic.ThreadsController;
import pojo.Tuple;

public class Window extends JFrame{
	private static final long serialVersionUID = -2542001418764869760L;
	private static ArrayList<ArrayList<DataOfSquare>> Grid;
	private static int width = 20;
	private static int height = 20;

	public Window(){
		
		
		// Creates the arraylist that'll contain the threads
		Grid = new ArrayList<>();
		ArrayList<DataOfSquare> data;
		
		// Creates Threads and its data and adds it to the arrayList
		for(int i=0;i<width;i++){
			data= new ArrayList<>();
			for(int j=0;j<height;j++){
				DataOfSquare c = new DataOfSquare(2);
				data.add(c);
			}
			Grid.add(data);
		}
		
		// Setting up the layout of the panel
		getContentPane().setLayout(new GridLayout(20,20,0,0));
		
		// Start & pauses all threads, then adds every square of each thread to the panel
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				getContentPane().add(Grid.get(i).get(j).getSquare());
			}
		}
		
		// initial position of the snake
		Tuple position = new Tuple(10,10);
		// passing this value to the controller
		ThreadsController c = new ThreadsController(position);
		//Let's start the game now..
		c.start();

		// Links the window to the keyboardlistenner.
		this.addKeyListener(new KeyboardListener());
		
	}

	public static ArrayList<ArrayList<DataOfSquare>> getGrid() {
		return Grid;
	}

	public static void setGrid(ArrayList<ArrayList<DataOfSquare>> grid) {
		Grid = grid;
	}

	public static int getWindowWidth() {
		return width;
	}

	public static void setWidth(int width) {
		Window.width = width;
	}

	public static int getWindowHeight() {
		return height;
	}

	public static void setHeight(int height) {
		Window.height = height;
	}
}
