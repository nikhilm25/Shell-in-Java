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
        } else {
            System.out.println(input + ": not found");
        }
    }
}
