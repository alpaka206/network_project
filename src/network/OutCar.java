import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;

public class OutCar {
	private LocalDateTime time;
	private String carNum;
	private String floor;
	private int parkSpace;
	private String result;

	public OutCar(LocalDateTime time, String carNum, String floor, int parkSpace) {
		this.time = time;
		this.carNum = carNum;
		this.floor = floor;
		this.parkSpace = parkSpace;
	}

	public String processOut() {
		try {
			Socket socket = new Socket("server address", 12345);
			// 데이터 전송
			PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

			int flag = Integer.parseInt(floor) * 10 + 2;
			writer.println(flag);

			// 서버 응답 받기
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String response = reader.readLine();
			//response 처리 한번 필요!!!!
			if (response.equals("true")) {
				//json 데이터 전송
				jsonMaker(writer, carNum, parkSpace, time);
				//서버 데이터 받기

				String jsonString = reader.readLine();
				result = jsonParser(jsonString);
			}

			socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	private void jsonMaker(PrintWriter writer, String carNum, int parkSpace, LocalDateTime time) {
		String jsonString = String.format("{\"carNum\":\"%s\",\"parkSpace\":%d,\"time\":\"%s\"}",
			carNum, parkSpace, time);
		writer.println(jsonString);
	}

	public String jsonParser(String jsonString) {
		String[] keyValuePairs = jsonString.split(",");
		int cost = 0;
		String carNum = "";

		for (String pair : keyValuePairs) {
			String[] entry = pair.split(":");
			String key = entry[0].trim().replaceAll("\"", "");
			String value = entry[1].trim().replaceAll("\"", "");

			if (key.equals("cost")) {
				cost = Integer.parseInt(value);
			} else if (key.equals("carNum")) {
				carNum = value;
			}
		}

		return cost + "/" + carNum;
	}
}