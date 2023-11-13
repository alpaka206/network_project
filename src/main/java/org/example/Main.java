package org.example;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import static org.example.Main.parking;
import static org.example.Main.spotArr;
import static org.example.RequestHandler.handleRequest;

public class Main {
    static String[] spotArr = {"P1", "P2", "P3", "P4",
            "J1", "J2", "J3", "J4",
            "H1", "H2", "H3", "H4",
            "F1", "F2", "F3", "F4"};

    static CarInfo[][] parking = new CarInfo[3][16];

    public static void main(String[] args) {
//        클라이언트로부터 받은 JSON 문자열
//        String json = "{\"flag\":15,\"parkSpace\":\"1\"}";
        String json = "{\"flag\":44}";

        parking[0][1] = CarInfo.builder()
                .carNum("12가 1234")
                .inCartime(LocalDateTime.parse("2023-09-26T06:00:00"))
                .parkSpace("1")
                .floor(0)
                .build();

        parking[1][8] = CarInfo.builder()
                .carNum("Admin")
                .inCartime(LocalDateTime.parse("2023-09-26T06:00:00"))
                .parkSpace("1")
                .floor(0)
                .build();

        // 요청 처리
        handleRequest(json);
    }

//        int port = 8080; // 원하는 포트 번호로 변경
//        try {
//            ServerSocket serverSocket = new ServerSocket(port);
//            System.out.println("서버가 " + port + " 포트에서 대기 중...");
//
//            while (true) {
//                Socket clientSocket = serverSocket.accept();
//                 클라이언트 요청을 처리하는 스레드를 시작하거나, 핸들러 함수를 호출하는 방식으로 진행
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
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

class JsonMapper {
    // 1. json -> 입차
    public static InCarRequest jsonToInCarRequest(JsonNode json, int floor) {
        return InCarRequest.builder()
                .floor(floor)
                .carNum(json.get("carNum").asText())
                .inCarTime(LocalDateTime.parse/*문자열 -> 시간 객체타입으로 변환*/(json.get("inCarTime").asText()/* json 시간 문자열을 가져옴 */))
                .spotString(spotArr[json.get("parkSpace").asInt()])
                .spotInt(json.get("parkSpace").asInt())
                .build(); // 객체가 생성됨.
    }
    // 2. json -> 출차
    public static OutCarRequest jsonToOutCarRequest(JsonNode json, int floor) {
        return OutCarRequest.builder()
                .floor(floor)
                .spotInt(json.get("parkSpace").asInt())
                .outCarTime(LocalDateTime.parse/*문자열 -> 시간 객체타입으로 변환*/(json.get("outCarTime").asText()/* json 시간 문자열을 가져옴 */))
                .build(); // 객체가 생성됨.
    }
    // 3. json -> 조회
    public static SearchCarRequest jsonToSearchCarRequest(JsonNode json) {
        return SearchCarRequest.builder()
                .carNum(json.get("carNum").asText())
                .nowTime(LocalDateTime.parse/*문자열 -> 시간 객체타입으로 변환*/(json.get("nowTime").asText()/* json 시간 문자열을 가져옴 */))
                .build();
    }
    public static AdminRequest jsonToAdminRequest(JsonNode json, int floor) {
        return AdminRequest.builder()
                .floor(floor)
                .parkSpace(json.get("parkSpace").asInt())
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
            System.out.println("price: " + (time/15)*500 + "\n" + time/60 +"시간 " + time%60 + "분이 지났습니다.");

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
    public static void handleRequest(String jsonRequest) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode json = objectMapper.readTree(jsonRequest);
            int flag = json.get("flag").asInt();
            int floor = flag / 10 - 1;
            int action = flag % 10;

            switch (action) {
                case 1:
                    // json -> 입차객체
                    InCarRequest inCarRequest = JsonMapper.jsonToInCarRequest(json, floor);
                    // 입차 처리 + 층수
                    Process.inCarProcess(inCarRequest);
                    break;
                case 2:
                    // 출차 처리
                    OutCarRequest outCarRequest = JsonMapper.jsonToOutCarRequest(json, floor);
                    // 출차 처리
                    Process.outCarProcess(outCarRequest);
                    break;
                case 3:
                    // 조회 처리
                    SearchCarRequest searchCarRequest = JsonMapper.jsonToSearchCarRequest(json);
                    Process.searchCarProcess(searchCarRequest);
                    break;
                case 4:
                    Process.synchronize();
                    break;
                case 5:
                    AdminRequest adminRequest = JsonMapper.jsonToAdminRequest(json, floor);
                    Process.adminProcess(adminRequest);
                    break;
                default:
                    // 잘못된 action 값 처리
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}