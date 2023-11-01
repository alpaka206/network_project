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

import components.ParkSpaceButton;
import components.ParkingLotFrame;

public class AdminFunc {
	public void adminBlock(Boolean[] parkingLots, Integer blockIndex, List<ParkSpaceButton> spaceList, int floor,
		ParkingLotFrame frame) {
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

}
