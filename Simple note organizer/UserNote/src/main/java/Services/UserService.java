package Services;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Database.DataBaseNote;
import Database.DataBaseUser;
import Model.User;


/**
 * @author  Shynkarenko Eduard
 *
 */

public class UserService {
    private User currentUser;
    private DataBaseUser dbUser;

    public UserService() {
        this.dbUser = new DataBaseUser("root", "admin");
    }

    public boolean doRegister(String name, String passHash, String mail) {
        if (isValidMail(mail)) {
            dbUser.connect();
            currentUser = dbUser.getUserByMail(mail);
            if (currentUser.getMail() == null) {
                dbUser.addUser(name, passHash, mail);
                currentUser = dbUser.getUserByMail(mail);
                dbUser.addIdSender(currentUser.getId(), currentUser.getId());
                return true;
            } else {
                System.out.println("User with this email address is already exist.");
            }
        } else {
            System.out.println("This email is invalid.");
        }
        dbUser.disconnect();
        return false;
    }

    public boolean doAuthorization(String mail, String password) {
        dbUser.connect();
        currentUser = dbUser.getUserByMail(mail);
        if(currentUser.getMail() != null) {
            if (currentUser.getMail().equals(mail)) {
                if (currentUser.getPassword().equals(password)) {
                    System.out.println("Hello, " + currentUser.getName());
                    return true;
                }
            } else {
                System.out.println("Wrong e-mail or password");
            }
        }
        dbUser.disconnect();
        return false;
    }

    public ArrayList<String> getUserList() {
        dbUser.connect();
        ArrayList<User> list1 = dbUser.getAllUsers();
        ArrayList<String> list2 = new ArrayList<String>();
        for (User user : list1) {
            String name = user.getName();
            String mail = user.getMail();
            list2.add(name + "  " + mail);
        }
        dbUser.disconnect();
        return list2;
    }

    public ArrayList<String> getMyUserList(int id) {
        dbUser.connect();
        ArrayList<User> list1 = dbUser.getMySendersList(id);
        ArrayList<String> list2 = new ArrayList<String>();
        for (User user : list1) {
            String name = user.getName();
            String mail = user.getMail();
            list2.add(name + "  " + mail);
        }
        dbUser.disconnect();
        return list2;
    }

    public void addToMyUserList(int id, String mail) {
        dbUser.connect();
        int idSender = dbUser.getUserByMail(mail).getId();

        if (containsIdSender(id, idSender)) {
            System.out.println("This user is already in your list");
            return;
        } else {
            dbUser.addIdSender(id, idSender);
        }
        dbUser.disconnect();
    }


    public boolean containsIdSender(int id, int idSender){
        dbUser.connect();
        boolean res = dbUser.containsIdSender(id, idSender);
        dbUser.disconnect();
        return res;
    }

    public void removeFromMyUserList(int id, String mail) {
        dbUser.connect();
        int idSender = dbUser.getUserByMail(mail).getId();
        dbUser.deleteIdSender(id, idSender);
        dbUser.disconnect();
    }

    public User getUser(int id) {
        dbUser.connect();
        User user = dbUser.getUser(id);
        dbUser.disconnect();
        return user;
    }

    public void updateUser(int id, String name, String password, String mail) {
        dbUser.connect();
        dbUser.updateUser(id, name, password, mail);
        currentUser = dbUser.getUserByMail(mail);
        dbUser.disconnect();
    }

    //should change and review this method
    private boolean isValidMail(String mail) {
        Pattern p = Pattern.compile("[a-zA-Z0-9\\.]+@[a-zA-Z0-9\\-\\_\\.]+\\.[a-zA-Z0-9]{2,4}");
        Matcher m = p.matcher(mail);
        System.out.println(m.matches());
        return m.matches();

    }

    public void deleteUser(int id) {
        dbUser.connect();
        dbUser.deleteUser(id);
        dbUser.disconnect();
    }

    //method for NoteService
    public int getIdByMail(String mail) {
        dbUser.connect();
        int id = dbUser.getUserByMail(mail).getId();
        dbUser.disconnect();
        return id;
    }

    public String getNameAndMail(int id) {
        dbUser.connect();
        String name = dbUser.getUser(id).getName();
        String mail = dbUser.getUser(id).getMail();
        dbUser.disconnect();
        return name + " " + mail;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}






