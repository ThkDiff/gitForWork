package CallBack;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client{
    public static void main(String[] args) {
        Socket socket;
        DataOutputStream out;
        Scanner scanner = new Scanner(System.in);
        MyMsg msg = new MyMsg();
        ForCallBack fcb = new ForCallBack(msg);

        try {
            socket = new Socket("localhost",9090);
            out = new DataOutputStream(socket.getOutputStream());

            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try(DataInputStream in = new DataInputStream(socket.getInputStream())) {
                        while (true) {
                            String s = in.readUTF();
                            if (s.equals("/end")){
                                out.writeUTF(s);
                                break;
                            }
                            System.out.println(s);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    finally {
                        try {
                            out.close();
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
                    try {
                        while (true) {
                            System.out.println("Enter your message");
                            String s = scanner.nextLine();
                            System.out.println("Your message is sent");
                            out.writeUTF("Client: " + s);
                            fcb.sendMsg(s);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    finally {
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
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

class ForCallBack{
    interface CallBack{
        void callingBack(String string);
    }

    CallBack callBack;

    public ForCallBack(CallBack callBack){
        this.callBack = callBack;
    }

    public void sendMsg(String s){
        this.callBack.callingBack(s);
    }
}

class MyMsg implements ForCallBack.CallBack{

    @Override
    public void callingBack(String string) {
        System.out.println("CallBack Message: " + string);
    }
}

