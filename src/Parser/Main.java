package Parser;

import java.nio.charset.Charset;

/**
 * Created by jorn on 12/19/16.
 */
public class Main {
    public static void main(String[] args) {
        long tStart = System.currentTimeMillis();

//        MoviesParser.getInstance().parseFile();
//        ActorParser.getInstance().parseFile();
//        ActressParser.getInstance().parseFile();
        GenreParser.getInstance().parseFile();
//        LocationParser.getInstance().parseFile();

        System.out.println("Finished parsing files. Time elapsed: " + (System.currentTimeMillis() - tStart) / 1000);
    }
}
