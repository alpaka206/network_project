package components;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

public class BlockButton extends JButton{
	public BlockButton(String name){
		setBackground(new Color(255, 255, 255));
		setFont(new Font("맑은 고딕", Font.BOLD, 20));
		setPreferredSize(new Dimension(60,60));
		setEnabled(false);
		setText(name);

	}

}
