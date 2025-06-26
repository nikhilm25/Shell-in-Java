package commands.builtin;

import models.WorkingDirectory;
import prompt.PromptDto;

import java.io.File;
import java.nio.file.Path;

public class CdCommand implements BuiltinCommand {

    private final WorkingDirectory workingDirectory;

    public CdCommand() {
        this.workingDirectory = WorkingDirectory.getInstance();
    }

    @Override
    public String name() {
        return "cd";
    }

    @Override
    public void execute(PromptDto input) {
        String dir = input.args().getFirst();

        if (dir.startsWith("/"))
            changeDir(dir);
        else if (dir.startsWith("~")) {
            String usersDir = System.getenv("HOME");
            Path path = Path.of(usersDir);
            changeDir(path.toString());
        } else {
            Path path = Path.of(workingDirectory.getCurrentDir()).resolve(dir).normalize();
            changeDir(path.toString());
        }
    }

    private void changeDir(String dir) {
        if (new File(dir).isDirectory())
            workingDirectory.setCurrentDir(dir);
        else
            System.out.printf("cd: %s: No such file or directory%n", dir);
    }
}
