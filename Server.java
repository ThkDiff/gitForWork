package CallBack;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        ServerSocket server;
        Socket socket;
        DataOutput out;
        Scanner scanner = new Scanner(System.in);
        try {
            server = new ServerSocket(9090);
            System.out.println("Server is started");

            socket = server.accept();
            System.out.println("Client is connected");
            out = new DataOutputStream(socket.getOutputStream());

            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                        try (DataInputStream in = new DataInputStream(socket.getInputStream())){
                            while(true) {
                                String s = in.readUTF();
                                if (s.equals("/end")){
                                    out.writeUTF("/end");
                                    break;
                                }
                                System.out.println(s);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }finally {
                            try {
                                ((DataOutputStream) out).close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                }
            });
            t1.start();

            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try (DataOutputStream out = new DataOutputStream(socket.getOutputStream())){
                        while (true) {
                            System.out.println("Enter your message");
                            String s = scanner.nextLine();
                            System.out.println("Your message is sent");
                            out.writeUTF("Server: " + s);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            t2.setDaemon(true);
            t2.start();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

