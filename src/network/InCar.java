import java.io.*;
import java.net.*;
import java.time.LocalDateTime;

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
        try {
            Socket socket = new Socket("server address?", 12345);

            // 데이터 전송
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            int flag = floor * 10 + 1;
            writer.println(flag);

            // 서버 응답 받기
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String response = reader.readLine();
            //response 처리 한번 필요!!!!

            if (response.equals("true")) {
                //json 데이터 전송
            	jsonMaker(System.out, carNum, parkSpace, time);
                String result = reader.readLine();
                System.out.println(result);
                //response 처리 한번 필요!!!!
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void jsonMaker(OutputStream outputStream, String carNum, int parkSpace, LocalDateTime time){
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream));

        String jsonString = String.format("{\"carNum\":\"%s\",\"parkSpace\":%d,\"time\":\"%s\"}",
                carNum, parkSpace, time.toString());

        writer.write(jsonString);
        writer.flush();
    }
}