package Model;

import java.util.ArrayList;


/**
 * @author  Shynkarenko Eduard
 *
 */

public class User {

    private int id;
    private String name;
    private String password;
    private ArrayList<Integer> IdSenders;
    private String mail;

    public User() {}

    public User(String name, String password, ArrayList<Integer> IdSender, String mail) {
        this.name = name;
        this.password = password;
        this.IdSenders = IdSender;
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Integer> getidSenders() {
        return IdSenders;
    }

    public String getMail() {
        return mail;
    }

    public int getId() {return id;}

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIdSenders (ArrayList<Integer> idSenders ) {
        IdSenders = idSenders;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setId(int id) {this.id = id;}
}
