package igor.project.shell.commands;

import igor.project.entities.Company;
import igor.project.entities.Shipment;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.jline.reader.LineReader;
import org.jline.terminal.Terminal;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.stream.BaseStream;
import java.util.stream.Collectors;

/**
 * Created by igor on 30.12.16.
 */
public class Information implements Command {

    private Session session;
    private LineReader reader;
    private Terminal terminal;
    private Query query;

    public Information (Session session, LineReader reader) {
        this.session = session;
        this.reader = reader;
        this.terminal = reader.getTerminal();
    }

    public String help () {
        return "Usage: info company|port|product <name>";
    }

    public void execute (List<String> args) {
        // Missing arguments check
        if (args.size() == 0) {
            help();
            return;
        }

        if(args.get(0).equals("company")) {
            args.remove(0);
            companyInformation(args);
            return;
        }
        else if (args.get(0).equals("port")) {
            args.remove(0);
            portInformation(args);
            return;
        }
        else if (args.get(0).equals("product")) {
            args.remove(0);
            productInformation(args);
            return;
        }
    }

    private void companyInformation(List<String> args) {
        // Takes the only argument "name", or run interactively
        if (args.size() == 0) {
            // Interactive part
            return;
        }

        query = session.createQuery("select s.volume, s.product.name, s.port.city " +
                "from Shipment s where s.company.name = :name").setParameter("name",args.get(0));
        System.out.println("Company: " + args.get(0));
        Object[] foo;
        for (Object o : query.list()) {
            foo = (Object[]) o;
            //System.out.printf("Trades %d units of %s through %s port\n", foo[0], foo[1], foo[2] );
            terminal.writer().printf("Trades %d units of %s through %s port\n", foo[0], foo[1], foo[2] );
        }
    }

    private void portInformation (List<String> args) {
        // Takes the only argument "city", or run interactively
        String name;
        if (args.size() == 0) {
            // Interactive part
            name = reader.readLine("Enter port city name: ");
        }
        else {
            name = args.get(0);
        }
        query = session.createQuery("select s.company.name, s.volume, s.product.name " +
                "from Shipment s where s.port.city = :name").setParameter("name", name);
        Object[] foo;
        for (Object o : query.list()) {
            foo = (Object[]) o;
            System.out.printf("Company %s trades %d units of %s through this port\n", foo[0], foo[1], foo[2] );
        }
    }

    private void productInformation (List<String> args) {
        if (args.size() == 0) {
            // Interactive part
            return;
        }

        query = session.createQuery("select s.volume, s.port.city, s.company.name " +
                "from Shipment s where s.product.name = :name").setParameter("name", args.get(0));
        Object[] foo;
        for (Object o : query.list()) {
            foo = (Object[]) o;
            System.out.printf("%d units through %s by %s\n", foo[0], foo[1], foo[2] );
        }
    }
}
