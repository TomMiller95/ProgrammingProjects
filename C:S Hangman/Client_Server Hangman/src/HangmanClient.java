import java.io.*;
import java.util.*;
import java.net.*;

public class HangmanClient {

    public static void main(String[] args) {
        
    }
    
    public String processServer(String playerName, String word){
    	Socket requestSocket = null;
        int cmd = 0;
        String playWord = ""; 
        
        try {
            try {
                requestSocket = new Socket();
                requestSocket.connect(
                    new InetSocketAddress("172.16.10.149", 10000));
                DataOutputStream writer = new DataOutputStream(
                        requestSocket.getOutputStream());
                writer.writeInt(cmd);
                writer.writeUTF(playerName);
                writer.writeUTF(word);
                writer.writeUTF("-1");
                //get
                DataInputStream reader = new DataInputStream(
                            (requestSocket.getInputStream()));
                //playWord = reader.readUTF();
                playWord = reader.readUTF();
                reader.close();
            } finally {
                requestSocket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return playWord;
    }
}