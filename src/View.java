import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;


public class View extends JFrame {
	

	Model model;
	Client client;
	Controller controller;
	Graphics g;
	static int counter=0;
	
	JRadioButton Line;
	JRadioButton Rect;
	JRadioButton Square;
	JRadioButton Oval;
	JRadioButton Circle;
	JRadioButton Eraser;

	
	
	public View(){
		super("Window "+ (++counter));
		setSize(800,800);
		setLayout(new FlowLayout());
		
		Line = new JRadioButton("Line");
		Rect = new JRadioButton("Rect");
		Square = new JRadioButton("Square");
		Oval = new JRadioButton("Oval");
		Circle = new JRadioButton("Circle");
		Eraser = new JRadioButton("Eraser");
		
		groupButton();
		
		
		

		this.add(Line);
		this.add(Rect);
		this.add(Square);
		this.add(Oval);
		this.add(Circle);
		this.add(Eraser);
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		g = getGraphics();


		
	}
	private void groupButton( ) {

		ButtonGroup bg1 = new ButtonGroup( );

		bg1.add(Line);
		bg1.add(Rect);
		bg1.add(Square);
		bg1.add(Oval);
		bg1.add(Circle);
		bg1.add(Eraser);

		
	}}



