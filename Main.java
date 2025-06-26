import commands.CommandFactory;
import shell.Shell;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        var factory = new CommandFactory();
        new Shell(factory).startRepl();
    }
}
