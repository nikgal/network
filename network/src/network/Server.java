package network;

import java.awt.HeadlessException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

    static private Socket connection;
    static private ObjectOutputStream output;
    static private ObjectInputStream input;
    static private ServerSocket server;
    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            server = new ServerSocket(6666,10);
            while(true){
                connection = server.accept();
                output = new ObjectOutputStream(connection.getOutputStream());
                input = new ObjectInputStream(connection.getInputStream());
                output.writeObject("You send: " + (String)input.readObject());
            }
        } catch (IOException | HeadlessException | ClassNotFoundException e) {
           
            e.printStackTrace();
        }
    }

}
