import java.awt.Graphics;


public interface FigureInterface {
	
	public abstract void draw(Graphics g);
	public abstract boolean isInside(Dot d);

}
