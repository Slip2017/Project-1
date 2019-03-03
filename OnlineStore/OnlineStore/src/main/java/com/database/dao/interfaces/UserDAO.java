package com.database.dao.interfaces;

import com.database.domain.User;
import java.util.List;

/**
 * @author  Shynkarenko Eduard
 *
 */

public interface UserDAO {
    public Long addUser(String name, String email, String password, String phone);
    public User getUser(Long userId);
    public boolean containsUserByMail(String email);
    public User getUserByMail(String email);
    public boolean containsUserByPhone(String phone);
    public boolean containsUserByMailAndPass(String email, String password);
    public User getUserWithId(Long userId);
    public List<User> getAllUsers();
    public void updateUserName(Long userId, String name);
    public void updateUserMail(Long userId, String email);
    public void updateUserPass(Long userId, String password);
    public void updateUserPhone(Long userId, String phone);
    public void deleteUser(Long userId);
    public void deleteAllUsers();
}
