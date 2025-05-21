import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

public class Main {
    
    public static HashMap<String, Function<String, Void>> mapOfCommands = new HashMap<>();

    static {
        mapOfCommands.put("echo", (input) -> {
            Commands.echo(input);
            return null;
        });

        mapOfCommands.put("exit", (input) -> {
            Commands.exit(input);
            return null;
        });

        mapOfCommands.put("type", (input) -> {
            Commands.type(input);
            return null;
        });

        mapOfCommands.put("pwd", (input) -> {
            Commands.pwd(input);
            return null;
        });

    }
    static String pathEnv = System.getenv("PATH");

    public static String[] paths = pathEnv.split(File.pathSeparator);

    public static HashMap<String, Path> externalExecutables = new HashMap<>();

    static {
        for (String path : paths) {
            Path properPath = Paths.get(path);

            if (Files.isRegularFile(properPath)) {
                if (Files.isExecutable(properPath)) {
                    String base = properPath.toFile().getName();
                    externalExecutables.put(base, properPath);
                }
            } else if (Files.isDirectory(properPath)) {
                try (Stream<Path> paths = Files.list(properPath)) {
                    paths
                            .filter(Files::isRegularFile)
                            .filter(Files::isExecutable)
                            .forEach(p -> {
                                String base = p.getFileName().toString();
                                externalExecutables.put(base, p);
                            });
                } catch (IOException e) {
                    e.printStackTrace(); // Handle or log properly
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) { 
            System.out.print("$ ");
            String input = scanner.nextLine().trim();
            String[] arr = input.split("\\s+", 2);
            String commandName = arr[0];


            if (mapOfCommands.containsKey(commandName)) {
                if (arr.length == 2) {
                    String argument = arr[1];
                    mapOfCommands.get(commandName).apply(argument);
                } else {
                    String argument = "";
                    mapOfCommands.get(commandName).apply(argument);
                }
            } else if (externalExecutables.containsKey(commandName)) {
                List<String> commandList = new ArrayList<>();
                commandList.add(externalExecutables.get(commandName).toFile().getName().toString());
                if (arr.length == 2) {
                    String[] arguments = arr[1].split("\\s+");
                    for (String argument : arguments) {
                        if (!argument.isEmpty()) commandList.add(argument);
                    }
                }
                ProcessBuilder pb = new ProcessBuilder(commandList);
                try {
                    Process process = pb.inheritIO().start();
                    process.waitFor();
                } catch (Exception e) {
                    System.out.println("Error running external command: " + e.getMessage());
                }
            } else {
                System.out.println(input + ": command not found");
            }
        }



        
    }
}
