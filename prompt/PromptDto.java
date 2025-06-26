package prompt;

import java.util.ArrayList;
import java.util.List;

public record PromptDto(
        String command,
        List<String> args,
        PromptRedirect stdout,
        PromptRedirect stderr,
        String redirectFilename
) {

    public List<String> toPathCommand() {
        List<String> pathCommand = new ArrayList<>();
        pathCommand.add(command);
        pathCommand.addAll(args);
        return pathCommand;
    }
}
