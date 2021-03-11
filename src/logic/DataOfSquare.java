package logic;

import java.util.ArrayList;
import java.awt.Color;

import graphics.SquarePanel;

public class DataOfSquare {
	//ArrayList that'll contain the colors
	private final ArrayList<Color> C = new ArrayList<>();
	private int color; //2: snake , 1: food, 0:empty
	private final SquarePanel square;

	public DataOfSquare(int col){
		
		//Lets add the color to the arrayList
		C.add(Color.darkGray);//0
		C.add(Color.BLUE);    //1
		C.add(Color.white);   //2
		color=col;
		square = new SquarePanel(C.get(color));
	}

	public void lightMeUp(int c){
		square.ChangeColor(C.get(c));
	}

	public SquarePanel getSquare() {
		return square;
	}
}
