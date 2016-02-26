//import java.awt.Graphics;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;




class Dot implements Serializable{
	
	int x,y;
	public Dot(int x, int y){
		this.x=x;
		this.y=y;
	
}}


abstract class Figure implements FigureInterface, Serializable {
	Dot d1,d2;
	int x,y,m,k,height,width;
	ArrayList<Figure> fList;

	
	public Figure(Dot d1, Dot d2){
	
	this.d1 = d1;
	this.d2 = d2;
		
	}
	
	public Figure(Dot d1){
		
	this.d1 = d1;
	}
	
	public void getCoordinates(Dot d11,Dot d22){
		x= d11.x;
		y= d11.y;
		m= d22.x;
		k= d22.y;
		
		
	}
	
	public void centerCoordinates(){
		this.x= x-(width/2);
		this.y= y-(height/2);
	}

	public abstract void draw(Graphics g);

	@Override
	public boolean isInside(Dot d){
		
		if(((this.x+(this.width))>=d.x-10)&&((this.x)<=d.x+10)&&((this.y)<=d.y+10)&&((this.y+(this.height))>=d.y-10))
			return true;
		else
			return false;
		
	}


}

class Line extends Figure{

	
	public Line(Dot d1, Dot d2) {
		super(d1, d2);
	}
	public void draw(Graphics g){
		getCoordinates(d1,d2);
		height = 2*(Math.abs(y-k));
		width = 2*(Math.abs(x-m));
		g.drawLine(x, y, m, k);
		centerCoordinates();
		
	}

}

class Rectangle extends Figure {

	public Rectangle(Dot d1, Dot d2) {
		super(d1, d2);
	}

	public void draw(Graphics g){
		getCoordinates(d1,d2);
		width= 2*Math.abs(m-x);
		height= 2*Math.abs(k-y);
		centerCoordinates();
		g.drawRect(x, y,width,height);
		
	}


}

class Square extends Rectangle {

	public Square(Dot d1, Dot d2) {
		super(d1, d2);
	}
	public void draw(Graphics g){
		getCoordinates(d1,d2);
		height =2*Math.max(Math.abs((m-x)),Math.abs((k-y)));
		width = height;
		centerCoordinates();
		g.drawRect(x, y, width, height);
	}
	

}

class Oval extends Figure {

	public Oval(Dot d1, Dot d2) {
		super(d1, d2);
	}
	public void draw(Graphics g){
		getCoordinates(d1,d2);
		width= 2*Math.abs(m-x);
		height= 2*Math.abs(k-y);
		centerCoordinates();
			g.drawOval(x,y,width,height);
	}

		
}

class Circle extends Oval {

	public Circle(Dot d1, Dot d2) {
		super(d1, d2);
	}
	public void draw(Graphics g){
		getCoordinates(d1,d2);
		height = 2*Math.max(Math.abs((m-x)),Math.abs((k-y)));
		width = height;
		centerCoordinates();
		
		g.drawOval(x, y, width, height);
		
	}

}

class Eraser extends Figure{
	
	
	public Eraser(Dot d1) {
		
		super(d1);
		x=d1.x;
		y=d1.y;
		
		// TODO Auto-generated constructor stub
	}

	
	public void erase(){
		
		for (Iterator<Figure> iter = Model.allFigures.iterator(); iter.hasNext(); ) {
		    Figure F = iter.next();
			if(F.isInside(d1)){
				iter.remove();
				System.out.println(F.getClass().toString());
				}
			if(F.getClass().toString()=="class Eraser"){
				iter.remove();
				System.out.println(iter + "should be removed");
			}

		}


		System.out.println(Model.allFigures);
		

		}


	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}


	}
	



