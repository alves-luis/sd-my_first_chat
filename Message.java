public class Message {
  private String msg;
  private String user;

  public Message(String m, String u) {
    msg = m;
    user = u;
  }

  public String getContent() {
    return msg;
  }

  public String getSender() {
    return user;
  }
}
