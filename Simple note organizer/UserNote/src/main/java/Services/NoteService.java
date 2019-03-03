package Services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Database.DataBaseNote;
import Model.Note;


/**
 * @author  Shynkarenko Eduard
 *
 */

public class NoteService {

    private Note currentNote;
    private DataBaseNote dbNote;
    private UserService servUser;

    //add arguments to DataBaseNote
    public NoteService(){
        this.dbNote = new DataBaseNote("root", "admin");
        this.servUser = new UserService();
    }

    public void addNote(String title, String text, String date, String time, int userId, int idSender){
        dbNote.connect();
        //int userId = servUser.getIdByMail(mail);

        dbNote.addNote(title, text, date, time, userId, idSender);
        dbNote.disconnect();
    }

    public void addSameNoteForUsers(String title, String text, String date, String time, ArrayList<String> mailUserId, int idSender) {
        dbNote.connect();
        String dateStr;
        String timeStr;
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (String mail : mailUserId) {
            int i = servUser.getIdByMail(mail);
            Integer userId = Integer.valueOf(i);
            list.add(userId);
        }

        dbNote.addSameNotes(title, text, date, time, list, idSender);
        dbNote.disconnect();
    }


    public void updateNote(int id, String title, String text, String date, String time, String mail, int idSender) {
        dbNote.connect();
        String dateStr;
        String timeStr;
        int userId = servUser.getIdByMail(mail);
        dbNote.updateNote(id, title, text, date, time, userId, idSender);
        dbNote.disconnect();
    }


    public ArrayList<String> getMyNotes(int user_id){
        dbNote.connect();
        ArrayList<Note> list1 = dbNote.getMyNotes(user_id);
        ArrayList<String> list2 = new ArrayList<String>();
        if (list1.size()==0){
            System.out.println("You haven't any notes");
        }
        else {
            for (Note note : list1){
                int id = note.getId();
                int userId = note.getUserId();
                String nameMailOfRecep = servUser.getNameAndMail(userId);
                String title = note.getTitle();
                String text = note.getBody();
                String date = note.getDate();
                String time = note.getTime();
                String str = id + " " + title + " " + text + " " + date + " " + time+" "+nameMailOfRecep;
                list2.add(str);
            }
        }
        dbNote.disconnect();
        return list2;
    }

    public ArrayList<String> getNotesForMe(int user_Id){
        dbNote.connect();
        ArrayList<Note> list1 = dbNote.getNotesForMe(user_Id);
        ArrayList<String> list2 = new ArrayList<String>();
        if (list1.size()==0){
            System.out.println("You haven't any notes");
        }
        else {
            for (Note note : list1) {
                if (note.getIdSender() != user_Id) {
                    int idSender = note.getIdSender();
                    String nameMailOfSender = servUser.getNameAndMail(idSender);
                    int id = note.getId();
                    String title = note.getTitle();
                    String text = note.getBody();
                    String date = note.getDate();
                    String time = note.getTime();
                    String str = id + " " + title + " " + text + " " + date + " " + time + " " + nameMailOfSender;
                    list2.add(str);
                }
            }
        }
        dbNote.disconnect();
        return list2;
    }

    public void deleteNote(int noteId){
        dbNote.connect();
        dbNote.deleteNote(noteId);
        dbNote.disconnect();
    }

    public void deleteAllUserNotes(int user_Id){
        dbNote.connect();
        ArrayList<Note> list1 = dbNote.getNotesForMe(user_Id);
        if (list1.size()==0){
            System.out.println("You haven't any notes");
        }
        else {
            for (Note note : list1) {
                if (note.getIdSender() == user_Id) {
                    dbNote.deleteNote(note.getId());
                }
            }
        }
        dbNote.disconnect();
    }

}
