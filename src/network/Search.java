package network;

import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.time.ZoneOffset;


public class Search {
	private LocalDateTime time;
	private String carNum;
	private String result;
	
	public Search(LocalDateTime time, String carNum) {
        this.time = time;
        this.carNum = carNum;
    }
	
	public String processOut() {
		try (Socket socket = new Socket("server address", 12345);
	             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
	             DataInputStream in = new DataInputStream(socket.getInputStream())) {

	            // Header 생성
	        	byte[] header = new byte[9];
	            String flag = "33";
	            ByteBuffer.wrap(header, 0, 4).put(flag.getBytes());
	            int bodySize = 20; // 16 (LocalDateTime) + 4 (parkSpace)
	            ByteBuffer.wrap(header, 4, 4).putInt(bodySize);

	            // Body 생성
	            byte[] body = new byte[bodySize];
	            ByteBuffer.wrap(body, 0, 16).putLong(time.toEpochSecond(null));
	            ByteBuffer.wrap(body, 16, 18).put(carNum.getBytes());

	            // 데이터 전송
	            out.write(header);
	            out.write(body);

	            // 서버 응답 받기
	            byte[] response = new byte[9]; // Header는 9바이트로 수정
	            in.readFully(response);

	            // 응답 데이터 처리
	            String responseFlag = new String(Arrays.copyOfRange(response, 0, 4));
	            boolean isSuccess = response[4] == 1;
	            int responseBodySize = ByteBuffer.wrap(response, 5, 4).getInt();

	            // Body 생성
	            byte[] responseBody = new byte[responseBodySize];
	            in.readFully(responseBody);

	            // Body 데이터 처리
	            int price = ByteBuffer.wrap(responseBody, 0, 4).getInt();
	            long epochSeconds = ByteBuffer.wrap(responseBody, 4, 12).getLong();
	            int nano = ByteBuffer.wrap(responseBody, 16, 4).getInt();
	            LocalDateTime usetime = LocalDateTime.ofEpochSecond(epochSeconds, nano, ZoneOffset.UTC);
	            int parkspace = ByteBuffer.wrap(responseBody, 20, 4).getInt();
	            int floor = ByteBuffer.wrap(responseBody, 24, 4).getInt();
	            // 결과 문자열 생성
	            result = price + "/" + carNum;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}

