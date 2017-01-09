package igor.project.shell.commands;

import igor.project.entities.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;
import org.jline.reader.*;
import org.jline.reader.impl.DefaultParser;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.terminal.Terminal;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by Igor on 30.12.2016.
 */
public class Add implements Command {

    private Session session;
    private Terminal terminal;
    private LineReader reader;
    private Transaction transaction;
    private List<String> types;

    public Add (Session session, LineReader reader) {
        this.session = session;
        this.transaction = session.beginTransaction();
        this.terminal = reader.getTerminal();
        this.reader = reader;
        types = getAvailableTypes();
    }

    public String help() {
        return "Usage: add company|product|port|shipment|capacity";
    }

    private List<String> getAvailableTypes () {
        Query query = session.createQuery("select t.name from Type t");
        return (List<String>) query.list();
    }

    public void execute(List<String> args) {

        if(args.get(0).equals("company")) {
            args.remove(0);
            addCompany();
        }
        else if (args.get(0).equals("port")) {
            args.remove(0);
            addPort();
        }
        else if (args.get(0).equals("product")) {
            args.remove(0);
            addProduct();
        }
        else if (args.get(0).equals("shipment")) {
            args.remove(0);
            addShipment();
        }
        else if (args.get(0).equals("capacity")) {
            args.remove(0);
            addCapacity();
        }
    }

    private void addCompany () {
        String name = reader.readLine("Enter company name: ");
        Query companies = session.createQuery("from Company c where c.name = :name")
                .setParameter("name", name);
        if (!companies.list().isEmpty()) {
            terminal.writer().println("Company \"" + name + "\" already exists in database!");
        }
        else {
            String country = reader.readLine("Enter country: ");
            Company company = new Company(name, country);
            session.saveOrUpdate(company);
            transaction.commit();
        }
    }

    private void addPort() {
        String city = reader.readLine("Enter port city name: ");
        Query ports = session.createQuery("from Port p where p.city = :city")
                .setParameter("city", city);
        if (!ports.list().isEmpty()) {
            terminal.writer().println("Port \"" + city + "\" already exists in database!");
        }
        else {
            String country = reader.readLine("Enter country: ");
            Port port = new Port(city, country);
            String capSwitch = reader.readLine("Do you wish to add capacities now? [Y/n]: ");
            if (capSwitch.length() == 0 || capSwitch.toLowerCase().charAt(0) == 'y') {
                addCapacity(port);
            }
            session.saveOrUpdate(port);
            transaction.commit();
        }
    }

    private void addProduct() {
        //Set<String> products = new HashSet(session.createQuery("select p.name from Product p").list());
        String name = reader.readLine("Enter product name: ");
        Query products = session.createQuery("from Product p where p.name = :name")
                .setParameter("name", name);
        //Check uniqueness
        if (!products.list().isEmpty()) {
        //if (products.contains(name)) {
            terminal.writer().println("Product \"" + name + "\" already exists in database!");
        }
        else {
            String typesPrompt = "Enter product type " + getAvailableTypes().toString() + ": ";
            String typeName = reader.readLine(typesPrompt);
            Query query = session.createQuery("from Type t where t.name = :name")
                    .setParameter("name", typeName);
            try {
                session.saveOrUpdate(new Product(name, (Type) query.uniqueResult()));
                transaction.commit();
            } catch (ConstraintViolationException e) {
                terminal.writer().printf("Product \"%s\" already exists in database!", name);
            }
        }
    }

    private void addShipment() {
        String companyName = reader.readLine("Enter company name: ");
        String portName = reader.readLine("enter port city name: ");
        String productName = reader.readLine("Enter product name: ");
        Integer quantity = new Integer(reader.readLine("Enter quantity: "));
        Query companyQuery = session.createQuery("from Company c where c.name = :name")
                .setParameter("name", companyName);
        Query portQuery = session.createQuery("from Port p where p.city = :city")
                .setParameter("city", portName);
        Query productQuery = session.createQuery("from Product p where p.name = :name")
                .setParameter("name", productName);
        Company company = (Company)companyQuery.uniqueResult();
        Port port = (Port)portQuery.uniqueResult();
        Product product = (Product)productQuery.uniqueResult();
        Shipment shipment = new Shipment(company,product,port,quantity);
        session.saveOrUpdate(shipment);
        transaction.commit();
    }

    private void addCapacity () {
        String portName = reader.readLine("Enter port city name: ");
        String typeName = reader.readLine("Enter product type " +
                "(container, bulk liquid, bulk solid): ");
        Integer quantity = new Integer(reader.readLine("Enter capacity: "));
        Query typeQuery = session.createQuery("from Type t where t.name = :name")
                .setParameter("name", typeName);
        Query portQuery = session.createQuery("from Port p where p.city = :city")
                .setParameter("city", portName);
        Type type = (Type)typeQuery.uniqueResult();
        Port port = (Port)portQuery.uniqueResult();
        Capacity capacity = new Capacity(type, port, quantity);
        session.saveOrUpdate(capacity);
        transaction.commit();
    }

    private void addCapacity(Port port) {
        String typeName = reader.readLine("Enter product type " +
                "(container, bulk liquid, bulk solid): ");
        Integer quantity = new Integer(reader.readLine("Enter capacity: "));
        Query query = session.createQuery("from Type t where t.name = :name")
                .setParameter("name", typeName);
        Capacity capacity = new Capacity((Type)query.uniqueResult(), port, quantity);
        session.saveOrUpdate(capacity);
    }
}
