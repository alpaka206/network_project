import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import components.BlockButton;
import components.FloorRadio;
import components.ParkSpaceButton;

public class Client {

	private JFrame frame;

	private Boolean[] parkSpace = new Boolean[16];
	private String sendString;
	private Integer floorLevel;
	private String carNum;
	private LocalDateTime time;
	private Integer cost = 5000;

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

	public Client() {
		initialize();
	}

	private void parkSpaceInit() {
		for (int i = 0; i < 16; i++) {
			parkSpace[i] = false;
			//System.out.println("parkSpace[" + i +"]" + parkSpace[i]);
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

	private void outCarDialog() {
		JOptionPane.showMessageDialog(frame, "차량번호: " + carNum + "\n요금: " + cost + "\n결제하시겠습니까??");
		JOptionPane.showMessageDialog(frame, "출차처리 되었습니다.");
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

	private void initialize() {

		parkSpaceInit();

		ImageIcon backgroundImage = new ImageIcon("./ButtonImage/ParkingLotBackground.jpg");

		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 0, 0));
		frame.getContentPane().setForeground(new Color(157, 208, 147));
		frame.setBounds(100, 100, 987, 415);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Exit ->");
		lblNewLabel_2.setForeground(new Color(255, 255, 51));
		lblNewLabel_2.setFont(new Font("Pretendard Medium", Font.BOLD, 20));
		lblNewLabel_2.setBounds(641, 153, 82, 39);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel = new JLabel("Entrance");
		lblNewLabel.setFont(new Font("Pretendard Medium", Font.BOLD, 20));
		lblNewLabel.setForeground(new Color(255, 255, 51));
		lblNewLabel.setBounds(24, 153, 113, 39);
		frame.getContentPane().add(lblNewLabel);

		//parkSpace[0]
		ParkSpaceButton P1 = new ParkSpaceButton("P1");
		P1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTime(LocalDateTime.now());
				if (parkSpace[0] == false) {
					ImageIcon buttonImage = new ImageIcon("./ButtonImage/Car3.png");
					Image image = buttonImage.getImage().getScaledInstance(40, 80, Image.SCALE_SMOOTH);
					buttonImage.setImage(image);

					inCarDialog();
					setParkSpace(0, true);
					P1.setIcon(buttonImage);
					P1.setText("");

					System.out.println("===inCar=== \nCarNumber: " + carNum + "\nParking Time: " + time);
				} else {
					setParkSpace(0, false);
					outCarDialog();
					P1.setText("P1");
					P1.setIcon(null);
					System.out.println("===outCar=== \nCarNumber: " + carNum + "\nParking Time: " + time);
				}
			}
		});
		P1.setBounds(80, 10, 50, 80);
		frame.getContentPane().add(P1);

		//parkSpace[1]
		ParkSpaceButton P2 = new ParkSpaceButton("P2");
		P2.setBounds(140, 10, 50, 80);
		frame.getContentPane().add(P2);

		//parkSpace[2]
		ParkSpaceButton P3 = new ParkSpaceButton("P3");
		P3.setBounds(200, 10, 50, 80);
		frame.getContentPane().add(P3);

		//parkSpace[3]
		ParkSpaceButton P4 = new ParkSpaceButton("P4");
		P4.setBounds(260, 10, 50, 80);
		frame.getContentPane().add(P4);

		//parkSpace[4]
		ParkSpaceButton J1 = new ParkSpaceButton("J1");
		J1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {}
		});
		J1.setBounds(410, 10, 50, 80);
		frame.getContentPane().add(J1);

		//parkSpace[5]
		ParkSpaceButton J2 = new ParkSpaceButton("J2");
		J2.setBounds(470, 10, 50, 80);
		frame.getContentPane().add(J2);

		//parkSpace[6]
		ParkSpaceButton J3 = new ParkSpaceButton("J3");
		J3.setBounds(530, 10, 50, 80);
		frame.getContentPane().add(J3);

		//parkSpace[7]
		ParkSpaceButton J4 = new ParkSpaceButton("J4");
		J4.setBounds(590, 10, 50, 80);
		frame.getContentPane().add(J4);

		//parkSpace[8]
		ParkSpaceButton H1 = new ParkSpaceButton("H1");
		H1.setBounds(80, 260, 50, 80);
		frame.getContentPane().add(H1);

		//parkSpace[9]
		ParkSpaceButton H2 = new ParkSpaceButton("H2");
		H2.setBounds(140, 260, 50, 80);
		frame.getContentPane().add(H2);

		//parkSpace[10]
		ParkSpaceButton H3 = new ParkSpaceButton("H3");
		H3.setBounds(200, 260, 50, 80);
		frame.getContentPane().add(H3);

		//parkSpace[11]
		ParkSpaceButton H4 = new ParkSpaceButton("H4");
		H4.setBounds(260, 260, 50, 80);
		frame.getContentPane().add(H4);

		//parkSpace[12]
		ParkSpaceButton L1 = new ParkSpaceButton("L1");
		L1.setBounds(410, 260, 50, 80);
		frame.getContentPane().add(L1);

		//parkSpace[13]
		ParkSpaceButton L2 = new ParkSpaceButton("L2");
		L2.setBounds(470, 260, 50, 80);
		frame.getContentPane().add(L2);

		//parkSpace[14]
		ParkSpaceButton L3 = new ParkSpaceButton("L3");
		L3.setBounds(530, 260, 50, 80);
		frame.getContentPane().add(L3);

		//parkSpace[15]
		ParkSpaceButton L4 = new ParkSpaceButton("L4");
		L4.setBounds(590, 260, 50, 80);
		frame.getContentPane().add(L4);

		FloorRadio B1_btn = new FloorRadio(" B1");
		B1_btn.setBounds(800, 190, 121, 46);
		frame.getContentPane().add(B1_btn);

		FloorRadio B2_btn = new FloorRadio(" B2");
		B2_btn.setBounds(800, 238, 121, 46);
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
		frame.getContentPane().add(P_block);

		BlockButton J_block = new BlockButton("J");
		J_block.setBounds(350, 44, 48, 46);
		frame.getContentPane().add(J_block);

		BlockButton H_block = new BlockButton("H");
		H_block.setBounds(20, 294, 48, 46);
		frame.getContentPane().add(H_block);

		BlockButton L_Block = new BlockButton("L");
		L_Block.setBounds(350, 294, 48, 46);
		frame.getContentPane().add(L_Block);

	}
}
