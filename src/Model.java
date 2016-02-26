import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;


public class Model {
	
	View view;
	static ArrayList<Figure> allFigures;
	
	
	public Model(View view){
		this.view= view;
		allFigures = new ArrayList<Figure>();
		
		
	}

	public void addFigure(Figure F){
		try{
			F=(Eraser) F;
		}catch(ClassCastException c){
			allFigures.add(F);
		}

		
		
	}
	
	public void drawFigures(Graphics g){ 
		view.update(g);
		view.paint(g);
		for(Figure F:Model.allFigures)
			F.draw(g);


		

			}


}
