package network;

import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class OutCar {
	private LocalDateTime time;
	private String carNum;
	private int floor;
	private int parkSpace;
	private String result;

	public OutCar(LocalDateTime time, String carNum, int floor, int parkSpace) {
		this.time = time;
		this.carNum = carNum;
		this.floor = floor;
		this.parkSpace = parkSpace;
	}
	public String processOut() {
        try (Socket socket = new Socket(NetworkSettings.srvIpAddr, NetworkSettings.portNum);
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             DataInputStream in = new DataInputStream(socket.getInputStream())) {
        	
        	String flag = Integer.toString(floor*10 +2);
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/HH/mm");
            String formattedTime = time.format(formatter);
            
            // Header 생성
        	byte[] header = new byte[8];
        	
        	ByteBuffer.wrap(header, 0, 4).put(flag.getBytes());
            int bodySize = 26; // 22 + 4 
            ByteBuffer.wrap(header, 4, 4).putInt(bodySize);

            // Body 생성
            byte[] body = new byte[bodySize];
            ByteBuffer.wrap(body, 0, 22).put(formattedTime.getBytes());
            ByteBuffer.wrap(body, 22, 4).putInt(parkSpace);

            // 데이터 전송
            byte[] request = new byte[bodySize + 8];
            System.arraycopy(header, 0, request, 0, 8);
            System.arraycopy(body, 0, request, 8, bodySize);
            out.write(request);

            // 서버 응답 받기
            byte[] response = new byte[31];
            in.readFully(response);

            // 응답 데이터 처리
            String responseFlag = new String(Arrays.copyOfRange(response, 0, 4));
            boolean isSuccess = response[4] == 1;
            int responseBodySize = ByteBuffer.wrap(response, 5, 4).getInt();


            // Body 데이터 처리
            int price = ByteBuffer.wrap(response, 9, 4).getInt();
            String carNum = new String(Arrays.copyOfRange(response, 13, 18));

            // 결과 문자열 생성
            result = price + "/" + carNum;

        } catch (IOException e) {
            e.printStackTrace();
        }
//	public String processOut() {
//		try(Socket socket = new Socket("server address", 12345);
//			PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
//			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
//			int flag = floor * 10 + 2;
//			jsonMaker(writer, flag, carNum, parkSpace, time);
//			String jsonString = reader.readLine();
//			result = jsonParser(jsonString);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return result;
//	}
	
	
	
	
//	public String processOut() {
//		try {
//			Socket socket = new Socket("server address", 12345);
//			// 데이터 전송
//			PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
//
//			int flag = Integer.parseInt(floor) * 10 + 2;
//			writer.println(flag);
//
//			// 서버 응답 받기
//			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//			String response = reader.readLine();
//			//response 처리 한번 필요!!!!
//			if (response.equals("true")) {
//				//json 데이터 전송
//				jsonMaker(writer, carNum, parkSpace, time);
//				//서버 데이터 받기
//
//				String jsonString = reader.readLine();
//				result = jsonParser(jsonString);
//			}
//
//			socket.close();
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		return result;
//	}

//	private void jsonMaker(PrintWriter writer, int flag, String carNum, int parkSpace, LocalDateTime time) {
//		String jsonString = String.format("{\"flag\":%d,\"carNum\":\"%s\",\"parkSpace\":%d,\"time\":\"%s\"}",
//			flag, carNum, parkSpace, time);
//		writer.println(jsonString);
//	}
//
//	public String jsonParser(String jsonString) {
//		String[] keyValuePairs = jsonString.split(",");
//		int cost = 0;
//		String carNum = "";
//
//		for (String pair : keyValuePairs) {
//			String[] entry = pair.split(":");
//			String key = entry[0].trim().replaceAll("\"", "");
//			String value = entry[1].trim().replaceAll("\"", "");
//
//			if (key.equals("cost")) {
//				cost = Integer.parseInt(value);
//			} else if (key.equals("carNum")) {
//				carNum = value;
//			}
//		}

		return result;
	}
}