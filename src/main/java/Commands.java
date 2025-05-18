public class Commands {
    public static void echo(String input) {
        System.out.println(input);
    }

    public static void exit(String input) {
        if (input.equals("0")){
            System.exit(0);
        }
    }
}
