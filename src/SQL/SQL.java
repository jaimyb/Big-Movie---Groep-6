package SQL;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created by Valesco on 1/16/2017.
 */
abstract class SQL {

    String filename;
    String line = "";
    BufferedReader br = null;

    void parseFile() {
        System.out.println("Parsing file " + filename);
        readFile("input/" + filename + ".csv");
        System.out.println("Closing print writer");
        System.out.println("Finished file " + filename);
    }

    private Stream<String> readFile(final String path) {
        try {
            br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
                String[] str_line = line.split(",");
                System.out.println("test "+str_line[0]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return Stream.empty();
    }

}
