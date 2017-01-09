package igor.project.shell.commands;

import java.util.List;

/**
 * Created by Igor on 27.12.2016.
 */
public interface Command {
    String help ();

    //Object execute(String ... args);
    void execute(List<String> args);
}
