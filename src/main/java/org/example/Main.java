package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import static org.example.Main.parking;
import static org.example.Main.spotArr;

public class Main {
    static String[] spotArr = {"P1", "P2", "P3", "P4",
            "J1", "J2", "J3", "J4",
            "H1", "H2", "H3", "H4",
            "F1", "F2", "F3", "F4"};

    static CarInfo[][] parking = new CarInfo[3][16];

    public static void main(String[] args) {

        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        int port = 54254; // 원하는 포트 번호로 변경

        DataInputStream din = null;
        DataOutputStream dout = null;

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("서버가 " + port + " 포트에서 대기 중...");

            clientSocket = serverSocket.accept();
            System.out.println("클라이언트 연결됨.");

            din = new DataInputStream(clientSocket.getInputStream());
            dout = new DataOutputStream(clientSocket.getOutputStream());

            // 1. request byte[] 배열
            byte[] request = new byte[64];
            din.readFully(request);

            // 2. flag와 bodySize를 추출한다.
            String flag = new String(Arrays.copyOfRange(request, 0, 4));
            int bodySize = ByteBuffer.wrap(request, 4, 4).getInt();

            // 3. body를 추출한다.
            byte[] body = new byte[bodySize];
            System.arraycopy(request, 8, body, 0, bodySize);

            // 4. flag와 body를 넘긴다.
            RequestHandler.handleRequest(flag, body);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

@Getter
@Builder
@AllArgsConstructor
class CarInfo {
    private String carNum;
    private LocalDateTime inCartime;
    private String parkSpace; // P4
    private int floor;
}


@Getter
@Builder
@AllArgsConstructor
class InCarRequest {
    private int floor;
    private String carNum;
    private LocalDateTime inCarTime;
    private String spotString;
    private int spotInt;
}


@Getter
@Builder
@AllArgsConstructor
class OutCarRequest {
    private int floor;
    private int spotInt;
    private LocalDateTime outCarTime;
}

@Getter
@Builder
@AllArgsConstructor
class SearchCarRequest {
    private String carNum;
    private LocalDateTime nowTime;
    private int floor;
}

@Getter
@Builder
@AllArgsConstructor
class Response {
    private Boolean isSuccess;
    private Object message;
}

@Getter
@Builder
@AllArgsConstructor
class OutCarResponse {
    private int price;
    private String carNum;
}

@Getter
@Builder
@AllArgsConstructor
class SearchCarResponse {
    private int price;
    private String useTime;
    private int parkSpace;
    private int floor;
}

@Getter
@Builder
@AllArgsConstructor
class AdminRequest {
    private int floor;
    private int parkSpace;
}

class ByteMapper {
    // 1. json -> 입차
    public static InCarRequest byteToInCarRequest(byte[] body, int floor) {

        String carNum = new String(Arrays.copyOfRange(body, 0, 18));
        String inCarTime = new String(Arrays.copyOfRange(body, 18, 18+22));
        int parkSpace = ByteBuffer.wrap(body, 40, 4).getInt();

        return InCarRequest.builder()
                .floor(floor)
                .carNum(carNum)
                .inCarTime(LocalDateTime.parse(inCarTime/*, timeFormatter*/))
                .spotString(spotArr[parkSpace])
                .spotInt(parkSpace)
                .build(); // 객체가 생성됨.
    }

    // 2. json -> 출차
    public static OutCarRequest byteToOutCarRequest(byte[] body, int floor) {

        String outCarTime = new String(Arrays.copyOfRange(body, 0, 22));
        int parkSpace = ByteBuffer.wrap(body, 22, 4).getInt();

        return OutCarRequest.builder()
                .floor(floor)
                .spotInt(parkSpace)
                .outCarTime(LocalDateTime.parse(outCarTime/*, TimeFormatter*/))
                .build(); // 객체가 생성됨.
    }

    // 3. json -> 조회
    public static SearchCarRequest byteToSearchCarRequest(byte[] body) {

        String nowTime = new String(Arrays.copyOfRange(body, 0, 22));
        String carNum = new String(Arrays.copyOfRange(body, 22, 22+4));

        return SearchCarRequest.builder()
                .carNum(carNum)
                .nowTime(LocalDateTime.parse(nowTime/*, timeFormatter*/))
                .build();
    }

    public static AdminRequest byteToAdminRequest(byte[] body, int floor) {

        int parkSpace = ByteBuffer.wrap(body, 0, 4).getInt();

        return AdminRequest.builder()
                .floor(floor)
                .parkSpace(parkSpace)
                .build();
    }
}

// 입차, 출차, 조회 처리하는 클래스
class Process {

    // 차량 조회 메서드
    private static CarInfo searchCarInfo(String carNum) {
        for (int i = 0; i < parking.length; i++) {
            for (int j = 0; j < parking[i].length; j++) {
                CarInfo carInfo = parking[i][j];
                if (carInfo != null && carInfo.getCarNum().equals(carNum)) {
                    return carInfo;
                }
            }
        }
        return null;
    }

    private static long calculateTime(LocalDateTime inCarTime, LocalDateTime time/*out, 그냥조회*/) {
        Duration duration = Duration.between(inCarTime, time);
        return duration.toMinutes();
    }

    public static void inCarProcess(InCarRequest inCarRequest) {
        if(searchCarInfo(inCarRequest.getCarNum()) != null) {
            throw new RuntimeException("요청한 자리에 주차된 차량이 있습니다.");
        }

        parking[inCarRequest.getFloor()][inCarRequest.getSpotInt()] =
                CarInfo.builder()
                        .carNum(inCarRequest.getCarNum())
                        .inCartime(inCarRequest.getInCarTime())
                        .parkSpace(inCarRequest.getSpotString())
                        .floor(inCarRequest.getFloor())
                        .build();
    }

    public static void outCarProcess(OutCarRequest outCarRequest) {
        try {

            int floor = outCarRequest.getFloor();
            int parkSpace = outCarRequest.getSpotInt();

            if(parking[floor][parkSpace]==null) throw new RuntimeException("해당 자리에 주차된 차량이 없습니다");
            long time = calculateTime(parking[floor][parkSpace].getInCartime(), outCarRequest.getOutCarTime());

            System.out.println("price: " + (time/15)*500 + "\n" + time/60 +"시간 " + time%60 + "분이 지났습니다.");
            parking[outCarRequest.getFloor()][outCarRequest.getSpotInt()] = null;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void searchCarProcess(SearchCarRequest searchCarRequest) {
        try {

            CarInfo findCarInfo = searchCarInfo(searchCarRequest.getCarNum());
            if(findCarInfo==null) throw new RuntimeException("해당 차량번호가 존재하지 않습니다.");

            long time = calculateTime(findCarInfo.getInCartime(), searchCarRequest.getNowTime());
            System.out.println("price: " + (time/15)*500 + "\n" + time/60 +"시간 " + time%60 + "분이 지났습니다."); // 분을 integer로 보내기

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void adminProcess(AdminRequest adminRequest){
        try{
            int floor = adminRequest.getFloor();
            int parkSpace = adminRequest.getParkSpace();

            if (parking[floor][parkSpace] == null) {
                parking[floor][parkSpace] = CarInfo.builder()
                        .carNum("Admin")
                        .inCartime(null)
                        .parkSpace(null)
                        .floor(floor) // 여기에 floor 값을 설정해야 합니다.
                        .build();
            } else if(parking[floor][parkSpace].getCarNum().equals("Admin")){
                parking[floor][parkSpace] = null;
            } else {
                throw new RuntimeException("차량이 주차된 자리입니다.");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void synchronize() {
        int[][] response = new int[3][16];

        for(int i=0; i<parking.length; i++) {
            for(int j=0; j<parking[i].length; j++) {
                if(parking[i][j]==null) {
                    response[i][j] = 0;
                } else if (parking[i][j].getCarNum() == "Admin") {
                    response[i][j] = 2;
                } else {
                    response[i][j] = 1;
                }
            }
        }

        System.out.println(Arrays.deepToString(response));
    }

}

class RequestHandler {
    public static void handleRequest(String flag, byte[] body) {
        try {
            // 1. flag에서 floor와 action을 얻는다.
            int floor = Character.getNumericValue(flag.indexOf(0));
            int action = Character.getNumericValue(flag.indexOf(1));

            // 2. action에 따라 body byte[] 배열을 알맞은 객체로 변환한다.
            switch (action) {
                case 1:
                    // 입차 처리
                    InCarRequest inCarRequest = ByteMapper.byteToInCarRequest(body, floor);
                    Process.inCarProcess(inCarRequest);
                    break;
                case 2:
                    // 출차 처리
                    OutCarRequest outCarRequest = ByteMapper.byteToOutCarRequest(body, floor);
                    Process.outCarProcess(outCarRequest);
                    break;
                case 3:
                    // 조회 처리
                    SearchCarRequest searchCarRequest = ByteMapper.byteToSearchCarRequest(body);
                    Process.searchCarProcess(searchCarRequest);
                    break;
                case 4:
                    // 동기화
                    Process.synchronize();
                    break;
                case 5:
                    AdminRequest adminRequest = ByteMapper.byteToAdminRequest(body, floor);
                    Process.adminProcess(adminRequest);
                    break;
                default:
                    throw new RuntimeException("프로토콜 오류");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}