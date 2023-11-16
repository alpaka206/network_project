package network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class Synchronize {
	private NetworkSettings network = new NetworkSettings();
	private int floor;
	private int receiveArray[][] = new int[4][16];

	//private Boolean[][] parkingLots = new Boolean[4][16];
	//private Boolean[][] adminBlockState = new Boolean[4][16];

	public Synchronize(int floor) {
		this.floor = floor;

		/*for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 16; j++) {
				parkingLots[i][j] = false;
				adminBlockState[i][j] = true;
			}
		}*/
	}

	public void receiveCurrentState(Boolean[][] parkingLots, Boolean[][] adminBlockState) {
		try {
			//int flag, len;
			DatagramSocket serverSocket = new DatagramSocket(network.portNum);

			byte[] receiveData = new byte[100];

			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			serverSocket.receive(receivePacket);
			//flag = ByteBuffer.wrap(Arrays.copyOfRange(receivePacket.getData(), 0, 4)).getInt();
			//len = ByteBuffer.wrap(Arrays.copyOfRange(receivePacket.getData(), 4, 8)).getInt();

			for (int i = 1; i < 4; i++) {
				for (int j = 0; j < 16; j++) {
					int n = ByteBuffer.wrap(Arrays.copyOfRange(receivePacket.getData(), 8 + i * 4, 12 + 4 * i))
						.getInt();

					if (n == 1)
						parkingLots[i][j] = true;
					else if (n == 2)
						adminBlockState[i][j] = false;
					else
						parkingLots[i][j] = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}