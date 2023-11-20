package network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class Synchronize {
	private NetworkSettings network = new NetworkSettings();
	private int floor;
	private int receiveArray[][] = new int[4][16];

	private Boolean[][] parkingLots = new Boolean[4][16];
	private Boolean[][] adminBlockState = new Boolean[4][16];

	public Synchronize(int floor) {
		this.floor = floor;

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 16; j++) {
				parkingLots[i][j] = false;
				adminBlockState[i][j] = true;
			}
		}
	}

	public void receiveCurrentState(Boolean[][] parkingLots, Boolean[][] adminBlockState) {
		try {
			//int flag, len;
			DatagramSocket serverSocket = new DatagramSocket(network.portNum);

			byte[] receiveData = new byte[100];

			DatagramPacket receivePacket = new DatagramPacket(receiveData, 54);
			serverSocket.setSoTimeout(100000);
			serverSocket.receive(receivePacket);
			//flag = ByteBuffer.wrap(Arrays.copyOfRange(receivePacket.getData(), 0, 4)).getInt();
			//len = ByteBuffer.wrap(Arrays.copyOfRange(receivePacket.getData(), 4, 8)).getInt();

			System.out.print("Response in Synchronzie: ");
			for (byte b : receiveData) {
				System.out.print(String.format("%02X ", b));
			}
			System.out.println();

			System.out.println("received Data:");
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 16; j++) {
					int n = ByteBuffer
						.wrap(Arrays.copyOfRange(receivePacket.getData(), 6 + i * 16 + j, 6 + i * 16 + j + 1))
						.get();

					System.out.print(n + " ");
					if (n == 1)
						parkingLots[i + 1][j] = true;
					else if (n == 2)
						adminBlockState[i + 1][j] = false;
					else
						parkingLots[i + 1][j] = false;
				}

				System.out.println();
			}
			serverSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}