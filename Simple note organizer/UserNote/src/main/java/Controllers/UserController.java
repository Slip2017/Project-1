package Controllers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import Model.User;
import Services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author  Shynkarenko Eduard
 *
 */

@RestController()
public class UserController {
    private static User activeUser;
    private UserService servUser;


    public UserController(){
        this.servUser = new UserService();
    }

    public User getActiveUser() {
        return activeUser;
    }

    //to registrate User
    @RequestMapping(method = RequestMethod.POST, value = "/reg")
    public String reg(@RequestParam(value = "name" , defaultValue="") String name,
                    @RequestParam(value = "password" , defaultValue="") String password,
                    @RequestParam(value = "mail" , defaultValue="") String mail){
        String res = null;
        if ((!name.isEmpty()) & (!mail.isEmpty()) & (!password.isEmpty())){
            String hashPass = getHash(password);
            if (servUser.doRegister(name, hashPass, mail)){
                activeUser = servUser.getCurrentUser();
                 res = "Вы успешно зарегистрированы";
            }else{
                res = "Неверно введены данные";
            }
        }else{
            res = "Пустое поле";
        }

        return res;
    }

    //to authorize User
    @RequestMapping(method = RequestMethod.POST, value = "/auth")
    public String auth(@RequestParam(value = "mail" , defaultValue="") String mail,
                     @RequestParam(value = "password" , defaultValue="") String password){
        String res = null;
        if(activeUser == null) {
            if ((!mail.isEmpty()) & (!password.isEmpty())) {
                String hashPass = getHash(password);
                if (servUser.doAuthorization(mail, hashPass)) {
                    activeUser = servUser.getCurrentUser();

                    res = "Авторизация прошла успешно. Ваш id - "+activeUser.getId();
                } else {
                    res = "Пользователя с такими данными не существует";
                }
            } else{
                res = "Пустое поле";
            }
        }else{
            res = "Вы уже в системе. Ваш id - "+activeUser.getId();
        }

        return res;
    }

    //turn password in md5hash
    private String getHash(String password){
        //?
        MessageDigest md5;
        StringBuffer hexString = new StringBuffer();
        try {
            md5 = MessageDigest.getInstance("md5");
            md5.reset();
            md5.update(password.getBytes());
            byte messageDigest[] = md5.digest();

            for (int i = 0; i < messageDigest.length; i++) {
                hexString.append(Integer.toString((messageDigest[i] & 0xff) + 0x100, 16).substring(1));
            }
        }
        catch (NoSuchAlgorithmException e) {
            return e.toString();
        }
        return hexString.toString();
    }

    @RequestMapping(method= RequestMethod.GET, value="/alluserlist")
    public ArrayList<String> showAllUserList() {
        ArrayList<String> list = servUser.getUserList();
        return list;
    }

    @RequestMapping(method= RequestMethod.GET, value="/myuserlist")
    public ArrayList<String> showMyUserList() {
        ArrayList<String> list = new ArrayList<>();
        if (getActiveUser() != null){
            int id = getActiveUser().getId();
         list = servUser.getMyUserList(id);
        if (list.size() == 0) {
            System.out.println("Your List is empty");
        }
    }
        return list;
    }

    @RequestMapping(method= RequestMethod.POST, value="/addusertolist")
    public void addUserToList(@RequestParam(value = "mail" , defaultValue="") String mail){
        if (getActiveUser() != null) {
            int id = getActiveUser().getId();
            servUser.addToMyUserList(id, mail);
            //showMyUserList();
        }
    }

    @RequestMapping(method= RequestMethod.DELETE, value="/removeuserfromlist")
    public void removeUserFromList(@RequestParam(value = "mail" , defaultValue="") String mail){
        if (getActiveUser() != null) {
            int id = getActiveUser().getId();
            servUser.removeFromMyUserList(id, mail);
            //showMyUserList();
        }
    }

    @RequestMapping(method= RequestMethod.PUT, value="/changeuserinfo")
    public void changeUserInfo(@RequestParam(value = "name" , defaultValue="") String name,
                               @RequestParam(value = "password" , defaultValue="") String password,
                               @RequestParam(value = "mail" , defaultValue="") String mail){
        if (getActiveUser() != null) {
            int id = getActiveUser().getId();
            String hashPass = getHash(password);
            servUser.updateUser(id, name, hashPass, mail);
        }
    }

    @RequestMapping(method= RequestMethod.GET, value="/showmyinfo")
    public User showMyInfo(){
        User user = null;
        if (getActiveUser() != null) {
            int id = getActiveUser().getId();
            user = servUser.getUser(id);
        }
        return user;
    }

    @RequestMapping(method= RequestMethod.DELETE, value="/deleteaccount")
    public void deleteAccount(){
        if (getActiveUser() != null) {
            int id = getActiveUser().getId();
            servUser.deleteUser(id);
        }
    }

    @RequestMapping( value="/exit")
    public String exit(){
        activeUser = null;
        return "Вы вышли из системы";
    }

}



