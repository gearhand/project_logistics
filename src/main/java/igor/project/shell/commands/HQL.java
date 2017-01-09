package igor.project.shell.commands;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by igor on 30.12.16.
 */
public class HQL implements Command {

    private Session session;

    public HQL (Session session) {
        this.session = session;
    }

    public String help() {
        return "Run HQL queries interactively";
    }

    public void execute (List<String> args) {
        if (args.size() == 0) {
            // Interactive part
            return;
        }
        System.out.println(args.get(0));
        Query query = session.createQuery(args.get(0));
        for (Object o : query.list()) {
            System.out.println(o);
        }
    }
}
