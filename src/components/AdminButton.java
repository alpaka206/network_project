package components;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class AdminButton extends JButton {

	public AdminButton(String name) {
		this.setForeground(new Color(255, 255, 255));
		this.setBorder(new LineBorder(new Color(255, 255, 255), 4));
		this.setBorderPainted(true);
		this.setBackground(new Color(0, 0, 0));
		this.setText(name);
	}
}
