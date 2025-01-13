import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) {
        int port = 12345;
        String host = "127.0.0.1";  

        try {
           
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server started at " + host + ":" + port + "...");
            
            Socket clientSocket = serverSocket.accept();
            System.out.println("Connected to client: " + clientSocket.getInetAddress());

            BufferedReader clientInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter clientOutput = new PrintWriter(clientSocket.getOutputStream(), true);

            String clientMessage;
            
            while (true) {
                clientMessage = clientInput.readLine();
                
                if (clientMessage == null || clientMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Client disconnected.");
                    break;
                }

                System.out.println("Received from client: " + clientMessage);

                clientOutput.println("Message received: " + clientMessage);
            }

            clientInput.close();
            clientOutput.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
