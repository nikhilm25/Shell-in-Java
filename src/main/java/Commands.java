import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Commands {

    public static String handleQuotes(String input) {
        String output = "";
        if (input.startsWith("\'") && input.endsWith("\'")) {
            Integer singleQuotesCount = 0;
            Boolean firstSpace = true;
            for (int i = 1; i < input.length(); i++) {
                if (input.charAt(i) == '\'') {

                    singleQuotesCount++;

                } else if (singleQuotesCount % 2 == 1) {

                    output += input.charAt(i);

                } else {
                    if (firstSpace) {
                        output += ' ';
                        firstSpace = false;
                    }
                }
            }

        } else if ((input.startsWith("\"") && input.endsWith("\""))) {
            output = input.substring(1, input.length() - 1);
        } else {
            output = String.join(" ",input.split("\\s+"));
        }
        return output;
    }

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

    public static void pwd(String input) {
        System.out.println(Main.currentDir);
    }

    public static void cd(String input) {
        if (input.equals("~")){
            Main.currentDir = Paths.get(System.getenv("HOME"));
            return;
        }
        Path newPath = Main.currentDir.resolve(input).normalize();
        if (!Files.exists(newPath) || !Files.isDirectory(newPath)) {
            System.out.println(input + ": No such file or directory");
        }
        Main.currentDir = newPath;
    }

    public static void cat(String input) {
        System.out.println(input);
    }
}
