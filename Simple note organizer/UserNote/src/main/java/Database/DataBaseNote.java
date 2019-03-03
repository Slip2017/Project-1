package Database;

import Interfaces.AbstractDataBaseNote;
import Model.Note;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static java.util.Calendar.*;


/**
 * @author  Shynkarenko Eduard
 *
 */

public class DataBaseNote implements AbstractDataBaseNote {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private String url = "jdbc:mysql://localhost:3306/usernote?autoReconnect=true&useSSL=false";
    private String user;
    private String password;

    public DataBaseNote(String user, String password) {
        this.user = user;
        this.password = password;
    }

    /**
     * adds Note object(note) to Database with fields (title (note topic), body(note text), date(notice), time(notice), user_id(recipient), IdSender(sender's id));
     */
    @Override
    public void addNote(String title, String body, String date, String time, int user_Id, int IdSender) {

        try {

            statement.executeUpdate("INSERT INTO note (title, body, date, time, user_Id, IdSender) VALUES('" + title + "', '" + body + "', '" + date + "', '" + time + "', " +
                    user_Id + ", " + IdSender + ")");

        } catch (SQLException e) {
            System.out.println(" Adding Note failed "+date);
            e.printStackTrace();
        }
    }

    /**
     * adds Note objects(same notes) to Database with fields (title (note topic), body(note text), date(notice), time(notice), list(list of recipients), IdSender(sender's id)) from single sender;
     */
    @Override
    public void addSameNotes(String title, String body, String date, String time, ArrayList<Integer> list, int IdSender) {

        try {
            for (int i = 0; i < list.size(); i++) {
                statement.executeUpdate("INSERT INTO note(title, body, date, time, user_Id, IdSender) VALUES('" + title + "', '" + body + "', '" + date + "', '" + time + "', " +
                        list.get(i) + ", " + IdSender + ")");
            }
        } catch (SQLException e) {
            System.out.println(" Adding Same Notes failed");
            e.printStackTrace();
        }
    }

