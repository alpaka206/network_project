package network;

import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.time.format.DateTimeFormatter;


public class InCar {
    private LocalDateTime time;
    private String carNum;
    private int floor;
    private int parkSpace;
    
    

    public InCar(LocalDateTime time, String carNum, int floor, int parkSpace) {
        this.time = time;
        this.carNum = carNum;
        this.floor = floor;
        this.parkSpace = parkSpace;
    }
   
    

    public void sendRequestToServer() {
    	try (Socket socket = new Socket(NetworkSettings.srvIpAddr, NetworkSettings.portNum);
    	         DataOutputStream out = new DataOutputStream(socket.getOutputStream());
    	         DataInputStream in = new DataInputStream(socket.getInputStream())) {

        	String flag = Integer.toString(floor*10 +1);
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/HH/mm");
            String formattedTime = time.format(formatter);
            
            // header
            byte[] header = new byte[8];
        	ByteBuffer.wrap(header, 0, 4).put(flag.getBytes());
            int bodySize = 44; // 18 + 22 + 4
            ByteBuffer.wrap(header, 4, 4).putInt(bodySize);

            // body
            byte[] body = new byte[bodySize];
            ByteBuffer.wrap(body, 0, 18).put(carNum.getBytes());
            ByteBuffer.wrap(body, 18, 22).put(formattedTime.getBytes());
            ByteBuffer.wrap(body, 40, 4).putInt(parkSpace);

            byte[] request = new byte[bodySize + 8];
            System.arraycopy(header, 0, request, 0, 8);
            System.arraycopy(body, 0, request, 8, bodySize);
            System.out.print("Request in Hexadecimal: ");
            for (byte b : request) {
                System.out.print(String.format("%02X ", b));
            }
            System.out.println();
            out.write(request);
            
            // 서버 응답 받기
            byte[] response = new byte[9];
            in.readFully(response);
            
            // 응답 데이터 처리
            String responseFlag = new String(Arrays.copyOfRange(response, 0, 4));
            boolean isSuccess = response[4] == 1;
            int responseBodySize = ByteBuffer.wrap(response, 5, 4).getInt();
            
            
            System.out.print("Response in Hexadecimal: ");
            for (byte b : response) {
                System.out.print(String.format("%02X ", b));
            }
            System.out.println();
            
//         // header
//            byte[] header = new byte[6];
//        	ByteBuffer.wrap(header, 0, 2).put(flag.getBytes());
//            int bodySize = 26; // 11 + 11 + 4
//            ByteBuffer.wrap(header, 2, 4).putInt(bodySize);
//
//            // body
//            byte[] body = new byte[bodySize];
//            ByteBuffer.wrap(body, 0, 11).put(carNum.getBytes());
//            ByteBuffer.wrap(body, 11, 11).put(formattedTime.getBytes());
//            ByteBuffer.wrap(body, 22, 4).putInt(parkSpace);
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
//            // 서버 응답 받기
//            byte[] response = new byte[7];
//            in.readFully(response);
//            
//            // 응답 데이터 처리
//            String responseFlag = new String(Arrays.copyOfRange(response, 0, 2));
//            boolean isSuccess = response[2] == 1;
//            int responseBodySize = ByteBuffer.wrap(response, 3, 4).getInt();
//            
//            
//            System.out.print("Response in Hexadecimal: ");
//            for (byte b : response) {
//                System.out.print(String.format("%02X ", b));
//            }
//            System.out.println();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}