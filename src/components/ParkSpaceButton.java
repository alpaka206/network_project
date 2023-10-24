package components;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class ParkSpaceButton extends JButton{
	public ParkSpaceButton(String name) {
		setBackground(new Color(0, 0, 0));
		setForeground(new Color(255, 255, 255));
		setFont(new Font("Consolas", Font.BOLD, 20));
		setPreferredSize(new Dimension(50, 80));
		setBorderPainted(true);
		setBorder(new LineBorder(new Color(255, 255, 255), 4));
		setText(name);
	}
	
}
