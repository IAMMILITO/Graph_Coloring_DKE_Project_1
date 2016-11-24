import java.awt.Color;
import java.awt.Graphics;

public class MenuVertex extends Vertex {
	private String label;
	public MenuVertex(int x, int y, String label) {
		super(x, y);
		this.label = label;
		super.diameter = 40;
	}
	public void draw(Graphics g){
		g.setColor(Color.CYAN);
		g.drawString(label, x, y);
		super.draw(g);
	}
}