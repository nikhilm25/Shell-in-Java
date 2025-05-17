import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Function;

public class Main {
    
    HashMap<String, Function<String, Void>> mapOfCommands = new HashMap<>();




    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true) { 
            System.out.print("$ ");
            String input = scanner.nextLine();
            input = input.trim();
            String[] arr = input.split("\\s+", 2);

            


            System.out.println(input + ": command not found");
        }

        
    }
}
