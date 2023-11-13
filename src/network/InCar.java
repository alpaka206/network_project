package network;

import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.nio.ByteBuffer;
import java.util.Arrays;

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
    	try (Socket socket = new Socket("server address", 12345);
    	         DataOutputStream out = new DataOutputStream(socket.getOutputStream());
    	         DataInputStream in = new DataInputStream(socket.getInputStream())) {

        	String flag = Integer.toString(floor*10 +1);

            // header
//            byte[] header = new byte[8];
        	byte[] header = new byte[9];
//            ByteBuffer.wrap(header).put(flag.getBytes());
        	ByteBuffer.wrap(header, 0, 4).put(flag.getBytes());
            int bodySize = 38; // 18 + 16 + 4
            ByteBuffer.wrap(header, 4, 4).putInt(bodySize);
//            out.write(header);

            // body
            byte[] body = new byte[bodySize];
            ByteBuffer.wrap(body, 0, 18).put(carNum.getBytes());
            ByteBuffer.wrap(body, 18, 16).putLong(time.toEpochSecond(null));
            ByteBuffer.wrap(body, 34, 4).putInt(parkSpace);
//            out.write(body);
            byte[] send = new byte[bodySize + 8];
            System.arraycopy(header, 0, send, 0, 8);
            System.arraycopy(body, 0, send, 8, bodySize);
            out.write(send);
            
//            String result = reader.readLine();
//            System.out.println(result);
            in.readFully(header);

            // 헤더에서 flag, isSuccess, bodySize 추출하기
            String responseFlag = new String(Arrays.copyOfRange(header, 0, 4));
            boolean isSuccess = header[4] == 1;
            int responseBodySize = ByteBuffer.wrap(header, 5, 4).getInt();

            // 바디 응답 읽기
            byte[] responseBody = new byte[responseBodySize];
            in.readFully(responseBody);

            // 필요한 처리를 수행하기 위해 응답 바디를 문자열로 변환
            String responseBodyString = new String(responseBody);
            
            System.out.println("응답 Flag: " + responseFlag);
            System.out.println("성공 여부: " + isSuccess);
            System.out.println("응답 바디: " + responseBodyString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}