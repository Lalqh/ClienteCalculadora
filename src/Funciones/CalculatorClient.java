package Funciones;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class CalculatorClient {
    private final Socket socket;
    private final DataInputStream input;
    private final DataOutputStream output;

    public CalculatorClient(String serverName, int port) throws IOException {
        socket = new Socket(serverName, port);
        input = new DataInputStream(socket.getInputStream());
        output = new DataOutputStream(socket.getOutputStream());
    }

    public void sendNumberAndOperation(double number, String operation) throws IOException {
        output.writeDouble(number);
        output.writeUTF(operation);
        output.flush();
    }

    public double receiveResult() throws IOException {
        return input.readDouble();
    }

    public void close() throws IOException {
        socket.close();
    }
}