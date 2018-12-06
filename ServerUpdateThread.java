import java.net.*;
import java.io.*;
import java.util.concurrent.locks.*;

public class ServerUpdateThread implements Runnable {

  private Socket s;
  private Chat c;
  private PrintWriter out;
  private int sentMessages;

  public ServerUpdateThread(Socket s, Chat c) {
    try {
      this.s = s;
      this.c = c;
      this.out = new PrintWriter(s.getOutputStream(),true);
      this.sentMessages = c.getSize();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void run() {
    Condition cond = c.getCondition();
    Lock l = c.getLock();
    while(true) {
      try {
        l.lock();
        while(c.getSize() == sentMessages)
          try {
            cond.await();
          }
          catch (Exception e) {
            e.printStackTrace();
          }
          for(; sentMessages < c.getSize(); sentMessages++)
            out.println(c.getMessage(sentMessages));
      }
      catch (Exception e) {
        e.printStackTrace();
      }
      finally {
        l.unlock();
      }
    }
  }
}
