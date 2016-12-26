package network;

import java.awt.HeadlessException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.Socket;
import java.util.Scanner;

public class App  implements Runnable{

    static private Socket connection;
    static private ObjectOutputStream output;
    static private ObjectInputStream input;
    
    
      
       
    
    public static void main(String[] args) {
        new Thread(new App()).start();
        new Thread(new Server()).start();
    }

    @Override
    public void run() {
        
        try {
            
            
            connection = new Socket(Inet4Address.getByName("127.0.0.1"),6666);
            output = new ObjectOutputStream(connection.getOutputStream());
            input = new ObjectInputStream(connection.getInputStream());
            
            while(true){
                
                System.out.println("Enter message: ");
                Scanner sc = new Scanner(System.in);
                String s = sc.nextLine();
                sendData(s);
                
                System.out.println(input.readObject());
            }
        } catch (IOException | HeadlessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private static void sendData(Object obj){
        try {
            output.flush();
            output.writeObject(obj);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
