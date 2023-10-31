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

public class InCarFunc {
	private String CarNum;

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
				CarNum = inputText;
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

	public void inCarProcess(ParkSpaceButton btn, int space, Boolean[] parkSpace, ParkingLotFrame frame, int floor) {
		if (parkSpace[space] == false) {

			LocalDateTime time = LocalDateTime.now();
			ImageIcon buttonImage = new ImageIcon("./ButtonImage/Car3.png");
			Image image = buttonImage.getImage().getScaledInstance(40, 80, Image.SCALE_SMOOTH);

			ImageIcon buttonImageR = new ImageIcon("./ButtonImage/Car3_r.png");
			Image imageR = buttonImageR.getImage().getScaledInstance(80, 40, Image.SCALE_SMOOTH);

			if (floor == 2) {
				buttonImage.setImage(imageR);
			} else {
				buttonImage.setImage(image);
			}

			this.inCarDialog(frame);
			parkSpace[space] = true;

			btn.setIcon(buttonImage);
			btn.setText("");

			//System.out.println( "===inCar[" + floor1SpaceName[space] + "]=== \nCarNumber: " + carNum + "\nParking Time: " + time);
		} else {
			//시간,요금 받아오기
			parkSpace[space] = false;
			outCarDialog(frame);
			//		btn.setText(floor1SpaceName[space]);
			btn.setIcon(null);
			//System.out.println(//"===outCar[" + floor1SpaceName[space] + "]=== \nCarNumber: " + carNum + "\nParking Time: " + time);
		}
	}

	private void outCarDialog(ParkingLotFrame frame) {
		//JOptionPane.showMessageDialog(frame, "차량번호: " + carNum + "\n요금: " + cost + "\n결제하시겠습니까??");
		JOptionPane.showMessageDialog(frame, "출차처리 되었습니다.");
	}
}
