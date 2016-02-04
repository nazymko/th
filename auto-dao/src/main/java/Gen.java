import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by nazymko
 */
public class Gen {

    public static String COMMAND = "java -classpath jooq-3.7.2.jar;jooq-meta-3.7.2.jar;jooq-codegen-3.7.2.jar;mysql-connector-java-5.1.37.jar;.\n" +
        "  org.jooq.util.GenerationTool auto-dao.xml";

    public static void main(String[] args) {
        excCommand(COMMAND);
    }

    public static void excCommand(String command) {
        try {

            Process p = Runtime.getRuntime().exec(command);
            new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
            new Thread(new SyncPipe(p.getInputStream(), System.out)).start();
            PrintWriter stdin = new PrintWriter(p.getOutputStream());

            stdin.println(command);
            stdin.close();

            int returnCode = p.waitFor();
            System.out.println("Return code = " + returnCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
