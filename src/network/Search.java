package network;

import java.io.*;
import java.net.*;
import java.time.LocalDateTime;

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
			PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
			LocalDateTime nowTime = LocalDateTime.now();
			int flag = 33;
			jsonMaker(writer, flag, carNum, time);
			String jsonString = reader.readLine();
			result = jsonParser(jsonString);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	private void jsonMaker(PrintWriter writer, int flag, String carNum, LocalDateTime time) {
		String jsonString = String.format("{\"flag\":%d,\"carNum\":\"%s\",\"time\":\"%s\"}",
				flag, carNum, time);
		writer.println(jsonString);
	}

	public String jsonParser(String jsonString) {
		String[] keyValuePairs = jsonString.split(",");
		int cost = 0;
		String useTime = "";
		int spot = 0;
		int thisfloor = 0;
		for (String pair : keyValuePairs) {
			String[] entry = pair.split(":");
			String key = entry[0].trim().replaceAll("\"", "");
			String value = entry[1].trim().replaceAll("\"", "");
			if (key.equals("cost")) {
				cost = Integer.parseInt(value);
			} else if (key.equals("useTime")) {
				useTime = value;
			} else if (key.equals("spot")) {
				spot = Integer.parseInt(value);
			} else if (key.equals("thisfloor")) {
				thisfloor = Integer.parseInt(value);
			}
		}
		return cost + "/" + carNum;
	}
}

