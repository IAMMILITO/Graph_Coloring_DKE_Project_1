import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class PauseMenu extends GameMode {


	public PauseMenu(GameState state) {
		super(state,null);
		MenuVertex[] items;
		super.setVertexListener(new PauseMenuVertexListener());
	}
	public class PauseMenuVertexListener extends VertexListener{
	    public void vertexPressed(int v, int mouseButton) {
            if (mouseButton == 1) {
                GameState state = getGameState();
                if (v == 0) { // TODO: If they click "Save," save the current state of the game to a file in the game folder.
                }
                if (v == 1) { // If they click "Quit," send them back to the start menu.
                    state.changeState(GameState.MAIN_MENU);
                }
                if (v == 2) { // TODO add a restart() function to the State class
                    state.replaceState(new GameMode(state, GraphUtil.generateRandomGraph(3, 3)), GameState.INGAME);
                    state.changeState(GameState.INGAME);
                }
                if (v == 3) { // If they click "Resume," resume same game with no changes.
                    state.changeState(GameState.INGAME);
                }
            }
        }
	}
	@Override
	public void draw(Graphics2D g) {
		g.setFont(new Font("Pause Menu Font", Font.BOLD, 24));
		g.drawString("PAUSED", (Game.WIDTH/2)-25, (Game.HEIGHT/2)-15);
		super.draw(g);
	}

	public void keyPressed(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
			getGameState().changeState(GameState.INGAME);
	}
	private static Graph getPauseMenuGraph(){
        MenuVertex[] items = new MenuVertex[4];
        items[0] = new MenuVertex(Game.WIDTH/4, Game.HEIGHT/2, "   Save");		// Spaces in string(s) to offset the text to be perfectly above the vertex.
        items[1] = new MenuVertex((Game.WIDTH*2)/3, Game.HEIGHT/2, "   Quit");
        items[2] = new MenuVertex((Game.WIDTH*2)/3, Game.HEIGHT/4, " Restart");
        items[3] = new MenuVertex(Game.WIDTH/4, Game.HEIGHT/4, " Resume");
        return new Graph(items, new int[][]{new int[]{1,3}, new int[]{2}, new int[]{3}, new int[]{}});
    }
}
