package components;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JRadioButton;

public class FloorRadio extends JRadioButton{
	public FloorRadio(String name){
		setFont(new Font("굴림", Font.BOLD, 30));
		setForeground(new Color(255, 255, 255));
		setBackground(new Color(0, 0, 0));
		setText(name);
	}
}
