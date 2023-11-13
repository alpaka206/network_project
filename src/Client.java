import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import components.AdminButton;
import components.BlockButton;
import components.FloorRadio;
import components.ParkSpaceButton;
import components.ParkingLotFrame;
import components.StateLabel;
import functions.AdminFunc;
import functions.CollocateSpace;
import functions.InCarFunc;

public class Client {

	private ParkingLotFrame frame;
	private Boolean adminMode = false;
	public InCarFunc inCarFunc = new InCarFunc();
	private Boolean[][] parkingLots = new Boolean[4][16]; //false = not parked
	private Boolean[][] adminBlockState = new Boolean[4][16]; //false = blocked
	private List<FloorRadio> radioList = new ArrayList<FloorRadio>();
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

	public Client() {
		initialize();
	}

	private void parkSpaceInit() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 16; j++) {
				parkingLots[i][j] = false;
				adminBlockState[i][j] = true;
			}
		}
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
				inCarFunc.inCarProcess(btn0, 0, parkingLots[floor], adminBlockState[floor], frame, floor, adminMode);
			}
		});
		btn0.setBounds(80, 10, 50, 80);
		frame.getContentPane().add(btn0);

		//parkSpace[1]
		ParkSpaceButton btn1 = new ParkSpaceButton("P2");
		spaceList.add(btn1);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inCarFunc.inCarProcess(btn1, 1, parkingLots[floor], adminBlockState[floor], frame, floor, adminMode);
			}
		});
		btn1.setBounds(140, 10, 50, 80);
		frame.getContentPane().add(btn1);

		//parkSpace[2]
		ParkSpaceButton btn2 = new ParkSpaceButton("P3");
		spaceList.add(btn2);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inCarFunc.inCarProcess(btn2, 2, parkingLots[floor], adminBlockState[floor], frame, floor, adminMode);
			}
		});
		btn2.setBounds(200, 10, 50, 80);
		frame.getContentPane().add(btn2);

		//parkSpace[3]
		ParkSpaceButton btn3 = new ParkSpaceButton("P4");
		spaceList.add(btn3);
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inCarFunc.inCarProcess(btn3, 3, parkingLots[floor], adminBlockState[floor], frame, floor, adminMode);
			}
		});
		btn3.setBounds(260, 10, 50, 80);
		frame.getContentPane().add(btn3);

		//parkSpace[4]
		ParkSpaceButton btn4 = new ParkSpaceButton("J1");
		spaceList.add(btn4);
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inCarFunc.inCarProcess(btn4, 4, parkingLots[floor], adminBlockState[floor], frame, floor, adminMode);
			}
		});
		btn4.setBounds(410, 10, 50, 80);
		frame.getContentPane().add(btn4);

		//parkSpace[5]
		ParkSpaceButton btn5 = new ParkSpaceButton("J2");
		spaceList.add(btn5);
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inCarFunc.inCarProcess(btn5, 5, parkingLots[floor], adminBlockState[floor], frame, floor, adminMode);
			}
		});
		btn5.setBounds(470, 10, 50, 80);
		frame.getContentPane().add(btn5);

		//parkSpace[6]
		ParkSpaceButton btn6 = new ParkSpaceButton("J3");
		spaceList.add(btn6);
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inCarFunc.inCarProcess(btn6, 6, parkingLots[floor], adminBlockState[floor], frame, floor, adminMode);
			}
		});
		btn6.setBounds(530, 10, 50, 80);
		frame.getContentPane().add(btn6);

		//parkSpace[7]
		ParkSpaceButton btn7 = new ParkSpaceButton("J4");
		spaceList.add(btn7);
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inCarFunc.inCarProcess(btn7, 7, parkingLots[floor], adminBlockState[floor], frame, floor, adminMode);
			}
		});
		btn7.setBounds(590, 10, 50, 80);
		frame.getContentPane().add(btn7);

		//parkSpace[8]
		ParkSpaceButton btn8 = new ParkSpaceButton("H1");
		spaceList.add(btn8);
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inCarFunc.inCarProcess(btn8, 8, parkingLots[floor], adminBlockState[floor], frame, floor, adminMode);
			}
		});
		btn8.setBounds(80, 260, 50, 80);
		frame.getContentPane().add(btn8);

		//parkSpace[9]
		ParkSpaceButton btn9 = new ParkSpaceButton("H2");
		spaceList.add(btn9);
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inCarFunc.inCarProcess(btn9, 9, parkingLots[floor], adminBlockState[floor], frame, floor, adminMode);
			}
		});
		btn9.setBounds(140, 260, 50, 80);
		frame.getContentPane().add(btn9);

		//parkSpace[10]
		ParkSpaceButton btn10 = new ParkSpaceButton("H3");
		spaceList.add(btn10);
		btn10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inCarFunc.inCarProcess(btn10, 10, parkingLots[floor], adminBlockState[floor], frame, floor, adminMode);
			}
		});
		btn10.setBounds(200, 260, 50, 80);
		frame.getContentPane().add(btn10);

		//parkSpace[11]
		ParkSpaceButton btn11 = new ParkSpaceButton("H4");
		spaceList.add(btn11);
		btn11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inCarFunc.inCarProcess(btn11, 11, parkingLots[floor], adminBlockState[floor], frame, floor, adminMode);
			}
		});
		btn11.setBounds(260, 260, 50, 80);
		frame.getContentPane().add(btn11);

		//parkSpace[12]
		ParkSpaceButton btn12 = new ParkSpaceButton("L1");
		spaceList.add(btn12);
		btn12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inCarFunc.inCarProcess(btn12, 12, parkingLots[floor], adminBlockState[floor], frame, floor, adminMode);
			}
		});
		btn12.setBounds(410, 260, 50, 80);
		frame.getContentPane().add(btn12);

		//parkSpace[13]
		ParkSpaceButton btn13 = new ParkSpaceButton("L2");
		spaceList.add(btn13);
		btn13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inCarFunc.inCarProcess(btn13, 13, parkingLots[floor], adminBlockState[floor], frame, floor, adminMode);
			}
		});
		btn13.setBounds(470, 260, 50, 80);
		frame.getContentPane().add(btn13);

		//parkSpace[14]
		ParkSpaceButton btn14 = new ParkSpaceButton("L3");
		spaceList.add(btn14);
		btn14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inCarFunc.inCarProcess(btn14, 14, parkingLots[floor], adminBlockState[floor], frame, floor, adminMode);
			}
		});
		btn14.setBounds(530, 260, 50, 80);
		frame.getContentPane().add(btn14);

		//parkSpace[15]
		ParkSpaceButton btn15 = new ParkSpaceButton("L4");
		spaceList.add(btn15);
		btn15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inCarFunc.inCarProcess(btn15, 15, parkingLots[floor], adminBlockState[floor], frame, floor, adminMode);
			}
		});
		btn15.setBounds(590, 260, 50, 80);
		frame.getContentPane().add(btn15);

		FloorRadio B1_btn = new FloorRadio(" B1");
		B1_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				floor = 1;
				CollocateSpace collocateSpace = new CollocateSpace();
				collocateSpace.collocateSpace(spaceList, blockList, labelList, 1, parkingLots[floor],
					adminBlockState[floor]);
				setRadioSelected(0);
			}
		});
		B1_btn.setBounds(800, 190, 121, 46);
		radioList.add(B1_btn);
		frame.getContentPane().add(B1_btn);

		FloorRadio B2_btn = new FloorRadio(" B2");
		B2_btn.setBounds(800, 238, 121, 46);
		B2_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				floor = 2;
				CollocateSpace collocateSpace = new CollocateSpace();
				collocateSpace.collocateSpace(spaceList, blockList, labelList, 2, parkingLots[floor],
					adminBlockState[floor]);
				setRadioSelected(1);
			}
		});
		radioList.add(B2_btn);
		frame.getContentPane().add(B2_btn);

		FloorRadio B3_btn = new FloorRadio(" B3");
		B3_btn.setBounds(800, 286, 121, 46);
		B3_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				floor = 3;
				CollocateSpace collocateSpace = new CollocateSpace();
				collocateSpace.collocateSpace(spaceList, blockList, labelList, 3, parkingLots[floor],
					adminBlockState[floor]);
				setRadioSelected(2);
			}
		});
		radioList.add(B3_btn);
		frame.getContentPane().add(B3_btn);

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

		JLabel floorLabel = new JLabel("B1");
		floorLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		floorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		floorLabel.setForeground(new Color(0, 153, 204));
		floorLabel.setFont(new Font("Consolas", Font.BOLD, 40));
		floorLabel.setBounds(800, 10, 50, 64);
		labelList.add(floorLabel);
		frame.getContentPane().add(floorLabel);

		//Parking State
		List<JLabel> stateList = new ArrayList<>();

		//B1 State
		StateLabel b1State = new StateLabel("B1: 여유");
		b1State.setBounds(800, 84, 100, 15);
		stateList.add(b1State);
		frame.getContentPane().add(b1State);

		//B2 State
		StateLabel b2State = new StateLabel("B2: 여유");
		b2State.setBounds(800, 109, 100, 15);
		stateList.add(b2State);
		frame.getContentPane().add(b2State);

		//B3 State
		StateLabel b3State = new StateLabel("B3: 여유");
		b3State.setBounds(800, 134, 100, 15);
		stateList.add(b3State);
		frame.getContentPane().add(b3State);


		//Administrator State
		JLabel adminState = new JLabel();
		adminState.setFont(new Font("굴림", Font.BOLD, 25));
		adminState.setForeground(new Color(255, 255, 0));
		adminState.setHorizontalAlignment(SwingConstants.LEFT);
		adminState.setBounds(800, 404, 183, 46);
		frame.getContentPane().add(adminState);

		AdminButton blockCancel = new AdminButton("블록 해제");
		blockCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminFunc adminFunc = new AdminFunc();
				adminFunc.unBlockSpace(frame, blockCancel, adminBlockState, spaceList);
			}
		});
		blockCancel.setEnabled(false);
		blockCancel.setBounds(912, 348, 100, 46);
		frame.getContentPane().add(blockCancel);
		//Administrator Login
		AdminButton adminLogin = new AdminButton("Admin Login");
		adminLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminFunc adminFunc = new AdminFunc();
				if (!adminMode) {
					adminMode = adminFunc.adminLogin();
					if (adminMode) {
						blockCancel.setEnabled(true);
						adminState.setText("Admin Mode");
						adminLogin.setText("Admin Logout");
					}

				} else {
					adminMode = adminFunc.adminLogout();
					adminLogin.setText("Admin Login");
					adminState.setText("");
					blockCancel.setEnabled(false);
				}
			}
		});
		adminLogin.setBounds(800, 348, 100, 46);
		frame.getContentPane().add(adminLogin);

		CollocateSpace collocateSpace = new CollocateSpace();
		collocateSpace.collocateSpace(spaceList, blockList, labelList, 1, parkingLots[floor], adminBlockState[floor]);
	}

	public void setRadioSelected(int index) {
		if (index == 0) {
			radioList.get(1).setSelected(false);
			radioList.get(2).setSelected(false);
		} else if (index == 1) {
			radioList.get(0).setSelected(false);
			radioList.get(2).setSelected(false);
		} else {
			radioList.get(0).setSelected(false);
			radioList.get(1).setSelected(false);
		}

	}
}
