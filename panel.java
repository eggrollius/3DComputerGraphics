import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class panel extends JPanel{
	public panel(){
		super();
		repaint();
	}

	public void paintComponent(Graphics g){
		for(int x = 0; x < main.WIDTH; x++){
			for(int y = 0; y < main.HEIGHT; y++){
				g.setColor(main.pixelBuffer[x + y*main.WIDTH]);
				g.drawLine(x, y, x, y);//Crude way of setting an individual pixel color	
			}
		}
	}
}
