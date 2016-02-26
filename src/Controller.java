import java.awt.Event;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;

import javax.swing.JRadioButton;


public class Controller implements MouseListener, ActionListener{
	int clicks = 1;
	int p1x = 0;
	int p1y = 0;
	int p2x = 0;
	int p2y = 0;
	Graphics g;
	String activeShape;
	
	View view;
	Model model;
	Client client;
	Figure E;
	public Controller(View view, Model model, Client client){

		view.addMouseListener(this);
		
		view.Line.addActionListener(this);
		view.Rect.addActionListener(this);
		view.Oval.addActionListener(this);
		view.Square.addActionListener(this);
		view.Circle.addActionListener(this);
		view.Eraser.addActionListener(this);
		
		this.view = view;
		this.model = model;
		this.client = client;
		this.g = view.g;
		
		}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		activeShape = e.getActionCommand();
		
		
	}



	@Override
	public void mouseClicked(MouseEvent m){
		
		if(activeShape == "Eraser"){
			E = new Eraser(new Dot(m.getX(),m.getY()));
			client.sendObject(E);
			((Eraser) E).erase();
			view.update(g);
			view.paint(g);
			model.drawFigures(g);

		}
		
		else{
		
		
		if(clicks%2!=0){
			
			p1x=m.getX();
			p1y=m.getY();
			g.fillOval(p1x,p1y,2,2);
			clicks++;
		}else{
			p2x = m.getX();
			p2y = m.getY();
			clicks = 1 ;
		
			
		Figure F = new Rectangle(new Dot(p1x,p1y), new Dot(p2x,p2y));
		if(activeShape == "Line")
			F = new Line(new Dot(p1x,p1y), new Dot(p2x,p2y));
		else if(activeShape == "Rect")
			F = new Rectangle(new Dot(p1x,p1y), new Dot(p2x,p2y));
		else if(activeShape == "Square")
			F = new Square(new Dot(p1x,p1y), new Dot(p2x,p2y));
		else if(activeShape == "Oval")
			F = new Oval(new Dot(p1x,p1y), new Dot(p2x,p2y));
		else if(activeShape == "Circle")
			F = new Circle(new Dot(p1x,p1y), new Dot(p2x,p2y));

		
		model.addFigure(F);
		client.sendObject(F);
		System.out.println("SKICCKAR!");
		model.drawFigures(g);}}
		
		
				
	}
	

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
