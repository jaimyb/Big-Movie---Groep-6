package Parser;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by jorn on 12/19/16.
 */
public class Main {

    private static Parser[] parsers = new Parser[]{
            MoviesParser.getInstance(),
            ActorParser.getInstance(),
            ActressParser.getInstance(),
            GenreParser.getInstance(),
            LocationParser.getInstance(),
            CountryParser.getInstance(),
            RatingParser.getInstance(),
            MPAAParser.getInstance(),
            SoundtrackParser.getInstance(),
            BiographyParser.getInstance()
    };

    public static void main(String[] args) {
        long tStart = System.currentTimeMillis();

        ExecutorService tpe = Executors.newFixedThreadPool(4);

        for (Parser p :
                parsers) {
            tpe.execute(p::parseFile);
        }

        tpe.shutdown();
        while(!tpe.isTerminated()) {

        }

        System.out.println("Finished parsing files. Time elapsed: " + (System.currentTimeMillis() - tStart) / 1000);
    }
}
