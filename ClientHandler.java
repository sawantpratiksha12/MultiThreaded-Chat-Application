package task3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

 class ClientHandler implements Runnable {
	 private Socket socket;
	    private PrintWriter out;
	    private BufferedReader in;

	    ClientHandler(Socket socket) {
	        this.socket = socket;
	    }

	    public void run() {
	        try {
	            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	            out = new PrintWriter(socket.getOutputStream(), true);

	            String message;
	            while ((message = in.readLine()) != null) {
	                System.out.println("Received: " + message);
	                ChatSever.broadcast(message, this);
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            ChatSever.removeClient(this);
	            try {
	                socket.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    void sendMessage(String message) {
	        out.println(message);
	    }
	}

	
