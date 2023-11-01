package network;

import java.io.*;
import java.net.*;

public class Synchronize {
    private int floor;

    public Synchronize(int floor) {
        this.floor = floor;
    }

    
    public void sendRequestToServer() {
        try(Socket socket = new Socket("server address", 12345);
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        	BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            int flag = floor * 10 + 4;
            writer.println(flag);
            String result = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}