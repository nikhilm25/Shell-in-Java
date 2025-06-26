package commands;

import prompt.PromptDto;

public interface Command {

    String name();

    void execute(PromptDto input);
}
