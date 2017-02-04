import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.*;

public class HangmanServer {

    private static ServerSocket server;
    private static HashMap<String, String> database;
    
    public static void main(String[] args) {
        Socket requestSocket = null;
        new Thread(new Monitor()).start();

        database = new HashMap<String, String>();
        
        try {
            server = new ServerSocket(10000);
            System.out.println("Server started:");
            System.out.println("Hit Enter to stop server");
            try {
                while (true) {
                    requestSocket = server.accept();
                    new Thread(
                            new RequestProcessor(requestSocket)).start();
                }
            } finally {
                requestSocket.close();
            }
            } catch (Exception ex) {
                Logger.getLogger(
                        HangmanServer.class.getName()).log(Level.SEVERE,
                        null, ex);
            }
        }

        private static void shutdownServer() {
            try {
                server.close();
            } catch (IOException ex) {
            }
            System.exit(0);
        }

        private static class Monitor implements Runnable {

            public void run() {
                try {
                    while (System.in.read() != '\n') {
                    }
                } catch (IOException ex) {
                }
                shutdownServer();
            }
        }

        private static class RequestProcessor implements Runnable {

            private Socket requestSocket;

            public RequestProcessor(Socket requestSocket) {
                this.requestSocket = requestSocket;
            }

            public void run() {
                try {
                    DataInputStream reader =
                            new DataInputStream(requestSocket.getInputStream());
                    DataOutputStream writer = new DataOutputStream(
                            requestSocket.getOutputStream());
                    int cmd = reader.readInt();
                    String playerName = "";
                    String message;
                    playerName = reader.readUTF();
                    message = playerName + " requested to put word";
                    message = playerName + " requested to get a word";
                    System.out.println(message);
                    uploadFile(reader, playerName);
                    downloadFile(writer, playerName);

            } catch (IOException ex) {
                Logger.getLogger(
                       HangmanServer.class.getName()).log(Level.SEVERE,
                       null, ex);
            }
        }

        private void uploadFile(DataInputStream in, String name) {
            try {
                String word;
                word = in.readUTF();
                database.put(name, word);
                System.out.println(word);
                //in.close();
                System.out.println("End message transmission.");
            } catch (IOException ex) {
                Logger.getLogger(
                       HangmanServer.class.getName()).log(Level.SEVERE,
                       null, ex);
            }
        }

        private void downloadFile(DataOutputStream out, String requestName) {
        	String word = "";
        	String name = "";
        	
        	while(true){
            	Iterator<String> it = database.keySet().iterator();
        		while(it.hasNext()){
        			name = it.next();
        			if(name != requestName){
        				word = database.get(name);
        	        	System.out.println(name + " shared a word with " + requestName + "    " + word);
        				database.remove(name);
        				break;
        			}
        		}
        		if(word.equals("")){
        			try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
        		} else {
        			break;
        		}
        	}
        	
        	try {
        		out.writeUTF(name);
                out.writeUTF(word);
                out.writeUTF("-1");
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(
                       HangmanServer.class.getName()).log(Level.SEVERE,
                       null, ex);
            }
        }
    }
}