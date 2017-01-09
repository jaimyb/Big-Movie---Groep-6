package Parser;

/**
 * Created by jorn on 1/9/17.
 */
public class GenreParser extends Parser {
    private static GenreParser instance;
    private boolean started = false;
    private int linesToStart = 2;

    public static GenreParser getInstance() {
        if(instance == null) instance = new GenreParser();
        return instance;
    }

    private GenreParser() {
        filename = "genres";
        pw = createPrintWriter();
    }

    @Override
    void process(String line) {
        if(started) {
            if(linesToStart > 0) {
                --linesToStart;
                return;
            }

            if(line.equals("")) return;

            String[] splitTabs = line.split("\t");

            // Parse movies
            String movieInfo = MoviesParser.getInstance().parseMovieString(splitTabs[0]);

            // Can't be parsed
            if(movieInfo.equals("")) {
                return;
            }

            writeToStream(movieInfo + formatAsCSV(splitTabs[splitTabs.length - 1]));
        }
        else if(line.equals("8: THE GENRES LIST")){
            started = true;
        }
    }
}
