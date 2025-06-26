package commands.builtin;

import models.WorkingDirectory;
import prompt.PromptDto;

public class PwdCommand implements BuiltinCommand {

    private final WorkingDirectory workingDirectory;

    public PwdCommand() {
        this.workingDirectory = WorkingDirectory.getInstance();
    }

    @Override
    public String name() {
        return "pwd";
    }

    @Override
    public void execute(PromptDto input) {
        System.out.println(workingDirectory.getCurrentDir());
    }
}
