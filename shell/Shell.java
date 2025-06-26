package shell;

import commands.CommandFactory;
import prompt.PromptDto;
import prompt.PromptInputTokenizer;
import prompt.PromptTranslator;

import java.io.IOException;
import java.util.*;

public class Shell {

    private final CommandFactory commandFactory;

    private StringBuilder builder;
    private Set<String> autoCompleteOptions;
    private boolean showAutoCompleteOptions;

    public Shell(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
        this.builder = new StringBuilder();
        this.autoCompleteOptions = new HashSet<>();
        this.showAutoCompleteOptions = false;
    }

    public void startRepl() throws IOException {
        enableRawMode();

        while (true) {
            System.out.print("$ ");
            String input = readStdin();

            if (input.isEmpty())
                continue;

            List<String> keywords = new PromptInputTokenizer(input).tokenize();
            PromptDto prompt = new PromptTranslator(keywords).translate();
            commandFactory
                    .getCommand(prompt.command())
                    .ifPresentOrElse(
                            command -> command.execute(prompt),
                            () -> System.out.println(prompt.command() + ": command not found")
                    );
        }
    }

    private String readStdin() throws IOException {
        while (true) {
            char ch = (char) System.in.read();

            if (ch == '\n') {
                System.out.println();
                break;
            } else if (ch == '\b' || ch == 127) {
                deleteLastLetter();
            } else if (ch == '\t' && showAutoCompleteOptions) {
                String options = String.join("  ", autoCompleteOptions.stream().sorted().toList());
                System.out.println("\n" + options);
                showAutoCompleteOptions = false;
                autoCompleteOptions.clear();
                System.out.print("$ " + builder);
            } else if (ch == '\t') {
                autoComplete();
            } else {
                System.out.print(ch);
                builder.append(ch);
            }
        }

        String input = builder.toString();
        builder = new StringBuilder();
        return input;
    }

    private void deleteLastLetter() {
        if (!builder.isEmpty()) {
            System.out.print("\b \b");
            builder.deleteCharAt(builder.length() - 1);
        }
    }

    private void autoComplete() {
        if (builder.isEmpty())
            return;

        autoCompleteOptions = commandFactory.findCommandKey(builder.toString());

        if (autoCompleteOptions.size() == 1) {
            writeCommandInShell(autoCompleteOptions.iterator().next() + " ");
            return;
        }

        OptionalInt minLength = autoCompleteOptions
                .stream()
                .filter(s -> s.startsWith(builder.toString()))
                .mapToInt(String::length)
                .min();

        List<String> shortestCommands = minLength.isPresent() ?
                autoCompleteOptions
                        .stream()
                        .filter(s -> s.startsWith(builder.toString()) && s.length() == minLength.getAsInt())
                        .toList() :
                Collections.emptyList();

        if (shortestCommands.size() == 1) {
            writeCommandInShell(shortestCommands.getFirst());
            return;
        }

        if (autoCompleteOptions.size() > 1)
            showAutoCompleteOptions = true;

        System.out.print('\u0007');
    }

    private void writeCommandInShell(String commandName) {
        while (!builder.isEmpty()) {
            System.out.print("\b \b");
            builder.deleteCharAt(builder.length() - 1);
        }

        builder = new StringBuilder(commandName);
        System.out.print(builder);
    }

    private void enableRawMode() {
        String[] cmd = {"/bin/sh", "-c", "stty -icanon -echo < /dev/tty"};

        try {
            Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
