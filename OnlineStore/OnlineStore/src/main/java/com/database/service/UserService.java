package com.database.service;

import com.database.dao.implementation.UserDAOImpl;
import com.database.domain.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;


/**
 * @author  Shynkarenko Eduard
 *
 */

@ManagedBean(name = "userService")
@SessionScoped
public class UserService implements Serializable {

    private UserDAOImpl user = new UserDAOImpl();

    /**
     *  Adds User object into database table "user"
     * @param name -  "name" field in table
     * @param email -  "email" field in table
     * @param password -  "password" field in table
     * @param phone -  "phone" field in table
     * @return id of added User object
     */
    public Long addUser(String name, String email, String password, String phone){
        return  user.addUser(name, email, password, phone);

    }

    /**
     *  Retrieves User object from database table "user"
     * @param userId  -  "user_id" field in table
     * @return User object with (userId, name, email, password, phone) fields
     */
    public User getUser(Long userId){
        return  user.getUser(userId);
    }

    public boolean containsUserByMail(String email){
        return user.containsUserByMail(email);
    }

    public boolean containsUserByPhone(String phone){ return user.containsUserByPhone(phone);}

    public boolean containsUserByMailAndPass(String email, String password){
        return user.containsUserByMailAndPass(email, password);
    }

    public User getUserByMail(String email) {
        return user.getUserByMail(email);
    }

    /**
     * Retrieves User proxy-object from database table "product"
     * @param userId  -  "user_id" field in table
     * @return User proxy-object with only field "userId"
     */
    public User getUserWithId(Long userId){
        return  user.getUserWithId(userId);
    }

    /**
     * Retrieves all User objects from database table "user"
     * @return  List of User objects
     */
    public List<User> getAllUsers(){
        return  user.getAllUsers();
    }

    /**
     * Updates "name" field in database table "user".
     * @param userId - "user_id" field in table
     * @param name - "name" field in table
     */
    public void updateUserName(Long userId, String name){
        user.updateUserName(userId, name);
    }

    /**
     * Updates "mail" field in database table "user".
     * @param userId - "user_id" field in table
     * @param email - "email" field in table
     */
    public void updateUserMail(Long userId, String email){
        user.updateUserMail(userId, email);
    }

    /**
     *  Updates "password" field in database table "user".
     * @param userId - "user_id" field in table
     * @param password - "password" field in table
     */
    public void updateUserPass(Long userId, String password){
        user.updateUserPass(userId, password);
    }

    /**
     * Updates "phone" field in database table "user".
     * @param userId - "user_id" field in table
     * @param phone - "phone" field in table
     */
    public void updateUserPhone(Long userId, String phone){
        user.updateUserPhone(userId, phone);
    }

    /**
     *  Deletes User object from database table "user"
     * @param userId  - "user_id" field in table
     */
    public void deleteUser(Long userId){
        user.deleteUser(userId);
    }

    /**
     *   Deletes all User objects from database table "user"
     */
    public void deleteAllUsers(){
        user.deleteAllUsers();
    }
}
