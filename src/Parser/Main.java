package Parser;

import java.nio.charset.Charset;

/**
 * Created by jorn on 12/19/16.
 */
public class Main {

    private static Parser[] parsers = new Parser[] {
//            MoviesParser.getInstance(),
//        ActorParser.getInstance(),
//        ActressParser.getInstance(),
//        GenreParser.getInstance(),
//        LocationParser.getInstance(),
//        CountryParser.getInstance(),
//        RatingParser.getInstance(),
//        MPAAParser.getInstance()
//            SoundtrackParser.getInstance(),
//            BiographyParser.getInstance()
    };

    public static void main(String[] args) {
        long tStart = System.currentTimeMillis();

        for (Parser p :
                parsers) {
            long tJobStart = System.currentTimeMillis();
            p.parseFile();
            System.out.println("Took " + (System.currentTimeMillis() - tJobStart) / 1000 + " seconds");
        }

        System.out.println("Finished parsing files. Time elapsed: " + (System.currentTimeMillis() - tStart) / 1000);
    }
}
