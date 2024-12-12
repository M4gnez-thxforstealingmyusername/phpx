package pl.phpx;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ProjectManager {

    private static ProjectManager instance;

    private final String projectFilepath = System.getProperty("user.dir") + "/.xpj";;

    public String getProjectFilepath() {
        return projectFilepath;
    }

    public boolean interpret() {
        try {
            var file = new File(projectFilepath);

            if(!file.exists())
                return false;

            var scanner = new Scanner(file);

            if (scanner.hasNextLine())
                System.out.printf("Project name: %s\n", scanner.nextLine());

            var interpreter = new PHPXInterpreter();

            while (scanner.hasNextLine()) {
                interpreter.interpret(scanner.nextLine());
            }

        }  catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return true;
    }

    public void addToProject(String filepath) {
        var configFile = new File(projectFilepath);
        if(configFile.exists()) {
            try (var writer = new FileWriter(configFile, true)) {
                writer.write("\n");
                writer.write(filepath);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        }
    }

}
