import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
public class Menu extends Level {
	private Font font;
	public Menu(GameState state){
		super(state, null);
		Vertex[] items = new MenuVertex[2];
		items[0] = new MenuVertex(Game.WIDTH/4, Game.HEIGHT/2, "Import");		// TODO We need to find a way to make these points equidistant from the edges, same as the...
		items[1] = new MenuVertex(Game.WIDTH/2, Game.HEIGHT/2, "Generate");		// TODO ...pause menu issue, where the vertices are displayed evenly in the frame. (ratio issue when dividing double/fraction)
		super.setGraph(new Graph(items, new int[][]{ new int[]{1}, new int[0]}));
		font = new Font("Main Menu Font", Font.BOLD, 20);
	}
    public void draw(Graphics2D g) {
		g.setFont(font);
		graph.draw(g);
    }

    public void tick(){}

    public void mousePressed(MouseEvent e){
    	super.mousePressed(e);
		if(e.getButton() == MouseEvent.BUTTON1){
    			//insert here what the menu items have to do
    			//start a level
			if(clickedVertex==0){
				String input = JOptionPane.showInputDialog("Please give the directory to your graph file, make use of \\"+"\\ instead of \\. ");//needs to be fixed
				String[] args = {input};
				state.states[GameState.INGAME] = new PlaygroundLevel(state, Graph.readGraphFromFile(args)); //Start the actual game. HOW to implement feedback: what happens if we cannot compute the chromatic number, or the file was corrupt??
				state.setState(GameState.INGAME);
			}
			else if(clickedVertex == 1){
				Boolean falseVertexEdgeComb=true;
				int n = 0;
				int m = 0;
				while (falseVertexEdgeComb){
					JTextField vertexField = new JTextField(5);
					JTextField edgeField = new JTextField(5);

					JPanel myPanel = new JPanel();
					myPanel.add(new JLabel("vertices:"));
					myPanel.add(vertexField);
					myPanel.add(Box.createHorizontalStrut(15)); // a spacer
					myPanel.add(new JLabel("edges:"));
					myPanel.add(edgeField);

					int result = JOptionPane.showConfirmDialog(null, myPanel,
							"Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
					if (result == JOptionPane.OK_OPTION) {
						m = Integer.parseInt(vertexField.getText());//number of vertices
						n = Integer.parseInt(edgeField.getText());//number of edges
						//The #vertices and #edges were/were not compatible together (to less edges: always disconnected vertices, or to much edges: graph would be more than complete)
						if((n<m-1)||(n>((m*(m-1))/2))){ //They were not > Throw an error message
							JPanel newPanel = new JPanel();
							JOptionPane.showMessageDialog(newPanel, "Problem with creating a graph, with: " + m + "vertices, and: "+n+" edges.","Backup problem", JOptionPane.ERROR_MESSAGE);
						}else{ //They were > create the graph
							falseVertexEdgeComb = false;
							state.states[GameState.INGAME] = new PlaygroundLevel(state, Graph.generateRandomGraph(m, n)); //Start the actual game
							state.setState(GameState.INGAME);
						}
					}else if(result == JOptionPane.CANCEL_OPTION){
						falseVertexEdgeComb = false;
					}
				}
    		}
    		clickedVertex = -1;
    	}
    }
    
    public void keyPressed(KeyEvent e){
    	if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
    		
    	}
    }
}
