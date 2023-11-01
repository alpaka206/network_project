package network;

import java.io.*;
import java.net.*;

public class Admin {
    private int floor;
    private int parkSpace;

    public Admin ( int floor, int parkSpace) {
        this.floor = floor;
        this.parkSpace = parkSpace;
    }

    
    public void sendRequestToServer() {
        try (Socket socket = new Socket("server address", 12345);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        	 BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
            int flag = floor * 10 + 5;
            jsonMaker(writer, flag, parkSpace);
            String result = reader.readLine();
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void jsonMaker(PrintWriter writer, int flag,  int parkSpace ){

        String jsonString = String.format("{\"flag\":%d,\"parkSpace\":%d}",
                flag,  parkSpace );

        writer.write(jsonString);
        writer.flush();
    }
}