package Interfaces;


import Model.User;

import java.util.ArrayList;


/**
 * @author  Shynkarenko Eduard
 *
 */
public interface AbstractDataBaseUser {

    void addUser(String name, String password, String mail);
    User getUser(int id);
    void updateUser(int id, String name, String password, String mail);
    void deleteUser(int id);
    ArrayList<User> getAllUsers();
    User getUserByMail(String mail);
    ArrayList<User> getMySendersList(int id);
    ArrayList<Integer> getIdSender(int id);
    boolean containsIdSender(int id, int idSenser);
    void addIdSender(int id, int idSender);
    void deleteIdSender(int id, int idSender);
    void connect();
    void disconnect();
}

