package task3;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
public class ChatSever {
	private static Set<ClientHandler>clientHandlers=new HashSet<>();
	public static void main(String[] args) {
		   System.out.println("Server started...");

	        try (ServerSocket serverSocket = new ServerSocket(5000)) {

	            while (true) {
	                Socket socket = serverSocket.accept();
	                System.out.println("New client connected");

	                ClientHandler clientHandler = new ClientHandler(socket);
	                clientHandlers.add(clientHandler);

	                Thread thread = new Thread(clientHandler);
	                thread.start();
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }}
	    

	    static void broadcast(String message, ClientHandler sender) {
	        for (ClientHandler client : clientHandlers) {
	            if (client != sender) {
	                client.sendMessage(message);
	            }
	        }
	    }

	    static void removeClient(ClientHandler clientHandler) {
	        clientHandlers.remove(clientHandler);
	    }
	

	}


