package components;
import java.awt.*;

import javax.swing.*;

public class backgroundFrame extends JFrame{
	Image img = new ImageIcon("\"./ButtonImage/ParkingLotBackground.jpg\"").getImage();
	
	
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}

}
