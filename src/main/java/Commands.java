import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Commands {
    public static void echo(String input) {
        System.out.println(input);
    }

    public static void exit(String input) {
        if (input.equals("0")){
            System.exit(0);
        }
    }

    public static void type(String input) {
        if (Main.mapOfCommands.containsKey(input)){
            System.out.println(input + " is a shell builtin");
            return;
        }
        for (String path : Main.paths) {
            Path properPath = Paths.get(path);
            Path fullPath = properPath.resolve(input);
            if (Files.isExecutable(fullPath)) {
                System.out.println(input + " is " + fullPath);
                return;
            }
        }
        System.out.println(input + ": not found");

    }
}
                                                