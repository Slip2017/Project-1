package Interfaces;

import Model.Note;

import java.util.ArrayList;


/**
 * @author  Shynkarenko Eduard
 *
 */
public interface AbstractDataBaseNote {

    void addNote(String title, String body, String date, String time, int user_Id, int IdSender);

    void addSameNotes(String title, String body, String date, String time, ArrayList<Integer> list, int IdSender);

    Note getNote(int id);

    ArrayList<Note> getMyNotes(int IdSender);

    ArrayList<Note> getNotesForMe(int user_Id);

    ArrayList<Note> getAllNotes();

    ArrayList<Note> getCurrTimeField();

    void deleteNote(int id);

    void updateNote(int id, String title, String body, String date, String time, int user_Id, int IdSender);

    void updateTitleField(int id, String title);

    void updateBodyField(int id, String body);

    void updateDateField(int id, String date);

    void updateTimeField(int id, String time);

    void updateUserIdField(int id, int user_Id);


    void connect();

    void disconnect();
}
