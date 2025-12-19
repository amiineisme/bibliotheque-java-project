package ma.est.biblio.util;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class LogUtil {

    private static final String FILE = "logs.txt";

    public static void log(String action, String details) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE, true))) {
            pw.println(
                    LocalDateTime.now() +
                    " | " + action +
                    " | " + details
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
