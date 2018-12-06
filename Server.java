import java.net.*;
import java.io.*;

public class Server {
  public static void main(String[] args) throws IOException {
    ServerSocket ss = new ServerSocket(9999);
    Chat c = new Chat();

    while (true) {
      Socket s = ss.accept();
      new Thread(new ServerThread(s,c)).start();
      new Thread(new ServerUpdateThread(s, c)).start();
      System.out.println("User has connected with address " + s.getRemoteSocketAddress());
    }
  }
}
