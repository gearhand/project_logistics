
package igor.project.shell;

import igor.project.HibernateUtil;
import igor.project.shell.commands.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.jline.reader.*;
import org.jline.reader.Parser;
import org.jline.reader.impl.*;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Shell {
    private Session session;

    public Shell(Session session) { this.session = session; }

    public void execute () throws IOException {
//        Session session = HibernateUtil.getSessionFactory().openSession();
        String prompt = ">>> ";
        String rightPrompt = null;
        Character mask = null;
        String trigger = null;
        boolean color = false;
        boolean timer = false;
        boolean timeToExit = false;

        Completer completer = new StringsCompleter("companies", "products");
        Parser parser = new DefaultParser();
        Terminal terminal = TerminalBuilder.terminal();
//        Terminal terminal = TerminalBuilder.builder().build();

        LineReader reader = LineReaderBuilder.builder()
                .terminal(terminal)
                .completer(completer)
                .parser(parser)
                .build();

        ParsedLine line;
        List<String> input;

        Map<String, Command> commandMap = new HashMap<String, Command>();
        commandMap.put("list", new ListEntities(session, terminal));
        commandMap.put("info", new Information(session, reader));
        commandMap.put("hql", new HQL(session));
        commandMap.put("add", new Add(session, reader));
        String command;

        // Shell loop
        while(!timeToExit) {
            line = parser.parse(reader.readLine(prompt),0);
            //line = reader.getParser().parse(reader.readLine(prompt),0);
            input = new LinkedList<>(line.words());
            command = input.remove(0);
            if (command.equals("exit")) {
                timeToExit = true;
            }
            else if (commandMap.containsKey(command)){
                commandMap.get(command).execute(input);
            }
            else  {
//                System.out.println("Here should be help;\nenter \"exit\" to exit");
                printHelp(commandMap, terminal);
            }
        }

        // Close terminal after all
//        HibernateUtil.shutdown();
        System.out.println("Closing terminal...");
        terminal.close();
    }

    private static void printHelp (Map<String, Command> map, Terminal terminal) {
        for (String s : map.keySet()) {
//            System.out.println(s + "\t" + map.get(s).help());
            terminal.writer().println(s + "\t" + map.get(s).help());
        }
//        System.out.println("exit\texit program");
        terminal.writer().println("exit\texit program");
    }

}