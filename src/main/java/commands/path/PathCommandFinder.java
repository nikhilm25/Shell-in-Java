package commands.path;

import commands.Command;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public class PathCommandFinder {

    private final String path;

    public PathCommandFinder() {
        this.path = System.getenv("PATH");

        if (Objects.isNull(path))
            throw new IllegalStateException("PATH environment variable not set");
    }

    public Optional<Command> findCommand(String command) {
        String[] folders = path.split(":");

        for (String folder : folders) {
            var commandPath = Path.of(folder, command);
            boolean exists = Files.exists(commandPath);

            if (exists)
                return Optional.of(
                        new PathCommand(command, commandPath.toString())
                );
        }

        return Optional.empty();
    }

    public Set<String> findCommandKey(String name) {
        String[] folders = path.split(":");
        Set<String> keys = new HashSet<>();

        for (String folder : folders) {
            try (Stream<Path> files = Files.list(Paths.get(folder));) {
                keys.addAll(
                        files
                                .map(Path::getFileName)
                                .map(Path::toString)
                                .filter(file -> file.startsWith(name))
                                .toList()
                );
            } catch (IOException ignored) {
            }
        }

        return keys;
    }
}
