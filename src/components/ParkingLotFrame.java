package components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ParkingLotFrame extends JFrame {
	Image img = new ImageIcon("./ButtonImage/ParkingLotBackground.jpg").getImage();

	public ParkingLotFrame() {
		init();
	}

	private void init() {
		// 배경 패널을 생성하여 이미지를 표시할 수 있도록 합니다.
		JPanel backgroundPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(img, 0, 0, this);
			}
		};
		backgroundPanel.setLayout(null); // 필요에 따라 레이아웃 설정

		setContentPane(backgroundPanel);
		setBackground(new Color(157, 208, 147));
		setBounds(100, 100, 1280, 720);
		setLocationRelativeTo(null);
	}
}