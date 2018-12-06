import java.util.concurrent.locks.*;
import java.util.*;

public class Chat {
  private List<Message> messages;
  private int size;
  private Condition chatUpdated;
  private Lock msgLock;

  public Chat() {
    messages = new ArrayList<>();
    size = messages.size();
    msgLock = new ReentrantLock();
    chatUpdated = msgLock.newCondition();
  }

  public void sendMessage(String nMsg, String sender) {
    Message m = new Message(nMsg,sender);
    msgLock.lock();
    try {
      messages.add(m);
      size++;
      chatUpdated.signalAll();
    }
    catch (IllegalMonitorStateException e) {
      e.printStackTrace();
    }
    finally {
      msgLock.unlock();
    }
    System.out.println("Sent message <" + nMsg + "> by (" + sender + ")");
  }

  public Condition getCondition() {
    return this.chatUpdated;
  }

  public Lock getLock() {
    return this.msgLock;
  }

  public synchronized int getSize() {
    return this.size;
  }

  public String getMessage(int i) {
    Message m = this.messages.get(i);
    return m.getSender() + " > " + m.getContent();
  }


}
