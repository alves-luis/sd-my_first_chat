import java.net.*;
import java.io.*;

public class ClientReceiver implements Runnable {
  private Socket s;
  private BufferedReader in;

  public ClientReceiver(Socket s) {
    try {
      this.s = s;
      this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void run() {
    try {
      String input;
      while((input = in.readLine()) != null) {
        System.out.println(input);
      }
      s.shutdownInput();
      s.close();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
