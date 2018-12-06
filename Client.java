import java.net.*;
import java.io.*;

public class Client {

  public static void main(String[] args) throws IOException {
    Socket s = new Socket("localhost",9999);

    PrintWriter out = new PrintWriter(s.getOutputStream(),true);
    BufferedReader sys = new BufferedReader(new InputStreamReader(System.in));

    new Thread(new ClientReceiver(s)).start();
    String input;
    while((input = sys.readLine()) != null) {
      out.println(input);
    }
    s.shutdownOutput();
  }
}
