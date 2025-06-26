package commands.builtin;

import commands.Command;
import commands.CommandFactory;
import commands.path.PathCommand;
import prompt.PromptDto;

public class TypeCommand implements BuiltinCommand {

    private final CommandFactory factory;

    public TypeCommand(CommandFactory factory) {
        this.factory = factory;
    }

    @Override
    public String name() {
        return "type";
    }

    @Override
    public void execute(PromptDto input) {
        String arg = input.args().getFirst();
        factory
                .getCommand(arg)
                .ifPresentOrElse(
                        this::printCommandType,
                        () -> System.out.printf("%s: not found%n", arg)
                );
    }

    private void printCommandType(Command command) {
        if (command instanceof BuiltinCommand)
            System.out.printf("%s is a shell builtin%n", command.name());
        else if (command instanceof PathCommand)
            System.out.printf("%s is %s%n", command.name(), ((PathCommand) command).location());
    }
}
