import java.io.File;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Function;

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

    }
    static String pathEnv = System.getenv("PATH");

    public static String[] paths = pathEnv.split(File.pathSeparator);








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
                    continue;
                }
            }
            System.out.println(input + ": command not found");
        }

        
    }
}