    /**
     * returns Note object from Database with fields(title (note topic), body(note text), date(notice), time(notice), user_id(recipient), IdSender(sender's id));
     */
    @Override
    public Note getNote(int id) {
        Note obj = new Note();
        try {

            resultSet = statement.executeQuery("SELECT id, title, body, date, time, user_Id, IdSender FROM note WHERE id = " + id);
            while (resultSet.next()) {
                obj.setId(resultSet.getInt("id"));
                obj.setTitle(resultSet.getString("title"));
                obj.setBody(resultSet.getString("body"));
                obj.setDate(resultSet.getString("date"));
                obj.setTime(resultSet.getString("time"));
                obj.setUserId(resultSet.getInt("user_Id"));
                obj.setIdSender(resultSet.getInt("IdSender"));
            }


        } catch (SQLException e) {
            System.out.println(" Getting Note failed");
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * returns Note objects from Database with fields(title (note topic), body(note text), date(notice), time(notice), user_id(recipient), IdSender(sender's id)) using sender's id;
     */
    @Override
    public ArrayList<Note> getMyNotes(int IdSender) {

        ArrayList<Note> list = new ArrayList<>();

        try {
            resultSet = statement.executeQuery("SELECT id, title, body, date, time, user_Id, IdSender FROM note WHERE IdSender = " + IdSender);
            while (resultSet.next()) {
                Note obj = new Note();
                obj.setId(resultSet.getInt("id"));
                obj.setTitle(resultSet.getString("title"));
                obj.setBody(resultSet.getString("body"));
                obj.setDate(resultSet.getString("date"));
                obj.setTime(resultSet.getString("time"));
                obj.setUserId(resultSet.getInt("user_Id"));
                obj.setIdSender(resultSet.getInt("IdSender"));
                list.add(obj);
            }

        } catch (SQLException e) {
            System.out.println(" Getting My Notes failed");
            e.printStackTrace();
        }
        return list;
    }

    /**
     * returns Note objects from Database with fields(title(note topic), body(note text), date(notice), time(notice), user_id(recipient), IdSender(sender's id)) using user's id(recipient);
     */
    @Override
    public ArrayList<Note> getNotesForMe(int user_Id) {

        ArrayList<Note> list = new ArrayList<>();
        try {

            resultSet = statement.executeQuery("SELECT  id, title, body, date, time, user_Id, IdSender FROM note WHERE user_Id = " + user_Id);
            while (resultSet.next()) {
                Note obj = new Note();
                obj.setId(resultSet.getInt("id"));
                obj.setTitle(resultSet.getString("title"));
                obj.setBody(resultSet.getString("body"));
                obj.setDate(resultSet.getString("date"));
                obj.setTime(resultSet.getString("time"));
                obj.setUserId(resultSet.getInt("user_Id"));
                obj.setIdSender(resultSet.getInt("IdSender"));
                list.add(obj);
            }

        } catch (SQLException e) {
            System.out.println(" Getting  Notes for Me failed");
            e.printStackTrace();
        }
        return list;
    }

    /**
     * returns all Note objects from Database with fields(title(note (topic), body(note text), date(notice), time(notice), user_id(recipient), IdSender(sender's id));
     */
    @Override
    public ArrayList<Note> getAllNotes() {
        ArrayList<Note> list = new ArrayList<>();
        try {

            resultSet = statement.executeQuery("SELECT * FROM note");
            while (resultSet.next()) {
                Note obj = new Note();
                obj.setId(resultSet.getInt("id"));
                obj.setTitle(resultSet.getString("title"));
                obj.setBody(resultSet.getString("body"));
                obj.setDate(resultSet.getString("date"));
                obj.setTime(resultSet.getString("time"));
                obj.setUserId(resultSet.getInt("user_Id"));
                obj.setIdSender(resultSet.getInt("IdSender"));
                list.add(obj);
            }

        } catch (SQLException e) {
            System.out.println(" Getting All Notes failed");
            e.printStackTrace();
        }

        return list;
    }

    /**
     * returns  list of Note objects from Database with fields(title(note topic), body(note text), IdSender(sender's id)) for current Time and Date;
     */
    @Override
    public ArrayList<Note> getCurrTimeField() {
        ArrayList<Note> list = new ArrayList<>();

        try {

            resultSet = statement.executeQuery("SELECT title, body, user_id, IdSender FROM note WHERE date = CURDATE() AND time = CONCAT(HOUR(CURTIME()), ':', MINUTE(CURTIME()), ':', '00' )");
            while (resultSet.next()) {
                Note obj = new Note();
                obj.setTitle(resultSet.getString("title"));
                obj.setBody(resultSet.getString("body"));
                obj.setUserId(resultSet.getInt("user_Id"));
                obj.setIdSender(resultSet.getInt("IdSender"));
                list.add(obj);
            }

        } catch (SQLException e) {
            System.out.println(" Getting AllTimeField failed");
            e.printStackTrace();
        }
        return list;
    }

    /**
     * deletes Note object from Database using id(note's id) ;
     */
    @Override
    public void deleteNote(int id) {

        try {

            statement.executeUpdate("DELETE FROM  note WHERE id = " + id);

        } catch (SQLException e) {
            System.out.println("Deleting Note failed");
            e.printStackTrace();
        }

    }

    /**
     * updates Note object in Database with fields (body(note text), time(notice), user_id(recipient), id(note's id));
     */
    @Override
    public void updateNote(int id, String title, String body, String date, String time, int user_Id, int IdSender) {
        try {

            statement.executeUpdate("UPDATE note SET  title = '" + title + "', body = '" + body + "', date = '" + date + "', time = '" + time + "', user_Id = " + user_Id + "," +
                    " IdSender = " + IdSender + " WHERE id = " + id);

        } catch (SQLException e) {
            System.out.println(" Updating Note failed");
            e.printStackTrace();
        }
    }

    /**
     * updates Note object in Database with field (title(note topic)) using note's id;
     */
    @Override
    public void updateTitleField(int id, String title) {
        try {

            statement.executeUpdate("UPDATE note SET title = '" + title + "' WHERE id = " + id);

        } catch (SQLException e) {
            System.out.println("Updating Note Title Field failed");
            e.printStackTrace();
        }
    }

    /**
     * updates Note object in Database with field (body(note text)) using note's id;
     */
    @Override
    public void updateBodyField(int id, String body) {
        try {

            statement.executeUpdate("UPDATE note SET body = '" + body + "' WHERE id = " + id);

        } catch (SQLException e) {
            System.out.println("Updating Note Body Field failed");
            e.printStackTrace();
        }

    }

    /**
     * updates Note object in Database with field (date(notice)) using note's id;
     */
    @Override
    public void updateDateField(int id, String date) {
        try {

            statement.executeUpdate("UPDATE note SET date = '" + date + "' WHERE id = " + id);

        } catch (SQLException e) {
            System.out.println("Updating Note Date Field failed");
            e.printStackTrace();
        }

    }

    /**
     * updates Note object in Database with field (time(notice)) using note's id;
     */
    @Override
    public void updateTimeField(int id, String time) {
        try {

            statement.executeUpdate("UPDATE note SET time = '" + time + "' WHERE id = " + id);

        } catch (SQLException e) {
            System.out.println("Updating Note Time Field failed");
            e.printStackTrace();
        }
    }


    /**
     * updates Note object in Database with field (user_id(recipient)) using sender's id;
     */
    @Override
    public void updateUserIdField(int id, int user_Id) {
        try {

            statement.executeUpdate("UPDATE note SET user_Id = " + user_Id + " WHERE id = " + id);

        } catch (SQLException e) {
            System.out.println("Updating Note UserIdField failed");
            e.printStackTrace();
        }

    }

    /**
     * creates connection with Database. This method should always be invoked before first invocation of any query method;
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

