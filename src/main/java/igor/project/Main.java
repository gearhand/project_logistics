package igor.project;

//import igor.project.shell.Shell;
import igor.project.shell.Shell;
import org.hibernate.Session;

        import java.io.IOException;

public class Main
{
    public static void main( String[] args ) throws IOException {
        //Open session and create a shell
        Shell shell = new Shell(HibernateUtil.getSessionFactory().openSession());
        // Execute shell loop
        shell.execute();
        // Close connections before finishing
        HibernateUtil.shutdown();
    }
}
