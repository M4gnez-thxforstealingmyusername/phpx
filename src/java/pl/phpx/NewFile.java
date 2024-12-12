package pl.phpx;

import java.io.File;
import java.io.IOException;

public class NewFile {

    public NewFile(String[] args) {
        String newFilepath;
        switch (args[0]) {
            case "t":
            case "temp":
            case "template":
                //TODO: check file name
                newFilepath = args[1] + "Template.phpx";
                break;
            case "f":
            case "file":
                newFilepath = args[1] + ".phpx";
                break;
            default:
                System.out.println("Invalid argument on position 1");
                return;
        }

        try {
            var newFile = new File(newFilepath);
            if (!newFile.exists()) {
                newFile.createNewFile();
                new ProjectManager().addToProject(newFilepath);
                System.out.printf("Created file: %s", newFilepath);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
