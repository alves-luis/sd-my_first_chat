import java.net.*;
import java.io.*;

public class Bot {

  public static void main(String[] args) throws IOException {
    Socket s = new Socket("localhost",9999);

    PrintWriter out = new PrintWriter(s.getOutputStream(),true);

    new Thread(new ClientReceiver(s)).start();
    int botNum = Integer.parseInt(args[0]);
    out.println("Bot " + botNum);
    int min = Integer.parseInt(args[1]);
    for(int i = min; i < 10000; i++) {
      out.println("Já contei até " + i);
      try {
        Thread.sleep(1000);
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }
    s.shutdownOutput();
  }
}
