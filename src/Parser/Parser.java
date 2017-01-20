package Parser;

import java.io.*;
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
        long tStart = System.currentTimeMillis();
        System.out.println("Parsing file " + filename);
        readFile("input/" + filename + ".list").forEach(this::process);
        pw.close();
        System.out.println("Finished file " + filename + " in " + (System.currentTimeMillis() - tStart) / 1000 + "s");
    }

    abstract void process(String line);

    String formatAsCSV(String... values) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String value : values) {
            stringBuilder.append(value).append(',');
        }
        return stringBuilder.toString();
    }

    void writeToStream(String s){
        // Print to stream, remove last comma
        pw.println(s.substring(0, s.length() - 1));
    }

    String escapeQuotes(String s) {
        return s.replaceAll("\"", "\"\"");
    }

    String wrapInQuotes(String s) {
        return String.format("\"%s\"", s);
    }

    String partBefore(String line, String selector) {
        int index = line.indexOf(selector);
        if(index == -1) {
            return "";
        }

        return line.substring(0, index);
    }

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
            FileOutputStream stream = new FileOutputStream(file);
            pw = new PrintWriter(stream, true);
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
