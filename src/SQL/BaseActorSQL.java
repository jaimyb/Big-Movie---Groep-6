package SQL;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created by Valesco on 1/16/2017.
 */
abstract class BaseActorSQL {

    String filename;
    String line = "";
    BufferedReader br = null;

    void parseFile() {
        System.out.println("Parsing file " + filename);
        readFile("output/" + filename + ".csv");
        System.out.println("Closing print writer");
        System.out.println("Finished file " + filename);
    }

    private Stream<String> readFile(final String path) {
        try {
            br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
                String[] str_line = line.split(",");
                System.out.println(str_line[2]+" "+str_line[1]+" "+str_line[3]);
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
