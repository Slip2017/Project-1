package Services;

import Database.DataBaseNote;
import Database.DataBaseUser;
import Model.Note;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


/**
 * @author  Shynkarenko Eduard
 *
 */
public class TimeService extends TimerTask {

    private DataBaseUser dbUser;
    private DataBaseNote dbNote;
    private MailService mailServ;

    public TimeService() {
        this.dbUser = new DataBaseUser("root", "admin");
        this.dbNote = new DataBaseNote("root", "admin");
        this.mailServ = new MailService();
    }

    public TimeService(DataBaseUser dbUser, DataBaseNote dbNote, MailService mailServ) {
        this.dbUser = dbUser;
        this.dbNote = dbNote;
        this.mailServ = mailServ;
    }

    public void run() {
        String mailTo;
        String title;
        String text;
        String senderName;

        dbNote.connect();
        ArrayList<Note> list = dbNote.getCurrTimeField();
        dbNote.disconnect();

        dbUser.connect();

        if (list.size() > 1) {
            for (int i = 0; i < list.size(); i++) {
                mailTo = dbUser.getUser(list.get(i).getUserId()).getMail();
                senderName = dbUser.getUser(list.get(i).getIdSender()).getName();
                title = list.get(i).getTitle();
                text = "Уведомление от пользователя: "+senderName+".\n"+list.get(i).getBody();
                mailServ.sendMail(mailTo, title, text);
            }
        }else if(list.size() == 1){
            mailTo = dbUser.getUser(list.get(0).getUserId()).getMail();
            senderName = dbUser.getUser(list.get(0).getIdSender()).getName();
            title = list.get(0).getTitle();
            text = "Уведомление от пользователя: "+senderName+".\n"+list.get(0).getBody();
            mailServ.sendMail(mailTo, title, text);
        }

        dbUser.disconnect();


    }

    public void scheduler() {
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(this, 0, (1*60000));
    }


}













