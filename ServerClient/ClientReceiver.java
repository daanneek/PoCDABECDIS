package ServerClient;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientReceiver {
    
    int port;
    String fileName;
    
    public ClientReceiver(int port, String fileName){
        this.port = port;
        this.fileName = fileName;
    }

    void receive(){
        try{
        ServerSocket serverSock = new ServerSocket(port);
        Socket sock = serverSock.accept();
        System.out.println("Socket has been bound succesfullyl!");

        InputStream is = sock.getInputStream();
        FileOutputStream fos = new FileOutputStream(this.fileName);
        BufferedOutputStream bos = new BufferedOutputStream(fos);

        byte[] fileByteArray = new byte[2048];
        
        int bytesRead = is.read(fileByteArray, 0, fileByteArray.length);
        int current = bytesRead;

        do{
            bytesRead =
            is.read(fileByteArray, current, (fileByteArray.length-current));
         if(bytesRead >= 0) current += bytesRead;
      } while(bytesRead > -1);

        bos.write(fileByteArray, 0 , current);
        bos.flush();
        System.out.println("File " + this.fileName
          + " downloaded (" + current + " bytes read)");

        if (fos != null) fos.close();
        if (bos != null) bos.close();
        if (sock != null) sock.close();
        serverSock.close();
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            System.exit(1);
        }
    }   

    public static void main(String[] args) {
        ClientReceiver cr1 = new ClientReceiver(8089, "test2.txt");
        cr1.receive();
    }
}