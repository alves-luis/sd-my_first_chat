import java.net.*;
import java.io.*;

public class ServerThread implements Runnable {

  private Socket s;
  private Chat c;
  private BufferedReader in;
  private PrintWriter out;
  private String user;

  public ServerThread(Socket s, Chat c){
    try {
      this.s = s;
      this.c = c;
      this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
      this.out = new PrintWriter(s.getOutputStream(),true);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void run() {
    try {
      out.println("Insere o teu username: ");
      user = in.readLine();
      out.println("Bem-Vindo " + user + "!");
      String input;
      while((input = in.readLine()) != null) {
        c.sendMessage(input,user);
      }
      out.println("Adeus " + user + "!");
      out.close();
      System.out.println("User " + user + " left us!");
    }
    catch (Exception e) {
      e.printStackTrace();
    }

  }
}
