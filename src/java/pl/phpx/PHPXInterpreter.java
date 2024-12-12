package pl.phpx;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PHPXInterpreter {

    public void interpret (String filename) {
        var oldFile = new File(filename);

        //TODO: replace with something that works better
        var dir = new File("out/");
        if (!dir.exists())
            dir.mkdir();

        try (var writer = new FileWriter("out/" + oldFile.getName().replace(".phpx", ".php"))) {

            var scanner = new Scanner(oldFile);

            for (int i = 1; scanner.hasNextLine(); i++) {
                var line = scanner.nextLine().strip();

                if (line.isEmpty())
                    continue;

                if (!line.startsWith("?") && !line.equals("/?") && !line.equals("?{") && !line.equals("/}")) {
                    writer.write(line + "\n");
                    continue;
                }

                line = line.replace(" ", "");

                switch (line) {
                    case "/?" -> {
                        writer.write("<?php } ?>\n");
                        continue;
                    }
                    case "?{" -> {
                        writer.write("<?php\n");
                        continue;
                    }
                    case "?}" -> {
                        writer.write("?>\n");
                        continue;
                    }
                }

                if (!line.contains("(")) {
                    System.out.printf("No '(' found at %s:%d", oldFile.getName(), i);
                    return;
                }

                if (!line.contains(")")) {
                    System.out.printf("No ')' found at %s:%d", oldFile.getName(), i);
                    return;
                }

                int index = line.indexOf("(");

                String command = line.substring(1, index);
                //TODO: check content viability
                String content = line.substring(index + 1, line.lastIndexOf(")"));

                var lineBuilder = new StringBuilder();

                switch (command) {
                    case "if":
                        lineBuilder.append("<?php if(");
                        lineBuilder.append(content);
                        lineBuilder.append(") { ?>");
                        break;
                    case "while":
                        lineBuilder.append("<?php while(");
                        lineBuilder.append(content);
                        lineBuilder.append(") { ?>");
                        break;
                    case "for":
                        lineBuilder.append("<?php for(");
                        lineBuilder.append(content);
                        lineBuilder.append(") { ?>");
                        break;
                    default:
                        System.out.printf("Instruction not recognised at %s:%d", oldFile.getName(), i);
                        return;
                }

                writer.write(lineBuilder + "\n");
            }
            System.out.printf("%s: interpretation complete with no errors\n", oldFile.getName());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No file specified");
        }

    }

}
