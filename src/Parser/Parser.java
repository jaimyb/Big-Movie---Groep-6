package Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created by jorn on 12/19/16.
 */
abstract class Parser {
    PrintWriter pw = new PrintWriter(System.out);
    String filename;

    void parseFile() {
        readFile("input/" + filename + ".list").forEach(this::process);
    }

    abstract void process(String line);

    String partBetween(String line, String firstSelector, String secondSelector) {
        int startIndex = line.indexOf(firstSelector);

        if(startIndex == -1) {
            return "";
        }

        int endIndex = line.indexOf(secondSelector, startIndex + 1);

        if(endIndex == -1) {
            return "";
        }

        return line.substring(startIndex + 1, endIndex);
    }

    String partBetween(String line, String selector) {
        return partBetween(line, selector, selector);
    }

    String allButLastWord(String line) {
        int index = getLastSpaceIndex(line);

        return line.substring(0, index).trim();
    }

    String lastWord(String line) {
        int index = getLastSpaceIndex(line);

        return line.substring(index).trim();
    }

    private int getLastSpaceIndex(String line) {
        int     lastTab = line.lastIndexOf("\t"),
                lastSpace = line.lastIndexOf(" ");

        return ((lastTab > lastSpace) ? lastTab : lastSpace) + 1;
    }

    PrintWriter createPrintWriter() {
        return createPrintWriter("output/" + filename + ".csv");
    }

    private PrintWriter createPrintWriter(String filename) {
        File file = new File("./" + filename);

        PrintWriter pw = null;

        try {
            pw = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return pw;
    }

    private Stream<String> readFile(final String path) {
        try {
            return Files.lines(Paths.get(path), Charset.availableCharsets().get("iso-8859-1"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Stream.empty();
    }

}
