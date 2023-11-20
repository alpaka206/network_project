package org.example;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import static org.example.Main.parking;

public class Main {
    static CarInfo[][] parking = new CarInfo[3][16];

    private static void sendUdpRequest() {
        try {
            // 1. Server configuration
            InetAddress serverAddress = InetAddress.getByName("192.168.123.5");
            int serverPort = 54254;

            DatagramSocket socket = new DatagramSocket();

            // 2. UDP 동기화 request를 전송한다.
            byte[] udpResponse = new byte[54];
            RequestHandler.udpRequest(udpResponse);
            System.out.println(Arrays.toString(udpResponse));

            DatagramPacket packet = new DatagramPacket(udpResponse, udpResponse.length, serverAddress, serverPort);
            socket.send(packet);

            System.out.println("UDP 전송 완료");

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        int port = 54254; // 원하는 포트 번호로 변경

        DataInputStream din = null;
        DataOutputStream dout = null;

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("서버가 " + port + " 포트에서 대기 중...");

            while(true) {
                clientSocket = serverSocket.accept();
                System.out.println("클라이언트 연결됨.");

                din = new DataInputStream(clientSocket.getInputStream());
                dout = new DataOutputStream(clientSocket.getOutputStream());

                // 1. request byte[] 배열
                byte[] request = new byte[64];
                din.read(request);
                System.out.println(Arrays.toString(request));

                // Todo 연결 종료 요청 시 연결 끊기
                if(new String(Arrays.copyOfRange(request,0,2)).equals("66")) break;

                // 2. flag와 bodySize를 추출한다.
                String flag = new String(Arrays.copyOfRange(request, 0, 2));
                int bodySize = ByteBuffer.wrap(request, 2, 4).getInt();

                // 3. body를 추출한다.
                byte[] body = new byte[bodySize];
                System.arraycopy(request, 6, body, 0, bodySize);

                // 4. flag와 body를 넘기고, response body를 얻는다.
                byte[] response = RequestHandler.handleRequest(flag, body); // body 바이트 배열
                System.out.println(Arrays.toString(response));

                dout.write(response);
                dout.flush();

//                dout.write("Connection Closed".getBytes());

                din.close();
                dout.close();
                clientSocket.close();

                sendUdpRequest();
            }
            serverSocket.close();

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
    private int parkSpace;
    private int floor;
}


@Getter
@Builder
@AllArgsConstructor
class InCarRequest {
    private int floor;
    private String carNum;
    private LocalDateTime inCarTime;
    private int parkSpace;
}


@Getter
@Builder
@AllArgsConstructor
class OutCarRequest {
    private int floor;
    private int parkSpace;
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
class AdminRequest {
    private int floor;
    private int parkSpace;
}

class ByteMapper {
    // 1. json -> 입차
    public static InCarRequest byteToInCarRequest(byte[] body, int floor) {

        String carNum = new String(Arrays.copyOfRange(body, 0, 11));
        String inCarTime = new String(Arrays.copyOfRange(body, 11, 11+11));
        int parkSpace = ByteBuffer.wrap(body, 22, 4).getInt();

        return InCarRequest.builder()
                .floor(floor)
                .carNum(carNum)
                .inCarTime(LocalDateTime.now()/* LocalDateTime.parse(inCarTime, timeFormatter*/) // Todo datetime 파싱
                .parkSpace(parkSpace)
                .build(); // 객체가 생성됨.
    }

    // 2. json -> 출차
    public static OutCarRequest byteToOutCarRequest(byte[] body, int floor) {

        String outCarTime = new String(Arrays.copyOfRange(body, 0, 11));
        int parkSpace = ByteBuffer.wrap(body, 11, 4).getInt();

        return OutCarRequest.builder()
                .floor(floor)
                .parkSpace(parkSpace)
                .outCarTime(LocalDateTime.now()/* LocalDateTime.parse(outCarTime, TimeFormatter*/) // Todo datetime 파싱
                .build(); // 객체가 생성됨.
    }

    // 3. json -> 조회
    public static SearchCarRequest byteToSearchCarRequest(byte[] body) {

        String nowTime = new String(Arrays.copyOfRange(body, 0, 11));
        String carNum = new String(Arrays.copyOfRange(body, 11, 11+11));

        return SearchCarRequest.builder()
                .carNum(carNum)
                .nowTime(LocalDateTime.now()/*LocalDateTime.parse(nowTime, timeFormatter*/) // Todo datetime 파싱
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

    public static byte[] inCarProcess(InCarRequest inCarRequest) throws RuntimeException {

        // 1. 로직 실행
        if(searchCarInfo(inCarRequest.getCarNum()) != null) {
            throw new RuntimeException("요청한 자리에 주차된 차량이 있습니다.");
        }

        parking[inCarRequest.getFloor()][inCarRequest.getParkSpace()] =
                CarInfo.builder()
                        .carNum(inCarRequest.getCarNum())
                        .inCartime(inCarRequest.getInCarTime())
                        .parkSpace(inCarRequest.getParkSpace())
                        .floor(inCarRequest.getFloor())
                        .build();

        // 2. responseBody 리턴
        return new byte[0];
    }

    public static byte[] outCarProcess(OutCarRequest outCarRequest) throws RuntimeException {

        byte[] body = new byte[22];

        // 1. 주차된 차량을 확인
        int floor = outCarRequest.getFloor();
        int parkSpace = outCarRequest.getParkSpace();

        if(parking[floor][parkSpace]==null) throw new RuntimeException("해당 자리에 주차된 차량이 없습니다");

        // 2. 주차 시간, 가격 계산
        long time = calculateTime(parking[floor][parkSpace].getInCartime(), outCarRequest.getOutCarTime());
        int price = ((int) time) / 15 * 500;
        System.out.println("price: " + price + "\n" + time/60 +"시간 " + time%60 + "분이 지났습니다.");

        // 3. 주차 차량 삭제 및 responseBody 리턴
        String carNum = parking[outCarRequest.getFloor()][outCarRequest.getParkSpace()].getCarNum();
        parking[outCarRequest.getFloor()][outCarRequest.getParkSpace()] = null;

        ByteBuffer.wrap(body,0,4).putInt(price);
        ByteBuffer.wrap(body,4,11).put(carNum.getBytes());

        return body;
    }

    public static byte[] searchCarProcess(SearchCarRequest searchCarRequest) throws RuntimeException {

        // 1. 로직 실행
        CarInfo findCarInfo = searchCarInfo(searchCarRequest.getCarNum());
        if(findCarInfo==null) throw new RuntimeException("해당 차량번호가 존재하지 않습니다.");

        long time = calculateTime(findCarInfo.getInCartime(), searchCarRequest.getNowTime());
        int price = ((int) time) / 15 * 500;

        System.out.println("price: " + price + "\n" + time/60 +"시간 " + time%60 + "분이 지났습니다."); // 분을 integer로 보내기

        // 2. responseBody 리턴
        byte[] body = new byte[16];
        ByteBuffer.wrap(body, 0, 4).putInt(price);
        ByteBuffer.wrap(body, 4, 4).putInt((int) time);
        ByteBuffer.wrap(body, 8, 4).putInt(findCarInfo.getParkSpace());
        ByteBuffer.wrap(body, 12, 4).putInt(findCarInfo.getFloor());

        return body;
    }

    public static byte[] adminProcess(AdminRequest adminRequest) throws RuntimeException {
        // 1. 로직 실행
        int floor = adminRequest.getFloor();
        int parkSpace = adminRequest.getParkSpace();

        if (parking[floor][parkSpace] == null) {
            parking[floor][parkSpace] = CarInfo.builder()
                    .carNum("Admin")
                    .inCartime(LocalDateTime.now())
                    .parkSpace(parkSpace)
                    .floor(floor)
                    .build();
            System.out.println("어드민 막기");
        } else if(parking[floor][parkSpace].getCarNum().equals("Admin")){
            parking[floor][parkSpace] = null;
            System.out.println("어드민 해제");
        } else {
            throw new RuntimeException("차량이 주차된 자리입니다.");
        }

        // 2. response body 리턴
        return new byte[0];
    }

    public static void synchronize(byte[] udpResponseBody) {

        for(int i=0; i<parking.length; i++) {
            for(int j=0; j<parking[i].length; j++) {
                if(parking[i][j]==null) {
                    udpResponseBody[i * 16 + j] = 0;
                } else if (parking[i][j].getCarNum() == "Admin") {
                    udpResponseBody[i * 16 + j] = 2;
                } else {
                    udpResponseBody[i * 16 + j] = 1;
                }
            }
        }
    }

}

class RequestHandler {
    public static byte[] handleRequest(String flag, byte[] body) {

        byte[] responseHeader = new byte[9];
        byte[] responseBody = null;
        byte[] response = null;

        try {
            // 1. flag에서 floor와 action을 얻는다.
            int floor = Integer.parseInt(flag) / 10 - 1;
            int action = Integer.parseInt(flag) % 10;

            // 2. action에 따라 body byte[] 배열을 알맞은 객체로 변환 / 로직을 실행 / body 배열을 리턴한다.
            switch (action) {
                case 1:
                    // 입차 처리
                    InCarRequest inCarRequest = ByteMapper.byteToInCarRequest(body, floor);
                    responseBody = Process.inCarProcess(inCarRequest);
                    break;
                case 2:
                    // 출차 처리
                    OutCarRequest outCarRequest = ByteMapper.byteToOutCarRequest(body, floor);
                    responseBody = Process.outCarProcess(outCarRequest);
                    break;
                case 3:
                    // 조회 처리
                    SearchCarRequest searchCarRequest = ByteMapper.byteToSearchCarRequest(body);
                    responseBody = Process.searchCarProcess(searchCarRequest);
                    break;
                case 5:
                    AdminRequest adminRequest = ByteMapper.byteToAdminRequest(body, floor);
                    responseBody = Process.adminProcess(adminRequest);
                    break;
                default:
                    throw new RuntimeException("프로토콜 오류");
            }

            // 3. response header 생성
            ByteBuffer.wrap(responseHeader, 0, 2).put(flag.getBytes());
            ByteBuffer.wrap(responseHeader, 2, 1).put((byte) (true ? 1 : 0));
            ByteBuffer.wrap(responseHeader, 3, 4).putInt(responseBody.length);

            // 4. response 생성
            response = new byte[7 + responseBody.length];
            System.arraycopy(responseHeader, 0, response, 0, 7);
            System.arraycopy(responseBody, 0, response, 7, responseBody.length);

        } catch (RuntimeException e) {
            // error response header 생성 -> client 요청의 flag / 실패 / bodySize 0 을 리턴
            ByteBuffer.wrap(responseHeader, 0, 2).put(flag.getBytes());
            ByteBuffer.wrap(responseHeader, 2, 1).put((byte) (false ? 1 : 0));
            ByteBuffer.wrap(responseHeader, 3, 4).putInt(0);

            // error response 생성
            response = new byte[7];
            System.arraycopy(responseHeader, 0, response, 0, 7);
        }

        return response;
    }

    public static void udpRequest(byte[] udpResponse) {
        // UDP 동기화 response 생성
        byte[] udpResponseBody = new byte[48];
        Process.synchronize(udpResponseBody);

        ByteBuffer.wrap(udpResponse, 0, 2).put("44".getBytes());
        ByteBuffer.wrap(udpResponse, 2, 4).putInt(48);
        ByteBuffer.wrap(udpResponse, 6, 48).put(udpResponseBody);
    }
}