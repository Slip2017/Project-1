package Model;


/**
 * @author  Shynkarenko Eduard
 *
 */
public class Note {


    private int id;
    private String title;
    private String body;
    private String date;
    private String time;
    private int user_Id;
    private int IdSender;

    public Note() {
    }

    public Note(String title, String date, String time, String body, int user_Id, int IdSender) {
        this.title = title;
        this.body = body;
        this.date = date;
        this.time = time;
        this.user_Id = user_Id;
        this.IdSender = IdSender;
    }


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getUserId() {
        return user_Id;
    }

    public int getIdSender() {
        return IdSender;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setUserId(int userId) {
        this.user_Id = userId;
    }

    public void setIdSender(int authorId) {
        this.IdSender = authorId;
    }


}
