package pl.phpx;

import java.io.File;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        if(args.length > 0)
            switch (args[0]) {
                case "generate":
                case "g":
                    if(args.length == 3)
                        new NewFile(Arrays.copyOfRange(args, 1, 3));
                    return;
                default:
                    System.out.println("Invalid argument on position 0");

            }

        var projectManager = new ProjectManager();

        if (!projectManager.interpret()) {
            var interpreter = new PHPXInterpreter();
            interpreter.interpret(args[0]);
        }

    }
}