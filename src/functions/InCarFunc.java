package functions;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import components.ParkSpaceButton;
import components.ParkingLotFrame;
import network.Admin;
import network.InCar;
import network.OutCar;

public class InCarFunc {
	private String carNum;

	public void inCarProcess(ParkSpaceButton btn, int space, Boolean[] parkSpace, Boolean[] adminBlockState,
		ParkingLotFrame frame, int floor,
		Boolean adminMode) {

		Admin admin = new Admin(floor, space);

		if (adminMode) {
			if (btn.isEnabled()) {
				if (parkSpace[space] == true) {
					int option = JOptionPane.showConfirmDialog(null, "!!Car is already parked!!", "Block Dialog",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);

					return;
				}
				JOptionPane.showConfirmDialog(null, "Do you want to block?", "Block Dialog", JOptionPane.YES_NO_OPTION);
				///asdf
				admin.sendRequestToServer();
				adminBlockState[space] = false;
				btn.setEnabled(false);
				return;
			} else {
				JOptionPane.showConfirmDialog(null, "Do you want to enable space?", "Block Dialog",
					JOptionPane.YES_NO_OPTION);
				admin.sendRequestToServer();
				adminBlockState[space] = true;
				btn.setEnabled(true);
				return;
			}
		}

		if (parkSpace[space] == false) {
			this.inCarDialog(frame);

			LocalDateTime time = LocalDateTime.now();
			InCar inCar = new InCar(time, carNum, floor, space);

			inCar.sendRequestToServer();

			ImageIcon buttonImage = new ImageIcon("./ButtonImage/Car3.png");
			Image image = buttonImage.getImage().getScaledInstance(40, 80, Image.SCALE_SMOOTH);

			ImageIcon buttonImageR = new ImageIcon("./ButtonImage/Car3_r.png");
			Image imageR = buttonImageR.getImage().getScaledInstance(80, 40, Image.SCALE_SMOOTH);

			if (floor == 2) {
				buttonImage.setImage(imageR);
			} else {
				buttonImage.setImage(image);
			}

			parkSpace[space] = true;

			btn.setIcon(buttonImage);
			btn.setText("");

		} else { //outCar process
			outCarDialog(frame);
			LocalDateTime time = LocalDateTime.now();
			OutCar outCar = new OutCar(time, carNum, floor, space);
			outCar.processOut();

			//시간,요금 받아오기
			parkSpace[space] = false;
			CollocateSpace collocateSpace = new CollocateSpace();
			btn.setText(collocateSpace.getSpaceName(floor, space));
			btn.setIcon(null);
		}
	}

	public void inCarDialog(ParkingLotFrame frame) {

		JDialog dialog = new JDialog(frame, "차 번호 입력", true);
		dialog.getContentPane().setLayout(new FlowLayout());
		dialog.setSize(300, 100);

		JTextField textField = new JTextField(20);
		JButton registerButton = new JButton("등록");

		registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String inputText = textField.getText();
				carNum = inputText;
				JOptionPane.showMessageDialog(frame, "차량이 등록되었습니다\n 등록된 차량번호: " + inputText);
				dialog.dispose();
				frame.setVisible(true);
			}
		});

		dialog.getContentPane().add(textField);
		dialog.getContentPane().add(registerButton);
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);

	}

	private void outCarDialog(ParkingLotFrame frame) {
		//JOptionPane.showMessageDialog(frame, "차량번호: " + carNum + "\n요금: " + cost + "\n결제하시겠습니까??");
		JOptionPane.showMessageDialog(frame, "출차처리 되었습니다.");
	}
}