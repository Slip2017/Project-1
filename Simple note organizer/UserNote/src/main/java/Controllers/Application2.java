package Controllers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author  Shynkarenko Eduard
 *
 */
@SpringBootApplication
public class Application2 {

    public static void main(String[] args) {
        SpringApplication.run(Application2.class, args);
        NoteController nc = new NoteController();
        nc.createScheduler();
    }
}
