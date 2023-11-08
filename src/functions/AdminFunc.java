package functions;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import components.AdminButton;
import components.ParkSpaceButton;
import components.ParkingLotFrame;

public class AdminFunc {

	private String[][] spaceName = {
		{},
		{"A1", "A2", "A3", "A4", "B1", "B2", "B3", "B4", "C1", "C2", "C3", "C4", "D1", "D2", "D3", "D4"},
		{"E1", "E2", "E3", "E4", "F1", "F2", "F3", "F4", "G1", "G2", "G3", "G4", "H1", "H2", "H3", "H4"},
		{"I1", "I2", "I3", "I4", "J1", "J2", "J3", "J4", "K1", "K2", "K3", "K4", "L1", "L2", "L3", "L4"}
	};

	public void adminBlock(Boolean[] parkingLots, Boolean[] adminBlockState, Integer blockIndex,
		List<ParkSpaceButton> spaceList, int floor, ParkingLotFrame frame) {
		if (parkingLots[blockIndex])
			JOptionPane.showMessageDialog(frame, "이미 주차된 공간입니다.");
		else {
			JOptionPane.showConfirmDialog(null, "Do you want to block?", "Block Dialog", JOptionPane.YES_NO_OPTION);

		}
	}

	public Boolean adminLogin() {
		String passwd;
		JDialog dialog = new JDialog();
		dialog.setTitle("관리자 로그인");
		dialog.setSize(300, 120);
		dialog.setLayout(new FlowLayout());

		JLabel label = new JLabel("관리자 번호를 입력하시오");
		JTextField textField = new JTextField(20);
		JButton okButton = new JButton("OK");
		JButton cancelButton = new JButton("Cancel");

		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.dispose(); // Close the dialog
			}
		});

		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setText(""); // Clear the text field
				dialog.dispose(); // Close the dialog
			}
		});

		dialog.add(label);
		dialog.add(textField);
		dialog.add(okButton);
		dialog.add(cancelButton);
		dialog.setLocationRelativeTo(null);
		dialog.setModal(true);
		dialog.setVisible(true);

		passwd = textField.getText();

		if (!passwd.equals("network")) {
			JOptionPane.showMessageDialog(null, "비밀번호가 잘못 되었습니다.", "Wrong", JOptionPane.WARNING_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "로그인 되었습니다.", "Success", JOptionPane.INFORMATION_MESSAGE);
		}

		return (passwd.equals("network")) ? true : false;
	}

	public Boolean adminLogout() {
		JOptionPane.showMessageDialog(null, "Logout!", "Information", JOptionPane.INFORMATION_MESSAGE);
		return false;
	}

	public void unBlockSpace(ParkingLotFrame frame, AdminButton btn, Boolean[][] adminBlockState,
		List<ParkSpaceButton> spaceList) {

		String input = JOptionPane.showInputDialog("Enter a string:");
		for (int i = 1; i < 4; i++) {
			for (int j = 0; j < 16; j++) {
				if (spaceName[i][j].equals(input)) {
					adminBlockState[i][j] = true;
					JOptionPane.showMessageDialog(null, spaceName[i][j] + "에 다시 주차가 가능합니다", "Unblocked",
						JOptionPane.INFORMATION_MESSAGE);
					spaceList.get(j).setEnabled(true);
					return;
				}
			}
		}
		JOptionPane.showMessageDialog(null, "알맞은 위치를 입력하세요 A1 ~ L4", "Failed", JOptionPane.WARNING_MESSAGE);
	}
}
