package components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class StateLabel extends JLabel {
	public StateLabel(String msg) {
		this.setText(msg);
		this.setForeground(new Color(0, 255, 0));
		this.setFont(new Font("맑은 고딕", Font.BOLD, 17));
	}
}
