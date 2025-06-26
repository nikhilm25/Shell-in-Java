package commands.builtin;

import prompt.PromptDto;

public class EchoCommand extends RedirectFileWriter implements BuiltinCommand {

    @Override
    public String name() {
        return "echo";
    }

    @Override
    public void execute(PromptDto input) {
        if (input.stdout().enabled())
            write(input.redirectFilename(), input.args(), input.stdout().append());
        else {
            if (input.stderr().enabled())
                write(input.redirectFilename(), "", input.stderr().append());
            System.out.println(String.join(" ", input.args()));
        }
    }
}
