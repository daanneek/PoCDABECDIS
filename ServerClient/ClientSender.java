package ServerClient;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.Socket;

class ClientSender{
    
    String fileLoc;
    String address;
    int port;

    public ClientSender(String fileLoc, String address, int port){
        this.fileLoc = fileLoc;
        this.address = address;
        this.port = port;
    }

    void sendFile() {
        try{
            Socket sock = new Socket(this.address, this.port);
            File file = new File(this.fileLoc);
            byte[] fileByteArray  = new byte [(int)file.length()];
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);

            bis.read(fileByteArray, 0, fileByteArray.length);
            OutputStream os = sock.getOutputStream();
            System.out.println("Sending " + file + "(" + fileByteArray.length + " bytes)");
            os.write(fileByteArray, 0, fileByteArray.length);
            os.flush();
            System.out.println("Done.");

            if (bis != null) bis.close();
            if (os != null) os.close();
            if (sock!=null) sock.close();
        }
        catch(Exception e){
            e.printStackTrace();
            System.exit(0);
        }
        finally{
            System.exit(1);   
        }
    }


    public static void main(String[] args) {
        ClientSender s1 = new ClientSender("1R7AM252.000", "83.87.30.119", 8089); //Caglar 178.84.178.16  
        s1.sendFile();
    }
    
}