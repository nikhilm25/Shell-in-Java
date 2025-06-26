package models;

public class WorkingDirectory {

    private static WorkingDirectory instance;
    private String currentDir;

    private WorkingDirectory() {
        this.currentDir = System.getProperty("user.dir");
    }

    public static WorkingDirectory getInstance() {
        if (instance == null)
            instance = new WorkingDirectory();

        return instance;
    }

    public String getCurrentDir() {
        return currentDir;
    }

    public void setCurrentDir(String dir) {
        this.currentDir = dir;
    }
}
