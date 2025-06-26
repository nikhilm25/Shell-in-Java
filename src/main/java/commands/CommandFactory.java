package commands;

import commands.builtin.*;
import commands.path.PathCommandFinder;

import java.util.*;

public class CommandFactory {

    private final PathCommandFinder pathFinder;
    private final Map<String, Command> builtins = new HashMap<>();

    public CommandFactory() {
        var exitCommand = new ExitCommand();
        builtins.put(exitCommand.name(), exitCommand);
        var echoCommand = new EchoCommand();
        builtins.put(echoCommand.name(), echoCommand);
        var typeCommand = new TypeCommand(this);
        builtins.put(typeCommand.name(), typeCommand);
        var pwdCommand = new PwdCommand();
        builtins.put(pwdCommand.name(), pwdCommand);
        var cdCommand = new CdCommand();
        builtins.put(cdCommand.name(), cdCommand);
        this.pathFinder = new PathCommandFinder();
    }

    public Optional<Command> getCommand(String name) {
        Command command = builtins.get(name);
        return command != null
                ? Optional.of(command)
                : pathFinder.findCommand(name);
    }

    public Set<String> findCommandKey(String name) {
        for (String key : builtins.keySet())
            if (key.startsWith(name)) {
                Set<String> set = new HashSet<>();
                set.add(key);
                return set;
            }

        return pathFinder.findCommandKey(name);
    }
}
