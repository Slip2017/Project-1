package Database;

import Interfaces.AbstractDataBaseUser;
import Model.User;

import java.sql.*;
import java.util.ArrayList;


/**
 * @author  Shynkarenko Eduard
 *
 */

public class DataBaseUser implements AbstractDataBaseUser {
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private String url = "jdbc:mysql://localhost:3306/usernote?autoReconnect=true&useSSL=false";
    private String user;
    private String password;

    public DataBaseUser() {
    }

    public DataBaseUser(String user, String password) {
        this.user = user;
        this.password = password;
    }

    /**
     * adds User object to Database with fields (name, password, mail);
     */
    @Override
    public void addUser(String name, String password, String mail) {

        try {
            statement.executeUpdate("INSERT INTO user(name, password, mail) VALUES('" + name + "', '" + password + "', '" + mail + "')");

        } catch (SQLException e) {
            System.out.println(" Adding User failed");
            e.printStackTrace();
        }
    }

    /**
     * returns User object from Database with fields (id, name, password, idUsers, mail) using his id;
     */
    @Override
    public User getUser(int id) {
        User obj = new User();
        try {

            resultSet = statement.executeQuery("SELECT id, name, password, mail FROM user WHERE id = " + id);
            resultSet.next();
            obj.setId(resultSet.getInt("id"));
            obj.setIdSenders(getIdSender(id));
            obj.setName(resultSet.getString("name"));
            obj.setPassword(resultSet.getString("password"));
            obj.setMail(resultSet.getString("mail"));

        } catch (SQLException e) {
            System.out.println(" Getting User failed");
            e.printStackTrace();
        }

        return obj;
    }

    /**
     * updates User object in Database with fields (id, name, password, mail) using his id;
     */
    @Override
    public void updateUser(int id, String name, String password, String mail) {

        try {

            statement.executeUpdate("UPDATE user SET name = '" + name + "', password = '" + password + "', " +
                    " mail= '" + mail + "' WHERE id = " + id);

        } catch (SQLException e) {
            System.out.println(" Updating User failed");
            e.printStackTrace();
        }
    }

    /**
     * deletes User object in Database with fields (id, name, password, idSenders, mail) using his id;
     */
    @Override
    public void deleteUser(int id) {
        try {

            statement.executeUpdate("DELETE FROM  user WHERE id = " + id);

        } catch (SQLException e) {
            System.out.println("Deleting User failed");
            e.printStackTrace();
        }
    }

    /**
     * gets all User objects from Database with fields (id, name, password, IdSenders, mail);
     */
    @Override
    public ArrayList<User> getAllUsers() {

        ArrayList<User> list1 = new ArrayList<>();
        try {

            resultSet = statement.executeQuery("SELECT * FROM user");
            while (resultSet.next()) {
                User obj = new User();
                obj.setId(resultSet.getInt("id"));
                obj.setName(resultSet.getString("name"));
                obj.setMail(resultSet.getString("mail"));
                list1.add(obj);
            }
        } catch (SQLException e) {
            System.out.println("Getting All Users failed");
            e.printStackTrace();
        }

        return list1;
    }

    /**
     * gets User object from Database with fields (id, name, password, idUsers, mail) using his mail;
     */
    @Override
    public User getUserByMail(String mail) {
        User obj = new User();
        try {

            resultSet = statement.executeQuery("SELECT id, name, password, mail FROM user WHERE mail = '" + mail + "'");
            while (resultSet.next()) {
                obj.setId(resultSet.getInt("id"));
                obj.setIdSenders(getIdSender(resultSet.getInt("id")));
                obj.setName(resultSet.getString("name"));
                obj.setPassword(resultSet.getString("password"));
                obj.setMail(resultSet.getString("mail"));
            }

        } catch (SQLException e) {
            System.out.println(" Getting User By Mail failed");
            e.printStackTrace();
        }

        return obj;
    }

    /**
     * gets  User objects list (list of users that can send me notes) from Database with fields (id, name, password, mail) using my id;
     */
    @Override
    public ArrayList<User> getMySendersList(int id) {
        ArrayList<User> list = new ArrayList<>();
        try {

            resultSet = statement.executeQuery("SELECT id, name,  mail FROM user WHERE id IN(SELECT idSender FROM senders WHERE user_id=" + id + ")");
            while (resultSet.next()) {
                User obj = new User();
                obj.setId(resultSet.getInt("id"));
                obj.setName(resultSet.getString("name"));
                obj.setMail(resultSet.getString("mail"));
                list.add(obj);
            }

        } catch (SQLException e) {
            System.out.println(" Getting My User List failed");
            e.printStackTrace();
        }

        return list;

    }

    /**
     * gets User's Id list (list of users that can send me notes) from Database using my id;
     */
    @Override
    public ArrayList<Integer> getIdSender(int id) {
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<Integer> list = new ArrayList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT idSender FROM senders WHERE user_id =" + id);
            while (resultSet.next()) {
                list.add(resultSet.getInt("idSender"));
            }

        } catch (SQLException e) {
            System.out.println(" Getting Id Senders failed");
            e.printStackTrace();
        }
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }

        } catch (SQLException e) {
            System.out.println("Disconnection Failed");
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean containsIdSender(int id, int idSender) {
        boolean res = false;
        ArrayList<Integer> list = new ArrayList<>();
        try {
            resultSet = statement.executeQuery("SELECT idSender FROM senders WHERE user_id =" + id + " AND idSender =" + idSender);
            System.out.println(resultSet.getFetchSize());
            if (resultSet.absolute(1)) {
                res =  true;
            }else{
                res = false;
            }

        } catch (SQLException e) {
            System.out.println(" Getting Id Senders failed");
            e.printStackTrace();
        }
        return res;
    }


    /**
     * adds User's Id (user that can send me notes) to Database using my id and user's id (idSender);
     */
    @Override
    public void addIdSender(int id, int idSender) {
        try {

            statement.executeUpdate("INSERT INTO senders (user_id, idSender) VALUES(" + id + ", " + idSender + ")");

        } catch (SQLException e) {
            System.out.println("Adding Id Users failed");
            e.printStackTrace();
        }
    }

    /**
     * deletes User's Id (user that can send me notes) from Database using my id and user's id (idSender);
     */
    @Override
    public void deleteIdSender(int id, int idSender) {
        try {

            statement.executeUpdate("DELETE FROM  senders WHERE user_id = " + id + " AND idSender = " + idSender);

        } catch (SQLException e) {
            System.out.println("Deleting Id Users failed");
            e.printStackTrace();
        }
    }

    /**
     * creates connection with Database. This method should always be invoked before every invocation of any query method;
     */
    @Override
    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Some problem with JDBC Driver");
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }


    }

    /**
     * closes connection with Database. This method should always be invoked after finish working with Database;
     */
    @Override
    public void disconnect() {

        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }

        } catch (SQLException e) {
            System.out.println("Disconnection Failed");
            e.printStackTrace();
        }

    }

}
