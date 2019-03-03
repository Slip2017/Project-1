package Controllers;


import Services.TimeService;
import com.sun.org.apache.xpath.internal.SourceTree;
import Model.Note;
import Services.NoteService;
import Services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;


/**
 * @author  Shynkarenko Eduard
 *
 */


@RestController
public class NoteController {
    private Note activeNote;
    private UserController contrUser;
    private NoteService servNote;
    private UserService servUser;
    private TimeService timeService;

    public NoteController() {
        this.contrUser = new UserController();
        this.servNote = new NoteService();
        this.servUser = new UserService();
        this.timeService = new TimeService();
    }

    public NoteController(UserController contrUser) {
        this.contrUser = contrUser;
        this.servNote = new NoteService();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/createnote")
    @ResponseStatus(HttpStatus.CREATED)
    public String createNote(@RequestParam(value = "title", defaultValue = "") String title,
                             @RequestParam(value = "text", defaultValue = "") String text,
                             @RequestParam(value = "date", defaultValue = "") String date,
                             @RequestParam(value = "time", defaultValue = "") String time,
                             @RequestParam(value = "mail", defaultValue = "") String mail) {
        String res=null;

        int userId = servUser.getIdByMail(mail);
        if (contrUser.getActiveUser() != null) {
            int idSender = contrUser.getActiveUser().getId();
            if (servUser.containsIdSender(userId, idSender)) {

                if (title == null) {
                    title = "No title";
                }
                if (mail == null) {
                    mail = contrUser.getActiveUser().getMail();
                }
                if (date == null && time != null) {
                    date = Calendar.getInstance().get(YEAR) + ".0" + (Calendar.getInstance().get(MONTH) + 1) + "." + Calendar.getInstance().get(DAY_OF_MONTH);
                }
                servNote.addNote(title, text, date, time, userId, idSender);
                res = "Заметка успешно создана";
            } else {
                res = "Вы не можете оставлять заметки этому пользователю";
            }
        }
        return res;
    }

    //same note for several persons
    @RequestMapping(method = RequestMethod.POST, value = "/createnotes")
    @ResponseStatus(HttpStatus.CREATED)
    public void createNotes(@RequestParam(value = "title", defaultValue = "") String title,
                            @RequestParam(value = "text", defaultValue = "") String text,
                            @RequestParam(value = "date", defaultValue = "") String date,
                            @RequestParam(value = "time", defaultValue = "") String time,
                            @RequestParam(value = "mail", defaultValue = "") String[] mails) {
        if (contrUser.getActiveUser() != null) {
            int idSender = contrUser.getActiveUser().getId();
            ArrayList<String> mailUserId = new ArrayList<String>();
            for (String mail : mails) {

                int userId = servUser.getIdByMail(mail);
                if (!servUser.containsIdSender(userId, idSender)) {
                    System.out.println("Вы не можете оставлять заметки этому пользователю " + servUser.getUser(userId).getName());
                    continue;
                }
                mailUserId.add(mail);
            }
            if (title == null) {
                title = "No title";
            }
            if (date == null && time != null) {
                date = Calendar.getInstance().get(YEAR) + ".0" + (Calendar.getInstance().get(MONTH) + 1) + "." + Calendar.getInstance().get(DAY_OF_MONTH);
            }

            servNote.addSameNoteForUsers(title, text, date, time, mailUserId, idSender);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/editnote")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editNote(@RequestParam(value = "id", defaultValue = "") int id,
                         @RequestParam(value = "title", defaultValue = "") String title,
                         @RequestParam(value = "text", defaultValue = "") String text,
                         @RequestParam(value = "date", defaultValue = "") String date,
                         @RequestParam(value = "time", defaultValue = "") String time,
                         @RequestParam(value = "mail", defaultValue = "") String mail) {
        if (mail == null & contrUser.getActiveUser()!=null) {
            mail = contrUser.getActiveUser().getMail();
        }
        int idSender = contrUser.getActiveUser().getId();
        servNote.updateNote(id, title, text, date, time, mail, idSender);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/mynotes")
    public ArrayList<String> showMyNotes() {
        ArrayList<String> list = new ArrayList<>();
        if (contrUser.getActiveUser() != null) {
            int idSender = contrUser.getActiveUser().getId();
            list = servNote.getMyNotes(idSender);
        }
        return list;
    }

    //show only notes from other users
    @RequestMapping(method = RequestMethod.GET, value = "/notesforme")
    public ArrayList<String> showNotesForMe() {
        ArrayList<String> list = new ArrayList<>();
        if (contrUser.getActiveUser() != null) {
            int user_id = contrUser.getActiveUser().getId();
             list = servNote.getNotesForMe(user_id);
        }
        return list;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deletenote")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNote(@RequestParam(value = "id", defaultValue = "") int id) {
        servNote.deleteNote(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteallmynotes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllMyNotes() {
        if (contrUser.getActiveUser() != null) {
            int idSender = contrUser.getActiveUser().getId();
            servNote.deleteAllUserNotes(idSender);
        }
    }

    public void createScheduler() {
        timeService.scheduler();
    }

}
