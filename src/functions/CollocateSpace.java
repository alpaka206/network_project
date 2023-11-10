package functions;

import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import components.BlockButton;
import components.ParkSpaceButton;

public class CollocateSpace {

	private String[] floor1SpaceName = {"A1", "A2", "A3", "A4", "B1", "B2", "B3", "B4", "C1", "C2", "C3", "C4", "D1",
		"D2", "D3", "D4"};
	private String[] floor2SpaceName = {"E1", "E2", "E3", "E4", "F1", "F2", "F3", "F4", "G1", "G2", "G3", "G4", "H1",
		"H2", "H3", "H4"};
	private String[] floor3SpaceName = {"I1", "I2", "I3", "I4", "J1", "J2", "J3", "J4", "K1", "K2", "K3", "K4", "L1",
		"L2", "L3", "L4"};

	public void collocateSpace(List<ParkSpaceButton> list, List<BlockButton> blockList, List<JLabel> labelList,
		int floor, Boolean[] parkSpaces, Boolean[] adminBlockState) {
		if (floor == 1) {
			list.get(0).setBounds(80, 10, 50, 80);
			list.get(1).setBounds(140, 10, 50, 80);
			list.get(2).setBounds(200, 10, 50, 80);
			list.get(3).setBounds(260, 10, 50, 80);

			list.get(4).setBounds(410, 10, 50, 80);
			list.get(5).setBounds(470, 10, 50, 80);
			list.get(6).setBounds(530, 10, 50, 80);
			list.get(7).setBounds(590, 10, 50, 80);

			list.get(8).setBounds(80, 260, 50, 80);
			list.get(9).setBounds(140, 260, 50, 80);
			list.get(10).setBounds(200, 260, 50, 80);
			list.get(11).setBounds(260, 260, 50, 80);

			list.get(12).setBounds(410, 260, 50, 80);
			list.get(13).setBounds(470, 260, 50, 80);
			list.get(14).setBounds(530, 260, 50, 80);
			list.get(15).setBounds(590, 260, 50, 80);

			blockList.get(0).setBounds(20, 44, 48, 46);
			blockList.get(1).setBounds(350, 44, 48, 46);
			blockList.get(2).setBounds(20, 294, 48, 46);
			blockList.get(3).setBounds(350, 294, 48, 46);

			labelList.get(0).setBounds(24, 153, 113, 39);
			labelList.get(1).setBounds(641, 153, 82, 39);

			ImageIcon buttonImage = new ImageIcon("./ButtonImage/Car3.png");
			Image image = buttonImage.getImage().getScaledInstance(40, 80, Image.SCALE_SMOOTH);
			buttonImage.setImage(image);

			for (int i = 0; i < 16; i++) {

				if (adminBlockState[i] == false)
					list.get(i).setEnabled(false);

				else if (adminBlockState[i] == true && list.get(i).isEnabled() == false)
					list.get(i).setEnabled(true);

				if (parkSpaces[i]) {
					list.get(i).setIcon(buttonImage);
					list.get(i).setText("");
				} else {
					list.get(i).setText(floor1SpaceName[i]);
					list.get(i).setIcon(null);
				}
			}

			labelList.get(2).setText("B1");

		} else if (floor == 2) {
			list.get(0).setBounds(80, 10, 80, 50);
			list.get(1).setBounds(80, 70, 80, 50);
			list.get(2).setBounds(80, 130, 80, 50);
			list.get(3).setBounds(80, 200, 80, 50);

			list.get(4).setBounds(80, 350, 80, 50);
			list.get(5).setBounds(80, 410, 80, 50);
			list.get(6).setBounds(80, 470, 80, 50);
			list.get(7).setBounds(80, 530, 80, 50);

			list.get(8).setBounds(410, 10, 80, 50);
			list.get(9).setBounds(410, 70, 80, 50);
			list.get(10).setBounds(410, 130, 80, 50);
			list.get(11).setBounds(410, 200, 80, 50);

			list.get(12).setBounds(410, 350, 80, 50);
			list.get(13).setBounds(410, 410, 80, 50);
			list.get(14).setBounds(410, 470, 80, 50);
			list.get(15).setBounds(410, 530, 80, 50);

			blockList.get(0).setBounds(20, 10, 48, 46);
			blockList.get(1).setBounds(350, 10, 48, 46);
			blockList.get(2).setBounds(20, 350, 48, 46);
			blockList.get(3).setBounds(350, 350, 48, 46);

			labelList.get(0).setBounds(230, 10, 113, 39);
			labelList.get(1).setBounds(230, 530, 82, 39);
			ImageIcon buttonImage = new ImageIcon("./ButtonImage/Car3_r.png");
			Image image = buttonImage.getImage().getScaledInstance(80, 40, Image.SCALE_SMOOTH);
			buttonImage.setImage(image);

			for (int i = 0; i < 16; i++) {

				if (adminBlockState[i] == false)
					list.get(i).setEnabled(false);

				else if (adminBlockState[i] == true && list.get(i).isEnabled() == false)
					list.get(i).setEnabled(true);

				if (parkSpaces[i] == true) {
					list.get(i).setText("");
					list.get(i).setIcon(buttonImage);
				} else {
					list.get(i).setText(floor2SpaceName[i]);
					list.get(i).setIcon(null);
				}
			}
			labelList.get(2).setText("B2");

		} else if (floor == 3) {
			list.get(0).setBounds(80, 10, 50, 80);
			list.get(1).setBounds(140, 10, 50, 80);
			list.get(2).setBounds(200, 10, 50, 80);
			list.get(3).setBounds(260, 10, 50, 80);

			list.get(4).setBounds(410, 10, 50, 80);
			list.get(5).setBounds(470, 10, 50, 80);
			list.get(6).setBounds(530, 10, 50, 80);
			list.get(7).setBounds(590, 10, 50, 80);

			list.get(8).setBounds(80, 260, 50, 80);
			list.get(9).setBounds(140, 260, 50, 80);
			list.get(10).setBounds(200, 260, 50, 80);
			list.get(11).setBounds(260, 260, 50, 80);

			list.get(12).setBounds(410, 260, 50, 80);
			list.get(13).setBounds(470, 260, 50, 80);
			list.get(14).setBounds(530, 260, 50, 80);
			list.get(15).setBounds(590, 260, 50, 80);

			blockList.get(0).setBounds(20, 44, 48, 46);
			blockList.get(1).setBounds(350, 44, 48, 46);
			blockList.get(2).setBounds(20, 294, 48, 46);
			blockList.get(3).setBounds(350, 294, 48, 46);

			labelList.get(0).setBounds(24, 153, 113, 39);
			labelList.get(1).setBounds(641, 153, 82, 39);

			ImageIcon buttonImage = new ImageIcon("./ButtonImage/Car3.png");
			Image image = buttonImage.getImage().getScaledInstance(40, 80, Image.SCALE_SMOOTH);
			buttonImage.setImage(image);

			for (int i = 0; i < 16; i++) {

				if (adminBlockState[i] == false)
					list.get(i).setEnabled(false);

				else if (adminBlockState[i] == true && list.get(i).isEnabled() == false)
					list.get(i).setEnabled(true);

				if (parkSpaces[i]) {
					list.get(i).setIcon(buttonImage);
					list.get(i).setText("");
				} else {
					list.get(i).setText(floor3SpaceName[i]);
					list.get(i).setIcon(null);
				}
			}
			labelList.get(2).setText("B3");
		}
	}

	public String getSpaceName(int floor, int space) {
		String name;
		if (floor == 1)
			name = floor1SpaceName[space];
		else if (floor == 2)
			name = floor2SpaceName[space];
		else {
			name = floor3SpaceName[space];
		}
		return name;
	}

}
