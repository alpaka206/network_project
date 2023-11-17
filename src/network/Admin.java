package network;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.Arrays;


public class Admin {
    private int floor;
    private int parkSpace;

    public Admin ( int floor, int parkSpace) {
        this.floor = floor;
        this.parkSpace = parkSpace;
    }

    
    public void sendRequestToServer() {
    	try (Socket socket = new Socket(NetworkSettings.srvIpAddr, NetworkSettings.portNum);
    	         DataOutputStream out = new DataOutputStream(socket.getOutputStream());
    	         DataInputStream in = new DataInputStream(socket.getInputStream())) {

        	String flag = Integer.toString(floor*10 +5);

            // header
            byte[] header = new byte[8];
        	ByteBuffer.wrap(header, 0, 4).put(flag.getBytes());
            int bodySize = 4; 
            ByteBuffer.wrap(header, 4, 4).putInt(bodySize);

            // body
            byte[] body = new byte[bodySize];
            ByteBuffer.wrap(body, 0, 4).putInt(parkSpace);
            
            byte[] request = new byte[bodySize + 8];
            System.arraycopy(header, 0, request, 0, 8);
            System.arraycopy(body, 0, request, 8, bodySize);
            System.out.print("Request in Hexadecimal: ");
            for (byte b : request) {
                System.out.print(String.format("%02X ", b));
            }
            System.out.println();
            out.write(request);
            
            byte[] response = new byte[9];
            in.readFully(response);


            String responseFlag = new String(Arrays.copyOfRange(response, 0, 4));
            boolean isSuccess = response[4] == 1;
            int responseBodySize = ByteBuffer.wrap(response, 5, 4).getInt();

            
            
            
            
            
//         // header
//            byte[] header = new byte[6];
//        	ByteBuffer.wrap(header, 0, 2).put(flag.getBytes());
//            int bodySize = 4; 
//            ByteBuffer.wrap(header, 2, 4).putInt(bodySize);
//
//            // body
//            byte[] body = new byte[bodySize];
//            ByteBuffer.wrap(body, 0, 4).putInt(parkSpace);
//            
//            byte[] request = new byte[bodySize + 6];
//            System.arraycopy(header, 0, request, 0, 6);
//            System.arraycopy(body, 0, request, 6, bodySize);
//            System.out.print("Request in Hexadecimal: ");
//            for (byte b : request) {
//                System.out.print(String.format("%02X ", b));
//            }
//            System.out.println();
//            out.write(request);
//            
//            byte[] response = new byte[7];
//            in.readFully(response);
//
//
//            String responseFlag = new String(Arrays.copyOfRange(response, 0, 2));
//            boolean isSuccess = response[2] == 1;
//            int responseBodySize = ByteBuffer.wrap(response, 3, 4).getInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}