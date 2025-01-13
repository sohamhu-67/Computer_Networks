import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 12345;

        try {
            Socket socket = new Socket(host, port);
            System.out.println("Connected to server at " + host + ":" + port);

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter serverOutput = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String message;

            while (true) {
                System.out.print("Enter message to send to server (or 'exit' to quit): ");
                message = userInput.readLine();

                if (message.equalsIgnoreCase("exit")) {
                    System.out.println("Closing connection.");
                    break;
                }

                serverOutput.println(message);

                String serverResponse = serverInput.readLine();
                System.out.println("Server replied: " + serverResponse);
            }

            userInput.close();
            serverOutput.close();
            serverInput.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
