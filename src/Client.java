import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import components.BlockButton;
import components.FloorRadio;
import components.ParkSpaceButton;
import components.ParkingLotFrame;
import functions.CollocateSpace;
import functions.InCarFunc;

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

	public InCarFunc inCarFunc = new InCarFunc();

	private Boolean[][] parkingLots = new Boolean[4][16];

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
		for (int i = 0; i < 16; i++) {
			parkSpace[i] = false;
			//System.out.println("parkSpace[" + i +"]" + parkSpace[i]);
		}
	}

	private void initialize() {

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 16; j++) {
				parkingLots[i][j] = false;
			}
		}

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
				inCarFunc.inCarProcess(btn0, 0, parkingLots[floor], frame, floor);
			}
		});
		btn0.setBounds(80, 10, 50, 80);
		frame.getContentPane().add(btn0);

		//parkSpace[1]
		ParkSpaceButton btn1 = new ParkSpaceButton("P2");
		spaceList.add(btn1);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inCarFunc.inCarProcess(btn1, 1, parkingLots[floor], frame, floor);
			}
		});
		btn1.setBounds(140, 10, 50, 80);
		frame.getContentPane().add(btn1);

		//parkSpace[2]
		ParkSpaceButton btn2 = new ParkSpaceButton("P3");
		spaceList.add(btn2);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inCarFunc.inCarProcess(btn2, 2, parkingLots[floor], frame, floor);
			}
		});
		btn2.setBounds(200, 10, 50, 80);
		frame.getContentPane().add(btn2);

		//parkSpace[3]
		ParkSpaceButton btn3 = new ParkSpaceButton("P4");
		spaceList.add(btn3);
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inCarFunc.inCarProcess(btn3, 3, parkingLots[floor], frame, floor);
			}
		});
		btn3.setBounds(260, 10, 50, 80);
		frame.getContentPane().add(btn3);

		//parkSpace[4]
		ParkSpaceButton btn4 = new ParkSpaceButton("J1");
		spaceList.add(btn4);
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inCarFunc.inCarProcess(btn4, 4, parkingLots[floor], frame, floor);
			}
		});
		btn4.setBounds(410, 10, 50, 80);
		frame.getContentPane().add(btn4);

		//parkSpace[5]
		ParkSpaceButton btn5 = new ParkSpaceButton("J2");
		spaceList.add(btn5);
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inCarFunc.inCarProcess(btn5, 5, parkingLots[floor], frame, floor);
			}
		});
		btn5.setBounds(470, 10, 50, 80);
		frame.getContentPane().add(btn5);

		//parkSpace[6]
		ParkSpaceButton btn6 = new ParkSpaceButton("J3");
		spaceList.add(btn6);
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inCarFunc.inCarProcess(btn6, 6, parkingLots[floor], frame, floor);
			}
		});
		btn6.setBounds(530, 10, 50, 80);
		frame.getContentPane().add(btn6);

		//parkSpace[7]
		ParkSpaceButton btn7 = new ParkSpaceButton("J4");
		spaceList.add(btn7);
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inCarFunc.inCarProcess(btn7, 7, parkingLots[floor], frame, floor);
			}
		});
		btn7.setBounds(590, 10, 50, 80);
		frame.getContentPane().add(btn7);

		//parkSpace[8]
		ParkSpaceButton btn8 = new ParkSpaceButton("H1");
		spaceList.add(btn8);
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inCarFunc.inCarProcess(btn8, 8, parkingLots[floor], frame, floor);
			}
		});
		btn8.setBounds(80, 260, 50, 80);
		frame.getContentPane().add(btn8);

		//parkSpace[9]
		ParkSpaceButton btn9 = new ParkSpaceButton("H2");
		spaceList.add(btn9);
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inCarFunc.inCarProcess(btn9, 9, parkingLots[floor], frame, floor);
			}
		});
		btn9.setBounds(140, 260, 50, 80);
		frame.getContentPane().add(btn9);

		//parkSpace[10]
		ParkSpaceButton btn10 = new ParkSpaceButton("H3");
		spaceList.add(btn10);
		btn10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inCarFunc.inCarProcess(btn10, 10, parkingLots[floor], frame, floor);
			}
		});
		btn10.setBounds(200, 260, 50, 80);
		frame.getContentPane().add(btn10);

		//parkSpace[11]
		ParkSpaceButton btn11 = new ParkSpaceButton("H4");
		spaceList.add(btn11);
		btn11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inCarFunc.inCarProcess(btn11, 11, parkingLots[floor], frame, floor);
			}
		});
		btn11.setBounds(260, 260, 50, 80);
		frame.getContentPane().add(btn11);

		//parkSpace[12]
		ParkSpaceButton btn12 = new ParkSpaceButton("L1");
		spaceList.add(btn12);
		btn12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inCarFunc.inCarProcess(btn12, 12, parkingLots[floor], frame, floor);
			}
		});
		btn12.setBounds(410, 260, 50, 80);
		frame.getContentPane().add(btn12);

		//parkSpace[13]
		ParkSpaceButton btn13 = new ParkSpaceButton("L2");
		spaceList.add(btn13);
		btn13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inCarFunc.inCarProcess(btn13, 13, parkingLots[floor], frame, floor);
			}
		});
		btn13.setBounds(470, 260, 50, 80);
		frame.getContentPane().add(btn13);

		//parkSpace[14]
		ParkSpaceButton btn14 = new ParkSpaceButton("L3");
		spaceList.add(btn14);
		btn14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inCarFunc.inCarProcess(btn14, 14, parkingLots[floor], frame, floor);
			}
		});
		btn14.setBounds(530, 260, 50, 80);
		frame.getContentPane().add(btn14);

		//parkSpace[15]
		ParkSpaceButton btn15 = new ParkSpaceButton("L4");
		spaceList.add(btn15);
		btn15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inCarFunc.inCarProcess(btn15, 15, parkingLots[floor], frame, floor);
			}
		});
		btn15.setBounds(590, 260, 50, 80);
		frame.getContentPane().add(btn15);

		FloorRadio B1_btn = new FloorRadio(" B1");
		B1_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				floor = 1;
				CollocateSpace collocateSpace = new CollocateSpace();
				collocateSpace.collocateSpace(spaceList, blockList, labelList, 1, parkingLots[floor]);
			}
		});
		B1_btn.setBounds(800, 190, 121, 46);
		frame.getContentPane().add(B1_btn);

		FloorRadio B2_btn = new FloorRadio(" B2");
		B2_btn.setBounds(800, 238, 121, 46);
		B2_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				floor = 2;
				CollocateSpace collocateSpace = new CollocateSpace();
				collocateSpace.collocateSpace(spaceList, blockList, labelList, 2, parkingLots[floor]);
			}
		});
		frame.getContentPane().add(B2_btn);

		FloorRadio B3_btn = new FloorRadio(" B3");
		B3_btn.setBounds(800, 286, 121, 46);
		B3_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				floor = 3;
				CollocateSpace collocateSpace = new CollocateSpace();
				collocateSpace.collocateSpace(spaceList, blockList, labelList, 3, parkingLots[floor]);
			}
		});
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
