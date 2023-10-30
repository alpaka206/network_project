import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import components.BlockButton;
import components.FloorRadio;
import components.ParkSpaceButton;
import components.ParkingLotFrame;

public class Client {

	private ParkingLotFrame frame;

	private Boolean[] parkSpace = new Boolean[16];
	private String sendString;
	private int floorLevel;
	private String carNum;
	private LocalDateTime time;
	private Integer cost = 5000;
	private String[] floor1SpaceName = {"A1", "A2", "A3", "A4", "B1", "B2", "B3", "B4", "C1", "C2", "C3", "C4", "D1",
		"D2", "D3", "D4"};
	private String[] floor2SpaceName = {"E1", "E2", "E3", "E4", "F1", "F2", "F3", "F4", "G1", "G2", "G3", "G4", "H1",
		"H2", "H3", "H4"};
	private String[] floor3SpaceName = {"I1", "I2", "I3", "I4", "J1", "J2", "J3", "J4", "K1", "K2", "K3", "K4", "L1",
		"L2", "L3", "L4"};

	private Boolean[] b1Parking = {false, false, false, false, false, false, false, false, false, false, false, false,
		false, false, false, false};
	private Boolean[] b2Parking = {false, false, false, false, false, false, false, false, false, false, false, false,
		false, false, false, false};
	private Boolean[] b3Parking = {false, false, false, false, false, false, false, false, false, false, false, false,
		false, false, false, false};

	private int floor = 1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client window = new Client();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	private void setTime(LocalDateTime time) {
		this.time = time;
	}

	private void setParkSpace(int index, Boolean status) {
		parkSpace[index] = status;
	}

	public Client() {
		initialize();
	}

	private void parkSpaceInit() {
		for (int i = 0; i < 16; i++) {
			parkSpace[i] = false;
			//System.out.println("parkSpace[" + i +"]" + parkSpace[i]);
		}
	}

	private void changeFloor(List<ParkSpaceButton> list, List<BlockButton> blockList, List<JLabel> labelList) {
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
				list.get(i).setText(floor1SpaceName[i]);
				if (b1Parking[i] == true) {
					list.get(i).setIcon(buttonImage);
				}
			}

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

			ImageIcon buttonImage = new ImageIcon("./ButtonImage/Car3_r.png");
			Image image = buttonImage.getImage().getScaledInstance(80, 40, Image.SCALE_SMOOTH);
			buttonImage.setImage(image);

			for (int i = 0; i < 16; i++) {
				list.get(i).setText(floor2SpaceName[i]);
				if (b2Parking[i] == true) {
					list.get(i).setIcon(buttonImage);
				}
			}

			blockList.get(0).setBounds(20, 10, 48, 46);
			blockList.get(1).setBounds(350, 10, 48, 46);
			blockList.get(2).setBounds(20, 350, 48, 46);
			blockList.get(3).setBounds(350, 350, 48, 46);

