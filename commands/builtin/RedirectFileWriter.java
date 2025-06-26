package commands.builtin;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

class RedirectFileWriter {

    void write(String filename, List<String> args, boolean append) {
        write(filename, String.join(" ", args) + "\n", append);
    }

    void write(String filename, String content, boolean append) {
        try (var writer = new FileWriter(filename, append)) {
            writer.write(content);
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
