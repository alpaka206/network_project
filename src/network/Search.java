package network;

import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;


public class Search {
	private LocalDateTime time;
	private String carNum;
	private String result;
	
	public Search(LocalDateTime time, String carNum) {
        this.time = time;
        this.carNum = carNum;
    }
	
	public String processOut() {
		try (Socket socket = new Socket(NetworkSettings.srvIpAddr, NetworkSettings.portNum);
	             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
	             DataInputStream in = new DataInputStream(socket.getInputStream())) {
			
			String flag = "33";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/HH/mm");
            String formattedTime = time.format(formatter);
			
//	            // Header 생성
//	        	byte[] header = new byte[8];
//	            ByteBuffer.wrap(header, 0, 4).put(flag.getBytes());
//	            int bodySize = 40; // 22 + 4 
//	            ByteBuffer.wrap(header, 4, 4).putInt(bodySize);
//
//	            // Body 생성
//	            byte[] body = new byte[bodySize];
//	            ByteBuffer.wrap(body, 0, 22).put(formattedTime.getBytes());
//	            ByteBuffer.wrap(body, 22, 18).put(carNum.getBytes());
//
//	            // 데이터 전송
//	            byte[] request = new byte[bodySize + 8];
//	            System.arraycopy(header, 0, request, 0, 8);
//	            System.arraycopy(body, 0, request, 8, bodySize);
//	            System.out.print("Request in Hexadecimal: ");
//	            for (byte b : request) {
//	                System.out.print(String.format("%02X ", b));
//	            }
//	            System.out.println();
//	            out.write(request);
//
//	            // 서버 응답 받기
//	            byte[] response = new byte[25];
//	            in.readFully(response);
//
//	            // 응답 데이터 처리
//	            String responseFlag = new String(Arrays.copyOfRange(response, 0, 4));
//	            boolean isSuccess = response[4] == 1;
//	            int responseBodySize = ByteBuffer.wrap(response, 5, 4).getInt();
//
//	            // 검색 실패 시 처리
//	            if (!isSuccess) {
//	                return "Fail";
//	            }
//
//	            
//	            // Body 데이터 처리
//	            int price = ByteBuffer.wrap(response, 9, 4).getInt();
//	            int usetime = ByteBuffer.wrap(response, 13, 4).getInt();
//	            int parkspace = ByteBuffer.wrap(response, 17, 4).getInt();
//	            int floor = ByteBuffer.wrap(response, 21, 4).getInt();
//
//	            int hours = usetime / 60;
//	            int minutes = usetime % 60;
//	            String formattedUseTime = hours + "시간 " + minutes + "분";
//
//	            // 결과 문자열 생성
//	            result = "가격: " + price + "<br>사용시간: " + formattedUseTime + "<br>주차 자리: " + parkspace + "<br>층수: " + floor;
//	            
//	            
//	            System.out.print("Response in Hexadecimal: ");
//	            for (byte b : response) {
//	                System.out.print(String.format("%02X ", b));
//	            }
//	            System.out.println();
//	            System.out.println(result);
	         // Header 생성
	        	byte[] header = new byte[6];
	            ByteBuffer.wrap(header, 0, 2).put(flag.getBytes());
	            int bodySize = 22; // 11 + 11 
	            ByteBuffer.wrap(header, 2, 4).putInt(bodySize);

	            // Body 생성
	            byte[] body = new byte[bodySize];
	            ByteBuffer.wrap(body, 0, 11).put(formattedTime.getBytes());
	            ByteBuffer.wrap(body, 11, 11).put(carNum.getBytes());

	            // 데이터 전송
	            byte[] request = new byte[bodySize + 6];
	            System.arraycopy(header, 0, request, 0, 6);
	            System.arraycopy(body, 0, request, 6, bodySize);
	            System.out.print("Request in Hexadecimal: ");
	            for (byte b : request) {
	                System.out.print(String.format("%02X ", b));
	            }
	            System.out.println();
	            out.write(request);

	            // 서버 응답 받기
	            byte[] response = new byte[23];
	            in.readFully(response);

	            // 응답 데이터 처리
	            String responseFlag = new String(Arrays.copyOfRange(response, 0, 2));
	            boolean isSuccess = response[2] == 1;
	            int responseBodySize = ByteBuffer.wrap(response, 3, 4).getInt();

	            // 검색 실패 시 처리
	            if (!isSuccess) {
	                return "Fail";
	            }

	            
	            // Body 데이터 처리
	            int price = ByteBuffer.wrap(response, 7, 4).getInt();
	            int usetime = ByteBuffer.wrap(response, 11, 4).getInt();
	            int parkspace = ByteBuffer.wrap(response, 15, 4).getInt();
	            int floor = ByteBuffer.wrap(response, 19, 4).getInt();

	            int hours = usetime / 60;
	            int minutes = usetime % 60;
	            String formattedUseTime = hours + "시간 " + minutes + "분";
	            
	            System.out.print("Response in Hexadecimal: ");
	            for (byte b : response) {
	                System.out.print(String.format("%02X ", b));
	            }

	            // 결과 문자열 생성
	            result = "가격: " + price + "<br>사용시간: " + formattedUseTime + "<br>주차 자리: " + parkspace + "<br>층수: " + floor;
		} catch (IOException e) {
			 // 검색 실패 예외 처리
            e.printStackTrace();
            return "Fail";
		}
		return result;
	}
}

