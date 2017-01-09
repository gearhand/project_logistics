package igor.project.shell.commands;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.jline.terminal.Terminal;

import java.util.List;

/**
 * Created by Igor on 27.12.2016.
 */
public class ListEntities implements Command {

    private Session session;
    private Terminal terminal;

    public ListEntities (Session session, Terminal terminal) {
        this.session = session;
        this.terminal = terminal;
    }

    public String help () {
        String help = "Usage: list companies|products|ports";
        return help;
    }

    public void execute (List<String> args) {
        String entityName;
        Query query;
        String help = "companies|products|ports";
        if (args.size() == 0) {
 //           System.out.println("Missing argument; must be" + help);
            terminal.writer().println("Missing argument; must be " + help);
            return;
        }
        if (args.get(0).equals("ports")) {
            query = session.createQuery("select city from Port");
            for (Object o : query.list()) {
//                System.out.println(o);
                terminal.writer().println(o);
            }
            return;
        }
        else if ( args.get(0).equals("companies"))
            entityName = "Company";
        else if (args.get(0).equals("products"))
            entityName = "Product";
        else {
//            System.out.println("Wrong command argument; must be" + help);
            terminal.writer().println("Wrong command argument; must be" + help);
            return;
        }

        query = session.createQuery("select name from " + entityName);
        //Query query = session.createQuery("select name from Company");
        for (Object o : query.list()) {
//            System.out.println(o);
            terminal.writer().println(o);
        }
        return;
    }
}