			labelList.get(0).setBounds(230, 10, 113, 39);
			labelList.get(1).setBounds(230, 530, 82, 39);
		} else if (floor == 3) {
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
		}
	}

	private void inCarDialog() {

		JDialog dialog = new JDialog(frame, "차 번호 입력", true);
		dialog.getContentPane().setLayout(new FlowLayout());
		dialog.setSize(300, 100);

		JTextField textField = new JTextField(20);
		JButton registerButton = new JButton("등록");

		registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String inputText = textField.getText();
				setCarNum(inputText); // 입력값을 배열에 저장
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

	private void inCarProcess(ParkSpaceButton btn, int space) {
		if (parkSpace[space] == false) {
			time = LocalDateTime.now();
			ImageIcon buttonImage = new ImageIcon("./ButtonImage/Car3.png");
			Image image = buttonImage.getImage().getScaledInstance(40, 80, Image.SCALE_SMOOTH);

			ImageIcon buttonImageR = new ImageIcon("./ButtonImage/Car3_r.png");
			Image imageR = buttonImage.getImage().getScaledInstance(80, 40, Image.SCALE_SMOOTH);

			buttonImage.setImage(image);

			inCarDialog();
			setParkSpace(space, true);
			btn.setIcon(buttonImage);
			btn.setText("");

			System.out.println(
				"===inCar[" + floor1SpaceName[space] + "]=== \nCarNumber: " + carNum + "\nParking Time: " + time);
		} else {
			//시간,요금 받아오기
			setParkSpace(space, false);
			outCarDialog();
			btn.setText(floor1SpaceName[space]);
			btn.setIcon(null);
			System.out.println(
				"===outCar[" + floor1SpaceName[space] + "]=== \nCarNumber: " + carNum + "\nParking Time: " + time);
		}
	}

	private void outCarDialog() {
		JOptionPane.showMessageDialog(frame, "차량번호: " + carNum + "\n요금: " + cost + "\n결제하시겠습니까??");
		JOptionPane.showMessageDialog(frame, "출차처리 되었습니다.");
	}

	private void initialize() {

		parkSpaceInit();

		//0~15 = 주차 버튼
		List<ParkSpaceButton> spaceList = new ArrayList();
		List<BlockButton> blockList = new ArrayList();
		List<JLabel> labelList = new ArrayList();

		frame = new ParkingLotFrame();

		//parkSpace[0]
		ParkSpaceButton btn0 = new ParkSpaceButton("P1");
		spaceList.add(btn0);
		btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inCarProcess(btn0, 0);
			}
		});
		btn0.setBounds(80, 10, 50, 80);
		frame.getContentPane().add(btn0);

		//parkSpace[1]
		ParkSpaceButton btn1 = new ParkSpaceButton("P2");
		spaceList.add(btn1);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inCarProcess(btn1, 1);
			}
		});
		btn1.setBounds(140, 10, 50, 80);
		frame.getContentPane().add(btn1);

		//parkSpace[2]
		ParkSpaceButton btn2 = new ParkSpaceButton("P3");
		spaceList.add(btn2);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inCarProcess(btn2, 2);
			}
		});
		btn2.setBounds(200, 10, 50, 80);
		frame.getContentPane().add(btn2);

		//parkSpace[3]
		ParkSpaceButton btn3 = new ParkSpaceButton("P4");
		spaceList.add(btn3);
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inCarProcess(btn3, 3);
			}
		});
		btn3.setBounds(260, 10, 50, 80);
		frame.getContentPane().add(btn3);

		//parkSpace[4]
		ParkSpaceButton btn4 = new ParkSpaceButton("J1");
		spaceList.add(btn4);
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inCarProcess(btn4, 4);
			}
		});
		btn4.setBounds(410, 10, 50, 80);
		frame.getContentPane().add(btn4);

		//parkSpace[5]
		ParkSpaceButton btn5 = new ParkSpaceButton("J2");
		spaceList.add(btn5);
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inCarProcess(btn5, 5);
			}
		});
		btn5.setBounds(470, 10, 50, 80);
		frame.getContentPane().add(btn5);

		//parkSpace[6]
		ParkSpaceButton btn6 = new ParkSpaceButton("J3");
		spaceList.add(btn6);
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inCarProcess(btn6, 6);
			}
		});
		btn6.setBounds(530, 10, 50, 80);
		frame.getContentPane().add(btn6);

		//parkSpace[7]
		ParkSpaceButton btn7 = new ParkSpaceButton("J4");
		spaceList.add(btn7);
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inCarProcess(btn7, 7);
			}
		});
		btn7.setBounds(590, 10, 50, 80);
		frame.getContentPane().add(btn7);

		//parkSpace[8]
		ParkSpaceButton btn8 = new ParkSpaceButton("H1");
		spaceList.add(btn8);
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inCarProcess(btn8, 8);
			}
		});
		btn8.setBounds(80, 260, 50, 80);
		frame.getContentPane().add(btn8);

		//parkSpace[9]
		ParkSpaceButton btn9 = new ParkSpaceButton("H2");
		spaceList.add(btn9);
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inCarProcess(btn9, 9);
			}
		});
		btn9.setBounds(140, 260, 50, 80);
		frame.getContentPane().add(btn9);

		//parkSpace[10]
		ParkSpaceButton btn10 = new ParkSpaceButton("H3");
		spaceList.add(btn10);
		btn10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inCarProcess(btn10, 10);
			}
		});
		btn10.setBounds(200, 260, 50, 80);
		frame.getContentPane().add(btn10);

		//parkSpace[11]
		ParkSpaceButton btn11 = new ParkSpaceButton("H4");
		spaceList.add(btn11);
		btn11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inCarProcess(btn11, 11);
			}
		});
		btn11.setBounds(260, 260, 50, 80);
		frame.getContentPane().add(btn11);

		//parkSpace[12]
		ParkSpaceButton btn12 = new ParkSpaceButton("L1");
		spaceList.add(btn12);
		btn12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inCarProcess(btn12, 12);
			}
		});
		btn12.setBounds(410, 260, 50, 80);
		frame.getContentPane().add(btn12);

		//parkSpace[13]
		ParkSpaceButton btn13 = new ParkSpaceButton("L2");
		spaceList.add(btn13);
		btn13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inCarProcess(btn13, 13);
			}
		});
		btn13.setBounds(470, 260, 50, 80);
		frame.getContentPane().add(btn13);

		//parkSpace[14]
		ParkSpaceButton btn14 = new ParkSpaceButton("L3");
		spaceList.add(btn14);
		btn14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inCarProcess(btn14, 14);
			}
		});
		btn14.setBounds(530, 260, 50, 80);
		frame.getContentPane().add(btn14);

		//parkSpace[15]
		ParkSpaceButton btn15 = new ParkSpaceButton("L4");
		spaceList.add(btn15);
		btn15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inCarProcess(btn15, 15);
			}
		});
		btn15.setBounds(590, 260, 50, 80);
		frame.getContentPane().add(btn15);

		FloorRadio B1_btn = new FloorRadio(" B1");
		B1_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				floor = 1;
				changeFloor(spaceList, blockList, labelList);
			}
		});
		B1_btn.setBounds(800, 190, 121, 46);
		frame.getContentPane().add(B1_btn);

		FloorRadio B2_btn = new FloorRadio(" B2");
		B2_btn.setBounds(800, 238, 121, 46);
		B2_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				floor = 2;
				changeFloor(spaceList, blockList, labelList);
			}
		});
		frame.getContentPane().add(B2_btn);

		FloorRadio B3_btn = new FloorRadio(" B3");
		B3_btn.setBounds(800, 286, 121, 46);
		frame.getContentPane().add(B3_btn);

		JLabel floorLabel = new JLabel("B1");
		floorLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		floorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		floorLabel.setForeground(new Color(0, 153, 204));
		floorLabel.setFont(new Font("Consolas", Font.BOLD, 40));
		floorLabel.setBounds(800, 10, 50, 64);
		frame.getContentPane().add(floorLabel);

		BlockButton P_block = new BlockButton("P");
		P_block.setBounds(20, 44, 48, 46);
		blockList.add(P_block);
		frame.getContentPane().add(P_block);

		BlockButton J_block = new BlockButton("J");
		J_block.setBounds(350, 44, 48, 46);
		blockList.add(J_block);
		frame.getContentPane().add(J_block);

		BlockButton H_block = new BlockButton("H");
		H_block.setBounds(20, 294, 48, 46);
		blockList.add(H_block);
		frame.getContentPane().add(H_block);

		BlockButton L_Block = new BlockButton("L");
		L_Block.setBounds(350, 294, 48, 46);
		blockList.add(L_Block);
		frame.getContentPane().add(L_Block);

		JLabel entrance = new JLabel("Entrance");
		entrance.setFont(new Font("Pretendard Medium", Font.BOLD, 20));
		entrance.setForeground(new Color(255, 255, 51));
		entrance.setBounds(24, 153, 113, 39);
		labelList.add(entrance);
		frame.getContentPane().add(entrance);

		JLabel exit = new JLabel("Exit");
		exit.setForeground(new Color(255, 255, 51));
		exit.setFont(new Font("Pretendard Medium", Font.BOLD, 20));
		exit.setBounds(641, 153, 82, 39);
		labelList.add(exit);
		frame.getContentPane().add(exit);

	}
}
