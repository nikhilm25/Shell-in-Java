package commands.builtin;

import prompt.PromptDto;

import java.io.IOException;

public class ExitCommand implements BuiltinCommand {

    @Override
    public String name() {
        return "exit";
    }

    @Override
    public void execute(PromptDto input) {
        disableRawMode();

        if (input.args().isEmpty())
            System.exit(0);
        System.exit(Integer.parseInt(input.args().getFirst()));
    }

    private void disableRawMode() {
        String[] cmd = {"/bin/sh", "-c", "stty sane < /dev/tty"};
        try {
            Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
